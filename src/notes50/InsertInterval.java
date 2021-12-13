package notes50;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
57. Insert Interval

You are given an array of non-overlapping intervals intervals
where intervals[i] = [starti, endi] represent the start and the end of the ith interval
and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end]
that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by
starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.



Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
Example 3:

Input: intervals = [], newInterval = [5,7]
Output: [[5,7]]
Example 4:

Input: intervals = [[1,5]], newInterval = [2,3]
Output: [[1,5]]
Example 5:

Input: intervals = [[1,5]], newInterval = [2,7]
Output: [[1,7]]


Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 105
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 105

 */
public class InsertInterval {
    public static void main(String[] args){
        int[][] res = new InsertInterval().insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}},new int[]{4,8});
        for(int[] pair : res){
            System.out.println(Arrays.toString(pair));
        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int start = newInterval[0];
        int end = newInterval[1];


        List<int[]> list = new ArrayList<>(Arrays.asList(intervals));
        int index = insertPoint(intervals,start);
        if(index == list.size())
            list.add(newInterval);
        if(index == 0){
            list.add(0,newInterval);
            check(list);
        } else {
            if(start > list.get(index-1)[1])
                list.add(index,newInterval);

            else
                list.get(index-1)[1] = Math.max(list.get(index-1)[1],end);
            check(list);
        }
        int[][] res  = new int[list.size()][2];

        for(int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }

    public void check(List<int[]> list){
        Iterator it = list.listIterator();
        int[] cur;
        if(it.hasNext()){
            cur = (int[]) it.next();
            while (it.hasNext()){
                int[] next = (int[]) it.next();
                if(cur[1] >= next[0]){
                    cur[1] = Math.max(cur[1],next[1]);
                    it.remove();
                } else {
                    cur = next;
                }
            }
        }
    }

    public int insertPoint(int[][] intervals ,int start){
        int lo = 0;
        int hi = intervals.length  - 1;

        while(lo<=hi){
            int mid = lo + (hi - lo)/2;
            if(intervals[mid][0] == start)
                return mid;
            if(intervals[mid][0]<start){
                lo = lo + 1;
            } else {
                hi = hi - 1;
            }
        }
        return lo;
    }

}
