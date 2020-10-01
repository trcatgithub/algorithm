package dp;

//小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves，
//字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
//出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。
//每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。
//请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。
//示例 1：
//
//输入：leaves = "rrryyyrryyyrr"
//输出：2
//解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"
//
//示例 2：
//
//输入：leaves = "ryr"
//输出：0
//解释：已符合要求，不需要额外操作
//
//提示：
//
//3 <= leaves.length <= 10^5
//leaves 中只包含字符 'r' 和字符 'y'
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/UlBDOe
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution11 {

    // 类似买股票的最佳时机
    // 设置第几片处于某个状态，下一片只能从上一片的某个状态转移而来
    // 84ms/47.7MB
    public int minimumOperations(String leaves) {
        if (leaves == null || leaves.length() == 0) {
            return 0;
        }

        // dp[i][0] 表示第i片树叶处于左侧“r”时，所需要的最少替换次数
        // dp[i][1] 表示第i片树叶处于中间“y”时，所需要的最少替换次数
        // dp[i][2] 表示第i片树叶处于右侧“r”时，所需要的最少替换次数
        // dp[i][j]只能由dp[i - 1][k]转移而来，而k必须<=j
        int[][] dp = new int[leaves.length()][3];
        dp[0][0] = (leaves.charAt(0) == 'y' ? 1 : 0);
        dp[0][1] = Integer.MAX_VALUE;
        dp[0][2] = Integer.MAX_VALUE;
        dp[1][2] = Integer.MAX_VALUE;
        // rrryyyrryyyrr
        for (int i = 1;i < leaves.length();i++) {
            // 把当前树叶换成“左侧r”所需总次数为 把前一位置树叶换成“左侧r”所用次数
            // 加上
            // 当前位置所用 次数(1或0，当前位置为“y”时，需要1次，为“r”时，需要0次)
            dp[i][0] = dp[i - 1][0] + (leaves.charAt(i) == 'y' ? 1 : 0);
            // 把当前树叶换成“y”所需总次数为
            // 前一位置换成“左侧r” 与 “中间y”所需次数中较小的一个
            // 加上
            // 替换当前位置 所用次数(1或0，当前位置为“r”时，需要1次，为“y”时，需要0次)
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + (leaves.charAt(i) == 'r' ? 1 : 0);
            if (i >= 2) {
                // 把当前树叶替换为“右侧r”所需总次数为
                // 前一位置换为“中间y”所需次数 与 “右侧r”所需次数中较小的一个
                // 加上
                // 替换当前位置 所用次数(1或0，当前位置为“y”时，需要1次，为“r”时，需要0次)
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + (leaves.charAt(i) == 'y' ? 1 : 0);
            }
        }
        return dp[dp.length - 1][2];
    }

    public static void main(String[] args) {
        System.out.println(new Solution11().minimumOperations("rrryyyrryyyrr")); // 2
        System.out.println(new Solution11().minimumOperations("ryr")); // 0
        System.out.println(new Solution11().minimumOperations("yry")); // 3
        System.out.println(new Solution11().minimumOperations("ryrrrrrrrrrrrrrryr")); // 1
    }
}
