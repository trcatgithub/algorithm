package list;

//给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。
//请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
//
//请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
//
//示例 1:
//
//输入: 1->2->3->4->5->NULL
//输出: 1->3->5->2->4->NULL
//
//
//示例 2:
//
//输入: 2->1->3->5->6->4->7->NULL
//输出: 2->3->6->7->1->5->4->NULL
//
//说明:
//
//
//应当保持奇数节点和偶数节点的相对顺序。
//链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/odd-even-linked-list
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution9 {
    // 注意切断引用，防止链表出环
    // 0ms/38.2MB
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode oddTail = null;
        ListNode evenHead = null;
        ListNode evenTail = null;
        ListNode tail = head;
        int count = 1;
        while(tail != null) {
            if(count%2 == 0) {
                if(evenTail == null) {
                    evenTail = tail;
                    evenHead = tail;
                    tail = tail.next;
                    evenTail.next = null;
                    count++;
                    continue;
                }
                evenTail.next = tail;
                tail = tail.next;
                evenTail = evenTail.next;
                evenTail.next = null;
            }else {
                if(oddTail == null) {
                    oddTail = tail;
                    tail = tail.next;
                    oddTail.next = null;
                    count++;
                    continue;
                }
                oddTail.next = tail;
                tail = tail.next;
                oddTail = oddTail.next;
                oddTail.next = null;
            }
            count++;
        }
        oddTail.next = evenHead;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListUtils.generateList(new Integer[]{1,2,3,4,5});
        ListUtils.printList(new Solution9().oddEvenList(head)); // 13524
        head = ListUtils.generateList(new Integer[]{2,1,3,5,6,4,7}); // 2367154
        ListUtils.printList(new Solution9().oddEvenList(head));
        head = ListUtils.generateList(new Integer[]{2,1}); //
        ListUtils.printList(new Solution9().oddEvenList(head)); // 21
        head = ListUtils.generateList(new Integer[]{1});
        ListUtils.printList(new Solution9().oddEvenList(head)); // 1
        head = ListUtils.generateList(new Integer[]{1,2,3});
        ListUtils.printList(new Solution9().oddEvenList(head)); // 132
    }
}
