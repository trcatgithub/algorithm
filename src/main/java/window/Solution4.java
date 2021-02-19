package window;

//给定一个由若干 0 和 1 组成的数组A，我们最多可以将K个值从 0 变成 1 。
//
//返回仅包含 1 的最长（连续）子数组的长度。
//
//
//
//示例 1：
//
//输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
//输出：6
//解释：
//[1,1,1,0,0,1,1,1,1,1,1]
//粗体数字从 0 翻转到 1，最长的子数组长度为 6。
//示例 2：
//
//输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
//输出：10
//解释：
//[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
//粗体数字从 0 翻转到 1，最长的子数组长度为 10。
//
//
//提示：
//
//1 <= A.length <= 20000
//0 <= K <= A.length
//A[i] 为0或1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/max-consecutive-ones-iii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution4 {
    // 滑动窗口
    // 3ms/39.7MB
    public int longestOnes(int[] A, int K) {
        int num = K;
        int left = 0,right=0;
        for(;right<A.length;right++){
            // 消耗K
            if(A[right]==0){
                num--;
            }
            // 右移窗口左边界 同时 补充K
            if(num<0 && A[left++]==0){
                num++;
            }
        }
        return right-left;
    }

    public static void main(String[] args) {
        int[] A = new int[]{1,1,1,0,0,0,1,1,1,1,0};
        int K = 2;
        System.out.println(new Solution4().longestOnes(A, K)); // 6
        A = new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        K = 3;
        System.out.println(new Solution4().longestOnes(A, K)); // 10
    }
}
