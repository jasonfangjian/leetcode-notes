package notes1000;


import java.util.ArrayList;
import java.util.List;

/*
1092. Shortest Common Supersequence

Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.
If there are multiple valid strings, return any of them.

A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.



Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation:
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
Example 2:

Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"


Constraints:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.
 */
public class ShortestCommonSupersequence {
    public static void main(String[] args){
        System.out.println(new ShortestCommonSupersequence().shortestCommonSupersequence("ade","cdf"));
        System.out.println(new ShortestCommonSupersequence().shortestCommonSupersequence("abac","cab"));
    }


    /*
    dp[len1+1][len2+1][3] -> cur pre-row pre-col
    acdef
    ade
    cdf
    [0,1,2,3]
    [1,2,3,4]
    [2,3,3,4]
    [3,4,4,5]
    a e
    [1,1] ->
    a   d
    [1,2] -> [0,2],[1,1] -> 2,2-> + 1 = 3
    a   f
    [1,3] -> [0,3],[1,2] -> 3 + 1 = 4;
    d   c
    [2,1] -> [1,1],[2,0] -> 2 , 2 + 1 = 3;
    d   d
    [2,2] -> [1,1] -> 2 + 1 = 3;
    d f
    [2,3] -> [1,3],[2,2] -> 3,3+1 = 4;
    e c
    [3,1] -> [2,1],[3,0] -> 3,3 + 1 = 4
    e d
    [3,2] -> [2,2],[3,1] -> 3,4 + 1 = 4;
    e f
    [3,3] -> [2,3],[3,2] -> 4 + 1 = 5;
     */
    public String shortestCommonSupersequence(String str1, String str2) {
        int len1 = str1.length();
        int len2  = str2.length();
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        Character[][][] dp = new Character[len1+1][len2+1][4];

        for(int i = 1; i <= len2; i++){
            dp[0][i][0] = arr2[i-1];
            dp[0][i][1] = '0';
            dp[0][i][2] = (char)(i-1 + '0');
            dp[0][i][3] = (char)(i + '0');
        }
        for(int j = 1; j <= len1; j++){
            dp[j][0][0] = arr1[j-1];
            dp[j][0][1] = (char)(j-1 + '0');
            dp[j][0][2] = '0';
            dp[j][0][3] = (char)(j + '0');
        }

        dp[0][0][3] = '0';
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j<= len2; j++){
                if(arr1[i-1] == arr2[j-1]){
                    dp[i][j][0] = arr1[i-1];
                    dp[i][j][1] = (char)(i-1 + '0');
                    dp[i][j][2] = (char)(j-1 + '0');
                    dp[i][j][3] = (char)(dp[i-1][j-1][3] + 1);
                } else {
                    if(dp[i][j-1][3] - '0' < dp[i-1][j][3] - '0'){
                        dp[i][j][0] = arr2[j-1];
                        dp[i][j][1] = (char)(i + '0');
                        dp[i][j][2] = (char)(j-1 + '0');
                        dp[i][j][3] = (char)(dp[i][j-1][3] + 1);
                    } else {
                        dp[i][j][0] = arr1[i-1];
                        dp[i][j][1] = (char)(i-1 + '0');
                        dp[i][j][2] = (char)(j + '0');
                        dp[i][j][3] = (char)(dp[i-1][j][3] + 1);
                    }
                }
            }
        }

        int i = len1;
        int j = len2;
        StringBuilder sb = new StringBuilder();
        while (dp[i][j][1] != null && dp[i][j][2] != null){
            sb.append(dp[i][j][0]);
            int newI = dp[i][j][1] - '0';
            int newJ = dp[i][j][2] - '0';
            i = newI;
            j = newJ;
        }
        return sb.reverse().toString();
    }
}
