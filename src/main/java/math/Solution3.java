package math;

//给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。
//任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
//在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
//
//然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，
//因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
//
//你需要计算完成所有任务所需要的 最短时间 。
//
//
//
//示例 1：
//
//输入：tasks = ["A","A","A","B","B","B"], n = 2
//输出：8
//解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
//在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，
//而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
//
//示例 2：
//
//输入：tasks = ["A","A","A","B","B","B"], n = 0
//输出：6
//解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
//["A","A","A","B","B","B"]
//["A","B","A","B","A","B"]
//["B","B","B","A","A","A"]
//...
//诸如此类
//
//
//示例 3：
//
//输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
//输出：16
//解释：一种可能的解决方案是：
//A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
//
//
//
//
//提示：
//
//
//1 <= task.length <= 10^4
//tasks[i] 是大写英文字母
//n 的取值范围为 [0, 100]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/task-scheduler
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution3 {
    // AAAAAABCDEFG   n=2
    // 0 1 2
    // A B G
    // A C #
    // A D #
    // A E #
    // A F #
    // A
    // AAAAABBBBBCD    n=2
    // A B C
    // A B D
    // A B #
    // A B #
    // A B
    // max: 数量最多的字符的字符出现次数
    // maxCount: 数量最多的字符的个数
    // max = 6; n = 2; maxCount = 1;
    // Math.max(tasks.length, (max-1)*(n+1)+maxCount)
    // 4ms/40MB
    public int leastInterval(char[] tasks, int n) {
        int[] memo = new int[26];
        int max = 0;
        int maxCount = 0;
        for(char task : tasks) {
            memo[task-'A']++;
            if(memo[task-'A'] > max) {
                max = memo[task-'A'];
                maxCount = 1;
            }else if(memo[task-'A'] == max) {
                maxCount++;
            }
        }
        return Math.max(tasks.length, (max-1)*(n+1)+maxCount);
    }

    public static void main(String[] args) {
        //  AAAABC    1    ABACA#A
        //  AAAABC    2    ABCA##A##A
        //  AAAABC    3    ABC#A###A###A
        //  AAAABC    4    ABC##A####A####A
        //  AAAABC    5    ABC###A#####A#####A
        char[] tasks = new char[]{'A','A','A','A','B','C'};
        System.out.println(new Solution3().leastInterval(tasks, 1));
    }
}
