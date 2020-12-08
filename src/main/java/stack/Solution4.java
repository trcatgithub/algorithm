package stack;

import java.util.Deque;
import java.util.LinkedList;

//返回字符串 text 中按字典序排列最小的子序列，该子序列包含 text 中所有不同字符一次。
//
//         
//
//示例 1：
//
//输入："cdadabcc"
//输出："adbc"
//示例 2：
//
//输入："abcd"
//输出："abcd"
//示例 3：
//
//输入："ecbacba"
//输出："eacb"
//示例 4：
//
//输入："leetcode"
//输出："letcod"
// 
//
//提示：
//
//1 <= text.length <= 1000
//text 由小写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution4 {

    // 利用双端队列(字典序升序)
    // 使用双端队列构成字典序单调递增栈
    // 维持队列时操作队尾
    // 组成字符串时操作队头
    // 3ms/36.8MB
    public String smallestSubsequence(String s) {
        // 统计所有字符的出现次数
        int[] memo = new int[26];
        char[] cs = s.toCharArray();
        for(char c : cs) {
            memo[c-'a']++;
        }
        // 双端队列
        Deque<Character> deque = new LinkedList<>();
        // 记录队列中包含的字符
        int[] dequeVals = new int[26];
        // 从前向后遍历
        for(int i=0; i<cs.length; i++) {
            // 初始化队列
            if(deque.isEmpty()) {
                deque.offerLast(cs[i]);
                dequeVals[cs[i]-'a']++;
                memo[cs[i]-'a']--;
                continue;
            }
            // 过滤掉重复字符
            if(dequeVals[cs[i]-'a'] > 0) {
                memo[cs[i]-'a']--;
                continue;
            }
            // 将字符放到正确的位置
            while(!deque.isEmpty()) {
                char prev = deque.peekLast();
                // 1,当前字符字典序大于prev
                // 2,前一个字符已经用完
                if(prev < cs[i] || memo[prev-'a'] == 0) {
                    deque.offerLast(cs[i]);
                    memo[cs[i]-'a']--;
                    dequeVals[cs[i]-'a']++;
                    break;
                }
                dequeVals[prev-'a']--;
                deque.pollLast();
            }
            // 当前字符cs[i]之前的字符逗比cs[i]大，导致之前的字符都被出队时
            if(deque.isEmpty()) {
                deque.offerLast(cs[i]);
                memo[cs[i]-'a']--;
                dequeVals[cs[i]-'a']++;
            }
        }
        // 正向组成字符串
        StringBuilder res = new StringBuilder();
        while(!deque.isEmpty()) {
            res.append(deque.pollFirst());
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "cdadabcc";
        System.out.println(new Solution4().smallestSubsequence(s)); // adbc
        s = "abcd";
        System.out.println(new Solution4().smallestSubsequence(s)); // abcd
        s = "ecbacba";
        System.out.println(new Solution4().smallestSubsequence(s)); // eacb
        s = "leetcode";
        System.out.println(new Solution4().smallestSubsequence(s)); // letcod
        s = "";
        System.out.println(new Solution4().smallestSubsequence(s)); //
        s = "z";
        System.out.println(new Solution4().smallestSubsequence(s)); // z
        s = "ajsidhcwabvwnafcfnowbfjesbgksdjfjdsjdjjjasdalsdsfkfansaclkv";
        System.out.println(new Solution4().smallestSubsequence(s)); // aidhbcfnowegjkslv
        s = "aliceisgoodgoodgoodgirlsheisagoodstudent";
        System.out.println(new Solution4().smallestSubsequence(s)); // acdgirlheostun
        //  aidhbc nowegfjkslv
        // "aidhbcfnoweg jkslv"
        //  ajsidhbcfnowegklv
        // "aidhbcfnowegjkslv"
        //  aidhbcfnowegjkslv

    }
}
