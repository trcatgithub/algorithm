package backtracking;

import java.util.*;

//输入一个字符串，打印出该字符串中字符的所有排列。
//
//
//
//你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
//
//
//
//示例:
//
//输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
//
//
//
//
//限制：
//
//1 <= s 的长度 <= 8
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution10 {
    public String[] permutation(String s) {
        List<String> res = new ArrayList<>();
        helper(res, s.toCharArray(), 0);
        return res.toArray(new String[res.size()]);
    }

    // 回溯
    // 5ms/43.5MB
    // 每次回溯 "位置i与位置p对应的元素"
    // 即 每次回溯的是元素位置的变化，
    private void helper(List<String> res, char[] cs, int p) {
        if(cs.length == p+1) {
            res.add(String.valueOf(cs));
            return;
        }
        for(int i=p; i<cs.length; i++) {
            // p到i范围内，有一个字符与cs[i]相同，则说明本次交换得到的组合 已出现过，无需再次交换
            if(!canSwap(cs, p, i)) {
                continue;
            }
            swap(cs, i, p);
            helper(res, cs, p+1);
            swap(cs, i, p);
        }
    }

    // 判断是否需要交换
    private boolean canSwap(char[] cs, int left, int right) {
        for(int i=left; i<right; i++) {
            if(cs[i] == cs[right]) {
                return false;
            }
        }
        return true;
    }

    // 交换ori与tar对应的元素
    private void swap(char[] cs, int ori, int tar) {
        char temp = cs[ori];
        cs[ori] = cs[tar];
        cs[tar] = temp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution10().permutation("abb")));
    }
}
