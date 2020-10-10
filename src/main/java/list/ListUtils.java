package list;

public class ListUtils {
    public static void printList(ListNode head) {
        if(head == null) {
            System.out.println("null");
            return;
        }
        ListNode tail = head;
        while(tail != null) {
            System.out.print(tail.val+(tail.next == null ? "" : ", "));
            tail = tail.next;
        }
        System.out.println();
    }

    public static ListNode generateList(Integer[] vals) {
        if(vals == null || vals.length == 0) {
            return null;
        }
        ListNode root = new ListNode(vals[0]);
        ListNode tail = root;
        for(int i=1; i<vals.length; i++) {
            tail.next = new ListNode(vals[i]);
            tail = tail.next;
        }
        return root;
    }

    public static ListNode generateList(Integer[] vals, int pos) {
        if(vals == null || vals.length == 0) {
            return null;
        }
        ListNode[] memo = new ListNode[vals.length];
        ListNode root = new ListNode(vals[0]);
        memo[0] = root;
        ListNode tail = root;
        for(int i=1; i<vals.length; i++) {
            tail.next = new ListNode(vals[i]);
            tail = tail.next;
            memo[i] = tail;
        }
        if(pos > -1) {
            tail.next = memo[pos];
        }
        return root;
    }
}
