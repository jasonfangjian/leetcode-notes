package notes500;

import java.util.*;
/*
503. Next Greater Element II

Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.

The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.



Example 1:

Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number.
The second 1's next greater number needs to search circularly, which is also 2.
Example 2:

Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]


Constraints:

1 <= nums.length <= 104
-109 <= nums[i] <= 109
 */
public class NextGreaterElementII {
    public static void main(String[] args){

    }


    public int[] nextGreaterElements(int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        int[] temp = new int[nums2.length*2];
        for(int i =0; i < nums2.length; i++){
            temp[i] = nums2[i];
            temp[i + nums2.length] = nums2[i];
        }
        int[] res = new int[nums2.length*2];
        Arrays.fill(res,-1);
        for(int i = 0;i<temp.length; i++){
            map.put(temp[i],i);
            while(!stack.isEmpty()&&temp[i] > temp[stack.peek()]){
                int index = stack.pop();
                res[index] = temp[i];
            }
            stack.push(i);
        }

        return Arrays.copyOfRange(res,0,nums2.length);
    }


}
