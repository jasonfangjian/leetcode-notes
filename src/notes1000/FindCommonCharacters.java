package notes1000;
import java.util.*;

/*
1002. Find Common Characters

Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates). You may return the answer in any order.



Example 1:

Input: words = ["bella","label","roller"]
Output: ["e","l","l"]
Example 2:

Input: words = ["cool","lock","cook"]
Output: ["c","o"]


Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of lowercase English letters.
 */
public class FindCommonCharacters {
    public static void main(String[] args){

    }


    public List<String> commonChars(String[] words) {
        int[][] count = new int[words.length+1][26];
        List<String> res= new ArrayList<>();
        int index = 1;
        for(String str : words){
            for(char c : str.toCharArray()){
                count[index][c-'a']++;
            }
            if(index > 1){
                for(int i = 0; i <26; i++){
                    count[index][i] = Math.min(count[index-1][i], count[index][i]);
                }
            }

            index++;
        }

        for(int i = 0; i < 26; i++){
            for(int j = 0; j < count[words.length][i];j++){
                res.add(String.valueOf((char) (i + 'a')));
            }
        }

        return res;
    }

}
