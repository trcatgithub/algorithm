package window;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
//给你一个整数数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
//
//返回滑动窗口中的最大值。
//
//
//
//示例 1：
//
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
//1 [3  -1  -3] 5  3  6  7       3
//1  3 [-1  -3  5] 3  6  7       5
//1  3  -1 [-3  5  3] 6  7       5
//1  3  -1  -3 [5  3  6] 7       6
//1  3  -1  -3  5 [3  6  7]      7
//示例 2：
//
//输入：nums = [1], k = 1
//输出：[1]
//示例 3：
//
//输入：nums = [1,-1], k = 1
//输出：[1,-1]
//示例 4：
//
//输入：nums = [9,11], k = 2
//输出：[11]
//示例 5：
//
//输入：nums = [4,-2], k = 2
//输出：[4]
//
//
//提示：
//
//1 <= nums.length <= 10^5
//-10^4<= nums[i] <= 10^4
//1 <= k <= nums.length
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/sliding-window-maximum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution1 {

    // 滑动窗口
    // 用数组模拟双端队列去维护窗口内的最大值队列
    // 12ms/56.9MB
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 处理边界问题
        if(nums.length == 0) {
            return nums;
        }
        int[] res = new int[nums.length-k+1];
        // 窗口内，最大值下标队列
        // 最大值降序，下标升序
        int[] deque = new int[k];
        int first=0, last=0, len=0;
        for(int left=0, right=0; right<nums.length; right++) {
            // 至少有一个最大值
            if(len == 0) {
                deque[0] = right;
                first = last = 0;
                len++;
            }else if(deque[first] < right-k+1) { // 将超出窗口的最大值出队
                first = (first == k-1 ? 0 : first+1);
                last = (last == k-1 ? 0 : last+1);
                deque[last] = right;
            }

            // 维护最大值队列，保证最大值降序
            while(len >= 0) {
                if(nums[deque[last]] > nums[right] || len == 0) {
                    last = (last == k-1 ? 0 : last+1);
                    deque[last] = right;
                    len++;
                    break;
                }
                last = (last == 0 ? k-1 : last-1);
                len--;
            }

            // 更新左边界元素值
            if(right-left == k-1) {
                res[left++] = nums[deque[first]];
            }

        }
        return res;
    }

//    // 滑动窗口
//    // 用双端队列维护窗口内的最大值队列
//    // 43ms/48.3MB
//    public int[] maxSlidingWindow(int[] nums, int k) {
//        // 处理边界问题
//        if(nums.length == 0) {
//            return nums;
//        }
//        int[] res = new int[nums.length-k+1];
//        // 窗口内，最大值下标队列
//        // 最大值降序，下标升序
//        Deque<Integer> deque = new LinkedList<>();
//        for(int left=0, right=0; right<nums.length; right++) {
//            // 至少有一个最大值
//            if(deque.isEmpty()) {
//                deque.offerLast(right);
//            }else if(deque.peekFirst() < right-k+1) { // 将超出窗口的最大值出队
//                deque.pollFirst();
//                deque.offerLast(right);
//            }
//            // 维护最大值队列，保证最大值降序
//            if(nums[deque.peekLast()] <= nums[right]) {
//                while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[right]) {
//                    deque.pollLast();
//                }
//            }
//            deque.offerLast(right);
//            // 更新左边界元素值
//            if(right-left == k-1) {
//                res[left++] = nums[deque.peekFirst()];
//            }
//        }
//        return res;
//    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(Arrays.toString(new Solution1().maxSlidingWindow(nums, k))); // 3, 3, 5, 5, 6, 7
        nums = new int[]{1};
        k = 1;
        System.out.println(Arrays.toString(new Solution1().maxSlidingWindow(nums, k))); // 1
        nums = new int[]{1,-1};
        k = 1;
        System.out.println(Arrays.toString(new Solution1().maxSlidingWindow(nums, k))); // 1, -1
        nums = new int[]{9,11};
        k = 2;
        System.out.println(Arrays.toString(new Solution1().maxSlidingWindow(nums, k))); // 11
        nums = new int[]{4,-2};
        k = 2;
        System.out.println(Arrays.toString(new Solution1().maxSlidingWindow(nums, k))); // 4
        nums = new int[0];
        k = 0;
        System.out.println(Arrays.toString(new Solution1().maxSlidingWindow(nums, k))); // []
        nums = new int[]{1,3,1,2,0,5};
        k = 3;
        System.out.println(Arrays.toString(new Solution1().maxSlidingWindow(nums, k))); // 3, 3, 2, 5

    }
}
