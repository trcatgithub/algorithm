package backtracking;

import java.util.*;

//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
//
//
//上图为 8 皇后问题的一种解法。
//
//给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
//
//每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
//
// 
//
//示例：
//
//输入：4
//输出：[
//[".Q..",  // 解法 1
//"...Q",
//"Q...",
//"..Q."],
//
//["..Q.",  // 解法 2
//"Q...",
//"...Q",
//".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
//提示：
//
//皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/n-queens
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution1 {

    // 5ms/39.4MB
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        // 0 blank
        // 1 queue
        int[][] arr = new int[n][n];
        helper(res, arr, 0);
        return res;
    }

    // 回溯法
    private void helper(List<List<String>> res, int[][] arr, int row) {
        // 计算到最后一行时，将结果添加到res
        if(row == arr.length) {
            res.add(convertArrayToString(arr));
            return;
        }
        // 依次判断当前行的每一列是否可以放置'Q'
        for(int col=0; col<arr.length; col++) {
            // 判断当前位置能否放'Q'
            if(isCurrentPositionAvailable(arr, row, col)) {
                // 将'Q'放到当前位置
                arr[row][col] = 1;
                // 继续下一行的递归计算
                helper(res, arr, row+1);
                // 恢复当前位置，继续当前行的下一列的计算
                arr[row][col] = 0;
            }
        }
    }

    // 判断当前位置(x,y)的 左侧，左上方，上方，右上方是否存在Q
    private boolean isCurrentPositionAvailable(int[][] arr, int x, int y) {
        for(int i=1; i<=x;i++) {
            if((arr[x-i][y] > 0) // 当前位置上方存在Q
                || (y >= i && arr[x-i][y-i] > 0) // 当前位置左上方存在Q
                || (y+i<arr.length && arr[x-i][y+i] > 0) // 当前位置右上方存在Q
                || (y >= i && arr[x][y-i] > 0)) { // 当前位置左侧存在Q
                return false;
            }
        }
        return true;
    }

    // 将数组转换为字符串List
    private List<String> convertArrayToString(int[][] arr) {
        List<String> res = new ArrayList<>();
        for(int[] row : arr) {
            StringBuilder str = new StringBuilder();
            for(int col : row) {
                if(col == 1) {
                    str.append("Q");
                }else {
                    str.append(".");
                }
            }
            res.add(str.toString());
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(new SolutionA().solveNQueens(0)); // []
//        System.out.println(new SolutionA().solveNQueens(1)); // [[Q]]
//        System.out.println(new SolutionA().solveNQueens(2)); // []
//        System.out.println(new SolutionA().solveNQueens(3)); // []
//        System.out.println(new SolutionA().solveNQueens(4)); // [[.Q.., ...Q, Q..., ..Q.], [..Q., Q..., ...Q, .Q..]]
//        System.out.println(new SolutionA().solveNQueens(5));
        System.out.println(new Solution1().solveNQueens(6));

        // [
        // [".Q....","...Q..",".....Q","Q.....","..Q...","....Q."],
        // ["..Q...",".....Q",".Q....","....Q.","Q.....","...Q.."],
        // ["...Q..","Q.....","....Q.",".Q....",".....Q","..Q..."],
        // ["....Q.","..Q...","Q.....",".....Q","...Q..",".Q...."]
        // ]

        // .Q....,
        // ...Q..,
        // Q.....,
        // ..Q...,
        // ....Q.,
        // .Q....
        // [.Q...., ...Q.., Q....., ..Q..., ....Q., .Q....],
        // [.Q...., ...Q.., .....Q, Q....., ..Q..., ....Q.],
        // [.Q...., ...Q.., .....Q, Q....., ....Q., .Q....],
        // [.Q...., ...Q.., .....Q, ..Q..., ....Q., .Q....],
        // [.Q...., ....Q., Q....., .....Q, ...Q.., .Q....],
        // [.Q...., ....Q., ..Q..., Q....., ...Q.., .Q....],
        // [.Q...., ....Q., ..Q..., .....Q, ...Q.., .Q....],
        // [..Q..., Q....., ...Q.., .Q...., ....Q., ..Q...],
        // [..Q..., ....Q., .Q...., ...Q.., Q....., ..Q...],
        // [..Q..., ....Q., .Q...., ...Q.., .....Q, ..Q...],
        // [..Q..., .....Q, .Q...., ....Q., Q....., ...Q..],
        // [..Q..., .....Q, ...Q.., .Q...., ....Q., ..Q...],
        // [...Q.., Q....., ..Q..., ....Q., .Q...., ...Q..],
        // [...Q.., Q....., ....Q., .Q...., .....Q, ..Q...],
        // [...Q.., .Q...., ....Q., ..Q..., Q....., ...Q..],
        // [...Q.., .Q...., ....Q., ..Q..., .....Q, ...Q..],
        // [...Q.., .....Q, ..Q..., ....Q., .Q...., ...Q..],
        // [....Q., .Q...., ...Q.., Q....., ..Q..., ....Q.],
        // [....Q., .Q...., ...Q.., .....Q, ..Q..., ....Q.],
        // [....Q., .Q...., .....Q, Q....., ..Q..., ....Q.],
        // [....Q., ..Q..., Q....., ...Q.., .Q...., ....Q.],
        // [....Q., ..Q..., Q....., .....Q, .Q...., ....Q.],
        // [....Q., ..Q..., Q....., .....Q, ...Q.., .Q....],
        // [....Q., ..Q..., .....Q, ...Q.., .Q...., ....Q.]]

//        System.out.println(new SolutionA().solveNQueens(6));

//        [
//        [".Q....","...Q..",".....Q","Q.....","..Q...","....Q."],
//        ["..Q...",".....Q",".Q....","....Q.","Q.....","...Q.."],
//        ["...Q..","Q.....","....Q.",".Q....",".....Q","..Q..."],
//        ["....Q.","..Q...","Q.....",".....Q","...Q..",".Q...."]
//        ]
//        System.out.println(new SolutionA().solveNQueens(6));
//        System.out.println(new SolutionA().solveNQueens(7));
//        System.out.println(new SolutionA().solveNQueens(8));
//        System.out.println(new SolutionA().solveNQueens(9));

        // . . . . . .
        // . . . . . .
        // . . . . . .
        // . . . . . .
        // . . . . . .
        // . . . . . .
    }
}
