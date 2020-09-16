package tree;

import java.util.LinkedList;
import java.util.Queue;

//翻转一棵二叉树。
//
//示例：
//
//输入：
//
//         4
//       /   \
//      2     7
//     / \   / \
//    1   3 6   9
//输出：
//
//         4
//       /   \
//      7     2
//     / \   / \
//    9   6 3   1
//备注:
//这个问题是受到 Max Howell 的 原问题 启发的 ：
//
//谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/invert-binary-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution15 {

    // bfs
    // 0ms/36.7MB
    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode current = queue.poll();
            TreeNode left = current.left;
            TreeNode right = current.right;
            current.left = right;
            current.right = left;
            if(left != null) {
                queue.offer(left);
            }
            if(right != null) {
                queue.offer(right);
            }
        }
        return root;
    }

//    public TreeNode invertTree(TreeNode root) {
//        dfs(root);
//        return root;
//    }
//
//    // dfs
//    // 0ms/36.2MB
//    private void dfs(TreeNode node) {
//        if(node == null) {
//            return;
//        }
//        TreeNode left = node.left;
//        TreeNode right = node.right;
//        node.left = right;
//        node.right = left;
//        dfs(node.left);
//        dfs(node.right);
//    }

    public static void main(String[] args) {
        Integer[] tree = new Integer[]{4,2,7,1,3,6,9};
        TreeUtils.printTree(TreeUtils.generateTree(tree));
        TreeUtils.printTree(new Solution15().invertTree(TreeUtils.generateTree(tree)));
    }
}
