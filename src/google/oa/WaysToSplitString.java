package google.oa;


/*
Question 2:
Given a string S, we can split S into 2 strings: S1 and S2. Return the number of ways
S can be split such that the number of unique characters between S1 and S2 are the same.

Example 1:

Input: "aaaa"
Output: 3
Explanation: we can get a - aaa, aa - aa, aaa- a
Example 2:

Input: "bac"
Output: 0
Example 3:

Input: "ababa"
Output: 2
Explanation: ab - aba, aba - ba
 */
public class WaysToSplitString {
    /*
    add more test case
    Todo:

     */
    public static void main(String[] args){
        System.out.println(new WaysToSplitString().solution("aacaba"));//2
    }


    //O(n) from left to right, track the number of distinct character of left and right side
    //1525. Number of Good Ways to Split a String
    public int solution(String s){
        char[] arr = s.toCharArray();
        int left = 0;
        int[] left_arr = new int[26];
        int right = 0;
        int[] right_arr = new int[26];
        for(int i = 0; i < s.length(); i++){
            if(right_arr[arr[i] - 'a'] == 0 ){
                right++;
            }
            right_arr[arr[i] - 'a']++;
        }
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            if(left_arr[arr[i] - 'a'] == 0){
                left++;
            }
            left_arr[arr[i] - 'a']++;
            right_arr[arr[i] - 'a']--;
            if(right_arr[arr[i] - 'a'] == 0){
                right--;
            }
            if(left == right)
                res++;
        }
        return res;
    }
}
