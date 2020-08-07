package sort;

import java.util.Arrays;

//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
//
// 
//
//示例 1:
//
//输入: [7,5,6,4]
//输出: 5
// 
//
//限制：
//
//0 <= 数组长度 <= 50000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution1 {

    // 38ms/48.3MB
    // 利用归并排序
    public int reversePairs(int[] nums) {
        int res = divide(nums, 0, nums.length-1);
        return res;
    }

    // 分割数组
    private int divide(int[] nums, int start, int end) {
        // start到end范围内超过3个元素时
        if(start+1 < end) {
            int count = 0;
            int mid = (start+end)/2;
            // 分割数组
            count+= divide(nums, start, mid);
            count+= divide(nums, mid+1, end);
            // 合并数组
            count+= merge(nums, start, mid, mid+1, end);
            // 返回分割与合并过程中计算的逆序对
            return count;
        }else if(start+1 == end) { // start到end范围内只有2个元素时
            // nums[start] > nums[end]时，交换元素，且返回1(即发生一次逆序)
            if(nums[start] > nums[end]) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                return 1;
            }
        }
        return 0;
    }

    // 合并数组
    private int merge(int[] nums, int start1, int end1, int start2, int end2) {
        // 用于保存合并结果
        int[] temp = new int[end2-start1+1];
        // 逆序对的个数
        int count = 0;
        // 双指针合并新数组
        for(int p0=0, p1=start1, p2=start2; p1<=end1 || p2<=end2;) {
            // p1合并完毕，p2有剩余元素时
            if(p1 == end1+1) {
                // 将p2的剩余元素copy到temp
                System.arraycopy(nums, p2, temp, p0, end2-p2+1);
                break;
            }
            // p2合并完毕，p1有剩余元素时
            if(p2 == end2+1) {
                // 将p1的剩余元素copy到temp
                System.arraycopy(nums, p1, temp, p0, end1-p1+1);
                break;
            }
            // 发生逆序时
            if(nums[p1] > nums[p2]) {
                // 将较小的元素赋值给temp，同时移动指针
                temp[p0++] = nums[p2++];
                // 加算逆序对数
                count+= (end1-p1+1);
            }else { // 未发生逆序时，将较小元素赋值给temp，同时移动指针
                temp[p0++] = nums[p1++];
            }
        }
        // 将合并结果覆盖到原数组
        System.arraycopy(temp, 0, nums, start1, temp.length);
        // 返回逆序对数
        return count;
    }

    private void printRangeArray(int[] nums, int start, int end) {
        int[] temp = new int[end-start+1];
        System.arraycopy(nums, start, temp, 0, temp.length);
        System.out.println(Arrays.toString(temp));
    }

//    overtime
//    public int reversePairs(int[] nums) {
//        int count = 0;
//        for(int i=0; i<nums.length; i++) {
//            for(int j=i+1; j<nums.length; j++) {
//                if(nums[i] > nums[j]) {
//                    count++;
//                }
//            }
//        }
//        return count;
//    }

    public static void main(String[] args) {
        int[] nums = new int[]{7,5,6,4}; // 5
        System.out.println(new Solution1().reversePairs(nums));
        nums = new int[]{1,3,2,3,1}; // 4
        System.out.println(new Solution1().reversePairs(nums));
        nums = new int[]{2,4,3,5,1}; // 5
        System.out.println(new Solution1().reversePairs(nums));
        nums = new int[]{5,4,3,2,1}; // 10
        System.out.println(new Solution1().reversePairs(nums));
    }
}
