package unclassify;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//
//你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
//
//
//
//示例:
//
//给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/two-sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution23 {
//    // 利用hashmap
//    // 2ms/39.2MB
//    public int[] twoSum(int[] nums, int target) {
//        Map<Integer, Integer> memo = new HashMap<>();
//        for(int i=0; i<nums.length; i++) {
//            if(memo.containsKey(target-nums[i])) {
//                return new int[]{memo.get(target-nums[i]), i};
//            }
//            memo.put(nums[i], i);
//        }
//        return new int[2];
//    }

    // 暴力
    // 61ms/39.1MB
    public int[] twoSum(int[] nums, int target) {
        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<nums.length; j++) {
                if(nums[i]+nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[2];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(new Solution23().twoSum(nums, target)));
    }
}
