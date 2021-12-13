package notes450;

/*
487. Max Consecutive Ones II

Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.



Example 1:

Input: nums = [1,0,1,1,0]
Output: 4
Explanation: Flip the first zero will get the maximum number of consecutive 1s. After flipping, the maximum number of consecutive 1s is 4.
Example 2:

Input: nums = [1,0,1,1,0,1]
Output: 4


Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.


Follow up: What if the input numbers come in one by one as an infinite stream?
In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
 */
public class MaxConsecutiveOnesII {
    public static void main(String[] args){
        System.out.println(new MaxConsecutiveOnesII().findMaxConsecutiveOnes(new int[]{1,0,1,1,0,1}));
    }

    /*

     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int[][] dp = new int[nums.length][2];
        int res = 0;
        dp[0][0] = nums[0] == 0 ? 0 : 1; // not swap
        dp[0][1] = nums[0] == 1 ? 0 : 1;//swapped
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == 1){
                dp[i][0] = dp[i-1][0]+1;
                dp[i][1] = Math.max(0, dp[i-1][1] + 1);
            } else {
                dp[i][0] = 0;
                dp[i][1] = dp[i-1][0] + 1;
            }

            res = Math.max(res,Math.max(dp[i][0],dp[i][1]));
        }

        return res;
    }
}
