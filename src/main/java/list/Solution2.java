package list;

import tree.TreeNode;
import tree.TreeUtils;

import java.util.ArrayList;
import java.util.List;

//给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
//
//本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
//
//示例:
//
//给定的有序链表： [-10, -3, 0, 5, 9],
//
//一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
//
//      0
//     / \
//   -3   9
//   /   /
// -10  5
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution2 {
    // 递归dfs
    // 2ms/41MB
    public TreeNode sortedListToBST(ListNode head) {
        // 处理边界问题
        if(head == null) {
            return null;
        }
        // 节点值
        List<Integer> vals = new ArrayList<>();
        // 遍历ListNode保存节点值
        while(head != null) {
            vals.add(head.val);
            head = head.next;
        }
        // dfs生成树
        return dfs(vals, 0, vals.size()-1);
    }

    // dfs
    private TreeNode dfs(List<Integer> vals, int start, int end) {
        // start-end范围只有一个节点时
        if(start == end) {
            return new TreeNode(vals.get(start));
        }else if(start + 1 == end) { // start-end范围内有两个节点时
            TreeNode node = new TreeNode(vals.get(start));
            node.right = new TreeNode(vals.get(end));
            return node;
        }
        // start-end范围内有3个及以上的节点时
        int mid = (start+end)/2;
        TreeNode node = new TreeNode(vals.get(mid));
        node.left = dfs(vals, start, mid-1);
        node.right = dfs(vals, mid+1, end);
        return node;
    }

    public static void main(String[] args) {
        Integer[] vals = new Integer[]{-10, -3, 0, 5, 9};
        TreeUtils.printTree(new Solution2().sortedListToBST(ListUtils.generateList(vals)));

//[0,-3,9,-10,null,5]
//        [0, -10, 5, null, -3, null, 9]
//           0
//     -10       5
//         -3       9
    }
}
