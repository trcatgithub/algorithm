package unclassify;

//给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
//
//你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
//
//字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
//
//示例 1:
//s = "abc", t = "ahbgdc"
//
//返回 true.
//
//示例 2:
//s = "axc", t = "ahbgdc"
//
//返回 false.
//
//后续挑战 :
//
//如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
//
//致谢:
//
//特别感谢 @pbrother 添加此问题并且创建所有测试用例。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/is-subsequence
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution7 {

    // 双指针
    // 0ms/37.6MB
    public boolean isSubsequence(String s, String t) {
        // 边界问题
        if(s.length() == 0) {
            return true;
        }
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        // 双指针判断
        for(int sp=0,tp=0; sp<sc.length && tp<tc.length;tp++) {
            if(sc[sp] == tc[tp]) {
                if(sp == sc.length-1) {
                    return true;
                }
                sp++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "abc", t = "ahbgdc"; // true
        System.out.println(new Solution7().isSubsequence(s, t));
        s = "axc"; t = "ahbgdc"; // false
        System.out.println(new Solution7().isSubsequence(s, t));
        s = ""; t = "ahbgdc"; // true
        System.out.println(new Solution7().isSubsequence(s, t));
    }
}
