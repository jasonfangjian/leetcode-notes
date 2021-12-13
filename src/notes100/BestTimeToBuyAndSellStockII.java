package notes100;


/*
122. Best Time to Buy and Sell Stock II

You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
However, you can buy it then immediately sell it on the same day.

Find and return the maximum profit you can achieve.



Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.


Constraints:

1 <= prices.length <= 3 * 104
0 <= prices[i] <= 104
 */
public class BestTimeToBuyAndSellStockII {
    public static void main(String[] args){
        System.out.println(new BestTimeToBuyAndSellStockII().maxProfit(new int[]{1,2,3,4,5}));
    }


    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length+1][2];

        dp[1][0] = - prices[0];
        dp[1][1] = 0;
        for(int i = 2; i <= prices.length; i++){
            dp[i][0] = Math.max(dp[i-1][1] - prices[i-1],dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][0]  + prices[i-1],dp[i-1][1]);//
        }
        return Math.max(dp[prices.length][0],dp[prices.length][1]);
    }
}
