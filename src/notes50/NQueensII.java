package notes50;


import java.util.HashSet;

/*
52. N-Queens II


The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.


Input: n = 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown.

Input: n = 1
Output: 1


Constraints:

1 <= n <= 9

 */
public class NQueensII {

    public static void main(String[] args){
        System.out.println(new NQueensII().totalNQueens(9));
    }

    HashSet<Integer> rowSet = new HashSet<>();
    HashSet<Integer> colSet  = new HashSet<>();
    HashSet<Integer> diaSet  = new HashSet<>();
    HashSet<Integer> diarSet  = new HashSet<>();

    public int totalNQueens(int n) {
        check(0,n);
        return res;
    }
    int res;
    public void check(int row,int n){
        if(row == n){
            res++;
            return;
        }
        for(int i = 0; i < n; i++){
            if(rowSet.contains(row) || colSet.contains(i) || diaSet.contains(row - i) || diarSet.contains(row + i))
                continue;
            rowSet.add(row);
            colSet.add(i);
            diaSet.add(row - i);
            diarSet.add(row + i);
            check(row+1, n);
            rowSet.remove(row);
            colSet.remove(i);
            diaSet.remove(row - i);
            diarSet.remove(row + i);
        }
    }

}
