package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//给定一个二叉树，返回它的中序 遍历。
//
//示例:
//
//输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2]
//进阶: 递归算法很简单，你可以通过迭代算法完成吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution10 {

    // 利用Stack进行dfs
    // 1ms/37.8MB
    public List<Integer> inorderTraversal(TreeNode root) {
        // 处理边界
        if(root == null) {
            return new ArrayList<>();
        }
        // 结果集
        List<Integer> res = new ArrayList<>();
        // 用于dfs的栈
        Stack<TreeNode> memo = new Stack<>();
        // 根节点入栈
        memo.push(root);
        while(!memo.isEmpty()) {
            // 当前节点
            TreeNode current = null;
            // 中序遍历为 左中右，因此入栈顺序为 右中左
            while(!memo.isEmpty() && (current=memo.pop()) != null) {
                // 当前节点右节点入栈
                if(current.right != null) {
                    memo.push(current.right);
                }
                // 当前节点左右节点至少有一个子节点时，将当前节点入栈
                if(current.left != null || current.right != null) {
                    memo.push(current);
                }else { // 将"最左节点"(中序遍历 的当前节点)加入到结果集
                    res.add(current.val);
                }
                // 当前节点左节点入栈
                if(current.left != null) {
                    memo.push(current.left);
                }
                // 将处理过的节点 标记为 "最左节点"
                current.right = null;
                current.left = null;
            }
        }
        return res;
    }

//    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList<>();
//        dfs(res, root);
//        return res;
//    }
//
//    // 递归dfs
//    // 0ms/38.1MB
//    private void dfs(List<Integer> list, TreeNode node) {
//        // 退出条件
//        if(node == null) {
//            return;
//        }
//        // 按照左中右的顺序填充list
//        dfs(list, node.left);
//        list.add(node.val);
//        dfs(list, node.right);
//    }

    public static void main(String[] args) {
        Integer[] nodes = new Integer[]{1,null,2,3}; // [1, 3, 2]
        System.out.println(new Solution10().inorderTraversal(TreeUtils.generateTree(nodes)));
        nodes = new Integer[]{1,2,null,3,null,4,null,5}; // [5, 4, 3, 2, 1]
        System.out.println(new Solution10().inorderTraversal(TreeUtils.generateTree(nodes)));
        nodes = new Integer[]{1,2,3,4,null,null,5}; // [4, 2, 1, 3, 5]
        System.out.println(new Solution10().inorderTraversal(TreeUtils.generateTree(nodes)));
        nodes = new Integer[]{1,2,2,3,3,3,3,4,4,4,4,4,4,null,null,5,5};
//                    1
//            2               2
//        3       3       3       3
//      4   4   4   4   4   4
//    5   5
        System.out.println(new Solution10().inorderTraversal(TreeUtils.generateTree(nodes)));
    }
}
