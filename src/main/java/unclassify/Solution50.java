package unclassify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//对于非负整数X而言，X的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果X = 1231，那么其数组形式为[1,2,3,1]。
//
//给定非负整数 X 的数组形式A，返回整数X+K的数组形式。
//
//
//
//示例 1：
//
//输入：A = [1,2,0,0], K = 34
//输出：[1,2,3,4]
//解释：1200 + 34 = 1234
//示例 2：
//
//输入：A = [2,7,4], K = 181
//输出：[4,5,5]
//解释：274 + 181 = 455
//示例 3：
//
//输入：A = [2,1,5], K = 806
//输出：[1,0,2,1]
//解释：215 + 806 = 1021
//示例 4：
//
//输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
//输出：[1,0,0,0,0,0,0,0,0,0,0]
//解释：9999999999 + 1 = 10000000000
//
//
//提示：
//
//1 <= A.length <= 10000
//0 <= A[i] <= 9
//0 <= K <= 10000
//如果A.length > 1，那么A[0] != 0
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution50 {
    // 6ms/39.7MB
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res = new ArrayList<>();
        int add = 0;
        for(int i=A.length-1; i>=0; i--) {
            int sum = A[i]+(K%10)+add;
            add = sum/10;
            res.add(sum%10);
            K/= 10;
        }
        for(;K>0;) {
            int sum = K%10+add;
            add = sum/10;
            res.add(sum%10);
            K/= 10;
        }
        if(add > 0) {
            res.add(add);
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        int[] A = new int[]{1,2,0,0};
        int K = 34;
        System.out.println(new Solution50().addToArrayForm(A, K).toString()); // [1,2,3,4]
        A = new int[]{2,7,4};
        K = 181;
        System.out.println(new Solution50().addToArrayForm(A, K).toString()); // [4,5,5]
        A = new int[]{2,1,5};
        K = 806;
        System.out.println(new Solution50().addToArrayForm(A, K).toString()); // [1,0,2,1]
        A = new int[]{9,9,9,9,9,9,9,9,9,9};
        K = 1;
        System.out.println(new Solution50().addToArrayForm(A, K).toString()); // [1,0,0,0,0,0,0,0,0,0,0]
        A = new int[0];
        K = 1;
        System.out.println(new Solution50().addToArrayForm(A, K).toString()); // [1]
        A = new int[0];
        K = 1234;
        System.out.println(new Solution50().addToArrayForm(A, K).toString()); // [1]
        A = new int[]{1,2};
        K = 1234;
        System.out.println(new Solution50().addToArrayForm(A, K).toString()); // [1]
    }
}
