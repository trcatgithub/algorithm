package unclassify;

import java.util.Arrays;

//给定一个数组，将数组中的元素向右移动k个位置，其中k是非负数。
//
//示例 1:
//
//输入: [1,2,3,4,5,6,7] 和 k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
//示例2:
//
//输入: [-1,-100,3,99] 和 k = 2
//输出: [3,99,-1,-100]
//解释:
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100]
//说明:
//
//尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
//要求使用空间复杂度为O(1) 的原地算法。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/rotate-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution48 {

    // 1ms/39.1MB
    public void rotate(int[] nums, int k) {
        if(nums.length <= 1) {
            return;
        }
        k%= nums.length;
        int[] memo = new int[k+1];

        for(int i=0; i<nums.length; i++) {
            int prev = (i-k >= 0 ? i-k : nums.length-k+i);
            memo[i%(k+1)] = nums[i];
            nums[i] = (i >= k ? memo[prev%(k+1)] : nums[prev]);
        }
    }


//    // 暴力
//    // 277ms/38.8MB
//    public void rotate(int[] nums, int k) {
//        if(nums.length == 0) {
//            return;
//        }
//        while(k-- > 0) {
//            int val = nums[0];
//            int temp = 0;
//            for(int i=1; i<nums.length; i++) {
//                temp = nums[i];
//                nums[i] = val;
//                val = temp;
//            }
//            nums[0] = val;
//        }
//    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        int k = 3;
        new Solution48().rotate(nums, k);
        System.out.println(Arrays.toString(nums));// 5,6,7,1,2,3,4
        nums = new int[]{-1,-100,3,99};
        k = 2;
        new Solution48().rotate(nums, k);
        System.out.println(Arrays.toString(nums));// 3,99,-1,-100
        nums = new int[]{-1};
        k = 2;
        new Solution48().rotate(nums, k);
        System.out.println(Arrays.toString(nums));// -1
        nums = new int[]{-1,1};
        k = 3;
        new Solution48().rotate(nums, k);
        System.out.println(Arrays.toString(nums));// 1 -1
    }
}
