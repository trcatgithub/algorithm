package backtracking;

import java.util.*;

//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
//
//示例:
//
//输入: n = 4, k = 2
//输出:
//[
//[2,4],
//[3,4],
//[2,3],
//[1,2],
//[1,3],
//[1,4],
//]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/combinations
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution2 {
    // 回溯
    // 26ms/41.2MB
    public List<List<Integer>> combine(int n, int k) {
        // 结果集
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), n, k, 1);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> current, int n, int k, int p) {
        // 已经找到k个数值的组合时，将其添加到结果集中
        if(current.size() == k) {
            res.add(new ArrayList<>(current));
            return;
        }

        // 开始回溯
        for(int i=p; i<=n; i++) {
            // 将当前值添加到current中
            current.add(i);
            // 继续下一次计算
            helper(res, current, n, k, i+1);
            // 回溯刚才添加的元素
            current.remove(current.size()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().combine(4, 2));
        System.out.println(new Solution2().combine(4, 3));
        System.out.println(new Solution2().combine(5, 3));

    }

}
