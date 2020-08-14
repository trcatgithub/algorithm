package stack;

import java.util.Stack;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
//有效字符串需满足：
//
//左括号必须用相同类型的右括号闭合。
//左括号必须以正确的顺序闭合。
//注意空字符串可被认为是有效字符串。
//
//示例 1:
//
//输入: "()"
//输出: true
//示例 2:
//
//输入: "()[]{}"
//输出: true
//示例 3:
//
//输入: "(]"
//输出: false
//示例 4:
//
//输入: "([)]"
//输出: false
//示例 5:
//
//输入: "{[]}"
//输出: true
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/valid-parentheses
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution1 {
    // 利用栈
    // 1ms/37.7MB
    public boolean isValid(String s) {
        // 保存左括号'(', '[', '{'
        Stack<Character> memo = new Stack<>();
        char[] cs = s.toCharArray();
        for(char c : cs) {
            // 当前字符为左括号时，直接入栈
            if(c == '(' || c == '[' || c == '{') {
                memo.push(c);
            }else if(c == ')') { // 当前字符为右括号')'
                // 如果此时栈为空，或栈顶元素不是'('，则返回false
                if(memo.isEmpty() || memo.pop() != '(') {
                    return false;
                }
            }else if(c == ']') { // 当前字符为右括号']'
                // 如果此时栈为空，或栈顶元素不是'['，则返回false
                if(memo.isEmpty() || memo.pop() != '[') {
                    return false;
                }
            }else { // 当前字符为右括号'}'
                // 如果此时栈为空，或栈顶元素不是'{'，则返回false
                if(memo.isEmpty() || memo.pop() != '{') {
                    return false;
                }
            }
        }
        // 栈为空时，返回true
        return memo.size() == 0 ? true : false;
    }

    public static void main(String[] args) {
        String s = "()[]{}"; // true
        System.out.println(new Solution1().isValid(s));
        s = "{[]}"; // true
        System.out.println(new Solution1().isValid(s));
        s = "(([]){})"; // true
        System.out.println(new Solution1().isValid(s));
        s = "(([]{)})"; // true
        System.out.println(new Solution1().isValid(s));

    }

}
