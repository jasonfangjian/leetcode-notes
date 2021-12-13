package google.oa;


import java.util.HashMap;

/*
Given an array A consisting of N integers,
find maximum K such that: K = |i - j| = |A[i]-A[j]|
That is
distance between 2 integers equals to the difference
i = j always Valid K = 0;

Example 1
[2,2,2,1] -> A[2] = 2 A[3] = 1 K =1

Example 2
[2,4,6,7,4,7,2]  -> A[0] = 2 A[5] = 7 K =5

Example 3
[100,100,100] -> K=0

Example 4
[100000] -> K=0
 */
public class MaximumKMeetRequirement {
    /*
    Todo: need more test cases
     */
    public static void main(String[] args){
        System.out.println(new MaximumKMeetRequirement().solution(new int[]{2,2,2,1})); //1
        System.out.println(new MaximumKMeetRequirement().solution(new int[]{2,4,6,7,4,7,2}));//5
        System.out.println(new MaximumKMeetRequirement().solution(new int[]{100,100,100}));//0
        System.out.println(new MaximumKMeetRequirement().solution(new int[]{1000000}));//0
        System.out.println(new MaximumKMeetRequirement().solution(new int[]{100,45,5,6,67,68,8,89,7,76,7,7,5,765,7,85}));//15
    }

    public int solution(int[] A){
        int res = 0;
        // one map to store the first index of A[i] + i;
        HashMap<Integer,Integer> plus = new HashMap<>();
        // another map to store te first index of  A[i] - i
        HashMap<Integer,Integer> minus = new HashMap<>();

        for(int i =0; i < A.length; i++){
            if(plus.containsKey(A[i] + i)){
                //if occurs before, then calculate the distance and track the maximum
                res = Math.max(res,i - plus.get(A[i] + i));
            } else {
                //store the first index of A[i] + i;
                plus.put(A[i] + i,i);
            }

            //A[i] - i part
            if(minus.containsKey(A[i] - i)){
                res = Math.max(res,i - minus.get(A[i] - i));
            } else {
                minus.put(A[i] - i, i);
            }

        }
        return res;
    }
}
