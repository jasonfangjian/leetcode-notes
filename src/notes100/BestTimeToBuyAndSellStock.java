package notes100;

/*
121. Best Time to Buy and Sell Stock

You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day
in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.



Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.


Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 104
 */
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args){
        System.out.println(new BestTimeToBuyAndSellStock().maxProfit(new int[]{7,1,5,3,6,4}));
    }

//    public int maxProfit(int[] price) {
//        int[] left = new int[price.length];
//        int[] right = new int[price.length];
//        left[0] = price[0];
//        right[price.length-1] = price[price.length-1];
//        for(int i =1; i < price.length; i++){
//            left[i] = Math.min(left[i-1],price[i]);
//        }
//
//        for(int i = price.length-2;i>=0;i--){
//            right[i] = Math.max(right[i+1],price[i]);
//        }
//        int res= 0;
//        for(int i = 0; i < price.length;i++){
//            res =Math.max(res,right[i] - left[i]);
//        }
//        return  res;
//    }


    //better solution
    public int maxProfit(int[] price) {
        int min = price[0];

        int res = 0;

        for(int i = 1; i < price.length; i++){
            min = Math.min(min, price[i]);
            res = Math.max(res,price[i]-min);
        }
        return  res;
    }
}
