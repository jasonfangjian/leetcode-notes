package notes1350;


/*
1397. Find All Good Strings

Given the strings s1 and s2 of size n and the string evil, return the number of good strings.

A good string has size n, it is alphabetically greater than or equal to s1,
it is alphabetically smaller than or equal to s2, and it does not contain the string evil as a substring.
Since the answer can be a huge number, return this modulo 109 + 7.

Example 1:

Input: n = 2, s1 = "aa", s2 = "da", evil = "b"
Output: 51
Explanation: There are 25 good strings starting with 'a': "aa","ac","ad",...,"az".
Then there are 25 good strings starting with 'c': "ca","cc","cd",...,"cz" and
finally there is one good string starting with 'd': "da".
Example 2:

Input: n = 8, s1 = "leetcode", s2 = "leetgoes", evil = "leet"
Output: 0
Explanation: All strings greater than or equal to s1 and smaller than or equal to s2 start with the prefix "leet",
therefore, there is not any good string.
Example 3:

Input: n = 2, s1 = "gx", s2 = "gz", evil = "x"
Output: 2


Constraints:

s1.length == n
s2.length == n
s1 <= s2
1 <= n <= 500
1 <= evil.length <= 50
All strings consist of lowercase English letters.
 */
public class FindAllGoodString {
    public static void main(String[] args){
        System.out.println(new FindAllGoodString().findGoodStrings(2,"aa","da","b"));
    }


    int mod = (int)(1e9+7);
    long[][][] dp;
    int n, el;
    char[] ev;
    int[][] dfa;
    public int findGoodStrings(int n, String s1, String s2, String evil) {
        if (s1.startsWith(evil) && s2.startsWith(evil)) return 0;
        this.dfa = KMP(evil);
        this.n = n;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        c1[c1.length - 1]--;
        ev = evil.toCharArray();
        this.el = evil.length();
        return (int) (((solve(c2) - solve(c1)) % mod) + mod) % mod;
    }

    int[][] KMP(String pat) {
        int m = pat.length(), r = 26;
        int[][] dfa = new int[r][m];
        dfa[pat.charAt(0) - 'a'][0] = 1;
        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < r; c++) {
                dfa[c][j] = dfa[c][x];
            }
            dfa[pat.charAt(j)- 'a'][j] = j + 1;
            x = dfa[pat.charAt(j)- 'a'][x];
        }
        return dfa;
    }

    long solve(char[] c) {
        dp = new long[n][el][2];
        long res = call(c, 0, 0, 0);
        return res;
    }

    long call(char[] c, int pos, int k, int f) {
        if (pos == n) return 1;
        if (dp[pos][k][f] != 0) return dp[pos][k][f];
        long res = 0;
        int lmt = f == 0 ? c[pos] - 'a' : 25;
        for (int nextc = 0; nextc <= lmt; nextc++) {
            int nk = dfa[nextc][k];
            if (nk < el) {
                res += call(c, pos + 1, nk, (f == 0 && nextc == lmt) ? 0 : 1) % mod;
            }
        }
        return dp[pos][k][f] = res;
    }

}
