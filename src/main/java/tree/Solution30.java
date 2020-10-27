package tree;

import java.util.*;

//给定一个二叉树，返回它的 前序 遍历。
//
// 示例:
//
//输入: [1,null,2,3]
//  1
//   \
//    2
//   /
//  3
//
//输出: [1,2,3]
//进阶: 递归算法很简单，你可以通过迭代算法完成吗？
//
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution30 {

    // 基于Stack进行前序遍历
    // 1ms/36.5MB
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode current = stack.pop();
            res.add(current.val);
            // Stack先进后出，此处要先将右叶子节点入栈，后将左叶子节点入栈
            // 实际的处理顺序才是 先处理左叶子节点，后处理右叶子节点
            if(current.right != null) {
                stack.push(current.right);
            }
            if(current.left != null) {
                stack.push(current.left);
            }
        }
        return res;
    }

//    // 基于递归
//    // 0ms/36.4MB
//    public List<Integer> preorderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList<>();
//        preDfs(res, root);
//        return res;
//    }
//
//    private void preDfs(List<Integer> res, TreeNode node) {
//        if(node == null) {
//            return;
//        }
//        res.add(node.val);
//        preDfs(res, node.left);
//        preDfs(res, node.right);
//    }

    public static void main(String[] args) {
        Integer[] tree = new Integer[]{1,null,2,3};
        System.out.println(new Solution30().preorderTraversal(TreeUtils.generateTree(tree)));
        tree = new Integer[]{1,2,3,4,5,6,7,8,9};// 1 2 4 8 9 5 3 6 7
        //               1
        //            2     3
        //          4   5 6   7
        //         8 9
        System.out.println(new Solution30().preorderTraversal(TreeUtils.generateTree(tree)));
    }
}
