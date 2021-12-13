package notes700;

import java.util.Arrays;

/*
727. Minimum Window Subsequence

Given strings s1 and s2, return the minimum contiguous substring part of s1, so that s2 is a subsequence of the part.

If there is no such window in s1 that covers all characters in s2, return the empty string "".
If there are multiple such minimum-length windows, return the one with the left-most starting index.



Example 1:

Input: s1 = "abcdebdde", s2 = "bde"
Output: "bcde"
Explanation:
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of s2 in the window must occur in order.
Example 2:

Input: s1 = "jmeqksfrsdcmsiwvaovztaqenprpvnbstl", s2 = "u"
Output: ""


Constraints:

1 <= s1.length <= 2 * 104
1 <= s2.length <= 100
s1 and s2 consist of lowercase English letters.
 */
public class MinimumWindowSubsequence {
    public static void main(String[] args){
        System.out.println(new MinimumWindowSubsequence().minWindow("abcdebdde","bde"));
    }

    /*
    "abcdebdde", s2 = "bde"
       b d e
     [x,x,x,x]
    a[x,x,x,x]
    b[x,1,x,x]
    c[x,1,x,x]
    d[x,1,1,x]
    e[x,1,1,1]
    b[x,5,1,1]
    d[x,5,5,1]
    d[x,5,5,1]
    e[x,5,5,5]
     */
    public String minWindow(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] dp = new int[len1+1][len2+1];

        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();

        for(int i = 0; i<= len1; i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }

        for(int i = 1; i<= len1; i++){
            if(arr1[i-1] == arr2[0]){
                dp[i][1] = i-1;
            } else {
                dp[i][1] = dp[i-1][1];
            }
        }

        for(int i =1; i <=len1; i++){
            for(int j = 2; j <= i && j<=len2; j++){
                char c1 = arr1[i-1];
                char c2 = arr2[j-1];

                if(c1 == c2){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        int res = Integer.MAX_VALUE;
        int lo = 0;
        int hi = 0;
        for(int i = 0; i <= len1; i++){
            if(dp[i][len2] != Integer.MAX_VALUE){
                if(res > i-dp[i][len2]){
                    res = i-dp[i][len2];
                    lo = dp[i][len2];
                    hi = i;
                }
            }
        }

        return s1.substring(lo,hi);
    }
}
