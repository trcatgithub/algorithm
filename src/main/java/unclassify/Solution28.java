package unclassify;

import java.util.Arrays;
import java.util.Comparator;

//你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
//
//视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。我们甚至可以对这些片段自由地再剪辑，
//例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
//
//我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，
//如果无法完成该任务，则返回 -1 。
//
//
//
//示例 1：
//
//输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
//输出：3
//解释：
//我们选中 [0,2], [8,10], [1,9] 这三个片段。
//然后，按下面的方案重制比赛片段：
//将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
//现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
//
//
//示例 2：
//
//输入：clips = [[0,1],[1,2]], T = 5
//输出：-1
//解释：
//我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
//
//
//示例 3：
//
//输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
//输出：3
//解释：
//我们选取片段 [0,4], [4,7] 和 [6,9] 。
//
//
//示例 4：
//
//输入：clips = [[0,4],[2,8]], T = 5
//输出：2
//解释：
//注意，你可能录制超过比赛结束时间的视频。
//
//
//
//
//提示：
//
//
//1 <= clips.length <= 100
//0 <= clips[i][0] <= clips[i][1] <= 100
//0 <= T <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/video-stitching
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution28 {
    // 贪心算法
    // 起始位置从0开始，每次寻找能够达到的最大值，找到最大值之后，更新起始位置
    // 0ms/35.6MB
    public int videoStitching(int[][] clips, int T) {
        // 最小片段数
        int count = 0;
        // 当前能够到达的最大位置
        int max = 0;
        // 每次寻找从max开始，能够到达的最大位置
        while(true) {
            // flag，用于判断本次循环是否找到了符合规则的片段
            boolean notHasSegement = true;
            // 用于计算从max开始能达到的最大位置
            int nextMax = 0;
            // 寻找max开始能到达的最大位置
            for(int[] clip : clips) {
                if(clip[0] <= max && clip[1] > max) {
                    nextMax = Math.max(nextMax, clip[1]);
                    notHasSegement = false;
                }
            }
            max = nextMax;
            // 累加次数
            count++;
            // 已经找到所有片段时，返回count
            if(nextMax >= T) {
                return count;
            }
            // 本次循环未找到符合规则的片段时，以后循环也不会有符合规则的片段，直接返回-1
            if(notHasSegement) {
                return -1;
            }
        }
    }

    public static void main(String[] args) {
        int[][] clips = new int[][]{{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}};
        int T = 10;
        System.out.println(new Solution28().videoStitching(clips, T)); // 3
        clips = new int[][]{{0,1},{1,2}};
        T = 5;
        System.out.println(new Solution28().videoStitching(clips, T)); // -1
        clips = new int[][]{{0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}};
        T = 9;
        System.out.println(new Solution28().videoStitching(clips, T)); // 3
        clips = new int[][]{{0,4},{2,8}};
        T = 5;
        System.out.println(new Solution28().videoStitching(clips, T)); // 2
    }
}
