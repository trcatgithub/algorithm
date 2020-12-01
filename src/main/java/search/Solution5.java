package search;

import java.util.Arrays;

//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
//
//如果数组中不存在目标值 target，返回 [-1, -1]。
//
//进阶：
//
//你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
// 
//
//示例 1：
//
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4]
//示例 2：
//
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1]
//示例 3：
//
//输入：nums = [], target = 0
//输出：[-1,-1]
// 
//
//提示：
//
//0 <= nums.length <= 105
//-109 <= nums[i] <= 109
//nums 是一个非递减数组
//-109 <= target <= 109
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution5 {

    // 1，利用二分搜索查找target的位置
    // 2，利用二分搜索不断更新左右边界
    // 0ms/41.2MB
    public int[] searchRange(int[] nums, int target) {
        int start = binarySearch(nums, 0, nums.length-1, target);
        if(start < 0) {
            return new int[]{-1,-1};
        }
        int end = start;
        int pos = start;
        while((pos = binarySearch(nums, 0, pos-1, target)) >= 0) {
            start = pos;
        }
        pos = end;
        while((pos = binarySearch(nums, pos+1, nums.length-1, target)) >= 0) {
            end = pos;
        }
        return new int[]{start, end};
    }

    // 二分搜索
    private int binarySearch(int[] nums, int start, int end, int target) {
        for(int l=start, r=end; l<=r;) {
            int mid = (l+r)/2;
            if(nums[mid] < target) {
                l = mid+1;
            }else if(nums[mid] > target) {
                r = mid-1;
            }else {
                return mid;
            }
        }
        return -1;
    }


//    // 利用API进行二分查找，先找到其中一个target的位置，再向前后分别二分查找
//    // 0ms/41.9MB
//    public int[] searchRange(int[] nums, int target) {
//        int start = Arrays.binarySearch(nums, target);
//        if(start < 0) {
//            return new int[]{-1,-1};
//        }
//        int end = start;
//        int pos = start;
//        while((pos = Arrays.binarySearch(nums, 0, pos, target)) >= 0) {
//            start = pos;
//        }
//        pos = end;
//        while((pos = Arrays.binarySearch(nums, pos+1, nums.length, target)) >= 0) {
//            end = pos;
//        }
//        return new int[]{start, end};
//    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,7,7,8,8,10};
        int target = 8;
        System.out.println(Arrays.toString(new Solution5().searchRange(nums, target))); // [3,4]
        nums = new int[]{5,7,7,8,8,8,8,8,8,8,8,10};
        target = 8;
        System.out.println(Arrays.toString(new Solution5().searchRange(nums, target))); // [3,10]
        nums = new int[]{5,7,7,8,8,10};
        target = 6;
        System.out.println(Arrays.toString(new Solution5().searchRange(nums, target))); // [-1,-1]
        nums = new int[0];
        target = 0;
        System.out.println(Arrays.toString(new Solution5().searchRange(nums, target))); // [-1,-1]
        nums = new int[]{5,5,5,5,5};
        target = 5;
        System.out.println(Arrays.toString(new Solution5().searchRange(nums, target))); // [0,4]
    }
}
