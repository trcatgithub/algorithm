package list;

//给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
//
//为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
//
//说明：不允许修改给定的链表。
//
// 
//
//示例 1：
//
//输入：head = [3,2,0,-4], pos = 1
//输出：tail connects to node index 1
//解释：链表中有一个环，其尾部连接到第二个节点。
//
//
//示例 2：
//
//输入：head = [1,2], pos = 0
//输出：tail connects to node index 0
//解释：链表中有一个环，其尾部连接到第一个节点。
//
//
//示例 3：
//
//输入：head = [1], pos = -1
//输出：no cycle
//解释：链表中没有环。
//
//
// 
//
//进阶：
//你是否可以不用额外空间解决此题？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution6 {
    // 环入口为：
    // 快慢指针找到的相同节点 与 头结点以相同步数(1步)遍历遇到的第一个相同节点
    // 0ms/38.8MB
    public ListNode detectCycle(ListNode head) {
        // 快慢指针
        ListNode one = head;
        ListNode two = head;
        // 快慢指针第一次相遇的节点
        ListNode same = null;
        while(one != null && two != null) {
            one = one.next;
            two = (two.next == null ? null : two.next.next);
            if(one == two && one != null) {
                same = one;
                break;
            }
        }
        // 存在环
        if(same != null) {
            // 如果head节点即是相遇节点，则直接返回head
            if(same == head) {
                return head;
            }
            // 寻找环的入口
            ListNode tail = head;
            while(tail != null && one != null) {
                tail = tail.next;
                one = one.next;
                if(tail == one) {
                    return tail;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // 1->2->3
        //    |  |
        //    5<-4
        // one = 1, two = 1
        // one = 2, two = 3
        // one = 3, two = 5
        // one = 4, two = 3
        // one = 5, two = 5
        Integer[] head = new Integer[]{3,2,0,-4};
        int pos = 1;
        // 2
        System.out.println(new Solution6().detectCycle(ListUtils.generateList(head, pos)));
        head = new Integer[]{1,2};
        pos = 0;
        // 1
        System.out.println(new Solution6().detectCycle(ListUtils.generateList(head, pos)));
        head = new Integer[]{1};
        pos = -1;
        // 1
        System.out.println(new Solution6().detectCycle(ListUtils.generateList(head, pos)));
    }
}
