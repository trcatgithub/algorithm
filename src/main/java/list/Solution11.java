package list;

//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
//
//进阶：
//
//
//你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
//
//
//
//
//示例 1：
//
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
//
//
//示例 2：
//
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
//
//
//示例 3：
//
//输入：head = []
//输出：[]
//
//
//
//
//提示：
//
//
//链表中节点的数目在范围 [0, 5 * 10^4] 内
//-10^5 <= Node.val <= 10^5
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/sort-list
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution11 {
    // 归并排序
    // 6ms/47.1MB
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        return helper(head, null);
    }

    private ListNode helper(ListNode head, ListNode tail) {
        // head与tail为相同元素时，不做处理
        if(head == tail) {
            return head;
        }
        // 1，head与tail相邻
        // 2，tail不为null
        // 3，head.val > tail.val
        // 满足条件1，2，3时，交换head与tail
        if(head.next == tail && tail != null) {
            if(head.val > tail.val) {
                head.next = tail.next;
                tail.next = head;
                return tail;
            }
            return head;
        }
        // 找到链表分割点
        ListNode one = head;
        ListNode two = head;
        while (two != tail && two.next != tail) {
            one = one.next;
            two = two.next.next;
        }
        ListNode mid = one.next;
        one.next = null;
        return merge(helper(head, one), helper(mid, tail));
    }

    // 合并两个链表
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode ascHead = new ListNode(-1);
        ListNode ascTail = ascHead;
        while(head1 != null || head2 != null) {
            if(head1 == null) {
                ascTail.next = head2;
                break;
            }
            if(head2 == null) {
                ascTail.next = head1;
                break;
            }
            if(head1.val < head2.val) {
                ascTail.next = head1;
                head1 = head1.next;
            }else {
                ascTail.next = head2;
                head2 = head2.next;
            }
            ascTail = ascTail.next;
        }
        return ascHead.next;
    }

    public static void main(String[] args) {
        ListNode head = ListUtils.generateList(new Integer[]{4,2,1,3});
        ListUtils.printList(new Solution11().sortList(head));
        head = ListUtils.generateList(new Integer[]{-1,5,3,4,0});
        ListUtils.printList(new Solution11().sortList(head));
    }
}
