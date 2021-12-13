package notes1300;

import java.util.Arrays;

/*
1335. Minimum Difficulty of a Job Schedule

You want to schedule a list of jobs in d days.
Jobs are dependent (i.e To work on the i-th job, you have to finish all the jobs j where 0 <= j < i).

You have to finish at least one task every day.
The difficulty of a job schedule is the sum of difficulties of each day of the d days.
The difficulty of a day is the maximum difficulty of a job done in that day.

Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].

Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.



Example 1:


Input: jobDifficulty = [6,5,4,3,2,1], d = 2
Output: 7
Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
Second day you can finish the last job, total difficulty = 1.
The difficulty of the schedule = 6 + 1 = 7
Example 2:

Input: jobDifficulty = [9,9,9], d = 4
Output: -1
Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
Example 3:

Input: jobDifficulty = [1,1,1], d = 3
Output: 3
Explanation: The schedule is one job per day. total difficulty will be 3.
Example 4:

Input: jobDifficulty = [7,1,7,1,7,1], d = 3
Output: 15
Example 5:

Input: jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
Output: 843


Constraints:

1 <= jobDifficulty.length <= 300
0 <= jobDifficulty[i] <= 1000
1 <= d <= 10

 */
public class MinimumDifficultyOfAJobSchedule {
    public static void main(String[] args){
        System.out.println(new MinimumDifficultyOfAJobSchedule().minDifficulty(new int[]{7,1,7,1,7,1},3));
    }

    public int minDifficulty(int[] jobDifficulty, int d) {
        int len = jobDifficulty.length;
        if(len < d)
            return -1;
        int[][] maxInRange = new int[len+1][len+1];

        for(int i = 1; i <= len; i++ ){
            maxInRange[i][i] = jobDifficulty[i-1];
        }

        for(int i = len -1; i>0; i--){
            for(int j = i + 1; j<=len; j++){
                maxInRange[i][j] = Math.max(maxInRange[i][j-1],jobDifficulty[j-1]);
            }
        }

        int[][] dp = new int[len+1][d+1];
        for(int i = 0; i <= len; i++){
            Arrays.fill(dp[i],3000001);
        }
        for(int i = 1; i <= len; i++){
            dp[i][1] = maxInRange[1][i];
        }

        for(int i =2; i <=len; i++){
            for(int j = 2; j<=i&&j<=d;j++){
                for(int m = i -1; m>=j-1;m--){
                    dp[i][j] = Math.min(dp[i][j],dp[m][j-1] + maxInRange[m+1][i]);
                }
            }
        }
        return dp[len][d];
    }
}
