package backtracking;

import java.util.ArrayList;
import java.util.List;

//给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
//
//形式上，斐波那契式序列是一个非负整数列表 F，且满足：
//
//0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
//F.length >= 3；
//对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
//另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
//
//返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
//
// 
//
//示例 1：
//
//输入："123456579"
//输出：[123,456,579]
//示例 2：
//
//输入: "11235813"
//输出: [1,1,2,3,5,8,13]
//示例 3：
//
//输入: "112358130"
//输出: []
//解释: 这项任务无法完成。
//示例 4：
//
//输入："0123"
//输出：[]
//解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
//示例 5：
//
//输入: "1101111"
//输出: [110, 1, 111]
//解释: 输出 [11,0,11,11] 也同样被接受。
// 
//
//提示：
//
//1 <= S.length <= 200
//字符串 S 中只含有数字。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution13 {

    // 回溯(利用递归切割数字)
    // 1ms/36.7MB
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        helper(res, S, 0, 0, 0);
        return res;
    }

    private boolean helper(List<Integer> res, String S, int prev1, int prev2, int p) {
        // 处理到最后一位时，结果集中元素大于3则说明可以组成费布那切数列
        if(p == S.length()) {
            return res.size() >= 3;
        }
        // 采用乘法与加法来获取数值(字符串转换会超出整形最大值)
        long current = 0;
        for(int i=p; i<S.length(); i++) {
            // 数字不能以0开头
            if(i > p && S.charAt(p) == '0') {
                break;
            }
            current = current * 10 + S.charAt(i) - '0';
            // 数字不能大于整形最大值
            if(current > Integer.MAX_VALUE) {
                break;
            }

            if(res.size() >= 2) {
                // 当前数字 小于 前两个数字和时，继续增大当前数字
                if(current < prev1 + prev2) {
                    continue;
                }else if(current > prev1 + prev2) { // 当前数字 大于 前两个数字和时，结束本次回溯
                    break;
                }
            }
            // res中元素少于2时，直接追加当前元素
            res.add((int)current);
            // 开始下一次计算
            if(helper(res, S, prev2, (int)current, i+1)) {
                return true;
            }else {
                // 回溯
                res.remove(res.size()-1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String S = "123456579";
        System.out.println(new Solution13().splitIntoFibonacci(S).toString()); // [123,456,579]
        S = "11235813";
        System.out.println(new Solution13().splitIntoFibonacci(S).toString()); // [1,1,2,3,5,8,13]
        S = "112358130";
        System.out.println(new Solution13().splitIntoFibonacci(S).toString()); // []
        S = "0123";
        System.out.println(new Solution13().splitIntoFibonacci(S).toString()); // []
        S = "1101111";
        System.out.println(new Solution13().splitIntoFibonacci(S).toString()); // [11,0,11,11]
    }

}
