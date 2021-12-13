package notes1400;

import java.util.PriorityQueue;

/*
1405. Longest Happy String

A string is called happy if it does not have any of the strings 'aaa', 'bbb' or 'ccc' as a substring.

Given three integers a, b and c, return any string s, which satisfies following conditions:

s is happy and longest possible.
s contains at most a occurrences of the letter 'a',
at most b occurrences of the letter 'b' and at most c occurrences of the letter 'c'.
s will only contain 'a', 'b' and 'c' letters.
If there is no such string s return the empty string "".



Example 1:

Input: a = 1, b = 1, c = 7
Output: "ccaccbcc"
Explanation: "ccbccacc" would also be a correct answer.
Example 2:

Input: a = 2, b = 2, c = 1
Output: "aabbc"
Example 3:

Input: a = 7, b = 1, c = 0
Output: "aabaa"
Explanation: It's the only correct answer in this case.


Constraints:

0 <= a, b, c <= 100
a + b + c > 0
 */
public class LongestHappyString {
    public static void main(String[] args){
        System.out.println(new LongestHappyString().longestDiverseString(7,1,0));
    }

    static class Pair{
        char str;
        int num;
        public Pair(char str, int num){
            this.str = str;
            this.num = num;
        }
    }

    public String longestDiverseString(int a, int b, int c) {
        Pair p1 = new Pair('a',a);
        Pair p2 = new Pair('b',b);
        Pair p3 = new Pair('c',c);

        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y) -> y.num - x.num);

        if(a != 0)
        pq.offer(p1);
        if(b != 0)
        pq.offer(p2);
        if(c!=0)
        pq.offer(p3);
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            Pair most = pq.poll();
            Pair sec = pq.poll();
            if(sec == null){
                if(most.num >= 2){
                    sb.append(most.str);
                    sb.append(most.str);
                    break;
                } else {
                    sb.append(most.str);
                    break;
                }
            }
            if(most.num - sec.num >= 2){
                sb.append(most.str);
                sb.append(most.str);
                sb.append(sec.str);
                most.num = most.num - 2;
                sec.num = sec.num - 1;
                if(sec.num > 0){
                    pq.offer(sec);
                }
                pq.offer(most);
            } else {
                sb.append(most.str);
                most.num = most.num - 1;
                if(most.num > 0)
                    pq.offer(most);
                pq.offer(sec);
            }
        }
        return sb.toString();
    }

}
