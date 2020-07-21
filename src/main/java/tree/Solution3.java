package tree;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
//
// 
//
//示例：
//
//输入：3
//输出：
//[
//  [1,null,3,2],
//  [3,2,null,1],
//  [3,1,null,null,2],
//  [2,1,3],
//  [1,null,2,null,3]
//]
//解释：
//以上的输出对应以下 5 种不同结构的二叉搜索树：
//
//1         3     3      2      1
// \       /     /      / \      \
//  3     2     1      1   3      2
// /     /       \                 \
//2     1         2                 3
// 
//
//提示：
//
//0 <= n <= 8
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// 2ms/40.5MB
public class Solution3 {
    public List<TreeNode> generateTrees(int n) {
        // 处理边界问题，0时返回空list
        if(n == 0) {
            return new ArrayList<>();
        }
        // 递归生成树
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int begin, int end) {
        // 结果集
        List<TreeNode> res = new ArrayList<>();
        // 结束条件
        if(begin > end) {
            // 返回一个包含null的list，用于表示一个节点的左节点或右节点为null
            res.add(null);
            return res;
        }

        // 遍历begin到end范围内的每一个值，依次将其值作为root节点，循环生成树
        for(int i=begin; i<=end; i++) {
            // 生成左子树
            List<TreeNode> leftTree = generateTrees(begin, i-1);
            // 生成右子树
            List<TreeNode> rightTree = generateTrees(i+1, end);
            // 根据左右子树拼接新树
            for(TreeNode left : leftTree) {
                for(TreeNode right : rightTree) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    // 将每次生成的树追加到结果集
                    res.add(root);
                }
            }
        }
        // 返回生成的树
        return res;
    }

}
