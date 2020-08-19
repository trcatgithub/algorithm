package unclassify;

//给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
//
//具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
//
// 
//
//示例 1：
//
//输入："abc"
//输出：3
//解释：三个回文子串: "a", "b", "c"
//示例 2：
//
//输入："aaa"
//输出：6
//解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
// 
//
//提示：
//
//输入的字符串长度不会超过 1000 。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/palindromic-substrings
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution13 {

    // 中心向两端扩散
    // 2ms/37.8MB
    public int countSubstrings(String s) {
        // 处理边界问题
        if(s == null || s.length() == 0) {
            return 0;
        }
        // 字符串长度
        int len = s.length();
        // 初始化count
        int count = len;
        char[] cs = s.toCharArray();
        // 统计奇数回文串
        for(int i=0; i<len; i++) {
            // j为步长，每次比较"i-j"与"i+j"位置的字符是否相等
            for(int j=1; i-j>=0 && i+j<len; j++) {
                if(cs[i-j] == cs[i+j]) {
                    count++;
                }else {
                    break;
                }
            }
        }

        // 统计偶数回文串
        for(int i=0; i<len-1; i++) {
            // "i"与"i+1"位置的字符相同时
            if(cs[i] == cs[i+1]) {
                count++;
                // j为步长，每次比较"i-j"与"i+j+1"位置的字符是否相等
                for(int j=1; i-j>=0 && i+j+1<len; j++) {
                    if(cs[i-j] == cs[i+j+1]) {
                        count++;
                    }else {
                        break;
                    }
                }
            }
        }

        return count;
    }

//    // 暴力
//    // 309ms/37.8MB
//    public int countSubstrings(String s) {
//        if(s == null || s.length() == 0) {
//            return 0;
//        }
//        char[] cs = s.toCharArray();
//        int len = s.length();
//        int count = len;
//        for(int i=0; i<len; i++) {
//            for(int j=i+1; j<len; j++) {
//                if(isPalindrome(cs, i, j)) {
//                    count++;
//                }
//            }
//        }
//
//        return count;
//    }
//
//    private boolean isPalindrome(char[] cs, int start, int end) {
//        for(int i=0; i<=(end-start)/2; i++) {
//            if(cs[start+i] != cs[end-i]) {
//                return false;
//            }
//        }
//        return true;
//    }

    public static void main(String[] args) {
        System.out.println(new Solution13().countSubstrings("abc")==3);
        System.out.println(new Solution13().countSubstrings("aaa")==6);
        System.out.println(new Solution13().countSubstrings("aaaaa")==15);
        System.out.println(new Solution13().countSubstrings("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")==500500); //500500
//        StringBuilder s = new StringBuilder();
//        for(int i=0; i<1000; i++) {
//            s.append((char)('a'+(int)(26*Math.random())));
//        }
//        System.out.println(new SolutionX().countSubstrings(s.toString()));
    }
}
