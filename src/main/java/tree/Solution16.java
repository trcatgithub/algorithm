package tree;

import java.util.LinkedList;
import java.util.Queue;

//计算给定二叉树的所有左叶子之和。
//
//示例：
//
//       3
//      / \
//     9  20
//       /  \
//      15   7
//
//在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/sum-of-left-leaves
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution16 {

    // 基于Queue的bfs
    // 1ms/36.7MB
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Queue<TreeNode> memo = new LinkedList<>();
        memo.offer(root);
        int res = 0;
        while(!memo.isEmpty()) {
            TreeNode current = memo.poll();
            // 找到左叶子节点时，累计节点值
            if(current.left != null) {
                if(current.left.left == null && current.left.right == null) {
                    res+= current.left.val;
                }
                memo.offer(current.left);
            }
            if(current.right != null) {
                memo.offer(current.right);
            }
        }
        return res;
    }

//    public int sumOfLeftLeaves(TreeNode root) {
//        return dfs(root);
//    }
//
//    // 基于递归的dfs
//    // 0ms/37MB
//    private int dfs(TreeNode node) {
//        // 退出条件
//        if(node == null) {
//            return 0;
//        }
//        // 当前节点的左节点为叶子节点时
//        if(node.left != null && node.left.left == null && node.left.right == null) {
//            return node.left.val + dfs(node.right);
//        }
//        // 向左右寻找叶子节点
//        return dfs(node.left)+dfs(node.right);
//    }

    public static void main(String[] args) {
        Integer[] tree = new Integer[]{3,9,20,null,null,15,7};
        System.out.println(new Solution16().sumOfLeftLeaves(TreeUtils.generateTree(tree)));
    }
}
