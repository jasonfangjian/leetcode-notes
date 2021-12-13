package notes1500;

import java.util.HashMap;

/*
1525. Number of Good Ways to Split a String

You are given a string s, a split is called good if you can split s into 2 non-empty strings p
and q where its concatenation is equal to s and the number of distinct letters in p and q are the same.

Return the number of good splits you can make in s.

Example 1:

Input: s = "aacaba"
Output: 2
Explanation: There are 5 ways to split "aacaba" and 2 of them are good.
("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.
Example 2:

Input: s = "abcd"
Output: 1
Explanation: Split the string as follows ("ab", "cd").
Example 3:

Input: s = "aaaaa"
Output: 4
Explanation: All possible splits are good.
Example 4:

Input: s = "acbadbaada"
Output: 2


Constraints:

s contains only lowercase English letters.
1 <= s.length <= 10^5
 */
public class NumberOfGoodWaysToSplitAString {
    public static void main(String[] args){
        System.out.println(new NumberOfGoodWaysToSplitAString().numSplits("acbadbaada"));
    }


    public int numSplits(String s) {
        HashMap<Character,Integer> map = new HashMap<>();

        char[] arr = s.toCharArray();



        for(char c : arr){
            map.put(c,map.getOrDefault(c,0) + 1);
        }

        int count = map.size();

        if(count == 1)
            return map.get(arr[0])-1;
        HashMap<Character,Integer> split = new HashMap<>();

        int res = 0;

        for(int i =0; i < arr.length-1;i++){
            map.put(arr[i],map.get(arr[i])-1);
            split.put(arr[i],split.getOrDefault(arr[i],0) + 1);
            if(map.get(arr[i]) == 0)
                map.remove(arr[i]);
            if(map.size() == split.size())
                res++;
        }

        return res;
    }





}
