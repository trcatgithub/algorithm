package list;

import java.util.ArrayList;
import java.util.List;

//请判断一个链表是否为回文链表。
//
//示例 1:
//
//输入: 1->2
//输出: false
//示例 2:
//
//输入: 1->2->2->1
//输出: true
//进阶：
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
//
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/palindrome-linked-list
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution8 {

    // 1,快慢指针寻找链表中点
    // 2,翻转中点之后的链表
    // 3,翻转后链表 与 原链表对比
    // 1ms/41MB
    public boolean isPalindrome(ListNode head) {
        // 找链表中点
        ListNode one = head;
        ListNode two = head;
        while(one != null && two != null) {
            one = one.next;
            two = two.next == null ? null : two.next.next;
        }

        // 翻转链表
        ListNode reverseHead = null;
        while(one != null) {
            ListNode next = one.next;
            if(reverseHead == null) {
                reverseHead = one;
                reverseHead.next = null;
            }else {
                one.next = reverseHead;
                reverseHead = one;
            }
            one = next;
        }

        // 比较链表
        ListNode tail = head;
        while(tail != null && reverseHead != null) {
            if(tail.val != reverseHead.val) {
                return false;
            }
            tail = tail.next;
            reverseHead = reverseHead.next;
        }

        return true;
    }

//    // 基于ArrayList
//    // 3ms/41.9MB
//    public boolean isPalindrome(ListNode head) {
//        List<ListNode> memo = new ArrayList<>();
//        ListNode tail = head;
//        while(tail != null) {
//            memo.add(tail);
//            tail = tail.next;
//        }
//        int len = (memo.size()&1) == 1 ? memo.size()/2+1 : memo.size()/2;
//        for(int i=0; i<len; i++) {
//            if(memo.get(i).val != memo.get(memo.size()-1-i).val && memo.get(i) != memo.get(memo.size()-1-i)) {
//                return false;
//            }
//        }
//        return true;
//    }

    public static void main(String[] args) {
        ListNode head = ListUtils.generateList(new Integer[]{1,2});
        System.out.println(new Solution8().isPalindrome(head) == false);
        head = ListUtils.generateList(new Integer[]{1,2,2,1});
        System.out.println(new Solution8().isPalindrome(head) == true);
        head = ListUtils.generateList(new Integer[]{1,2,1});
        System.out.println(new Solution8().isPalindrome(head) == true);
        head = ListUtils.generateList(new Integer[]{});
        System.out.println(new Solution8().isPalindrome(head) == true);
    }
}
