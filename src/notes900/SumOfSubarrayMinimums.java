package notes900;


import java.util.Stack;

/*
907. Sum of Subarray Minimums

Given an array of integers arr, find the sum of min(b),
where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.



Example 1:

Input: arr = [3,1,2,4]
Output: 17
Explanation:
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.
Example 2:

Input: arr = [11,81,94,43,3]
Output: 444


Constraints:

1 <= arr.length <= 3 * 104
1 <= arr[i] <= 3 * 104

 */
public class SumOfSubarrayMinimums {
    public static void main(String[] args){
        System.out.println(new SumOfSubarrayMinimums().sumSubarrayMins(new int[]{3,1,2,4}));
        System.out.println(new SumOfSubarrayMinimums().sumSubarrayMins(new int[]{11,81,94,43,3}));
    }

    /*
    Todo:need more explanation
     */
    public int sumSubarrayMins(int[] A) {
        long res = 0, mod = (int)1e9 + 7;
        int n = A.length, left[] = new int[n], right[] = new int[n];
        Stack<int[]> s1 = new Stack<>(), s2 = new Stack<>();
        for (int i = 0; i < n; ++i) {
            int count = 1;
            while (!s1.isEmpty() && s1.peek()[0] > A[i])
                count += s1.pop()[1];
            s1.push(new int[] {A[i], count});
            left[i] = count;
        }
        for (int i = n - 1; i >= 0; --i) {
            int count = 1;
            while (!s2.isEmpty() && s2.peek()[0] >= A[i])
                count += s2.pop()[1];
            s2.push(new int[] {A[i], count});
            right[i] = count;
        }
        for (int i = 0; i < n; ++i)
            res = (res + (long)A[i] * left[i] * right[i]) % mod;
        return (int)res;
    }
}
