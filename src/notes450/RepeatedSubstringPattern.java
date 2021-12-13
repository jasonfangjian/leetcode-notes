package notes450;

/*
459. Repeated Substring Pattern

Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.



Example 1:

Input: s = "abab"
Output: true
Explanation: It is the substring "ab" twice.
Example 2:

Input: s = "aba"
Output: false
Example 3:

Input: s = "abcabcabcabc"
Output: true
Explanation: It is the substring "abc" four times or the substring "abcabc" twice.


Constraints:

1 <= s.length <= 104
s consists of lowercase English letters.
 */
public class RepeatedSubstringPattern {
    public static void main(String[] args){

    }

    public boolean repeatedSubstringPattern(String s) {
        int[] next = new int[s.length()];
        getNext(next,s);
        if(next[s.length()-1] != -1 && s.length()%(s.length() - (next[s.length() - 1] + 1)) == 0)
            return true;
        return false;
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
