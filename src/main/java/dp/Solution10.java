package dp;

import java.util.Arrays;

//给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，
//然后玩家 1 拿，…… 。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
//
//给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
//
// 
//
//示例 1：
//
//输入：[1, 5, 2]
//输出：False
//解释：一开始，玩家1可以从1和2中进行选择。
//如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。
//所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
//因此，玩家 1 永远不会成为赢家，返回 False 。
//示例 2：
//
//输入：[1, 5, 233, 7]
//输出：True
//解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
//最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 True，表示玩家 1 可以成为赢家。
// 
//
//提示：
//
//1 <= 给定的数组长度 <= 20.
//数组里所有分数都为非负数且不会大于 10000000 。
//如果最终两个玩家的分数相等，那么玩家 1 仍为赢家。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/predict-the-winner
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution10 {

    // 1ms/36.9MB
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        // 每一位表示
        // 选择当前数值时，玩家1比玩家2 分数之差的最大值
        int[] memo = Arrays.copyOf(nums, len);
        // +++[1, 5, 233, 7]
        // ---[1, 5, 233, 226]
        // +++[1, 5, 233, 226]
        // ---[1, 5, 228, -221]
        // +++[1, 5, 228, -221]
        // ---[1, 4, 229, 222]
        for(int i=len-2; i>=0; i--) {
//            System.out.println("+++"+Arrays.toString(memo));
            for(int j=i+1; j<len; j++) {
                memo[j] = Math.max(nums[i]-memo[j], nums[j]-memo[j-1]);
            }
//            System.out.println("---"+Arrays.toString(memo));
        }
        return  memo[len-1] >= 0;
    }

//    // 1, 5, 233, 7
//    // 1, 5, 233, 7, 8
//    // 1, 5, 7, 233, 8
//    // 1, 2, 3, 223, 4, 5, 6
//    // 1, 2, 3, 4, 223, 5, 6
//    // 1, 2, 3, 4, 5, 223, 6
//    public boolean PredictTheWinner(int[] nums) {
//        // 保存计算结果，防止重复计算
//        int[][] memo = new int[nums.length][nums.length];
//
//        return helper(nums, memo, 0, nums.length-1) >= 0;
//    }
//
//    // 递归函数定义为：
//    // 计算当前做选择的玩家能赢过对手的分数，如果大于零，则表示他在这个子问题中赢了
//    // 怎么计算呢？当前选择的分数，减去，接下来对手赢过自己的分数（剩余数组的递归结果）
//    // 0ms/37.2MB
//    private int helper(int[] nums, int[][] memo, int left, int right) {
//        if(left == right) {
//            return nums[left];
//        }
//        // 防止重复计算
//        if(memo[left][right] > 0) {
//            return memo[left][right];
//        }
//        int player1 = nums[left] - helper(nums, memo, left+1, right);
//        int player2 = nums[right] - helper(nums, memo, left, right-1);
//        int max = Math.max(player1, player2);
////        System.out.println("left: "+nums[left]+"   right: "+nums[right]+"    max: "+max +"   player1: "+player1+"   player2: "+player2);
//        // 将本次计算结果保存到memo
//        memo[left][right] = max;
//        return max;
//    }

    private void printArray(int[][] arr) {
        for(int[] element : arr) {
            System.out.println(Arrays.toString(element));
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 233, 7, 9};
//        System.out.println(new Solution10().PredictTheWinner(nums));
        nums = new int[]{1, 3, 2};
//        System.out.println(new Solution10().PredictTheWinner(nums));
        nums = new int[]{1, 5, 233, 7};
        System.out.println(new Solution10().PredictTheWinner(nums));
    }



























//    public boolean PredictTheWinner(int[] nums) {
//        int length = nums.length;
//        int[] dp = new int[length];
//        for (int i = 0; i < length; i++) {
//            dp[i] = nums[i];
//        }
//        for (int i = length - 2; i >= 0; i--) {
//            for (int j = i + 1; j < length; j++) {
//                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
//            }
//        }
//        return dp[length - 1] >= 0;
//    }
}
