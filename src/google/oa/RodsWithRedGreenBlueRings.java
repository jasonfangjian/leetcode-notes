package google.oa;

import java.util.*;

/*
You have 10 rods numbered from 0 to 9

3 types of ring red green blue

you ge a point for each rod that has a ring of each color on it

that is to get a point you need a red, green and blue on one rod.

example 1
B2R5G2R2 -> 1

example 2
R8R0B5G1B8G8 -> 1

Q1: 一个上面有2组rgb算一分还是两分 -> 倾向一分
Q2：一分是不是太简单了。。。
 */
public class RodsWithRedGreenBlueRings {

    public static void main(String[] args){
        System.out.println(new RodsWithRedGreenBlueRings().Solution("B2R5G2R2")); //1
        System.out.println(new RodsWithRedGreenBlueRings().Solution("R8R0B5G1B8G8"));//1
    }


    //array of set, index of array represent rods
    //put ring into the set, if set size equals to 3, which means this rod has all the rings then get 1 points.
    public int Solution(String S){
        Set[] rods = new Set[10];
        char[] arr = S.toCharArray();

        for(int i =0; i < arr.length; i=i+2){
            int index = arr[i + 1] - '0';
            Set<Character> set = rods[index] == null ? new HashSet<>() : rods[index];
            set.add(arr[i]);
            rods[index] = set;
        }

        int res = 0;
        for(int i =0; i < 10; i++){
            if(rods[i] != null && rods[i].size() == 3)
                res++;
        }
        return res;
    }
}
