package tree;

//给定一个二叉树，原地将它展开为一个单链表。
//
//
//
//例如，给定二叉树
//
//    1
//   / \
//  2   5
// / \   \
//3   4   6
//
//将其展开为：
//
//   1
//    \
//     2
//      \
//       3
//        \
//         4
//          \
//           5
//            \
//             6
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class Solution5 {

    public void flatten(TreeNode root) {
        dfs(root);
    }

    // 前序遍历
    // 0ms/39.4MB
    private TreeNode dfs(TreeNode node) {
        // 迭代退出条件
        if(node == null) {
            return null;
        }
        // 保存当前节点左右引用
        TreeNode left = node.left;
        TreeNode right = node.right;
        // 获得当前节点展开后的左右节点
        TreeNode dfsLeft = dfs(left);
        TreeNode dfsRight = dfs(right);
        // 展开后左节点不为空时
        if(dfsLeft != null) {
            // 将当前节点左节点清空
            node.left = null;
            // 将展开后左节点放到当前节点右节点位置
            node.right = dfsLeft;
            // 将展开后右节点放到展开后左节点的右叶子节点位置
            TreeNode leafRight = dfsLeft;
            while(leafRight != null) {
                if(leafRight.right == null) {
                    leafRight.right = dfsRight;
                    break;
                }
                leafRight = leafRight.right;
            }
        }else { // 展开后左节点为空时
            // 直接将展开后右节点放到当前节点右节点位置
            node.right = dfsRight;
        }
        // 返回当前节点
        return node;
    }
}
