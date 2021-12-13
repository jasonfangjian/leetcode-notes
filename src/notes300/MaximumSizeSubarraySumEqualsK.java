package notes300;


import java.util.Arrays;
import java.util.HashMap;

/*
325. Maximum Size Subarray Sum Equals k


Given an integer array nums and an integer k, return the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.



Example 1:

Input: nums = [1,-1,5,-2,3], k = 3
Output: 4
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
Example 2:

Input: nums = [-2,-1,2,1], k = 1
Output: 2
Explanation: The subarray [-1, 2] sums to 1 and is the longest.


Constraints:

1 <= nums.length <= 2 * 105
-104 <= nums[i] <= 104
-109 <= k <= 109

 */
public class MaximumSizeSubarraySumEqualsK {
    public static void main(String[] args){
        System.out.println(new MaximumSizeSubarraySumEqualsK().maxSubArrayLen(new int[]{-2,-1,2,1},1));
    }

    /*
      [-2,-1,2,1]
      [-2,-3,2,1]
      []
     */
    public int maxSubArrayLen(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        int target = sum - k;
        if(target == 0)
            return nums.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        int prefix = 0;
        for(int i =0;i < nums.length; i++){
            prefix+=nums[i];
            if(!map.containsKey(prefix)){
                map.put(prefix,i);
            }
        }
        int len = 0;
                   if(map.containsKey(target)){
            len = nums.length-map.get(target)-1;
        }

        int suffix = 0;
        for(int i = nums.length-1; i>=0; i--){
            suffix += nums[i];
            int temp = target - suffix;
            if(temp == 0){
                len = Math.max(len,i);
            }
            if(map.containsKey(temp) && map.get(temp) < i){
                len = Math.max(len,i-1-map.get(temp));
            }
        }

        return len;
    }
}
