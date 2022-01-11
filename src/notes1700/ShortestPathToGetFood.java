package notes1700;

import java.util.*;
/*
1730. Shortest Path to Get Food

You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.

You are given an m x n character matrix, grid, of these different types of cells:

'*' is your location. There is exactly one '*' cell.
'#' is a food cell. There may be multiple food cells.
'O' is free space, and you can travel through these cells.
'X' is an obstacle, and you cannot travel through these cells.
You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.

Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.

 */
public class ShortestPathToGetFood {
    int row;
    int col;
    public int getFood(char[][] grid) {
        row = grid.length;
        col = grid[0].length;

        Queue<int[]> queue =new LinkedList<>();

        boolean[][] visited = new boolean[row][col];
        int startRow = -1;
        int startCol = -1;
        for(int i =0;i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == '*'){
                    startRow = i;
                    startCol = j;
                    break;
                }
            }
        }

        int[] start = new int[]{startRow,startCol,0};

        queue.offer(start);
        visited[startRow][startCol] = true;
        int count = 0;
        int[] dir = new int[]{0,1,0,-1,0};
        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i =0; i < size; i++){
                int[] cur = queue.poll();
                int curRow = cur[0];
                int curCol = cur[1];
                if(cur[2] == 1)
                    return count;
                for(int j =0; j < dir.length - 1; j++){
                    int nextRow = curRow + dir[j];
                    int nextCol = curCol + dir[j+1];

                    if(nextRow >= 0 && nextRow < row && nextCol >=0 && nextCol < col && !visited[nextRow][nextCol] && grid[nextRow][nextCol] != 'X'){
                        queue.offer(new int[]{nextRow,nextCol,grid[nextRow][nextCol] == '#' ? 1 : 0});
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
            count++;

        }
        return -1;
    }
}
