package dp;

import java.util.HashMap;
import java.util.Map;

//给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
//
//示例 1:
//
//输入: 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1。
//示例 2:
//
//输入: 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
//说明: 你可以假设 n 不小于 2 且不大于 58。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/integer-break
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution7 {

    // dp
    // 1ms/36.6MB
    public int integerBreak(int n) {
        // 2，3不参与计算，直接返回
        if(n==2 || n==3) {
            return n-1;
        }
        // 记录每个数字拆分后的最大值
        int[] memo = new int[n+1];
        // 初始化
        memo[1] = 1;
        memo[2] = 2;
        memo[3] = 3;
        // 循环计算每个位置的最大乘积
        for(int i=4; i<=n; i++) {
            for(int j=1; j<i; j++) {
                memo[i] = Math.max(memo[i], memo[i-j]*memo[j]);
            }
        }
        // 返回n对应的最大乘积
        return memo[n];
    }

//    // 递归
//    // 1ms/36.6MB
//    public int integerBreak(int n) {
//        // 处理边界问题
//        if(n == 2 || n == 3) {
//            return n-1;
//        }
//        // 保存计算结果，减少重复计算
//        Map<Integer, Integer> memo = new HashMap<>();
//        // 递归计算
//        return dp(n, memo);
//    }
//
//    private int dp(int n, Map<Integer, Integer> memo) {
//        // 已计算过该n，则直接返回
//        if(memo.containsKey(n)) {
//            return memo.get(n);
//        }
//        // n==2或3时，直接返回n，用于乘算
//        if(n == 3 || n == 2) {
//            return n;
//        }
//        // 最大值
//        int max = 0;
//        // 循环减去1到n-1，计算能达到的最大值
//        for(int i=1; i<n; i++) {
//            max = Math.max(max, i*dp(n-i, memo));
//        }
//        // 保存n对应的最大值
//        memo.put(n, max);
//        return max;
//    }

    public static void main(String[] args) {
        int n = 2; // 1
        System.out.println("n: "+n+"    "+new Solution7().integerBreak(n));
        n = 3; // 2
        System.out.println("n: "+n+"    "+new Solution7().integerBreak(n));
        n = 4; // 4
        System.out.println("n: "+n+"    "+new Solution7().integerBreak(n));
        n = 5; // 6
        System.out.println("n: "+n+"    "+new Solution7().integerBreak(n));
        n = 6; // 9
        System.out.println("n: "+n+"    "+new Solution7().integerBreak(n));
        n = 7; // 12
        System.out.println("n: "+n+"    "+new Solution7().integerBreak(n));
        n = 8; // 18
        System.out.println("n: "+n+"    "+new Solution7().integerBreak(n));
        n = 9; // 27
        System.out.println("n: "+n+"    "+new Solution7().integerBreak(n));
        n = 10; // 36
        System.out.println("n: "+n+"    "+new Solution7().integerBreak(n));
        n = 58; // 1549681956
        System.out.println("n: "+n+"    "+new Solution7().integerBreak(n));
    }
}
