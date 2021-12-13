package notes450;


import java.util.Arrays;

/*
494. Target Sum

You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums
and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.



Example 1:

Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
Example 2:

Input: nums = [1], target = 1
Output: 1


Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000
 */
public class TargetSum {
    public static void main(String[] args){
        System.out.println(new TargetSum().findTargetSumWays(new int[]{0,0,0,0,0,0,0,0,1},1));
        System.out.println(new TargetSum().findTargetSumWays(new int[]{1,1,1,1,1},3));
    }


    /*
        if sum = 4;
        sum*2 + 1 = 9;
        0  1   2  3  4 5 6 7 8
        -4 -3 -2 -1  0 1 2 3 4
         */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        int len = nums.length;

        if(Math.abs(target) > sum)
            return 0;
        int zeroIndex = sum;

        int[][] dp = new int[len][sum*2+1];
        dp[0][zeroIndex - nums[0]] += 1;
        dp[0][zeroIndex + nums[0]] += 1;

        for(int i = 1; i < len; i++){
            int cur = nums[i];
            for(int j = 0; j < sum*2+1; j++){
                if(dp[i-1][j] == 0)
                    continue;
                if(cur == 0){
                    dp[i][j] = dp[i-1][j]*2;
                } else {
                    dp[i][j-cur] += dp[i-1][j];
                    dp[i][j+cur] += dp[i-1][j];
                }

            }
        }

        return dp[len-1][zeroIndex+target];
    }
}
