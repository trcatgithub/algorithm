package sort;

import java.util.Arrays;

//给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
//
// 
//
//示例 1：
//
//输入：[-4,-1,0,3,10]
//输出：[0,1,9,16,100]
//示例 2：
//
//输入：[-7,-3,2,3,11]
//输出：[4,9,9,49,121]
// 
//
//提示：
//
//1 <= A.length <= 10000
//-10000 <= A[i] <= 10000
//A 已按非递减顺序排序。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution3 {

    // 利用数组有序
    // 使用双指针，由边界向中心进行判断
    // 2ms/40.4MB
    public int[] sortedSquares(int[] A) {
        int[] res = new int[A.length];
        int pos = res.length-1;
        for(int l=0, r=A.length-1; l<=r;) {
            if(A[l] < 0 && A[r] < 0) {
                res[pos--] = A[l]*A[l];
                l++;
            }else if(A[l] >= 0 && A[r] >= 0) {
                res[pos--] = A[r]*A[r];
                r--;
            }else if(A[l] < 0 && A[r] >= 0) {
                if(Math.abs(A[l]) > A[r]) {
                    res[pos--] = A[l]*A[l];
                    l++;
                }else {
                    res[pos--] = A[r]*A[r];
                    r--;
                }
            }
        }
        return res;
    }


//    // 直接排序
//    // 3ms/40.4MB
//    public int[] sortedSquares(int[] A) {
//        int[] res = new int[A.length];
//        for(int i=0; i<A.length; i++) {
//            A[i] = Math.abs(A[i]);
//        }
//        Arrays.sort(A);
//        for(int i=0; i<A.length; i++) {
//            res[i] = A[i]*A[i];
//        }
//        return res;
//    }

    public static void main(String[] args) {
        int[] A = new int[]{-4,-1,0,3,10};
        System.out.println(Arrays.toString(new Solution3().sortedSquares(A)));
        A = new int[]{-7,-3,2,3,11};
        System.out.println(Arrays.toString(new Solution3().sortedSquares(A)));
    }
}
