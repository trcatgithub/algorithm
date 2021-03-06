package list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
//
//示例：
//
//给定一个链表: 1->2->3->4->5, 和 n = 2.
//
//当删除了倒数第二个节点后，链表变为 1->2->3->5.
//说明：
//
//给定的 n 保证是有效的。
//
//进阶：
//
//你能尝试使用一趟扫描实现吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution1 {

//    // 1ms/39.2MB
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        // 用于节点遍历
//        ListNode tail = head;
//        // 保存每一个节点
//        Map<Integer, ListNode> memo = new HashMap<>();
//        // 遍历所有节点
//        for(int i=1; tail != null; i++) {
//            memo.put(i, tail);
//            tail = tail.next;
//        }
//        // 处理边界问题
//        if(memo.size() == 1) {
//            return null;
//        }else if(memo.size() == n) { // 待删除节点为head节点时
//            ListNode newHead = head.next;
//            head.next = null;
//            return newHead;
//        }
//        // 将待删除节点前一个节点的next 指向 待删除节点的下一个节点
//        memo.get(memo.size()-n).next = memo.get(memo.size()-n+2);
//        // 将待删除节点的next 赋null，便于垃圾回收
//        memo.get(memo.size()-n+1).next = null;
//        // 返回头节点引用
//        return head;
//    }

//    // 基于ArrayList
//    // 1ms/36.6MB
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        List<ListNode> memo = new ArrayList<>();
//        // 将所有节点存入ArrayList
//        ListNode tail = head;
//        while(tail != null) {
//            memo.add(tail);
//            tail = tail.next;
//        }
//        // 删除的节点为头节点时
//        if(n == memo.size()) {
//            return head.next;
//        }else if(n == 1) { // 删除的节点为尾节点时
//            memo.get(memo.size()-2).next = null;
//            return head;
//        }
//        // 删除n对应的节点
//        memo.get(memo.size()-n-1).next = memo.get(memo.size()-n+1);
//        memo.get(memo.size()-n).next = null;
//        return head;
//    }

    // 基于循环
    // 0ms/36.4MB
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int count = 0;
        ListNode tail = head;
        while(tail != null) {
            count++;
            tail = tail.next;
        }
        // 删除的是头节点时
        if(count == n) {
            return head.next;
        }
        tail = head;
        for(int i=0; i<count-n; i++) {
            if(i == count-n-1) {
                // 删除的是最后一个节点时
                if(tail.next.next == null) {
                    tail.next = null;
                }else {
                    ListNode temp = tail.next;
                    tail.next = tail.next.next;
                    temp.next = null;
                }
                break;
            }
            tail = tail.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        int n = 1;
        ListUtils.printList(new Solution1().removeNthFromEnd(head, n));
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        n = 2; // 2;
        ListUtils.printList(new Solution1().removeNthFromEnd(head, n));
        head = new ListNode(1);
        head.next = new ListNode(2);
        n = 2; // 2;
        ListUtils.printList(new Solution1().removeNthFromEnd(head, n));
        head = new ListNode(1);
        head.next = new ListNode(2);
        n = 1; // 1;
        ListUtils.printList(new Solution1().removeNthFromEnd(head, n));
    }

    private void showList() {

    }
}
