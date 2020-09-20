package backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
//
//说明：解集不能包含重复的子集。
//
//示例:
//
//输入: nums = [1,2,3]
//输出:
//[
//[3],
//[1],
//[2],
//[1,2,3],
//[1,3],
//[2,3],
//[1,2],
//[]
//]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/subsets
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution8 {
    // 回溯
    // 1ms/39.3MB
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 依次选择0 -> nums.length位元素
        for(int i=0; i<=nums.length; i++) {
            helper(nums, res, new Stack<>(), 0, i);
        }
        return res;
    }

    private void helper(int[] nums, List<List<Integer>> res, Stack<Integer> current, int pos, int count) {
        // 找到位数为count的子集
        if(current.size() == count) {
            res.add(new ArrayList<>(current));
        }

        for(int i=pos; i<nums.length; i++) {
            // 向子集中添加元素
            current.push(nums[i]);
            // 继续添加下一位元素
            helper(nums, res, current, i+1, count);
            // 回溯
            current.pop();
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        System.out.println(new Solution8().subsets(nums));
    }
}
