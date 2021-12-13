package notes50;
import java.util.*;
/*
76. Minimum Window Substring

Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

A substring is a contiguous sequence of characters within the string.



Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.


Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.


Follow up: Could you find an algorithm that runs in O(m + n) time?
 */
public class MinimumWindowSubstring {
    public static void main(String[] args){

    }

    public String minWindow(String s, String t) {
        HashMap<Character,Integer> map = new HashMap<>();
        char[] arr = t.toCharArray();

        for(int i =0; i < arr.length; i++){
            map.put(arr[i],map.getOrDefault(arr[i],0) + 1);
        }
        HashMap<Character,Integer> count = new HashMap<>();
        HashMap<Character,Integer> findHi = new HashMap<>(map);
        char[] sa = s.toCharArray();
        int hi = -1;
        int lo = 0;
        int rl = 0;
        int rh = 0;
        for(int i =0; i < sa.length; i++){
            count.put(sa[i],count.getOrDefault(sa[i],0) + 1);
            if(findHi.containsKey(sa[i])){
                findHi.put(sa[i],findHi.get(sa[i]) -1);
                if(findHi.get(sa[i]) == 0)
                    findHi.remove(sa[i]);
            }

            if(findHi.size() == 0){
                hi = i;
                break;
            }
        }
        if(hi == -1)
            return "";
        rh = hi;
        while(!map.containsKey(sa[lo]) || count.get(sa[lo]) - 1 >= map.get(sa[lo])){
            lo++;
            count.put(sa[lo-1],count.get(sa[lo-1]) - 1);
        }
        rl = lo;
        for(int i = hi+1; i < sa.length; i++){
            count.put(sa[i],count.getOrDefault(sa[i],0) + 1);
            while(!map.containsKey(sa[lo]) || count.get(sa[lo]) - 1 >= map.get(sa[lo])){
                lo++;
                count.put(sa[lo-1],count.get(sa[lo-1]) - 1);
            }
            if(i - lo < rh - rl){
                rh = i;
                rl = lo;
            }
        }

        return s.substring(rl,rh+1);
    }


}
