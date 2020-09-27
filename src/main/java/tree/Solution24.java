package tree;

import java.util.*;

//给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
//
//假定 BST 有如下定义：
//
//结点左子树中所含结点的值小于等于当前结点的值
//结点右子树中所含结点的值大于等于当前结点的值
//左子树和右子树都是二叉搜索树
//例如：
//给定 BST [1,null,2,2],
//
// 1
//  \
//   2
//  /
// 2
//返回[2].
//
//提示：如果众数超过1个，不需考虑输出顺序
//
//进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution24 {

    private int maxCount = 0;
    private int val = 0;
    private int count = 0;
    public int[] findMode(TreeNode root) {
        List<Integer> temp = new ArrayList<>();
        dfs(temp, root);
        int[] res = new int[temp.size()];
        for(int i=0; i<res.length; i++) {
            res[i] = temp.get(i);
        }
        return res;
    }

    // 基于递归的dfs
    // 1ms/39.5MB
    private void dfs(List<Integer> tempRes, TreeNode node) {
        if(node == null) {
            return;
        }
        dfs(tempRes, node.left);
        if(maxCount == 0) {
            val = node.val;
            count = 1;
            maxCount = 1;
            tempRes.add(node.val);
        }else if(node.val == val) {
            count++;
            if(count > maxCount) {
                maxCount = count;
                tempRes.clear();
                tempRes.add(node.val);
            }else if(count == maxCount) {
                tempRes.add(node.val);
            }
        }else {
            val = node.val;
            count = 1;
            if(count > maxCount) {
                maxCount = count;
                tempRes.clear();
                tempRes.add(node.val);
            }else if(count == maxCount) {
                tempRes.add(node.val);
            }
        }
        dfs(tempRes, node.right);
    }


//    // 基于栈的dfs，在dfs过程中维护 众数 list
//    // 6m39.4MB
//    public int[] findMode(TreeNode root) {
//        if(root == null) {
//            return new int[0];
//        }
//        List<Integer> tempRes = new ArrayList<>();
//        Stack<TreeNode> tree = new Stack<>();
//        tree.push(root);
//        TreeNode node = root;
//        int val = 0, count = 0, maxCount = 0;
//        // 基于栈的中序遍历
//        while(!tree.isEmpty()) {
//            // 处理左节点
//            if(node != null && node.left != null) {
//                tree.push(node.left);
//                node = node.left;
//            }else {
//                // 处理当前节点
//                node = tree.pop();
//                if(maxCount == 0) {
//                    val = node.val;
//                    count = 1;
//                    maxCount = 1;
//                    tempRes.add(node.val);
//                }else if(node.val == val) {
//                    count++;
//                    if(count > maxCount) {
//                        maxCount = count;
//                        tempRes.clear();
//                        tempRes.add(node.val);
//                    }else if(count == maxCount) {
//                        tempRes.add(node.val);
//                    }
//                }else {
//                    val = node.val;
//                    count = 1;
//                    if(count > maxCount) {
//                        maxCount = count;
//                        tempRes.clear();
//                        tempRes.add(node.val);
//                    }else if(count == maxCount) {
//                        tempRes.add(node.val);
//                    }
//                }
//                // 处理右节点
//                if(node != null && node.right != null) {
//                    tree.push(node.right);
//                    node = node.right;
//                }else {
//                    node = null;
//                }
//            }
//        }
//
//        // 链表转数组
//        int[] res = new int[tempRes.size()];
//        for(int i=0; i<res.length; i++) {
//            res[i] = tempRes.get(i);
//        }
//        return res;
//    }

//    // 8ms/40.4MB
//    public int[] findMode(TreeNode root) {
//        // 处理边界问题
//        if(root == null) {
//            return new int[0];
//        }
//        // 保存中序遍历结果
//        List<Integer> midVals = new ArrayList<>();
//        // 基于栈的中序遍历
//        Stack<TreeNode> tree = new Stack<>();
//        tree.push(root);
//        TreeNode node = root;
//        while(!tree.isEmpty()) {
//            // 只要节点存在左节点，优先处理左节点
//            if(node != null && node.left != null) {
//                tree.push(node.left);
//                node = node.left;
//            }else { // 节点为null 或 节点不存在左节点时
//                // 先处理当前节点(左->中->右  的"中"节点)
//                node = tree.pop();
//                midVals.add(node.val);
//                // 处理右节点
//                if(node != null && node.right != null) {
//                    tree.push(node.right);
//                    node = node.right;
//                }else { // 将当前节点指针标记为null
//                    node = null;
//                }
//            }
//        }
//
//        List<Integer> tempRes = new ArrayList<>();
//        int prevCount = 0, val = 0, count = 0;
//
//        // 找出链表中的"众数"
//        for(int i=0; i<midVals.size(); i++) {
//            if(count == 0) {
//                val = midVals.get(i);
//                count = 1;
//                if(i == midVals.size()-1) {
//                    tempRes.add(val);
//                }
//                continue;
//            }
//            if(midVals.get(i) == val) {
//                count++;
//            }else {
//                if(count > prevCount) {
//                    prevCount = count;
//                    tempRes.clear();
//                    tempRes.add(val);
//                }else if(count == prevCount) {
//                    tempRes.add(val);
//                }
//                val = midVals.get(i);
//                count = 1;
//            }
//            if(i == midVals.size()-1) {
//                if(count == prevCount) {
//                    tempRes.add(val);
//                }else if(count > prevCount) {
//                    tempRes.clear();
//                    tempRes.add(val);
//                }
//            }
//        }
//        // 转换成数组
//        int[] res = new int[tempRes.size()];
//        for(int i=0; i<res.length; i++) {
//            res[i] = tempRes.get(i);
//        }
//        return res;
//    }

    public static void main(String[] args) {
        Integer[] tree = new Integer[]{1, null, 2, 2};
        TreeNode root = TreeUtils.generateTree(tree);
        System.out.println(Arrays.toString(new Solution24().findMode(root)));
        //                     1
        //                2         3
        //             4     5   6     7
        //           8   9
        tree = new Integer[]{1,2,3,4,5,6,7,8,9};
        root = TreeUtils.generateTree(tree);
        System.out.println(Arrays.toString(new Solution24().findMode(root)));
        tree = new Integer[]{2147483647};
        root = TreeUtils.generateTree(tree);
        System.out.println(Arrays.toString(new Solution24().findMode(root)));
        tree = new Integer[]{1,1,2};
        root = TreeUtils.generateTree(tree);
        System.out.println(Arrays.toString(new Solution24().findMode(root)));
    }
}
