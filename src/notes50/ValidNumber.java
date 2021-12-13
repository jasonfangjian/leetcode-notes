package notes50;


import java.util.Arrays;

/*
65. Valid Number

A valid number can be split up into these components (in order):

A decimal number or an integer.
(Optional) An 'e' or 'E', followed by an integer.
A decimal number can be split up into these components (in order):

(Optional) A sign character (either '+' or '-').
One of the following formats:
One or more digits, followed by a dot '.'.
One or more digits, followed by a dot '.', followed by one or more digits.
A dot '.', followed by one or more digits.
An integer can be split up into these components (in order):

(Optional) A sign character (either '+' or '-').
One or more digits.
For example, all the following are valid numbers:
["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"],
while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].

Given a string s, return true if s is a valid number.


Example 1:

Input: s = "0"
Output: true
Example 2:

Input: s = "e"
Output: false
Example 3:

Input: s = "."
Output: false
Example 4:

Input: s = ".1"
Output: true


Constraints:

1 <= s.length <= 20
s consists of only English letters (both uppercase and lowercase), digits (0-9), plus '+', minus '-', or dot '.'.
 */
public class ValidNumber {
    public static void main(String[] args){
        System.out.println(new ValidNumber().isNumber("3."));
        System.out.println(new ValidNumber().isNumber(".3"));
        System.out.println(new ValidNumber().isNumber("."));
        System.out.println(new ValidNumber().isNumber(".1."));
    }

    public boolean isNumber(String s) {
        if(checkInteger(s))
            return true;
        if(checkDecimal(s))
            return true;
        if(checkWithE(s))
            return true;
        return false;
    }

    public boolean checkDecimal(String s){
        if(s.length() == 0)
            return false;
        s = " " + s + " ";
        String[] dots = s.split("\\.");
        if(dots.length!= 2)
            return false;
        String front = dots[0];
        String back = dots[1];

        front = front.trim();
        back = back.trim();

        boolean f = false;
        boolean b = true;

        if(front.length() == 0 && back.length() == 0)
            return false;
        if(((front.length() == 1 && front.charAt(0) == '+') || (front.length() == 1 && front.charAt(0) == '-'))&&back.length() == 0)
            return false;
        if(front.length() == 0 || (front.length() == 1 && front.charAt(0) == '+') || (front.length() == 1 && front.charAt(0) == '-') || checkInteger(front))
            f = true;
        char[] barr = back.toCharArray();
        for(char c : barr){
            if(!Character.isDigit(c)){
                b = false;
                break;
            }
        }
        return f && b;
    }

    public boolean checkInteger(String str){
        if(str.length() == 0)
            return false;
        if(str.charAt(0) == '+' || str.charAt(0) == '-' )
            str = str.substring(1);
        if(str.length() == 0)
            return false;
        char[] arr = str.toCharArray();
        for(char c : arr){
            if(!Character.isDigit(c))
                return false;
        }

        return true;
    }


    public boolean checkWithE(String str){
        if(str.length() == 0)
            return false;
        str = " " + str + " ";
        String s = str.replace('E','e');
        String[] sp = s.split("e");
        if(sp.length != 2)
            return false;
        String front = sp[0].trim();
        String back = sp[1].trim();

        return (checkDecimal(front) || checkInteger(front))&& checkInteger(back);
    }
}
