package math;

//有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
//
//移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
//
//在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
//
//返回尽可能高的分数。
//
// 
//
//示例：
//
//输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
//输出：39
//解释：
//转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
//0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
// 
//
//提示：
//
//1 <= A.length <= 20
//1 <= A[0].length <= 20
//A[i][j] 是 0 或 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/score-after-flipping-matrix
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution4 {

    // 贪心
    // 1, 先将第0列都变成1
    // 2, 根据后续列中1的数量，决定是否对该列进行列翻转
    // 1ms/36.3MB
    public int matrixScore(int[][] A) {
        // 通过翻转行，将第0列都变成1
        for(int i=0; i<A.length; i++) {
            if(A[i][0] == 0) {
                // 翻转第i行
                for(int j=0; j<A[i].length; j++) {
                    A[i][j] = (A[i][j] == 0 ? 1 : 0);
                }
            }
        }
        // 从第1列开始向后续列遍历
        for(int i=1; i<A[0].length; i++) {
            int count = 0;
            // 统计每列1的数量
            for(int j=0; j<A.length; j++) {
                if(A[j][i] == 1) {
                    count++;
                }
            }
            // 每列的1数量小于0时,翻转该列
            if(count < A.length-count) {
                for(int j=0; j<A.length; j++) {
                    A[j][i] = (A[j][i] == 0 ? 1 : 0);
                }
            }
        }
        // 计算最大值
        int res = 0;
        for(int[] num : A) {
            StringBuilder temp = new StringBuilder();
            for(int i=0; i<num.length; i++) {
                temp.append(num[i]);
            }
            res+= Integer.parseInt(temp.toString(), 2);
        }
        return res;
    }


    public static void main(String[] args) {
        int[][] A = new int[][]{{0,0,1,1},{1,0,1,0},{1,1,0,0}};
        System.out.println(new Solution4().matrixScore(A)); // 39

    }
}
