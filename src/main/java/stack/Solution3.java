package stack;

import java.util.Deque;
import java.util.LinkedList;

//给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
//
//注意:
//
//
//num 的长度小于 10002 且 ≥ k。
//num 不会包含任何前导零。
//
//
//示例 1 :
//
//输入: num = "1432219", k = 3
//输出: "1219"
//解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
//
//
//示例 2 :
//
//输入: num = "10200", k = 1
//输出: "200"
//解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
//
//
//示例 3 :
//
//输入: num = "10", k = 2
//输出: "0"
//解释: 从原数字移除所有的数字，剩余为空就是0。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/remove-k-digits
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution3 {

    // 利用栈(双端队列)删除“山峰”
    // 10ms/39.5MB
    public String removeKdigits(String num, int k) {
        Deque<Character> memo = new LinkedList<>();
        char[] cnum = num.toCharArray();
        // 删除山峰
        for(int i=0; i<cnum.length; i++) {
            while(!memo.isEmpty() && memo.peekLast() > cnum[i] && k > 0) {
                k--;
                memo.pollLast();
            }
            memo.offerLast(cnum[i]);
        }

        // 山峰数小于k时，从后向前删除memo
        for(int i=0; i<k; i++) {
            memo.pollLast();
        }

        // 删除前导零
        while(!memo.isEmpty()) {
            if(memo.peekFirst() == '0') {
                memo.pollFirst();
            }else {
                break;
            }
        }

        // 构造结果集
        StringBuilder res = new StringBuilder();
        while(!memo.isEmpty()) {
            res.append(memo.pollFirst());
        }

        return res.length() == 0 ? "0" : res.toString();
    }

//    // 删除“山峰”
//    // 49ms/38.2MB
//    public String removeKdigits(String num, int k) {
//        if(num.length() == k) {
//            return "0";
//        }
//        StringBuilder temp = new StringBuilder(num);
//        // 删除k个“山峰”
//        while(k > 0) {
//            for(int i=0; i<temp.length()-1; i++) {
//                if(temp.charAt(i) > temp.charAt(i+1)) {
//                    temp.deleteCharAt(i);
//                    break;
//                }else if(i == temp.length()-2) {
//                    temp.deleteCharAt(i+1);
//                    break;
//                }
//            }
//            k--;
//        }
//
//        // 删除前导零
//        while(temp.length() > 1 && temp.charAt(0) == '0') {
//            temp.deleteCharAt(0);
//        }
//
//        return temp.toString();
//    }


//    // 回溯 超时
//    private int min = Integer.MAX_VALUE;
//
//    public String removeKdigits(String num, int k) {
//        if(k == num.length()) {
//            return "0";
//        }
//        helper(num.toCharArray(), new int[num.length()], k, 0);
//        return String.valueOf(min);
//    }
//
//    private void helper(char[] cnums, int[] deleted, int k, int p) {
//        if(k == 0) {
//            StringBuilder temp = new StringBuilder();
//            for(int i=0; i<cnums.length; i++) {
//                if(deleted[i] == 1) {
//                    continue;
//                }
//                temp.append(cnums[i]);
//            }
//            int val = Integer.valueOf(temp.toString());
//            if(val < min) {
//                min = val;
//            }
//        }
//
//        for(int i=p; i<cnums.length; i++) {
//            deleted[i] = 1;
//            helper(cnums, deleted, k-1, i+1);
//            deleted[i] = 0;
//        }
//    }

    public static void main(String[] args) {
        String num = "1432219";
        int k = 3;
        System.out.println(new Solution3().removeKdigits(num, k)); // 1219
        num = "10200";
        k = 1;
        System.out.println(new Solution3().removeKdigits(num, k)); // 200
        num = "10";
        k = 2;
        System.out.println(new Solution3().removeKdigits(num, k)); // 0
        num = "1000000000000000000000000000000000000000000000000000000000";
        k = 2;
        System.out.println(new Solution3().removeKdigits(num, k)); // 0
        num = "12214439";
        k = 3;
        System.out.println(new Solution3().removeKdigits(num, k)); // 11439
        num = "112";
        k = 1;
        System.out.println(new Solution3().removeKdigits(num, k)); // 11
    }
}
