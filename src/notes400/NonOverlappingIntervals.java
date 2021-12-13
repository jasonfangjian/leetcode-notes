package notes400;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
435. Non-overlapping Intervals

Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.



Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.


Constraints:

1 <= intervals.length <= 105
intervals[i].length == 2
-5 * 104 <= starti < endi <= 5 * 104
 */
public class NonOverlappingIntervals {
    public static void main(String[] args){
        System.out.println(new NonOverlappingIntervals().eraseOverlapIntervals(new int[][]{{0,2},{1,3},{2,4},{3,5},{4,6}}));
    }

    /*
    [[0,2],[1,3],[2,4],[3,5],[4,6]]
    [0,2]
    []
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals,(a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int[] cur = intervals[0];
        int count = 0;
        for(int i =1; i < intervals.length; i++){
            int[] temp = intervals[i];
            if(cur[1] > temp[0]){
                cur = temp[1] > cur[1] ? cur : temp;
                count++;
            } else {
                cur = temp;
            }
        }

        return count;
    }
}
