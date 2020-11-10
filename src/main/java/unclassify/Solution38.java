package unclassify;

import java.util.Arrays;

//实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
//
//如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
//
//必须原地修改，只允许使用额外常数空间。
//
//以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
//1,2,3 → 1,3,2
//3,2,1 → 1,2,3
//1,1,5 → 1,5,1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/next-permutation
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution38 {
    // 1,从后向前遍历，维护最大值max
    // 2,遍历过程中找到第一个小于max的位置i
    // 3,在i到nums.length-1范围内寻找，大于位置i的值 且最小的位置j，然后将i与j位置的元素交换
    // 4,对i+1到nums.length-1范围内的元素进行排序
    // 1ms/38.7MB
    public void nextPermutation(int[] nums) {
        if(nums.length == 0) {
            return;
        }
        int temp = 0;
        int max = nums[nums.length-1];
        // 从后向前遍历
        for(int i=nums.length-2; i>=0; i--) {
            // 找到目标位置i
            if(nums[i] < max) {
                int pos = -1;
                int min = Integer.MAX_VALUE;
                // 寻找目标位置j
                for(int j=i+1; j<nums.length; j++) {
                    if(nums[j] > nums[i] && nums[j] < min) {
                        min = nums[j];
                        pos = j;
                    }
                }
                // 交换i与j对应的元素
                temp = nums[i];
                nums[i] = nums[pos];
                nums[pos] = temp;
                // 将i+1到nums.length范围内元素从小到大排序
                if(i < nums.length-1) {
                    Arrays.sort(nums, i+1, nums.length);
                }
                return;
            }
            // 更新最大值
            max = nums[i];
        }
        // 序列已是最大值，翻转序列，将其变成最小值
        for(int i=0; i<nums.length; i++) {
            if(i < nums.length-1-i) {
                temp = nums[i];
                nums[i] = nums[nums.length-1-i];
                nums[nums.length-1-i] = temp;
            }else {
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        new Solution38().nextPermutation(nums);
        System.out.println(Arrays.toString(nums)); // [1, 3, 2]
        nums = new int[]{3,2,1};
        new Solution38().nextPermutation(nums);
        System.out.println(Arrays.toString(nums)); // [1, 2, 3]
        nums = new int[]{1,1,5};
        new Solution38().nextPermutation(nums);
        System.out.println(Arrays.toString(nums)); // [1, 5, 1]
        nums = new int[]{2,3,1};
        new Solution38().nextPermutation(nums);
        System.out.println(Arrays.toString(nums)); // [3, 1, 2]
        nums = new int[]{2,3,1,4};
        new Solution38().nextPermutation(nums);
        System.out.println(Arrays.toString(nums)); // [2, 3, 4, 1]
        nums = new int[]{2,3,4,1};
        new Solution38().nextPermutation(nums);
        System.out.println(Arrays.toString(nums)); // [2, 4, 1, 3]
        nums = new int[]{2,4,3,1};
        new Solution38().nextPermutation(nums);
        System.out.println(Arrays.toString(nums)); // [3, 1, 2, 4]
        nums = new int[]{4,2,4,3};
        new Solution38().nextPermutation(nums);
        System.out.println(Arrays.toString(nums)); // [4, 3, 2, 4]
    }
}
