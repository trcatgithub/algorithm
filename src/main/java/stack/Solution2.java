package stack;

import java.util.Stack;

//给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
//
//注意：如果对空文本输入退格字符，文本继续为空。
//
// 
//
//示例 1：
//
//输入：S = "ab#c", T = "ad#c"
//输出：true
//解释：S 和 T 都会变成 “ac”。
//示例 2：
//
//输入：S = "ab##", T = "c#d#"
//输出：true
//解释：S 和 T 都会变成 “”。
//示例 3：
//
//输入：S = "a##c", T = "#a#c"
//输出：true
//解释：S 和 T 都会变成 “c”。
//示例 4：
//
//输入：S = "a#c", T = "b"
//输出：false
//解释：S 会变成 “c”，但 T 仍然是 “b”。
// 
//
//提示：
//
//1 <= S.length <= 200
//1 <= T.length <= 200
//S 和 T 只含有小写字母以及字符 '#'。
// 
//
//进阶：
//
//你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
// 
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/backspace-string-compare
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution2 {

    // 基于stack
    // 3ms/36.3MB
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> sStack = new Stack<>();
        Stack<Character> tStack = new Stack<>();
        // 利用栈维护S，生成经过退格删除后的元素
        for(int i=0; i<S.length(); i++) {
            if(S.charAt(i) == '#') {
                if(!sStack.isEmpty()) {
                    sStack.pop();
                }
            }else {
                sStack.push(S.charAt(i));
            }
        }
        // 利用栈维护T，生成经过退格删除后的元素
        for(int i=0; i<T.length(); i++) {
            if(T.charAt(i) == '#') {
                if(!tStack.isEmpty()) {
                    tStack.pop();
                }
            }else {
                tStack.push(T.charAt(i));
            }
        }
        // 按位判断字符串是否相同
        while(!sStack.isEmpty() && !tStack.isEmpty()) {
            if(!sStack.pop().equals(tStack.pop())) {
                return false;
            }
        }
        // 两个字符串长度不同时返回false， 否则返回true
        return sStack.isEmpty() && tStack.isEmpty() ? true : false;
    }

//    // 双指针
//    // 从后向前遍历处理字符
//    // 0ms/36.4MB
//    public boolean backspaceCompare(String S, String T) {
//        // 从后向前遇到的还未使用的'#'的数量
//        int sLeft = 0;
//        int tLeft = 0;
//        char[] cS = S.toCharArray();
//        char[] cT = T.toCharArray();
//        // 双指针
//        int sp=cS.length-1,tp=cT.length-1;
//        while(sp>=0 || tp>=0) {
//            // 移动S的指针，直到找到一个 执行过退格后仍存在的字符
//            while(sp >= 0 && (cS[sp] == '#' || sLeft > 0)) {
//                if(cS[sp] == '#') {
//                    sLeft++;
//                    sp--;
//                    continue;
//                }
//                if(sLeft > 0) {
//                    sLeft--;
//                    sp--;
//                }
//            }
//            // 移动T的指针，直到找到一个 执行过退格后仍存在的字符
//            while(tp >=0 && (cT[tp] == '#' || tLeft > 0)) {
//                if(cT[tp] == '#') {
//                    tLeft++;
//                    tp--;
//                    continue;
//                }
//                if(tLeft > 0) {
//                    tLeft--;
//                    tp--;
//                }
//            }
//            // 两个指针都小于0，说明两个字符串都被退格完全删除，返回true
//            if(sp < 0 && tp < 0) {
//                return true;
//            }else if((sp >= 0 && tp < 0) || (sp < 0 && tp >= 0)
//                || cS[sp] != cT[tp]) { // 只有一个字符串能被完全删除 或 双指针指定的当前字符不相同时，返回false
//                return false;
//            }
//            // 指针向前移动
//            sp--;
//            tp--;
//        }
//        return true;
//    }

    public static void main(String[] args) {
        String S = "ab#c", T = "ad#c";
        System.out.println(new Solution2().backspaceCompare(S, T)); // true
        S = "ab##"; T = "c#d#";
        System.out.println(new Solution2().backspaceCompare(S, T)); // true
        S = "a##c"; T = "#a#c";
        System.out.println(new Solution2().backspaceCompare(S, T)); // true
        S = "a#c"; T = "b";
        System.out.println(new Solution2().backspaceCompare(S, T)); // false
        S = "##########a#c"; T = "c";
        System.out.println(new Solution2().backspaceCompare(S, T)); // true
        S = "##########a#c##"; T = "c";
        System.out.println(new Solution2().backspaceCompare(S, T)); // false
        S = "bbbextm"; T = "bbb#extm";
        System.out.println(new Solution2().backspaceCompare(S, T)); // false
    }
}
