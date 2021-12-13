package notes350;

/*
383. Ransom Note

Given two stings ransomNote and magazine, return true if ransomNote can be constructed from magazine and false otherwise.

Each letter in magazine can only be used once in ransomNote.



Example 1:

Input: ransomNote = "a", magazine = "b"
Output: false
Example 2:

Input: ransomNote = "aa", magazine = "ab"
Output: false
Example 3:

Input: ransomNote = "aa", magazine = "aab"
Output: true


Constraints:

1 <= ransomNote.length, magazine.length <= 105
ransomNote and magazine consist of lowercase English letters.
 */
public class RansomNote {
    public static void main(String[] args){

    }
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];

        for(int i =0; i < magazine.length(); i++){
            count[magazine.charAt(i) - 'a']++;
        }

        for(int i = 0; i < ransomNote.length(); i++){
            count[ransomNote.charAt(i) - 'a']--;
        }

        for(int i =0; i < 26; i++){
            if(count[i] < 0)
                return false;
        }

        return true;
    }
}
