package contest;

import java.util.*;

public class T3 {
    public static void main(String[] args){
        System.out.println(new T3().findAllPeople(1,new int[][]{{5,1,4},{0,4,18}},1));
    }

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        TreeMap<Integer,List<int[]>> map = new TreeMap<>();
        HashSet<Integer> has = new HashSet<>();
        has.add(firstPerson);
        for(int[] m : meetings){
            List<int[]> list;
            if(map.containsKey(m[2])){
                list = map.get(m[2]);
                list.add(m);
            } else {
                list = new ArrayList<>();
                list.add(m);
            }
            map.put(m[2],list);
        }

        for(Integer time: map.keySet()){
            boolean flag = false;
            List<int[]> meets = map.get(time);
            for(Integer i : has){
                meets.add(new int[]{0,i,0});
            }
            HashMap<Integer,Set<Integer>> from = new HashMap<>();
            for(int[] meet : meets){
                if(has.contains(meet[0]) || has.contains(meet[1])){
                    flag = true;
                }
                Set<Integer> to;
                if(from.containsKey(meet[0])){
                    to = from.get(meet[0]);
                    to.add(meet[1]);
                } else {
                    to = new HashSet<>();
                    to.add(meet[1]);
                }
                from.put(meet[0],to);
                if(from.containsKey(meet[1])){
                    to = from.get(meet[1]);
                    to.add(meet[0]);
                } else {
                    to = new HashSet<>();
                    to.add(meet[0]);
                }
                from.put(meet[1],to);
            }
            if(!flag)
                continue;
            HashSet<Integer> visited = new HashSet<>();
            dfs(from,has,visited,0);
        }
        has.add(0);
        return new ArrayList<>(has);
    }

    public void dfs(HashMap<Integer,Set<Integer>> map, Set<Integer> path, Set<Integer> visited,int cur){
        if(visited.contains(cur))
            return;
        visited.add(cur);
        path.add(cur);
        if(map.containsKey(cur)){
            Set<Integer> to = map.get(cur);
            for(Integer i : to){
                dfs(map,path,visited,i);
            }
        }
    }
}
