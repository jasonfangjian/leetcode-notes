package notes2100;


import java.util.Arrays;

/*
2136 Earliest Possible Day of Full Bloom


You have n flower seeds. Every seed must be planted first before it can begin to grow, then bloom. Planting a seed takes time and so does the growth of a seed. You are given two 0-indexed integer arrays plantTime and growTime, of length n each:

plantTime[i] is the number of full days it takes you to plant the ith seed. Every day, you can work on planting exactly one seed. You do not have to work on planting the same seed on consecutive days, but the planting of a seed is not complete until you have worked plantTime[i] days on planting it in total.
growTime[i] is the number of full days it takes the ith seed to grow after being completely planted. After the last day of its growth, the flower blooms and stays bloomed forever.
From the beginning of day 0, you can plant the seeds in any order.

Return the earliest possible day where all seeds are blooming.


 */
public class EarliestPossibleDayOfFullBloom {


    /*
    Todo:still needs more explanation
     */
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n  = plantTime.length;
        //create a multi-dim array
        int arr[][] = new int[n][2];
        for(int i=0;i<n;i++){
            arr[i][0] = growTime[i];
            arr[i][1] = plantTime[i];
        }
        //sort array according to max grow time first
        Arrays.sort(arr,(a, b)->(b[0]-a[0]));
        int time=0;
        int max=0;
        //plant max grow time first and keep track of max time it can take
        for(int i=0;i<n;i++){
            time += arr[i][1];
            max = Math.max(max,time+arr[i][0]);
        }
        return max;
    }
}
