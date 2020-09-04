package tree;

import java.util.Stack;

//给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original 中的目标节点 target。
//
//其中，克隆树 cloned 是原始树 original 的一个 副本 。
//
//请找出在树 cloned 中，与 target 相同 的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身）。
//
// 
//
//注意：
//
//你 不能 对两棵二叉树，以及 target 节点进行更改。
//只能 返回对克隆树 cloned 中已有的节点的引用。
//进阶：如果树中允许出现值相同的节点，你将如何解答？
//
// 
//
//示例 1:
//
//
//
//输入: tree = [7,4,3,null,null,6,19], target = 3
//输出: 3
//解释: 上图画出了树 original 和 cloned。target 节点在树 original 中，用绿色标记。答案是树 cloned 中的黄颜色的节点（其他示例类似）。
//示例 2:
//
//
//
//输入: tree = [7], target =  7
//输出: 7
//示例 3:
//
//
//
//输入: tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
//输出: 4
//示例 4:
//
//
//
//输入: tree = [1,2,3,4,5,6,7,8,9,10], target = 5
//输出: 5
//示例 5:
//
//
//
//输入: tree = [1,2,null,3], target = 2
//输出: 2
// 
//
//提示：
//
//树中节点的数量范围为 [1, 10^4] 。
//同一棵树中，没有值相同的节点。
//target 节点是树 original 中的一个节点，并且不会是 null 。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution12 {

    // 基于栈的dfs
    // 17ms/47.9MB
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        // 处理边界情况
        if(original == null || cloned == null || target == null) {
            return null;
        }
        // 定义2个stack，用于dfs
        Stack<TreeNode> memoOri = new Stack<>();
        Stack<TreeNode> memoClo = new Stack<>();
        memoOri.push(original);
        memoClo.push(cloned);
        while(!memoOri.isEmpty()) {
            TreeNode ori = memoOri.pop();
            TreeNode clo = memoClo.pop();
            // 找到与target对应的节点
            if(ori == target) {
                return clo;
            }
            // 左叶子节点入栈
            if(ori.left != null) {
                memoOri.push(ori.left);
                memoClo.push(clo.left);
            }
            // 右叶子节点入栈
            if(ori.right != null) {
                memoOri.push(ori.right);
                memoClo.push(clo.right);
            }
        }
        return null;
    }

//    // 递归DFS
//    // 2ms/47.4MB
//    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
//        return dfs(original, cloned, target);
//    }
//
//    private TreeNode dfs(TreeNode original, TreeNode cloned, TreeNode target) {
//        // 递归结束条件
//        if(original == null) {
//            return null;
//        }
//        // 找到目标节点
//        if(original == target) {
//            return cloned;
//        }
//        // 递归寻找左右子节点
//        TreeNode left = dfs(original.left, cloned.left, target);
//        TreeNode right = dfs(original.right, cloned.right, target);
//        // 返回不为空的节点(不为空的节点即cloned中与target对应的节点)
//        return left == null ? right : left;
//    }
}
