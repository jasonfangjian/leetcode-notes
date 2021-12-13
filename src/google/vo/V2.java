package google.vo;

import java.util.*;
/*
澳洲a(ustralian)bc老姐，pointwise sum.
给两个time series (time_stamp, value) T1=[(0, 0), (3, 1), (5,2), (7,0)] T2=[(0, 0), (4,1),(10,0)]
输出结果Ts = [(0, 0), (3, 1), (4, 2), (5, 3), (7,1), (10, 0)] 如图（图2）
 */
public class V2 {
    public static void main(String[] args){
        List<int[]> res = new V2().solution(new int[][]{{0, 0}, {3, 1}, {5,2}, {7,0}},new int[][]{{0, 0}, {4,1},{10,0}});
        for(int[] i : res){
            System.out.println(Arrays.toString(i));
        }
    }

    public List<int[]> solution(int[][] input1, int[][] input2){
        int l1 = 0;
        int l2 = 0;
        List<int[]> res = new ArrayList<>();
        while (l1 < input1.length && l2 < input2.length){
            int[] tuple1 = input1[l1];
            int[] tuple2 = input2[l2];
            int[] newPair;
            if(tuple2[0] == tuple1[0]){
                newPair = new int[]{tuple1[0],tuple1[1] + tuple2[1]};
                l1++;
                l2++;
            } else if(tuple2[0] > tuple1[0]){
                if(l2 == 0){
                    newPair = new  int[]{tuple1[0],tuple1[1]};
                } else {
                    newPair = new int[]{tuple1[0],tuple1[1] + input2[l2-1][1]};
                }
                l1++;
            } else {
                if(l1 == 0){
                    newPair = new  int[]{tuple2[0],tuple2[1]};
                } else {
                    newPair = new int[]{tuple2[0],tuple2[1] + input1[l1-1][1]};
                }
                l2++;
            }
            res.add(newPair);
        }

        if(l1 == input1.length){
            while (l2 < input2.length){
                int[] newPair = new int[]{input2[l2][0], input2[l2][1] +  input1[l1-1][1]};
                res.add(newPair);
                l2++;
            }
        }
        if(l2 == input2.length){
            while (l1 < input1.length){
                int[] newPair = new int[]{input1[l1][0], input1[l1][1] + input2[l2-1][1]};
                res.add(newPair);
                l1++;
            }
        }
        return res;
    }

}
