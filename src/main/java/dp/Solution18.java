package dp;

import java.util.Arrays;

//给定一个整数数组prices ，它的第 i 个元素prices[i] 是一支给定的股票在第 i 天的价格。
//
//设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
//
//注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//
//
//
//示例 1：
//
//输入：k = 2, prices = [2,4,1]
//输出：2
//解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
//示例 2：
//
//输入：k = 2, prices = [3,2,6,5,0,3]
//输出：7
//解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
//随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
//
//
//提示：
//
//0 <= k <= 10^9
//0 <= prices.length <= 1000
//0 <= prices[i] <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution18 {

    // 动态规划
    // 4ms/37.7MB
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        k = Math.min(k, prices.length / 2);

        // buy[i][j] 表示对于数组 prices[0..i] 中的价格而言，进行恰好 j 笔交易，并且当前手上持有一支股票，这种情况下的最大利润；
        // sell[i][j] 表示恰好进行 j 笔交易，并且当前手上不持有股票，这种情况下的最大利润。
        int[][] buy = new int[prices.length][k + 1];
        int[][] sell = new int[prices.length][k + 1];

        buy[0][0] = -prices[0];
        sell[0][0] = 0;
        for (int i = 1; i <= k; ++i) {
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }

        // 如果是第 i 天买入的，那么在第 i−1 天时，我们手上不持有股票，对应状态 sell[i−1][j]，并且需要扣除 prices[i] 的买入花费；
        // 如果不是第 i 天买入的，那么在第 i−1 天时，我们手上持有股票，对应状态 buy[i][j]。那么我们可以得到状态转移方程
        // buy[i][j]=max{buy[i−1][j],sell[i−1][j]−price[i]}

        // 同理对于 sell[i][j]，如果是第 i 天卖出的，那么在第 i−1 天时，我们手上持有股票，对应状态 buy[i−1][j−1]，
        // 并且需要增加 prices[i] 的卖出收益；如果不是第 i 天卖出的，那么在第 i−1 天时，我们手上不持有股票，对应状态
        // sell[i−1][j]。那么我们可以得到状态转移方程
        // sell[i][j]=max{sell[i−1][j],buy[i−1][j−1]+price[i]}

        // 由于在所有的 n 天结束后，手上不持有股票对应的最大利润一定是严格由于手上持有股票对应的最大利润的，
        // 然而完成的交易数并不是越多越好（例如数组 prices 单调递减，我们不进行任何交易才是最优的），因此最终的答案即为 sell[n−1][0..k] 中的最大值

        for (int i = 1; i < prices.length; ++i) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for (int j = 1; j <= k; ++j) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }

        return Arrays.stream(sell[prices.length - 1]).max().getAsInt();
    }


//    public int maxProfit(int k, int[] prices) {
//        int n = prices.length;
//        if (n <= 1) {
//            return 0;
//        }
//        if (k > n / 2) {
//            k = n / 2;
//        }
//        int[][] dp = new int[501][2]; // 交易次数， 是否持存. 最多交易1000 / 2次
//        for (int i = 0; i <= k; i ++) {
//            dp[i][1] = -prices[0];
//        }
//        for (int i = 1; i < n; i ++) {
//            if (k >= 1) {
//                dp[k][0] = Math.max(dp[k][0], dp[k - 1][1] + prices[i]);
//            }
//            for (int j = k - 1; j >= 1; j --) {
//                dp[j][1] = Math.max(dp[j][1], dp[j][0] - prices[i]);
//                dp[j][0] = Math.max(dp[j][0], dp[j - 1][1] + prices[i]);
//            }
//            dp[0][1] = Math.max(dp[0][1], dp[0][0] - prices[i]);
//        }
//        return dp[k][0];
//    }

    public static void main(String[] args) {
        int[] prices = new int[]{2,4,1};
        int k = 2;
        System.out.println(new Solution18().maxProfit(k, prices)); // 2
        prices = new int[]{3,2,6,5,0,3};
        k = 2;
        System.out.println(new Solution18().maxProfit(k, prices)); // 7
    }
}
