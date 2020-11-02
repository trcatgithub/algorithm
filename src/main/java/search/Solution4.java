package search;

import java.util.*;

//给定两个数组，编写一个函数来计算它们的交集。
//
//         
//
//示例 1：
//
//输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2]
//示例 2：
//
//输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[9,4]
// 
//
//说明：
//
//输出结果中的每个元素一定是唯一的。
//我们可以不考虑输出结果的顺序。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution4 {

    // 先对数组排序，再利用双指针进行判断
    // 1ms/38.8MB
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] temp = new int[Math.min(nums1.length, nums2.length)];
        int pos = 0;
        for(int p1=0, p2=0; p1<nums1.length && p2<nums2.length;) {
            if(nums1[p1] < nums2[p2]) {
                p1++;
            }else if(nums1[p1] > nums2[p2]) {
                p2++;
            }else {
                if((pos > 0 && temp[pos-1] != nums1[p1]) || pos == 0) {
                    temp[pos++] = nums1[p1];
                }
                p1++;
                p2++;
            }
        }
        return Arrays.copyOf(temp, pos);
    }

//    // 基于hashset
//    // 4ms/38.6MB
//    public int[] intersection(int[] nums1, int[] nums2) {
//        Set<Integer> memo = new HashSet<>();
//        Set<Integer> temp = new HashSet<>();
//        for(int num : nums1) {
//            memo.add(num);
//        }
//        for(int num : nums2) {
//            if(memo.contains(num)) {
//                temp.add(num);
//            }
//        }
//        int[] res = new int[temp.size()];
//        int pos = 0;
//        for(Integer element : temp) {
//            res[pos++] = element;
//        }
//        return res;
//    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,2,1};
        int[] nums2 = new int[]{2,2};
        // [2]
        System.out.println(Arrays.toString(new Solution4().intersection(nums1, nums2)));
        nums1 = new int[]{4,9,5};
        nums2 = new int[]{9,4,9,8,4};
        // [9, 4]
        System.out.println(Arrays.toString(new Solution4().intersection(nums1, nums2)));
    }
}
