package dfs;

import java.util.Arrays;

//给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
//
//找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
//
//示例:
//
//X X X X
//X O O X
//X X O X
//X O X X
//运行你的函数后，矩阵变为：
//
//X X X X
//X X X X
//X X X X
//X O X X
//解释:
//
//被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
//任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/surrounded-regions
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution2 {

    // 优化代码，不使用List
    // 2ms/42.3MB
    public void solve(char[][] board) {
        if(board == null || board.length == 0) {
            return;
        }
        int len = board.length;
        int width = board[0].length;
        for(int i=0; i<len; i++) {
            for(int j=0; j<width; j++) {
                if(i>0 && j>0 && i<len-1 && j<width-1) {
                    continue;
                }
                if(board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }

        for(int i=0; i<len; i++) {
            for(int j=0; j<width; j++) {
                if(board[i][j] == 'X') {
                    continue;
                }
                board[i][j] = (board[i][j]=='O' ? 'X' : 'O');
            }
        }
    }

    // dfs染色
    private void dfs(char[][] board, int x, int y) {
        // 超过边界直接退出
        if(x<0 || x==board.length || y<0 || y==board[x].length) {
            return;
        }
        // 'X'无需染色，'#'已被染色
        if(board[x][y] == 'X' || board[x][y] == '#') {
            return;
        }
        // 染色
        board[x][y] = '#';
        // 向该位置的四周染色
        dfs(board, x+1, y);
        dfs(board, x-1, y);
        dfs(board, x, y+1);
        dfs(board, x, y-1);
        return;
    }

//    // 1, 找到所有边界的'0'，找到所有非边界的'0'
//    // 2, dfs将边界的'0'，以及与边界'0'可达(直接或间接相邻)的'0'标记为'#'
//    // 3, 将所有内部的'0'标记为'X'
//    // 4, 将step2中标记为'#'的位置重新标记为'0'
//    // 8ms/41.8MB
//    public void solve(char[][] board) {
//        // 保存边界'0'的坐标
//        List<int[]> bound = new ArrayList<>();
//        // 保存内部'0'的坐标
//        List<int[]> in = new ArrayList<>();
//        // 遍历数组，将'0'进行分类
//        for(int i=0; i<board.length; i++) {
//            for(int j=0; j<board[i].length; j++) {
//                if(board[i][j] == 'O') {
//                    if(i==0 || i==board.length-1 || j==0 || j==board[i].length-1) {
//                        bound.add(new int[]{i, j});
//                    }else {
//                        in.add(new int[]{i, j});
//                    }
//                }
//            }
//        }
//        // 从边界'0'开始标记
//        for(int[] e : bound) {
//            dfs(board, e[0], e[1]);
//        }
//        // 将内部'0'标记为'X'
//        for(int[] e : in) {
//            if(board[e[0]][e[1]] == 'O') {
//                board[e[0]][e[1]] = 'X';
//            }else {
//                board[e[0]][e[1]] = 'O';
//            }
//        }
//        // 恢复边界'0'
//        for(int[] e : bound) {
//            board[e[0]][e[1]] = 'O';
//        }
//    }
//
//    // dfs染色
//    private void dfs(char[][] board, int x, int y) {
//        // 超过边界直接退出
//        if(x<0 || x==board.length || y<0 || y==board[x].length) {
//            return;
//        }
//        // 'X'无需染色，'#'已被染色
//        if(board[x][y] == 'X' || board[x][y] == '#') {
//            return;
//        }
//        // 染色
//        board[x][y] = '#';
//        // 向该位置的四周染色
//        dfs(board, x+1, y);
//        dfs(board, x-1, y);
//        dfs(board, x, y+1);
//        dfs(board, x, y-1);
//        return;
//    }

    private static void printArray(char[][] board) {
        for(char[] e : board) {
            System.out.println(Arrays.toString(e));
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        board = new char[][]{{'O','O'},{'O','O'}};
        new Solution2().solve(board);
        printArray(board);

//        board = new char[][]{{'X','X','X','X'},{'X','X','X','X'},{'X','X','X','X'},{'X','O','X','X'}};
    }
}
