package notes500;


/*
516. Longest Palindromic Subsequence

Given a string s, find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another sequence by deleting some
or no elements without changing the order of the remaining elements.



Example 1:

Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".
Example 2:

Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".


Constraints:

1 <= s.length <= 1000
s consists only of lowercase English letters.
 */
public class LongestPalindromicSubsequence {
    public static void main(String[] args){
        System.out.println(new LongestPalindromicSubsequence().longestPalindromeSubseq("bbbab"));
    }


    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        char[] arr1 = s.toCharArray();
        for (int i =0; i<len; i++){
            if(i == len-1){
                dp[i][i] = 1;
            } else {
                dp[i][i] = 1;
                dp[i][i+1] = arr1[i] == arr1[i+1] ? 2 : 1;
            }
        }

        for(int i = len -2; i>=0; i--){
            for(int j = i + 2; j < len; j++){
                if(arr1[i] == arr1[j]){
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j],Math.max(dp[i][j-1],dp[i][j-1]));
                 }
            }
        }

        return dp[0][len-1];
    }

}
