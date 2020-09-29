package tree;

import java.util.*;

//给定一个二叉树，返回它的 后序 遍历。
//
//示例:
//
//输入: [1,null,2,3]
// 1
//  \
//   2
//  /
// 3
//
//输出: [3,2,1]
//进阶: 递归算法很简单，你可以通过迭代算法完成吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution26 {

    // 基于栈的dfs（后序遍历）
    // 1ms/37.1MB
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> tree = new Stack<>();
        tree.push(root);
        TreeNode node = root;
        //                        1
        //                   2         3
        //                4     5   6     7
        //              8   9
        while(!tree.isEmpty()) {
            // 优先处理左节点
            if(node != null && node.left != null) {
                tree.push(node.left);
                // 切断当前节点 与 左叶子节点的引用
                node.left = null;
                node = tree.peek();
            }else {
                // 不存在左节点时，先处理右节点
                if(node != null && node.right != null) {
                    tree.push(node.right);
                    // 切断当前节点 与 右叶子节点的引用
                    node.right = null;
                    node = tree.peek();
                }else {
                    // 处理当前节点
                    node = tree.peek();
                    // 当当前节点不存在左右叶子节点时
                    if(node.left == null && node.right == null) {
                        // 追加当前节点
                        res.add(node.val);
                        tree.pop();
                    }
                }
            }
        }
        return res;
    }

//    public List<Integer> postorderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList<>();
//        dfs(res, root);
//        return res;
//    }
//
//    // 基于递归的dfs
//    // 0ms/37.4MB
//    private void dfs(List<Integer> res, TreeNode node) {
//        if(node == null) {
//            return;
//        }
//        // 先处理左叶子节点
//        dfs(res, node.left);
//        // 再处理右叶子节点
//        dfs(res, node.right);
//        // 处理当前节点
//        res.add(node.val);
//    }

    public static void main(String[] args) {
        Integer[] tree = new Integer[]{1, null, 2, 3};
        TreeNode root = TreeUtils.generateTree(tree);
        System.out.println(new Solution26().postorderTraversal(root)); // [3, 2, 1]
        tree = new Integer[]{1,2,3,4,5,6,7,8,9};
        //                        1
        //                   2         3
        //                4     5   6     7
        //              8   9
        root = TreeUtils.generateTree(tree);
        System.out.println(new Solution26().postorderTraversal(root)); // [8, 9, 4, 5, 2, 6, 7, 3, 1]
    }
}
