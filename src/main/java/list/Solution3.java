package list;

//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
//
//你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
//
// 
//
//示例:
//
//给定 1->2->3->4, 你应该返回 2->1->4->3.
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution3 {
//    // 0ms/37.4MB
//    public ListNode swapPairs(ListNode head) {
//        // 处理边界问题
//        if(head == null || head.next == null) {
//            return head;
//        }
//        // 交换后链表的头结点
//        ListNode newHead = head.next;
//        // 当前节点
//        ListNode tail = head;
//        // 前一个节点
//        ListNode prev = null;
//        // 遍历每一个节点，同时进行两两交换
//        while(tail != null) {
//            // 初始化
//            if(prev == null) {
//                prev = tail;
//                tail = tail.next;
//                continue;
//            }
//            // 设原链表为 1，2，3，4
//            // prev为1，tail为2
//
//            // 将前一个节点的next指向当前节点的next
//            // 此时链表分为两部分 (1,3,4) 与（2,3,4）
//            prev.next = tail.next;
//            // 将当前节点next指向前一个节点( prev:1,3,4  tail:2,1,3,4)
//            // 此时链表为(2,1,3,4)  tail指向2   prev指向1
//            tail.next = prev;
//            // 将当前节点移动到下一个节点
//            // 此时链表为(2,1,3,4)  tail指向3   prev指向1
//            tail = tail.next.next;
//            // 移动后的当前节点不为空时
//            if(tail != null) {
//                // 移动后的当前节点的next不为空时
//                if(tail.next != null) {
//                    // 此时链表为(2,1,3,4) tail指向3   prev(1)的next指向4
//                    prev.next = tail.next;
//                }
//                // 更新prev
//                // 此时链表为(2,1,3,4) tail指向3   prev指向3
//                prev = tail;
//                // tail指针后移
//                // 此时链表为(2,1,3,4) tail指向4   prev指向3
//                tail = tail.next;
//            }
//        }
//        return newHead;
//    }

    // 0ms/36.4MB
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode tail = head;
        ListNode prev = null;
        ListNode newHead = head.next;
        ListNode newTail = null;
        while(tail != null) {
            if(prev == null) {
                prev = tail;
                tail = tail.next;
                prev.next = null;
            }else {
                if(newTail == null) {
                    newTail = tail;
                    tail = tail.next;
                    newTail.next = prev;
                    newTail = newTail.next;
                }else {
                    newTail.next = tail;
                    tail = tail.next;
                    newTail.next.next = prev;
                    newTail = newTail.next.next;
                }
                prev = null;
            }
        }
        if(prev != null) {
            newTail.next = prev;
        }
        return newHead;
    }
    public static void main(String[] args) {
        ListUtils.printList(new Solution3().swapPairs(ListUtils.generateList(new Integer[]{1})));
        ListUtils.printList(new Solution3().swapPairs(ListUtils.generateList(new Integer[]{1,2})));
        ListUtils.printList(new Solution3().swapPairs(ListUtils.generateList(new Integer[]{1,2,3})));
        ListUtils.printList(new Solution3().swapPairs(ListUtils.generateList(new Integer[]{1,2,3,4})));
        ListUtils.printList(new Solution3().swapPairs(ListUtils.generateList(new Integer[]{1,2,3,4,5,6,7,8,9})));
    }
}
