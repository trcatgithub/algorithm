package unclassify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。
//由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
//
//一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，
//且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。
//弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
//
//给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
//
// 
//示例 1：
//
//输入：points = [[10,16],[2,8],[1,6],[7,12]]
//输出：2
//解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
//示例 2：
//
//输入：points = [[1,2],[3,4],[5,6],[7,8]]
//输出：4
//示例 3：
//
//输入：points = [[1,2],[2,3],[3,4],[4,5]]
//输出：2
//示例 4：
//
//输入：points = [[1,2]]
//输出：1
//示例 5：
//
//输入：points = [[2,3],[2,3]]
//输出：1
// 
//
//提示：
//
//0 <= points.length <= 10^4
//points[i].length == 2
//-2^31 <= xstart < xend <= 2^31 - 1
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution41 {

    // 1,按右边界升序排序排序
    // [1.......6]
    //   [2..........8]
    //            [7.........12]
    //                   [10.........16]
    // 2,交集区间右边界初始化为第一个区间的右边界
    // 3,若下一个区间的左边界 > 交集区间的右边界 则 结果+1 同时更新交集区间的右边界
    // 19ms/46.1MB
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0) {
            return 0;
        }
        Arrays.sort(points, (ori, tar)->Integer.compare(ori[1], tar[1]));
        int count = 1;
        int prev = points[0][1];
        for(int i=1; i<points.length; i++) {
            if(points[i][0] > prev) {
                count++;
                prev = points[i][1];
            }
        }
        return count;
    }

//    // 先排序，再合并交集，交集数量即是所求
//    // 21ms/46.2MB
//    public int findMinArrowShots(int[][] points) {
//        if(points.length == 0) {
//            return 0;
//        }
//        // 为防止数值溢出，排序时不能做减法
//        // 只按第0位排序即可
//        Arrays.sort(points, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] ori, int[] tar) {
//                if(ori[0] > tar[0]) {
//                    return 1;
//                }else if(ori[0] < tar[0]) {
//                    return -1;
//                }else {
//                    return 0;
//                }
//            }
//        });
//        int count = 1;
//        int[] prev = points[0];
//        for(int i=1; i<points.length; i++) {
//            if(prev[1] < points[i][0]) {
//                count++;
//                prev = points[i];
//            }else if(prev[1] <= points[i][1]) {
//                prev[0] = points[i][0];
//            }else if(prev[1] > points[i][1]) {
//                prev[0] = points[i][0];
//                prev[1] = points[i][1];
//            }
//        }
//        return count;
//    }

    public static void main(String[] args) {
        int[][] points = new int[][]{{10,16},{2,8},{1,6},{7,12}}; // 2
        System.out.println(new Solution41().findMinArrowShots(points));
        points = new int[][]{{1,2},{3,4},{5,6},{7,8}}; // 4
        System.out.println(new Solution41().findMinArrowShots(points));
        points = new int[][]{{1,2},{2,3},{3,4},{4,5}}; // 2
        System.out.println(new Solution41().findMinArrowShots(points));
        points = new int[][]{{1,2}}; // 1
        System.out.println(new Solution41().findMinArrowShots(points));
        points = new int[][]{{2,3},{2,3}}; // 1
        System.out.println(new Solution41().findMinArrowShots(points));
        points = new int[][]{{-2147483646,-2147483645},{2147483646,2147483647}}; // 2
        System.out.println(new Solution41().findMinArrowShots(points));
    }
}
