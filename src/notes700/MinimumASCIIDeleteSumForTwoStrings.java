package notes700;


/*
712. Minimum ASCII Delete Sum for Two Strings

Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.



Example 1:

Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
Example 2:

Input: s1 = "delete", s2 = "leet"
Output: 403
Explanation: Deleting "dee" from "delete" to turn the string into "let",
adds 100[d] + 101[e] + 101[e] to the sum.
Deleting "e" from "leet" adds 101[e] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.


Constraints:

1 <= s1.length, s2.length <= 1000
s1 and s2 consist of lowercase English letters.
 */
public class MinimumASCIIDeleteSumForTwoStrings {
    public static void main(String[] args){
        System.out.println(new MinimumASCIIDeleteSumForTwoStrings().minimumDeleteSum("sea","eat"));
        System.out.println(new MinimumASCIIDeleteSumForTwoStrings().minimumDeleteSum("delete","leet"));
    }



    public int minimumDeleteSum(String s1, String s2) {
        int len1  = s1.length();
        int len2 = s2.length();

        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();

        int[][] dp = new int[len1+1][len2+1];


        for(int j = 1; j <= len2; j++){
            dp[0][j] = dp[0][j-1] + arr2[j-1];
        }

        for(int i = 1; i <= len1; i++){
            dp[i][0] = dp[i-1][0] + arr1[i-1];
        }

        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                char c1 = arr1[i-1];
                char c2 = arr2[j-1];

                if(c1 == c2){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j] + arr1[i-1],Math.min(dp[i][j-1] + arr2[j-1],dp[i-1][j-1] + arr1[i-1] + arr2[j-1]));
                }
            }
        }

        return dp[len1][len2];
    }
}
