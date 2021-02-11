package sort;

//给你一个长度为n的整数数组，请你判断在 最多 改变1 个元素的情况下，该数组能否变成一个非递减数列。
//
//我们是这样定义一个非递减数列的：对于数组中所有的i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
//
//
//
//示例 1:
//
//输入: nums = [4,2,3]
//输出: true
//解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
//示例 2:
//
//输入: nums = [4,2,1]
//输出: false
//解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
//
//
//说明：
//
//1 <= n <= 10 ^ 4
//- 10 ^ 5<= nums[i] <= 10 ^ 5
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/non-decreasing-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution18 {
    // 1ms/40.1MB
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for(int i=0; i<nums.length-1; i++) {
            if(nums[i] > nums[i+1]) {
                if(i > 0) {
                    // 处理i-1, i, i+1位
                    if(nums[i-1] > nums[i+1]) {
                        nums[i+1] = nums[i];
                    }else {
                        nums[i] = nums[i-1];
                    }
                    count++;
                    if(i < nums.length-2 && nums[i+1] > nums[i+2]) {
                        return false;
                    }
                }else if(i < nums.length-2) {
                    // 处理i, i+1, i+2位
                    if(nums[i+1] > nums[i+2]) {
                        return false;
                    }else {
                        nums[i] = nums[i+1];
                    }
                    count++;
                }
                if(count >= 2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,2,3};
        System.out.println(new Solution18().checkPossibility(nums)); // true
        nums = new int[]{4,2,1};
        System.out.println(new Solution18().checkPossibility(nums)); // false
        nums = new int[]{3,4,2,3};
        System.out.println(new Solution18().checkPossibility(nums)); // false
        nums = new int[]{4,2,3};
        System.out.println(new Solution18().checkPossibility(nums)); // true
        nums = new int[]{1,2,7,2,5,9};
        System.out.println(new Solution18().checkPossibility(nums)); // true
        nums = new int[]{5,7,1,8};
        System.out.println(new Solution18().checkPossibility(nums)); // true
        nums = new int[]{1,5,4,6,7,10,8,9};
        System.out.println(new Solution18().checkPossibility(nums)); // false
    }
}
