package math;

//给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
//
//进阶：不要 使用任何内置的库函数，如  sqrt 。
//
// 
//
//示例 1：
//
//输入：num = 16
//输出：true
//示例 2：
//
//输入：num = 14
//输出：false
// 
//
//提示：
//
//1 <= num <= 2^31 - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/valid-perfect-square
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution14 {
    // 1 1     1
    // 2 4     2*2
    // 3 9     3*3
    // 4 16    2*2*2*2
    // 5 25    5*5
    // 6 36    2*2*3*3
    // 7 49    7*7
    // 8 64    2*2*2*2*2*2
    // 9 81    3*3*3*3
    //10 100   2*2*5*5
    //11 121   11*11
    //12 144   2*2*2*2*3*3
    //13 169
    //14 196
    //15 215
    //16 256
    //17 289
    //18 324
    //19 361
    //20 400

    // 0ms/35.2MB
    // 1. 二分法
    // 2. 右侧上线为num/2
    // 3. 注意数值有效范围
    public boolean isPerfectSquare(int num) {
        if(num == 1) {
            return true;
        }
        for(int left=1,right=num/2;left<=right;) {
            int mid = (left+right)/2;
            // 需要转换为long，int会发生截位
            long value = (long)mid*mid;
            if(value == num) {
                return true;
            }else if(value < num) {
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return false;
    }


    public static void main(String[] args) {
//        System.out.println(6+" : "+new Solution14().isPerfectSquare(6));
//        for(int i=1; i<100; i++) {
//            System.out.println(i+" : "+new Solution14().isPerfectSquare(i));
//        }
        System.out.println(new Solution14().isPerfectSquare(808201));
    }
}
