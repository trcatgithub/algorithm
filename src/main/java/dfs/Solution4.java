package dfs;


import java.util.Arrays;

//让我们一起来玩扫雷游戏！
//
//给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）
//地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
//
//现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
//
//如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
//如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
//如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
//如果在此次点击中，若无更多方块可被揭露，则返回面板。
// 
//
//示例 1：
//
//输入:
//
//[['E', 'E', 'E', 'E', 'E'],
//['E', 'E', 'M', 'E', 'E'],
//['E', 'E', 'E', 'E', 'E'],
//['E', 'E', 'E', 'E', 'E']]
//
//Click : [3,0]
//
//输出:
//
//[['B', '1', 'E', '1', 'B'],
//['B', '1', 'M', '1', 'B'],
//['B', '1', '1', '1', 'B'],
//['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
//示例 2：
//
//输入:
//
//[['B', '1', 'E', '1', 'B'],
//['B', '1', 'M', '1', 'B'],
//['B', '1', '1', '1', 'B'],
//['B', 'B', 'B', 'B', 'B']]
//
//Click : [1,2]
//
//输出:
//
//[['B', '1', 'E', '1', 'B'],
//['B', '1', 'X', '1', 'B'],
//['B', '1', '1', '1', 'B'],
//['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
// 
//
//注意：
//
//输入矩阵的宽和高的范围为 [1,50]。
//点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。
//输入面板不会是游戏结束的状态（即有地雷已被挖出）。
//简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/minesweeper
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution4 {

    // dfs
    public char[][] updateBoard(char[][] board, int[] click) {
        // 踩到地雷时，直接将该位置标记为'X'
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
        }else { // 从click位置向四周dfs
            dfs(board, click[0], click[1]);
        }
        return board;
    }

    // 0ms/40.2MB
    private int dfs(char[][] board, int x, int y) {
        // 到达边界时直接返回0
        if(x<0 || y<0 || x==board.length || y==board[x].length) {
            return 0;
        }
        // 返回一个地雷
        if(board[x][y] == 'M') {
            return 1;
        }
        // 遇到新方块时
        if(board[x][y] == 'E') {
            // 判断方块的四周，将方块更新为'B'或者四周的地雷数之和
            int count = 0;
            count+= (x>0 ? (board[x-1][y] == 'M' ? 1 : 0) : 0);
            count+= (x>0 && y>0 ? (board[x-1][y-1] == 'M' ? 1 : 0) : 0);
            count+= (x>0 && y<board[x].length-1 ? (board[x-1][y+1] == 'M' ? 1 : 0) : 0);
            count+= (x<board.length-1 ? (board[x+1][y] == 'M' ? 1 : 0) : 0);
            count+= (x<board.length-1 && y>0 ? (board[x+1][y-1] == 'M' ? 1 : 0) : 0);
            count+= (x<board.length-1 && y<board[x].length-1 ? (board[x+1][y+1] == 'M' ? 1 : 0) : 0);
            count+= (y>0 ? (board[x][y-1] == 'M' ? 1 : 0) : 0);
            count+= (y<board[x].length-1 ? (board[x][y+1] == 'M' ? 1 : 0) : 0);
            if(count > 0) {
                board[x][y] = (char)(count+'0');
            }else { // 将方块更新为'B'时，dfs处理该方块的周围方块
                board[x][y] = 'B';
                dfs(board, x-1, y);
                dfs(board, x-1, y-1);
                dfs(board, x-1, y+1);
                dfs(board, x+1, y);
                dfs(board, x+1, y-1);
                dfs(board, x+1, y+1);
                dfs(board, x, y-1);
                dfs(board, x, y+1);
            }
        }
        // 本次位置不是'M'时，返回0个地雷
        return 0;
    }

    private static void printArray(char[][] board) {
        for(char[] line : board) {
            System.out.println(Arrays.toString(line));
        }
        System.out.println("====================");
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}};
        int[] click = new int[]{3, 0};
        printArray(new Solution4().updateBoard(board, click));
        board = new char[][]
                {{'B', '1', 'E', '1', 'B'},
                {'B', '1', 'M', '1', 'B'},
                {'B', '1', '1', '1', 'B'},
                {'B', 'B', 'B', 'B', 'B'}};
        click = new int[]{1, 2};
        printArray(new Solution4().updateBoard(board, click));
    }

}
