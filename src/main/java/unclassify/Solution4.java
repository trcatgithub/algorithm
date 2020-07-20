package unclassify;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//    给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
//
//    函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
//
//    说明:
//
//    返回的下标值（index1 和 index2）不是从零开始的。
//    你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
//    示例:
//
//    输入: numbers = [2, 7, 11, 15], target = 9
//    输出: [1,2]
//    解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution4 {

//    // 空间换时间，先构造hashmap，然后使用hashmap遍历数组
//    // 5ms/40.2MB
//    public int[] twoSum(int[] numbers, int target) {
//        // key:数组元素值   value:数组下标
//        Map<Integer, Integer> memo = new HashMap<>();
//        for(int i=0; i<numbers.length; i++) {
//            memo.put(numbers[i], i+1);
//        }
//        // 遍历数组，寻找符合条件的数组下标
//        for(int i=0; i<numbers.length; i++) {
//            if(memo.containsKey(target-numbers[i])) {
//                return new int[]{i+1, memo.get(target-numbers[i])};
//            }
//        }
//        // 默认值
//        return new int[0];
//    }

    // 数组有序，利用双指针
    // 左右指针元素和大于target，右指针--
    // 左右指针元素和小于target，左指针++
    // 左右指针元素和等于target，返回left+1与right+1
    // 1ms/40MB
    public int[] twoSum(int[] numbers, int target) {
        for(int left=0, right=numbers.length-1; left<right;) {
            if(numbers[left]+numbers[right] == target) {
                return new int[]{left+1, right+1};
            }else if(numbers[left]+numbers[right] < target) {
                left++;
            }else {
                right--;
            }
        }
        return new int[0];
    }


    public static void main(String[] args) {
        int[] numbers = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(new Solution4().twoSum(numbers, target)));
    }
}
