package notes1250;


import java.util.Arrays;

/*
1278. Palindrome Partitioning III

You are given a string s containing lowercase letters and an integer k. You need to :

First, change some characters of s to other lowercase English letters.
Then divide s into k non-empty disjoint substrings such that each substring is a palindrome.
Return the minimal number of characters that you need to change to divide the string.



Example 1:

Input: s = "abc", k = 2
Output: 1
Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
Example 2:

Input: s = "aabbc", k = 3
Output: 0
Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
Example 3:

Input: s = "leetcode", k = 8
Output: 0


Constraints:

1 <= k <= s.length <= 100.
s only contains lowercase English letters.
 */
public class PalindromePartitioningIII {
    public static void main(String[] args){
         System.out.println(new PalindromePartitioningIII().palindromePartition("abc",2));
        System.out.println(new PalindromePartitioningIII().palindromePartition("leetcode",2));
    }


    /*
    aabbc
    [x,x,x]
    [x,0,x]
    [x,0,]
    [x,1,x]
    [x,2,x]
    [x,2,x]
     */

    /*
    Todo: need more review
     */

    public int palindromePartition(String s, int k) {
        int len = s.length();

        char[] arr1 = s.toCharArray();

        int[][] temp = new int[len+1][len+1];

        for(int i = 0; i < len-1; i++){
            if(arr1[i] == arr1[i+1]){
                temp[i+1][i+2] = 0;
            } else {
                temp[i+1][i+2] = 1;
            }
        }

        for(int i = len; i>0; i--){
            for(int j = i+2; j <= len; j++){
                if(arr1[i-1] == arr1[j-1]){
                    temp[i][j] = temp[i+1][j-1];
                } else {
                    temp[i][j] = temp[i+1][j-1] + 1;
                }
            }
        }

        /////////////////////////////////////////////////////////////////
        int[][] dp = new int[len+1][k+1];

        for(int i  = 0; i <= len; i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        for(int i = 1; i <= len; i++){
            dp[i][1] = temp[1][i];
        }

        for(int i = 1; i <= len; i++){
            for(int j = 2 ; j <= i && j <= k; j++){
                for(int m = i-1; m>=j-1; m--){
                    dp[i][j] = Math.min(dp[i][j],dp[m][j-1] + temp[m+1][i]);
                }
            }
        }

        return dp[len][k];
    }

}
