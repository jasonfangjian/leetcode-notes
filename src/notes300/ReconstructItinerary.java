package notes300;


import java.util.*;

/*
332. Reconstruct Itinerary

You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.

All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.

For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.



Example 1:


Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
Output: ["JFK","MUC","LHR","SFO","SJC"]
Example 2:


Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.


Constraints:

1 <= tickets.length <= 300
tickets[i].length == 2
fromi.length == 3
toi.length == 3
fromi and toi consist of uppercase English letters.
fromi != toi
 */
public class ReconstructItinerary {
    public static void main(String[] args){

    }


    List<String> res;
    public List<String> findItinerary(List<List<String>> tickets) {
        res = new ArrayList<>();
        HashMap<String, TreeSet<String>> map = new HashMap<>();
        HashMap<String,Integer> count = new HashMap<>();
        for(List<String> list : tickets){
            TreeSet<String> set = map.getOrDefault(list.get(0),new TreeSet<String>());
            set.add(list.get(1));
            map.put(list.get(0),set);
            count.put(list.get(0) + list.get(1),count.getOrDefault(list.get(0) + list.get(1),0)+1);
        }
        List<String> path = new ArrayList<>();
        path.add("JFK");
        helper(count,map,tickets.size()+1,path,"JFK");

        return res;

    }

    public void helper(HashMap<String,Integer> count, HashMap<String,TreeSet<String>> map, int target,List<String> path,String cur){
        if(res.size() != 0)
            return;
        if(path.size() == target){
            if(res.size() == 0){
                res = new ArrayList<>(path);
            }
            return;
        }
        TreeSet<String> set = map.get(cur);
        if(set != null){
            for(String str : set){
                if(count.get(cur+str) > 0){
                    path.add(str);
                    count.put(cur+str,count.get(cur+str)-1);
                    helper(count,map,target,path,str);
                    path.remove(path.size()-1);
                    count.put(cur+str,count.get(cur+str)+1);
                }
            }
        }
    }

}
