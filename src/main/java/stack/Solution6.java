package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
//
//连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，
//都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1],
//nums[r]] 就是连续递增子序列。
//
//
//
//示例 1：
//
//输入：nums = [1,3,5,4,7]
//输出：3
//解释：最长连续递增序列是 [1,3,5], 长度为3。
//尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
//
//
//示例 2：
//
//输入：nums = [2,2,2,2,2]
//输出：1
//解释：最长连续递增序列是 [2], 长度为1。
//
//
//
//
//提示：
//
//
//0 <= nums.length <= 10^4
//-10^9 <= nums[i] <= 10^9
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution6 {
    // 类似栈
    // 1ms/39.4MB
    public int findLengthOfLCIS(int[] nums) {
        int max = 0;
        int len = 0;
        int prev = Integer.MIN_VALUE;
        for(int num : nums) {
            if(num > prev) {
                len++;
            }else {
                max = Math.max(max, len);
                len = 1;
            }
            prev = num;
        }
        return Math.max(max, len);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,4,7}; // 3
        System.out.println(new Solution6().findLengthOfLCIS(nums));
        nums = new int[]{2,2,2,2,2}; // 1
        System.out.println(new Solution6().findLengthOfLCIS(nums));
        nums = new int[]{1,3,5,4,2,3,4,5}; // 4
        System.out.println(new Solution6().findLengthOfLCIS(nums));
    }
}
