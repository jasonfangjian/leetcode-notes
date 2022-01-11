package notes2100;

/*
2134. Minimum Swaps to Group All 1's Together II

A swap is defined as taking two distinct positions in an array and swapping the values in them.

A circular array is defined as an array where we consider the first element and the last element to be adjacent.

Given a binary circular array nums, return the minimum number of swaps required to group all 1's present in the array together at any location.



Example 1:

Input: nums = [0,1,0,1,1,0,0]
Output: 1
Explanation: Here are a few of the ways to group all the 1's together:
[0,0,1,1,1,0,0] using 1 swap.
[0,1,1,1,0,0,0] using 1 swap.
[1,1,0,0,0,0,1] using 2 swaps (using the circular property of the array).
There is no way to group all 1's together with 0 swaps.
Thus, the minimum number of swaps required is 1.
Example 2:

Input: nums = [0,1,1,1,0,0,1,1,0]
Output: 2
Explanation: Here are a few of the ways to group all the 1's together:
[1,1,1,0,0,0,0,1,1] using 2 swaps (using the circular property of the array).
[1,1,1,1,1,0,0,0,0] using 2 swaps.
There is no way to group all 1's together with 0 or 1 swaps.
Thus, the minimum number of swaps required is 2.

 */
public class MinimumSwapsToGroupAll1sTogetherII {

    public int minSwaps(int[] nums) {
        int[] prefix = new int[nums.length + 1];

        int count = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                prefix[i + 1] = prefix[i] + 1;
            } else {
                prefix[i+1] = prefix[i];
                count++;
            }
        }

        int min = nums.length;

        for(int i = 0; i <= nums.length - count; i++){
            min = Math.min(min,prefix[i+count] - prefix[i]);
        }

        for(int i = nums.length - count + 1; i < nums.length; i++){
            min = Math.min(min, prefix[nums.length] - prefix[i] + prefix[count - (nums.length - i)]);
        }

        return min;
    }

}
