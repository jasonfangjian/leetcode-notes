package google.vo;
import java.util.*;
/*
 国人老哥。给一个pool = [a,b,c], 又一堆baskets: basket1 = [a,b], basket2 = [b,c], basket3 = [a,b,c],还有个m=2，
 要求返回pool里长度为m的所有subsequence分别在这堆baskets里的出现次数。
 这里subsequence有[[a,b], [a,c], [b,c]] return一个{[a,b]:2, [a,c]:1. [b,c]:2} key是subsequence，value是分别出现了2次1次2次
 */
public class V3 {
    public static void main(String[] args){
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        l1.add(1);
        l2.add(1);
        l1.add(2);
        l2.add(2);
        HashSet<List<Integer>> set = new HashSet<>();
        set.add(l1);
        set.add(l2);
        System.out.println(set.size());
        System.out.println(new V3().solution(new char[]{'a','b','c'},new char[][]{{'a','b'},{'b','c'},{'a','b','c'}}));
    }

    public HashMap<String,Integer> solution(char[] pool, char[][] baskets){{
        HashMap<String,Integer> res = new HashMap<>();
        for(char[] b : baskets){
            res.put(String.valueOf(b),count(pool,b));
        }

        return res;
    }

    }
    public int count(char[] pool ,char[] pattern){
        int len1 = pool.length;
        int len2 = pattern.length;
        if(len2 > len1)
            return 0;
        int[][] dp = new int[len1+1][len2+1];
        int count = 0;
        for(int i = 1 ; i<=len1; i++){
            if(pool[i-1] == pattern[0]){
                count++;
                dp[i][1] = count;
            } else {
                dp[i][1] = dp[i-1][1];
            }
        }
        for(int i = 1; i <= len1; i++){
            for(int j = 2; j<=len2&&j <= i; j++){
                char a = pool[i-1];
                char b = pattern[j-1];
                if(a == b){
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[len1][len2];
    }


}
