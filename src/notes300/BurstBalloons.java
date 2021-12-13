package notes300;


/*
312. Burst Balloons

You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums.
You are asked to burst all the balloons.

If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1
goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.

Return the maximum coins you can collect by bursting the balloons wisely.



Example 1:

Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
Example 2:

Input: nums = [1,5]
Output: 10


Constraints:

n == nums.length
1 <= n <= 500
0 <= nums[i] <= 100
 */
public class BurstBalloons {
    public static void main(String[] args){
        System.out.println(new BurstBalloons().maxCoins(new int[]{3,1,5,8}));
    }


    /*
    Todo: need to review
     */
    public int maxCoins(int[] nums) {
        int[] extend = new int[nums.length+2];
        extend[0] = 1;
        extend[extend.length-1] = 1;
        for(int i = 0 ; i< nums.length; i++){
            extend[i+1] = nums[i];
        }

        int[][] dp = new int[nums.length+2][nums.length+2];


        for(int len =1; len <= nums.length; len ++){
            for(int i = 1; i + len-1<=nums.length; i++){
                int j = i + len -1;
                for(int k = i; k <= j; k++){
                    dp[i][j] = Math.max(dp[i][j],dp[i][k-1]+extend[i-1]*extend[k]*extend[j+1]+dp[k+1][j]);

                }
            }
        }
        return dp[1][dp.length-2];
    }


}
