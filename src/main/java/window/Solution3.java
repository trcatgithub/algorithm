package window;

//给定两个字符串s1和s2，写一个函数来判断 s2 是否包含 s1的排列。
//
//换句话说，第一个字符串的排列之一是第二个字符串的子串。
//
//示例1:
//
//输入: s1 = "ab" s2 = "eidbaooo"
//输出: True
//解释: s2 包含 s1 的排列之一 ("ba").
//
//
//示例2:
//
//输入: s1= "ab" s2 = "eidboaoo"
//输出: False
//
//
//注意：
//
//输入的字符串只包含小写字母
//两个字符串的长度都在 [1, 10,000] 之间
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/permutation-in-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution3 {

    // 滑动窗口
    // 4ms/38.7MB
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()) {
            return false;
        }
        int[] memo = new int[26];
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        // 统计s1中的字符
        for(char c1 : cs1) {
            memo[c1-'a']++;
        }
        // 第一个窗口中的字符
        int[] window = new int[26];
        for(int i=0; i<cs1.length-1; i++) {
            window[cs2[i]-'a']++;
        }
        // 滑动窗口
        for(int left=0, right=cs1.length-2; right<cs2.length-1;) {
            // 窗口右边界右移
            window[cs2[++right]-'a']++;
            boolean flag = true;
            for(int i=0; i<26; i++) {
                if(memo[i] != window[i]) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                return true;
            }
            // 窗口左边界右移
            window[cs2[left++]-'a']--;
        }
        return false;
    }

//    // 常规循环
//    // 172ms/39MB
//    public boolean checkInclusion(String s1, String s2) {
//        int[] memo = new int[26];
//        for(char c1 : s1.toCharArray()) {
//            memo[c1-'a']++;
//        }
//        int[] temp = Arrays.copyOf(memo, memo.length);
//        int count = s1.length();
//        int start = 0;
//        char[] cs = s2.toCharArray();
//        for(int i=0; i<cs.length; i++) {
//            // 出现了s1中不存在的字符，直接从下一位开始判断
//            if(memo[cs[i]-'a'] == 0) {
//                // 消耗过temp中的字符，重置temp与count
//                if(count < s1.length()) {
//                    temp = Arrays.copyOf(memo, memo.length);
//                    count = s1.length();
//                }
//                start = i+1;
//                continue;
//            }
//            // s1中存在，但是在i之前出现过
//            // 此时从start+1位开始判断
//            if(temp[cs[i]-'a'] == 0) {
//                start++;
//                i = start;
//                temp = Arrays.copyOf(memo, memo.length);
//                count = s1.length();
//            }
//            // 消耗字符
//            count--;
//            temp[cs[i]-'a']--;
//            if(count == 0) {
//                return true;
//            }
//        }
//        return false;
//    }

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(new Solution3().checkInclusion(s1, s2)); // True
        s1 = "ab";
        s2 = "eidboaoo";
        System.out.println(new Solution3().checkInclusion(s1, s2)); // False
        s1 = "adc";
        s2 = "dcda";
        System.out.println(new Solution3().checkInclusion(s1, s2)); // True
        s1 = "horse";
        s2 = "ros";
        System.out.println(new Solution3().checkInclusion(s1, s2)); // True
    }
}
