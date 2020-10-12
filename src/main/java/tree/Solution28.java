package tree;

import java.util.Stack;

//给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
//
// 
//
//示例：
//
//输入：
//
//  1
//   \
//    3
//   /
//  2
//
//输出：
//1
//
//解释：
//最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
// 
//
//提示：
//
//树中至少有 2 个节点。
//本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution28 {

    // 基于栈的中序遍历
    // 3ms/37.9MB
    public int getMinimumDifference(TreeNode root) {
        int min = Integer.MAX_VALUE;
        Stack<TreeNode> memo = new Stack<>();
        memo.push(root);
        TreeNode current = root;
        int prev = -1;
        while(!memo.isEmpty()) {
            // 优先处理左节点
            if(current != null && current.left != null) {
                memo.push(current.left);
                // 切断当前节点 与 左节点的引用
                current.left = null;
                current = memo.peek();
            }else {
                // 处理当前节点
                current = memo.pop();
                if(prev == -1) {
                    prev = current.val;
                }else {
                    min = Math.min(min, Math.abs(current.val-prev));
                    prev = current.val;
                }
                // 处理右节点
                if(current.right != null) {
                    memo.push(current.right);
                    // 切断当前节点 与 右节点的引用
                    current.right = null;
                    current = memo.peek();
                }
            }
        }
        return min;
    }

//    private int min = Integer.MAX_VALUE;
//    private int pre = -1;
//
//    public int getMinimumDifference(TreeNode root) {
//        dfs(root);
//        return min;
//    }
//
//    // 基于递归的中序遍历
//    // 1ms/38.2MB
//    private void dfs(TreeNode node) {
//        if(node == null) {
//            return;
//        }
//        dfs(node.left);
//        if(pre == -1) {
//            pre = node.val;
//        }else {
//            min = Math.min(min, Math.abs(node.val-pre));
//            pre = node.val;
//        }
//        dfs(node.right);
//    }

    public static void main(String[] args) {
        Integer[] tree = new Integer[]{1,null,3,2};
        System.out.println(new Solution28().getMinimumDifference(TreeUtils.generateTree(tree)));
        tree = new Integer[]{236,104,701,null,227,null,911}; // 9
        //              236
        //
        //       104          701
        //
        //            227           911
        System.out.println(new Solution28().getMinimumDifference(TreeUtils.generateTree(tree)));
        tree = new Integer[]{1564,1434,3048,1,null,null,3184}; // 130
        //              1564
        //
        //        1434        3048
        //
        //    1                     3184
        System.out.println(new Solution28().getMinimumDifference(TreeUtils.generateTree(tree)));
    }
}
