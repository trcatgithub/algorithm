package dp;

import java.util.*;

//给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
//
//说明：
//
//拆分时可以重复使用字典中的单词。
//你可以假设字典中没有重复的单词。
//示例 1：
//
//输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
//示例 2：
//
//输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
//     注意你可以重复使用字典中的单词。
//示例 3：
//
//输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/word-break
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution4 {
    // 动态规划
    // 从前向后依次判断单词是否在字典当中
    // 17ms/39.8MB
    public boolean wordBreak(String s, List<String> wordDict) {
        // dp数组，记录之前的分词结果
        boolean[] dp = new boolean[s.length()+1];
        // 初始位置为true
        dp[0] = true;
        // 字符串长度
        int len = s.length();
        // 分词开始位置
        List<Integer> starts = new ArrayList<>();
        // 将0加入分词开始位置
        starts.add(0);
        // 从1开始遍历字符串
        for(int i=1; i<=len; i++) {
            // 遍历每一个开始位置
            for(int start : starts) {
                // 从任意一个开始位置 到 当前位置i 的单词包含在字典当中，且 该开始位置对应的单次也包含在字典当中
                dp[i] = wordDict.contains(s.substring(start, i)) && dp[start];
                // 找到一个符合条件的位置即可
                if(dp[i]) {
                    // 将当前位置i作为开始位置加入到starts当中
                    starts.add(i);
                    // 结束循环
                    break;
                }
            }
        }
        // 返回分词结果
        return dp[len];
    }


    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code"); //true
        System.out.println(new Solution4().wordBreak(s, wordDict));
        s = "applepenapple";
        wordDict = Arrays.asList("apple", "pen"); //true
        System.out.println(new Solution4().wordBreak(s, wordDict));
        s = "catsandog";
        wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat"); //false
        System.out.println(new Solution4().wordBreak(s, wordDict));
        s = "a";
        wordDict = Arrays.asList(""); //false
        System.out.println(new Solution4().wordBreak(s, wordDict));
        s = "bb";
        wordDict = Arrays.asList("a","b","bbb","bbbb"); // true
        System.out.println(new Solution4().wordBreak(s, wordDict));
        s = "cars";
        wordDict = Arrays.asList("car","ca","rs"); // true
        System.out.println(new Solution4().wordBreak(s, wordDict));
        s = "a";
        wordDict = Arrays.asList("b"); // false
        System.out.println(new Solution4().wordBreak(s, wordDict));
        s = "cars";
        wordDict = Arrays.asList("car","ca","s"); // true
        System.out.println(new Solution4().wordBreak(s, wordDict));
    }

}
