package notes50;
import java.util.*;
/*
90. Subsets II

Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example 2:

Input: nums = [0]
Output: [[],[0]]


Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10


 */
public class SubsetsII {
    public static void main(String[] args){

    }

    List<List<Integer>> res;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        res = new ArrayList<>();

        helper(new ArrayList<>(),0,nums);
        return res;
    }

    public void helper(List<Integer> path, int index, int[] nums){
        res.add(new ArrayList<>(path));
        for(int i = index; i< nums.length;i++){
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            helper(path,i+1,nums);
            path.remove(path.size()-1);
        }
    }
}
