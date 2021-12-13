package notes150;

import util.ListNode;

public class IntersectionOfTwoLinkedLists {
    public static void main(String[] args){

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1 = 0;
        int len2 = 0;
        ListNode cur1 = headA;
        ListNode cur2 = headB;

        while (cur1!=null){
            len1++;
            cur1 = cur1.next;
        }
        while (cur2!=null){
            len2++;
            cur2 = cur2.next;
        }
        ListNode shortList;
        ListNode longList;
        if(len1 > len2){
            shortList = headB;
            longList = headA;
        } else {
            shortList = headA;
            longList = headB;
        }
        int curIndex = 0;
        while (curIndex < Math.abs(len1-len2)){
            curIndex++;
            longList = longList.next;
        }
        while (shortList!=null && longList!=null){
            if(shortList == longList)
                return shortList;
            shortList = shortList.next;
            longList = longList.next;
        }

        return null;

    }
}
