package sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.StringJoiner;

//我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
//
//（这里，平面上两点之间的距离是欧几里德距离。）
//
//你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
//
// 
//
//示例 1：
//
//输入：points = [[1,3],[-2,2]], K = 1
//输出：[[-2,2]]
//解释：
//(1, 3) 和原点之间的距离为 sqrt(10)，
//(-2, 2) 和原点之间的距离为 sqrt(8)，
//由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
//我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
//示例 2：
//
//输入：points = [[3,3],[5,-1],[-2,4]], K = 2
//输出：[[3,3],[-2,4]]
//（答案 [[-2,4],[3,3]] 也会被接受。）
// 
//
//提示：
//
//1 <= K <= points.length <= 10000
//-10000 < points[i][0] < 10000
//-10000 < points[i][1] < 10000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution6 {

    // 基于快排
    // 14ms/48MB
    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];
        int[][] tempPoints = new int[points.length][3];
        for(int i=0; i<points.length; i++) {
            tempPoints[i][0] = points[i][0];
            tempPoints[i][1] = points[i][1];
            tempPoints[i][2] = points[i][0]*points[i][0]+points[i][1]*points[i][1];
        }
        quickSort(tempPoints, 0, points.length-1, K);
        for(int i=0; i<K; i++) {
            res[i][0] = tempPoints[i][0];
            res[i][1] = tempPoints[i][1];
        }
        return res;
    }

    private void quickSort(int[][] points, int left, int right, int k) {
        if(left == k || right == k-1) {
            return;
        }
        for(int l=left, r=right; l<r;) {
            if(l < k && k < r) {
                if(points[l][2] >= points[k][2] && points[r][2] <= points[k][2]) {
                    swap(points, l, r);
                    l++;
                    r--;
                }else if(points[l][2] < points[k][2]) {
                    l++;
                }else if(points[r][2] > points[k][2]) {
                    r--;
                }
            }else if(l == k) {
                int newRight = k;
                for(int i=k+1; i<=r; i++) {
                    if(points[newRight][2] > points[i][2]) {
                        if(newRight < i-1) {
                            swap(points, newRight, newRight+1);
                        }
                        swap(points, newRight, i);
                        newRight++;
                    }
                }
                if(newRight > k) {
                    quickSort(points, left, newRight, k);
                }
                return;
            }else if(r == k) {
                int newLeft = k;
                for(int i=k-1; i>=l; i--) {
                    if(points[newLeft][2] < points[i][2]) {
                        if(newLeft > i+1) {
                            swap(points, newLeft, newLeft-1);
                        }
                        swap(points, newLeft, i);
                        newLeft--;
                    }
                }
                if(newLeft < k) {
                    quickSort(points, newLeft, right, k);
                }
                return;
            }
        }
    }

    private void swap(int[][] points, int ori, int tar) {
        int x = points[ori][0];
        int y = points[ori][1];
        int len = points[ori][2];
        points[ori][0] = points[tar][0];
        points[ori][1] = points[tar][1];
        points[ori][2] = points[tar][2];
        points[tar][0] = x;
        points[tar][1] = y;
        points[tar][2] = len;
    }

//    // 堆排序
//    // 758ms/47.9MB
//    public int[][] kClosest(int[][] points, int K) {
//        int[][] res = new int[K][2];
//        int[][] tempPoints = new int[points.length][3];
//        for(int i=0; i<points.length; i++) {
//            tempPoints[i][0] = points[i][0];
//            tempPoints[i][1] = points[i][1];
//            tempPoints[i][2] = points[i][0]*points[i][0]+points[i][1]*points[i][1];
//        }
//
//        for(int i=0, len = points.length; i<K; i++) {
//            for(int root=len/2-1; root>=0; root--) {
//                int left = root*2+1;
//                int right = (left+1 >= len ? len-1 : left+1);
//                if(tempPoints[root][2] > tempPoints[left][2]) {
//                    if(tempPoints[left][2] <= tempPoints[right][2]) {
//                        swap(tempPoints, root, left);
//                    }else {
//                        swap(tempPoints, root, right);
//                    }
//                }else if(tempPoints[root][2] > tempPoints[right][2]) {
//                    if(tempPoints[left][2] <= tempPoints[right][2]) {
//                        swap(tempPoints, root, left);
//                    }else {
//                        swap(tempPoints, root, right);
//                    }
//                }
//            }
//            res[i][0] = tempPoints[0][0];
//            res[i][1] = tempPoints[0][1];
//            swap(tempPoints, 0, len-1);
//            len--;
//        }
//        return res;
//    }
//
//    private void swap(int[][] points, int ori, int tar) {
//        int x = points[ori][0];
//        int y = points[ori][1];
//        int len = points[ori][2];
//        points[ori][0] = points[tar][0];
//        points[ori][1] = points[tar][1];
//        points[ori][2] = points[tar][2];
//        points[tar][0] = x;
//        points[tar][1] = y;
//        points[tar][2] = len;
//    }

//    // 利用Arrays.sort
//    // 37ms/47.3MB
//    public int[][] kClosest(int[][] points, int K) {
//        int[][] res = new int[K][2];
//        Arrays.sort(points, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[0]*o1[0]+o1[1]*o1[1]-o2[0]*o2[0]-o2[1]*o2[1];
//            }
//        });
//        for(int i=0; i<K; i++) {
//            res[i][0] = points[i][0];
//            res[i][1] = points[i][1];
//        }
//        return res;
//    }

    private static void printArray(int[][] arr) {
        StringJoiner sj = new StringJoiner(",");
        for(int[] element : arr) {
            sj.add(Arrays.toString(element));
        }
        System.out.println(sj.toString());
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{{1,3},{-2,2}};
        int K = 1;
        printArray(new Solution6().kClosest(points, K)); // [[-2,2]]
        points = new int[][]{{3,3},{5,-1},{-2,4}};
        K = 2;
        printArray(new Solution6().kClosest(points, K)); // [[3,3],[-2,4]]
        points = new int[][]{{0,1},{1,0}};
        K = 2;
        printArray(new Solution6().kClosest(points, K)); // [[0,1],[1,0]]
        points = new int[][]{{1,3},{-2,2},{2,-2}};
        K = 2;
        printArray(new Solution6().kClosest(points, K)); // [[-2,2],[2,-2]]
        points = new int[][]{{2,2},{2,2},{2,2},{2,2},{2,2},{2,2},{1,1}};
        K = 1;
        printArray(new Solution6().kClosest(points, K)); // [[1,1]]
        points = new int[][]{{-95,76},{17,7},{-55,-58},{53,20},{-69,-8},{-57,87},{-2,-42},{-10,-87},{-36,-57},{97,-39},{97,49}};
        K = 5;
        printArray(new Solution6().kClosest(points, K)); // [[17,7],[-2,-42],[53,20],[-36,-57],[-69,-8]]
        points = new int[][]{{89,6},{-39,-4},{-13,91},{97,-61},{1,7},{-66,69},{-51,68},{82,-6},{-21,44},{-58,-83},{-40,73},{-88,-24}};
        K = 8;
        printArray(new Solution6().kClosest(points, K)); // [[1,7],[-39,-4],[-21,44],[82,-6],[-40,73],[-51,68],[89,6],[-88,-24]]

    }
}
