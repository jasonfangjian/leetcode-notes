package notes1300;


/*
1312. Minimum Insertion Steps to Make a String Palindrome

Given a string s. In one step you can insert any character at any index of the string.

Return the minimum number of steps to make s palindrome.

A Palindrome String is one that reads the same backward as well as forward.



Example 1:

Input: s = "zzazz"
Output: 0
Explanation: The string "zzazz" is already palindrome we don't need any insertions.
Example 2:

Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".
Example 3:

Input: s = "leetcode"
Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".
Example 4:

Input: s = "g"
Output: 0
Example 5:

Input: s = "no"
Output: 1


Constraints:

1 <= s.length <= 500
All characters of s are lower case English letters.
 */
public class MinimumInsertionStepsToMakeAStringPalindrome {
    public static void main(String[] args){
        System.out.println(new MinimumInsertionStepsToMakeAStringPalindrome().minInsertions("leetcode"));
        System.out.println(new MinimumInsertionStepsToMakeAStringPalindrome().minInsertions("mbadm"));
    }

    public int minInsertions(String s) {
        int len  = s.length();
        char[] arr = s.toCharArray();
        int[][] dp = new int[len][len];

        for(int i = 0; i < len-1; i++){
            if(arr[i] != arr[i+1]){
                dp[i][i+1] = 1;
            }
        }

        for(int i = len -1; i >=0; i--){
            for(int j = i + 2; j < len; j++){
                if(arr[i] == arr[j]){
                    dp[i][j] = dp[i+1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i+1][j] + 1,Math.min(dp[i][j-1]+1,dp[i+1][j-1]+2));
                }
            }
        }

        return dp[0][len-1];
    }


}
