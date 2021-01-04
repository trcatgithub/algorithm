package math;

//斐波那契数，通常用F(n) 表示，形成的序列称为 斐波那契数列 。该数列由0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
//
//F(0) = 0，F(1)= 1
//F(n) = F(n - 1) + F(n - 2)，其中 n > 1
//给你 n ，请计算 F(n) 。
//
//
//
//示例 1：
//
//输入：2
//输出：1
//解释：F(2) = F(1) + F(0) = 1 + 0 = 1
//示例 2：
//
//输入：3
//输出：2
//解释：F(3) = F(2) + F(1) = 1 + 1 = 2
//示例 3：
//
//输入：4
//输出：3
//解释：F(4) = F(3) + F(2) = 2 + 1 = 3
//
//
//提示：
//
//0 <= n <= 30
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/fibonacci-number
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution11 {

    // 非递归实现
    // 0ms/35.4MB
    public int fib(int n) {
        int prev1 = 0;
        int prev2 = 1;
        while(n > 0) {
            prev2 = prev1 + prev2;
            prev1 = prev2 - prev1;
            n--;
        }
        return prev1;
    }

//    // 使用缓存
//    // 0ms/35.5MB
//    public int fib(int n) {
//        int[] memo = new int[31];
//        return fib(n, memo);
//    }
//
//    private int fib(int n, int[] memo) {
//        if(n < 2) {
//            return n;
//        }
//        if(memo[n-1] == 0) {
//            memo[n-1] = fib(n-1, memo);
//        }
//        if(memo[n-2] == 0) {
//            memo[n-2] = fib(n-2, memo);
//        }
//        return memo[n-1]+memo[n-2];
//    }

//    // 单纯递归
//    // 8ms/35.1MB
//    public int fib(int n) {
//        if(n < 2) {
//            return n;
//        }
//        return fib(n-1)+fib(n-2);
//    }

    public static void main(String[] args) {
        for(int i=0; i<= 30; i++) {
            System.out.println("i: "+i+"     "+new Solution11().fib(i));
        }
    }
}
