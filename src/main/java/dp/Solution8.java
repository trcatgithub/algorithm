package dp;

//在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
//
//示例:
//
//输入:
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//
//输出: 4
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximal-square
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// dp   6ms/42.8MB
// 暴力  99ms/45.5MB
public class Solution8 {

    //1 0 1 0 0
    //1 0 1 1 1
    //1 1 1 1 1
    //1 0 0 1 0
    // dp
    // 设'1'对应的坐标为(i,j)
    // 则该点所组成的正方形最大面积为 下列1，2中较小的值
    // 1, 点(i-1,j-1)对应的最大面积 + 点(i-1,j-1)对应的最大面积的边长*2 + 1
    // 2, 从(i,j)向左上遍历, 能够找到的不为'0'的最大边长*2
    // 6ms/42.8MB
    public int maximalSquare(char[][] matrix) {
        // 处理边界问题
        if (matrix.length == 0) {
            return 0;
        }
        // 最大面积
        int max = 0;
        // 记忆数组
        int[][] memo = new int[matrix.length+1][matrix[0].length+1];
        // 遍历每一个点
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                // 若当前节点为'1'
                if(matrix[i][j] == '1') {
                    // 该节点位于左上边界时
                    if(i == 0 || j == 0) {
                        // 更新记忆数组
                        memo[i][j] = 1;
                        // 更新最大面积
                        max = Math.max(max, memo[i][j]);
                    }else { // 该节点不在左右边界时
                        // 获取(i-1,j-1)位置对应最大正方形的边长
                        int len = (int)Math.sqrt(memo[i-1][j-1]);
                        // 该边长大于0时
                        if(len > 0) {
                            // (i,j)位置向左上方向能构成的最大正方形面积（理论最大正方形）
                            memo[i][j] = memo[i-1][j-1] + len*2 + 1;
                            // 判断(i,j)左上方向是否会遇到'0'
                            for(int k=1; k<=len; k++) {
                                // 遇到'0'时，说明(i,j)位置节点无法构成理论最大正方形
                                if(matrix[i][j-k] == '0' || matrix[i-k][j] == '0') {
                                    // 更新实际最大面积
                                    memo[i][j] = k*k;
                                    break;
                                }
                            }
                        }else { // (i-1,j-1)位置正方形面积为0时
                            // (i,j)左上方向构成最大正方形的面积为1
                            memo[i][j] = 1;
                        }
                        // 更新全局最大面积
                        max = Math.max(max, memo[i][j]);
                    }
                }
            }
        }
        return max;
    }

//    // 从每个1开始向右下方向寻找（暴力）
//    // 99ms/45.5MB
//    public int maximalSquare(char[][] matrix) {
//        int area = 0;
//        // 所有'0'的位置
//        Set<String> memo = new HashSet<>();
//        // 所有'1'的位置
//        List<int[]> onePoints = new ArrayList<>();
//        // 遍历数组，将'0'与'1'分类
//        for(int i=0; i<matrix.length; i++) {
//            for(int j=0; j<matrix[i].length; j++) {
//                if(matrix[i][j] == '0') {
//                    memo.add(i+":"+j);
//                }else {
//                    onePoints.add(new int[]{i, j});
//                }
//            }
//        }
//        // 依次从每个'1'的位置向右下寻找
//        for(int[] element : onePoints) {
//            area = Math.max(area, getMaxArea(matrix, element[0], element[1], memo));
//        }
//        return area;
//    }
//
//    private int getMaxArea(char[][] matrix, int x, int y, Set<String> memo) {
//        // 步长
//        int base = 1;
//        // 正方形最大边长
//        int max = Math.min(matrix.length, matrix[0].length);
//        // (1,2) 1
//        // 当步长小于等于最大边长时
//        while(base <= max) {
//            // 遍历当前位置(x,y)与步长，所构成的正方形的 右方与下方的边上的点
//            for(int i=0; i<=base; i++) {
//                // 如果点(x,y)与base组成的正方形已经超出数组范围
//                if(base+x > matrix.length || base+y > matrix[0].length) {
//                    return base>1 ? (base-1)*(base-1) : 1;
//                }
//                // 如果该点为'0'
//                if(memo.contains((i+x)+":"+(base+y)) || memo.contains((base+x)+":"+(i+y))) {
//                    return base*base;
//                }
//            }
//            // 步长递增
//            base++;
//        }
//        // 返回最大面积
//        return max*max;
//    }

    public static void main(String[] args) {
        //1 0 1 0 0
        //1 0 1 1 1
        //1 1 1 1 1
        //1 0 0 1 0
        // (1,2)
        // ()
        char[][] nums = new char[][]{{'1','0','1','0','0'}, {'1','0','1','1','1'}, {'1','1','1','1','1'}, {'1','0','0','1','0'}}; // 4
        System.out.println(new Solution8().maximalSquare(nums) == 4);
        nums = new char[][]{{'1'}}; // 1
        System.out.println(new Solution8().maximalSquare(nums) == 1);
        nums = new char[][]{{'0','1'},{'0','1'}}; // 1
        System.out.println(new Solution8().maximalSquare(nums) == 1);
        nums = new char[][]{{'1','1','1','1','1'},{'1','1','1','1','1'},{'0','0','0','0','0'},{'1','1','1','1','1'},{'1','1','1','1','1'}};// 4
        System.out.println(new Solution8().maximalSquare(nums) == 4);
        nums = new char[][]{{'0','0','0','0','0'},{'1','0','0','0','0'},{'0','0','0','0','0'},{'0','0','0','0','0'}}; // 1
        System.out.println(new Solution8().maximalSquare(nums) == 1);
        nums = new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}; // 4
        System.out.println(new Solution8().maximalSquare(nums) == 4);
        nums = new char[][]{{'0','1','1','0','1'},{'1','1','0','1','0'},{'0','1','1','1','0'},{'1','1','1','1','0'},{'1','1','1','1','1'},{'0','0','0','0','0'}}; // 9
        System.out.println(new Solution8().maximalSquare(nums) == 9);
        nums = new char[][]{{'0','0','0','0','0'},{'1','0','0','0','0'},{'0','0','0','0','0'},{'0','0','0','0','0'}}; // 1
        System.out.println(new Solution8().maximalSquare(nums) == 1);
        nums = new char[][]{{'0','0','0','1'},{'1','1','0','1'},{'1','1','1','1'},{'0','1','1','1'},{'0','1','1','1'}}; // 9
        System.out.println(new Solution8().maximalSquare(nums) == 9);
        nums = new char[][]{{'1','1','1'},{'1','1','1'},{'0','1','1'}}; // 4
        System.out.println(new Solution8().maximalSquare(nums) == 4);
        nums = new char[][]{{'1','1'}}; // 1
        System.out.println(new Solution8().maximalSquare(nums) == 1);
        nums = new char[][]{{'0','0','0','1','0','1','1','1'},{'0','1','1','0','0','1','0','1'},{'1','0','1','1','1','1','0','1'},{'0','0','0','1','0','0','0','0'},{'0','0','1','0','0','0','1','0'},{'1','1','1','0','0','1','1','1'},{'1','0','0','1','1','0','0','1'},{'0','1','0','0','1','1','0','0'},{'1','0','0','1','0','0','0','0'}}; // 1
        System.out.println(new Solution8().maximalSquare(nums) == 1);
    }
}
