package notes1350;

import util.TreeNode;

import java.util.*;


/*
1382. Balance a Binary Search Tree

Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.

A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.



Example 1:


Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.
Example 2:


Input: root = [2,1,3]
Output: [2,1,3]


Constraints:

The number of nodes in the tree is in the range [1, 104].
1 <= Node.val <= 105
 */
public class BalanceABinarySearchTree {
    public static void main(String[] args){

    }

    public TreeNode balanceBST(TreeNode root) {
        ArrayList<TreeNode> al = new ArrayList<>();
        inorder(root, al);
        return BalancedBST(0, al.size()-1, al);
    }
    public void inorder(TreeNode node, ArrayList<TreeNode> al){
        if(node == null) return;
        inorder(node.left, al);
        al.add(node);
        inorder(node.right, al);
    }
    public TreeNode BalancedBST(int left, int right, ArrayList<TreeNode> al){
        if(right<left) return null;
        int x = (int)((left+right)/2);
        TreeNode node = new TreeNode(al.get(x).val);
        node.left = BalancedBST(left, x-1, al);
        node.right = BalancedBST(x+1, right, al);
        return node;
    }
}
