package sort;

import java.util.Arrays;

//给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
//
//对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
//
//你可以返回任何满足上述条件的数组作为答案。
//
// 
//
//示例：
//
//输入：[4,2,5,7]
//输出：[4,5,2,7]
//解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
// 
//
//提示：
//
//2 <= A.length <= 20000
//A.length % 2 == 0
//0 <= A[i] <= 1000
// 
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution7 {

    // 一次循环，记录需要交换位置的数字位置
    // 3ms/40.5MB
    public int[] sortArrayByParityII(int[] A) {
        int[] evenMemo = new int[A.length/2];
        int[] oddMemo = new int[A.length/2];
        int evenPos = -1;
        int oddPos = -1;
        int swap = 0;
        for(int i=0; i<A.length; i++) {
            if(i%2 == 0 && (A[i]&1) == 1) {
                if(evenPos >= 0) {
                    swap = A[i];
                    A[i] = A[evenMemo[evenPos]];
                    A[evenMemo[evenPos]] = swap;
                    evenPos--;
                }else {
                    oddMemo[++oddPos] = i;
                }
            }else if((i&1)==1 && A[i]%2 == 0) {
                if(oddPos >= 0) {
                    swap = A[i];
                    A[i] = A[oddMemo[oddPos]];
                    A[oddMemo[oddPos]] = swap;
                    oddPos--;
                }else {
                    evenMemo[++evenPos] = i;
                }
            }
        }
        return A;
    }

//    // 两次循环
//    // 第一次先把所有偶数复制到新数组
//    // 第二次把所有奇数复制到新数组
//    // 3ms/41.4MB
//    public int[] sortArrayByParityII(int[] A) {
//        int[] res = new int[A.length];
//        for(int i=0, pos=0; i<A.length && pos<res.length; i++) {
//            if(A[i]%2 == 0) {
//                res[pos] = A[i];
//                pos+= 2;
//            }
//        }
//
//        for(int i=0, pos=1; i<A.length && pos<res.length; i++) {
//            if((A[i]&1) == 1) {
//                res[pos] = A[i];
//                pos+= 2;
//            }
//        }
//        return res;
//    }

    public static void main(String[] args) {
        int[] A = new int[]{4,2,5,7};
        System.out.println(Arrays.toString(new Solution7().sortArrayByParityII(A)));
    }
}
