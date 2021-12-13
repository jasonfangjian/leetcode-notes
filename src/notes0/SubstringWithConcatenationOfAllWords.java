package notes0;


import util.TrieNode;

import java.util.*;

/*
30. Substring with Concatenation of All Words

You are given a string s and an array of strings words of the same length.
Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once,
in any order, and without any intervening characters.

You can return the answer in any order.

Example 1:

Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
Example 2:

Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []
Example 3:

Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]


Constraints:

1 <= s.length <= 104
s consists of lower-case English letters.
1 <= words.length <= 5000
1 <= words[i].length <= 30
words[i] consists of lower-case English letters.
 */
public class SubstringWithConcatenationOfAllWords {
    public static void main(String[] args) {
        System.out.println(new SubstringWithConcatenationOfAllWords().findSubstring("barfoofoobarthefoobarman",new String[]{"bar","foo","the"}));
        System.out.println(new SubstringWithConcatenationOfAllWords().findSubstring("a",new String[]{"a"}));
    }

    /*
    "wordgoodgoodgoodbestword"
    ["word","good","best","good"]

    "aaaaaaaaaaaaaa"
    ["aa","aa"]
     */

    public List<Integer> findSubstring(String s, String[] words) {
        int len = words[0].length();

        HashMap<String,Integer> dict = new HashMap<>();
        for(String word : words){
            dict.put(word,dict.getOrDefault(word,0) + 1);
        }

        List<Integer> res = new ArrayList<>();

        for(int i = 0; i <= s.length() - len; i++){
            HashMap<String,Integer> used = new HashMap<>();
            String str = s.substring(i,i+len);
            int cur = i;
            if(dict.containsKey(str)){
                cur = cur + len;
                used.put(str,used.getOrDefault(str,0) + 1);
                if(cur+len> s.length()){
                    if(cur - i == words.length*len){
                        res.add(i);
                        used.clear();
                    }
                    break;
                }
                String foloow = s.substring(cur,cur+len);
                while(cur + len <= s.length()&&dict.containsKey(foloow) && used.getOrDefault(foloow,0) < dict.get(foloow)){
                    cur = cur + len;
                    used.put(foloow,used.getOrDefault(foloow,0) + 1);
                    if(cur+len> s.length()){
                        break;
                    }
                    foloow = s.substring(cur,cur+len);
                }
            }
            if(cur - i == words.length*len){
                res.add(i);
                used.clear();
            }
        }
        return res;
    }

}
