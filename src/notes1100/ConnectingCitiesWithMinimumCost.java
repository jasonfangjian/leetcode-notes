package notes1100;


import java.util.Arrays;

/*
1135. Connecting Cities With Minimum Cost


There are n cities labeled from 1 to n. You are given the integer n and an array connections where connections[i] = [xi, yi, costi] indicates that the cost of connecting city xi and city yi (bidirectional connection) is costi.

Return the minimum cost to connect all the n cities such that there is at least one path between each pair of cities. If it is impossible to connect all the n cities, return -1,

The cost is the sum of the connections' costs used.



Example 1:


Input: n = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation: Choosing any 2 edges will connect all cities so we choose the minimum 2.
Example 2:


Input: n = 4, connections = [[1,2,3],[3,4,4]]
Output: -1
Explanation: There is no way to connect all cities even if all edges are used.


Constraints:

1 <= n <= 104
1 <= connections.length <= 104
connections[i].length == 3
1 <= xi, yi <= n
xi != yi
0 <= costi <= 105
 */
public class ConnectingCitiesWithMinimumCost {

    int[] parent;
    int n;

    /*
    Todo:need more review: Java Kruskal’s Minimum Spanning Tree Algorithm with Union Find
     */
    private void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px != py) {
            parent[px] = py;
            n--;
        }
    }

    private int find(int x) {
        if (parent[x] == x) {
            return parent[x];
        }
        parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    public int minimumCost(int N, int[][] connections) {
        parent = new int[N + 1];
        n = N;
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        Arrays.sort(connections, (a, b) -> (a[2] - b[2]));

        int res = 0;

        for (int[] c : connections) {
            int x = c[0], y = c[1];
            if (find(x) != find(y)) {
                res += c[2];
                union(x, y);
            }
        }

        return n == 1 ? res : -1;
    }
}
