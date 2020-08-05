package tree;


//在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
//除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
//如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
//
//计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
//
//示例 1:
//
//输入: [3,2,3,null,3,null,1]
//
//        3
//       / \
//      2   3
//       \   \
//        3   1
//
//输出: 7
//解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
//示例 2:
//
//输入: [3,4,5,1,3,null,1]
//
//           3
//          / \
//         4   5
//        / \   \
//       1   3   1
//
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/house-robber-iii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// dfs递归（普通递归） 975ms/39.9MB
// dfs递归（记忆化递归） 3ms/39.9MB
// 动态规划             0ms/39.4MB
public class Solution6 {

    public int rob(TreeNode root) {
        int[] res = dp(root);
        return Math.max(res[0], res[1]);
    }

    // 返回长度为2的数组，第0个元素表示"加算该节点时对应的最大值"，第1个元素表示"跳过该节点时对应的最大值"
    // 0ms/39.4MB
    private int[] dp(TreeNode node) {
        // 递归退出条件
        if(node == null) {
            return new int[2];
        }
        // 左子树的dp结果
        int[] left = dp(node.left);
        // 右子树的dp结果
        int[] right = dp(node.right);
        // 返回结果
        // 结果集[0]表示: 当前节点值+跳过当前节点左右节点值的和
        // 结果集[1]表示: 跳过当前节点值，选择/不选择当前节点左右节点值中的较大值的和
        return new int[]{left[1]+right[1]+node.val, Math.max(left[0], left[1])+Math.max(right[0], right[1])};
    }



//    public int rob(TreeNode root) {
//        Map<TreeNode, Integer> memo = new HashMap<>();
//        return dfs(root, memo);
//    }
//
//    // 最大收益为：1与2中较大的值
//    // 1,当前节点值 + 当前节点左节点的左右子节点值的和 + 当前节点右节点的左右子节点值的和
//    // 2,当前节点的左节点值 + 当前节点的右节点值
//    // 普通递归: 975ms/39.9MB
//    // 记忆化递归: 3ms/39.9MB
//    private int dfs(TreeNode node, Map<TreeNode, Integer> memo) {
//        if(memo.containsKey(node)) {
//            return memo.get(node);
//        }
//        // 遍历到叶子节点时，返回0
//        if(node == null) {
//            return 0;
//        }
//        // 当前节点 的 左节点 的 左右子节点值的和
//        int left = node.left == null ? 0 : dfs(node.left.left, memo)+dfs(node.left.right, memo);
//        // 当前节点 的 右节点 的 左右子节点值的和
//        int right = node.right == null ? 0 : dfs(node.right.left, memo)+dfs(node.right.right, memo);
//        // 返回 当前节点左右节点值的和 与 当前节点值+left+right中较大的值
//        int max = Math.max(dfs(node.left, memo)+dfs(node.right, memo), left+right+node.val);
//        memo.put(node, max);
//        return max;
//    }

    public static void main(String[] args) {
        // 7
        System.out.println(new Solution6().rob(TreeUtils.generateTree(new Integer[]{3,2,3,null,3,null,1})));
        // 9
        System.out.println(new Solution6().rob(TreeUtils.generateTree(new Integer[]{3,4,5,1,3,null,1})));
        // 7
        System.out.println(new Solution6().rob(TreeUtils.generateTree(new Integer[]{4,1,null,2,null,3})));
    }
}
