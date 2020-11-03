package unclassify;

import java.util.Stack;

//给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
//
//让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
//
//A.length >= 3
//在 0 < i < A.length - 1 条件下，存在 i 使得：
//A[0] < A[1] < ... A[i-1] < A[i]
//A[i] > A[i+1] > ... > A[A.length - 1]
// 
//
//
//
// 
//
//示例 1：
//
//输入：[2,1]
//输出：false
//示例 2：
//
//输入：[3,5,5]
//输出：false
//示例 3：
//
//输入：[0,3,2,1]
//输出：true
// 
//
//提示：
//
//0 <= A.length <= 10000
//0 <= A[i] <= 10000 
// 
//
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/valid-mountain-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
public class Solution36 {

    // 从前向后遍历，以下情况返回false，其余情况返回flag
    // 1, 降序状态时，出现 当前节点值大于prev的情况(说明降序后出现升序)
    // 2, 从位置1开始出现降序(说明之前没有升序序列)
    // 3, 存在连续相同的节点
    // 2ms/39.7MB
    public boolean validMountainArray(int[] A) {
        int prev = -1;
        // false: 升序     true: 降序
        boolean flag = false;
        for(int i=0; i<A.length; i++) {
            // 初始化prev
            if(prev == -1) {
                prev = A[i];
                continue;
            }
            // 当前值 大于 prev
            if(A[i] > prev) {
                // 若当前处于降序状态，则直接返回false
                if(flag) {
                    return false;
                }
            }else if(A[i] < prev) { // 当前值 小于 prev
                // i之前没有升序序列，直接返回false
                if(i == 1) {
                    return false;
                }
                // 将序列标记为降序
                if(!flag) {
                    flag = true;
                }
            }else { // 存在连续相同的节点时，返回false
                return false;
            }
            // 更新prev
            prev = A[i];
        }
        return flag;
    }

    public static void main(String[] args) {
        int[] A = new int[]{2,1};
        System.out.println(new Solution36().validMountainArray(A) == false);
        A = new int[]{3,5,5};
        System.out.println(new Solution36().validMountainArray(A) == false);
        A = new int[]{0,3,2,1};
        System.out.println(new Solution36().validMountainArray(A) == true);

        A = new int[]{2,1,4,7,3,2,5};
        System.out.println(new Solution36().validMountainArray(A) == false);
        A = new int[]{2,2,2};
        System.out.println(new Solution36().validMountainArray(A) == false);
        A = new int[]{1,2,3};
        System.out.println(new Solution36().validMountainArray(A) == false);
        A = new int[]{1,2,3,2};
        System.out.println(new Solution36().validMountainArray(A) == true);
        A = new int[]{2,3,3,2,0,2};
        System.out.println(new Solution36().validMountainArray(A) == false);
        A = new int[]{875,884,239,731,723,685};
        System.out.println(new Solution36().validMountainArray(A) == false);
        A = new int[]{0,2,0,0,2,0,2,1,1,0,2,1,0,0,1,0,2,1,2,0,1,1,0,2,2,
                1,2,2,0,0,0,1,0,2,0,0,1,2,0,1,0,2,0,2,0,0,0,0,2,1,0,0,0,
                0,1,0,2,1,2,2,1,0,0,1,0,2,0,0,0,2,1, 0,1,2,1,0 ,1,0,2,1,0,
                2,0,2,1,1,2,0,1,0,1,1,1,1,2,1,2,2,2,0};
        System.out.println(new Solution36().validMountainArray(A) == false); // 5
        A = new int[]{0,2,1,0,1,2,1,0};
        System.out.println(new Solution36().validMountainArray(A) == false); // 5
    }
}
