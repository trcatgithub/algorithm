package dp;

//https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
//给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
//
//设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
//
//你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
//示例:
//
//输入: [1,2,3,0,2]
//输出: 3
//解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
//2ms/39.6MB

public class Solution2 {
    // 1,2,3,0,2
    // 4,0,3,1,9
    // 0,1,2,2,2

    //  四种选择的dp
    //  对于每一个股票，有下列四种操作
    //  memo[?][0] 买入
    //  memo[?][1] 卖出
    //  memo[?][2] 持有
    //  memo[?][3] 冷冻
    public int maxProfit(int[] prices) {
        if(prices.length > 0) {
            // 用于记录每种选择在当前位置的最大值
            int[][] memo = new int[prices.length][4];
            // 开始遍历
            for(int i=1; i<prices.length; i++) {
                // 本次选择买入时，对应的最大值(即前次选择冷冻的最大值)
                memo[i][0] = memo[i-1][3];
                // 本次选择卖出时，对应的最大值
                // 1,前次选择买入时最大值
                // 2,前次选择持有时最大值
                // 1,2中取较大的值作为本次卖出的最大值
                memo[i][1] = Math.max(memo[i-1][0]+prices[i]-prices[i-1], memo[i-1][2]+prices[i]-(prices[i-1]-(memo[i-1][1]-memo[i-1][2])));
                // 不做赔本买卖(选择卖出值为负值时，将对应值归零)
                memo[i][1] = Math.max(memo[i][1], 0);
                // 本次选择持有时，对应的最大值(即前次买入 与 前次持有中较大的值)
                memo[i][2] = Math.max(memo[i-1][0], memo[i-1][2]);
                // 本次选择冷冻时，对应的最大值(即前次卖出 与 前次冷冻中较大的值)
                memo[i][3] = Math.max(memo[i-1][1], memo[i-1][3]);
                if(i == prices.length-1) {
                    return Math.max(Math.max(memo[i][0], memo[i][1]), Math.max(memo[i][2], memo[i][3]));
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1,2,3,0,2}; //3
//        prices = new int[]{9,2,3,0,2}; //2
//        prices = new int[]{1,2,4}; //3
//        prices = new int[]{4,0,3,1,9}; //9
//        prices = new int[]{2,1,4,5,2,9,7}; //10
//        prices = new int[]{1,2,4,2,5,7,2,4,9,0}; //11
//        prices = new int[]{6,1,6,4,3,0,2}; //7
        System.out.println(new Solution2().maxProfit(prices));
    }

}
