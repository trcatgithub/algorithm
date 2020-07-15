package dp;
//给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
//
//示例:
//
//输入: 3
//输出: 5
//解释:
//给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
//
//    1         3     3      2      1
//     \       /     /      / \      \
//      3     2     1      1   3      2
//     /     /       \                 \
//    2     1         2                 3
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/unique-binary-search-trees
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// 0ms/36.3MB
public class Solution3 {
    // 解题思路：假设n个节点存在二叉排序树的个数是G(n)，1为根节点，2为根节点，...，n为根节点，
    // 当1为根节点时，其左子树节点个数为0，右子树节点个数为n-1，同理当2为根节点时，其左子树节点个数为1，
    // 右子树节点为n-2，所以可得G(n) = G(0)*G(n-1)+G(1)*(n-2)+...+G(n-1)*G(0)
    // (n=1,count=dp[0]*dp[0])
    //     1
    //dp[0]  dp[0]
    // (n=2,count=dp[0]*dp[1]+dp[1]*dp[0])
    //     1               2
    //dp[0]  dp[1]   dp[1]  dp[0]
    // (n=3,count=dp[0]*dp[2]+dp[1]*dp[1]+dp[2]*dp[0])
    //     1               2               3
    //dp[0]  dp[2]   dp[1]  dp[1]    dp[2]   dp[0]
    // (n=4,count=dp[0]*dp[3]+dp[1]*dp[2]+dp[2]*dp[1]+dp[3]*dp[0])
    //     1               2               3                 4
    //dp[0]  dp[3]   dp[1]  dp[2]    dp[2]   dp[1]      dp[3] dp[0]
    public int numTrees(int n) {
        // 保存每次计算的结果
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        // 从节点数为2开始，计算到节点数为n时，树的个数
        for(int i=2; i<=n; i++) {
            int sum = 0;
            for(int j=0; j<i; j++) {
                sum+= dp[j]*dp[i-j-1];
            }
            dp[i] = sum;
        }
        return dp[n];
    }

    public static void main(String[] args) {



//case 1:  return 1;
//case 2:  return 2;
//case 3:  return 5;
//case 4:  return 14;
//case 5:  return 42;
//case 6:  return 132;
//case 7:  return 429;
//case 8:  return 1430;
//case 9:  return 4862;
//case 10: return 16796;
//case 11: return 58786;
//case 12: return 208012;
//case 13: return 742900;
//case 14: return 2674440;
//case 15: return 9694845;
//case 16: return 35357670;
//case 17: return 129644790;
//case 18: return 477638700;
//case 19: return 1767263190;
//default: return 0;

        int n = 1;
        System.out.println(new Solution3().numTrees(n));
        n = 2;
        System.out.println(new Solution3().numTrees(n));
        n = 3;
        System.out.println(new Solution3().numTrees(n));
        n = 4;
        System.out.println(new Solution3().numTrees(n));
        n = 5;
        System.out.println(new Solution3().numTrees(n));
        n = 6;
        System.out.println(new Solution3().numTrees(n));
        n = 7;
        System.out.println(new Solution3().numTrees(n));
        n = 8;
        System.out.println(new Solution3().numTrees(n));
        n = 9;
        System.out.println(new Solution3().numTrees(n));
        n = 10;
        System.out.println(new Solution3().numTrees(n));
    }
}
