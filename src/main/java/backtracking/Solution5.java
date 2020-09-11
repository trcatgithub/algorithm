package backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
//
//说明：
//
//所有数字都是正整数。
//解集不能包含重复的组合。 
//示例 1:
//
//输入: k = 3, n = 7
//输出: [[1,2,4]]
//示例 2:
//
//输入: k = 3, n = 9
//输出: [[1,2,6], [1,3,5], [2,3,4]]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/combination-sum-iii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution5 {
    // 回溯
    // 1ms/37.2MB
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new Stack<>(), k, n, 1);
        return res;
    }

    private void helper(List<List<Integer>> res, Stack<Integer> current, int k, int n, int p) {
        // 找到和为n，数量为k的序列
        if(n == 0 && current.size() == k) {
            res.add(new ArrayList<>(current));
            return;
        }
        // 和超过n 或 元素数量超过k
        if(n < 0 || current.size() > k) {
            return;
        }
        for(int i=p; i<10; i++) {
            // 加算当前元素
            n-= i;
            current.push(i);
            // 计算下一位
            helper(res, current, k, n, i+1);
            // 回溯当前元素
            n+= i;
            current.pop();
        }
    }

    private static void printList(List<List<Integer>> list) {
        for(List<Integer> element : list) {
            System.out.println(element);
        }
        System.out.println("----------------------");
    }

    public static void main(String[] args) {
        printList(new Solution5().combinationSum3(3, 7));
        printList(new Solution5().combinationSum3(3, 9));
    }
}

