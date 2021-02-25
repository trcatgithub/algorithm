package unclassify;

import java.util.Arrays;

//给你一个二维整数数组 matrix，返回 matrix 的 转置矩阵 。
//
//矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
//
//  1 2 3                1 4 7
//  4 5 6                2 5 8
//  7 8 9                3 6 9
//
//示例 1：
//
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[[1,4,7],[2,5,8],[3,6,9]]
//示例 2：
//
//输入：matrix = [[1,2,3],[4,5,6]]
//输出：[[1,4],[2,5],[3,6]]
//
//
//提示：
//
//m == matrix.length
//n == matrix[i].length
//1 <= m, n <= 1000
//1 <= m * n <= 105
//-109 <= matrix[i][j] <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/transpose-matrix
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution56 {
    // 1 2 3       1 4
    // 4 5 6       2 5
    //             3 6
    // 0ms/39.4MB
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[n][m];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                res[j][i] = matrix[i][j];
            }
        }
        return res;
    }

    private static void printArray(int[][] arr) {
        for(int[] e : arr) {
            System.out.println(Arrays.toString(e));
        }
        System.out.println("======================");
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        printArray(new Solution56().transpose(matrix));
        matrix = new int[][]{{1,2,3},{4,5,6}};
        printArray(new Solution56().transpose(matrix));
        matrix = new int[][]{{1,4},{2,5},{3,6}};
        printArray(new Solution56().transpose(matrix));
    }
}
