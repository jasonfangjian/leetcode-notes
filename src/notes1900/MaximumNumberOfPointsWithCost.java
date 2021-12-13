package notes1900;


import java.util.HashMap;

/*
1937. Maximum Number of Points with Cost

You are given an m x n integer matrix points (0-indexed). Starting with 0 points,
you want to maximize the number of points you can get from the matrix.

To gain points, you must pick one cell in each row.
Picking the cell at coordinates (r, c) will add points[r][c] to your score.

However, you will lose points if you pick a cell too far from the cell that you picked in the previous row.
For every two adjacent rows r and r + 1 (where 0 <= r < m - 1),
picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.

Return the maximum number of points you can achieve.

abs(x) is defined as:

x for x >= 0.
-x for x < 0.

Input: points = [[1,2,3},{1,5,1},{3,1,1]]
Output: 9
Explanation:
The blue cells denote the optimal cells to pick, which have coordinates (0, 2), (1, 1), and (2, 0).
You add 3 + 5 + 3 = 11 to your score.
However, you must subtract abs(2 - 1) + abs(1 - 0) = 2 from your score.
Your final score is 11 - 2 = 9.

Input: points = [[1,5},{2,3},{4,2]]
Output: 11
Explanation:
The blue cells denote the optimal cells to pick, which have coordinates (0, 1), (1, 1), and (2, 0).
You add 5 + 3 + 4 = 12 to your score.
However, you must subtract abs(1 - 1) + abs(1 - 0) = 1 from your score.
Your final score is 12 - 1 = 11.

Constraints:

m == points.length
n == points[r].length
1 <= m, n <= 105
1 <= m * n <= 105
0 <= points[r][c] <= 105
 */
public class MaximumNumberOfPointsWithCost {
    public static void main(String[] args){
        System.out.println(new MaximumNumberOfPointsWithCost().maxPoints(new int[][]{{1,2,3},{1,5,1},{3,1,1}}));
        System.out.println(new MaximumNumberOfPointsWithCost().maxPoints(new int[][]{{1,5},{2,3},{4,2}}));
    }


    //ELT
    HashMap<String, Long> memo = new HashMap<>();


//    public long maxPoints(int[][] points) {
//        Long max = Long.MIN_VALUE;
//        for(int i =0; i < points[0].length; i++){
//            max = Math.max(max,helper(points,1,i,points[0][i]));
//        }
//        System.out.println(memo);
//        return max;
//    }
//
//
//
//    public long helper(int[][] points, int row, int prev, long sum){
//        if(memo.containsKey(row + "+" + prev))
//            return memo.get(row + "+" + prev) + sum;
//        if(row == points.length){
//            return sum;
//        }
//        int[] cur = points[row];
//
//        Long curMax = Long.MIN_VALUE;
//        for(int i =0; i < cur.length; i++){
//            Long res = helper(points,row+1,i,sum + cur[i] - Math.abs(i - prev));
//            curMax = Math.max(curMax,res);
//        }
//        memo.put(row + "+" + prev, curMax - sum);
//        return curMax;
//    }


        public long maxPoints(int[][] points) {
            int m = points.length;
            int n = points[0].length;
            Long[][] sum = new Long[m][n];
            for(int i = 0; i < n; i++){
                sum[m-1][i] = (long) points[m-1][i];
            }
            for(int i = m-2 ; i>=0; i--){
                Long[] left = new Long[n];
                left[0] = sum[i+1][0];
                for(int j = 1; j < n; j ++){
                    left[j] = Math.max(sum[i+1][j],left[j-1] -1);
                }
                Long[] right = new Long[n];
                right[n-1] = sum[i+1][n-1];
                for(int j = n - 2; j>=0; j--){
                    right[j] = Math.max(sum[i+1][j],right[j+1] - 1);
                }
                for(int j = 0 ; j < n; j++){
                    sum[i][j] = Math.max(left[j],right[j]) + points[i][j];
                }
            }
            Long res = Long.MIN_VALUE;
            for(int i = 0; i < sum[0].length; i++){
                res = Math.max(res,sum[0][i]);
            }
            return res;
    }


}
