package dfs;

import java.util.Arrays;

//给定一个二维网格和一个单词，找出该单词是否存在于网格中。
//
//单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
//
//
//
//示例:
//
//board =
//[
//['A','B','C','E'],
//['S','F','C','S'],
//['A','D','E','E']
//]
//
//给定 word = "ABCCED", 返回 true
//给定 word = "SEE", 返回 true
//给定 word = "ABCB", 返回 false
//
//
//
//提示：
//
//
//board 和 word 中只包含大写和小写英文字母。
//1 <= board.length <= 200
//1 <= board[i].length <= 200
//1 <= word.length <= 10^3
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/word-search
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution6 {
    // 基于递归的dfs（类似回溯）
    // 6ms/42MB
    public boolean exist(char[][] board, String word) {
        // 处理异常情况
        if(word == null || word.length() == 0) {
            return false;
        }
        // 从word的第一个字符开始dfs
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                if(board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int x, int y, int p) {
        // 递归退出条件
        if(x<0 || y<0 || x==board.length || y==board[x].length || p==word.length() || board[x][y] != word.charAt(p)) {
            return false;
        }
        // 找到该路径
        if(p==word.length()-1 && board[x][y] == word.charAt(p)) {
            return true;
        }
        // 记录原节点值
        char temp = board[x][y];
        // 将该节点标记为'#'
        board[x][y] = '#';
        // 向四个方向dfs
        boolean flag = dfs(board, word, x+1, y, p+1) ||
                dfs(board, word, x, y+1, p+1) ||
                dfs(board, word, x-1, y, p+1) ||
                dfs(board, word, x, y-1, p+1);
        // 将该节点还原
        board[x][y] = temp;
        return flag;
    }

    private static void printArray(char[][] board) {
        for(char[] line : board) {
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        String word = "ABCCED"; // true
        System.out.println(new Solution6().exist(board, word));
        board = new char[][]{{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        word = "SEE"; // true
        System.out.println(new Solution6().exist(board, word));
        board = new char[][]{{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        word = "ABCB"; // false
        System.out.println(new Solution6().exist(board, word));
        board = new char[][]{{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        word = "ABCESEEEFS"; // true
        System.out.println(new Solution6().exist(board, word));
        // ABCE
        // SFES
        // ADEE
//        printArray(board);

    }
}
