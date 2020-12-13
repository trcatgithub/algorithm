package search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//给定一个整数数组，判断是否存在重复元素。
//
//如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
//
//
//
//示例 1:
//
//输入: [1,2,3,1]
//输出: true
//
//示例 2:
//
//输入: [1,2,3,4]
//输出: false
//
//示例 3:
//
//输入: [1,1,1,3,3,4,3,2,4,2]
//输出: true
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/contains-duplicate
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution6 {
    // 基于排序
    // 4ms/41.6MB
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i=0; i<nums.length-1; i++) {
            if(nums[i] == nums[i+1]) {
                return true;
            }
        }
        return false;
    }

//    // 基于散列
//    // 8ms/44.2MB
//    public boolean containsDuplicate(int[] nums) {
//        Set<Integer> memo = new HashSet<>();
//        for(int num : nums) {
//            if(memo.contains(num)) {
//                return true;
//            }
//            memo.add(num);
//        }
//        return false;
//    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,1};
        System.out.println(new Solution6().containsDuplicate(nums)); // true
        nums = new int[]{1,2,3,4};
        System.out.println(new Solution6().containsDuplicate(nums)); // false
        nums = new int[]{1,1,1,3,3,4,3,2,4,2};
        System.out.println(new Solution6().containsDuplicate(nums)); // true
        nums = new int[0];
        System.out.println(new Solution6().containsDuplicate(nums)); // false
    }
}
