package dp;

import java.util.*;

//视频游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
//
//给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
//
//最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
//
//旋转 ring 拼出 key 字符 key[i] 的阶段中：
//
//您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
//如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
//示例：
//                  g
//              g      o
//           n            d
//              i      d
// 
//输入: ring = "godding", key = "gd"
//输出: 4
//解释:
//对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
//对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
//当然, 我们还需要1步进行拼写。
//因此最终的输出是 4。
//提示：
//
//ring 和 key 的字符串长度取值范围均为 1 至 100；
//两个字符串中都只有小写字符，并且均可能存在重复字符；
//字符串 key 一定可以由字符串 ring 旋转拼出。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/freedom-trail
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution14 {

    // dp
    // 14ms/39.1MB
    public int findRotateSteps(String ring, String key) {
        // memo每一位下标分别对应26个小写字母
        // memo值表示该字母出现的位置
        List<Integer>[] memo = new List[26];
        for(int i=0; i<ring.length(); i++) {
            if(memo[ring.charAt(i)-'a'] == null) {
                memo[ring.charAt(i)-'a'] = new ArrayList<>();
            }
            memo[ring.charAt(i)-'a'].add(i);
        }

        // dp[i][j]表示 平凑到key的第i个字符时，所需要的最小步数
        int[][] dp = new int[key.length()][ring.length()];
        // 将dp填充成Integer.MAX_VALUE
        for(int[] e : dp) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        // 初始化dp数组第0位(即找到key的第一个字符所需步数)
        for(int pos : memo[key.charAt(0)-'a']) {
            dp[0][pos] = Math.min(pos, ring.length()-pos) + 1;
        }
        // 从第1位开始寻找key的后续字符
        for(int i=1; i<key.length(); i++) {
            // key中第i位字符的所有可能位置
            for(int j : memo[key.charAt(i)-'a']) {
                // key中第i-1位字符的所有可能位置
                for (int k : memo[key.charAt(i - 1) - 'a']) {
                    // dp[i - 1][k]: 选择第k个i-1位置对应的字符位置时，所需的最小步数
                    // Math.min(Math.abs(j - k), ring.length() - Math.abs(j - k)): 从位置i-1找到位置i字符所需的步数
                    // +1: 表示取得当前位置的字符
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), ring.length() - Math.abs(j - k)) + 1);
                }
            }
        }

        // 计算最小步数
        int min = Integer.MAX_VALUE;
        for(int i=0; i<ring.length(); i++) {
            if(dp[dp.length-1][i] > 0) {
                min = Math.min(min, dp[dp.length-1][i]);
            }
        }
        return min;
    }

    private static void printArray(int[][] dp) {
        for(int[] e : dp) {
            System.out.println(Arrays.toString(e));
        }
    }

    public static void main(String[] args) {
        String ring = "godding";
        String key = "gd";
        System.out.println(new Solution14().findRotateSteps(ring, key)); // 4
        ring = "goddiog";
        key = "goi";
        System.out.println(new Solution14().findRotateSteps(ring, key)); // 6
        ring = "caotmcaataijjxi";
        key = "oatjiioicitatajtijciocjcaaxaaatmctxamacaamjjx";
        System.out.println(new Solution14().findRotateSteps(ring, key)); // 137
    }
}
