package notes1600;

import java.util.*;

/*
1610. Maximum Number of Visible Points

You are given an array points, an integer angle, and your location,
where location = [posx, posy] and points[i] = [xi, yi] both denote integral coordinates on the X-Y plane.

Initially, you are facing directly east from your position.
You cannot move from your position, but you can rotate. In other words, posx and posy cannot be changed.
Your field of view in degrees is represented by angle, determining how wide you can see from any given view direction.
Let d be the amount in degrees that you rotate counterclockwise.
Then, your field of view is the inclusive range of angles [d - angle/2, d + angle/2].


You can see some set of points if, for each point, the angle formed by the point, your position,
and the immediate east direction from your position is in your field of view.

There can be multiple points at one coordinate. There may be points at your location,
and you can always see these points regardless of your rotation. Points do not obstruct your vision to other points.

Return the maximum number of points you can see.


Input: points = [[2,1},{2,2},{3,3]], angle = 90, location = [1,1]
Output: 3
Explanation: The shaded region represents your field of view. All points can be made visible in your field of view,
including [3,3] even though [2,2] is in front and in the same line of sight.

Input: points = [[2,1},{2,2},{3,4},{1,1]], angle = 90, location = [1,1]
Output: 4
Explanation: All points can be made visible in your field of view, including the one at your location.

Input: points = [[1,0},{2,1]], angle = 13, location = [1,1]
Output: 1
Explanation: You can only see one of the two points, as shown above.

1 <= points.length <= 105
points[i].length == 2
location.length == 2
0 <= angle < 360
0 <= posx, posy, xi, yi <= 100
 */
public class MaximumNumberOfVisiblePoints {


    public static void main(String[] args){
        List<List<Integer>> test = new ArrayList<>();
        List<Integer> p1 = new ArrayList<>();
        p1.add(0);
        p1.add(0);
        List<Integer> p2 = new ArrayList<>();
        p2.add(0);
        p2.add(2);
        test.add(p1);
        test.add(p2);

        List<Integer> loc = new ArrayList<>();
        loc.add(1);
        loc.add(1);

        System.out.println(new MaximumNumberOfVisiblePoints().visiblePoints(test,90,loc));
    }


    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int max = Integer.MIN_VALUE;
        int atPoints = 0;

        List<List<Integer>> transfer = new ArrayList<>();
        int index = 0;
        for(List<Integer> list : points){
            int x = list.get(0) - location.get(0);;
            int y = list.get(1) - location.get(1);
            if(x == 0 && y == 0){
                atPoints++;
                continue;
            }
            List<Integer> addT = new ArrayList<>();
            addT.add(x);
            addT.add(y);
            transfer.add(addT);
            index++;
        }

        Double[] degree = new Double[transfer.size()];
        index=0;
        for(List<Integer> pair : transfer){
            degree[index++] = calculateDegree(pair);
        }

        Arrays.sort(degree);
        List<Double> dg = new ArrayList<>(Arrays.asList(degree));
        for(double db : degree){
            dg.add(db+360);
        }

        int res = atPoints;
        for (int i = 0, j = 0; i < dg.size(); i++) {
            while (dg.get(i)- dg.get(j) > angle) {
                j++;
            }
            res = Math.max(res, atPoints + i - j + 1);
        }
        return res;
    }


    public double calculateDegree(List<Integer> point){
        return Math.atan2(point.get(1), point.get(0)) * (180 / Math.PI);
    }





}
