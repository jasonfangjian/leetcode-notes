package notes400;


import java.util.HashMap;

/*
416. Partition Equal Subset Sum

Given a non-empty array nums containing only positive integers,
find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.



Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.


Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100
 */
public class PartitionEqualSubsetSum {
    public static void main(String[] args){
        /*
        [100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97]
         */
        System.out.println(new PartitionEqualSubsetSum().canPartition(new int[]{1,5,11,5}));
         System.out.println(new PartitionEqualSubsetSum().canPartition(new int[]{100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97}));
        System.out.println(new PartitionEqualSubsetSum().canPartition(new int[]{1,5,1,5}));
    }

//dfs with memo, not so good
    Boolean[][] memo;
    public boolean canPartition(int[] nums) {
        int sum = 0;

        for(int i : nums){
            sum+=i;
        }

        if(sum%2 == 1)
            return false;

        int target = sum/2;
        memo = new Boolean[target+1][nums.length+1];
        return helper(nums,0,target,0);
    }



    public boolean helper(int[] nums, int cur, int target, int start){
        if(cur > target)
            return false;
        if(memo[cur][start] != null)
            return memo[cur][start];
        if(target == cur)
            return true;

        for(int i = start; i < nums.length; i++){
                if(helper(nums,cur + nums[i],target,i+1)){
                    memo[cur][start] = true;
                    return true;
                }
        }
        memo[cur][start] = false;
        return false;
    }


}
