package tree;

import java.util.Stack;

//给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
//
//例如，从根到叶子节点路径 1->2->3 代表数字 123。
//
//计算从根到叶子节点生成的所有数字之和。
//
//说明: 叶子节点是指没有子节点的节点。
//
//示例 1:
//
//输入: [1,2,3]
//   1
//  / \
// 2   3
//输出: 25
//解释:
//从根到叶子节点路径 1->2 代表数字 12.
//从根到叶子节点路径 1->3 代表数字 13.
//因此，数字总和 = 12 + 13 = 25.
//示例 2:
//
//输入: [4,9,0,5,1]
//     4
//    / \
//   9   0
//  / \
// 5   1
//输出: 1026
//解释:
//从根到叶子节点路径 4->9->5 代表数字 495.
//从根到叶子节点路径 4->9->1 代表数字 491.
//从根到叶子节点路径 4->0 代表数字 40.
//因此，数字总和 = 495 + 491 + 40 = 1026.
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution33 {


    // 基于栈的DFS
    // 1ms/36.1MB
    public int sumNumbers(TreeNode root) {
        // 空树返回0
        if(root == null) {
            return 0;
        }
        // 数字总和
        int res = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        // 遍历栈
        while(!stack.isEmpty()) {
            TreeNode current = stack.pop();
            // 遍历到叶子节点时，累加数字和
            if(current.left == null && current.right == null) {
                res+= current.val;
            }
            // 左节点入栈
            if(current.left != null) {
                // 累加父节点值到左节点
                current.left.val+= current.val*10;
                stack.push(current.left);
            }
            // 右节点入栈
            if(current.right != null) {
                // 累加父节点值到右节点
                current.right.val+= current.val*10;
                stack.push(current.right);
            }
        }
        return res;
    }

//    public int sumNumbers(TreeNode root) {
//        return dfs(root, 0);
//    }
//
//    // 基于递归
//    // 0ms/36MB
//    private int dfs(TreeNode node, int sum) {
//        // 遍历到不是叶子节点的某个为null的节点，返回0
//        if(node == null) {
//            return 0;
//        }
//        // 计算当前路径的和
//        sum = sum*10+node.val;
//        // 当前节点为叶子节点时，返回sum
//        if(node.left == null && node.right == null) {
//            return sum;
//        }else { // 递归计算
//            return dfs(node.left, sum)+dfs(node.right, sum);
//        }
//    }


    public static void main(String[] args) {
        Integer[] tree = new Integer[]{1,2,3};
        System.out.println(new Solution33().sumNumbers(TreeUtils.generateTree(tree)));
        tree = new Integer[]{4,9,0,5,1};
        System.out.println(new Solution33().sumNumbers(TreeUtils.generateTree(tree)));
    }
}
