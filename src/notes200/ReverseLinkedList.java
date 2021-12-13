package notes200;

import util.ListNode;

import java.util.List;

public class ReverseLinkedList {
    public static void main(String[] args){

    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        return pre;
    }
}
