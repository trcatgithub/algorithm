package tree;

//给定一个二叉树，找出其最小深度。
//
//最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//
//说明: 叶子节点是指没有子节点的节点。
//
//示例:
//
//给定二叉树 [3,9,20,null,null,15,7],
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
//返回它的最小深度  2.
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution9 {

    public int minDepth(TreeNode root) {
        return dfs(root);
    }


    // dfs获取最小深度
    // 0ms/39.7MB
    private int dfs(TreeNode node) {
        // 递归到叶子节点时，返回0
        if(node == null) {
            return 0;
        }
        // 左节点为空时，递归右节点
        if(node.left == null) {
            return dfs(node.right)+1;
        }else if(node.right == null) { // 右节点为空时，递归左节点
            return dfs(node.left)+1;
        }else {
            // 左右节点都不为空时，递归左右节点，取较小的值
            return Math.min(dfs(node.left), dfs(node.right))+1;
        }
    }

    public static void main(String[] args) {
        Integer[] tree = new Integer[]{3,9,20,null,null,15,7}; // 2
        System.out.println(new Solution9().minDepth(TreeUtils.generateTree(tree)));
        tree = new Integer[]{1,2}; // 2
        //     1
        //   2
        System.out.println(new Solution9().minDepth(TreeUtils.generateTree(tree)));
        tree = new Integer[]{0}; // 1
        System.out.println(new Solution9().minDepth(TreeUtils.generateTree(tree)));
        tree = new Integer[]{1,2,3,4,null,null,5}; // 3
        //      1
        //    2   3
        //   4 n n 5
        System.out.println(new Solution9().minDepth(TreeUtils.generateTree(tree)));
        tree = new Integer[]{1,2,null,3,null,4,null,5}; // 5
        //             1
        //           2   n
        //         3   n
        //       4   n
        //     5
        System.out.println(new Solution9().minDepth(TreeUtils.generateTree(tree)));

    }
}
