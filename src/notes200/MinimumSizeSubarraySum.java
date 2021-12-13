package notes200;

/*
209. Minimum Size Subarray Sum

Given an array of positive integers nums and a positive integer target,
return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr]
of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.



Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:

Input: target = 4, nums = [1,4,4]
Output: 1
Example 3:

Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0


Constraints:

1 <= target <= 109
1 <= nums.length <= 105
1 <= nums[i] <= 105


Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 */
public class MinimumSizeSubarraySum {
    public static void main(String[] args){
        System.out.println(new MinimumSizeSubarraySum().minSubArrayLen(7,new int[]{2,3,1,2,4,3}));
    }

    public int minSubArrayLen(int target, int[] nums) {
        int lo =0;
        int hi = 0;

        int sum = nums[0];

        int len = Integer.MAX_VALUE;

        while (hi < nums.length){
            if(sum == target){
                len = Math.min(len,hi - lo + 1);
                sum = sum - nums[lo];
                lo ++;
                hi ++;
                if(hi == nums.length)
                    continue;
                sum = sum + nums[hi];
            } else if(sum < target){
                hi++;
                if(hi == nums.length)
                    continue;
                sum = sum + nums[hi];
            } else {
                len = Math.min(len,hi - lo + 1);
                lo++;
                if(lo > hi){
                    hi = lo;
                    if(hi == nums.length)
                        continue;
                    sum = nums[hi];
                } else {
                    sum = sum - nums[lo-1];
                }
            }

        }

        return len == Integer.MAX_VALUE ? 0 : len;
    }




    public int minSubArrayLen2(int s, int[] nums) {
        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= s) {
                result = Math.min(result, right - left + 1);
                sum -= nums[left++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
