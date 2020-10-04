package list;

//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
//并且它们的每个节点只能存储 一位 数字。
//
//如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//
//您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
//示例：
//
//输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/add-two-numbers
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution4 {
    // 2ms/38.7MB
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 处理边界
        if(l1 == null) {
            return l2;
        }else if(l2 == null) {
            return l1;
        }

        ListNode res = null;
        ListNode tail = null;
        int add = 0;
        int val = 0;
        // 链表相加
        while(l1 != null && l2 != null) {
            val = (add+l1.val+l2.val)%10;
            add = (add+l1.val+l2.val)/10;
            if(res == null) {
                res = new ListNode(val);
                tail = res;
            }else {
                tail.next = new ListNode(val);
                tail = tail.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        // 处理剩余的链表
        ListNode left = (l1 == null ? l2 : l1);
        if(left != null) {
            while(left != null) {
                val = (add+left.val)%10;
                add = (add+left.val)/10;
                tail.next = new ListNode(val);
                tail = tail.next;
                left = left.next;
            }
        }
        // 处理最后的进位
        if(add > 0) {
            tail.next = new ListNode(add);
        }
        return res;
    }

//    private ListNode reverse(ListNode list) {
//        ListNode tail = list;
//        ListNode reverse = null;
//        while(tail != null) {
//            if(reverse == null) {
//                reverse = tail;
//                tail = tail.next;
//                reverse.next = null;
//                continue;
//            }
//            ListNode temp = tail;
//            tail = tail.next;
//            temp.next = reverse;
//            reverse = temp;
//        }
//        return reverse;
//    }

    public static void main(String[] args) {
        ListNode l1 = ListUtils.generateList(new Integer[]{2,4,3});
        ListNode l2 = ListUtils.generateList(new Integer[]{5,6,4});
        ListUtils.printList(new Solution4().addTwoNumbers(l1, l2)); // 7,0,8
        l1 = ListUtils.generateList(new Integer[]{8,3,2});
        l2 = ListUtils.generateList(new Integer[]{9,2,1});
        ListUtils.printList(new Solution4().addTwoNumbers(l1, l2)); // 7,6,3
        l1 = ListUtils.generateList(new Integer[]{9});
        l2 = ListUtils.generateList(new Integer[]{9});
        ListUtils.printList(new Solution4().addTwoNumbers(l1, l2)); // 8,1
    }
}
