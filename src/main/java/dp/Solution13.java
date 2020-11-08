package dp;

import java.util.Arrays;

//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
//
//设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
//
//注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//
//
//
//示例 1:
//
//输入: [7,1,5,3,6,4]
//输出: 7
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
//
//
//示例 2:
//
//输入: [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
//因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
//
//
//示例 3:
//
//输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
//
//
//
//提示：
//
//
//1 <= prices.length <= 3 * 10 ^ 4
//0 <= prices[i] <= 10 ^ 4
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution13 {

    // 四状态DP
    // 7ms/38.4MB
    public int maxProfit(int[] prices) {
        // 0 买
        // 1 卖
        // 2 持有
        // 3 无操作
        int[][] dp = new int[prices.length+1][4];
        dp[0][0] = Integer.MIN_VALUE;
        dp[0][1] = Integer.MIN_VALUE;
        dp[0][2] = Integer.MIN_VALUE;
        dp[0][3] = 0;
        for(int i=1; i<dp.length; i++) {
            // 第i天买时获利 = Math.max(第i-1天卖时获利, 第i-1天无操作时获利) - 第i天股票价格
            dp[i][0] = Math.max(dp[i-1][1], dp[i-1][3]) - prices[i-1];
            // 第i天卖时获利 = Math.max(第i-1天买时获利, 第i-1天持有时获利) + 第i天股票价格
            dp[i][1] = Math.max(dp[i-1][0], dp[i-1][2]) + prices[i-1];
            // 第i天持有时获利 = Math.max(第i-1天买时获利, 第i-1天持有时获利)
            dp[i][2] = Math.max(dp[i-1][0], dp[i-1][2]);
            // 第i天无操作时获利 = Math.max(第i-1天卖时获利, 第i-1天无操作时获利)
            dp[i][3] = Math.max(dp[i-1][1], dp[i-1][3]);
        }
        return Math.max(dp[dp.length-1][1], dp[dp.length-1][3]);
    }

    private void printArray(int[][] arr) {
        for(int[] e : arr) {
            System.out.println(Arrays.toString(e));
        }
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4}; // 7
        System.out.println(new Solution13().maxProfit(prices));
        prices = new int[]{1,2,3,4,5}; // 4
        System.out.println(new Solution13().maxProfit(prices));
        prices = new int[]{7,6,4,3,1}; // 0
        System.out.println(new Solution13().maxProfit(prices));
        prices = new int[]{4,1,2}; // 1
        System.out.println(new Solution13().maxProfit(prices));
    }
}
