package unclassify;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
//
//如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
//
// 
//
//示例 1：
//
//输入：arr = [1,2,2,1,1,3]
//输出：true
//解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
//示例 2：
//
//输入：arr = [1,2]
//输出：false
//示例 3：
//
//输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
//输出：true
// 
//
//提示：
//
//1 <= arr.length <= 1000
//-1000 <= arr[i] <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/unique-number-of-occurrences
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution32 {

    // 利用数组
    // 1ms/37.7MB
    public boolean uniqueOccurrences(int[] arr) {
        // 记录每个数字出现的次数(模拟散列)
        int[] memo = new int[2001];
        for(int num : arr) {
            memo[num+1000]++;
        }
        // 对次数进行重复判断
        int[] count = new int[1000];
        for(int num : memo) {
            if(num == 0) {
                continue;
            }
            if(count[num] > 0) { // 该次数已经被使用过，返回false
                return false;
            }
            count[num]++; // 将该次数标记为被使用
        }
        return true;
    }

//    // 基于散列
//    // 2ms/36.1MB
//    public boolean uniqueOccurrences(int[] arr) {
//        // 保存每个数字的出现次数
//        Map<Integer, Integer> memo = new HashMap<>();
//        for(int num : arr) {
//            memo.put(num, memo.getOrDefault(num, 0)+1);
//        }
//        // 判断是否有重复数字
//        Set<Integer> omit = new HashSet<>();
//        for(Map.Entry<Integer, Integer> element : memo.entrySet()) {
//            if(omit.contains(element.getValue())) {
//                return false;
//            }
//            omit.add(element.getValue());
//        }
//        return true;
//    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,2,1,1,3};
        System.out.println(new Solution32().uniqueOccurrences(arr) == true);
        arr = new int[]{1,2};
        System.out.println(new Solution32().uniqueOccurrences(arr) == false);
        arr = new int[]{-3,0,1,-3,1,1,1,-3,10,0};
        System.out.println(new Solution32().uniqueOccurrences(arr) == true);

    }
}
