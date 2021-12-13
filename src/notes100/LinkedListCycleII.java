package notes100;

import util.ListNode;

public class LinkedListCycleII {
    public static void main(String[] args){

    }

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next;
            if(fast == null)
                return null;
            fast = fast.next;
            if(fast == slow){
                ListNode l1 = fast;
                ListNode l2 = head;
                while (l1!=l2){
                    l1 = l1.next;
                    l2 = l2.next;
                }
                return l1;
            }
        }
        return null;
    }

}
