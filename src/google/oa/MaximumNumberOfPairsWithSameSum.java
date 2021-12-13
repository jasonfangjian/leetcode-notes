package google.oa;


import java.util.HashMap;

/*
array of integers

Your task is to create pairs of them, such that every created pair has the same sum,
this sum is not specified, but the number of created pairs should be the maximum possible.
Each element may belong to one pair only.

example 1
[1,9,8,100,2] -> A[0] + A[1] = A[0]+A[2] -> 2

example 2
[2,2,2,3] -> 1

example 3
[5,5] -> 1
 */
public class MaximumNumberOfPairsWithSameSum {
    /*
    Todo:need more test cases
     */
    public static void main(String[] args){
        System.out.println(new MaximumNumberOfPairsWithSameSum().solution(new int[]{1,9,8,100,2}));
    }

    public int solution(int[] A){
        // brute force, count freq of all possible pairs
        // any improvement?
        int res = 1;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < A.length; i++){
            for(int j = i + 1; j < A.length; j++){
                map.put(A[i] + A[j],map.getOrDefault(A[i]+A[j],0) + 1);
                res = Math.max(map.get(A[i]+A[j]),res);
            }
        }

        return res;
    }


}
