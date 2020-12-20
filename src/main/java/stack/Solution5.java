package stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

//给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
//需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
//
//注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
//
//
//
//示例 1：
//
//输入：s = "bcabc"
//输出："abc"
//
//
//示例 2：
//
//输入：s = "cbacdcbc"
//输出："acdb"
//
//
//
//提示：
//
//
//1 <= s.length <= 104
//s 由小写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/remove-duplicate-letters
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution5 {

    // 利用双端队列(字典序升序)
    // 使用双端队列构成字典序单调递增栈
    // 维持队列时操作队尾
    // 组成字符串时操作队头
    // 2ms/38.5MB
    public String removeDuplicateLetters(String s) {
        // 统计所有字符的出现次数
        int[] memo = new int[26];
        char[] cs = s.toCharArray();
        for(char c : cs) {
            memo[c-'a']++;
        }
        Deque<Character> stack = new LinkedList<>();
        // 记录队列中包含的字符
        int[] used = new int[26];
        for(char c : cs) {
            // 初始化队列
            if(stack.isEmpty()) {
                memo[c-'a']--;
                used[c-'a']++;
                stack.offerLast(c);
                continue;
            }
            // 过滤掉重复字符
            if(used[c-'a'] == 1) {
                memo[c-'a']--;
                continue;
            }

            while(!stack.isEmpty()) {
                char prev = stack.peekLast();
                if(prev > c && memo[prev-'a'] > 0) {
                    stack.pollLast();
                    used[prev-'a']--;
                }else {
                    break;
                }
            }
            stack.offerLast(c);
            memo[c-'a']--;
            used[c-'a']++;
        }
        StringBuilder res = new StringBuilder();
        while(!stack.isEmpty()) {
            res.append(stack.pollFirst());
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "cdadabcc";
        System.out.println(new Solution5().removeDuplicateLetters(s)); // adbc
        s = "abcd";
        System.out.println(new Solution5().removeDuplicateLetters(s)); // abcd
        s = "ecbacba";
        System.out.println(new Solution5().removeDuplicateLetters(s)); // eacb
        s = "leetcode";
        System.out.println(new Solution5().removeDuplicateLetters(s)); // letcod
        s = "";
        System.out.println(new Solution5().removeDuplicateLetters(s)); //
        s = "z";
        System.out.println(new Solution5().removeDuplicateLetters(s)); // z
        s = "ajsidhcwabvwnafcfnowbfjesbgksdjfjdsjdjjjasdalsdsfkfansaclkv";
        System.out.println(new Solution5().removeDuplicateLetters(s)); // aidhbcfnowegjkslv
        s = "aliceisgoodgoodgoodgirlsheisagoodstudent";
        System.out.println(new Solution5().removeDuplicateLetters(s)); // acdgirlheostun
    }
}
