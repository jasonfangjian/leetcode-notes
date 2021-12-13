package notes1650;


import java.util.Arrays;

/*
1658. Minimum Operations to Reduce X to Zero

You are given an integer array nums and an integer x. In one operation,
you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x.
Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.



Example 1:

Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
Example 2:

Input: nums = [5,6,7,8,9], x = 4
Output: -1
Example 3:

Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements
(5 operations in total) to reduce x to zero.


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 104
1 <= x <= 109
 */
public class MinimumOperationsToReduceXToZero {
    /*
    [6016,5483,541,4325,8149,3515,7865,2209,9623,9763,4052,6540,2123,2074,765,7520,4941,5290,5868,6150,6006,6077,2856,7826,9119]
31841
     */
    public static void main(String[] args){
        System.out.println(new MinimumOperationsToReduceXToZero().minOperations(new int[]{500,1,2,3,4},500));
    }

    public int minOperations(int[] nums, int x) {
        int sum = Arrays.stream(nums).sum();

        if(sum < x)
            return -1;
        if(sum == x)
            return nums.length;

        int target = sum - x;

        int len = Integer.MIN_VALUE;
        int j = 0;
        int track = 0;
        for(int i =0; i < nums.length; i++){
            track += nums[i];
            if(track == target){
                len = Math.max(len,i - j + 1);
                track -= nums[j++];
            } else if(track > target){
                while (j <= i && track >= target){
                    track -= nums[j++];
                    if(track == target) {
                        len = Math.max(len, i - j + 1);
                        break;
                    }
                }
            }
        }

        return len == Integer.MIN_VALUE ?-1 : nums.length - len;
    }
}
