package notes650;

import java.util.*;

/*
696. Count Binary Substrings

Give a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's,
and all the 0's and all the 1's in these substrings are grouped consecutively.

Substrings that occur multiple times are counted the number of times they occur.

Example 1:

Input: s = "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's:
"0011", "01", "1100", "10", "0011", and "01".
Notice that some of these substrings repeat and are counted the number of times they occur.
Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.

Example 2:

Input: s = "10101"
Output: 4
Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.


Constraints:

1 <= s.length <= 105
s[i] is either '0' or '1'.
 */

import java.util.ArrayList;
import java.util.HashSet;

public class CountBinarySubstrings {
    public static void main(String[] args){
        System.out.println(new CountBinarySubstrings().countBinarySubstrings("10101"));
    }

    public int countBinarySubstrings(String s) {
        if(s.length() == 1)
            return 0;
        int lo = 0;
        int flag = -1;
        s= s + "x";
        char[] arr = s.toCharArray();
        List<String> group = new ArrayList<>();
        for(int i = 1; i < s.length(); i++){
            if(i == s.length() - 1)
                group.add(s.substring(lo,i));
            if(arr[i] == arr[lo] && flag > 0){
                group.add(s.substring(lo,i));
                lo = flag;
                flag = -1;
                i = lo;
                continue;
            }

            if(arr[i] != arr[lo] && flag < 0)
                flag = i;
        }

        System.out.println(group);
        int sum = 0;
        for(int i = 0 ; i< group.size(); i++){
            sum  += countMin(group.get(i));
        }
        return sum;
    }


    public int countMin(String s){
        int count = 0;

        char[] arr = s.toCharArray();
        char pre = arr[0];

        for(int i = 0; i< arr.length; i++){
            if(arr[i] != pre)
                break;
            count++;
        }

        return Math.min(count,s.length() - count);

    }

}
