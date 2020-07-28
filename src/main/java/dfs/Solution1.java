package dfs;

//给定一个整数矩阵，找出最长递增路径的长度。
//
//对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
//
//示例 1:
//
//输入: nums =
//[
//[9,9,4],
//[6,6,8],
//[2,1,1]
//]
//输出: 4
//解释: 最长递增路径为 [1, 2, 6, 9]。
//示例 2:
//
//输入: nums =
//[
//[3,4,5],
//[3,2,6],
//[2,2,1]
//]
//输出: 4
//解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution1 {

    // dfs
    // 9ms/40.2MB
    public int longestIncreasingPath(int[][] matrix) {
        // 处理边界问题
        if(matrix.length == 0) {
            return 0;
        }
        // 保存计算结果，减少重复计算
        int[][] memo = new int[matrix.length][matrix[0].length];
        // 最长路径
        int max = 0;
        // 遍历数组，依次从每一个位置开始寻找最长路径
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                max = Math.max(dfs(matrix, memo, i, j), max);
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int[][] memo, int x, int y) {
        // memo中存在则直接返回，减少重复计算
        if(memo[x][y] > 0) {
            return memo[x][y];
        }
        // 最长路径
        int max = 0;
        // 向上移动
        if(x>0 && matrix[x-1][y] > matrix[x][y]) {
            max = Math.max(max, dfs(matrix, memo, x-1, y));
        }
        // 向下移动
        if(x<matrix.length-1 && matrix[x+1][y] > matrix[x][y]) {
            max = Math.max(max, dfs(matrix, memo, x+1, y));
        }
        // 向左移动
        if(y>0 && matrix[x][y-1] > matrix[x][y]) {
            max = Math.max(max, dfs(matrix, memo, x, y-1));
        }
        // 向右移动
        if(y<matrix[x].length-1 && matrix[x][y+1] > matrix[x][y]) {
            max = Math.max(max, dfs(matrix, memo, x, y+1));
        }
        // 记录计算结果
        memo[x][y] = max+1;
        return memo[x][y];
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{{9,9,4}, {6,6,8}, {2,1,1}}; // 4
        System.out.println(new Solution1().longestIncreasingPath(nums));
        nums = new int[][]{{3,4,5}, {3,2,6}, {2,2,1}}; // 4
        System.out.println(new Solution1().longestIncreasingPath(nums));
        nums = new int[][]{{0,1,2,3,4,5,6,7,8,9},{19,18,17,16,15,14,13,12,11,10},{20,21,22,23,24,25,26,27,28,29},{39,38,37,36,35,34,33,32,31,30},{40,41,42,43,44,45,46,47,48,49},{59,58,57,56,55,54,53,52,51,50},{60,61,62,63,64,65,66,67,68,69},{79,78,77,76,75,74,73,72,71,70},{80,81,82,83,84,85,86,87,88,89},{99,98,97,96,95,94,93,92,91,90},{100,101,102,103,104,105,106,107,108,109},{119,118,117,116,115,114,113,112,111,110},{120,121,122,123,124,125,126,127,128,129},{139,138,137,136,135,134,133,132,131,130},{0,0,0,0,0,0,0,0,0,0}};
        System.out.println(new Solution1().longestIncreasingPath(nums));
    }
}
