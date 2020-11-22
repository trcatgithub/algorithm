package unclassify;

//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//
//示例 1:
//
//输入: s = "anagram", t = "nagaram"
//输出: true
//
//
//示例 2:
//
//输入: s = "rat", t = "car"
//输出: false
//
//说明:
//你可以假设字符串只包含小写字母。
//
//进阶:
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/valid-anagram
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution40 {
    // 两次循环
    // 第一次循环统计出s中所有字母出现次数
    // 第二次循环统计出t中字母及出现次数与s是否相同
    // 4ms/38.8MB
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }
        // 包含 unicode 字符时，可将memo换成HashMap
        int[] memo = new int[26];
        for(int i=0; i<s.length(); i++) {
            memo[s.charAt(i)-97]++;
        }
        for(int i=0; i<t.length(); i++) {
            if(--memo[t.charAt(i)-97] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(new Solution40().isAnagram(s, t));
        s = "rat";
        t = "car";
        System.out.println(new Solution40().isAnagram(s, t));
    }

}
