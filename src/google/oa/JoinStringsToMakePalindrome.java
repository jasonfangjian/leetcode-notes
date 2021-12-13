package google.oa;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.summingInt;

/*
we are given an array of N strings n which each string consists of 2 lowercase English Letters.

We would like to join as many strings together as possible in order to obtain a palindrome.

["ck","kc","ho","kc"] -> 4

["ab","hu","ba","nn","nn"] -> 6
 */
public class JoinStringsToMakePalindrome {
    /*
    Todo:need more test cases
     */
    public static void main(String[] args){
        System.out.println(new JoinStringsToMakePalindrome().solution(new String[]{"ab","hu","ba","nn"})); //6
        System.out.println(new JoinStringsToMakePalindrome().solution(new String[]{"ck","kc","ho","kc"})); //4
        System.out.println(new JoinStringsToMakePalindrome().solution(new String[]{"ck","kc","ho","kc","ck","nn","nn","nn"}));//14
        System.out.println(new JoinStringsToMakePalindrome().solution(new String[]{"ck","kc","ho","kc","ck","nn","nn","nn","cc"}));//14
        System.out.println(new JoinStringsToMakePalindrome().solution(new String[]{"ck","ck","ck","kc","ho","kc","ck","nn","nn","nn","cc"}));//14
        System.out.println(new JoinStringsToMakePalindrome().solution(new String[]{"xx","xx","nn","mm","mm","mm","xx"}));//10
    }

    //may have corner cases are not considered
//    public int solution(String[] A){
//        int doubleLetter = 0;
//        int pair = 0;
//        //calculate frequency
//        Map<String,Integer> map = Arrays.stream(A).collect(Collectors.groupingBy(Function.identity(),
//                summingInt(e -> 1)));
//
//
//        for(int i = 0; i < A.length; i++){
//            //already used
//            if(map.get(A[i]) <= 0)
//                continue;
//            //palindrome itself
//            if(A[i].charAt(0) == A[i].charAt(1)){
//                //single string with same character can only be used once in the middle
//                if(map.get(A[i]) == 1 && doubleLetter == 0){
//                    doubleLetter = 2;
//                    map.put(A[i],map.get(A[i]) -1);
//                    continue;
//                }
//                //if there is already a single string with same character in the middle, then we can not pick this one.
//                if(map.get(A[i]) == 1)
//                    continue;
//                //if there is more than 1 string with same characters, we still pick a pair of them first.
//            }
//            //reverse the string
//            String rev = new StringBuilder(A[i]).reverse().toString();
//            //check whether the reversed string in the map
//            if(map.containsKey(rev) && map.get(rev) > 0){
//                pair = pair + 4;
//                map.put(A[i],map.get(A[i]) - 1);
//                map.put(rev,map.get(rev) - 1);
//            }
//        }
//
//        return pair + doubleLetter;
//    }

    public int solution(String[] A){
        int doubleLetter = 0;
        int count = 0;
        Map<String,Integer> map = Arrays.stream(A).collect(Collectors.groupingBy(Function.identity(), summingInt(e -> 1)));
        for(String str : map.keySet()){
            if(map.get(str) == 0)
                continue;
            String rev = new StringBuilder(str).reverse().toString();
            if(rev.equals(str)){
                int pair = map.get(rev)/2;
                count = count + pair*4;
                map.put(rev,map.get(rev) - pair*2);
                if(map.get(rev) == 1 && doubleLetter == 0){
                    doubleLetter = 2;
                    map.put(rev,0);
                }
            } else {
                if(map.containsKey(rev)){
                    int pair = Math.min(map.get(str),map.get(rev));
                    count = count + pair*4;
                    map.put(str,map.get(str) - pair);
                    map.put(rev,map.get(rev) - pair);
                }
            }
        }

        return count + doubleLetter;
    }


}
