package graph;

//有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
//
//省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
//
//给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
//
//返回矩阵中 省份 的数量。
//
// 
//
//示例 1：
//
//
//输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//输出：2
//示例 2：
//
//
//输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
//输出：3
// 
//
//提示：
//
//1 <= n <= 200
//n == isConnected.length
//n == isConnected[i].length
//isConnected[i][j] 为 1 或 0
//isConnected[i][i] == 1
//isConnected[i][j] == isConnected[j][i]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/number-of-provinces
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution5 {

    // 使用一个visited数组, 依次判断每个节点, 如果其未访问, 省数加1并对该节点进行dfs搜索标记所有访问到的节点
    // 1ms/39.5MB
    public int findCircleNum(int[][] isConnected) {
        int cities = isConnected.length;
        boolean[] visited = new boolean[cities];
        int provinces = 0;
        for (int i = 0; i < cities; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, cities, i);
                provinces++;
            }
        }
        return provinces;
    }

    // 标记i访问到的节点
    private void dfs(int[][] isConnected, boolean[] visited, int cities, int i) {
        for (int j = 0; j < cities; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, cities, j);
            }
        }
    }

    public static void main(String[] args) {
        int[][] isConnected = new int[][]{{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(new Solution5().findCircleNum(isConnected)); // 2
        isConnected = new int[][]{{1,0,0},{0,1,0},{0,0,1}};
        System.out.println(new Solution5().findCircleNum(isConnected)); // 3
    }
}
