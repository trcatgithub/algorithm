package unclassify;

import java.util.HashMap;
import java.util.Map;

//给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
//
//为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1 。
//
//例如:
//
//输入:
//A = [ 1, 2]
//B = [-2,-1]
//C = [-1, 2]
//D = [ 0, 2]
//
//输出:
//2
//
//解释:
//两个元组如下:
//1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
//2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/4sum-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution43 {

    // 利用HashMap
    // 69ms/57.4MB
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        int len = A.length;
        // 保存A与B元素和以及该和的出现次数
        Map<Integer, Integer> memo = new HashMap<>();
        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                memo.put(A[i]+B[j], memo.getOrDefault(A[i]+B[j], 0)+1);
            }
        }
        // 判断memo中是否包含-(C[i]+D[i])
        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                count+= memo.getOrDefault(-C[i]-D[j], 0);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] A = new int[]{1,2};
        int[] B = new int[]{-2,-1};
        int[] C = new int[]{-1,2};
        int[] D = new int[]{0,2};
        System.out.println(new Solution43().fourSumCount(A, B, C, D)); // 2
        A = new int[]{1,2};
        B = new int[]{1,2};
        C = new int[]{-1,-2};
        D = new int[]{-1,-2};
        System.out.println(new Solution43().fourSumCount(A, B, C, D)); // 2
    }
}
