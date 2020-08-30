package unclassify;

//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
//
//
//
//示例：
//
//输入："Let's take LeetCode contest"
//输出："s'teL ekat edoCteeL tsetnoc"
//
//
//
//
//提示：
//
//
//在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution18 {

    // 6ms/40.2MB
    public String reverseWords(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }
        StringBuilder res = new StringBuilder();
        char[] cs = s.toCharArray();
        // 当前单词的开始位置，遇到空格后，更新为空格位置+1
        int pos = 0;
        // 遍历字符数组，遇到空格时，逆向append空格之前的字符串
        for(int i=0; i<cs.length; i++) {
            if(cs[i] == ' ') {
                // 逆向append
                for(int j=i-1; j>=pos; j--) {
                    res.append(cs[j]);
                }
                res.append(cs[i]);
                pos = i+1;
            }
        }
        // 处理最后一个空格之后的单词
        if(pos < cs.length) {
            for(int j=cs.length-1; j>=pos; j--) {
                res.append(cs[j]);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest"; // "s'teL ekat edoCteeL tsetnoc"
        System.out.println(new Solution18().reverseWords(s));
        s = "Let's"; // "s'teL"
        System.out.println(new Solution18().reverseWords(s));
        s = " Let's"; // "s'teL"
        System.out.println(new Solution18().reverseWords(s));
    }
}
