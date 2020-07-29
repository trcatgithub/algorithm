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
}
