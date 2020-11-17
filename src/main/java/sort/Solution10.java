package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

//给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
//
//另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
//
//返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，
//其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
//
// 
//
//示例 1：
//
//输入：R = 1, C = 2, r0 = 0, c0 = 0
//输出：[[0,0],[0,1]]
//解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
//示例 2：
//
//输入：R = 2, C = 2, r0 = 0, c0 = 1
//输出：[[0,1],[0,0],[1,1],[1,0]]
//解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
//[[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
//示例 3：
//
//输入：R = 2, C = 3, r0 = 1, c0 = 2
//输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
//解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
//其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
// 
//
//提示：
//
//1 <= R <= 100
//1 <= C <= 100
//0 <= r0 < R
//0 <= c0 < C
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/matrix-cells-in-distance-order
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution10 {


    // 按曼哈段距离分组
    // 10ms/40.5MB
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] res = new int[R*C][2];
        int pos = 0;
        // 最大曼哈段距离
        int len = Math.max(r0, R-1-r0) + Math.max(c0, C-1-c0);
        List<List<int[]>> memo = new ArrayList<>(len+1);
        for(int i=0; i<=len; i++) {
            memo.add(new ArrayList<>());
        }
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                memo.get(Math.abs(r0-i)+Math.abs(c0-j)).add(new int[]{i, j});
            }
        }
        for(List<int[]> out : memo) {
            for(int[] in : out) {
                res[pos++] = in;
            }
        }
        return res;
    }

//    // 直接排序
//    // 19ms/40.4MB
//    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
//        int[][] res = new int[R*C][2];
//        int pos = 0;
//        for(int i=0; i<R; i++) {
//            for(int j=0; j<C; j++) {
//                res[pos++] = new int[]{i,j};
//            }
//        }
//        Arrays.sort(res, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return Math.abs(o1[0]-r0)+Math.abs(o1[1]-c0)-Math.abs(o2[0]-r0)-Math.abs(o2[1]-c0);
//            }
//        });
//        return res;
//    }

    private static void printArray(int[][] arr) {
        StringJoiner sj = new StringJoiner(", ");
        for(int[] e : arr) {
            sj.add(Arrays.toString(e));
        }
        System.out.println(sj.toString());
    }

    public static void main(String[] args) {
        // [[0,0],[0,1]]
        printArray(new Solution10().allCellsDistOrder(1, 2, 0, 0));
        // [[0,1],[0,0],[1,1],[1,0]]
        printArray(new Solution10().allCellsDistOrder(2, 2, 0, 1));
        // [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
        printArray(new Solution10().allCellsDistOrder(2, 3, 1, 2));
    }
}
