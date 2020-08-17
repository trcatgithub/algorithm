package dp;

import java.util.Arrays;

//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
//
// 
//
//示例 1:
//
//输入: coins = [1, 2, 5], amount = 11
//输出: 3
//解释: 11 = 5 + 5 + 1
//示例 2:
//
//输入: coins = [2], amount = 3
//输出: -1
// 
//
//说明:
//你可以认为每种硬币的数量是无限的。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/coin-change
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution9 {

    public int coinChange(int[] coins, int amount) {
        // amount为0时无需硬币
        if(amount == 0) {
            return 0;
        }
        // 保存每个amount所需最小硬币数
        int[] memo = new int[amount+1];
        // 递归dp
        dp(coins, memo, amount);
        System.out.println(Arrays.toString(memo));
        // 返回amount对应的最小值
        return memo[amount];
    }

    // 递归dp
    // 22ms/39.8MB
    private int dp(int[] coins, int[] memo, int amount) {
        // 计算当前amount选择不同coin所需要最小硬币数
        int min = Integer.MAX_VALUE;
        // 选择硬币
        for(int coin : coins) {
            //
            if(amount > coin){
                // 获得amount-coin对应的最小硬币数
                int dpRes = 0;
                if(memo[amount-coin] > 0) { // 已计算过该值时，直接从memo中获取
                    dpRes = memo[amount-coin];
                }else if(memo[amount-coin] == -1){ // 该值不可达时，跳过本次选择，继续下一个面值的coin
                    continue;
                }else { // 计算amount-coin对应的最小硬币数
                    dpRes = dp(coins, memo, amount-coin);
                }
                // 该值不可达时，跳过本次选择，继续下一个面值的coin
                if(dpRes == -1) {
                    continue;
                }
                // 计算最小硬币数
                min = Math.min(min, dpRes+1);
            }else if(amount == coin) { // amount刚好减为0时，返回1
                memo[amount] = 1;
                return 1;
            }
        }
        // 计算最小值(-1表示该选择不可达)
        min = min == Integer.MAX_VALUE ? -1 : min;
        // 保存计算结果
        memo[amount] = min;
        return min;
    }

//    // 251ms/40.5MB
//    public int coinChange(int[] coins, int amount) {
//        // 金额为0时需要0个硬币
//        if(amount == 0) {
//            return 0;
//        }
//        // key : amount
//        // value : 每个amount所需最小硬币数
//        Map<Integer, Integer> memo = new HashMap<>();
//        // 初始化
//        for(int coin : coins) {
//            memo.put(coin, 1);
//        }
//        // 从1计算到amount
//        for(int i=1; i<=amount; i++) {
//            // 选择不同硬币时，最小硬币数
//            int min = Integer.MAX_VALUE;
//            // 选择不同硬币
//            for(int coin : coins) {
//                // 防止重复计算
//                if(memo.containsKey(i)) {
//                    min = memo.get(i);
//                    break;
//                }
//                // 当memo中存在i-coin时，更新最小值min为memo.get(i-coin)+1。
//                // (此最小值是选择当前面值coin的最小值，需要遍历所有可能，取得全局最小值)
//                if(memo.containsKey(i-coin)) {
//                    min = Math.min(min, memo.get(i-coin)+1);
//                }
//            }
//            // 防止重复计算
//            if(min < Integer.MAX_VALUE) {
//                // 保存计算结果到memo
//                memo.put(i, min);
//            }
//        }
//        // 获取amount对应的最小值
//        return memo.getOrDefault(amount, -1);
//    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
//        System.out.println(new SolutionX().coinChange(coins, amount)); // 3
//        coins = new int[]{2};
//        amount = 3;
//        System.out.println(new SolutionX().coinChange(coins, amount)); // -1
//        coins = new int[]{2,5,10,1};
//        amount = 27;
//        System.out.println(new SolutionX().coinChange(coins, amount)); // 4
//        coins = new int[]{186,419,83,408};
//        amount = 6249;
//        System.out.println(new SolutionX().coinChange(coins, amount)); // 20
//        coins = new int[]{1};
//        amount = 0;
//        System.out.println(new SolutionX().coinChange(coins, amount)); // 0
        coins = new int[]{1};
        amount = 1;
        System.out.println(new Solution9().coinChange(coins, amount)); // 1
    }

}
