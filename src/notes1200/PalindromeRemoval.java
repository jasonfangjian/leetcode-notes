package notes1200;


/*
1246. Palindrome Removal

Given an integer array arr, in one move you can select a palindromic subarray arr[i], arr[i+1], ..., arr[j] where i <= j,
and remove that subarray from the given array.
Note that after removing a subarray,
the elements on the left and on the right of that subarray move to fill the gap left by the removal.

Return the minimum number of moves needed to remove all numbers from the array.



Example 1:

Input: arr = [1,2]
Output: 2
Example 2:

Input: arr = [1,3,4,1,5]
Output: 3
Explanation: Remove [4] then remove [1,3,1] then remove [5].


Constraints:

1 <= arr.length <= 100
1 <= arr[i] <= 20
 */
public class PalindromeRemoval {
    public static void main(String[] args){
        System.out.println(new PalindromeRemoval().minimumMoves(new int[]{17,6,5,18,17,4,18,8,16,8,12,1,5,14,14,6,17,18,2,19,11,15,8,18,7,8,20,2,10,3,18,17,18,18,8,9,20,3,16,19,6,9,8,8,16,19,13,8,5,20}));
    }

    /*
        1,2,1,1,3,1
     [x,x,x,x,x,x,x]
    1[x,1,2,x,x,x,x]
    2[x,x,1,2,x,x,x]
    1[x,x,x,1,1,x,x]
    1[x,x,x,x,1,2,x]
    3[x,x,x,x,x,1,2]
    1[x,x,x,x,x,x,1]
     */
    public int minimumMoves(int[] arr) {
        int len = arr.length;
        int[][] dp = new int[len+1][len+1];
        for(int i = 1; i < len; i++){
            dp[i][i] = 1;
            if(arr[i-1] != arr[i]){
                dp[i][i+1] = 2;
            } else {
                dp[i][i+1] = 1;
            }
        }
        dp[len][len] = 1;

       for(int i = len -2; i > 0; i--){
           for(int j = i+2; j <=len; j++){
               if(arr[i-1] == arr[j-1]){
                   int c1 = dp[i+1][j-1];
                   for(int k = i; k < j; k++){
                       c1  = Math.min(dp[i][k] + dp[k+1][j],c1);
                   }
                   dp[i][j] = c1;
               } else {
                   int c2 = Math.min(dp[i+1][j],dp[i][j-1]) + 1;
                   for(int k = i; k < j; k++){
                       c2  = Math.min(dp[i][k] + dp[k+1][j],c2);
                   }
                   dp[i][j] = c2;
               }
           }
       }

        return dp[1][len];
    }

}

