package tree;

import java.util.Stack;

//根据一棵树的中序遍历与后序遍历构造二叉树。
//
//注意:
//你可以假设树中没有重复的元素。
//
//例如，给出
//
//中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3]
//返回如下的二叉树：
//
//      3
//     / \
//    9  20
//      /  \
//     15   7
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution21 {

    // 4ms/39.3MB
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(inorder, postorder, 0, 0, inorder.length-1, postorder.length-1);
    }

    // 递归生成树
    private TreeNode helper(int[] inorder, int[] postorder, int inFrom, int postFrom, int inTo, int postTo) {
        // 递归结束条件
        if(inFrom > inTo || postFrom > postTo) {
            return null;
        }
        // 后序遍历的最后一个元素即是parent节点
        TreeNode node = new TreeNode(postorder[postTo]);
        for(int i=inFrom; i<=inTo; i++) {
            // 在中序遍历中寻找parent节点
            if(inorder[i] == postorder[postTo]) {
                // 用中序遍历parent节点左侧数据生成左子树
                node.left = helper(inorder, postorder, inFrom, postFrom, i-1, postFrom+i-inFrom-1);
                // 用中序遍历parent节点右侧数据生成右子树
                node.right = helper(inorder, postorder, i+1, postFrom+i-inFrom, inTo, postTo-1);
                break;
            }
        }
        return node;
    }

    public static void main(String[] args) {
        int[] inorder = new int[]{9,3,15,20,7};
        int[] postorder = new int[]{9,15,7,20,3};
        TreeUtils.printTree(new Solution21().buildTree(inorder, postorder));
        //               1
        //           2       3
        //         4   5   6   7
        //        8 9
//        inorder = new int[]{8,4,9,2,5,1,6,3,7};
//        postorder = new int[]{8,9,4,5,2,6,7,3,1};
//        TreeUtils.printTree(new Solution21().buildTree(inorder, postorder));
    }
}
