package notes900;


import java.util.HashMap;
import java.util.jar.JarEntry;

/*
935. Knight Dialer

The chess knight has a unique movement, it may move two squares vertically and one square horizontally,
or two squares horizontally and one square vertically (with both forming the shape of an L).
The possible movements of chess knight are shown in this diagaram:

A chess knight can move as indicated in the chess diagram below:

We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).


Given an integer n, return how many distinct phone numbers of length n we can dial.

You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n. All jumps should be valid knight jumps.

As the answer may be very large, return the answer modulo 109 + 7.



Example 1:

Input: n = 1
Output: 10
Explanation: We need to dial a number of length 1, so placing the knight over any numeric cell of the 10 cells is sufficient.
Example 2:

Input: n = 2
Output: 20
Explanation: All the valid number we can dial are [04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
Example 3:

Input: n = 3
Output: 46
Example 4:

Input: n = 4
Output: 104
Example 5:

Input: n = 3131
Output: 136006598
Explanation: Please take care of the mod.


Constraints:

1 <= n <= 5000

 */
public class KnightDialer {
    public static void main(String[] args){
        System.out.println(new KnightDialer().knightDialer(100));
    }


    HashMap<String,Integer> memo = new HashMap<>();
    public int knightDialer(int N) {
        HashMap<Integer,int[]> map = new HashMap<>();
        map.put(0,new int[]{4,6});
        map.put(1,new int[]{8,6});
        map.put(2,new int[]{7,9});
        map.put(3,new int[]{4,8});
        map.put(4,new int[]{0,3,9});
        map.put(5,new int[]{});
        map.put(6,new int[]{0,1,7});
        map.put(7,new int[]{2,6});
        map.put(8,new int[]{1,3});
        map.put(9,new int[]{2,4});
        int sum = 0;
        for(int i = 0; i < 10; i++){
            sum = sum + helper(map,N-1,i);
            sum =sum%1000000007;
        }
        return sum;
    }


    public int helper(HashMap<Integer,int[]> map, int N,int cur){
         if(N == 0)
             return 1;
         String key = N + " " + cur;
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        int[] next = map.get(cur);
        int sum = 0;
        for(int i : next){
            sum += helper(map,N-1,i);
            sum = sum%1000000007;
        }
        memo.put(key,sum%1000000007);
        return sum%1000000007;
    }

}
