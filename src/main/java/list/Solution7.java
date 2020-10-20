package list;

import java.util.ArrayList;
import java.util.List;

//给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
//将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
//
//你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
//
//示例 1:
//
//给定链表 1->2->3->4, 重新排列为 1->4->2->3.
//示例 2:
//
//给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/reorder-list
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution7 {

    // 1,先找链表中点
    // 2,翻转中点之后的部分
    // 3,合并原链表 与 翻转后链表
    // 1ms/41.1MB
    public void reorderList(ListNode head) {
        // 快慢指针找链表中点
        ListNode one = head;
        ListNode two = head;
        while(one != null && two != null) {
            one = one.next;
            two = (two.next == null ? null : two.next.next);
        }
        // 处理边界问题
        if(one == null) {
            return;
        }
        // 从中点one开始翻转链表
        ListNode reverseHead = one;
        ListNode reverseTail = one.next;
        one.next = null;
        while(reverseTail != null) {
            ListNode next = reverseTail.next;
            reverseTail.next = reverseHead;
            reverseHead = reverseTail;
            reverseTail = next;
        }

        // 合并两个链表
        ListNode tail = head;
        reverseTail = reverseHead;
        while(tail != null || reverseTail != null) {
            // 切断最后一个节点的next引用，防止链表出环
            if(reverseTail == null) {
                tail.next =null;
                break;
            }
            ListNode oriNext = tail.next;
            ListNode reverseNext = reverseTail.next;
            tail.next = reverseTail;
            reverseTail.next = oriNext;
            tail = oriNext;
            reverseTail = reverseNext;
        }
    }

//    // 利用ArrayList
//    // 4ms/40.7Mb
//    public void reorderList(ListNode head) {
//        List<ListNode> memo = new ArrayList<>();
//        ListNode tail = head;
//        // 将链表保存到ArrayList
//        while(tail != null) {
//            memo.add(tail);
//            tail = tail.next;
//        }
//        // 根据下标维护引用
//        for(int l=0, r=memo.size()-1; l<r;) {
//            memo.get(l).next = memo.get(r);
//            l++;
//            memo.get(r).next = (l<r ? memo.get(l) : null);
//            r--;
//            if(l == r) {
//                memo.get(r).next = null;
//            }
//        }
//    }

    public static void main(String[] args) {
        ListNode list = ListUtils.generateList(new Integer[]{1,2,3,4,5,6});
        new Solution7().reorderList(list);
        ListUtils.printList(list);
        list = ListUtils.generateList(new Integer[]{1,2,3,4,5,6,7});
        new Solution7().reorderList(list);
        ListUtils.printList(list);
        list = ListUtils.generateList(new Integer[]{1});
        new Solution7().reorderList(list);
        ListUtils.printList(list);
        list = ListUtils.generateList(new Integer[]{1,2});
        new Solution7().reorderList(list);
        ListUtils.printList(list);
        list = ListUtils.generateList(new Integer[]{1,2,3});
        new Solution7().reorderList(list);
        ListUtils.printList(list);
        list = ListUtils.generateList(new Integer[]{});
        new Solution7().reorderList(list);
        ListUtils.printList(list);
    }
}
