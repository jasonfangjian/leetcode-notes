package notes700;
/*
707. Design Linked List

Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node.
If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement the MyLinkedList class:

MyLinkedList() Initializes the MyLinkedList object.
int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
void addAtTail(int val) Append a node of value val as the last element of the linked list.
void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list. If index equals the length of the linked list, the node will be appended to the end of the linked list. If index is greater than the length, the node will not be inserted.
void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.


Example 1:

Input
["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
[[], [1], [3], [1, 2], [1], [1], [1]]
Output
[null, null, null, null, 2, null, 3]

Explanation
MyLinkedList myLinkedList = new MyLinkedList();
myLinkedList.addAtHead(1);
myLinkedList.addAtTail(3);
myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
myLinkedList.get(1);              // return 2
myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
myLinkedList.get(1);              // return 3


Constraints:

0 <= index, val <= 1000
Please do not use the built-in LinkedList library.
At most 2000 calls will be made to get, addAtHead, addAtTail, addAtIndex and deleteAtIndex.
 */
public class MyLinkedList {
    public class Node {
        int val;
        Node prev;
        Node next;
        public Node(){};
    }

    Node dummy_head;
    Node dummy_tail;
    int size;
    public MyLinkedList() {
        dummy_head = new Node();
        dummy_tail = new Node();
        dummy_head.next = dummy_tail;
        dummy_tail.prev = dummy_head;
        size = 0;
    }

    public int get(int index) {
        if(index >= size)
            return -1;
        Node cur = dummy_head;
        cur = cur.next;
        int curIndex = 0;
        while (curIndex<index){
            cur = cur.next;
            curIndex++;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        Node new_node = new Node();
        new_node.val = val;
        new_node.next = dummy_head.next;
        dummy_head.next.prev = new_node;
        new_node.prev = dummy_head;
        dummy_head.next = new_node;
        size++;
    }

    public void addAtTail(int val) {
        Node new_node = new Node();
        new_node.val = val;
        new_node.prev = dummy_tail.prev;
        dummy_tail.prev.next = new_node;
        new_node.next = dummy_tail;
        dummy_tail.prev = new_node;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if(index > size)
            return;
        int curIndex = 0;
        Node cur = dummy_head;
        while (curIndex < index){
            cur =cur.next;
            curIndex++;
        }
        Node new_node = new Node();
        new_node.val = val;
        new_node.next = cur.next;
        cur.next.prev = new_node;
        new_node.prev = cur;
        cur.next = new_node;
        size++;

    }

    public void deleteAtIndex(int index) {
        if(index >= size)
            return;
        int curIndex = 0;
        Node cur = dummy_head;
        while (curIndex < index){
            cur =cur.next;
            curIndex++;
        }
        cur.next = cur.next.next;
        cur.next.prev = cur;
        size--;
    }
}
