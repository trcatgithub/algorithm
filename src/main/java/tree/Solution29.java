package tree;

import java.util.LinkedList;
import java.util.Queue;

//给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
//
//struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//}
//填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
//
//初始状态下，所有 next 指针都被设置为 NULL。
//
// 
//
//示例：
//
//
//
//输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
//
//输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}
//
//解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
// 
//
//提示：
//
//你只能使用常量级额外空间。
//使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution29 {

    //                      1
    //                    /   \
    //                   2     3
    //                  / \   / \
    //                 4   5 6   7
    // 递归
    // 利用当前节点的next节点 连接 当前节点(2)的右节点(5) 与 当前节点next节点(3)的左节点(6)
    // 0ms/38.7MB
    public Node connect(Node root) {
        if(root == null || root.left == null || root.right == null) {
            return root;
        }
        // 连接当前节点的左右节点
        root.left.next = root.right;
        // 如果当前节点的next节点不为null
        if(root.next != null) {
            // 连接当前节点的右叶子节点 与 当前节点next节点的左叶子节点
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }

//    // 基于队列的bfs
//    // 3ms/38.8MB
//    public Node connect(Node root) {
//        // 处理边界问题
//        if(root == null) {
//            return null;
//        }
//        Queue<Node> queue = new LinkedList<>();
//        queue.offer(root);
//        // 按层处理每一个元素
//        while(!queue.isEmpty()) {
//            int size = queue.size();
//            // 前一个节点
//            Node left = null;
//            for(int i=0; i<size; i++) {
//                Node current = queue.poll();
//                // 将当前节点左节点入队
//                if(current.left != null) {
//                    queue.offer(current.left);
//                }
//                // 将当前节点右节点入队
//                if(current.right != null) {
//                    queue.offer(current.right);
//                }
//                // 维护next节点引用
//                if(left == null) {
//                    left = current;
//                }else {
//                    left.next = current;
//                    left = current;
//                }
//            }
//        }
//        return root;
//    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
