package notes0;

import java.util.Stack;

/*
32. Longest Valid Parentheses

Given a string containing just the characters '(' and ')',
find the length of the longest valid (well-formed) parentheses substring.



Example 1:

Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".
Example 2:

Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".
Example 3:

Input: s = ""
Output: 0


Constraints:

0 <= s.length <= 3 * 104
s[i] is '(', or ')'.
 */
public class LongestValidParentheses {
    public static void main(String[] args){
        System.out.println(new LongestValidParentheses().longestValidParentheses(")()(((())))("));
    }

    public int longestValidParentheses(String s) {
        int lo = 0;
        int hi = 0;
        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        int max = Integer.MIN_VALUE;
        while(lo < s.length() && hi < s.length()){
            if(arr[hi] == '('){
                if(stack.isEmpty()){
                    max = Math.max(max,hi - lo);
                }
                stack.push('(');
                hi++;
            } else {
                if(stack.isEmpty()){
                    max = Math.max(max,hi - lo);
                    lo = hi+1;
                    hi = hi + 1;
                } else {
                    stack.pop();
                    hi++;
                }
            }
        }
        if(stack.isEmpty()){
            max = Math.max(max, hi - lo);
        }


        int maxR = Integer.MIN_VALUE;
        hi = s.length()-1;
        lo = s.length()-1;
        Stack<Character> right = new Stack<>();
        while (lo >=0 && hi >=0){
            if(arr[lo] == ')'){
                if(right.isEmpty()){
                    maxR = Math.max(maxR,hi- lo);
                }
                right.push(arr[lo]);
                lo--;
            } else {
                if(right.isEmpty()){
                    maxR =Math.max(maxR,hi - lo);
                    lo = lo - 1;
                    hi = lo;
                } else {
                    right.pop();
                    lo--;
                }
            }
        }

        if(right.isEmpty()){
            maxR = Math.max(maxR,hi- lo);
        }
        return Math.max(max,maxR);
    }

}
