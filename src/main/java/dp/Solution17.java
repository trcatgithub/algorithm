package dp;

// 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
//
// 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
//
// 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
//
// 示例 1:
//
// 输入: cost = [10, 15, 20]
// 输出: 15
// 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
//  示例 2:
//
// 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
// 输出: 6
// 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
// 注意：
//
// cost 的长度将会在 [2, 1000]。
// 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution17 {
    // // 动态规划
    // // 从下标 i−1 使用 cost[i−1] 的花费达到下标 i，
    // // 或者从下标 i−2 使用 cost[i−2] 的花费达到下标 i。
    // // 为了使总花费最小，dp[i] 应取上述两项的最小值，因此状态转移方程如下
    // // dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2])
    // // 1ms/38.3MB
    // public int minCostClimbingStairs(int[] cost) {
    //     int[] dp = new int[cost.length + 1];
    //     dp[0] = dp[1] = 0;
    //     for (int i=2; i<=cost.length; i++) {
    //         dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
    //     }
    //     return dp[cost.length];
    // }

    // 压缩空间（贪心）
    // 1ms/38.1MB
    public int minCostClimbingStairs(int[] cost) {
        int prev1 = 0;
        int prev2 = 0;
        for(int i=2; i<=cost.length; i++) {
            int temp = Math.min(prev2 + cost[i-1], prev1 + cost[i-2]);
            prev1 = prev2;
            prev2 = temp;
        }
        return prev2;
    }
}
