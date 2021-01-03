package list;

//给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
//
//你应当保留两个分区中每个节点的初始相对位置。
//
//
//
//示例：
//
//输入：head = 1->4->3->2->5->2, x = 3
//输出：1->2->2->4->3->5
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/partition-list
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution12 {
    // 定义新引用保存小于x的节点
    // 将剩余节点接到小于x节点链表的末尾
    // 0ms/37.5MB
    public ListNode partition(ListNode head, int x) {
        ListNode lessHead = null;
        ListNode lessTail = null;
        ListNode greaterHead = null;
        ListNode tail = head;
        ListNode prev = null;
        while(tail != null) {
            if(tail.val < x) {
                if(lessTail == null) {
                    lessTail = tail;
                    lessHead = lessTail;
                }else {
                    lessTail.next = tail;
                    lessTail = lessTail.next;
                }
                if(prev != null) {
                    prev.next = tail.next;
                }
                tail = tail.next;
                lessTail.next = null;
            }else {
                if(greaterHead == null) {
                    greaterHead = tail;
                }
                prev = tail;
                tail = tail.next;
            }
        }
        if(lessTail != null) {
            lessTail.next = greaterHead;
            return lessHead;
        }else {
            return head;
        }
    }

    public static void main(String[] args) {
        ListNode list = ListUtils.generateList(new Integer[]{1,4,3,2,5,2});
        ListUtils.printList(new Solution12().partition(list, 0)); // 1,4,3,2,5,2
        list = ListUtils.generateList(new Integer[]{1,4,3,2,5,2});
        ListUtils.printList(new Solution12().partition(list, 1)); // 1,4,3,2,5,2
        list = ListUtils.generateList(new Integer[]{1,4,3,2,5,2});
        ListUtils.printList(new Solution12().partition(list, 2)); // 1,4,3,2,5,2
        list = ListUtils.generateList(new Integer[]{1,4,3,2,5,2});
        ListUtils.printList(new Solution12().partition(list, 3)); // 1->2->2->4->3->5
        list = ListUtils.generateList(new Integer[]{1,4,3,2,5,2});
        ListUtils.printList(new Solution12().partition(list, 4)); // 1,3,2,2,4,5
        list = ListUtils.generateList(new Integer[]{1,4,3,2,5,2});
        ListUtils.printList(new Solution12().partition(list, 5)); // 1,4,3,2,2,5
        list = ListUtils.generateList(new Integer[]{1,4,3,2,5,2});
        ListUtils.printList(new Solution12().partition(list, 6)); // 1,4,3,2,5,2
        list = ListUtils.generateList(new Integer[0]);
        ListUtils.printList(new Solution12().partition(list, 6)); // 1,4,3,2,5,2
        list = ListUtils.generateList(new Integer[]{8});
        ListUtils.printList(new Solution12().partition(list, 6)); // 1,4,3,2,5,2
    }
}
