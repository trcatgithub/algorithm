package sort;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
//示例:
//
//输入: [0,1,0,3,12]
//输出: [1,3,12,0,0]
//说明:
//
//必须在原数组上操作，不能拷贝额外的数组。
//尽量减少操作次数。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/move-zeroes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution11 {

    // 利用数组+双指针模拟双端队列
    // 0ms/38.8MB
    public void moveZeroes(int[] nums) {
        int[] memo = new int[nums.length];
        int head = -1;
        int tail = -1;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] == 0) {
                memo[++tail] = i;
                if(head == -1) {
                    head = 0;
                }
            }else if(head >= 0) {
                int temp = nums[i];
                nums[i] = nums[memo[head]];
                nums[memo[head]] = temp;
                head++;
                memo[++tail] = i;
            }
        }
    }

//    // 基于双端队列
//    // 2ms/38.9MB
//    public void moveZeroes(int[] nums) {
//        Deque<Integer> memo = new LinkedList<>();
//        for(int i=0; i<nums.length; i++) {
//            if(nums[i] == 0) {
//                memo.offerLast(i);
//            }else if(memo.size() > 0) {
//                int swapPos = memo.pollFirst();
//                int temp = nums[i];
//                nums[i] = nums[swapPos];
//                nums[swapPos] = temp;
//                memo.offerLast(i);
//            }
//        }
//    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        new Solution11().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
