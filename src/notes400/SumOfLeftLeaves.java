package notes400;


import util.TreeNode;

/*
404. Sum of Left Leaves

Given the root of a binary tree, return the sum of all left leaves.



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: 24
Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
Example 2:

Input: root = [1]
Output: 0


Constraints:

The number of nodes in the tree is in the range [1, 1000].
-1000 <= Node.val <= 1000
 */
public class SumOfLeftLeaves {
    public static void main(String[] args){

    }

    int sum =0;
    public int sumOfLeftLeaves(TreeNode root) {
        helper(root.left,1);
        helper(root.right,2);
        return sum;
    }

    public void helper(TreeNode root, int direction){
        if(root == null)
            return;
        if(root.left == null && root.right == null && direction == 1){
            sum += root.val;
        }
        helper(root.left,1);
        helper(root.right,2);
    }


}
