package notes950;


import java.util.Arrays;

/*
956. Tallest Billboard

You are installing a billboard and want it to have the largest height. The billboard will have two steel supports,
one on each side. Each steel support must be an equal height.

You are given a collection of rods that can be welded together. For example, if you have rods of lengths 1, 2, and 3,
you can weld them together to make a support of length 6.

Return the largest possible height of your billboard installation. If you cannot support the billboard, return 0.



Example 1:

Input: rods = [1,2,3,6]
Output: 6
Explanation: We have two disjoint subsets {1,2,3} and {6}, which have the same sum = 6.
Example 2:

Input: rods = [1,2,3,4,5,6]
Output: 10
Explanation: We have two disjoint subsets {2,3,5} and {4,6}, which have the same sum = 10.
Example 3:

Input: rods = [1,2]
Output: 0
Explanation: The billboard cannot be supported, so we return 0.


Constraints:

1 <= rods.length <= 20
1 <= rods[i] <= 1000
sum(rods[i]) <= 5000
 */
public class TallestBillboard {
    public static void main(String[] args){
        System.out.println(new TallestBillboard().tallestBillboard(new int[]{1,2,3,4,5,6}));
    }

    /*
    [1,2,3,4,5,6]

     */
    public int tallestBillboard(int[] rods) {
        int sum = Arrays.stream(rods).sum();
        int cases = 2*sum + 1;

        int[][] dp = new int[rods.length+1][cases+1];

        /*
         12
        -3 -2 -1 0 1 2 3
        1. hi = 1; -> j =
        Todo: need more review
         */
        for(int i =0; i < dp.length; i++){
            Arrays.fill(dp[i],-1);
        }
        dp[1][rods[0]+sum] = rods[0];
        dp[1][sum - rods[0]] = 0;
        dp[1][sum] = 0;
        for(int i = 2; i<= rods.length; i++){
            int hi = rods[i-1];
            for(int j = 0; j <= cases; j++){
                dp[i][j] = Math.max(dp[i-1][j],Math.max(j+hi <= cases ? dp[i-1][j+hi]:-1, j - hi >= 0 ? dp[i-1][j-hi] == -1 ? -1 : dp[i-1][j-hi]+hi : -1));
            }
        }
        return dp[rods.length][sum];
    }
}
