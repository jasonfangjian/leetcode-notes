package notes500;


import java.util.Locale;

/*
541. Reverse String II

Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.

If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.



Example 1:

Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Example 2:

Input: s = "abcd", k = 2
Output: "bacd"


Constraints:

1 <= s.length <= 104
s consists of only lowercase English letters.
1 <= k <= 104
 */
public class ReverseStringII {

    public static void main(String[] args){

    }

    /*
    0 1 2 3 4 5 6
     */
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for(int i = 0; i < s.length(); i = i + 2*k){
            int lo = i;
            int hi = s.length()-i > k ?  i + k -1 : s.length()-1;
            swap(chars,lo,hi);
        }
        return String.valueOf(chars);
    }

    public void swap(char[] chars,int i, int j){
        while (i < j){
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
    }
}
