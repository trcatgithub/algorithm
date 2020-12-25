package unclassify;

import java.util.Arrays;

//假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
//
//对每个孩子 i，都有一个胃口值g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j]。如果 s[j]>= g[i]，
//我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
//
//
//示例1:
//
//输入: g = [1,2,3], s = [1,1]
//输出: 1
//解释:
//你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
//虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
//所以你应该输出1。
//示例2:
//
//输入: g = [1,2], s = [1,2,3]
//输出: 2
//解释:
//你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
//你拥有的饼干数量和尺寸都足以让所有孩子满足。
//所以你应该输出2.
//
//
//提示：
//
//1 <= g.length <= 3 * 10^4
//0 <= s.length <= 3 * 10^4
//1 <= g[i], s[j] <=2^31 - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/assign-cookies
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution46 {

    // 先排序，再从大到小分配饼干
    // 7ms/39.1MB
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        for(int ps=s.length-1, pg=g.length-1; ps>=0 && pg>=0;) {
            if(s[ps] >= g[pg]) {
                count++;
                ps--;
            }
            pg--;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] g = new int[]{1,2,3};
        int[] s = new int[]{1,1};
        System.out.println(new Solution46().findContentChildren(g, s)); // 1
        g = new int[]{1,2};
        s = new int[]{1,2,3};
        System.out.println(new Solution46().findContentChildren(g, s)); // 2
        g = new int[]{1,2};
        s = new int[0];
        System.out.println(new Solution46().findContentChildren(g, s)); // 0
    }
}
