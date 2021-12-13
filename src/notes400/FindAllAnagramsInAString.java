package notes400;
import java.util.*;

/*
438. Find All Anagrams in a String

Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.



Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".


Constraints:

1 <= s.length, p.length <= 3 * 104
s and p consist of lowercase English letters.
 */
public class FindAllAnagramsInAString {
    public static void main(String[] args) {

    }

    public List<Integer> findAnagrams(String s, String p) {
        if(p.length() > s.length())
            return new ArrayList<>();
        int[][] count = new int[s.length()+1][26];

        for(int i = 1 ; i <= s.length(); i++){
            for(int j = 0; j < 26; j++){
                if(s.charAt(i-1)-'a' == j){
                    count[i][j] = count[i-1][j]+1;
                } else {
                    count[i][j] = count[i-1][j];
                }
            }
        }
        int[] standard = new int[26];
        for(int i =0; i < p.length(); i++){
            standard[p.charAt(i) - 'a']++;
        }
        List<Integer> res = new ArrayList<>();
        for(int i = p.length()-1; i < s.length(); i++){
            int j = 0;
            for(; j  < 26; j++){
                if(count[i+1][j] - count[i - p.length() +1][j] != standard[j]){
                    break;
                }
            }
            if(j == 26)
                res.add(i - p.length() + 1);

        }

        return res;
    }
}
