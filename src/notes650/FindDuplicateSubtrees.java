package notes650;

import util.TreeNode;
import java.util.*;

/*
652. Find Duplicate Subtrees

Given the root of a binary tree, return all duplicate subtrees.

For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with the same node values.



Example 1:


Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]
Example 2:


Input: root = [2,1,1]
Output: [[1]]
Example 3:


Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]


Constraints:

The number of the nodes in the tree will be in the range [1, 10^4]
-200 <= Node.val <= 200
 */


/*
[0,0,0,0,null,null,0,null,null,null,0]
                0
              0   0
            0  n n 0
           n n    n 0

           0xx
           00xxx
           0x0x0
           0x0

 */

public class FindDuplicateSubtrees {
    public static void main(String[] args){}
    HashSet<String> set;
    HashMap<String,TreeNode> map;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        set = new HashSet<>();
        map = new HashMap<>();
        if(root == null)
            return new ArrayList<>();
        helper(root);
        return new ArrayList<>(map.values());
    }

    public String helper(TreeNode root){
        StringBuilder sb = new StringBuilder();
        sb.append(root.left == null ? "l" : helper(root.left));
        sb.append(root.val);
        sb.append(root.right == null ? "f" : helper(root.right));
        String str = sb.toString();
        if(set.contains(str)){
            map.put(str,root);
        }

        set.add(str);
        return str;
    }

}
