package tree;

import java.util.ArrayList;
import java.util.List;



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// https://leetcode-cn.com/problems/path-sum/submissions/
//    给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
//
//    说明: 叶子节点是指没有子节点的节点。
//
//    示例: 
//    给定如下二叉树，以及目标和 sum = 22，
//
//               5
//            /     \
//           4       8
//          /       / \
//         11      13  4
//        /  \          \
//       7    2           1
//    返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
// bfs(2 ms/39.8MB)
// dfs(0 ms/39.7MB)
public class Solution1 {

    public boolean hasPathSum(TreeNode root, int sum) {
//            return bfs(root, sum);
        return root == null ? false : dfs(root, sum);
    }

    // bfs(2 ms/39.8MB)
    private boolean bfs(TreeNode node, int sum) {
        // 用于遍历每层元素
        List<TreeNode> nodes = new ArrayList<>();
        // 添加root节点
        nodes.add(node);
        // 开始遍历
        while(true) {
            // 保存下一层元素
            List<TreeNode> nextLevel = new ArrayList<>();
            // 遍历当前层元素
            for(TreeNode n : nodes) {
                // 当前节点不为空时
                if(n != null) {
                    // 当前节点的左节点不为空时
                    if(n.left != null) {
                        // 将当前节点值 加算 到左节点
                        n.left.val = n.left.val+n.val;
                        // 保存左节点
                        nextLevel.add(n.left);
                    }
                    // 当前节点的右节点不为空时
                    if(n.right != null) {
                        // 将当前节点值 加算 到右节点
                        n.right.val = n.right.val+n.val;
                        // 保存右节点
                        nextLevel.add(n.right);
                    }
                    // 当前节点为叶子节点 且 节点值=sum
                    if(n.left == null && n.right == null && n.val == sum) {
                        return true;
                    }
                }
            }
            // 下一层还有不为null的节点时
            if(nextLevel.size() > 0) {
                nodes = nextLevel;
            }else {
                break;
            }
        }
        // 无法找到和为sum的路径时
        return false;
    }

    // dfs(0 ms/39.7MB)
    private boolean dfs(TreeNode node, int sum) {
        // sum减去当前节点值
        sum-= node.val;
        // sum为0 且 当前节点为叶子节点时，返回true
        if(sum == 0 && node.left == null && node.right == null) {
            return true;
        }else {
            // 分别递归当前节点的左右子树
            // 当前节点的左右节点不能为null
            // 左右子树只要有一条路径满足条件即可
            return (node.left != null && dfs(node.left, sum)) || (node.right != null && dfs(node.right, sum));
        }
    }

    public static void main(String[] args) {
        Integer[] tree = new Integer[]{1,2};
        int sum = 1;
        tree = new Integer[0];
        sum = 0;
        tree = new Integer[]{1,2,null,3,null,4,null,5};
        sum = 6;
        tree = new Integer[]{-2,null,-3};
        sum = -5;
        tree = new Integer[]{1};
        sum = 1;
        tree = new Integer[]{5,4,8,11,null,13,4,7,2,null,null,null,1};
        sum = 22;
        Solution1 s1 = new Solution1();
        TreeNode root = TreeUtils.generateTree(tree);
        System.out.println(s1.hasPathSum(root, sum));
    }


}
