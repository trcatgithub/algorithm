package tree;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.*;

//给定一个二叉树，返回所有从根节点到叶子节点的路径。
//
//说明: 叶子节点是指没有子节点的节点。
//
//示例:
//
//输入:
//
//     1
//   /   \
//  2     3
//   \
//    5
//
//输出: ["1->2->5", "1->3"]
//
//解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/binary-tree-paths
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution11 {

    // 递归dfs
    // 1ms/39.8MB
    public List<String> binaryTreePaths(TreeNode root) {
        // 结果集
        List<String> res = new ArrayList<>();
        // 递归追加路径
        dfs(res, new StringBuilder(), root);
        return res;
    }

    private void dfs(List<String> res, StringBuilder str, TreeNode node) {
        // 结束条件
        if(node == null) {
            return;
        }
        // 追加"->"
        if(str.length() > 0) {
            str.append("->");
        }
        // 追加当前节点
        str.append(node.val);
        // 遍历到叶子节点时，将该路径加入到res
        if(node.left == null && node.right == null) {
            res.add(str.toString());
        }
        // 处理左叶子节点
        dfs(res, new StringBuilder(str.toString()), node.left);
        // 处理右叶子节点
        dfs(res, new StringBuilder(str.toString()), node.right);
    }

    public static void main(String[] args) {
        Integer[] tree = new Integer[]{1,2,3,null,5};
        System.out.println(new Solution11().binaryTreePaths(TreeUtils.generateTree(tree)));
        tree = new Integer[]{1};
        System.out.println(new Solution11().binaryTreePaths(TreeUtils.generateTree(tree)));
        tree = new Integer[]{1,2};
        System.out.println(new Solution11().binaryTreePaths(TreeUtils.generateTree(tree)));
        tree = new Integer[]{1,2,3,5,6};
        System.out.println(new Solution11().binaryTreePaths(TreeUtils.generateTree(tree)));


    }
}
