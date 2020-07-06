package sort;

// https://leetcode-cn.com/problems/sort-an-array/
// 超出时间限制
// 堆排序
// 大顶堆性质 arr[i] >= arr[i*2+1] && arr[i] >= arr[i*2+2]
// 第一个非叶子结点 arr.length/2-1=5/2-1=1，也就是下面的6结点），从左至右，从下至上进行调整。
public class HeapSort {
    // 堆排序
    // 大顶堆性质 arr[i] >= arr[i*2+1] && arr[i] >= arr[i*2+2]
    // 第一个非叶子结点 arr.length/2-1=5/2-1=1，也就是下面的6结点），从左至右，从下至上进行调整。
    public int[] sortArray(int[] input) {
        // 数组长度
        int len = input.length;
        // 每次找到最大值时，将其与(len-1)的值进行交换，交换后len减1
        while(len > 0) {
            // len/2-1为大顶堆的第一个非叶子节点
            // 从左至右，从下至上维护大顶堆
            for(int i=len/2-1; i>=0; i--) {
                // 当前位置i的左叶子节点
                int left = i*2+1;
                // 当前位置i的右叶子节点
                int right = i*2+2>=len ? len-1 : i*2+2;
                // 当前节点i满足大顶堆性质
                if(input[i] >= input[left] && input[i] >= input[right]) {
                    continue;
                }
                // 调整i与其左右节点，直到i与其左右节点满足大顶堆的性质
                while(input[i] < input[left] || input[i] < input[right]) {
                    // 位置i的值小于左叶子节点的值时，交换i与左叶子节点的值
                    if(input[i] < input[left]) {
                        int temp = input[i];
                        input[i] = input[left];
                        input[left] = temp;
                    }
                    // 位置i的值小于右叶子节点的值时，交换i与右叶子节点的值
                    if(input[i] < input[right]) {
                        int temp = input[i];
                        input[i] = input[right];
                        input[right] = temp;
                    }
                }
            }
            // 将(0, len)范围内的input维护成大顶堆之后，将最大值(位置0)交换到len-1
            // 然后将len减1
            int temp = input[0];
            input[0] = input[len-1];
            input[len-1] = temp;
            len = len-1;
        }
        return input;
    }
}
