package backtracking;

import java.util.*;

//编写一个程序，通过已填充的空格来解决数独问题。
//
//一个数独的解法需遵循如下规则：
//
//数字 1-9 在每一行只能出现一次。
//数字 1-9 在每一列只能出现一次。
//数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
//空白格用 '.' 表示。
//
//
//
//一个数独。
//
//
//
//答案被标成红色。
//
//Note:
//
//给定的数独序列只包含数字 1-9 和字符 '.' 。
//你可以假设给定的数独只有唯一解。
//给定数独永远是 9x9 形式的。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/sudoku-solver
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution6 {

    // 用于控制当一条路径满足要求时，结束其他路径对原数组的回溯
    private boolean flag = false;

    // 回溯
    // 35ms/39.9MB
    public void solveSudoku(char[][] board) {
        // 记录'.'的位置
        List<int[]> memo = new ArrayList<>();
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board.length; j++) {
                if('.' == board[i][j]) {
                    memo.add(new int[]{i, j});
                }
            }
        }
        // dfs
        helper(board, memo, 0);
    }

    // dfs
    private void helper(char[][] board, List<int[]> memo, int p) {
        // dfs结束条件即 找到所有数字的位置
        if(p == memo.size()) {
            // 将flag标记为true
            flag = true;
            // 结束当前路径的递归
            return;
        }
        // 获取p位置对应的所有可能数字
        List<Character> availableChars = getAvailableForPosition(board, memo.get(p)[0], memo.get(p)[1]);
        for(char c : availableChars) {
            // 标记当前位置
            board[memo.get(p)[0]][memo.get(p)[1]] = c;
            // 继续下一个位置的标记
            helper(board, memo, p+1);
            // flag为true表示有其他路径已经满足要求，结束当前路径对原数组的回溯
            if(flag) {
                return;
            }
            // 回溯当前位置的标记
            board[memo.get(p)[0]][memo.get(p)[1]] = '.';
        }
    }

    // 获取当前位置的所有可能数字
    private List<Character> getAvailableForPosition(char[][] board, int x, int y) {
        List<Character> res = new ArrayList<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
        // 判断当前位置的行与列
        for(int i=0; i<board.length; i++) {
            if(board[x][i] != '.') {
                res.remove(Character.valueOf(board[x][i]));
            }
            if(board[i][y] != '.') {
                res.remove(Character.valueOf(board[i][y]));
            }
        }
        int startX = x/3 * 3;
        int startY = y/3 * 3;
        // 判断当前位置所在九宫格
        for(int i=startX; i<startX+3; i++) {
            for(int j=startY; j<startY+3; j++) {
                if(board[i][j] != '.') {
                    res.remove(Character.valueOf(board[i][j]));
                }
            }
        }
        return res;
    }

    private static void printArray(char[][] cs) {
        System.out.println("======================");
        for(char[] row : cs) {
            System.out.println(row);
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        new Solution6().solveSudoku(board);
        printArray(board);
    }
}
