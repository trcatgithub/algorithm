package tree;

// 求二叉树中任意两个节点之间的距离
public class Solution31 {
    // 1,先求出node1与node2的最近公共父节点
    // 2,从该父节点向下，依次求node1与node2的深度
    // 3,node1与node2的深度之和即是node1与node2的距离
    public int lengthOfTwoNode(TreeNode root, TreeNode node1, TreeNode node2) {
        TreeNode father = findFatherNode(root, node1, node2);
        return getDepth(father, node1, 0)+getDepth(father, node2, 0);
    }

    // 找到两个节点的最近公共祖先
    // 从根节点向下递归寻找node1与node2
    // 有三种情况：
    // 1,找到的是node1
    // 2,找到的是node2
    // 3,遍历到叶子节点，未找到node1与node2，返回null
    //
    // 当当前节点node的左右子树遍历结果都不为空时，说明左右子树分别找到了node1与node2，则当前节点即是最近公共父节点
    private TreeNode findFatherNode(TreeNode node, TreeNode node1, TreeNode node2) {
        if(node == null || node == node1 || node == node2) {
            return node;
        }
        TreeNode left = findFatherNode(node.left, node1, node2);
        TreeNode right = findFatherNode(node.right, node1, node2);
        if(left == null && right == null) {
            return null;
        }else if(left == null) {
            return right;
        }else if(right == null) {
            return left;
        }else {
            return node;
        }
    }

    // 计算node节点的深度
    private int getDepth(TreeNode root, TreeNode node, int depth) {
        if(root == null) {
            return 0;
        }else if(root == node) {
            return depth;
        }
        return Math.max(getDepth(root.left, node, depth+1), getDepth(root.right, node, depth+1));
    }

    public static void main(String[] args) {
        //                            1
        //                          /   \
        //                         2     3
        //                        / \   / \
        //                       4   5 6   7
        //                      / \
        //                     8   9
        Integer[] tree = new Integer[]{1,2,3,4,5,6,7,8,9};
        TreeNode root = TreeUtils.generateTree(tree);
        System.out.println(new Solution31().getDepth(root, new TreeNode(1), 0));
        System.out.println(new Solution31().getDepth(root, new TreeNode(2), 0));
        System.out.println(new Solution31().getDepth(root, new TreeNode(3), 0));
        System.out.println(new Solution31().getDepth(root, new TreeNode(4), 0));
        System.out.println(new Solution31().getDepth(root, new TreeNode(5), 0));
        System.out.println(new Solution31().getDepth(root, new TreeNode(6), 0));
        System.out.println(new Solution31().getDepth(root, new TreeNode(7), 0));
        System.out.println(new Solution31().getDepth(root, new TreeNode(8), 0));
        System.out.println(new Solution31().getDepth(root, new TreeNode(9), 0));
    }
}
