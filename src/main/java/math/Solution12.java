package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给定由若干0和1组成的数组 A。我们定义N_i：从A[0] 到A[i]的第 i个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
//
//返回布尔值列表answer，只有当N_i可以被 5整除时，答案answer[i] 为true，否则为 false。
//
//
//
//示例 1：
//
//输入：[0,1,1]
//输出：[true,false,false]
//解释：
//输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。
//示例 2：
//
//输入：[1,1,1]
//输出：[false,false,false]
//示例 3：
//
//输入：[0,1,1,1,1,1]
//输出：[true,false,false,false,true,false]
//示例4：
//
//输入：[1,1,1,0,1]
//输出：[false,false,false,false,false]
//
//
//提示：
//
//1 <= A.length <= 30000
//A[i] 为0或1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/binary-prefix-divisible-by-5
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution12 {
    // 0,1,1,1,1,1
    // 0,1,3,7,15,31
    // 最后一位是5或0(000, 101)
    // 3ms/39.8MB
    public List<Boolean> prefixesDivBy5(int[] A) {
        Boolean[] res = new Boolean[A.length];
        int num = 0;
        for (int i = 0;i < A.length;i++) {
            // num = num*2
            num <<= 1;
            num += A[i];
            num %= 5;
            res[i] = (num == 0);
        }
        return Arrays.asList(res);
    }

    public static void main(String[] args) {
        int[] A = new int[]{0, 1, 1}; // [true,false,false]
        System.out.println(new Solution12().prefixesDivBy5(A));
        A = new int[]{1, 1, 1}; // [false,false,false]
        System.out.println(new Solution12().prefixesDivBy5(A));
        A = new int[]{0, 1, 1, 1, 1, 1}; // [true,false,false,false,true,false]
        System.out.println(new Solution12().prefixesDivBy5(A));
        A = new int[]{1, 1, 1, 0, 1}; // [false,false,false,false,false]
        System.out.println(new Solution12().prefixesDivBy5(A));
    }
}
