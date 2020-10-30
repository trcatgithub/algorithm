package unclassify;

//给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
//
//网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
//
//岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
//
// 
//
//示例 :
//
//输入:
//[[0,1,0,0],
// [1,1,1,0],
// [0,1,0,0],
// [1,1,0,0]]
//
//输出: 16
//
//解释: 它的周长是下面图片中的 16 个黄色的边：
//
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/island-perimeter
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution34 {
    // 遍历每一个节点，寻找边界节点
    // 累加该边界节点与边界的接触面
    // 8ms/39.6MB
    public int islandPerimeter(int[][] grid) {
        int len = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[i].length; j++) {
                if(grid[i][j] == 1) {
                    // 左节点为0 或 当前节点为左边界
                    if((j > 0 && grid[i][j-1] == 0) || j == 0) {
                        len++;
                    }
                    // 右节点为0 或 当前节点为右边界
                    if((j < grid[i].length-1 && grid[i][j+1] == 0) || j == grid[i].length-1) {
                        len++;
                    }
                    // 上节点为0 或 当前节点为上边界
                    if((i > 0 && grid[i-1][j] == 0) || i == 0) {
                        len++;
                    }
                    // 下节点为0 或 当前节点为下边界
                    if((i < grid.length-1 && grid[i+1][j] == 0) || i == grid.length-1) {
                        len++;
                    }
                }
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        System.out.println(new Solution34().islandPerimeter(grid)); // 16
        grid = new int[][]{{1,1,1,0},{1,1,1,0},{1,1,1,0},{1,1,1,0}};
        System.out.println(new Solution34().islandPerimeter(grid)); // 14
        grid = new int[][]{{1},{1},{0},{0}};
        System.out.println(new Solution34().islandPerimeter(grid)); // 6
        grid = new int[][]{{1}};
        System.out.println(new Solution34().islandPerimeter(grid)); // 4
    }
}
