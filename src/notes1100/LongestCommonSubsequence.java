package notes1100;


import java.util.Locale;

/*
1143. Longest Common Subsequence

Given two strings text1 and text2, return the length of their longest common subsequence.
If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted
without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.



Example 1:

Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.


Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
 */
public class LongestCommonSubsequence {
    public static void main(String[] args){
//        System.out.println(new LongestCommonSubsequence().longestCommonSubsequence("acdf","bcdef"));
        System.out.println(new LongestCommonSubsequence().longestCommonSubsequence("yfobudkbgpqtadspgvinafefktctinyvgf",
                "kpurgrihwbkjsrybmnqrgnubufebatwberi"));
//        System.out.println(new LongestCommonSubsequence().longestCommonSubsequence("bl","yby"));
//        System.out.println(new LongestCommonSubsequence().longestCommonSubsequence(
//                "bsbininm",
//                "jmjkbkjkv"));
                }


    /*
    acdf
    bcdef
    [0,0,0,0,0]
    [0,1,1,1,1]
    [0,1,2,2,2]
    [0,1,2,2,3]
     */
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1.length()  > text2.length()){
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }

        char[] arr1 = text1.toCharArray();
        char[] arr2 = text2.toCharArray();

        int len1  = text1.length();
        int len2 = text2.length();

        int[][] dp = new int[len1+1][len2+1];

        for(int i = 0 ; i < len1; i++){
           for(int j = 0; j < len2; j++){
               if(arr1[i] == arr2[j]){
                   dp[i+1][j+1] = dp[i][j] + 1;
               } else {
                   dp[i+1][j+1] = Math.max(dp[i+1][j],dp[i][j+1]);
               }
           }
        }


        return dp[len1][len2];
    }

}
