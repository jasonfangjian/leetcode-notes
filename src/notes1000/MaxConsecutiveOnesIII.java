package notes1000;

import java.util.*;

/*

1004. Max Consecutive Ones III
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array
if you can flip at most k 0's.



Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
Example 2:

Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.


Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
0 <= k <= nums.length
 */
public class MaxConsecutiveOnesIII {

    public static void main(String[] args){
        System.out.println(new MaxConsecutiveOnesIII().longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0},2));
    }

    /*
    1 1 1 0 0 0 1 1 1 1 0
    1 2 3 4 5 2 3 4 5 6 6
     */
    public int longestOnes(int[] nums, int k) {
           int count = 0;
           int cur = 0;
           int max = 0;


           int lo = 0;
           int hi = 0;
           for(int i = 0; i < nums.length; i++){
               if(nums[i] == 0){
                   lo = i;
                   break;
               }
           }
           while (lo < nums.length && hi  <nums.length){
               if(nums[hi] == 0){
                   if(k == 0){
                       cur = 0 ;
                       count = 0;
                       max = Math.max(cur,max);
                   }
                   else if(count < k){
                       count++;
                       cur++;
                       max = Math.max(max,cur);
                   } else {
                       lo = lo + 1;
                       if(nums[lo] == 1){
                           int index1  = lo;
                           while (lo < nums.length && nums[lo] != 0){
                               lo++;
                           }
                           if(nums[lo -1] != 1){
                               cur  = hi - lo + 1;
                               max = Math.max(max,cur);
                           } else {
                               cur = hi - index1 + 1;
                               max = Math.max(max,cur);
                           }
                       } else {
                           if(nums[lo -1] != 1){
                               cur  = hi - lo + 1;
                               max = Math.max(max,cur);
                           }
                       }


                   }

               } else {
                   cur++;
                   max = Math.max(max,cur);
               }
               hi++;
           }
           return max;
    }
}
