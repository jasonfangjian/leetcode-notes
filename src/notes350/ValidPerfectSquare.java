package notes350;


/*
367. Valid Perfect Square


Given a positive integer num, write a function which returns True if num is a perfect square else False.

Follow up: Do not use any built-in library function such as sqrt.



Example 1:

Input: num = 16
Output: true
Example 2:

Input: num = 14
Output: false


Constraints:

1 <= num <= 2^31 - 1
 */
public class ValidPerfectSquare {
    public static void main(String[] args){
        System.out.println(new ValidPerfectSquare().isPerfectSquare(808201));
    }

    public boolean isPerfectSquare(int num) {
        int lo = 1;
        int hi = num;

        while(lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(mid*mid == num)
                return true;
            if((long)mid *(long) mid < (long)num){
                lo = mid + 1;
            } else {
                hi = mid -1;
            }
        }

        return false;
    }
}
