package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
//
//         
//
//示例 1：
//
//输入：
//     3
//    / \
//   9  20
//     /  \
//    15   7
//输出：[3, 14.5, 11]
//解释：
//第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
// 
//
//提示：
//
//节点值的范围在32位有符号整数范围内。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/average-of-levels-in-binary-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution14 {

    // 基于递归的dfs，依次统计每层节点数与节点之和
    // 2ms/41.7MB
    public List<Double> averageOfLevels(TreeNode root) {
        // 每层节点值之和
        List<Double> res = new ArrayList<>();
        // 每层节点数
        List<Integer> count = new ArrayList<>();
        dfs(res, count, root, 0);
        // 计算每层平均值
        for(int i=0; i<res.size(); i++) {
            res.set(i, res.get(i)/count.get(i));
        }
        return res;
    }

    private void dfs(List<Double> res, List<Integer> count, TreeNode node, int level) {
        // 递归结束条件
        if(node == null) {
            return;
        }
        // 初始化
        if(res.size() <= level) {
            res.add(0.0);
            count.add(0);
        }
        // 加算节点值 与 节点数
        res.set(level, res.get(level)+node.val);
        count.set(level, count.get(level)+1);
        // dfs左右子树
        dfs(res, count, node.left, level+1);
        dfs(res, count, node.right, level+1);
    }

//    // 基于队列的bfs
//    // 3ms/41.4MB
//    public List<Double> averageOfLevels(TreeNode root) {
//        // 处理空树
//        if(root == null) {
//            return new ArrayList<>();
//        }
//        // 结果集
//        List<Double> res = new ArrayList<>();
//        // 节点队列
//        Queue<TreeNode> memo = new LinkedList<>();
//        // 根节点入队
//        memo.offer(root);
//        // 每一层的节点数
//        int count = 1;
//
//        while(!memo.isEmpty()) {
//            // 当前层节点值之和
//            double sum = 0;
//            // 下一层节点数
//            int nextLevel = 0;
//            // 从队列中取出当前层节点数个节点
//            for(int i=0; i<count; i++) {
//                TreeNode current = memo.poll();
//                // 将下一层节点入队，并累加下一层节点数
//                if(current.left != null) {
//                    memo.offer(current.left);
//                    nextLevel++;
//                }
//                if(current.right != null) {
//                    memo.offer(current.right);
//                    nextLevel++;
//                }
//                // 加算节点和
//                sum+= current.val;
//            }
//            // 将平均值加入到结果集中国
//            res.add(sum/count);
//            // 重置当前层节点数
//            count = nextLevel;
//        }
//        return res;
//    }

    public static void main(String[] args) {
        Integer[] tree = new Integer[]{3,9,20,null,null,15,7};
        System.out.println(new Solution14().averageOfLevels(TreeUtils.generateTree(tree)));
    }
}
