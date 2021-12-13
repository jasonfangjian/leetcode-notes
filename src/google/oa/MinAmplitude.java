package google.oa;

/*
Question 1:
Given an Array A, find the minimum amplitude you can get after changing up to 3 elements.
Amplitude is the range of the array (basically difference between largest and smallest element).

Example 1:

Input: [-1, 3, -1, 8, 5 4]
Output: 2
Explanation: we can change -1, -1, 8 to 3, 4 or 5
Example 2:

Input: [10, 10, 3, 4, 10]
Output: 0
Explanation: change 3 and 4 to 10
So the way I did it was sort it, and then start removing the end elements because we would only want to change
a element to a number within the smallest amplitude. There are 4 options,
remove all 3 from the end, remove 2 from end 1 from start, remove 1 from end and 2 from start,
remove 3 from start.

The runtime should be O(nlogn) since we used sort. I'm not sure if my logic is correct or maybe if we can do it in O(n)
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MinAmplitude {
    public static void main(String[] args){
        /*

        Todo:need more test case

         */

//        System.out.println(new MinAmplitude().solution(new int[]{1,5,6,14,15}));//1
        System.out.println(new MinAmplitude().solution(new int[]{1, 5, 0, 10, 14}));
    }

    //leetcode 1509. Minimum Difference Between Largest and Smallest Value in Three Moves
    public int solution(int[] nums){
        if(nums.length <= 4)
            return 0;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int max4 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int min3 = Integer.MAX_VALUE;
        int min4 = Integer.MAX_VALUE;

        for(int n : nums){
            if(n > max1){
                max4 = max3;
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2){
                max4 = max3;
                max3 = max2;
                max2 = n;
            } else if(n > max3){
                max4 = max3;
                max3 = n;
            } else if(n > max4){
                max4 = n;
            }

            if(n < min1){
                min4 = min3;
                min3 = min2;
                min2 = min1;
                min1 = n;
            } else if(n < min2){
                min4 = min3;
                min3 = min2;
                min2 = n;
            } else if(n < min3){
                min4 = min3;
                min3 = n;
            } else if(n < min4){
                min4 = n;
            }
        }


        int situation1 = max4 - min1;
        int situation2 = max3 - min2;
        int situation3 = max2 - min3;
        int situation4 = max1 - min4;

        int res = Math.min(situation1,Math.min(situation2,Math.min(situation3,situation4)));

        return res;

    }



}
