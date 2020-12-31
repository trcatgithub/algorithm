package math;

import java.util.Arrays;
import java.util.Comparator;

//给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
//
//注意:
//
//可以认为区间的终点总是大于它的起点。
//区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
//示例 1:
//
//输入: [ [1,2], [2,3], [3,4], [1,3] ]
//
//输出: 1
//
//解释: 移除 [1,3] 后，剩下的区间没有重叠。
//示例 2:
//
//输入: [ [1,2], [1,2], [1,2] ]
//
//输出: 2
//
//解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
//示例 3:
//
//输入: [ [1,2], [2,3] ]
//
//输出: 0
//
//解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/non-overlapping-intervals
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution10 {

    // 贪心策略，先计算最多能组成的不重叠区间个数，
    // 然后用区间总个数减去不重叠区间的个数。
    // 3ms/38.6MB
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals,new Comparator<int [] >(){
            public int compare(int [] a1,int [] a2) {
                return a1[1] - a2[1];   //升序排列
            }
        });
        int count = 1;	//最多能组成的不重叠区间个数
        int end = intervals[0][1];
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                continue;
            }
            end = intervals[i][1];
            count++;
        }
        return intervals.length - count;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,2},{2,3},{3,4},{1,3}};
        System.out.println(new Solution10().eraseOverlapIntervals(intervals)); // 1
        intervals = new int[][]{{1,2},{2,3},{3,4},{1,3}};
        System.out.println(new Solution10().eraseOverlapIntervals(intervals)); // 1
        intervals = new int[][]{{1,2},{2,3}};
        System.out.println(new Solution10().eraseOverlapIntervals(intervals)); // 0
    }
}
