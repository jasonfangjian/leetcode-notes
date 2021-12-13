package google.oa;

/*
A String contains N letters 'a' and 'b'. In one move, a single letter can be removed.

Removing the first and last letter in the string cost 1 and removing any other letter costs 2

minimum cost needed to obtain a string without b

example 1
aabaa -> 2

example 2
abbaaba -> bbaaba -> baaba -> aaba -> aab 0> aa ->5

example 3
bbb -> 3

 */
public class MinCostToDeleteB {

    /*
    need more test cases:
    Todo:
    "abababababab" -> abababababa ->abababa -> aba-> aa -> 1 + 4 + 4 + 2 ->11
     */
    public static void main(String[] args){
        System.out.println(new MinCostToDeleteB().solution("aaaaaaaa")); //0
        System.out.println(new MinCostToDeleteB().solution("aaabaaaa")); //2
        System.out.println(new MinCostToDeleteB().solution("abbaaba ")); //5
        System.out.println(new MinCostToDeleteB().solution("bbb"));      //3
        System.out.println(new MinCostToDeleteB().solution("abababababab"));//11
    }



    public int solution(String str){
        //left array to track the minimum cost to i that string(0 - i) is valid;
        //right array to track the minimum cost to i that string(i - len-1) is valid;
        int[] right = new int[str.length()+1];
        int[] left= new int[str.length()+1];

        char[] arr = str.toCharArray();
        // string a b b a a b a
        // left 0 1 2 3 4 5 6 7
        // if the left most is a, we do not need to delete to make 0 -> 0 valid;
        // if the left most is b, it costs 1 to make 0 -> 0 valid;
        if(arr[0] == 'a'){
            left[1] = 0;
        } else {
            left[1] = 1;
        }


        //string a b b a a b a
        //right  0 1 2 3 4 5 7 8
        //if right most a, then len-1 -> len -1 cost 0;
        //if right most b, then len-1 -> len - 1 cost 1;
        if(arr[arr.length-1] == 'a'){
            right[arr.length - 1] = 0;
        } else {
            right[arr.length -1] = 1;
        }

        //from left to right
        for(int i =1; i < arr.length; i++){
            // if arr[i] is a, which means we do not need to delete, so the dp equation is left[i+1] = left[i];
            // if it is b, we have 2 options, first, delete all character from 0 -> i, which cost i+1;
            //second, treat this b as middle b and cost 2, so the equation is left[i-1](2 indexes before cur index) + 2
            // tract the minimum one left[i+1] = Math.min(i + 1, left[i-1] + 2);
                if(arr[i] == 'a'){
                    left[i+1] = left[i];
                } else {
                    left[i+1] = Math.min(i + 1, left[i-1] + 2);
                }

        }

        //from right to left
        for(int i = arr.length - 2; i>=0; i--){
            // if arr[i] is a, do not need to delete
            // if it is b, 2 option as well, right[i] = Math.min(arr.length - i, right[i+2] + 2);
            if(arr[i] == 'a'){
                right[i] = right[i+1];
            } else {
                right[i] = Math.min(arr.length - i, right[i+2] + 2);
            }
        }

        //then iterate the string with index i;
        //we already get the minimum cost to make 0 - i valid, which is left[i+1];(we have dummy node in 0)
        // we also have minimum cost to make i+1 -> len -1 valid, which is right[i+1];
        //so to make all string valid, we need right[i+1] + left[i+1], then try to track the minimum one.
        int res = Integer.MAX_VALUE;
        for(int i = 0 ; i < arr.length; i++){
            res = Math.min(res,right[i+1]+left[i+1]);
        }

        return res;
    }

}
