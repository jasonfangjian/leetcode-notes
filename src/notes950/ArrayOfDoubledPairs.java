package notes950;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

/*
954. Array of Doubled Pairs

Given an integer array of even length arr, return true if it is possible to reorder arr such
that arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2, or false otherwise.

Example 1:

Input: arr = [3,1,3,6]
Output: false

Example 2:

Input: arr = [2,1,2,6]
Output: false

Example 3:

Input: arr = [4,-2,2,-4]
Output: true
Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
Example 4:

Input: arr = [1,2,4,16,8,4]
Output: false


Constraints:

2 <= arr.length <= 3 * 104
arr.length is even.
-105 <= arr[i] <= 105
 */
public class ArrayOfDoubledPairs {
    public static void main(String[] args){
        System.out.println(new ArrayOfDoubledPairs().canReorderDoubled(new int[]{-2,4,-4,2}));
    }

    public boolean canReorderDoubled(int[] arr) {
        int i = 0;
        List<Integer> neg = new ArrayList<>();
        List<Integer> pos = new ArrayList<>();
        for(; i< arr.length; i++){
            if(arr[i] >=0){
                pos.add(arr[i]);
            } else {
                neg.add(-arr[i]);
            }
        }
        return findOriginalArray(neg) && findOriginalArray(pos);
    }

    public boolean findOriginalArray(List<Integer> changed) {
        if (changed.size() == 0)
            return true;
        if(changed.size()% 2 != 0){
            return false;
        }

        Collections.sort(changed);
        HashMap<Integer,Integer> set = new HashMap<>();
        for(int i =0 ;i < changed.size(); i ++){
            set.put(changed.get(i),set.getOrDefault(changed.get(i),0)+1);
        }
        int lo = 0;
        int hi = changed.size() - 1;
        
        while(lo < hi){
            if(set.get(changed.get(lo)) != 0){
                if(!set.containsKey(changed.get(lo) * 2) || set.get(changed.get(lo) *2) == 0)
                    return false;
                set.put(changed.get(lo) * 2, set.get(changed.get(lo) * 2) -1);
                set.put(changed.get(lo), set.get(changed.get(lo)) - 1);
            }

            if(set.get(changed.get(hi)) != 0){
                if(changed.get(hi) %2 != 0 || !set.containsKey(changed.get(hi)/2) || set.get(changed.get(hi)/2) == 0)
                    return false;
                set.put(changed.get(hi)/2, set.get(changed.get(hi)/2) -1);
                set.put(changed.get(hi),set.get(changed.get(hi)) - 1);
            }
            hi--;
            lo++;
        }

        return true;

    }

}
