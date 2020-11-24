package tree;

import java.util.LinkedList;
import java.util.Queue;

//给出一个完全二叉树，求出该树的节点个数。
//
//说明：
//
//完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，
//其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
//
//示例:
//
//输入:
//      1
//     / \
//    2   3
//   / \  /
//  4   5 6
//
//输出: 6
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution34 {

//    // 基于递归的dfs
//    // 0ms/41MB
//    public int countNodes(TreeNode root) {
//        if(root == null) {
//            return 0;
//        }
//        return countNodes(root.left)+countNodes(root.right)+1;
//    }

    // 基于队列的bfs
    // 5ms/41.1MB
    public int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int count = 0;
        Queue<TreeNode> level = new LinkedList<>();
        level.offer(root);
        while(!level.isEmpty()) {
            TreeNode current = level.poll();
            count++;
            if(current.left != null) {
                level.offer(current.left);
            }
            if(current.right != null) {
                level.offer(current.right);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.generateTree(new Integer[]{1,2,3,4,5,6});
        System.out.println(new Solution34().countNodes(root));
    }
}
