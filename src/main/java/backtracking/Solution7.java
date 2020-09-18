package backtracking;

import java.util.*;

//给定一个可包含重复数字的序列，返回所有不重复的全排列。
//
//示例:
//
//输入: [1,1,2]
//输出:
//[
//[1,1,2],
//[1,2,1],
//[2,1,1]
//]
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/permutations-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution7 {

    // 4ms/39.7MB
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 必须先排序
        Arrays.sort(nums);
        helper(nums, new int[nums.length], res, new Stack<>());
        return res;
    }

    // 回溯
    private void helper(int[] nums, int[] visited, List<List<Integer>> res, Stack<Integer> current) {
        if(current.size() == nums.length) {
            res.add(new ArrayList<>(current));
            return;
        }

        for(int i=0; i<nums.length; i++) {
            // 排除已使用过的数字
            if(visited[i] == 1) {
                continue;
            }
            // 排除重复组合(需要nums有序)
            if(i > 0 && nums[i] == nums[i-1] && visited[i-1] == 1) {
                continue;
            }
            visited[i] = 1;
            current.push(nums[i]);
            helper(nums, visited, res, current);
            // 回溯
            visited[i] = 0;
            current.pop();
        }
    }
//    public List<List<Integer>> permuteUnique(int[] nums) {
//        List<List<Integer>> res = new ArrayList<>();
//        helper(nums, new int[nums.length], res, new Stack<>(), new HashSet<>());
//        return res;
//    }
//
//    // 回溯
//    // 145ms/39.9MB
//    private void helper(int[] nums, int[] visited, List<List<Integer>> res, Stack<Integer> current, Set<String> memo) {
//        // 找到一条组合记录
//        if(current.size() == nums.length) {
//            // 如果该组合已出现过
//            if(memo.contains(current.toString())) {
//                return;
//            }
//            // 将该组合加入到结果集中
//            res.add(new ArrayList<>(current));
//            // 维护memo
//            memo.add(current.toString());
//            return;
//        }
//        // 排列组合
//        for(int i=0; i<nums.length; i++) {
//            // 跳过已经使用过的元素
//            if(visited[i] == 1) {
//                continue;
//            }
//            // 使用i对应的元素
//            visited[i] = 1;
//            current.push(nums[i]);
//            helper(nums, visited, res, current, memo);
//            // 回溯
//            visited[i] = 0;
//            current.pop();
//        }
//    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2};
//        System.out.println(new Solution7().permuteUnique(nums));
//        nums = new int[]{1,1,1};
//        System.out.println(new Solution7().permuteUnique(nums));
//        nums = new int[]{1,2,3};
//        System.out.println(new Solution7().permuteUnique(nums));
//        nums = new int[]{1,2,3,4};
//        System.out.println(new Solution7().permuteUnique(nums));
        nums = new int[]{3,3,0,3}; // [[0,3,3,3],[3,0,3,3],[3,3,0,3],[3,3,3,0]]
        System.out.println(new Solution7().permuteUnique(nums));
    }
}
