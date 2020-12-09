package dp;

//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
//
//机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
//
//问总共有多少条不同的路径？
// S # # # # # #
// # # # # # # #
// # # # # # # F
//例如，上图是一个7 x 3 的网格。有多少可能的路径？
//
// 
//
//示例 1:
//
//输入: m = 3, n = 2
//输出: 3
//解释:
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向右 -> 向下
//2. 向右 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向右
//示例 2:
//
//输入: m = 7, n = 3
//输出: 28
// 
//
//提示：
//
//1 <= m, n <= 100
//题目数据保证答案小于等于 2 * 10 ^ 9
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/unique-paths
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution15 {

    // S # #    S # # #
    // # # #    # # # #
    // # # #    # # # F
    // # # F                 10

    // S 1 1
    // 1 2 3
    // 1 3 6
    // 1 4 F
    // 动态规划
    // dp[i][j] = dp[i-1][j] + dp[i][j-1];
    // 0ms/35.6MB
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(i == 0 && j == 0) {
                    continue;
                }
                if(i-1 >= 0) {
                    dp[i][j]+= dp[i-1][j];
                }
                if(j-1 >= 0) {
                    dp[i][j]+= dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

//    // 个别用例超时
//    // 基于dfs，每次向右或向下移动。遇到右下角时，路径加1
//    public int uniquePaths(int m, int n) {
//        return dfs(m, n, 0, 0);
//    }
//
//    private int dfs(int m, int n, int x, int y) {
//        if(x < 0 || y < 0 || x == m || y == n) {
//            return 0;
//        }
//        if(x == m-1 && y == n-1) {
//            return 1;
//        }
//        return dfs(m, n, x+1, y) + dfs(m, n, x, y+1);
//    }

    public static void main(String[] args) {
        //   S      S F
        //   F                   1

        // S #
        // # F                   2

        // S # #    S #
        // # # F    # #
        //          # F          3

        // S # #    S 2 2
        // # # #    2 1 1
        // # # F    2 1 F        6

        // S # #    S # # #
        // # # #    # # # #
        // # # #    # # # F
        // # # F                 10

        // S 3 3
        // 3 2 2
        // 2 1 1
        // 2 1 F



        // S # # #
        // # # # #
        // # # # #
        // # # # F               20

        // S # # # # # #
        // # # # # # # #
        // # # # # # # F      28

        //
        System.out.println(new Solution15().uniquePaths(1, 1)); // 1
        System.out.println(new Solution15().uniquePaths(1, 2)); // 1
        System.out.println(new Solution15().uniquePaths(2, 1)); // 1
        System.out.println(new Solution15().uniquePaths(2, 2)); // 2
        System.out.println(new Solution15().uniquePaths(2, 3)); // 3
        System.out.println(new Solution15().uniquePaths(3, 2)); // 3
        System.out.println(new Solution15().uniquePaths(3, 3)); // 6
        System.out.println(new Solution15().uniquePaths(3, 4)); // 10
        System.out.println(new Solution15().uniquePaths(4, 3)); // 10
        System.out.println(new Solution15().uniquePaths(4, 4)); // 20
        System.out.println(new Solution15().uniquePaths(7, 3)); // 28
        System.out.println(new Solution15().uniquePaths(15, 15)); // 40116600
        System.out.println(new Solution15().uniquePaths(20, 20)); // 985525432
        // 2000000000
        //
    }
}
