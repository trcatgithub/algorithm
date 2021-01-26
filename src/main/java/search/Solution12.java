package search;

//给你一个由一些多米诺骨牌组成的列表dominoes。
//
//如果其中某一张多米诺骨牌可以通过旋转 0度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
//
//形式上，dominoes[i] = [a, b]和dominoes[j] = [c, d]等价的前提是a==c且b==d，或是a==d 且b==c。
//
//在0 <= i < j < dominoes.length的前提下，找出满足dominoes[i] 和dominoes[j]等价的骨牌对 (i, j) 的数量。
//
//
//
//示例：
//
//输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
//输出：1
//
//
//提示：
//
//1 <= dominoes.length <= 40000
//1 <= dominoes[i][j] <= 9
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution12 {
    // 3ms/47.4MB
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] num = new int[100];
        int ret = 0;
        for (int[] domino : dominoes) {
            // 将数组转换为数字
            int val = domino[0] < domino[1] ? domino[0] * 10 + domino[1] : domino[1] * 10 + domino[0];
            ret += num[val];
            num[val]++;
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] dominoes = new int[][]{{1,2},{2,1},{3,4},{5,6}};
        System.out.println(new Solution12().numEquivDominoPairs(dominoes));
        dominoes = new int[][]{{1,2},{2,1},{1,2},{1,2},{5,6}};
        System.out.println(new Solution12().numEquivDominoPairs(dominoes));
    }
}
