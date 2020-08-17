package tree;

//给定一个二叉树，判断它是否是高度平衡的二叉树。
//
//本题中，一棵高度平衡二叉树定义为：
//
//一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
//
//示例 1:
//
//给定二叉树 [3,9,20,null,null,15,7]
//
//     3
//    / \
//   9  20
//     /  \
//    15   7
//返回 true 。
//
//示例 2:
//
//给定二叉树 [1,2,2,3,3,null,null,4,4]
//
//       1
//      / \
//     2   2
//    / \
//   3   3
//  / \
// 4   4
//返回 false 。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/balanced-binary-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution8 {
    // 1ms/40.2MB
    public boolean isBalanced(TreeNode root) {
        // 退出条件
        if(root == null) {
            return true;
        }
        // 递归判断每一个节点的左右子树是否平衡
        return isBalanced(root.left) && isBalanced(root.right) && (Math.abs(dfs(root.left)-dfs(root.right)) > 1 ? false : true);
    }

    // 计算节点的最大深度
    private int dfs(TreeNode node) {
        // 退出条件
        if(node == null) {
            return 0;
        }
        // 左节点深度
        int left = dfs(node.left)+1;
        // 右节点深度
        int right = dfs(node.right)+1;
        // 返回当前节点的最大深度
        return Math.max(left, right);
    }

    public static void main(String[] args) {
        Integer[] tree = new Integer[]{3,9,20,null,null,15,7}; // true
        System.out.println(new Solution8().isBalanced(TreeUtils.generateTree(tree)));
        tree = new Integer[]{1,2,2,3,3,null,null,4,4}; // false
        System.out.println(new Solution8().isBalanced(TreeUtils.generateTree(tree)));
        tree = new Integer[]{1,2,2,3,3,3,3,4,4,4,4,4,4,null,null,5,5}; // true
        System.out.println(new Solution8().isBalanced(TreeUtils.generateTree(tree)));
        tree = new Integer[]{1,2,2,3,null,null,3,4,null,null,4}; // false
        System.out.println(new Solution8().isBalanced(TreeUtils.generateTree(tree)));
    }
}
