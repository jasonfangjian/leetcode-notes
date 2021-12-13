package notes2000;

import java.util.*;
import java.util.HashSet;

/*
An integer array original is transformed into a doubled array changed by
appending twice the value of every element in original, and then randomly shuffling the resulting array.

Given an array changed, return original if changed is a doubled array.
If changed is not a doubled array, return an empty array. The elements in original may be returned in any order.

Example 1:

Input: changed = [1,3,4,2,6,8]
Output: [1,3,4]
Explanation: One possible original array could be [1,3,4]:
- Twice the value of 1 is 1 * 2 = 2.
- Twice the value of 3 is 3 * 2 = 6.
- Twice the value of 4 is 4 * 2 = 8.
Other original arrays could be [4,3,1] or [3,1,4].
Example 2:

Input: changed = [6,3,0,1]
Output: []
Explanation: changed is not a doubled array.
Example 3:

Input: changed = [1]
Output: []
Explanation: changed is not a doubled array.


Constraints:

1 <= changed.length <= 105
0 <= changed[i] <= 105
 */
public class FindOriginalArrayFromDoubledArray {
    public static void main(String[] args){
        System.out.println(Arrays.toString(new FindOriginalArrayFromDoubledArray().findOriginalArray(new int[]{1, 1, 2, 2, 2, 4})));
    }

    public int[] findOriginalArray(int[] changed) {
        if(changed.length % 2 != 0){
            return new int[0];
        }

        Arrays.sort(changed);
        HashMap<Integer,Integer> set = new HashMap<>();
        for(int i =0 ;i < changed.length; i ++){
            set.put(changed[i],set.getOrDefault(changed[i],0)+1);
        }
        int lo = 0;
        int hi = changed.length - 1;

        int[] res = new int[changed.length/2];
        int index = 0;
        while(lo < hi){
            System.out.println(set);
            if(set.get(changed[lo]) != 0){
                if(!set.containsKey(changed[lo] * 2) || set.get(changed[lo] *2) == 0)
                    return new int[0];
                set.put(changed[lo] * 2, set.get(changed[lo] * 2) -1);
                set.put(changed[lo], set.get(changed[lo]) - 1);
                res[index++] = changed[lo];
            }

            if(set.get(changed[hi]) != 0){
                if(changed[hi] %2 != 0 || !set.containsKey(changed[hi]/2) || set.get(changed[hi]/2) == 0)
                    return new int[0];
                set.put(changed[hi]/2, set.get(changed[hi]/2) -1);
                set.put(changed[hi],set.get(changed[hi]) - 1);
                res[index++] = changed[hi]/2;
            }
            hi--;
            lo++;
        }

        return res;

    }





}
