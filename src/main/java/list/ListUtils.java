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
}
