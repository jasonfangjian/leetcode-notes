package google.vo;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
1st
写一个logger类. 成员函数 started(timestamp, request_id), finished(timestamp, request_id)
1) 往log里面输出request_id started at xxx, finished at xxx.
维护一个hashmap存start time，finished的时候输出到log就可以了。
2) 要求按照start的先后顺序输出log
维护两个hashmap，一个存start time，另一个存end time
维护一个queue，按照start先后顺序入栈。
每次调用finished的时候循环判断栈头是否完成并输出到log
3) 有长log怎么办
每次调用finished判断一下栈头元素是不是存在很久了，是的话移到一个新的long request queue，没写代码，讲了下思路
 */
public class LoggerSystem {
    HashMap<String, Integer> map = new HashMap<>();

    HashMap<String,Integer> finished = new HashMap<>();

    PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> map.get(a) - map.get(b));

    public static void main(String[] args){
        LoggerSystem test  = new LoggerSystem();
        test.started1(100,"1");
        test.started1(105,"2");
        test.finished1(110,"2");
        test.finished1(120,"1");


        test.started2(100,"1");
        test.started2(105,"2");
        test.finished2(110,"2");
        test.finished2(120,"1");
    }

    public void started1(int timestamp, String request_id){
        map.put(request_id,timestamp);
    }

    public void finished1(int timestamp, String request_id){
        System.out.println(request_id + " started at " + map.get(request_id) + " and finished at " + timestamp);
    }

    public void  started2(int timestamp, String request_id){
        map.put(request_id,timestamp);
        pq.offer(request_id);
    }

    public void finished2(int timestamp, String request_id){
        finished.put(request_id,timestamp);
        while (!pq.isEmpty() && finished.containsKey(pq.peek())){
            String cur = pq.poll();
            System.out.println(cur + " started at " + map.get(cur) + " and finished at " + finished.get(cur));
        }
    }


}
