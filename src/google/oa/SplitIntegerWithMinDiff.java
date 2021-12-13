package google.oa;

/*
给int A， A可以被切成a,b两个数，求|a-b|的最小值。eg: int A = 235, when a=2,b=35,then |a-b| = 32; when a = 23,b=5,then |a-b| = 18, 返回18

把一个整数劈成两个数字，比如191 变成[19，1]，[‍‍‌‌‍‍‌‌‍‌‍‌‌‌‌‍‌‍‌1,91]然后返回劈开后最小的绝对值差。

example 1:
12001
1-> 1 2001 -> 2000
2-> 12 001 -> 11
3-> 120 01 ->119
4-> 1200 1 -> 1199

example 2
510 -> 5 10 -> 5

example 3
7007 -> 7 007 -> 0

 */
public class SplitIntegerWithMinDiff {
    /*
    Todo: more test cases
     */
    public static void main(String[] args){
        System.out.println(new SplitIntegerWithMinDiff().solution(12001)); // 11
        System.out.println(new SplitIntegerWithMinDiff().solution(510)); // 5
        System.out.println(new SplitIntegerWithMinDiff().solution(7007)); // 0
    }


    //O(n) from left to right
    public int solution(int input){
        int level = 1;
        int temp = input;
        while (temp > 9){
            level = level*10;
            temp = temp/10;
        }
        int res = Integer.MAX_VALUE;
        int left = 0;
        int cur = input;
        while(cur > 9){
            left = left *10 + cur/level;
            cur = input - left * level;
            res = Math.min(res,Math.abs(left - cur));
            level = level/10;
        }
        return res;
    }
}
