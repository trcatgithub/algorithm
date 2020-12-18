package search;

//给定两个字符串 s 和 t，它们只包含小写字母。
//
//字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
//
//请找出在 t 中被添加的字母。
//
// 
//
//示例 1：
//
//输入：s = "abcd", t = "abcde"
//输出："e"
//解释：'e' 是那个被添加的字母。
//示例 2：
//
//输入：s = "", t = "y"
//输出："y"
//示例 3：
//
//输入：s = "a", t = "aa"
//输出："a"
//示例 4：
//
//输入：s = "ae", t = "aea"
//输出："a"
// 
//
//提示：
//
//0 <= s.length <= 1000
//t.length == s.length + 1
//s 和 t 只包含小写字母
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-the-difference
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution8 {
    // 计数法
    // 统计并记录s中字母出现次数
    // 根据次数寻找t中多出的字母
    // 1ms/36.9MB
    public char findTheDifference(String s, String t) {
        int[] memo = new int[26];
        for(char c : s.toCharArray()) {
            memo[c-'a']++;
        }
        for(char c : t.toCharArray()) {
            memo[c-'a']--;
            if(memo[c-'a'] < 0) {
                return c;
            }
        }
        return ' ';
    }


    public static void main(String[] args) {
        String s = "abcd";
        String t = "abcde";
        System.out.println(new Solution8().findTheDifference(s, t)); // e
        s = "";
        t = "y";
        System.out.println(new Solution8().findTheDifference(s, t)); // y
        s = "a";
        t = "aa";
        System.out.println(new Solution8().findTheDifference(s, t)); // a
        s = "ae";
        t = "aea";
        System.out.println(new Solution8().findTheDifference(s, t)); // a
    }
}
