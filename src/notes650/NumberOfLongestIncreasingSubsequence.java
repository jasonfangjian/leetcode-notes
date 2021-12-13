package notes650;

/*
673. Number of Longest Increasing Subsequence

Given an integer array nums, return the number of longest increasing subsequences.

Notice that the sequence has to be strictly increasing.



Example 1:

Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:

Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.



Constraints:

1 <= nums.length <= 2000
-106 <= nums[i] <= 106
 */
public class NumberOfLongestIncreasingSubsequence {
    public static void main(String[] args){

    }

    public int findNumberOfLIS(int[] nums) {

        int[][] dp  = new int[nums.length+1][2];
        dp[1][0] = 1;
        dp[1][1] = 1;
        int max = 1;
        for(int i = 2; i <= nums.length; i++){
            dp[i][0] = 1;
            dp[i][1] = 1;
            for(int j = 1; j < i; j++){
                if(nums[i-1] > nums[j-1]){
                    if(dp[j][0] + 1 == dp[i][0]){
                        dp[i][1] = dp[i][1] + dp[j][1];
                    } else if(dp[j][0] + 1 > dp[i][0]){
                        dp[i][1] = dp[j][1];
                        dp[i][0] = dp[j][0] + 1;
                    }
                }
            }
            max = Math.max(dp[i][0],max);
        }
        int count = 0;
        for(int i = 1; i <= nums.length; i++){
            if(dp[i][0] == max){
                count += dp[i][1];
            }
        }

        return count;
    }
}
