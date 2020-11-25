package unclassify;

import java.util.Arrays;

//给你一个字符串 s ，请你根据下面的算法重新构造字符串：
//
//从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
//从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
//重复步骤 2 ，直到你没法从 s 中选择字符。
//从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
//从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
//重复步骤 5 ，直到你没法从 s 中选择字符。
//重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
//在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
//
//请你返回将 s 中字符重新排序后的 结果字符串 。
//
// 
//
//示例 1：
//
//输入：s = "aaaabbbbcccc"
//输出："abccbaabccba"
//解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
//第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
//第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
//第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
//第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
//示例 2：
//
//输入：s = "rat"
//输出："art"
//解释：单词 "rat" 在上述算法重排序以后变成 "art"
//示例 3：
//
//输入：s = "leetcode"
//输出："cdelotee"
//示例 4：
//
//输入：s = "ggggggg"
//输出："ggggggg"
//示例 5：
//
//输入：s = "spo"
//输出："ops"
// 
//
//提示：
//
//1 <= s.length <= 500
//s 只包含小写英文字母。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/increasing-decreasing-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution42 {

    // 先统计所用字母出现次数
    // 按照字典序 不断升序拼接 降序拼接，直到拼接后的字符串达到s的长度
    // 3ms/38.7MB
    public String sortString(String s) {
        int[] memo = new int[26];
        for(int i=0; i<s.length(); i++) {
            memo[s.charAt(i)-97]++;
        }
        StringBuilder res = new StringBuilder();
        while(res.length() < s.length()) {
            // 升序拼接
            for(int i=0; i<memo.length; i++) {
                if(memo[i] > 0) {
                    res.append((char)(i+97));
                    memo[i]--;
                }
            }
            // 降序拼接
            for(int i=memo.length-1; i>=0; i--) {
                if(memo[i] > 0) {
                    res.append((char)(i+97));
                    memo[i]--;
                }
            }
        }
        return res.toString();
    }

//    // 先对字符数组进行升序排序
//    // 再不断按 先升序后降序 拼接字符
//    // 10ms/38.4MB
//    public String sortString(String s) {
//        char[] cs = s.toCharArray();
//        Arrays.sort(cs);
//        StringBuilder res = new StringBuilder();
//        int left = 0;
//        int right = cs.length-1;
//        while(res.length() < cs.length) {
//            char prev = '#';
//            // 升序拼接字符
//            for(int i=left; i<=right; i++) {
//                if(cs[i] != '#') {
//                    if(prev == '#' || prev < cs[i]) {
//                        prev = cs[i];
//                        cs[i] = '#';
//                        res.append(prev);
//                        if(i == left) {
//                            left++;
//                        }
//                    }
//                }
//            }
//            if(res.length() == cs.length) {
//                break;
//            }
//            prev = '#';
//            // 降序拼接字符
//            for(int i=right; i>=left; i--) {
//                if(cs[i] != '#') {
//                    if(prev == '#' || prev > cs[i]) {
//                        prev = cs[i];
//                        cs[i] = '#';
//                        res.append(prev);
//                        if(i == right) {
//                            right--;
//                        }
//                    }
//                }
//            }
//        }
//
//        return res.toString();
//    }


    public static void main(String[] args) {
        String s = "aaaabbbbcccc";
        System.out.println(new Solution42().sortString(s)); // abccbaabccba
        s = "rat";
        System.out.println(new Solution42().sortString(s)); // art
        s = "leetcode";
        System.out.println(new Solution42().sortString(s)); // cdelotee
        s = "ggggggg";
        System.out.println(new Solution42().sortString(s)); // ggggggg
        s = "spo";
        System.out.println(new Solution42().sortString(s)); // ops
        s = "";
        System.out.println(new Solution42().sortString(s)); // ""
    }
}
