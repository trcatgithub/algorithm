package sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
//
// 
//
//示例 1:
//
//输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
//示例 2:
//
//输入: nums = [1], k = 1
//输出: [1]
// 
//
//提示：
//
//你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
//你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
//题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
//你可以按任意顺序返回答案。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/top-k-frequent-elements
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution2 {

    // 利用小顶堆进行降序排序
    // 15ms/42.1MB
    public int[] topKFrequent(int[] nums, int k) {
        // 结果集
        int[] res = new int[k];
        // 统计每个数字出现次数
        Map<Integer, Integer> memo = new HashMap<>();
        for(int num : nums) {
            if(memo.containsKey(num)) {
                memo.put(num, memo.get(num)+1);
            }else {
                memo.put(num, 1);
            }
        }
        // temp[x][0] 表示数值出现次数
        // temp[x][1] 表示数值
        int[][] temp = new int[memo.size()][2];
        int pointer = 0;
        for(Map.Entry<Integer, Integer> element : memo.entrySet()) {
            temp[pointer][1] = element.getKey();
            temp[pointer][0] = element.getValue();
            pointer++;
        }
        int len = temp.length;
        // 使用小顶堆降序排序
        // 每次找到最小值时，将其与(len-1)的值进行交换，交换后len减1
        while(len > k) {
            // len/2-1为小顶堆的第一个非叶子节点
            // 从左至右，从下至上维护小顶堆
            for(int i=len/2-1; i>=0; i--) {
                int left = i*2+1;
                int right = i*2+2 > len-1 ? len-1 : i*2+2;
                // 调整i与其左右节点，直到i与其左右节点满足小顶堆的性质
                while(temp[i][0] > temp[left][0] || temp[i][0] > temp[right][0]) {
                    if(temp[left][0] < temp[i][0]) {
                        int[] swap = temp[left];
                        temp[left] = temp[i];
                        temp[i] = swap;
                    }
                    if(temp[right][0] < temp[i][0]) {
                        int[] swap = temp[right];
                        temp[right] = temp[i];
                        temp[i] = swap;
                    }
                }
            }
            // 将(0, len)范围内的input维护成小顶堆之后，将最大值(位置0)交换到len-1
            // 然后将len减1
            int[] swap = temp[0];
            temp[0] = temp[len-1];
            temp[len-1] = swap;
            len--;
        }

        for(int i=0; i<k; i++) {
            res[i] = temp[i][1];
        }
        return res;
    }

//    // 12ms/42.1MB
//    // 基于双指针快速排序
//    public int[] topKFrequent(int[] nums, int k) {
//        // 结果集
//        int[] res = new int[k];
//        // 统计每个数字出现次数
//        Map<Integer, Integer> memo = new HashMap<>();
//        for(int num : nums) {
//            if(memo.containsKey(num)) {
//                memo.put(num, memo.get(num)+1);
//            }else {
//                memo.put(num, 1);
//            }
//        }
//        // temp[x][0] 表示数值出现次数
//        // temp[x][1] 表示数值
//        int[][] temp = new int[memo.size()][2];
//        int pointer = 0;
//        for(Map.Entry<Integer, Integer> element : memo.entrySet()) {
//            temp[pointer][1] = element.getKey();
//            temp[pointer][0] = element.getValue();
//            pointer++;
//        }
//        // 双指针快排
//        // 在k左侧寻找小于k对应的值， 与k右侧大于k对应的值进行交换
//        for(int l=0,r=temp.length-1; l<=r;) {
//            // 左右指针未到达k位置时
//            if(l<k && r>k) {
//                // 找到能够交换的两调记录
//                if(temp[l][0] < temp[k][0] && temp[r][0] >= temp[k][0]) {
//                    // 交换数据
//                    swap(temp, l, r);
//                    // 两端指针向中心移动
//                    l++;
//                    r--;
//                }else if(temp[l][0] < temp[k][0] && temp[r][0] < temp[k][0]) { // 在k右侧寻找比k对应的值大的值
//                    r--;
//                }else if(temp[l][0] >= temp[k][0]) { // 在k左侧寻找比k对应的值小的值
//                    l++;
//                }
//            }else if(l==k && r>k) { // 左指针到达k，右指针未到达k时
//                if(temp[l][0] < temp[r][0]) { // 右指针对应记录值 大于 k对应记录值时
//                    // 交换k与r的值
//                    swap(temp, l, r);
//                    // 将k左侧最小的值与k交换
//                    for(int i=0; i<=k; i++) {
//                        if(temp[i][0] < temp[k][0]) {
//                            swap(temp, i, k);
//                        }
//                    }
//                }
//                // 右指针左移
//                r--;
//            }else if(r==k && l<k) { // 右指针到达k，左指针未到达k时
//                if(temp[l][0] < temp[r][0]) { // 左指针对应记录值 小于 k对应记录值时
//                    // 交换k与l的值
//                    swap(temp, l, r);
//                }
//                break;
//            }else {
//                break;
//            }
//        }
//        // 转换结果集
//        for(int i=0; i<res.length; i++) {
//            res[i] = temp[i][1];
//        }
//
//        return res;
//    }
//
//    // 交换数组元素
//    private void swap(int[][] nums, int p1, int p2) {
//        int[] temp = nums[p1];
//        nums[p1] = nums[p2];
//        nums[p2] = temp;
//    }

    private void printArray(int[][] arr) {
        System.out.println("-------------------");
        for(int[] e : arr) {
            System.out.println(Arrays.toString(e));
        }
        System.out.println("-------------------");
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2,2,3};
        int k = 2;
        System.out.println(Arrays.toString(new Solution2().topKFrequent(nums, k)));// 1,2
        nums = new int[]{1};
        k = 1;
        System.out.println(Arrays.toString(new Solution2().topKFrequent(nums, k))); // 1
        nums = new int[]{3,0,1,0};
        k = 1;
        System.out.println(Arrays.toString(new Solution2().topKFrequent(nums, k))); // 0
        nums = new int[]{4,1,-1,2,-1,2,3};
        k = 2;
        System.out.println(Arrays.toString(new Solution2().topKFrequent(nums, k))); // -1,2
        nums = new int[]{5,2,5,3,5,3,1,1,3};
        k = 2;
        System.out.println(Arrays.toString(new Solution2().topKFrequent(nums, k))); // 3,5
    }

}
