package notes450;


/*
474. Ones and Zeroes

You are given an array of binary strings strs and two integers m and n.

Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.

A set x is a subset of a set y if all elements of x are also elements of y.



Example 1:

Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
Output: 4
Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
{"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
Example 2:

Input: strs = ["10","0","1"], m = 1, n = 1
Output: 2
Explanation: The largest subset is {"0", "1"}, so the answer is 2.


Constraints:

1 <= strs.length <= 600
1 <= strs[i].length <= 100
strs[i] consists only of digits '0' and '1'.
1 <= m, n <= 100
 */
public class OnesAndZeroes {
    public static void main(String[] args){
        System.out.println(new OnesAndZeroes().findMaxForm(new String[]{"10","0001","111001","1","0"},5,3));
        System.out.println(new OnesAndZeroes().findMaxForm(new String[]{"10","0","1"},1,1));

    }


    /*

     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] transfer = new int[strs.length][2];
        int[][][] dp = new int[strs.length+1][m+1][n+1];

        for(int i =0; i < strs.length; i++){
            int count0 = count0(strs[i]);
            transfer[i][0] = count0;
            transfer[i][1] = strs[i].length()-count0;
        }
        int res = 0;
        if(transfer[0][0] <= m && transfer[0][1] <= n){
            dp[1][transfer[0][0]][transfer[0][1]] = 1;
            res = 1;
        }

        for(int i = 2; i <= strs.length; i++){
            int curM = transfer[i-1][0];
            int curN = transfer[i-1][1];
            for(int j = 0; j <= m; j++){
                for(int k = 0; k <= n; k++){
                    if(j - curM >=0 && k-curN >=0){
                        dp[i][j][k] = Math.max(dp[i-1][j-curM][k-curN] + 1,dp[i-1][j][k]);
                    } else {
                        dp[i][j][k] = dp[i-1][j][k];
                    }
                    res = Math.max(res,dp[i][j][k]);
                }
            }
        }

        return res;

    }

    public int count0(String str){
        int count = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '0')
                count++;
        }
        return  count;
    }
}
