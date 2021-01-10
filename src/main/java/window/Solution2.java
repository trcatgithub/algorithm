package window;

import java.util.ArrayList;
import java.util.List;

//给定一个无重复元素的有序整数数组 nums 。
//
//返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
//
//列表中的每个区间范围 [a,b] 应该按如下格式输出：
//
//"a->b" ，如果 a != b
//"a" ，如果 a == b
//
//
//示例 1：
//
//输入：nums = [0,1,2,4,5,7]
//输出：["0->2","4->5","7"]
//解释：区间范围是：
//[0,2] --> "0->2"
//[4,5] --> "4->5"
//[7,7] --> "7"
//示例 2：
//
//输入：nums = [0,2,3,4,6,8,9]
//输出：["0","2->4","6","8->9"]
//解释：区间范围是：
//[0,0] --> "0"
//[2,4] --> "2->4"
//[6,6] --> "6"
//[8,9] --> "8->9"
//示例 3：
//
//输入：nums = []
//输出：[]
//示例 4：
//
//输入：nums = [-1]
//输出：["-1"]
//示例 5：
//
//输入：nums = [0]
//输出：["0"]
// 
//
//提示：
//
//0 <= nums.length <= 20
//-2^31 <= nums[i] <= 2^31 - 1
//nums 中的所有值都 互不相同
//nums 按升序排列
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/summary-ranges
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution2 {

    // 基于StringBuilder
    // 0ms/36.3MB
    public List<String> summaryRanges(int[] nums) {
        if(nums.length == 0) {
            return new ArrayList<>();
        }
        int start = nums[0];
        int prev = nums[0];
        List<String> res = new ArrayList<>();
        for(int i=1; i<nums.length; i++) {
            if(prev + 1 < nums[i]) {
                StringBuilder temp = new StringBuilder();
                temp.append(start);
                if(prev > start) {
                    temp.append("->").append(prev);
                }
                res.add(temp.toString());
                start = nums[i];
            }
            prev = nums[i];
        }
        StringBuilder temp = new StringBuilder();
        temp.append(start);
        if(prev > start) {
            temp.append("->").append(nums[nums.length-1]);
        }
        res.add(temp.toString());
        return res;
    }

//    // 8ms/37MB
//    public List<String> summaryRanges(int[] nums) {
//        if(nums.length == 0) {
//            return new ArrayList<>();
//        }
//        int start = nums[0];
//        int prev = nums[0];
//        List<String> res = new ArrayList<>();
//        for(int i=1; i<nums.length; i++) {
//            if(prev + 1 < nums[i]) {
//                if(start == prev) {
//                    res.add(start+"");
//                }else {
//                    res.add(start+"->"+prev);
//                }
//                start = nums[i];
//            }
//            prev = nums[i];
//        }
//        if(start == prev) {
//            res.add(start+"");
//        }else {
//            res.add(start+"->"+nums[nums.length-1]);
//        }
//        return res;
//    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,4,5,7};
        System.out.println(new Solution2().summaryRanges(nums).toString()); // ["0->2","4->5","7"]
        nums = new int[]{0,2,3,4,6,8,9};
        System.out.println(new Solution2().summaryRanges(nums).toString()); // ["0","2->4","6","8->9"]
        nums = new int[0];
        System.out.println(new Solution2().summaryRanges(nums).toString()); // []
        nums = new int[]{-1};
        System.out.println(new Solution2().summaryRanges(nums).toString()); // ["-1"]
        nums = new int[]{0};
        System.out.println(new Solution2().summaryRanges(nums).toString()); // ["0"]
    }
}
