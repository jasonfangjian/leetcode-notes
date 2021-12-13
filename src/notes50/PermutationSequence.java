package notes50;

import java.util.*;

/*
60. Permutation Sequence

The set [1, 2, 3, ..., n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.


Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"
Example 3:

Input: n = 3, k = 1
Output: "123"


Constraints:

1 <= n <= 9
1 <= k <= n!
 */
public class PermutationSequence {
    public static void main(String[] args){
        System.out.println(new PermutationSequence().getPermutation(4,2));
        System.out.println(new PermutationSequence().getPermutation(9,362880));
    }

    public String getPermutation(int n, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 1; i <= n ; i++){
            set.add(i);
        }
       return helper(set,k);
    }

    public String helper(TreeSet<Integer> pool, int k){
        StringBuilder sb = new StringBuilder();
        if (k == 1) {
            for(Integer i : pool){
                sb.append(i);
            }
            return sb.toString();
        } else {
            int i  = 1;
            int sum = 1;
            while (sum < k){
                i++;
                sum = sum * i;
            }
            int index = 0;
            Iterator it = pool.iterator();
            int size = pool.size();
            while (index < size - i){
                if (it.hasNext()){
                    sb.append(it.next());
                    it.remove();
                    index++;
                } else
                    break;
            }

            int prev = sum/i;
            int curk = (k-1)/prev;
            List<Integer> remain = new ArrayList<>(pool);
            sb.append(remain.get(curk));
            pool.remove(remain.get(curk));
            k = k - curk*prev;
            sum = prev;
            sb.append(helper(pool,k));
            return sb.toString();
        }
    }
}
