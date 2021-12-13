package notes650;


import java.util.Stack;

/*
678. Valid Parenthesis String

Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".


Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "(*)"
Output: true
Example 3:

Input: s = "(*))"
Output: true


Constraints:

1 <= s.length <= 100
s[i] is '(', ')' or '*'.
 */
public class ValidParenthesisString {
    public static void main(String[] args){
        System.out.println(new ValidParenthesisString().checkValidString("((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()"));
    }

    public boolean checkValidString(String s) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> star = new Stack<>();

        char[] arr = s.toCharArray();

        for(int i = 0; i < arr.length; i++){
            if(arr[i] == '('){
                stack.push(i);
            } else if(arr[i] == '*'){
                star.push(i);
            } else {
                if(stack.isEmpty()){
                    if(star.isEmpty())
                        return false;
                    else
                        star.pop();
                } else {
                    stack.pop();
                }
            }
        }

        if(stack.isEmpty())
            return true;
        while (!stack.isEmpty()){
            int idx1 = stack.pop();
            if(star.isEmpty())
                return false;
            int idx2 = star.pop();
            if(idx2 < idx1)
                return false;
        }

        return true;

    }

}
