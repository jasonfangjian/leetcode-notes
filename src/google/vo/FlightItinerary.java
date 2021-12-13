package google.vo;


import java.util.*;

/*
2nd
给定机票arr, 机票格式 A->B [A, B, 起飞时间, 落地时间]
给定origin城市和destination城市
1) 判断origin能不能到达给定origin城市和destination城市
dijkstra, 记得判断一下转机走的边的合法性就可以。

2) 从origin到destination到的最少等待时间，不一定是最短时间。
写了个暴力dfs，面试官问可以优化么，说了个memo可以优化，他好像觉得可以。我感觉其实不太行。。。
 */
public class FlightItinerary {
    public static void main(String[] args){
        FlightItinerary test = new FlightItinerary();

        String[][] flights = new String[][]{
                {"A","B","10","20"},
                {"A", "B","11","15"},
                {"A","B","8","18"},
                {"B","C","14","24"},
                {"B","C","16","26"},
                {"C","D","23","40"}
        };
        System.out.println(test.question1(flights,"A","C"));
        System.out.println(test.question1(flights,"B","D"));
        System.out.println(test.question1(flights,"A","D"));

        String[][] flights2 = new String[][]{
                {"A","B","10","200"},
                {"A", "B","11","15"},
                {"A","B","8","18"},
                {"A","B","201","300"},
                {"B","C","14","24"},
                {"B","C","16","26"},
                {"B","C","23","40"},
                {"C","D","23","40"},
                {"C","D","401","500"},
//                {"B","C","301","400"}//2
        };
        System.out.println(new FlightItinerary().shortestWaitTime(flights2,"A","D"));
    }


    public static class Info {
        String cur;
        Integer landTime;
        public Info(String cur, Integer landTime){
            this.cur = cur;
            this.landTime = landTime;
        }
    }


    public boolean question1(String[][] flights,String src, String dst){


        for(int i = 0; i < flights.length; i++){
            List<String[]> cur = graph.getOrDefault(flights[i][0],new ArrayList<>());
            cur.add(new String[]{flights[i][1],flights[i][2],flights[i][3]});
            graph.put(flights[i][0],cur);
        }
        HashMap<String,Integer> map = new HashMap<>();

        Queue<Info> queue = new LinkedList<>();
        queue.offer(new Info(src,-1));
        while (!queue.isEmpty()){
            Info temp = queue.poll();
            if(temp.cur.equals(dst))
                return true;
            map.put(temp.cur,temp.landTime);
            if(!graph.containsKey(temp.cur))
                continue;
            List<String[]> list = graph.get(temp.cur);
            for(String[] sa : list){
                if(Integer.parseInt(sa[1]) >= temp.landTime){
                    if(!map.containsKey(sa[0]) || map.get(sa[0]) > Integer.parseInt(sa[2])){
                        queue.offer(new Info(sa[0],Integer.parseInt(sa[2])));
                    }
                }
            }
        }
        return false;
    }

    int minWait = -1;
    HashMap<String,List<String[]>> graph = new HashMap<>();
    public int shortestWaitTime(String[][] flights, String src, String dst){
        for(int i = 0; i < flights.length; i++){
            List<String[]> cur = graph.getOrDefault(flights[i][0],new ArrayList<>());
            cur.add(new String[]{flights[i][1],flights[i][2],flights[i][3]});
            graph.put(flights[i][0],cur);
        }
        List<String[]> list = graph.get(src);
        for(String[] str : list){
            helper(new Info(str[0],Integer.parseInt(str[2])),dst,0);
        }
        return minWait;
    }
    //记录cur station 和 landTime，以及之前遍历到的最短时间
    HashMap<String, Integer> used = new HashMap<>();
    public void helper(Info info, String dst, int wait){
        if(dst.equals(info.cur)){
            minWait = minWait < 0 ? wait : Math.min(wait,minWait);
            return;
        }

        if(used.containsKey(info.cur + info.landTime) && used.get(info.cur + info.landTime) <= wait)
            return;
        List<String[]> list = graph.get(info.cur);
        for(String[] sa : list) {
            if (Integer.parseInt(sa[1]) >= info.landTime) {
                int newWait = wait + Integer.parseInt(sa[1]) - info.landTime;
                if (newWait <= minWait || minWait < 0)
                    helper(new Info(sa[0], Integer.parseInt(sa[2])), dst, newWait);
            }
        }
        used.put(info.cur + info.landTime, wait);
    }
}
