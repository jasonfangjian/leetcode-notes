package notes750;


import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

/*
Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.

A subsequence of a string is a new string generated from the original
string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".

Example 1:

Input: s = "abcde", words = ["a","bb","acd","ace"]
Output: 3
Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
Example 2:

Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
Output: 2


Constraints:

1 <= s.length <= 5 * 104
1 <= words.length <= 5000
1 <= words[i].length <= 50
s and words[i] consist of only lowercase English letters.
 */
public class NumberOfMatchingSubsequences {
    public static void main(String[] args){
        System.out.println(new NumberOfMatchingSubsequences().numMatchingSubseq("dsahjpjauf",new String[]{"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"}));
        System.out.println(new NumberOfMatchingSubsequences().numMatchingSubseq("abcde",new String[]{"a","bb","acd","ace"}));
    }

    public int numMatchingSubseq(String s, String[] words) {
        int[] check = new int[26];
        ArrayList[] indexs = new ArrayList[26];
        char[] arr = s.toCharArray();
        for(int i = 0; i < s.length(); i++){
            check[arr[i] - 'a'] ++;
            ArrayList list = indexs[arr[i] - 'a'];
            if(list == null)
                list = new ArrayList();
            list.add(i);
            indexs[arr[i] - 'a'] = list;
        }
        int count = 0;
        for(String str : words){
            if(isSub(str.toCharArray(),check,0,indexs))
                count++;
        }
        return count;
    }

    public boolean isSub(char[] arr, int[] check, int lower,  ArrayList[] indexs  ){
        if(arr.length == 0)
            return true;
        if(check[arr[0] - 'a'] == 0)
            return false;
        ArrayList list = indexs[arr[0] - 'a'];
        int bs = Collections.binarySearch(list,lower);
        Integer ceiling;
        if(bs < 0){
            bs = -(bs + 1);
        }
        if(bs >= list.size())
            return false;
        ceiling = (Integer) list.get(bs);
        return isSub(Arrays.copyOfRange(arr,1,arr.length),check,ceiling+1,indexs);
    }



}
