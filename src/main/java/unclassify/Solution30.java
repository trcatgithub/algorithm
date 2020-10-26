package unclassify;

import java.util.Arrays;

//给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
//
//换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
//
//以数组形式返回答案。
//
// 
//
//示例 1：
//
//输入：nums = [8,1,2,2,3]
//输出：[4,0,1,1,3]
//解释：
//对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
//对于 nums[1]=1 不存在比它小的数字。
//对于 nums[2]=2 存在一个比它小的数字：（1）。
//对于 nums[3]=2 存在一个比它小的数字：（1）。
//对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
//示例 2：
//
//输入：nums = [6,5,4,8]
//输出：[2,1,0,3]
//示例 3：
//
//输入：nums = [7,7,7,7]
//输出：[0,0,0,0]
// 
//
//提示：
//
//2 <= nums.length <= 500
//0 <= nums[i] <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution30 {

    // 1ms/38.5MB
    public int[] smallerNumbersThanCurrent(int[] nums) {
        // 由小到大，保存每个数字出现的次数
        int[] memo = new int[101];
        for(int num : nums) {
            memo[num]++;
        }
        // 将memo的元素变成: 保存小于当前数字的数字的个数
        int sum = 0;
        for(int i=0; i<memo.length; i++) {
            int temp = memo[i];
            memo[i] = sum;
            sum+= temp;
        }
        // 生成结果集
        int[] res = new int[nums.length];
        for(int i=0; i<nums.length; i++) {
            res[i] = memo[nums[i]];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{8,1,2,2,3}; // [4,0,1,1,3]
        System.out.println(Arrays.toString(new Solution30().smallerNumbersThanCurrent(nums)));
        nums = new int[]{6,5,4,8}; // [2,1,0,3]
        System.out.println(Arrays.toString(new Solution30().smallerNumbersThanCurrent(nums)));
        nums = new int[]{7,7,7,7}; // [0,0,0,0]
        System.out.println(Arrays.toString(new Solution30().smallerNumbersThanCurrent(nums)));
        nums = new int[]{7}; // [0,0,0,0]
        System.out.println(Arrays.toString(new Solution30().smallerNumbersThanCurrent(nums)));
    }
}
