package notes1200;


/*
1216. Valid Palindrome III

Given a string s and an integer k, return true if s is a k-palindrome.

A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.



Example 1:

Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.
Example 2:

Input: s = "abbababa", k = 1
Output: true


Constraints:

1 <= s.length <= 1000
s consists of only lowercase English letters.
1 <= k <= s.length
 */
public class ValidPalindromeIII {
    public static void main(String[] args) {
        System.out.println(new ValidPalindromeIII().isValidPalindrome("abcdeca",2));
        System.out.println(new ValidPalindromeIII().isValidPalindrome("abbababa",1));
    }

    /*
    abcdeca
        a b c d e c a
     [x,x,x,x,x,x,x,x]
    a[x,0,x,x,x,x,x,x]
    b[x,x,0,x,x,x,x,x]
    c[x,x,x,0,x,x,x,x]
    d[x,x,x,x,0,x,x,x]
    e[x,x,x,x,x,0,x,x]
    c[x,x,x,x,x,x,0,x]
    a[x,x,x,x,x,x,x,0]
     */

    public boolean isValidPalindrome(String s, int k) {
        int len = s.length();

        char[] arr1 = s.toCharArray();

        int[][] dp = new int[len+1][len+1];

        for(int i = 0; i < len-1; i++){
            if(arr1[i] == arr1[i+1]){
                dp[i+1][i+2] = 0;
            } else {
                dp[i+1][i+2] = 1;
            }
        }

        for(int i = len; i>0; i--){
            for(int j = i+2; j <= len; j++){
                if(arr1[i-1] == arr1[j-1]){
                    dp[i][j] = dp[i+1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i+1][j]+1,Math.min(dp[i][j-1] + 1,dp[i+1][j-1] + 2));
                }
            }
        }

        return dp[1][len] <= k;
    }

}
