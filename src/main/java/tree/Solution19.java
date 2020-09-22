package tree;

import sun.tools.jconsole.inspector.XNodeInfo;

//给定一个二叉树，我们在树的节点上安装摄像头。
//
//节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
//
//计算监控树的所有节点所需的最小摄像头数量。
//
// 
//
//示例 1：
//             0
//           0
//         0   0
//输入：[0,0,null,0,0]
//输出：1
//解释：如图所示，一台摄像头足以监控所有节点。
//示例 2：
//             0
//          0
//       0
//    0
//       0
//输入：[0,0,null,0,null,0,null,null,0]
//输出：2
//解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
//
//提示：
//
//给定树的节点数的范围是 [1, 1000]。
//每个节点的值都是 0。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/binary-tree-cameras
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution19 {
    private int count = 0;
    // 1ms/38.5MB
    public int minCameraCover(TreeNode root) {
        helper(root);
        // 若根节点未被监控，在根节点加装一个摄像头
        if(root != null && root.val == 0) {
            count++;
        }
        return count;
    }

    // 自底向上处理该树
    // 0: 未被监控
    // 1: 安装了摄像头
    // 2: 被摄像头监控
    private void helper(TreeNode node) {
        // 递归退出条件
        if(node == null) {
            return;
        }
        // 自底向上处理该树，所以需要先递归，再判断
        helper(node.left);
        helper(node.right);
        // 当前节点的左右子节点存在未被监控的节点
        if((node.left != null && node.left.val == 0)
                || (node.right != null && node.right.val == 0)) {
            // 在当前节点安装摄像头
            node.val = 1;
            count++;
        }else if((node.left != null && node.left.val == 1)
                || (node.right != null && node.right.val == 1)) {
            // 左右子节点中有摄像头时，将当前节点更新为被监控
            node.val = 2;
        }
    }
}