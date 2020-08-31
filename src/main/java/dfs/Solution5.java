package dfs;

import java.util.*;

//有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
//
//在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。
//钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
//
//最初，除 0 号房间外的其余所有房间都被锁住。
//
//你可以自由地在房间之间来回走动。
//
//如果能进入每个房间返回 true，否则返回 false。
//
//示例 1：
//
//输入: [[1],[2],[3],[]]
//输出: true
//解释:
//我们从 0 号房间开始，拿到钥匙 1。
//之后我们去 1 号房间，拿到钥匙 2。
//然后我们去 2 号房间，拿到钥匙 3。
//最后我们去了 3 号房间。
//由于我们能够进入每个房间，我们返回 true。
//示例 2：
//
//输入：[[1,3],[3,0,1],[2],[0]]
//输出：false
//解释：我们不能进入 2 号房间。
//提示：
//
//1 <= rooms.length <= 1000
//0 <= rooms[i].length <= 1000
//所有房间中的钥匙数量总计不超过 3000。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/keys-and-rooms
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution5 {

    // 非递归dfs
    // 2ms/39.9MB
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Stack<Integer> memo = new Stack<>();
        // 访问过的房间数
        int count = 1;
        // 访问过的房间
        int[] visited = new int[rooms.size()];
        // 将房间0标记为已访问
        visited[0] = 1;
        // 将房间0入栈
        memo.push(0);
        // dfs
        while(!memo.isEmpty()) {
            for(int key : rooms.get(memo.pop())) {
                // 若该房间未访问过
                if(visited[key] == 0) {
                    // 房间数++
                    count++;
                    // 将该房间标记为已访问
                    visited[key] = 1;
                    memo.push(key);
                }
            }
        }
        // 判断 已访问过的房间数 是否等于 房间总数
        return count == rooms.size();
    }

//    // 递归DFS
//    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
//        // 处理边界
//        if(rooms.size() == 1) {
//            return true;
//        }
//        // 保存能够进入的房间
//        Map<Integer, Boolean> memo = new HashMap<>();
//        // dfs
//        dfs(memo, rooms, 0);
//        // 能够进入的房间数 与 rooms数 进行比较
//        return memo.size() == rooms.size();
//    }
//
//    // 2ms/39.9MB
//    private boolean dfs(Map<Integer, Boolean> memo, List<List<Integer>> rooms, int num) {
//        // 能够进入的房间数 等于 rooms数
//        if(memo.size() == rooms.size()) {
//            return true;
//        }else if(memo.containsKey(num)) { // memo包含num时直接返回，降低运算次数
//            return memo.get(num);
//        }else if(memo.size()+1 == rooms.size()) { // 能够进入的房间数+1 等于 rooms数   且 memo不包含num
//            return true;
//        }
//        boolean flag = false;
//        List<Integer> temp = rooms.get(num);
//        // 遍历当前房间钥匙列表
//        for(int i=0; i<temp.size(); i++) {
//            int current = temp.get(i);
//            if(current >= 0) {
//                // 标记已使用的钥匙
//                temp.set(i, -1);
//                // dfs判断该路径是否能包含所有房间
//                flag = flag || dfs(memo, rooms, current);
//            }
//        }
//        // 保存计算结果
//        memo.put(num, flag);
//        return flag;
//    }

    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(new ArrayList<Integer>(Arrays.asList(1)));
        rooms.add(new ArrayList<Integer>(Arrays.asList(2)));
        rooms.add(new ArrayList<Integer>(Arrays.asList(3)));
        rooms.add(new ArrayList<>());
        System.out.println(new Solution5().canVisitAllRooms(rooms));
//        [[1,3],[3,0,1],[2],[0]]
        rooms = new ArrayList<>();
        rooms.add(new ArrayList<Integer>(Arrays.asList(1,3)));
        rooms.add(new ArrayList<Integer>(Arrays.asList(3,0,1)));
        rooms.add(new ArrayList<Integer>(Arrays.asList(2)));
        rooms.add(new ArrayList<Integer>(Arrays.asList(0)));
        System.out.println(new Solution5().canVisitAllRooms(rooms));
        // [[]]
        rooms = new ArrayList<>();
        rooms.add(new ArrayList<>());
        System.out.println(new Solution5().canVisitAllRooms(rooms));
    }
}
