package graph;

import java.util.Arrays;

//给定一个 n × n 的二维矩阵表示一个图像。
//
//将图像顺时针旋转 90 度。
//
//说明：
//
//你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
//
//示例 1:
//
//给定 matrix =
//[
//[1,2,3],
//[4,5,6],
//[7,8,9]
//],
//
//原地旋转输入矩阵，使其变为:
//[
//[7,4,1],
//[8,5,2],
//[9,6,3]
//]
//
//
//示例 2:
//
//给定 matrix =
//[
//[ 5, 1, 9,11],
//[ 2, 4, 8,10],
//[13, 3, 6, 7],
//[15,14,12,16]
//],
//
//原地旋转输入矩阵，使其变为:
//[
//[15,13, 2, 5],
//[14, 3, 4, 1],
//[12, 6, 8, 9],
//[16, 7,10,11]
//]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/rotate-image
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution4 {

    // 先按对角线对折
    // 再按水平线翻转
    // 0ms/38.6MB
    public void rotate(int[][] matrix) {
        int len = matrix.length-1;
        for(int i=0; i<=len; i++) {
            for(int j=0; j<=len-i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len-j][len-i];
                matrix[len-j][len-i] = temp;
            }
        }
        for(int i=0; i<(len+1)/2; i++) {
            for(int j=0; j<=len; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len-i][j];
                matrix[len-i][j] = temp;
            }
        }
    }

    private static void printArray(int[][] matrix) {
        for(int[] e : matrix) {
            System.out.println(Arrays.toString(e));
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        new Solution4().rotate(matrix);
        printArray(matrix);
        matrix = new int[][]{{5, 1, 9,11},{2, 4, 8,10},{13, 3, 6, 7},{15,14,12,16}};
        new Solution4().rotate(matrix);
        printArray(matrix);
        matrix = new int[][]{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        new Solution4().rotate(matrix);
        printArray(matrix);
    }
}
