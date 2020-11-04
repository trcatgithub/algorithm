package unclassify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

//给出一个无重叠的 ，按照区间起始端点排序的区间列表。
//
//在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
//
// 
//
//示例 1：
//
//输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
//输出：[[1,5],[6,9]]
//示例 2：
//
//输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//输出：[[1,2],[3,10],[12,16]]
//解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
// 
//
//注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/insert-interval
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution37 {

    // 循环判断
    // 1ms/41.1MB
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 处理边界问题
        if(intervals == null || intervals.length == 0) {
            return (newInterval == null || newInterval.length == 0) ? new int[0][0] : new int[][]{newInterval};
        }
        // 存储合并后的区间
        List<int[]> temp = new ArrayList<>();
        for(int i=0; i<intervals.length; i++) {
            // 当前intervals区间包含 newInterval区间
            if(newInterval[0] >= intervals[i][0] && newInterval[1] <= intervals[i][1]) {
                return intervals;
            }else if(newInterval[0] < intervals[i][0] && newInterval[1] > intervals[i][1]) { // 当前intervals区间被newInterval包含
                // 最后一次循环，将newInterval区间追加到结果集
                if(i == intervals.length-1) {
                    temp.add(newInterval);
                }
                continue;
            }else if(newInterval[0] >= intervals[i][0] && newInterval[0] <= intervals[i][1]) { // 当前intervals区间包含newInterval区间起点
                newInterval[0] = intervals[i][0];
                // 最后一次循环，将newInterval区间追加到结果集
                if(i == intervals.length-1) {
                    temp.add(newInterval);
                }
            }else if(newInterval[1] >= intervals[i][0] && newInterval[1] <= intervals[i][1]) { // 当前intervals区间包含newInterval区间终点
                newInterval[1] = intervals[i][1];
                temp.add(newInterval);
            }else if(newInterval[0] > intervals[i][1]) { // 当前intervals区间在newInterval区间左侧
                temp.add(intervals[i]);
                // 最后一次循环，将newInterval区间追加到结果集
                if(i == intervals.length-1) {
                    temp.add(newInterval);
                }
            }else { // 当前intervals区间在newInterval区间右侧
                // 后续区间都在newInterval区间右侧时
                if((temp.size() >0 && temp.get(temp.size()-1)[0] < newInterval[0]) || temp.size() == 0) {
                    // 将newInterval区间追加到结果集
                    temp.add(newInterval);
                }
                temp.add(intervals[i]);
            }
        }

        int[][] res = new int[temp.size()][2];
        for(int i=0; i<res.length; i++) {
            res[i] = temp.get(i);
        }
        return res;
    }

    private static void printArray(int[][] arr) {
        StringJoiner res = new StringJoiner(", ");
        for(int[] element : arr) {
            res.add(Arrays.toString(element));
        }
        System.out.println(res.toString());
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,3},{6,9}};
        int[] newInterval = new int[]{2,5};
        printArray(new Solution37().insert(intervals, newInterval)); // [1, 5], [6, 9]
        intervals = new int[][]{{1,3},{6,9}};
        newInterval = new int[]{4,8};
        printArray(new Solution37().insert(intervals, newInterval)); // [1, 3], [4, 9]
        intervals = new int[][]{{1,3},{6,9}};
        newInterval = new int[]{2,8};
        printArray(new Solution37().insert(intervals, newInterval)); // [1, 9]
        intervals = new int[][]{{2,3},{6,9}};
        newInterval = new int[]{1,4};
        printArray(new Solution37().insert(intervals, newInterval)); // [1, 4], [6, 9]
        intervals = new int[][]{{2,3},{6,9}};
        newInterval = new int[]{5,10};
        printArray(new Solution37().insert(intervals, newInterval)); // [2, 3], [5, 10]
        intervals = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
        newInterval = new int[]{4,8};
        printArray(new Solution37().insert(intervals, newInterval)); // [1, 2], [3, 10], [12, 16]
        intervals = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
        newInterval = new int[]{17,18};
        printArray(new Solution37().insert(intervals, newInterval)); // [1, 2], [3, 5], [6, 7], [8, 10], [12, 16], [17, 18]
        intervals = new int[][]{{3,5},{6,7},{8,10},{12,16}};
        newInterval = new int[]{1,2};
        printArray(new Solution37().insert(intervals, newInterval)); // [1, 2], [3, 5], [6, 7], [8, 10], [12, 16]
        intervals = new int[][]{{3,5},{8,10},{12,16}};
        newInterval = new int[]{6,7};
        printArray(new Solution37().insert(intervals, newInterval)); // [3, 5], [6, 7], [8, 10], [12, 16]
        intervals = new int[][]{};
        newInterval = new int[]{5,7};
        printArray(new Solution37().insert(intervals, newInterval)); // [5, 7]
        intervals = new int[][]{};
        newInterval = new int[]{};
        printArray(new Solution37().insert(intervals, newInterval)); // []
        intervals = new int[][]{{1,5}};
        newInterval = new int[]{2,7};
        printArray(new Solution37().insert(intervals, newInterval)); // [1, 7]
    }
}
