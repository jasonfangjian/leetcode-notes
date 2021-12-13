package notes850;

import java.util.Arrays;

/*
879. Profitable Schemes

There is a group of n members, and a list of various crimes they could commit.
The ith crime generates a profit[i] and requires group[i] members to participate in it.
If a member participates in one crime, that member can't participate in another crime.

Let's call a profitable scheme any subset of these crimes that generates at least minProfit profit,
and the total number of members participating in that subset of crimes is at most n.

Return the number of schemes that can be chosen. Since the answer may be very large, return it modulo 109 + 7.



Example 1:

Input: n = 5, minProfit = 3, group = [2,2], profit = [2,3]
Output: 2
Explanation: To make a profit of at least 3, the group could either commit crimes 0 and 1, or just crime 1.
In total, there are 2 schemes.
Example 2:

Input: n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
Output: 7
Explanation: To make a profit of at least 5, the group could commit any crimes, as long as they commit one.
There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).


Constraints:

1 <= n <= 100
0 <= minProfit <= 100
1 <= group.length <= 100
1 <= group[i] <= 100
profit.length == group.length
0 <= profit[i] <= 100
 */
public class ProfitableSchemes {
    public static void main(String[] args){
        System.out.println(new ProfitableSchemes().profitableSchemes(5,3,new int[]{2,2},new int[]{2,3}));
        System.out.println(new ProfitableSchemes().profitableSchemes(6,3,new int[]{2,2,2},new int[]{2,3,3}));
        System.out.println(new ProfitableSchemes().profitableSchemes(10,5,new int[]{2,3,5},new int[]{6,7,8}));
        System.out.println(new ProfitableSchemes().profitableSchemes(64,0,new int[]{80,40},new int[]{88,88}));
    }


    /*
    1
    []
     */
    public int profitableSchemesMemory(int n, int minProfit, int[] group, int[] profit) {
        int mod = 1000000007;
        int len = group.length;
        int maxP = Arrays.stream(profit).sum()+1;
        int[][][] dp = new int[len+1][n+1][maxP+1];

        for(int i = 1; i <= len; i++){
            if(group[i-1] <=n && profit[i-1] == 0){
                dp[0][n][0] = 1;
            }
        }


        for(int i = 1; i <=len; i++){
            int curN = group[i-1];
            int curP= profit[i-1];
            for(int j = 1; j<=n; j++){
                for(int k = 1; k <= maxP; k++){
                    if(j - curN >=0 && k -curP >=0){
                        dp[i][j][k] += (dp[i-1][j][k] + dp[i-1][j-curN][k-curP])%mod;
                    } else
                    dp[i][j][k] = dp[i-1][j][k];
                }
            }
            if(curN<=n && curP<=maxP){
                dp[i][curN][curP]++;
            }
        }

        int res= 0;

        for(int i = minProfit; i <= maxP; i++){
            for(int j = 1; j<= n; j++){
                res = (res + dp[len][j][i])%mod;
            }
        }

        return minProfit == 0 ? res+1:res;
    }


    /*
    Todo:need more review;
     */
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int M=(int)1e9+7;
        int len=group.length;
        int[][] dp=new int[G+1][P+1];
        for(int i=0;i<=G;i++) dp[i][0]=1;

        for(int i=0;i<len;i++){
            for(int j=G;j>=group[i];j--){
                for(int p=0;p<=P;p++){
                    int ind= Math.min(p + profit[i], P);
                    dp[j][ind]=(dp[j][ind]+dp[j-group[i]][p])%M;
                }
            }
        }
        return dp[G][P];
    }



}
