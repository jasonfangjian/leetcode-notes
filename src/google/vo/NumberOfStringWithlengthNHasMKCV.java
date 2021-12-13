package google.vo;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
4th
DP
1) https://stackoverflow.com/questions/62816143/how-many-words-of-length-n-have-at-most-k-consecutive-vowels
2) 如果字符串不能出现某些denylist的词怎么办
只要求说了思路没写代码，参考后面这道题的思路，分析了下复杂度就没了。 https://leetcode.com/problems/find-all-good-strings/
 */
public class NumberOfStringWithlengthNHasMKCV {
    public static void main(String[] args){
        System.out.println(new NumberOfStringWithlengthNHasMKCV().solution(3,3));
    }

    static int power(int x, int y, int p)
    {
        int res = 1;
        x = x % p;

        if (x == 0)
            return 0;

        while (y > 0)
        {
            if ((y & 1) != 0)
                res = (res * x) % p;

            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }

    public int solution(int N, int K){
        int i, j;
        int MOD = 1000000007;

        // Array dp to store number of ways
        int[][] dp = new int[N + 1][K + 1] ;

        int sum = 1;
        for(i = 1; i <= N; i++)
        {

            // dp[i][0] = (dp[i-1][0]+dp[i-1][1]..dp[i-1][k])*21
            dp[i][0] = sum * 21;
            dp[i][0] %= MOD;

            // Now setting sum to be dp[i][0]
            sum = dp[i][0];

            for(j = 1; j <= K; j++)
            {

                // If j>i, no ways are possible to create
                // a string with length i and vowel j
                if (j > i)
                    dp[i][j] = 0;

                else if (j == i)
                {

                    // If j = i all the character should
                    // be vowel
                    dp[i][j] = power(5, i, MOD);
                }
                else
                {

                    // dp[i][j] relation with dp[i-1][j-1]
                    dp[i][j] = dp[i - 1][j - 1] * 5;
                }

                dp[i][j] %= MOD;

                // Adding dp[i][j] in the sum
                sum += dp[i][j];
                sum %= MOD;
            }
        }
        return sum;
    }

}
