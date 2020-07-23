package dp;

//给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
//
//说明：每次只能向下或者向右移动一步。
//
//示例:
//
//输入:
//[
//[1,3,1],
//[1,5,1],
//[4,2,1]
//]
//输出: 7
//解释: 因为路径 1→3→1→1→1 的总和最小。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/minimum-path-sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
//dfs 37ms/44.4MB
//dp  4 ms/42.4MB
public class Solution5 {

    // 动态规划
    public int minPathSum(int[][] grid) {
        // 数组行数
        int x = grid.length;
        // 数组列数
        int y = grid[0].length;
        // 记录每个位置为止的最短路径
        int[][] memo = new int[x][y];
        // 遍历每一个位置
        for(int i=0; i<x; i++) {
            for(int j=0; j<y; j++) {
                // 将当前位置值赋值给memo
                memo[i][j] = grid[i][j];
                // 未到达边界时
                if(i >= 1 && j >= 1) {
                    // memo累加左上中较小的值
                    memo[i][j] += Math.min(memo[i-1][j], memo[i][j-1]);
                }else if(i >= 1 && j == 0) { // 到达右边界时
                    memo[i][j] += memo[i-1][j]; // memo累加上方最小路径和
                }else if(i == 0 && j >= 1) { // 到达下边界时
                    memo[i][j] += memo[i][j-1]; // memo累加左侧最小路径和
                }
            }
        }
        // 返回到达右下角位置时最短路径和
        return memo[x-1][y-1];
    }

//    // 记录路径和，减少重复计算
//    private Map<String, Integer> memo = new HashMap<>();
//
//    public int minPathSum(int[][] grid) {
//        return dfs(grid, 0, 0);
//    }
//
//    // dfs向右下移动
//    // 37ms/44.4MB
//    private int dfs(int[][] grid, int x, int y) {
//        // 到达边界时返回0
//        if(x == grid.length || y == grid[x].length) {
//            return 0;
//        }
//        // 路径和的较小值
//        int min = 0;
//        // 向下已达边界时，则下次向右移动
//        if(x == grid.length-1) {
//            min = memo.containsKey(x+":"+(y+1)) ? memo.get(x+":"+(y+1)) : dfs(grid, x, y+1);
//        }else if(y == grid[x].length-1) { // 向右已达边界时，则下次向下移动
//            min = memo.containsKey((x+1)+":"+y) ? memo.get((x+1)+":"+y) : dfs(grid, x+1, y);
//        }else { // 未达边界时，选取较小的路径和
//            min = Math.min(memo.containsKey((x+1)+":"+y) ? memo.get((x+1)+":"+y) : dfs(grid, x+1, y),
//                    memo.containsKey(x+":"+(y+1)) ? memo.get(x+":"+(y+1)) : dfs(grid, x, y+1));
//        }
//        memo.put(x+":"+y, min+grid[x][y]);
//        // 返回较小路径和+当前位置值
//        return min+grid[x][y];
//    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,3,1},{1,5,1},{4,2,1}}; // 7
        grid = new int[][]{{1,3,1},{1,5,1},{1,2,1},{1,1,1}};// 6
        grid = new int[][]{{3,8,6,0,5,9,9,6,3,4,0,5,7,3,9,3},{0,9,2,5,5,4,9,1,4,6,9,5,6,7,3,2},{8,2,2,3,3,3,1,6,9,1,1,6,6,2,1,9},{1,3,6,9,9,5,0,3,4,9,1,0,9,6,2,7},{8,6,2,2,1,3,0,0,7,2,7,5,4,8,4,8},{4,1,9,5,8,9,9,2,0,2,5,1,8,7,0,9},{6,2,1,7,8,1,8,5,5,7,0,2,5,7,2,1},{8,1,7,6,2,8,1,2,2,6,4,0,5,4,1,3},{9,2,1,7,6,1,4,3,8,6,5,5,3,9,7,3},{0,6,0,2,4,3,7,6,1,3,8,6,9,0,0,8},{4,3,7,2,4,3,6,4,0,3,9,5,3,6,9,3},{2,1,8,8,4,5,6,5,8,7,3,7,7,5,8,3},{0,7,6,6,1,2,0,3,5,0,8,0,8,7,4,3},{0,4,3,4,9,0,1,9,7,7,8,6,4,6,9,5},{6,5,1,9,9,2,2,7,4,2,7,2,2,3,7,2},{7,1,9,6,1,2,7,0,9,6,6,4,4,5,1,0},{3,4,9,2,8,3,1,2,6,9,7,0,2,4,2,0},{5,1,8,8,4,6,8,5,2,4,1,6,2,2,9,7}};
        System.out.println(new Solution5().minPathSum(grid));
    }
}
