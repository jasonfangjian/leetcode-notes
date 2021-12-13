package notes0;

import util.ListNode;

public class SwapNodesInPairs {
    public static void main(String[] args){
        ListNode l1 = new ListNode();
        l1.val = 1;
        ListNode l2 = new ListNode();
        l2.val = 2;
        ListNode l3 = new ListNode();
        l3.val = 3;
        ListNode l4 = new ListNode();
        l4.val = 4;
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        System.out.println(new SwapNodesInPairs().swapPairs(l1));
    }

    /*
    1 2 3 4
    1 2 3
     */
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode res  = head.next;
        ListNode dummy1  = new ListNode();
        ListNode dummy2 = new ListNode();
        dummy1 = head;
        dummy2 = head.next;
        while (dummy2 != null){
            ListNode temp1 = dummy1.next.next;
            if(temp1 == null){
                dummy2.next = dummy1;
                dummy1.next = null;
                break;
            }
            ListNode temp2 = dummy2.next.next;
            dummy2.next = dummy1;
            if(temp2 == null){
                dummy1.next = temp1;
            } else {
                dummy1.next = temp2;
            }
            dummy1 = temp1;
            dummy2 = temp2;
        }
        return res;
    }

    public ListNode swapPairs2(ListNode head) {
        if(head == null || head.next == null)
            return head;
       ListNode dummy = new ListNode();
       dummy.next = head;
       ListNode l1 = head;
       ListNode l2 = head;
       while (l2 != null){
           dummy.next = l2;
           ListNode temp = l2.next;
           l2.next = l1;
           l1.next = temp;
           dummy = l1;
           if(temp == null)
               break;
           l1 = temp;
           l2 = temp.next;
       }
       return head.next;
    }
}
