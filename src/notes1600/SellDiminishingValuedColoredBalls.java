package notes1600;


import java.util.Arrays;

/*
1648. Sell Diminishing-Valued Colored Balls

You have an inventory of different colored balls, and there is a customer that wants orders balls of any color.

The customer weirdly values the colored balls. Each colored ball's value is the number of balls of that color you currently have in your inventory. For example, if you own 6 yellow balls, the customer would pay 6 for the first yellow ball. After the transaction, there are only 5 yellow balls left, so the next yellow ball is then valued at 5 (i.e., the value of the balls decreases as you sell more to the customer).

You are given an integer array, inventory, where inventory[i] represents the number of balls of the ith color that you initially own. You are also given an integer orders, which represents the total number of balls that the customer wants. You can sell the balls in any order.

Return the maximum total value that you can attain after selling orders colored balls. As the answer may be too large, return it modulo 109 + 7.



Example 1:


Input: inventory = [2,5], orders = 4
Output: 14
Explanation: Sell the 1st color 1 time (2) and the 2nd color 3 times (5 + 4 + 3).
The maximum total value is 2 + 5 + 4 + 3 = 14.
Example 2:

Input: inventory = [3,5], orders = 6
Output: 19
Explanation: Sell the 1st color 2 times (3 + 2) and the 2nd color 4 times (5 + 4 + 3 + 2).
The maximum total value is 3 + 2 + 5 + 4 + 3 + 2 = 19.


Constraints:

1 <= inventory.length <= 105
1 <= inventory[i] <= 109
1 <= orders <= min(sum(inventory[i]), 109)
 */
public class SellDiminishingValuedColoredBalls {
    public static void main(String[] args){
        System.out.println(new SellDiminishingValuedColoredBalls().maxProfit(new int[]{1000000000},1000000000));
    }

    public int maxProfit(int[] inventory, int orders) {
        long lo = 1L;
        long hi = (long)Arrays.stream(inventory).max().getAsInt();
        long mod = 1000000007L;
        long k = -1L;

        while(lo<=hi){
            long mid = lo + (hi - lo)/2L;

            long count = 0;
            for(int i : inventory){
                if(i >= mid){
                    count += (long)i - mid + 1L;
                }
            }

            if(count > orders){
                lo = mid + 1;
            } else if(count < orders) {
                hi = mid -1;
            } else {
                k = mid;
                break;
            }
        }

        if(k == -1L){
            k = lo;
        }

        int sum = 0;
        int count = 0;
        for(int i : inventory){
            if(i >= k){
                count = count + i -(int)k + 1;
                sum += helper(k,i);
                sum = sum%(int)mod;
            }
        }

        long rest = 0L;

        rest = (long)(orders - count) * (long)(k-1);

        rest = rest%mod;

        return (int)(rest+sum)%1000000007;
    }


    public int helper(long s, int end){
        long mod = 1000000007L;
        long e = (long) end;

        long res = (s + e)*(e -s + 1L)/2;

        return (int)(res%mod);
    }

}
