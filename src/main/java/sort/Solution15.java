package sort;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *     需求:
 *     给定一台电脑，32核，64GB内存，2TB硬盘，硬盘里面有1TB的纯文本数据，
 *     数据的值分别2字节范围内的整型，请用任意编程语言写一个排序算法，完成从大到小的排序
 * </p>
 *
 * <p>
 *     分析:
 *     待排序数据远大于内存，需要分割原始数据，分段排序。步骤如下
 *     1，将原始数据依次读入内存
 *     2，对内存中的数据归并排序
 *     3，将排序后的数据写入分割文件
 *     4，不断合并分割文件，将topK数据写入到排序后的文件
 * </p>
 *
 * <p>
 *     用内存模拟磁盘
 *     总数据量: 1024*10000(个）
 *     分割文件数: 32
 *     内存容量(每次排序数据量): 320000(个)
 * </p>
 *
 * */
public class Solution15 {

    // 数据分割份数
    private static final int PARTS = 32;
    // 数据规模
    private static final int BASE = 10000;
    // 与BASE一起表示数据量
    private static final int SIZE = 1024;
    // 线程数(CPU核数)
    private static final int THREAD_NUM = 32;
    // 内存大小
    private static final int MEMORY_SIZE = SIZE*BASE/PARTS;
    // 位置0表示文件指针(0表示无数据)
    // 待排序数据
    private static final int[] data = new int[SIZE*BASE+1];
    // 排序后数据
    private static final int[] sortedData = new int[SIZE*BASE+1];
    // 模拟分割后的磁盘文件
    private static final int[][] desk = new int[PARTS][SIZE*BASE/PARTS+1];
    // 分割文件选择指针
    private static AtomicInteger pos = new AtomicInteger();
    // 最大k个数(数字数为:SIZE*BASE/PARTS)
    private static int[] topK = null;
    // 合并写入控制器
    private static AtomicInteger mergeCount = new AtomicInteger();
    private static AtomicInteger mergeCountLimit = new AtomicInteger(PARTS);
    // 保存从原始磁盘中读取的数据
    private static BlockingQueue<int[]> inputBuf = new SynchronousQueue<>();
    // 保存已排序且数据量小于内存的分片数据，用于写入磁盘
    private static BlockingQueue<int[]> outputBuf = new SynchronousQueue<>();
    // 保存已排序且数据量小于内存的分片数据
    private static BlockingQueue<int[]> mergeInputBuf = new SynchronousQueue<>();
    // 合并topK后的剩余数据
    private static BlockingQueue<int[]> mergeOutputBuf = new SynchronousQueue<>();
    // 每轮合并之后的最大值，用于写入sortedData
    private static BlockingQueue<int[]> topKBuf = new SynchronousQueue<>();


    private static void helper() throws InterruptedException {
        // 生成测试数据
        generateData();
        // 判断数据顺序
        checkOrder(data);

        ExecutorService pool = Executors.newFixedThreadPool(THREAD_NUM);
        // 读取最初的文件
        pool.execute(()->{
            readTask(data);
        });
        // 对读入内存的数据排序
        pool.execute(()->{
            sortTask();
        });
        // 将排序后的文件写入分割文件
        pool.execute(()->{
            writeTask();
        });
        // 读取分割后的文件
        pool.execute(()->{
            readPartFileTask();
        });
        // 合并分割后的文件
        pool.execute(()->{
            mergeTask();
        });
        // 将topK文件写入排序后的文件
        pool.execute(()->{
            writePartFileTask();
        });
        pool.execute(()->{
            writeTopKTask();
        });
        pool.shutdown();
        // 检查排序结果
        Thread.sleep(2000);
        checkOrder(sortedData);
    }


    /**
     * <p>将磁盘中的原始数据分批读入内存</p>
     *
     * */
    private static void readTask(int[] file) {
        while(file[0]+1 < file.length) {
            try {
                inputBuf.put(Arrays.copyOfRange(file, file[0]+1, MEMORY_SIZE+file[0]+1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            file[0]+= MEMORY_SIZE;
        }
    }

    /**
     * <p>将内存中的数据排序</p>
     *
     * */
    private static void sortTask() {
        while(data[0]+1 < data.length) {
            try {
                int[] temp = inputBuf.take();
                mergeSort(temp, 0, temp.length-1);
                outputBuf.put(temp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <p>将排好序的数据从内存写入磁盘中的分割文件</p>
     *
     * */
    private static void writeTask() {
        while(pos.get() < desk.length) {
            try {
                int[] arr = outputBuf.take();
                System.arraycopy(arr, 0, desk[pos.get()], desk[pos.get()][0]+1, arr.length);
                desk[pos.get()][0]+= arr.length;
                pos.getAndIncrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * <p>将磁盘中的分割文件读入内存</p>
     *
     * */
    private static void readPartFileTask() {
        while(sortedData[0]+1 < sortedData.length) {
            while(pos.get() < desk.length) {
                continue;
            }
            try {
                for(int i=0; i<desk.length; i=(i+1==desk.length?0:i+1)) {
                    if(desk[i][0] > 0) {
                        mergeInputBuf.put(Arrays.copyOfRange(desk[i], 1, desk[i].length));
                        desk[i][0] = 0;
                    }
                    if(sortedData[0]+1 == sortedData.length) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <p>将每次与topK合并后剩余的文件写入磁盘</p>
     *
     * */
    private static void writePartFileTask() {
        while(sortedData[0]+1 < sortedData.length) {
            try {
                for(int i=0; i<desk.length; i=(i+1==desk.length?0:i+1)) {
                    if(desk[i][0] == 0) {
                        int[] arr = mergeOutputBuf.take();
                        System.arraycopy(arr, 0, desk[i], 1, arr.length);
                        desk[i][0]+= arr.length;
                        if(sortedData[0]+1 == sortedData.length) {
                            break;
                        }
                    }
                    if(sortedData[0]+1 == sortedData.length) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <p>将topK从内存写入到磁盘</p>
     *
     * */
    private static void writeTopKTask() {
        while(sortedData[0]+1 < sortedData.length) {
            try {
                int[] arr = topKBuf.take();
                System.arraycopy(arr, 0, sortedData, sortedData[0]+1, arr.length);
                sortedData[0]+= arr.length;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <p>将分割后的文件合并到topK</p>
     *
     * */
    private static void mergeTask() {
        while(sortedData[0]+1 < sortedData.length) {
            try {
                int[] part = mergeInputBuf.take();
                if(topK == null) {
                    topK = Arrays.copyOf(part, part.length);
                    if(mergeCount.incrementAndGet() == mergeCountLimit.get()) {
                        topKBuf.put(Arrays.copyOfRange(topK, 0, topK.length));
                        topK = null;
                        mergeCount.set(0);
                        mergeCountLimit.getAndDecrement();
                        // close
                        mergeOutputBuf.put(part);
                        return;
                    }
                    continue;
                }

                int[] newTopK = new int[topK.length];
                int[] left = new int[part.length];
                // 将part与topK的数据，降序合并到newTopK与left(优先合并到newTopK)
                for(int p0=0, p1=0, p2=0; p0<newTopK.length+part.length; p0++) {
                    int[] arrObj = (p0 < newTopK.length ? newTopK : left);
                    if(p1 >= newTopK.length || (p2 < part.length && topK[p1] > part[p2])) {
                        arrObj[p0%newTopK.length] = (p1 >= newTopK.length ? part[p2++] : topK[p1++]);
                    }else {
                        arrObj[p0%newTopK.length] = (p2 >= part.length ? topK[p1++] : part[p2++]);
                    }
                }
                topK = newTopK;
                mergeOutputBuf.put(left);
                if(mergeCount.incrementAndGet() == mergeCountLimit.get()) {
                    topKBuf.put(Arrays.copyOfRange(topK, 0, topK.length));
                    topK = null;
                    mergeCount.set(0);
                    mergeCountLimit.getAndDecrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * <p>归并排序(desc)</p>
     *
     * @param nums 待排序数组
     * @param start 待排序数组的起始位置
     * @param end 待排序数组的结束位置
     * */
    private static void mergeSort(int[] nums, int start, int end) {
        if(end-start>1) {
            int mid = (start+end)/2;
            mergeSort(nums, start, mid);
            mergeSort(nums, mid+1, end);
            // 数组排序
            int[] temp = new int[end-start+1];
            for(int p0=0, p1=start, p2=mid+1; p0<temp.length; p0++) {
                if(p1 > mid || (p2 <= end && nums[p1] > nums[p2])) {
                    temp[p0] = (p1 > mid ? nums[p2++] : nums[p1++]);
                }else {
                    temp[p0] = (p2 > end ? nums[p1++] : nums[p2++]);
                }
            }
            System.arraycopy(temp, 0, nums, start, temp.length);
        }else if(nums[start] < nums[end]) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }
    }

    /**
     * <p>生成测试数据</p>
     *
     * */
    private static void generateData() {
        for(int j=1, i=Short.MIN_VALUE; j<data.length; j++) {
            data[j] = i;
            i= (i+1 > Short.MAX_VALUE ? Short.MIN_VALUE : i+1);
        }
    }

    /**
     * <p>检查文件中数值的顺序</p>
     *
     * */
    private static void checkOrder(int[] file) {
        for(int i=1; i<file.length-1; i++) {
            if(file[i] < file[i+1]) {
                System.out.println("文件中存在升序序列");
                return;
            }
        }
        System.out.println("文件降序排序成功 !");
    }

    public static void main(String[] args) throws InterruptedException {
        helper();
    }

}
