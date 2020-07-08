package tree;


// https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
//
//百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
// 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
//
//例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
//                                     3
//                              5           1
//                          6      2      0   8
//                        n   n  7   4
//
//         
//
//示例 1:
//
//输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出: 3
//解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
//示例 2:
//
//输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出: 5
//解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
//         
//
//说明:
//
//所有节点的值都是唯一的。
//p、q 为不同节点且均存在于给定的二叉树中。
//8ms/42MB
public class Solution2 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }

    // 某次dfs结果为null，表示该递归路径中不存在p和q
    // 找到p或q时，直接返回p或q
    // 同时找到p和q时，直接返回node，node即是所求父节点
    private TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
        // 当前节点为空 或 p 或 q 时，直接返回当前节点
        if(node == null || node.val == p.val || node.val == q.val ) {
            return node;
        }
        // 递归当前节点左节点
        TreeNode left = dfs(node.left, p, q);
        // 递归当前节点右节点
        TreeNode right = dfs(node.right, p, q);
        // 左右节点都为空时，返回空
        if(left == null && right == null) {
            return null;
        }else if(left == null) { // 左节点为空，右节点不为空时，返回右节点
            return right;
        }else if(right == null) { // 右节点为空，左节点不为空时，返回左节点
            return left;
        }else { // 左右节点都不为空时，返回当前节点
            return node;
        }
    }

    public static void main(String[] args) {
        Integer[] tree = new Integer[]{3,5,1,6,2,0,8,null,null,7,4};
        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(5);
        TreeNode root = TreeUtils.generateTree(tree);
        System.out.println(new Solution2().lowestCommonAncestor(root, p, q).val);;
    }
}
