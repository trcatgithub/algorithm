package dp;

import java.util.Arrays;

//给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
//
//你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
//
//返回获得利润的最大值。
//
//注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
//
//示例 1:
//
//输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
//输出: 8
//解释: 能够达到的最大利润:
//在此处买入 prices[0] = 1
//在此处卖出 prices[3] = 8
//在此处买入 prices[4] = 4
//在此处卖出 prices[5] = 9
//总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
//注意:
//
//0 < prices.length <= 50000.
//0 < prices[i] < 50000.
//0 <= fee < 50000.
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution16 {

    // 贪心
    // 使用变量替换dp数组
    // 4ms/47.5MB
    public int maxProfit(int[] prices, int fee) {
        int prevBuy = -prices[0];
        int prevSell = 0;
        for(int i=1; i<prices.length; i++) {
            // 本次买时 最大收益
            int currentBuy = Math.max(prevSell - prices[i], prevBuy);
            // 本次卖时 最大收益
            int currentSell = Math.max(prevBuy + prices[i] - fee, prevSell);
            prevBuy = currentBuy;
            prevSell = currentSell;
        }
        return prevSell;
    }

//    // 两状态dp
//    // 24ms/47.9MB
//    public int maxProfit(int[] prices, int fee) {
//        int[][] dp = new int[prices.length][2];
//        // buy/hold
//        dp[0][0] = -prices[0];
//        // sell/blank
//        dp[0][1] = 0;
//        for(int i=1; i<dp.length; i++) {
//            // 本次买时 最大收益
//            dp[i][0] = Math.max(dp[i-1][1] - prices[i], dp[i-1][0]);
//            // 本次卖时 最大收益
//            dp[i][1] = Math.max(dp[i-1][0] + prices[i] - fee, dp[i-1][1]);
//        }
//        return dp[dp.length-1][1];
//    }

//    // 四状态dp
//    // 30ms/48MB
//    public int maxProfit(int[] prices, int fee) {
//        int[][] dp = new int[prices.length][4];
//        // buy
//        dp[0][0] = -prices[0];
//        // sell
//        dp[0][1] = 0;
//        // hold
//        dp[0][2] = Integer.MIN_VALUE;
//        // blank
//        dp[0][3] = 0;
//        for(int i=1; i<dp.length; i++) {
//            dp[i][0] = Math.max(dp[i-1][1], dp[i-1][3]) - prices[i];
//            dp[i][1] = Math.max(dp[i-1][0], dp[i-1][2]) + prices[i] - fee;
//            dp[i][2] = Math.max(dp[i-1][0], dp[i-1][2]);
//            dp[i][3] = Math.max(dp[i-1][1], dp[i-1][3]);
//        }
//        return Math.max(dp[dp.length-1][1], dp[dp.length-1][3]);
//    }

    private static void printArray(int[][] arr) {
        for(int[] e : arr) {
            System.out.println(Arrays.toString(e));
        }
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println(new Solution16().maxProfit(prices, fee)); // 8
        prices = new int[]{1,2,3,0,2};
        fee = 1;
        System.out.println(new Solution16().maxProfit(prices, fee)); // 2
        prices = new int[]{1};
        fee = 0;
        System.out.println(new Solution16().maxProfit(prices, fee)); // 0
        prices = new int[]{1,3,7,5,10,3};
        fee = 3;
        System.out.println(new Solution16().maxProfit(prices, fee)); // 6
    }
}
