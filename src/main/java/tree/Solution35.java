package tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
//
//例如：
//给定二叉树 [3,9,20,null,null,15,7],
//
//      3
//     / \
//    9  20
//      /  \
//     15   7
//返回锯齿形层序遍历如下：
//
//[
//[3],
//[20,9],
//[15,7]
//]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution35 {

    // 利用双端队列
    // 1ms/38。6MB
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        boolean flag = true;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offerLast(root);
        while(!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> level = new ArrayList<>();
            for(int i=0; i<size; i++) {
                TreeNode current = null;
                if(flag) {
                    current = deque.pollLast();
                    if(current.left != null) {
                        deque.offerFirst(current.left);
                    }
                    if(current.right != null) {
                        deque.offerFirst(current.right);
                    }
                }else {
                    current = deque.pollFirst();
                    if(current.right != null) {
                        deque.offerLast(current.right);
                    }
                    if(current.left != null) {
                        deque.offerLast(current.left);
                    }
                }
                level.add(current.val);
            }
            flag = (flag ? false : true);
            res.add(level);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.generateTree(new Integer[]{3,9,20,null,null,15,7});
        System.out.println(new Solution35().zigzagLevelOrder(root).toString());
        root = TreeUtils.generateTree(new Integer[]{3,9,20,5,6,15,7});
        System.out.println(new Solution35().zigzagLevelOrder(root).toString());
    }
}
