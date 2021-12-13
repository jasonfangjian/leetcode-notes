package notes100;


/*
115. Distinct Subsequences

Given two strings s and t, return the number of distinct subsequences of s which equals t.

A string's subsequence is a new string formed from the original string by deleting some (can be none)
of the characters without disturbing the remaining characters' relative positions.
(i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).

It is guaranteed the answer fits on a 32-bit signed integer.



Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from S.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from S.
babgbag
babgbag
babgbag
babgbag
babgbag


Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.
 */
public class DistinctSubsequences {
    public static void main(String[] args){
        System.out.println(new DistinctSubsequences().numDistinct("babgbag","bag"));
        System.out.println(new DistinctSubsequences().numDistinct("rabbbit","rabbit"));
    }
/*
babgbag

bag

rabbbit
rabbit
    r a b b i t
[x,x,x,x,x,x,x]
r[x,1,0,0,0,0,0]
a[x,1,1,0,0,0,0]
b[x,1,1,1,0,0,0]
b[x,1,1,2,1,0,0]
b[x,1,1,3,3,0,x]
i[x,1,1,3,3,3,0]
t[x,1,1,3,3,3,3]


 */
    public int numDistinct(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        if(len2 > len1)
            return 0;
        int[][] dp = new int[len1+1][len2+1];
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        int count = 0;
        for(int i = 1 ; i<=len1; i++){
            if(arr1[i-1] == arr2[0]){
                count++;
                dp[i][1] = count;
            } else {
                dp[i][1] = dp[i-1][1];
            }
        }
        for(int i = 1; i <= len1; i++){
            for(int j = 2; j<=len2&&j <= i; j++){
                char a = arr1[i-1];
                char b = arr2[j-1];
                if(a == b){
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[len1][len2];
    }

}
