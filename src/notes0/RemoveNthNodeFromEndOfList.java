package notes0;

import util.ListNode;

public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args){

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode low = new ListNode();
        ListNode fast = new ListNode();
        low.next = head;
        fast.next = head;
        int curIndex = 0;
        while (curIndex <= n){
            curIndex++;
            fast = fast.next;
        }

        while (fast.next != null){
            fast = fast.next;
            low = low.next;
        }
        low.next = low.next.next;
        return head;
    }
}
