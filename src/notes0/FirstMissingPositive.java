package notes0;


import java.util.HashSet;

/*
41. First Missing Positive

Given an unsorted integer array nums, return the smallest missing positive integer.

You must implement an algorithm that runs in O(n) time and uses constant extra space.



Example 1:

Input: nums = [1,2,0]
Output: 3
Example 2:

Input: nums = [3,4,-1,1]
Output: 2
Example 3:

Input: nums = [7,8,9,11,12]
Output: 1


Constraints:

1 <= nums.length <= 5 * 105
-231 <= nums[i] <= 231 - 1
 */
public class FirstMissingPositive {
    public static void main(String[] args){
        System.out.println(new FirstMissingPositive().firstMissingPositive(new int[]{
                1,2,6,3,5,4}));
    }

    public int firstMissingPositive(int[] nums) {
        int min = Integer.MAX_VALUE;
        int count = 0;
        for(int i =0; i < nums.length; i++){
            if(nums[i] > 0){
                min = Math.min(min,nums[i]);
            } else {
                nums[i] = Integer.MAX_VALUE;
                count++;
            }
        }

        for(int i = 0; i < nums.length;i++){
            if(2<=Math.abs(nums[i])&&Math.abs(nums[i])<=nums.length - count){
                nums[Math.abs(nums[i])-1] = nums[Math.abs(nums[i])-1] > 0 ? -nums[Math.abs(nums[i])-1] : nums[Math.abs(nums[i])-1];
            }
        }

        if(min > 1 || min == Integer.MAX_VALUE)
            return 1;
        for(int i = 2; i <= nums.length - count;i++){
            if(nums[i-1] >0)
                return i;
        }
        return nums.length - count + 1;
    }
}
