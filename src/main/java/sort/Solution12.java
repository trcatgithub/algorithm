package sort;

import java.util.ArrayList;
import java.util.LinkedList;

//给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
//
//如果数组元素个数小于 2，则返回 0。
//
//示例 1:
//
//输入: [3,6,9,1]
//输出: 3
//解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
//示例 2:
//
//输入: [10]
//输出: 0
//解释: 数组元素个数小于 2，因此返回 0。
//说明:
//
//你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
//请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximum-gap
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution12 {

    // 基数排序
    // 8ms/39.2MB
    public int maximumGap(int[] nums) {
        LinkedList<Integer>[] memo = new LinkedList[10];
        // 元素值/base%10 表示基数
        int base = 1;
        while(true) {
            // 每次清空链表
            for(int i=0; i<memo.length; i++) {
                if(memo[i] == null) {
                    memo[i] = new LinkedList<>();
                }else {
                    memo[i].clear();
                }
            }
            // 循环结束flag
            boolean flag = true;
            // 根据基数将元素放到对应的桶
            for(int i=0; i<nums.length; i++) {
                if(flag && nums[i]/base > 0) {
                    flag = false;
                }
                memo[nums[i]/base%10].add(nums[i]);
            }
            // 重建数组
            for(int i=0, pos=0; i<memo.length; i++) {
                for(int e : memo[i]) {
                    nums[pos++] = e;
                }
            }
            base*= 10;
            if(flag) {
                break;
            }
        }
        // 根据排序后的数据计算最大差值
        int max = 0;
        for(int i=0; i<nums.length-1; i++) {
            max = Math.max(max, nums[i+1]-nums[i]);
        }
        return max;
    }

//    // 先排序数组，再计算相邻差的最大值
//    // 2ms/38.7MB
//    public int maximumGap(int[] nums) {
//        int max = 0;
//        Arrays.sort(nums);
//        for(int i=0; i<nums.length-1; i++) {
//            max = Math.max(max, nums[i+1]-nums[i]);
//        }
//        return max;
//    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,6,9,1};
        System.out.println(new Solution12().maximumGap(nums)); // 3
        nums = new int[]{10};
        System.out.println(new Solution12().maximumGap(nums)); // 0
        nums = new int[]{10,1};
        System.out.println(new Solution12().maximumGap(nums)); // 9
        nums = new int[]{81, 22, 73, 93, 43, 14, 55, 65, 28, 39};
        System.out.println(new Solution12().maximumGap(nums)); // 12
    }
}
