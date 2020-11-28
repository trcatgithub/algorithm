package sort;


//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
//
//你需要返回给定数组中的重要翻转对的数量。
//
//示例 1:
//
//输入: [1,3,2,3,1]
//输出: 2
//示例 2:
//
//输入: [2,4,3,5,1]
//输出: 3
//注意:
//
//给定数组的长度不会超过50000。
//输入数组中的所有数字都在32位整数的表示范围内。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/reverse-pairs
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution13 {

    // 9,3,4,1
    // 3,9 1,4   2
    // 2,4,3,5,1

    // 2,4,3     5,1
    // 2,4    3      5,1
    // 2,3,4   1,5
    // 归并排序
    // 60ms/47.6MB
    public int reversePairs(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        return helper(nums, 0, nums.length-1);
    }

    private int helper(int[] nums, int start, int end) {
        if(start == end) {
            return 0;
        }else if(start+1 == end) {
            if(nums[start] > nums[end]) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                return nums[start]*2l < nums[end] ? 1 : 0;
            }
            return nums[end]*2l < nums[start] ? 1 : 0;
        }
        int mid = (start+end)/2;
        return helper(nums, start, mid) + helper(nums, mid+1, end) + merge(nums, start, mid, mid+1, end);
    }

    private int merge(int[] nums, int start1, int end1, int start2, int end2) {
        // 统计重要翻转对
        int count = 0;
        int beginPosition = end2;
        // 由大向小判断
        for(int i=end1; i>=start1; i--) {
            // 当前值 < 第二数组的最小值*2时，退出循环
            if(nums[i] < nums[start2]*2l) {
                break;
            }
            // 由大向小判断第二数组，每次更新起始位置
            for(int j=beginPosition; j>=start2; j--) {
                if(nums[i] > nums[j]*2l) {
                    count+= (j-start2+1);
                    beginPosition = j;
                    break;
                }
            }
        }
        // 数组排序
        int[] temp = new int[end2-start1+1];
        for(int p0=0, p1=start1, p2=start2; p0<temp.length; p0++) {
            if(p1 > end1 || (p2 <= end2 && nums[p1] < nums[p2])) {
                temp[p0] = (p1 > end1 ? nums[p2++] : nums[p1++]);
            }else {
                temp[p0] = (p2 > end2 ? nums[p1++] : nums[p2++]);
            }
        }
        System.arraycopy(temp, 0, nums, start1, temp.length);
        return count;
    }

//    // 暴力(个别用例超时)
//    public int reversePairs(int[] nums) {
//        int count = 0;
//        for(int i=nums.length-1; i>0; i--) {
//            for(int j=i-1; j>=0; j--) {
//                if(nums[i]*2l < nums[j]) {
//                    count++;
//                }
//            }
//        }
//        return count;
//    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,2,3,1};
        System.out.println(new Solution13().reversePairs(nums)); // 2
        nums = new int[]{2,4,3,5,1};
        System.out.println(new Solution13().reversePairs(nums)); // 3
        nums = new int[50000];
        for(int i=50000; i>0; i--) {
            nums[50000-i] = i;
        }
        System.out.println(new Solution13().reversePairs(nums)); // 624975000
        nums = new int[]{2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
        System.out.println(new Solution13().reversePairs(nums)); // 0
    }
}
