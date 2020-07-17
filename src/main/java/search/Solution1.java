package search;

//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
//
//你可以假设数组中无重复元素。
//
//示例 1:
//
//输入: [1,3,5,6], 5
//输出: 2
//示例 2:
//
//输入: [1,3,5,6], 2
//输出: 1
//示例 3:
//
//输入: [1,3,5,6], 7
//输出: 4
//示例 4:
//
//输入: [1,3,5,6], 0
//输出: 0
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/search-insert-position
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution1 {

    // 二分
    // 0ms/39.6MB
    public int searchInsert(int[] nums, int target) {
        // 左边界
        int left = 0;
        // 右边界
        int right = nums.length-1;
        while(left <= right) {
            // 中点
            int mid = (left+right)/2;
            // 查找到该元素时，返回mid
            if(nums[mid] == target) {
                return mid;
            }else if(nums[mid] < target) { // 向右查询
                // 左边界右移
                left = mid+1;
                if(mid == nums.length-1) { // 已到右边界时，说明target大于所有元素，返回nums.length
                    return nums.length;
                }else if(target<=nums[left]) { // target小于等于mid+1位置元素时，直接返回left
                    return left;
                }

            }else { // 向左查询
                // 右边界左移
                right = mid-1;
                if(mid == 0) { // 已到左边界时，说明target小于所有元素，返回0
                    return 0;
                }else if(target==nums[right]) { // target等于mid-1位置元素时，直接返回right
                    return right;
                }else if(target > nums[right]) { // target大于mid-1位置元素时，直接返回mid
                    return mid;
                }
            }

        }
        // 默认值，永远无法走到这里
        return -1;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,6};
        int target = 5;
        System.out.println(new Solution1().searchInsert(nums, target)); // 2
        nums = new int[]{1,3,5,6};
        target = 2;
        System.out.println(new Solution1().searchInsert(nums, target)); // 1
        nums = new int[]{1,3,5,6};
        target = 7;
        System.out.println(new Solution1().searchInsert(nums, target)); // 4
        nums = new int[]{1,3,5,6};
        target = 0;
        System.out.println(new Solution1().searchInsert(nums, target)); // 0
        nums = new int[]{1,3,5};
        target = 1;
        System.out.println(new Solution1().searchInsert(nums, target)); // 0
        nums = new int[]{1,3};
        target = 3;
        System.out.println(new Solution1().searchInsert(nums, target)); // 1

    }
}
