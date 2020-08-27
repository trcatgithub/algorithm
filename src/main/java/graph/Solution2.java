package graph;

import java.util.*;

//给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，
//对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
//
//说明:
//
//如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
//所有的机场都用三个大写字母表示（机场代码）。
//假定所有机票至少存在一种合理的行程。
//示例 1:
//
//输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
//输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
//示例 2:
//
//输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
//解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/reconstruct-itinerary
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution2 {

    // 1，将List<List<String>>构造成图(Map<String, Queue<String>>)
    // 2，利用Stack进行dfs
    // 11ms/40.5MB
    public List<String> findItinerary(List<List<String>> tickets) {
        // 处理边界
        if(tickets == null || tickets.size() == 0) {
            return new ArrayList<>();
        }
        // 构造图
        // key:表示当前位置
        // value:能够到达位置的集合(按照字典序的优先队列)
        Map<String, Queue<String>> graph = new HashMap<>();
        // 遍历tickets，构造每一个节点
        for(List<String> ticket : tickets) {
            // 获取当前出发点ticket.get(0)的 到达点集合
            Queue<String> queue = graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>());
            // 将到达点ticket.get(1)加入到该集合当中
            queue.offer(ticket.get(1));
        }
        // 结果集
        List<String> res = new ArrayList<>();
        // 用于遍历的栈
        Stack<String> stack = new Stack<>();
        // 将起点入栈
        stack.push("JFK");
        // 保存到达节点集合的引用
        Queue<String> tempQueue = null;
        // 开始dfs
        while(!stack.isEmpty()) {
            // 获取 "当前起始节点" 的 "到达节点集合"， 并将该结合内的节点 按照 字典序顺序 入栈
            while((tempQueue = graph.get(stack.peek())) != null && !tempQueue.isEmpty()) {
                stack.push(tempQueue.poll());
            }
            // 将stack内数据 逆序 加入到res
            // （stack内的数据为字典序顺序入栈，则出栈顺序为字典序逆序，因此这里采用逆序加入res中）
            res.add(0, stack.pop());
        }
        return res;
    }
//            JFK
//           /   \\
//         KUL   NRT

    public static void main(String[] args) {
//        [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
        List<List<String>> tickets = new ArrayList<>();
//        tickets.add(Arrays.asList("MUC", "LHR"));
//        tickets.add(Arrays.asList("JFK", "MUC"));
//        tickets.add(Arrays.asList("SFO", "SJC"));
//        tickets.add(Arrays.asList("LHR", "SFO"));
//        // ["JFK", "MUC", "LHR", "SFO", "SJC"]
//        System.out.println(new SolutionX().findItinerary(tickets));

//        [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//          JFK
//        /     \\
//      SFO == ATL
        tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK","SFO"));
        tickets.add(Arrays.asList("JFK","ATL"));
        tickets.add(Arrays.asList("SFO","ATL"));
        tickets.add(Arrays.asList("ATL","JFK"));
        tickets.add(Arrays.asList("ATL","SFO"));
        // ["JFK","ATL","JFK","SFO","ATL","SFO"]
        System.out.println(new Solution2().findItinerary(tickets));

//        [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
//            JFK
//           /   \\
//         KUL   NRT

        tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK","KUL"));
        tickets.add(Arrays.asList("JFK","NRT"));
        tickets.add(Arrays.asList("NRT","JFK"));
        // ["JFK","NRT","JFK","KUL"]
        System.out.println(new Solution2().findItinerary(tickets));

        // [a,b] [a,c] [c,d] [d,a] [b,e]
        // [a,c] [c,d] [d,a] [a,b] [b,e]
        // [a,b] [b,e]
    }
}
