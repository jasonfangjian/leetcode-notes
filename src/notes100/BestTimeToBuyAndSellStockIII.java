package notes100;


/*
123. Best Time to Buy and Sell Stock III

You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later,
as you are engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
Example 4:

Input: prices = [1]
Output: 0


Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 105
 */
public class BestTimeToBuyAndSellStockIII {
    public static void main(String[] args){
        System.out.println(new BestTimeToBuyAndSellStockIII().maxProfit(new int[]{3,3,5,0,0,3,1,4}));
    }

    /*
    s1: hold t1 -> - val and i-1 s1
    s2: sell t1 -> i-1 s1 + val  and i-1 s2
    s3: hold t2 -> i-1 s3 and i-1 s2 - val
    s4: sell t2 -> i-1 s4 and i-1 s3 + val
     */

    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length+1][4];

        dp[1][0] = -prices[0];
        dp[1][1] = 0;
        dp[1][2] = - prices[0];
        dp[1][3] = 0;
        for(int i =2 ; i<= prices.length; i++){
            dp[i][0] = Math.max(dp[i-1][0],-prices[i-1]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0] + prices[i-1]);
            dp[i][2]  =Math.max(dp[i-1][2],dp[i-1][1] - prices[i-1]);
            dp[i][3] = Math.max(dp[i-1][3],dp[i-1][2] + prices[i-1]);
        }
        int res = 0;
        for(int i =0; i < 4; i++){
            res = Math.max(res,dp[prices.length][i]);
        }

        return res;
    }

}
