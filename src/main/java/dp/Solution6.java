package dp;

//爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
//
//最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
//
//选出任一 x，满足 0 < x < N 且 N % x == 0 。
//用 N - x 替换黑板上的数字 N 。
//如果玩家无法执行这些操作，就会输掉游戏。
//
//只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
//
// 
//
//示例 1：
//
//输入：2
//输出：true
//解释：爱丽丝选择 1，鲍勃无法进行操作。
//示例 2：
//
//输入：3
//输出：false
//解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
// 
//
//提示：
//
//1 <= N <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/divisor-game
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// dp   11ms/36.5MB
//找规律  0ms/36.5MB
public class Solution6 {

    // 动态规划
    // 11ms/36.5MB
    public boolean divisorGame(int N) {
        // 记录计算结果
        boolean[] memo = new boolean[N+1];
        // 初始化
        memo[0] = false;
        memo[1] = false;
        // 从2计算到N
        for(int i=2; i<=N; i++) {
            // 寻找满足整除的除数
            for(int j=i-1; j>0; j--) {
                // 满足整除 且 剩余数字使鲍勃无法进行操作
                if(i%j == 0 && !memo[i-j]) {
                    // 将该位置标记为true
                    memo[i] = true;
                    break;
                }
            }
        }
        // 返回结果
        return memo[N];
    }

////    如果N是奇数，因为奇数的所有因数都是奇数，因此 N 进行一次 N-x 的操作结果一定是偶数，
////    所以如果 a 拿到了一个奇数，那么轮到 b 的时候，b拿到的肯定是偶数，这个时候 b 只要进行 -1，
////    还给 a 一个奇数，那么这样子b就会一直拿到偶数，到最后b一定会拿到最小偶数2，a就输了。
////
////    所以如果游戏开始时Alice拿到N为奇数，那么她必输，也就是false。如果拿到N为偶数，她只用 -1，
////    让bob 拿到奇数，最后bob必输，结果就是true。
//    0ms/36.5MB
//    public boolean divisorGame(int N) {
//        return (N&1) == 1 ? false : true;
//    }

    public static void main(String[] args) {
        System.out.println(new Solution6().divisorGame(1));
        System.out.println(new Solution6().divisorGame(2));
        System.out.println(new Solution6().divisorGame(3));
        System.out.println(new Solution6().divisorGame(1000));
        // 4
        // a 3 1
        // b 1 2
        // a 1 1
    }
}
