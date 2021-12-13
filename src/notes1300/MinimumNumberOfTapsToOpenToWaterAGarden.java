package notes1300;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
1326. Minimum Number of Taps to Open to Water a Garden

There is a one-dimensional garden on the x-axis. The garden starts at the point 0 and ends at the point n.
(i.e The length of the garden is n).

There are n + 1 taps located at points [0, 1, ..., n] in the garden.

Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means the i-th tap can water
the area [i - ranges[i], i + ranges[i]] if it was open.

Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered return -1.

Input: n = 5, ranges = [3,4,1,1,0,0]
Output: 1
Explanation: The tap at point 0 can cover the interval [-3,3]
The tap at point 1 can cover the interval [-3,5]
The tap at point 2 can cover the interval [1,3]
The tap at point 3 can cover the interval [2,4]
The tap at point 4 can cover the interval [4,4]
The tap at point 5 can cover the interval [5,5]
Opening Only the second tap will water the whole garden [0,5]
Example 2:

Input: n = 3, ranges = [0,0,0,0]
Output: -1
Explanation: Even if you activate all the four taps you cannot water the whole garden.
Example 3:

Input: n = 7, ranges = [1,2,1,0,2,1,0,1]
Output: 3
Example 4:

Input: n = 8, ranges = [4,0,0,0,0,0,0,0,4]
Output: 2
Example 5:

Input: n = 8, ranges = [4,0,0,0,4,0,0,0,4]
Output: 1


Constraints:

1 <= n <= 104
ranges.length == n + 1
0 <= ranges[i] <= 100
 */
public class MinimumNumberOfTapsToOpenToWaterAGarden {
    public static  void main(String[] args){
        System.out.println(new MinimumNumberOfTapsToOpenToWaterAGarden().minTaps(5,new int[]{3,4,1,1,0,0}));
        System.out.println(new MinimumNumberOfTapsToOpenToWaterAGarden().minTaps(4,new int[]{0,0,0,0}));
        System.out.println(new MinimumNumberOfTapsToOpenToWaterAGarden().minTaps(17,new int[]{0,3,3,2,2,4,2,1,5,1,0,  1, 2, 3, 0, 3,  1,  1}));
    }

    /*

    [0,3,3,2,2,4,2,1,5,1,0,  1, 2, 3, 0, 3,  1,  1]
     0 1 2 3 4 5 6 7 8 9 10 11 12  13 14 15  16  17
     0  0
     -2 4 -> 4 1
     -1 5 -> 5 1
     1  5 -> 5 1
     2  6 -> 6 2
     4  8 -> 8 2
     6  8 -> 8 2
     1  9 -> 9 2
     8  10 -> 10 3
     10 10
     10 12 -> 12 4
     3 13  -> 13 2
     10 14 -> 14 4
     14 14 ->
     10 16 -> 16 4
     15 17 -> 17 5
     12 18 -> 18 3
     16 18 -> 18 3

     */
    public int minTaps(int n, int[] ranges) {
        int[][] info = new int[ranges.length][2];
        for(int i = 0; i < ranges.length; i++){
            info[i] = new int[]{i - ranges[i], i + ranges[i]};
        }
        Arrays.sort(info, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        List<int[]> dp = new ArrayList<>();
        for(int i = 0; i < info.length; i++){
            if(info[i][0] == info[i][1])
                continue;
            if(info[i][0] <= 0){
                int index = insertPoint(dp,info[i][1]);
                if(index == dp.size() || dp.get(index)[1] > 1)
                    dp.add(new int[]{info[i][1],1});
            }
            else{
                int need = info[i][0];
                if(dp.size() == 0){
                    continue;
                }
                int lowIndex = insertPoint(dp,need);
                int hiIndex = insertPoint(dp,info[i][1]);
                if(lowIndex == dp.size())
                    continue;
                int pick = dp.get(lowIndex)[1] + 1;
                if(hiIndex == dp.size() || dp.get(hiIndex)[1] > pick)
                    dp.add(new int[]{info[i][1],pick});
            }
        }

        int index = insertPoint(dp,n);
        if(index == dp.size())
            return -1;
        int min = Integer.MAX_VALUE;
        for(int i = index; i < dp.size(); i++){
            min = Math.min(min,dp.get(i)[1]);
        }
        return min;
    }

    public int insertPoint(List<int[]> list, int target){
        int lo = 0;
        int hi = list.size() -1;

        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(list.get(mid)[0] == target)
                return mid;
            if(list.get(mid)[0] > target){
                hi = hi -1;
            } else {
                lo = lo + 1;
            }
        }
        return lo;
    }


    /*
    best
     */
    public int minTaps2(int n, int[] A) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 2);
        dp[0] = 0;
        for (int i = 0; i <= n; ++i)
            for (int j = Math.max(i - A[i] + 1, 0); j <= Math.min(i + A[i], n); ++j)
                dp[j] = Math.min(dp[j], dp[Math.max(0, i - A[i])] + 1);
        return dp[n]  < n + 2 ? dp[n] : -1;
    }
}
