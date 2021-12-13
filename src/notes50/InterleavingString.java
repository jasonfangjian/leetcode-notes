package notes50;


/*
97. Interleaving String

Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.



Example 1:


Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Example 3:

Input: s1 = "", s2 = "", s3 = ""
Output: true


Constraints:

0 <= s1.length, s2.length <= 100
0 <= s3.length <= 200
s1, s2, and s3 consist of lowercase English letters.


Follow up: Could you solve it using only O(s2.length) additional memory space?
 */
public class InterleavingString {
    public static void main(String[] args){
        System.out.println(new InterleavingString().isInterleave("aabcc","dbbca","aadbbcbcac"));
    }


    /*
    aabcc
    dbbca

    aadbbcbcac
      0 1 2 3 4 5
    0[T,F,F,F,F,F]
    1[T,F,F,F,F,F]
    2[T,T,x,x,x,x]
    3[F,x,x,x,x,x]
    4[F,x,x,x,x,x]
    5[F,x,x,x,x,x]
     a a
    [1,1] -> [0,1] F + arr1[1] a==a T or [1,0] T + arr2[1] d==a F -> F
     a b
    [1,2] -> [0,2] F + arr1[1] F or [1,1] F + arr2[2] -> F
     a d
    [2,1] -> [1,1] + arr1[2] or [2,0] + arr2[1] d d - > T
    a b
    [2,2] -> [2,1] + arr2[]
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();

        boolean[][] dp = new boolean[len1+1][len2+1];

        dp[0][0] = true;
        for(int i = 1; i <= len2; i++){
            dp[0][i] = s2.substring(0,i).equals(s3.substring(0,i));
        }

        for(int i = 1; i<= len1; i++){
            dp[i][0] = s1.substring(0,i).equals(s3.substring(0,i));
        }

        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();

        for(int i = 1; i <= len1; i++){
            for(int j  =1; j<= len2; j++){
                dp[i][j]  = (dp[i-1][j] && arr1[i-1] == s3.charAt(i+j-1)) || (dp[i][j-1] && arr2[j-1] == s3.charAt(i+j-1));
            }
        }

        return dp[len1][len2];


    }


}
