package notes800;


/*
813. Largest Sum of Averages

You are given an integer array nums and an integer k. You can partition the array into at most k non-empty adjacent subarrays.
 The score of a partition is the sum of the averages of each subarray.

Note that the partition must use every integer in nums, and that the score is not necessarily an integer.

Return the maximum score you can achieve of all the possible partitions. Answers within 10-6 of the actual answer will be accepted.



Example 1:

Input: nums = [9,1,2,3,9], k = 3
Output: 20.00000
Explanation:
The best choice is to partition nums into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
We could have also partitioned nums into [9, 1], [2], [3, 9], for example.
That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
Example 2:

Input: nums = [1,2,3,4,5,6,7], k = 4
Output: 20.50000


Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 104
1 <= k <= nums.length
 */
public class LargestSumOfAverages {
    public static void main(String[] args){
        System.out.println(new LargestSumOfAverages().largestSumOfAverages(new int[]{1,2,3,4,5,6,7},4));
    }


    /*

     */
    public double largestSumOfAverages(int[] nums, int k) {

        int len = nums.length;
        int[] prefix = new int[nums.length+1];
        for(int i = 1; i <= len; i++){
            prefix[i] = prefix[i-1] + nums[i-1];
        }
        double[][] dp = new double[len+1][k+1];

        for(int i = 1; i <= len; i++){
            dp[i][1] =prefix[i]/(double) i;
        }
        //  1 2 3 4 5 6 7 8
        for(int i = 2; i <= len; i++){
            for(int j = 2; j<=i && j <= k; j++){
                for(int m = i-1; m>=j-1; m--){
                    dp[i][j] = Math.max(dp[m][j-1] + (double) (prefix[i] - prefix[m])/(double)(i-m) ,dp[i][j]);
                }
            }
        }
        return dp[len][k];
    }
}
