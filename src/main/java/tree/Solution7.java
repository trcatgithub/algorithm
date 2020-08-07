package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//给定两个二叉树，编写一个函数来检验它们是否相同。
//
//如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
//
//示例 1:
//
//输入:       1         1
//           / \       / \
//          2   3     2   3
//
//[1,2,3],   [1,2,3]
//
//输出: true
//示例 2:
//
//输入:      1          1
//          /           \
//         2             2
//
//[1,2],     [1,null,2]
//
//输出: false
//示例 3:
//
//输入:       1         1
//          / \       / \
//         2   1     1   2
//
//[1,2,1],   [1,1,2]
//
//输出: false
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/same-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// 递归dfs 0ms/37.6MB
// 非递归dfs 1ms/37.5MB
// bfs     0ms/37.5MB
public class Solution7 {

    // 利用queue进行bfs（逻辑与非递归dfs类似，只是将Stack换成了Queue）
    // 0ms/37.5MB
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        }else if((p == null && q != null) || (p != null && q == null)) {
            return false;
        }
        Queue<TreeNode> pQueue = new LinkedList<>();
        pQueue.offer(p);
        Queue<TreeNode> qQueue = new LinkedList<>();
        qQueue.offer(q);

        while(!pQueue.isEmpty() && !qQueue.isEmpty()) {
            TreeNode tempP = pQueue.poll();
            TreeNode tempQ = qQueue.poll();
            if((tempP == null && tempQ != null) || (tempP != null && tempQ == null)) {
                return false;
            }else if(tempP != null && tempQ != null) {
                if(tempP.val != tempQ.val) {
                    return false;
                }
                pQueue.offer(tempP.left);
                pQueue.offer(tempP.right);
                qQueue.offer(tempQ.left);
                qQueue.offer(tempQ.right);
            }
        }
        return true;
    }

//    // 非递归dfs
//    // 1ms/37.5MB
//    public boolean isSameTree(TreeNode p, TreeNode q) {
//        // 边界条件
//        if(p == null && q == null) {
//            return true;
//        }else if((p == null && q != null) || (p != null && q == null)) {
//            return false;
//        }
//        // 利用stack进行dfs
//        Stack<TreeNode> sp = new Stack<>();
//        sp.push(p);
//        Stack<TreeNode> sq = new Stack<>();
//        sq.push(q);
//        while(!sp.isEmpty() && !sq.isEmpty()) {
//            TreeNode tp = sp.pop();
//            TreeNode tq = sq.pop();
//            // 只有一个节点为null时，返回false
//            if((tp == null && tq != null) || (tp != null && tq == null)) {
//                return false;
//            }else if(tp != null && tq != null) {
//                // 两个节点val不相等时，返回false
//                if(tp.val != tq.val) {
//                    return false;
//                }
//                // 左右节点入栈
//                sp.push(tp.left);
//                sp.push(tp.right);
//                sq.push(tq.left);
//                sq.push(tq.right);
//            }
//        }
//        return true;
//    }

//    public boolean isSameTree(TreeNode p, TreeNode q) {
//        return dfs(p, q);
//    }
//
//    // 0ms/37.6MB
//    // 递归dfs
//    private boolean dfs(TreeNode p, TreeNode q) {
//        // 只有一个节点为null时，返回false
//        if((p == null && q != null) || (p != null && q == null)) {
//            return false;
//        }else if(p == null && q == null) {// 两个节点都为null时，返回true
//            return true;
//        }
//        // 返回 当前节点 && 当前节点的左右节点的递归结果
//        return p.val==q.val && dfs(p.left, q.left) && dfs(p.right, q.right);
//    }

    public static void main(String[] args) {
        Integer[] ap = new Integer[]{1,2,3};
        Integer[] aq = new Integer[]{1,2,3};
        System.out.println(new Solution7().isSameTree(TreeUtils.generateTree(ap), TreeUtils.generateTree(aq)));
        ap = new Integer[]{1,2};
        aq = new Integer[]{1,null,2};
        System.out.println(new Solution7().isSameTree(TreeUtils.generateTree(ap), TreeUtils.generateTree(aq)));
        ap = new Integer[]{1,2,1};
        aq = new Integer[]{1,1,2};
        System.out.println(new Solution7().isSameTree(TreeUtils.generateTree(ap), TreeUtils.generateTree(aq)));
    }
}
