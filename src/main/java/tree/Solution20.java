package tree;

import java.util.Stack;

//给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
//
//你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
//
//示例 1:
//
//输入:
//Tree 1                     Tree 2
//     1                         2
//    / \                       / \
//   3   2                     1   3
//  /                           \   \
// 5                             4   7
//输出:
//合并后的树:
//     3
//    / \
//   4   5
//  / \   \
// 5   4   7
//注意: 合并必须从两个树的根节点开始。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/merge-two-binary-trees
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution20 {

    // 基于stack的dfs
    // 3ms/39.4MB
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null) {
            return t2;
        }else if(t2 == null) {
            return t1;
        }
        Stack<TreeNode> nodes1 = new Stack<>();
        Stack<TreeNode> nodes2 = new Stack<>();
        // 存储合并后的节点
        Stack<TreeNode> nodes3 = new Stack<>();
        TreeNode root = new TreeNode(t1.val+t2.val);
        nodes1.push(t1);
        nodes2.push(t2);
        nodes3.push(root);
        while(!nodes1.isEmpty() && !nodes2.isEmpty()) {
            TreeNode n1 = nodes1.pop();
            TreeNode n2 = nodes2.pop();
            TreeNode n3 = nodes3.pop();
            // 合并左叶子节点
            if(n1.left != null && n2.left != null) {
                n3.left = new TreeNode(n1.left.val+n2.left.val);
                nodes1.push(n1.left);
                nodes2.push(n2.left);
                nodes3.push(n3.left);
            }else if(n1.left == null) {
                n3.left = n2.left;
            }else if(n2.left == null) {
                n3.left = n1.left;
            }
            // 合并右叶子节点
            if(n1.right != null && n2.right != null) {
                n3.right = new TreeNode(n1.right.val+n2.right.val);
                nodes1.push(n1.right);
                nodes2.push(n2.right);
                nodes3.push(n3.right);
            }else if(n1.right == null) {
                n3.right = n2.right;
            }else if(n2.right == null) {
                n3.right = n1.right;
            }
        }
        return root;
    }

//    // 1ms/39.2MB
//    // 基于递归的dfs
//    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
//        if(t1 == null && t2 == null) {
//            return null;
//        }else if(t1 == null && t2 != null) {
//            return t2;
//        }else if(t1 != null && t2 == null) {
//            return t1;
//        }else {
//            // 合并当前节点，并递归生成左右子树
//            TreeNode node = new TreeNode(t1.val+t2.val);
//            node.left = mergeTrees(t1.left, t2.left);
//            node.right = mergeTrees(t1.right, t2.right);
//            return node;
//        }
//    }

    public static void main(String[] args) {
        Integer[] tree1 = new Integer[]{1,3,2,5,null};
        Integer[] tree2 = new Integer[]{2,1,3,null,4,null,7};
        TreeUtils.printTree(new Solution20().mergeTrees(TreeUtils.generateTree(tree1), TreeUtils.generateTree(tree2)));
        tree1 = new Integer[]{3,4,5,1,2};
        tree2 = new Integer[]{4,1,2,1};
        //           3                4
        //        4     5          1     2
        //      1   2            1
        // [7,5,7,2,2]
        TreeUtils.printTree(new Solution20().mergeTrees(TreeUtils.generateTree(tree1), TreeUtils.generateTree(tree2)));
    }
}
