package notes500;


import java.util.*;

/*
526. Beautiful Arrangement

Suppose you have n integers labeled 1 through n. A permutation of those n integers perm (1-indexed)
is considered a beautiful arrangement if for every i (1 <= i <= n), either of the following is true:

perm[i] is divisible by i.
i is divisible by perm[i].
Given an integer n, return the number of the beautiful arrangements that you can construct.



Example 1:

Input: n = 2
Output: 2
Explanation:
The first beautiful arrangement is [1,2]:
    - perm[1] = 1 is divisible by i = 1
    - perm[2] = 2 is divisible by i = 2
The second beautiful arrangement is [2,1]:
    - perm[1] = 2 is divisible by i = 1
    - i = 2 is divisible by perm[2] = 1
Example 2:

Input: n = 1
Output: 1


Constraints:

1 <= n <= 15
 */
public class BeautifulArrangement {
    public static void main(String[] args){
        System.out.println(new BeautifulArrangement().countArrangement(15));
    }

    HashMap<String, Integer> map = new HashMap<>();

    public int countArrangement(int n) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i<=n; i++){
            set.add(i);
        }
        return helper(set,n,0);
    }

    public int helper(HashSet<Integer> set, int n, int index){
        int sum = 0;
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        String key = list.toString()+index;
        if(map.containsKey(key)){
            return map.get(key);
        }
        if(set.size() == 0){
            return 1;
        }

        for(Integer i : list){
            if(i % (index+1) == 0 || (index+1) % i == 0){
                set.remove(i);
                sum += helper(set,n,index+1);
                set.add(i);
            }
        }

        map.put(key,sum);


        return sum;
    }







}
