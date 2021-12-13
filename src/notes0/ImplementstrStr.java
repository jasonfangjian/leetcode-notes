package notes0;


/*
28. Implement strStr()

Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().



Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Example 3:

Input: haystack = "", needle = ""
Output: 0


Constraints:

0 <= haystack.length, needle.length <= 5 * 104
 */
public class ImplementstrStr {
    public static void main(String[] args){

    }

        public int strStr(String haystack, String needle) {
            if(needle.length() == 0)
                return 0;
            int[] next = new int[needle.length()];
            getNext(next,needle);
            int j = -1;
            for(int i =0; i < haystack.length(); i++){
                while (j >=0 && haystack.charAt(i) != needle.charAt(j+1)){
                    j = next[j];
                }
                if(needle.charAt(j+1) == haystack.charAt(i)){
                    j++;
                }

                if(j == needle.length()-1){
                    return i - needle.length() +1;
                }
            }
            return -1;
        }

        public void getNext(int[] next,String str){
            char[] chars = str.toCharArray();
            int j = -1;
            next[0] = j;
            for(int i = 1; i < next.length; i++){
                while (j >=0 && chars[j+1] != chars[i]){
                    j = next[j];
                }
                if(chars[j+1] == chars[i]){
                    j++;
                }

                next[i] = j;
            }
        }
}
