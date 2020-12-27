package search;

import java.util.HashMap;
import java.util.Map;

//给定两个字符串 s 和 t，判断它们是否是同构的。
//
//如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
//
//所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。
//两个字符不能映射到同一个字符上，但字符可以映射自己本身。
//
//示例 1:
//
//输入: s = "egg", t = "add"
//输出: true
//
//
//示例 2:
//
//输入: s = "foo", t = "bar"
//输出: false
//
//示例 3:
//
//输入: s = "paper", t = "title"
//输出: true
//
//说明:
//你可以假设 s 和 t 具有相同的长度。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/isomorphic-strings
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution10 {

    // 利用HashMap
    // 14ms/38.4MB
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> memo = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            if(memo.containsKey(s.charAt(i))) {
                if(t.charAt(i) != memo.get(s.charAt(i))) {
                    return false;
                }
            }else {
                if(memo.containsValue(t.charAt(i))) {
                    return false;
                }
                memo.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "egg", t = "add";
        System.out.println(new Solution10().isIsomorphic(s, t)); // true
        s = "foo"; t = "bar";
        System.out.println(new Solution10().isIsomorphic(s, t)); // false
        s = "paper"; t = "title";
        System.out.println(new Solution10().isIsomorphic(s, t)); // true
        s = ""; t = "";
        System.out.println(new Solution10().isIsomorphic(s, t)); // true
        s = "ab"; t = "aa";
        System.out.println(new Solution10().isIsomorphic(s, t)); // false
    }
}
