package sort;

//给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
//区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
//
//说明:
//最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
//
//示例:
//
//输入: nums = [-2,5,-1], lower = -2, upper = 2,
//输出: 3
//解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/count-of-range-sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution5 {

    // 利用归并排序（对前缀和数组排序）
    // 对前缀和数组排序并不会修改数组中元素的值，只是改变了元素的位置，
    // 15ms/38.6MB
    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums.length == 0) {
            return 0;
        }
        // 构造前缀和数组
        long[] sum = new long[nums.length];
        sum[0]+= nums[0];
        for(int i=1; i<nums.length; i++) {
            sum[i]+= nums[i];
            sum[i]+= sum[i-1];
        }
        // 在归并排序过程中计算出组合数
        return helper(sum, lower, upper, 0, nums.length-1);
    }

    private int helper(long[] nums, int lower, int upper, int start, int end) {
        // 处理一个元素的序列
        if(start == end) {
            return (nums[start] >= lower && nums[start] <= upper ? 1 : 0);
        }
        // 处理两个元素的序列
        if(start+1 == end) {
            int count = 0;
            long gap = nums[end]-nums[start];
            count+= (gap >= lower && gap <= upper ? 1 : 0 );
            count+= (nums[end] >= lower && nums[end] <= upper ? 1 : 0 );
            count+= (nums[start] >= lower && nums[start] <= upper ? 1 : 0 );
            if(nums[start] > nums[end]) {
                long temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
            }
            return count;
        }
        int count = 0;
        int mid = (start+end)/2;
        count+= helper(nums, lower, upper, start, mid);
        count+= helper(nums, lower, upper, mid+1, end);
        // 合并序列
        count+= merge(nums, lower, upper, start, end);
        return count;
    }

    // 计算start到end范围内，符合条件的序列个数
    private int calculate(long[] nums, int lower, int upper, int start, int end, int p1, int p2) {
        int count = 0;
        if(p2 == -1) {
            for(int pos=start; pos<=end; pos++) {
                if(nums[pos] - nums[p1] >= lower && nums[pos] - nums[p1] <= upper) {
                    count++;
                }else if(nums[pos] - nums[p1] > upper) {
                    break;
                }
            }
        }else {
            for(int pos=start; pos<=end; pos++) {
                if(nums[p2] - nums[pos] >= lower && nums[p2] - nums[pos] <= upper) {
                    count++;
                }else if(nums[p2] - nums[pos] < lower) {
                    break;
                }
            }
        }
        return count;
    }

    // 合并序列，在合并的过程中计算符合条件的序列个数
    private int merge(long[] nums, int lower, int upper, int start, int end) {
        long[] temp = new long[end-start+1];
        int count = 0;
        int mid = (start+end)/2;
        int p0 = 0;
        for(int p1=start, p2=mid+1; p1<=mid || p2<=end;) {
            if(p1 > mid) {
                System.arraycopy(nums, p2, temp, p0, end-p2+1);
                break;
            }
            if(p2 > end) {
                System.arraycopy(nums, p1, temp, p0, mid-p1+1);
                break;
            }
            if(nums[p1] >= nums[p2]) {
                count+= calculate(nums, lower, upper, p1, mid, -1, p2);
                temp[p0++] = nums[p2++];
            }else {
                count+= calculate(nums, lower, upper, p2, end, p1, -1);
                temp[p0++] = nums[p1++];
            }
        }
        // 用排序后的数组替换原数组
        System.arraycopy(temp, 0, nums, start, temp.length);
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2,5,-1};
        int lower = -2;
        int upper = 2;
        // -2 3 2
        System.out.println(new Solution5().countRangeSum(nums, lower, upper)); // 3
        // [] -564 3864
        nums = new int[]{-2147483647,0,-2147483647,2147483647};
        lower = -564;
        upper = 3864;
        // -2147483647， -2147483647， -2147483647， -2147483647*2
        System.out.println(new Solution5().countRangeSum(nums, lower, upper)); // 3
        nums = new int[]{1,1,2};
        lower = 0;
        upper = 2;
        // 1, 2, 4
        System.out.println(new Solution5().countRangeSum(nums, lower, upper)); // 4
        nums = new int[]{2147483647,-2147483648,-1,0};
        lower = -1;
        upper = 0;
        // 2147483647 -1 -2 -2
        System.out.println(new Solution5().countRangeSum(nums, lower, upper)); // 4
        nums = new int[]{0,-3,-3,1,1,2};
        lower = 3;
        upper = 5;
        // 0 -3 -6 -5 -4 -2
        System.out.println(new Solution5().countRangeSum(nums, lower, upper)); // 2
    }
}
