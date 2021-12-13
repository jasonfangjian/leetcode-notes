package notes2000;

import util.ListNode;


/*
2074. Reverse Nodes in Even Length Groups

You are given the head of a linked list.

The nodes in the linked list are sequentially assigned to non-empty groups whose lengths form the sequence of the natural numbers (1, 2, 3, 4, ...). The length of a group is the number of nodes assigned to it. In other words,

The 1st node is assigned to the first group.
The 2nd and the 3rd nodes are assigned to the second group.
The 4th, 5th, and 6th nodes are assigned to the third group, and so on.
Note that the length of the last group may be less than or equal to 1 + the length of the second to last group.

Reverse the nodes in each group with an even length, and return the head of the modified linked list.



Example 1:


Input: head = [5,2,6,3,9,1,7,3,8,4]
Output: [5,6,2,3,9,1,4,8,3,7]
Explanation:
- The length of the first group is 1, which is odd, hence no reversal occurrs.
- The length of the second group is 2, which is even, hence the nodes are reversed.
- The length of the third group is 3, which is odd, hence no reversal occurrs.
- The length of the last group is 4, which is even, hence the nodes are reversed.
Example 2:


Input: head = [1,1,0,6]
Output: [1,0,1,6]
Explanation:
- The length of the first group is 1. No reversal occurrs.
- The length of the second group is 2. The nodes are reversed.
- The length of the last group is 1. No reversal occurrs.
Example 3:


Input: head = [2,1]
Output: [2,1]
Explanation:
- The length of the first group is 1. No reversal occurrs.
- The length of the last group is 1. No reversal occurrs.
Example 4:

Input: head = [8]
Output: [8]
Explanation: There is only one group whose length is 1. No reversal occurrs.


Constraints:

The number of nodes in the list is in the range [1, 105].
0 <= Node.val <= 105
 */
public class ReverseNodesInEvenLengthGroups {
    public static void main(String[] args){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3  = new ListNode(3);
        ListNode n4  = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6  = new ListNode(6);
        ListNode n7 = new ListNode(7);
        ListNode n8 = new ListNode(8);
        ListNode n9 = new ListNode(9);
        ListNode n10 = new ListNode(10);
        ListNode n11 = new ListNode(11);
        ListNode n12 = new ListNode(12);
        ListNode n13 = new ListNode(13);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        n9.next = n10;
        n10.next = n11;
        n11.next = n12;
        //n12.next = n13;
        ListNode res = new ReverseNodesInEvenLengthGroups().reverseEvenLengthGroups(n1);

        while (res != null){
            System.out.println(res.val);
            res = res.next;
        }
    }


    public ListNode reverseEvenLengthGroups(ListNode head) {
        if(head == null || head.next == null || head.next.next == null)
            return head;
        ListNode startIndex = head;
        ListNode endIndex = head;
        int group = 1;
        ListNode res = head;
        ListNode lastCase = head;
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            if (group % 2 == 0) {
                int count = 0;
                startIndex = cur;
                while (cur.next != null && count < group - 1) {
                    cur = cur.next;
                    count++;
                }
                if(cur.next == null && count%2 == 0){
                    return res;
                }
                endIndex = cur;
                cur = cur.next;
                prev.next = helper(startIndex, endIndex);
                group++;

            } else {
                int count = 0;
                ListNode s1 = cur;
                while (cur.next != null && count < group - 1) {
                    cur = cur.next;
                    count++;
                }
                if (cur.next == null && count % 2 == 1) {
                    startIndex.next = helper(s1, cur);
                    return res;
                } else {
                    prev = cur;
                    cur = cur.next;
                    group++;
                }
            }
        }

        return res;
    }



    public ListNode helper(ListNode start, ListNode end){

        ListNode prev = end.next;
        ListNode curr = start;
        ListNode teg = end.next;
        while(curr != teg) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;

    }



}
