package notes950;


import java.util.ArrayList;
import java.util.List;

/*
983. Minimum Cost For Tickets

You have planned some train traveling one year in advance.
The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.

Train tickets are sold in three different ways:

a 1-day pass is sold for costs[0] dollars,
a 7-day pass is sold for costs[1] dollars, and
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.

For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
Return the minimum number of dollars you need to travel every day in the given list of days.



Example 1:

Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total, you spent $11 and covered all the days of your travel.
Example 2:

Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
Output: 17
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
In total, you spent $17 and covered all the days of your travel.


Constraints:

1 <= days.length <= 365
1 <= days[i] <= 365
days is in strictly increasing order.
costs.length == 3
1 <= costs[i] <= 1000

 */
public class MinimumCostForTickets {
    public static void main(String[] args){
        System.out.println(new MinimumCostForTickets().mincostTickets(new int[]{1,4,6,7,8,20},new int[]{2,7,15}));
        System.out.println(new MinimumCostForTickets().mincostTickets(new int[]{1,2,3,4,5,6,7,8,9,10,30,31},new int[]{2,7,15}));
    }

    /*
    [2,7,15]
    [1,4,6,7,8,20]
    [0,2,2,2,4,4,6,7,9,9,9,9,9,9,9,9,9,9,9,11]
      [1,2,3,4,5,6,7,8,9,10,30,31]
    [0,7,7,7,7,9,11,13,14]
     */
    public int mincostTickets(int[] days, int[] costs) {
        int dp[] = new int[366];
        dp[0] = 0;
        int tar = 0;
        for(int i = 1; i < 366;i++) {
            if(tar == days.length || i != days[tar]){
                dp[i] = dp[i-1];
            } else {
                int t1 = days[tar] - 1;
                int t2 = days[tar] -7;
                int t3 = days[tar] -30;

                int c1 = dp[t1] + costs[0];
                int c2 = t2 < 0 ? costs[1] : dp[t2] + costs[1];
                int c3 = t3 < 0 ? costs[2] : dp[t3] + costs[2];

                dp[i] = Math.min(c1,Math.min(c2,c3));

                tar++;
            }

        }

        return dp[365];
    }
}
