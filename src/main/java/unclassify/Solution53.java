package unclassify;

//给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
//
//
//
//示例：
//
//输入：[1,12,-5,-6,50,3], k = 4
//输出：12.75
//解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
//
//
//提示：
//
//1 <= k <= n <= 30,000。
//所给数据范围 [-10,000，10,000]。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximum-average-subarray-i
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution53 {
    // 3ms/42.7MB
    public double findMaxAverage(int[] nums, int k) {
        for(int i=1; i<nums.length; i++) {
            nums[i]+= nums[i-1];
        }
        int max = Integer.MIN_VALUE;
        for(int i=k-1; i<nums.length; i++) {
            max = Math.max(nums[i]- (i>=k ? nums[i-k] : 0), max);
        }
        return (double)max/k;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,12,-5,-6,50,3};
        int k = 4;
        System.out.println(new Solution53().findMaxAverage(nums, k));
        nums = new int[]{1,12,-5,-6,50,3};
        k = 1;
        System.out.println(new Solution53().findMaxAverage(nums, k));
    }
}
