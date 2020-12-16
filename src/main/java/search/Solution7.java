package search;

import java.util.HashMap;
import java.util.Map;

//给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
//
//这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
//
//示例1:
//
//输入: pattern = "abba", str = "dog cat cat dog"
//输出: true
//示例 2:
//
//输入:pattern = "abba", str = "dog cat cat fish"
//输出: false
//示例 3:
//
//输入: pattern = "aaaa", str = "dog cat cat dog"
//输出: false
//示例 4:
//
//输入: pattern = "abba", str = "dog dog dog dog"
//输出: false
//说明:
//你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/word-pattern
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution7 {
    // 从前向后循环对比，利用散列保存出现过的pattern与字符串的映射
    // 相同的pattern与字符串 出现了与之前不同的映射关系时，返回false
    // 1ms/36.4MB
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if(words.length != pattern.length()) {
            return false;
        }
        Map<Character, String> memo = new HashMap<>();
        for(int i=0; i<words.length; i++) {
            if((!memo.containsKey(pattern.charAt(i)) && memo.containsValue(words[i])) ||
                    !memo.getOrDefault(pattern.charAt(i), words[i]).equals(words[i])) {
                return false;
            }
            memo.put(pattern.charAt(i), words[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        String pattern = "abba";
        String str = "dog cat cat dog";
//        System.out.println(new Solution46().wordPattern(pattern, str)); // true
//        pattern = "abba";
//        str = "dog cat cat fish";
//        System.out.println(new Solution46().wordPattern(pattern, str)); // false
//        pattern = "aaaa";
//        str = "dog cat cat dog";
//        System.out.println(new Solution46().wordPattern(pattern, str)); // false
//        pattern = "abba";
//        str = "dog dog dog dog";
//        System.out.println(new Solution46().wordPattern(pattern, str)); // false
        pattern = "";
        str = "";
        System.out.println(new Solution7().wordPattern(pattern, str)); // false
    }
}
