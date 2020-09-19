package tree;

//给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
//
//二叉树的根是数组中的最大元素。
//左子树是通过数组中最大值左边部分构造出的最大二叉树。
//右子树是通过数组中最大值右边部分构造出的最大二叉树。
//通过给定的数组构建最大二叉树，并且输出这个树的根节点。
//
// 
//
//示例 ：
//
//输入：[3,2,1,6,0,5]
//输出：返回下面这棵树的根节点：
//
//           6
//         /   \
//        3     5
//         \   /
//          2 0
//           \
//            1
// 
//
//提示：
//
//给定的数组的大小在 [1, 1000] 之间。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximum-binary-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution17 {
    // 2ms/38.8MB
    // 基于递归的dfs
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length-1);
    }

    // 递归生成树
    private TreeNode dfs(int[] nums, int start, int end) {
        // 结束条件
        if(start > end) {
            return null;
        }
        // 创建当前节点
        int pos = findMaxPos(nums, start, end);
        TreeNode current = new TreeNode(nums[pos]);
        // 递归创建左右子树
        current.left = dfs(nums, start, pos-1);
        current.right = dfs(nums, pos+1, end);
        return current;
    }

    // 查找指定范围内最大值的位置
    private int findMaxPos(int[] nums, int start, int end) {
        int max = Integer.MIN_VALUE;
        int pos = 0;
        for(int i=start; i<=end; i++) {
            if(max < nums[i]) {
                max = nums[i];
                pos = i;
            }
        }
        return pos;
    }
}
