package tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
//
// 
//
//例如：
//
//输入: 原始二叉搜索树:
//       5
//     /   \
//    2     13
//
//输出: 转换为累加树:
//       18
//      /   \
//    20     13
// 
//
//注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution18 {

    // 非递归 逆中序遍历(右->中->左)
    // 4ms/38.9MB
    public TreeNode convertBST(TreeNode root) {
        if(root == null) {
            return null;
        }
        int sum = 0;
        Stack<TreeNode> memo = new Stack<>();
        memo.push(root);
        // 当前节点
        TreeNode current = root;
        while(!memo.isEmpty()) {
            // 优先处理右子树
            if(current!=null && current.right!=null) {
                memo.add(current.right);
                current = current.right;
            }else { // 不存在右子树 或 当前节点为空时
                // 加算当前节点值
                current = memo.pop();
                current.val+= sum;
                sum = current.val;
                // 处理左子树
                if(current!=null && current.left!=null) {
                    memo.add(current.left);
                    current = current.left;
                }else { // 标记处理过的节点，防止重复处理
                    current = null;
                }
            }
        }
        return root;
    }

//    // 逆中序遍历(右->中->左)
//    // 1ms/38.9MB
//    private int sum = 0;
//    public TreeNode convertBST(TreeNode root) {
//        // 退出条件
//        if(root == null) {
//            return null;
//        }
//        // 递归计算右子树节点值
//        convertBST(root.right);
//        // 改变当前节点
//        root.val+= sum;
//        // 更新sum
//        sum = root.val;
//        // 递归计算左子树节点值
//        convertBST(root.left);
//        return root;
//    }

    public static void main(String[] args) {
        //       5
        //     /   \
        //    2     13
        Integer[] tree = new Integer[]{5,2,13}; // [18,20,13]
        TreeUtils.printTree(new Solution18().convertBST(TreeUtils.generateTree(tree)));

        //         2
        //      0     3
        //   -4   1
        tree = new Integer[]{2,0,3,-4,1}; // [5,6,3,2,6]
        TreeUtils.printTree(new Solution18().convertBST(TreeUtils.generateTree(tree)));

        //               1                              8
        //          0         4                    8         4
        //       -2   N     3                    6   N     7
        tree = new Integer[]{1,0,4,-2,null,3}; // [8,8,4,6,null,7]
        TreeUtils.printTree(new Solution18().convertBST(TreeUtils.generateTree(tree)));

        //               -3                            -4
        //           -4       0                     -8      1
        //                 -2   1                        -1   1
        tree = new Integer[]{-3,-4,0,null,null,-2,1}; // [-4,-8,1,null,null,-1,1]
        TreeUtils.printTree(new Solution18().convertBST(TreeUtils.generateTree(tree)));

        //               4
        //             2
        //         -3
        //            -1
        //               0
        tree = new Integer[]{4,2,null,-3,null,null,-1,null,0}; // [4,6,null,2,null,null,5,null,6]
        TreeUtils.printTree(new Solution18().convertBST(TreeUtils.generateTree(tree)));
    }
}
