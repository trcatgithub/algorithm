package math;

//统计所有小于非负整数 n 的质数的数量。
//
// 
//
//示例 1：
//
//输入：n = 10
//输出：4
//解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
//示例 2：
//
//输入：n = 0
//输出：0
//示例 3：
//
//输入：n = 1
//输出：0
// 
//
//提示：
//
//0 <= n <= 5 * 10^6
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/count-primes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution1 {

    // 筛选
    // 1、不去访问偶数
    // 2、不去访问已经统计到的素数的奇数倍
    // 10ms/37.3MB
    public int countPrimes(int n) {
        int count = n>2 ? 1 : 0;
        boolean[] memo = new boolean[n+1];
        // 只访问奇数
        for(int i=3; i<n; i+= 2) {
            if(memo[i]) {
                continue;
            }
            // 将质数的奇数被标记为true
            for(int j=3; i*j<n; j+= 2) {
                memo[i*j] = true;
            }
            count++;
        }
        return count;
    }

//    // 暴力 个别用例超时
//    public int countPrimes(int n) {
//        int count = 0;
//        for(int i=2; i<=n; i++) {
//            boolean isPrime = true;
//            for(int j=2; j<=Math.sqrt(i); j++) {
//                if(i%j == 0) {
//                    isPrime = false;
//                    break;
//                }
//            }
//            if(isPrime) {
//                count++;
//            }
//        }
//        return count;
//    }

    public static void main(String[] args) {
        System.out.println(new Solution1().countPrimes(1)); // 0
        System.out.println(new Solution1().countPrimes(2)); // 0
        System.out.println(new Solution1().countPrimes(3)); // 1
        System.out.println(new Solution1().countPrimes(4)); // 2
        System.out.println(new Solution1().countPrimes(5)); // 2
        System.out.println(new Solution1().countPrimes(6)); // 3
        System.out.println(new Solution1().countPrimes(7)); // 3
        System.out.println(new Solution1().countPrimes(8)); // 4
        System.out.println(new Solution1().countPrimes(9)); // 4
        System.out.println(new Solution1().countPrimes(10)); // 4
        System.out.println(new Solution1().countPrimes(11)); // 4
        System.out.println(new Solution1().countPrimes(12)); // 5
        System.out.println(new Solution1().countPrimes(13)); // 5
        System.out.println(new Solution1().countPrimes(5000000)); // 348513


    }
}
