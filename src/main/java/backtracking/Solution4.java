package backtracking;

import java.util.*;

//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
//candidates 中的每个数字在每个组合中只能使用一次。
//
//说明：
//
//所有数字（包括目标数）都是正整数。
//解集不能包含重复的组合。 
//示例 1:
//
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//所求解集为:
//[
//[1, 7],
//[1, 2, 5],
//[2, 6],
//[1, 1, 6]
//]
//示例 2:
//
//输入: candidates = [2,5,2,1,2], target = 5,
//所求解集为:
//[
//  [1,2,2],
//  [5]
//]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/combination-sum-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution4 {

    // 6ms/39.8MB
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 结果集
        List<List<Integer>> res = new ArrayList<>();
        // 将数组排序，利用有序数组来去重复
        Arrays.sort(candidates);
        // 回溯
        helper(res, new Stack<>(), candidates, 0, target);
        return res;
    }

    private void helper(List<List<Integer>> res, Stack<Integer> current, int[] candidates, int p, int target) {
        // 找到和为target的序列
        if(target == 0) {
            res.add(new ArrayList<>(current));
            return;
        }
        // 序列和超过target
        if(target < 0) {
            return;
        }
        // 从位置p开始向后判断
        for(int i=p; i<candidates.length; i++) {
            // 确保连续相等的值只计算一次
            // 例子：1，1，7     target=8
            // 此处保证只计算位置0的1与位置2的7
            if(i>p && candidates[i] == candidates[i-1]) {
                continue;
            }
            // 减算target
            target-= candidates[i];
            // 当前值入栈
            current.push(candidates[i]);
            // 继续下一次计算
            helper(res, current, candidates, i+1, target);
            // 回溯target与current
            target+= candidates[i];
            current.pop();
        }
    }

//    // 38ms/40.2MB
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        // 结果集
//        List<List<Integer>> res = new ArrayList<>();
//        // 记录已经出现过的组合
//        Set<String> memo = new HashSet<>();
//        // 数组排序
//        Arrays.sort(candidates);
//        // 回溯
//        helper(res, new ArrayList<>(), candidates, memo, target, 0, 0);
//        return res;
//    }
//
//    private void helper(List<List<Integer>> res, List<Integer> current, int[] candidates, Set<String> memo, int target, int sum, int p) {
//        // 找到和为target的组合
//        if(sum == target) {
//            // 若该组合第一次出现
//            if(!memo.contains(current.toString())) {
//                res.add(new ArrayList<>(current));
//            }
//            memo.add(current.toString());
//            return;
//        }
//        // 退出条件
//        if(sum > target) {
//            return;
//        }
//
//        // 依次选择p之后的元素
//        for(int i=p; i<candidates.length; i++) {
//            sum+= candidates[i];
//            current.add(candidates[i]);
//            helper(res, current, candidates, memo, target, sum, i+1);
//            sum-= candidates[i];
//            current.remove(current.size()-1);
//        }
//    }

    private static void printList(List<List<Integer>> list) {
        for(List<Integer> element : list) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{10,1,2,7,6,1,5};
        int target = 8;
        printList(new Solution4().combinationSum2(candidates, target));
        candidates = new int[]{2,5,2,1,2};
        target = 5;
        printList(new Solution4().combinationSum2(candidates, target));
    }
}
