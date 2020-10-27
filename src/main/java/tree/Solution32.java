package tree;

import java.util.*;

//给定一个无向、连通的树。树中有 N 个标记为 0...N-1 的节点以及 N-1 条边 。
//
//第 i 条边连接节点 edges[i][0] 和 edges[i][1] 。
//
//返回一个表示节点 i 与其他所有节点距离之和的列表 ans。
//
//示例 1:
//
//输入: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
//输出: [8,12,6,10,10,10]
//解释:
//如下为给定的树的示意图：
//       0
//      / \
//     1   2
//        /|\
//       3 4 5
//
//我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
//也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
//说明: 1 <= N <= 10000
public class Solution32 {

    // 1, 将二维数组描述成链表结构，下标为当前节点，内容为当前节点的子节点
    // 2, 利用递归从底向上计算出，每个节点的子节点个数 与 每个节点到其所有叶子节点的距离和
    // 3, 利用递归从顶向下计算出，每个节点到所有节点的距离和
    //       0
    //      / \
    //     1   2
    //        /|\
    //       3 4 5
    // 2到所有节点的距离和 = 0到所有节点的距离和(0到所有叶子节点的距离和) - 2的子节点个数 + 总节点个数 - 2的子节点个数
    //                   = 8 - 4 + 6 - 4
    //                   = 6
    // 23ms/46.3MB
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        // 用链表描述该树，链表每一位的位置表示对应的节点，每一位的value表示该节点的叶子节点
        List<List<Integer>> tree = new ArrayList<>();
        for(int i=0; i<N; i++) {
            tree.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }

        // 每个位置对应的节点 距离 其他所有节点的距离和
        int[] distSum = new int[N];
        // 每个位置对应的节点 的子节点个数(包括自己)
        int[] nodeNum = new int[N];
        Arrays.fill(nodeNum, 1);
        // 计算出所有节点的子节点个数nodeNum
        // 计算出所有包含子节点的节点 到其所有子节点的距离和
        //       0
        //      / \
        //     1   2
        //        /|\
        //       3 4 5
        // 本次计算后:
        // 0到其所有子节点的距离和为8(由于0没有父节点，该和即是0到其他所有节点的距离和)
        // 2到其所有子节点的距离和为3
        postOrder(tree, distSum, nodeNum, 0, -1);
        // 计算每个节点到所有节点的距离和
        preOrder(tree, distSum, nodeNum, 0, -1);
        return distSum;
    }

    // 自底向上处理每个节点
    // 1,计算当前节点 与 所有子节点之间的距离和
    // 2,计算所有节点的子节点数
    private void postOrder(List<List<Integer>> tree, int[] distSum, int[] nodeNum, int root, int parent) {
        // 获取root节点所有直接相连的节点
        List<Integer> neighbors = tree.get(root);
        for(int neighbor : neighbors) {
            // 不处理root节点的父节点
            if(neighbor == parent) {
                continue;
            }
            // 递归处理neighbor节点对应的子树
            postOrder(tree, distSum, nodeNum, neighbor, root);
            // 计算root节点的子节点个数
            nodeNum[root]+= nodeNum[neighbor];
            // 计算root节点到所有子节点之间的距离和
            distSum[root]+= (nodeNum[neighbor]+distSum[neighbor]);
        }
    }

    // 自顶向下处理每个节点
    // 根据当前节点的父节点到所有节点距离和 与 当前节点的个数，计算出当前节点到每个节点距离的和
    private void preOrder(List<List<Integer>> tree, int[] distSum, int[] nodeNum, int root, int parent) {
        // 获取root节点所有直接相连的节点
        List<Integer> neighbors = tree.get(root);
        for(int neighbor : neighbors) {
            // 不处理root节点的父节点
            if(neighbor == parent) {
                continue;
            }
            // neighbor是root的直接子节点
            // neighbor到所有节点的距离 = root节点到所有节点的距离 - neighbor节点子节点个数 + 总结点个数 - neighbor节点子节点个数
            //       0
            //      / \
            //     1   2
            //        /|\
            //       3 4 5
            // 1到所有节点的距离 = 0到所有节点的距离 - 1的子节点个数 + 总结点个数 - 1的子节点个数
            //                 = 8 - 1 + 6 - 1
            //                 = 12
            // 2到所有节点的距离 = 0到所有节点的距离 - 2的子节点个数 + 总结点个数 - 2的子节点个数
            //                 = 8 - 4 + 6 - 4
            //                 = 6
            // 3到所有节点的距离 = 2到所有节点的距离 - 3的子节点个数 + 总结点个数 - 3的子节点个数
            //                 = 6 - 1 + 6 - 1
            //                 = 10
            // 4到所有节点的距离 = 2到所有节点的距离 - 4的子节点个数 + 总结点个数 - 4的子节点个数
            //                 = 6 - 1 + 6 - 1
            //                 = 10
            // 5到所有节点的距离 = 2到所有节点的距离 - 5的子节点个数 + 总结点个数 - 5的子节点个数
            //                 = 6 - 1 + 6 - 1
            //                 = 10
            distSum[neighbor] = distSum[root] - nodeNum[neighbor] + (distSum.length - nodeNum[neighbor]);
            preOrder(tree, distSum, nodeNum, neighbor, root);
        }
    }

    public static void main(String[] args) {
        int N = 6;
        int[][] edges = new int[][]{{0,1},{0,2},{2,3},{2,4},{2,5}};
        // [8,12,6,10,10,10]
        System.out.println(Arrays.toString(new Solution32().sumOfDistancesInTree(N, edges)));
    }

}
