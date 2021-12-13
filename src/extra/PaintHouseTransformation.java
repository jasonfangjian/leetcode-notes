package extra;

/*
给N个房子，涂白色和涂黑色的花费分别是a,b。要求不能有连续三间房子涂同一种颜色。求喷涂所有房子的最小价格。
 */
public class PaintHouseTransformation {
    public static void main(String[] args){
        System.out.println(new PaintHouseTransformation().solution(9,3,4));
    }
    /*

     */
    public int solution(int N, int a, int b){
        int[][][] dp = new int[N][2][2];
        dp[0][0][0] = a;
        dp[0][0][1] = 1;
        dp[0][1][0] = b;
        dp[0][1][1] = 1;

        for(int i = 1; i < N;i++){
            int prevACost = dp[i-1][0][0];
            int prevBCost = dp[i-1][1][0];

            int prevALen = dp[i-1][0][1];
            int prevBLen = dp[i-1][1][1];

            if(prevALen == 2){
                dp[i][0][0] = prevBCost + a;
                dp[i][0][1] = 1;

            } else {
                if(prevACost < prevBCost){
                    dp[i][0][0] = prevACost + a;
                    dp[i][0][1] = prevALen + 1;
                } else {
                    dp[i][0][0] = prevBCost + a;
                    dp[i][0][1] = 1;
                }
            }

            if(prevBLen == 2){
                dp[i][1][0] = prevACost + b;
                dp[i][1][1] = 1;
            } else {
                if(prevBCost < prevACost){
                    dp[i][1][0] = prevBCost + b;
                    dp[i][1][1] = prevBLen+1;
                } else {
                    dp[i][1][0] = prevACost + b;
                    dp[i][1][1] = 1;
                }
            }
        }

        return Math.min(dp[N-1][0][0],dp[N-1][1][0]);
    }
}
