package tree;

import java.util.ArrayList;
import java.util.List;

//给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
//
//说明: 叶子节点是指没有子节点的节点。
//
//示例:
//给定如下二叉树，以及目标和 sum = 22，
//
//         5
//        / \
//       4   8
//      /   / \
//     11  13  4
//    /  \    / \
//   7    2  5   1
//
//
//返回:
//
//[
//[5,4,11,2],
//[5,8,4,5]
//]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/path-sum-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution22 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), sum, root);
        return res;
    }

    // 1ms/39.4MB
    private void helper(List<List<Integer>> res, List<Integer> memo, int sum, TreeNode node) {
        // 退出条件
        if(node == null) {
            return;
        }
        // 追加路径
        memo.add(node.val);
        // 剩余节点和等于node.val时判断该节点是否是叶子节点
        if(sum == node.val && node.left == null && node.right == null) {
            res.add(new ArrayList<>(memo));
        }

        // 递归处理左右子树
        helper(res, memo, sum-node.val, node.left);
        helper(res, memo, sum-node.val, node.right);
        // 类似回溯处理，本次计算后，删除本次添加的节点
        memo.remove(memo.size()-1);
    }

    private static void printList(List<List<Integer>> list) {
        for(List<Integer> e : list) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
//         5
//        / \
//       4   8
//      /   / \
//     11  13  4
//    /  \    / \
//   7    2  5   1
        Integer[] tree = new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1};
        printList(new Solution22().pathSum(TreeUtils.generateTree(tree), 22));
        tree = new Integer[]{-2,null,-3};
        printList(new Solution22().pathSum(TreeUtils.generateTree(tree), -5));
    }
}
