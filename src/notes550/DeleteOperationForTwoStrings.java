package notes550;


/*
583. Delete Operation for Two Strings

Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.



Example 1:

Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Example 2:

Input: word1 = "leetcode", word2 = "etco"
Output: 4


Constraints:

1 <= word1.length, word2.length <= 500
word1 and word2 consist of only lowercase English letters.
 */
public class DeleteOperationForTwoStrings {
    public static void main(String[] args){
        System.out.println(new DeleteOperationForTwoStrings().minDistance("sea","eat"));
        System.out.println(new DeleteOperationForTwoStrings().minDistance("leetcode","etco"));
    }

    /*
    sea eat
        e a t
     [0,1,2,3]
    s[1,2,3,4]
    e[2,1,2,3]
    a[3,2,1,2]
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        char[] arr1 = word1.toCharArray();
        char[] arr2 = word2.toCharArray();

        int[][] dp = new int[len1+1][len2+1];

        for(int i = 0; i <= len2; i++){
            dp[0][i] = i;
        }

        for(int i = 0; i <= len1; i++){
            dp[i][0] = i;
        }

        for(int i = 1; i <=len1; i++){
            for(int j = 1; j <=len2; j++){
                char c1 = arr1[i-1];
                char c2 = arr2[j-1];
                if(c1 == c2){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j] + 1,Math.min(dp[i][j-1] + 1, dp[i-1][j-1] + 2));
                }
            }
        }

        return dp[len1][len2];
    }
}
