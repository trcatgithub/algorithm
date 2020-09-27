package tree;

//给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
//
//百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
//满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
//
//例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
//
//
//
// 
//
//示例 1:
//
//输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
//输出: 6
//解释: 节点 2 和节点 8 的最近公共祖先是 6。
//示例 2:
//
//输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
//输出: 2
//解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
// 
//
//说明:
//
//所有节点的值都是唯一的。
//p、q 为不同节点且均存在于给定的二叉搜索树中。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution23 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return helper(root, p, q);
    }

    // 利用二叉搜索树的有序性
    // 6ms/39.8MB
    private TreeNode helper(TreeNode node, TreeNode p, TreeNode q) {
        // p，q在node左侧时，递归遍历node左子树
        if(node.val > p.val && node.val > q.val) {
            return helper(node.left, p, q);
        }else if(node.val < p.val && node.val < q.val) { // p，q在node右侧时，递归遍历node右子树
            return helper(node.right, p, q);
        }else { // p，q分别在node左右两侧，node即是所求最近公共祖先
            return node;
        }
    }

//    // 基于递归的dfs
//    // 8ms/39.6MB
//    private TreeNode helper(TreeNode node, TreeNode p, TreeNode q) {
//        // 遍历到叶子节点时，直接返回null
//        if(node == null) {
//            return null;
//        }
//        // 找到p或q时，直接返回该节点
//        if(node.val == p.val || node.val == q.val) {
//            return node;
//        }
//        // 递归左右子节点
//        TreeNode left = helper(node.left, p, q);
//        TreeNode right = helper(node.right, p, q);
//        // left与right有1个为null时，说明当前节点不是公共祖先，需要向上查找
//        // 返回右节点
//        if(left == null) {
//            return right;
//        }else if(right == null) { // 返回左节点
//            return left;
//        }else {
//            // 当p与q分别处于node的左右子节点递归结果中时
//            // p与q分别位于node的左右两侧，即node即是p与q的最近公共祖先
//            if((left.val == p.val && right.val == q.val) || (left.val == q.val && right.val == p.val)) {
//                return node;
//            }else {
//                return null;
//            }
//        }
//    }

    public static void main(String[] args) {
        // 6,2,8,0,4,7,9,null,null,3,5
        //                 6
        //             2       8
        //           0   4   7   9
        //              3 5
        Integer[] tree = new Integer[]{6,2,8,0,4,7,9,null,null,3,5};
        TreeNode root = TreeUtils.generateTree(tree);
        System.out.println(new Solution23().helper(root, new TreeNode(2), new TreeNode(8)).val);
        System.out.println(new Solution23().helper(root, new TreeNode(2), new TreeNode(4)).val);
        System.out.println(new Solution23().helper(root, new TreeNode(2), new TreeNode(9)).val);
        System.out.println(new Solution23().helper(root, new TreeNode(3), new TreeNode(5)).val);
    }
}
