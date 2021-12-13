package notes650;


import java.util.*;

/*
664. Strange Printer

There is a strange printer with the following two special properties:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at
any place and will cover the original existing characters.
Given a string s, return the minimum number of turns the printer needed to print it.



Example 1:

Input: s = "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".
Example 2:

Input: s = "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string,
which will cover the existing character 'a'.


Constraints:

1 <= s.length <= 100
s consists of lowercase English letters.
 */
public class StrangePrinter {
    public static void main(String[] args){
        System.out.println(new StrangePrinter().strangePrinter("aaabbbaaaaabbbb"));
        System.out.println(new StrangePrinter().strangePrinter("dddccbdbababaddcbcaabdbdddcccddbbaabddb"));
    }


    /*
    bacdadacbdbcabdabdbcdcbacbdcabca
    bca -> 3
    abc -> 3
    abca -> 3
    cab -> 3
    cabc -> 3
    cabca -> 4
    dca -> 3
    dcab -> 4
    dcabc-> 4
    dcabca -> 5
    bdc -> 3
    bdca -> 4
    bdcab -> 4
    bdcabc -> 5
    bdcabca -> 6
    cdcbacbdcabca
    cbd -> 3
    cbdc -> 3
    cbdca -> 4
    cbdcab -> 5
    cbdcabc -> 5
    cbdcabca -> 6
    acb -> 3
    acbd -> 4
    acbdc -> 4
    acbdca -> 4
    acbdcab -> 5
    acbdcabc -> 6
    acbdcabca -> 6
    cdcbacbdcabca
    bac -> 3
    bacb -> 3
    bacbd -> 4
    bacbdc -> 5
    bacbdca -> 5
    bacbdcab -> 5
    bacbdcabc -> 6
    bacbdcabca -> 7
    cdcbacbdcabca
    cba -> 3
    cbac -> 3
    cbacb -> 4
    cbacbd -> 5
    cbacbdc -> 5
    cbacbdca -> 5
    cbacbdcab -> 6
    cbacbdcabc -> 6
    cbacbdcabca -> 7
    cdcbacbdcabca
    dcb -> 3
    dcba -> 4
    dcbac -> 4
    dcbacb -> 5
    dcbacbd -> 5
    dcbacbdc -> 6
    dcbacbdca -> 7
    dcbacbdcab -> 7
    dcbacbdcabc -> 7
    dcbacbdcabca -> 8
    cdcbacbdcabca
    cdc - > 2
    cdcb -> 3
    cdcba -> 4
    cdcbac ->4


     */
    public int strangePrinter(String s) {
        List<Character> list = new ArrayList<>();

        for(int i = 0; i < s.length(); i++){
            char cur = s.charAt(i);
            list.add(cur);
            while (i < s.length() && s.charAt(i) == cur){
                i++;
            }
            i = i-1;
        }
        int[][] dp = new int[list.size()][list.size()];

        for(int i = 0; i < list.size(); i++){
            dp[i][i] = 1;
        }
        for(int i = 0; i < list.size()-1;i++){
            dp[i][i+1] = 2;
        }

        for(int i = list.size()-3; i >=0;i--){
            for(int j = i + 2; j< list.size(); j++){
                dp[i][j] = Math.min(dp[i+1][j],dp[i][j-1]) + 1;
                for(int k = i; k < j; k++){
                    if(list.get(k) == list.get(j)){
                        if(k == i){
                            dp[i][j] = Math.min(dp[i][j],dp[i][j-1]);
                        } else {
                            dp[i][j] = Math.min(dp[i][j],dp[i][k] + dp[k+1][j-1]);
                        }
                    }
                }
            }
        }

        return dp[0][list.size()-1];
    }

}
