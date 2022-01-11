package notes350;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
370. Range Addition

You are given an integer length and an array updates where updates[i] = [startIdxi, endIdxi, inci].

You have an array arr of length length with all zeros, and you have some operation to apply on arr. In the ith operation, you should increment all the elements arr[startIdxi], arr[startIdxi + 1], ..., arr[endIdxi] by inci.

Return arr after applying all the updates.



Example 1:


Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
Output: [-2,0,3,5,3]
Example 2:

Input: length = 10, updates = [[2,4,6],[5,6,8],[1,9,-4]]
Output: [0,-4,2,2,2,4,4,-4,-4,-4]


Constraints:

1 <= length <= 105
0 <= updates.length <= 104
0 <= startIdxi <= endIdxi < length
-1000 <= inci <= 1000
 */
public class RangeAddition {

    public int[] getModifiedArray(int length, int[][] updates) {
        int[][] start = Arrays.copyOf(updates,updates.length);
        int[][] end = Arrays.copyOf(updates,updates.length);
        int[] res = new int[length];
        Arrays.sort(start,(a,b) -> a[0] - b[0]);
        Arrays.sort(end,(a,b) -> a[1] - b[1]);

        List<int[]> scan = new ArrayList<>();


        int i =0;
        int j = 0;

        int cur = 0;
        while(i < start.length && j < end.length){
            int startIndex = start[i][0];
            int endIndex = end[j][1] + 1;

            if(startIndex < endIndex){
                cur  = cur + start[i][2];
                scan.add(new int[]{startIndex,cur});
                i++;
            } else if (startIndex > endIndex){
                cur = cur - end[j][2];
                scan.add(new int[]{endIndex,cur});
                j++;
            } else {
                cur = cur + start[i][2] - end[j][2];
                scan.add(new int[]{startIndex,cur});
                i++;
                j++;
            }
        }

        if(i == start.length){
            for(;j < end.length; j++){
                cur = cur - end[j][2];
                scan.add(new int[]{end[j][1]+1,cur});
            }
        }

        if(j == end.length){
            for(; i< start.length; i++){
                cur  = cur + start[i][2];
                scan.add(new int[]{start[i][0],cur});
            }
        }
        for(int m =0; m < scan.size()-1; m++){
            int[] s = scan.get(m);
            int[] e = scan.get(m+1);

            for(int n = s[0]; n < e[0]; n++){
                res[n] = s[1];
            }
        }
        return res;
    }
}
