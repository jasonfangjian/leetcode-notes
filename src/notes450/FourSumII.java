package notes450;


import java.util.HashMap;

/*
454. 4Sum II

Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:

0 <= i, j, k, l < n
nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0


Example 1:

Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
Output: 2
Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
Example 2:

Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
Output: 1


Constraints:

n == nums1.length
n == nums2.length
n == nums3.length
n == nums4.length
1 <= n <= 200
-228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228
 */
public class FourSumII {
    public static void main(String[] args){
        System.out.println(new FourSumII().fourSumCount(new int[]{1,2},new int[]{-2,-1},new int[]{-1,2},new int[]{0,2}));
    }

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer,Integer> map1 = new HashMap<>();
        HashMap<Integer,Integer> map2 = new HashMap<>();
        helper(map1,nums1);
        helper(map2,nums2);

        HashMap<Integer,Integer> count = new HashMap<>();

        for(int i : map1.keySet()){
            for(int j : map2.keySet()){
                count.put(i+j,count.getOrDefault(i+j,0) + map1.get(i)*map2.get(j));
            }
        }

        int res= 0;
        for(int i : nums3){
            for(int j : nums4){
                int target = - (i+j);
                if(count.containsKey(target)){
                    res+=count.get(target);
                }
            }
        }
        return res;
    }

    public void helper(HashMap<Integer,Integer> map, int[] nums){
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
    }


}
