package unclassify;

import java.util.Arrays;

//给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
//
//此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
//
//注意:
//不能使用代码库中的排序函数来解决这道题。
//
//示例:
//
//输入: [2,0,2,1,1,0]
//输出: [0,0,1,1,2,2]
//进阶：
//
//一个直观的解决方案是使用计数排序的两趟扫描算法。
//首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
//你能想出一个仅使用常数空间的一趟扫描算法吗？
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/sort-colors
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution21 {
    // 遍历一次数组，遇到0时，将其交换到数组起始位置，遇到2时，将其交换到数组末尾
    // 0ms/37.5MB
    public void sortColors(int[] nums) {
        // 尾部指针head左侧元素都是1
        int head = 0;
        // 尾部指针tail右侧元素都是2
        int tail = nums.length-1;
        for(int i=0; i<nums.length; i++) {
            // 将0交换到数组头部
            if(nums[i] == 0 && i > head) {
                System.arraycopy(nums, head, nums, head+1, i-head);
                nums[head] = 0;
                head++;
            }else if(nums[i] == 2 && i < tail) { // 将2交换到数组尾部
                System.arraycopy(nums, i+1, nums, i, tail-i);
                nums[tail] = 2;
                tail--;
                i--;
            }
        }
    }

//    // 计算0与1的个数，剩余部分都是2
//    // 0ms/37.2MB
//    public void sortColors(int[] nums) {
//        int count0 = 0, count1 = 0;
//        // 计算各种颜色数值的个数
//        for(int num : nums) {
//            if(num == 0) {
//                count0++;
//            }else if(num == 1) {
//                count1++;
//            }
//        }
//        // 排序(染色)
//        for(int i=0; i<nums.length; i++) {
//            if(i < count0) {
//                nums[i] = 0;
//            }else if(i < count1+count0) {
//                nums[i] = 1;
//            }else {
//                nums[i] = 2;
//            }
//        }
//    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,0,2,1,1,0};
        new Solution21().sortColors(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{1,2,0};
        new Solution21().sortColors(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{1,2,2,2,2,0,0,0,1,1};
        new Solution21().sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
