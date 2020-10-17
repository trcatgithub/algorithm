package backtracking;

import java.util.Arrays;

//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
//
//
//上图为 8 皇后问题的一种解法。
//
//给定一个整数 n，返回 n 皇后不同的解决方案的数量。
//
//示例:
//
//输入: 4
//输出: 2
//解释: 4 皇后问题存在如下两个不同的解法。
//[
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
//
//
//
//
//提示：
//
//
//皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。
//当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N-1 步，可进可退。（引用自 百度百科 - 皇后 ）
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/n-queens-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution11 {
    private int count = 0;
    public int totalNQueens(int n) {
        if(n <= 0) {
            return 0;
        }
        char[][] memo = new char[n][n];
        helper(memo, 0);
        return count;
    }

    // 回溯
    // 2ms/34.9MB
    private void helper(char[][] memo, int row) {
        if(row == memo.length) {
            count++;
            return;
        }
        for(int i=0; i<memo.length; i++) {
            if(isHere(memo, i, row)) {
                memo[i][row] = 'Q';
                helper(memo, row+1);
                memo[i][row] = '.';
            }
        }
    }

    // 判断(x,y)对应的位置可否能放‘Q’
    private boolean isHere(char[][] memo, int x, int y) {
        // 当前位置为‘Q’时返回false
        if(memo[x][y] == 'Q') {
            return false;
        }
        // 当前位置左侧存在‘Q’时返回false
        for(int i=0; i<x; i++) {
            if(memo[i][y] == 'Q') {
                return false;
            }
        }
        // 当前位置上方存在‘Q’时返回false
        for(int i=0; i<y; i++) {
            if(memo[x][i] == 'Q') {
                return false;
            }
        }
        // 当前位置左上方或右上方存在‘Q’时返回false
        for(int i=1; i<=(Math.max(x, y)); i++) {
            if((x >= i && y >= i && memo[x-i][y-i] == 'Q') ||
                    (x+i < memo.length && y >= i && memo[x+i][y-i] == 'Q')) {
                return false;
            }
        }
        return true;
    }

    private void printArray(char[][] arr) {
        for(char[] e : arr) {
            System.out.println(Arrays.toString(e));
        }
        System.out.println("++++++++++++++++++++++++++++++");
    }

    public static void main(String[] args) {
        System.out.println(new Solution11().totalNQueens(0)); // 0
        System.out.println(new Solution11().totalNQueens(1)); // 1
        System.out.println(new Solution11().totalNQueens(2)); // 0
        System.out.println(new Solution11().totalNQueens(3)); // 0
        System.out.println(new Solution11().totalNQueens(4)); // 2
        System.out.println(new Solution11().totalNQueens(5)); // 10
        System.out.println(new Solution11().totalNQueens(6)); // 4
    }
}
