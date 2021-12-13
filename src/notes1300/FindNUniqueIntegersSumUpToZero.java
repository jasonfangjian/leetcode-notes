package notes1300;


import java.util.Arrays;

/*
1304. Find N Unique Integers Sum up to Zero

Given an integer n, return any array containing n unique integers such that they add up to 0.



Example 1:

Input: n = 5
Output: [-7,-1,1,3,4]
Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
Example 2:

Input: n = 3
Output: [-1,0,1]
Example 3:

Input: n = 1
Output: [0]


Constraints:

1 <= n <= 1000
 */
public class FindNUniqueIntegersSumUpToZero {

    public static void main(String[] args){
        System.out.println(Arrays.toString(new FindNUniqueIntegersSumUpToZero().sumZero(100)));
    }


    public int[] sumZero(int n) {
        int[] res = new int[n];
        int sum = 0;
        for(int i = 0; i < n - 1; i++){
            sum =sum + i + 1;
            res[i] = i + 1;
        }
        res[n - 1] = -sum;
        return  res;
    }
}
