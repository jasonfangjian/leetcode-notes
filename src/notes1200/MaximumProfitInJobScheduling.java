package notes1200;


import java.util.Arrays;
import java.util.TreeMap;

/*
1235. Maximum Profit in Job Scheduling

We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i],
obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum
profit you can take such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.

Example 1:

Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job.
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.

Example 2:

Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job.
Profit obtained 150 = 20 + 70 + 60.

Example 3:

Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6

Constraints:

1 <= startTime.length == endTime.length == profit.length <= 5 * 104
1 <= startTime[i] < endTime[i] <= 109
1 <= profit[i] <= 104
 */
public class MaximumProfitInJobScheduling {
    public static void main(String[] args){
        System.out.println(new MaximumProfitInJobScheduling().jobScheduling(new int[]{1,2,3,3},new int[]{3,4,5,6}, new int[]{50,10,40, 70}));
    }


//        [4,2,4,8,2]
//        [5,5,5,10,8]
//        [1,2,8,10,4]

    // 2 5 2 -> 5 2
    // 2 8 4 -> 8 4
    // 4 5 8 -> 5 8
    // 4 5 1
    // 8 10 10
    // 2 5 2 -> 5 2
    // 4 5 8 -> 5 8
    // 4 5 1 -> 5 8
    // 2 8 4 -> 5 8
    // 8 10 1->
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] graph = new int[startTime.length][3];
        for(int i = 0 ; i < endTime.length; i++){
            graph[i] = new int[]{startTime[i],endTime[i],profit[i]};
        }

        Arrays.sort(graph,(a,b) -> a[1] == b[1] ? a[0]-b[0] : a[1] - b[1]);

        TreeMap<Integer,Integer> treeMap = new TreeMap<>();


        int max = Integer.MIN_VALUE;
        for(int i = 0; i < graph.length; i++){
            Integer floorS = treeMap.floorKey(graph[i][0]);
            Integer floorE = treeMap.floorKey(graph[i][1]);
            if(floorS == null){
                if(floorE == null){
                    treeMap.put(graph[i][1],graph[i][2]);
                    max = Math.max(max,graph[i][2]);
                } else {
                    int pick = graph[i][2];
                    int unpick = treeMap.get(floorE);
                    if(pick > unpick){
                        treeMap.put(graph[i][1],pick);
                        max = Math.max(max,pick);
                    }
                }
            } else {
                if(floorE <= graph[i][0]){
                    treeMap.put(graph[i][1], treeMap.get(floorE) + graph[i][2]);
                    max = Math.max(max,treeMap.get(floorE) + graph[i][2]);
                } else {
                    int pick = treeMap.get(floorS) + graph[i][2];
                    int unpick = treeMap.get(floorE);
                    if(pick > unpick){
                        treeMap.put(graph[i][1],pick);
                        max = Math.max(max,pick);
                    }
                }
            }
        }
        return max;
    }
}
