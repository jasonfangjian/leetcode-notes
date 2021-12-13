package notes400;


import java.util.Arrays;

/*
410. Split Array Largest Sum

Given an array nums which consists of non-negative integers and an integer m,
you can split the array into m non-empty continuous subarrays.

Write an algorithm to minimize the largest sum among these m subarrays.



Example 1:

Input: nums = [7,2,5,10,8], m = 2
Output: 18
Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
Example 2:

Input: nums = [1,2,3,4,5], m = 2
Output: 9
Example 3:

Input: nums = [1,4,4], m = 3
Output: 4


Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 106
1 <= m <= min(50, nums.length)
 */
public class SplitArrayLargestSum {
    public static void main(String[] args){
        System.out.println(new SplitArrayLargestSum().splitArray(new int[]{1,2,3,4,5},2));
    }


    public int splitArray(int[] nums, int m) {
        int len = nums.length;
        int[][] dp = new int[len+1][m+1];
        int[] prefix = new int[len+1];
        for(int i = 0; i <= len; i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        for(int i =1; i <= len; i++){
            prefix[i] = prefix[i-1] + nums[i-1];
        }
        for (int i = 1; i <= len; i++){
            dp[i][1] = prefix[i];
        }

        for(int i = 2; i <=len; i++){
            for(int j = 2; j <=i&& j<=m; j++){
                for(int k = i-1; k>=j-1;k--){
                    dp[i][j] = Math.min(dp[i][j],Math.max(dp[k][j-1],prefix[i] - prefix[k]));
                }
            }
        }

        return dp[len][m];

    }
}
