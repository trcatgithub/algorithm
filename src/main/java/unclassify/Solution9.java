package unclassify;

import java.util.*;

//给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
//
//示例 1:
//
//输入: ["abcd","dcba","lls","s","sssll"]
//输出: [[0,1],[1,0],[3,2],[2,4]]
//解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
//示例 2:
//
//输入: ["bat","tab","cat"]
//输出: [[0,1],[1,0]]
//解释: 可拼接成的回文串为 ["battab","tabbat"]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/palindrome-pairs
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution9 {

    // 四种情况：
    // 1,数组里有空字符串，并且数组里还有自己就是回文的字符串，每出现一个可与空字符串组成两对。
    // 2,如果自己的翻转后的字符串也在数组里，可以组成一对，注意翻转后不能是自己。
    // 3,如果某个字符串能找到一个分割点，分割点前的部分是回文，后半部分翻转后也在数组里，可组成一对。
    // 4,把3反过来，如果后部分是回文，前半部分翻转后在数组里，可组成一对。
    // 208ms/42.1MB
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Map<String, Integer> memo = new HashMap<>();
        for(int i=0; i< words.length; i++) {
            memo.put(words[i], i);
        }
        for(int i=0; i<words.length; i++) {
            // 数组里有空字符串，并且数组里还有自己就是回文的字符串，每出现一个可与空字符串组成两对。
            if(isPalindrome(words[i]) && memo.containsKey("")) {
                res.add(Arrays.asList(i, memo.get("")));
                res.add(Arrays.asList(memo.get(""), i));
            }
            String reverse = new StringBuilder(words[i]).reverse().toString();
            // 如果自己的翻转后的字符串也在数组里，可以组成一对，注意翻转后不能是自己。
            if(!words[i].equals(reverse) && memo.containsKey(reverse)) {
                res.add(Arrays.asList(i, memo.get(reverse)));
            }
            // 如果某个字符串能找到一个分割点
            for(int j=1; j<words[i].length(); j++) {
                String key = new StringBuilder(words[i].substring(j)).reverse().toString();
                // 分割点前的部分是回文，后半部分翻转后也在数组里，可组成一对
                if(memo.containsKey(key)) {
                    if(isPalindrome(words[i].substring(0, j))) {
                        res.add(Arrays.asList(memo.get(key), i));
                    }
                }
                key = new StringBuilder(words[i].substring(0, j)).reverse().toString();
                // 分割点后的部分是回文，前半部分翻转后也在数组里，可组成一对
                if(memo.containsKey(key)) {
                    if(isPalindrome(words[i].substring(j))) {
                        res.add(Arrays.asList(i, memo.get(key)));
                    }
                }
            }
        }

        return res;
    }



    private boolean isPalindrome(String str) {
        if(str.length() == 0) {
            return false;
        }
        for(int i=0; i<=str.length()/2; i++) {
            if(str.charAt(i) != str.charAt(str.length()-1-i)) {
                return false;
            }
        }
        return true;
    }
//    // 暴力配对比较
//    // 882ms/41.2MB
//    public List<List<Integer>> palindromePairs(String[] words) {
//        List<List<Integer>> res = new ArrayList<>();
//        for(int i=0; i<words.length; i++) {
//            for(int j=i+1; j<words.length; j++) {
//                if(isPair(words[i], words[j])) {
//                    res.add(Arrays.asList(i, j));
//                }
//                if(isPair(words[j], words[i])) {
//                    res.add(Arrays.asList(j, i));
//                }
//            }
//        }
//        return res;
//    }
//
//    private boolean isPair(String str1, String str2) {
//        int len1 = str1.length();
//        int len2 = str2.length();
//        char[] cstr1 = str1.toCharArray();
//        char[] cstr2 = str2.toCharArray();
//        for(int i=0;i<=(len1+len2)/2;i++) {
//            char left = i<len1 ? cstr1[i] : cstr2[i-len1];
//            char right = len2-i>=1 ? cstr2[len2-1-i] : cstr1[len1-1-i+len2];
//            if(left != right) {
//                return false;
//            }
//        }
//        return true;
//    }

    public static void main(String[] args) {
        String[] words = new String[]{"abcd","dcba","lls","s","sssll"};
        System.out.println(new Solution9().palindromePairs(words).toString());
        words = new String[]{"bat","tab","cat"};
        System.out.println(new Solution9().palindromePairs(words).toString());
        words = new String[]{"abcddcb","a"};
        System.out.println(new Solution9().palindromePairs(words).toString());
        words = new String[]{"a","bcddcba"};
        System.out.println(new Solution9().palindromePairs(words).toString());
        words = new String[]{"a",""};
        System.out.println(new Solution9().palindromePairs(words).toString());

    }
}
