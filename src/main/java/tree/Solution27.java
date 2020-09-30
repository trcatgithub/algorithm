package tree;

//给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
//
//注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
//
// 
//
//例如, 
//
//给定二叉搜索树:
//
//      4
//     / \
//    2   7
//   / \
//  1   3
//
//和 插入的值: 5
//你可以返回这个二叉搜索树:
//
//         4
//       /   \
//      2     7
//     / \   /
//    1   3 5
//或者这个树也是有效的:
//
//           5
//         /   \
//        2     7
//       / \
//      1   3
//           \
//            4
// 
//
//提示：
//
//给定的树上的节点数介于 0 和 10^4 之间
//每个节点都有一个唯一整数值，取值范围从 0 到 10^8
//-10^8 <= val <= 10^8
//新值和原始二叉搜索树中的任意节点值都不同
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution27 {

    // 基于节点指针进行查找
    // 0ms/39.3MB
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) {
            return new TreeNode(val);
        }
        // 当前节点指针
        TreeNode node = root;
        // 当前节点的父节点
        TreeNode parent = null;
        // 判断插入到parent节点的左侧还是右侧
        // false: right, true: left
        boolean leftOrRight = false;
        // 遍历节点，维护引用以及插入位置
        while(node != null) {
            if(val > node.val) {
                parent = node;
                node = node.right;
                leftOrRight = false;
            }else {
                parent = node;
                node = node.left;
                leftOrRight = true;
            }
        }
        // 根据插入位置创建节点
        if(leftOrRight) {
            parent.left = new TreeNode(val);
        }else {
            parent.right = new TreeNode(val);
        }
        return root;
    }

//    // 基于递归的dfs
//    // 0ms/39.3MB
//    public TreeNode insertIntoBST(TreeNode root, int val) {
//        if(root == null) {
//            return new TreeNode(val);
//        }
//        // 根据节点值进行dfs
//        if(val > root.val) {
//            root.right = insertIntoBST(root.right, val);
//        }else {
//            root.left = insertIntoBST(root.left, val);
//        }
//        return root;
//    }

    public static void main(String[] args) {
        Integer[] tree = new Integer[]{4,2,7,1,3}; // 5
        TreeNode root = TreeUtils.generateTree(tree);
        TreeUtils.printTree(new Solution27().insertIntoBST(root, 5));
        tree = new Integer[]{4,2,7,1,3,5}; // 5
        root = TreeUtils.generateTree(tree);
        TreeUtils.printTree(new Solution27().insertIntoBST(root, 6));
    }
}
