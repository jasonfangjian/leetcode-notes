package notes1250;

import java.util.*;

/*
1277. Count Square Submatrices with All Ones
Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

Example 1:

00 false
11 true
22 true
33 true

Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation:
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.

Example 2:

Input: matrix =
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation:
There are 6 squares of side 1.
There is 1 square of side 2.
Total number of squares = 6 + 1 = 7.

Constraints:

1 <= arr.length <= 300
1 <= arr[0].length <= 300
0 <= arr[i][j] <= 1
 */
public class CountSquareSubmatricesWithAllOnes {
    public static void main(String[] args){
        System.out.println(new CountSquareSubmatricesWithAllOnes().countSquares(new int[][]{{1,0,1},{1,1,0},{1,1,0}}));
        System.out.println(new CountSquareSubmatricesWithAllOnes().countSquares(new int[][]{{0,1,1,1},{1,1,1,1},{0,1,1,1}}));
    }



    public int countSquares(int[][] matrix) {
        List<boolean[][]> list = new ArrayList<>();

        int count = 0;

        for(int[] arr : matrix){
            boolean[][] temp = new boolean[arr.length][arr.length];
            for(int i =0; i < arr.length; i++){
                temp[i][i] = arr[i]  == 1;
            }
            for(int i = 0 ; i < arr.length; i++){
                for(int j = i + 1; j < arr.length; j++){
                    temp[i][j] = temp[i][j-1] && arr[j] == 1;
                }
            }
            list.add(temp);
        }

        for(int i = 0; i < list.size(); i++){
            boolean[][] cur = list.get(i);
            for(int m = 0; m < cur.length; m++){
                for (int n = m; n < cur.length; n++){
                    if(!cur[m][n])
                        break;
                    boolean flag = true;
                    if(i + n - m >= list.size())
                        break;
                    for(int j = i + 1; j <= i + n - m; j ++){
                        boolean[][] temp = list.get(j);
                        if(!temp[m][n]){
                            flag = false;
                            break;
                        }
                    }
                    if(flag)
                        count++;
                }
            }
        }
        return count;
    }

}
