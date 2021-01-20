package unclassify;

import java.util.Arrays;
import java.util.Comparator;

//给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
//
//示例 1:
//
//输入: [1,2,3]
//输出: 6
//示例 2:
//
//输入: [1,2,3,4]
//输出: 24
//注意:
//
//给定的整型数组长度范围是[3,10^4]，数组中所有的元素范围是[-1000, 1000]。
//输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximum-product-of-three-numbers
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution49 {

    // 一次循环
    // 2ms/40MB
    public int maximumProduct(int[] nums) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE, third = Integer.MIN_VALUE;
        int last1 = Integer.MAX_VALUE, last2 = Integer.MAX_VALUE;
        for(int num : nums) {
            if(num > third) {
                if(num >= first) {
                    third = second;
                    second = first;
                    first = num;
                }else if(num >= second) {
                    third = second;
                    second = num;
                }else {
                    third = num;
                }
            }

            if(num < last2) {
                if(num <= last1) {
                    last2 = last1;
                    last1 = num;
                }else {
                    last2 = num;
                }
            }
        }
        return Math.max(first*second*third, last1*last2*first);
    }

//    // 基于排序
//    // 12ms/40MB
//    public int maximumProduct(int[] nums) {
//        Arrays.sort(nums);
//        int len = nums.length;
//        int max1 = nums[len-1]*nums[len-2]*nums[len-3];
//        int max2 = nums[0]*nums[1]*nums[len-1];
//        return Math.max(max1, max2);
//    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        System.out.println(new Solution49().maximumProduct(nums)); // 6
        nums = new int[]{1,2,3,4};
        System.out.println(new Solution49().maximumProduct(nums)); // 24
        nums = new int[]{-10,-9,3,4};
        System.out.println(new Solution49().maximumProduct(nums)); // 360
    }
}
