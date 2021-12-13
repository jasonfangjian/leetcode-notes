package notes1000;

import java.util.*;

public class LongestStringChain {
    /*
    1048. Longest String Chain
    You are given an array of words where each word consists of lowercase English letters.

    wordA is a predecessor of wordB if and only if we can insert exactly one letter
    anywhere in wordA without changing the order of the other characters to make it equal to wordB.

    For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
    A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is
    a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word
    chain with k == 1.

    Return the length of the longest possible word chain with words chosen from the given list of words.

    Example 1:

    Input: words = ["a","b","ba","bca","bda","bdca"]
    Output: 4
    Explanation: One of the longest word chains is ["a","ba","bda","bdca"].

    Example 2:

    Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
    Output: 5
    Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].

    Example 3:

    Input: words = ["abcd","dbqca"]
    Output: 1
    Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
    ["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.

    Constraints:
    1 <= words.length <= 1000
    1 <= words[i].length <= 16
    words[i] only consists of lowercase English letters.
     */
    public static void main(String[] args){
        System.out.println(new LongestStringChain().longestStrChain(new String[]{"a","ab","ac","bd","abc","abd","abdd"}));
    }

// Wrong Answer
//    static class Pair{
//        char[] arr;
//        Integer len;
//    }
//
//    public int longestStrChain(String[] words) {
//        Arrays.sort(words, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.length() - o2.length();
//            }
//        });
//
//        List<Pair> list = new ArrayList<>();
//        if (words.length == 1)
//            return 1;
//        int index = 1;
//        Pair pair = createPair(words[0],1);
//        list.add(pair);
//        while (words[index].length() ==words[0].length()){
//           Pair temp = createPair(words[index],1);
//            list.add(temp);
//            index++;
//        }
//
//        int max_length = 1;
//
//        for(int i = index; i < words.length; i++){
//            boolean flag = false;
//            for(Pair p : list){
//                if(p.arr.length + 1 == words[i].length()){
//                    int count = 0;
//                    int cur = 0;
//                    char[] temp = words[i].toCharArray();
//                    for(int j = 0; j < temp.length; j++){
//                        char c = temp[j];
//                        if(p.arr[cur] != c){
//                            count++;
//                            if(count > 1){
//                                break;
//                            }
//                            cur--;
//                        }
//                        cur++;
//                        if(cur == p.arr.length && j < temp.length-1){
//                            count++;
//                            break;
//                        }
//                    }
//                    if(count == 1){
//                        flag = true;
//                        p.arr = words[i].toCharArray();
//                        p.len = p.len + 1;
//                        max_length = Math.max(max_length,p.len);
//                    }
//                }
//            }
//            if(!flag){
//                Pair t = createPair(words[i],1);
//                list.add(t);
//            }
//        }
//
//        return max_length;
//
//    }
//
//
//    public Pair createPair(String str, int len){
//        HashSet<Character> initSet = new HashSet<>();
//        Pair pair = new Pair();
//        pair.arr = str.toCharArray();
//        pair.len = len;
//        return pair;
//    }




    //works
    public static class Pair{
        String str;
        int len;

        public Pair(String s, int len){
            this.str =s;
            this.len = len;
        }
    }

    int max_length = 1;
    public int longestStrChain(String[] words) {
        TreeMap<Integer, List<Pair>> map = new TreeMap<>();
        for(String str: words){
            List<Pair> temp = map.getOrDefault(str.length(), new ArrayList<>());
            temp.add(new Pair(str,1));
            map.put(str.length(),temp);
        }

        List<Integer> key = new ArrayList<>(map.keySet());
        if(key.size() == 1)
            return 1;
        for(int i = 1 ; i < key.size(); i++){
            if(key.get(i) -1 == key.get(i-1)){
                List<Pair> cur = map.get(key.get(i));
                List<Pair> prev = map.get(key.get(i-1));

                for(Pair cp : cur) {
                    for(Pair pp : prev){
                        if(check(pp.str,cp.str)){
                            cp.len = Math.max(cp.len ,pp.len+1);
                            max_length = Math.max(max_length,cp.len);
                        }
                    }
                }
            }
        }

        return max_length;
    }

    public boolean check(String str1, String str2){
        char[] a1 = str1.toCharArray();
        char[] a2 = str2.toCharArray();

        int lo = 0;
        int hi = 0;
        int count = 0;

        while(lo < str1.length() && hi < str2.length()){
            if(a1[lo] != a2[hi]){
                count++;
                if(count > 1)
                    return false;
                hi++;
            } else {
                lo++;
                hi++;
            }
        }

        if(count == 1 && hi == str2.length() -1)
            return false;
        return true;

    }
    


}
