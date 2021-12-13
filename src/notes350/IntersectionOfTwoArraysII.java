package notes350;

import java.util.ArrayList;
import java.util.*;


public class IntersectionOfTwoArraysII {
    public static void main(String[] args){

    }

    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        HashMap<Integer,Integer> map1 = new HashMap<>();
        HashMap<Integer,Integer> map2 = new HashMap<>();
        for(int i : nums1){
            map1.put(i,map1.getOrDefault(i,0) + 1);
        }

        for(int i : nums2){
            map2.put(i,map2.getOrDefault(i,0)+1);
        }

        for(int i : map1.keySet()){
            if(map2.containsKey(i)){
                int c = Math.min(map1.get(i),map2.get(i));
                for(int j =0; j<c; j++){
                    list.add(i);
                }
            }
        }

        int[] res = new int[list.size()];

        for(int i =0; i < list.size();i++){
            res[i] = list.get(i);
        }

        return res;
    }
}
