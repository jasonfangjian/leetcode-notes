package notes950;

import util.TreeNode;


/*
968. Binary Tree Cameras

You are given the root of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.

Return the minimum number of cameras needed to monitor all nodes of the tree.



Example 1:


Input: root = [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.
Example 2:


Input: root = [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.


Constraints:

The number of nodes in the tree is in the range [1, 1000].
Node.val == 0
 */
public class BinaryTreeCameras {
    public static void main(String[] args){

    }
    int res = 0;
    public int minCameraCover(TreeNode root) {
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return 1;
        if(helper(root) == 0){
            res++;
        };
        return res;
    }

    public int helper(TreeNode root){
        if(root == null)
            return 0;
        if(root.left == null && root.right == null){
            return 0;
        }

        int left = helper(root.left);
        int right = helper(root.right);
        if(root.left != null && left == 0  || root.right != null && right == 0){
            res++;
            return 1;
        }

        if(left == 1 || right == 1)
            return 2;
        return 0;

    }
}
