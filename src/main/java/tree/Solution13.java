package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
//
//例如：
//给定二叉树 [3,9,20,null,null,15,7],
//
//      3
//     / \
//    9  20
//   /  \
//  15   7
//
//
//返回其自底向上的层次遍历为：
//
//[
//[15,7],
//[9,20],
//[3]
//]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution13 {
    // 利用Queue进行bfs
    // 1ms/39.9MB
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        // 保存每层节点
        Queue<TreeNode> queue = new LinkedList<>();
        // 结果集
        List<List<Integer>> res = new ArrayList<>();
        // 根节点入队
        queue.offer(root);
        int len = 1;
        // 开始遍历
        while(!queue.isEmpty()) {
            // 保存当前层节点值
            List<Integer> temp = new ArrayList<>();
            // 记录下一层节点数
            int count = 0;
            // 根据每层节点数，从queue中获取节点
            for(int i=0; i<len; i++) {
                // 获取当前节点，并将节点值加入到temp中
                TreeNode current = queue.poll();
                temp.add(current.val);
                // 将当前节点左右节点入队，并累加下一层节点值
                if(current.left != null) {
                    queue.offer(current.left);
                    count++;
                }
                if(current.right != null) {
                    queue.offer(current.right);
                    count++;
                }
            }
            len = count;
            res.add(0, temp);
        }
        return res;
    }

    private void printList(List<List<Integer>> list) {
        System.out.println(list);
    }

    public static void main(String[] args) {
        Integer[] tree = new Integer[]{3,9,20,null,null,15,7};
        Solution13 s = new Solution13();
        s.printList(s.levelOrderBottom(TreeUtils.generateTree(tree)));
    }
}
