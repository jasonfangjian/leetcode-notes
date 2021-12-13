package notes500;


import util.TreeNode;

import java.util.*;

/*
513. Find Bottom Left Tree Value

Given the root of a binary tree, return the leftmost value in the last row of the tree.



Example 1:


Input: root = [2,1,3]
Output: 1
Example 2:


Input: root = [1,2,3,4,null,5,6,null,null,7]
Output: 7


Constraints:

The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1
 */
public class FindBottomLeftTreeValue {
    public static void main(String[] args){

    }


    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode leftMost = root;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i =0; i < size; i++){
                TreeNode cur = queue.poll();
                if(i == 0){
                    leftMost = cur;
                }
                if(cur.left != null)
                    queue.offer(cur.left);
                if(cur.right != null)
                    queue.offer(cur.right);
            }
        }
        return leftMost.val;
    }


}
