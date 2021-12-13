package google.vo;


import java.util.Arrays;
import java.util.HashSet;

/*
3rd
树 delete_node
给定一棵树，形式为parent数组，例如
    0
   / \
  1   2
 /   / \
3   4   5
tree = [-1, 0, 0, 1, 2, 2]

1) delete one node, point all children to its parent
delete 2之后，tree变成[-1, 0, '*', 1, 0, 0]

2) delete one node and its subtree
delete 2之后，tree变成[-1, 0, '*', 1, '*', '*']


    0
   / \
  1   2
 /   / \
3   4   5
     \  / \
     6  7  8

[-1,0,0,1,2,2,4,5,5] -> [-1,0,'*',1,0,0,4,5,5]
[-1,0,'*',1,'*','*','*','*','*']
 */
public class DeleteNodeInTree {
    public  static void main(String[] args){
        System.out.println(Arrays.toString(new DeleteNodeInTree().solution1(new String[]{"-1", "0", "0", "1", "2", "2", "4", "5", "5"}, 2)));
        System.out.println(Arrays.toString(new DeleteNodeInTree().solution2(new String[]{"-1", "0", "0", "1", "2", "2", "4", "5", "5"}, 2)));
    }



    public String[] solution1(String[] input, int delete){
        String temp = input[delete];
        input[delete] = "*";
        for(int i =0; i < input.length; i++){
            if(input[i].equals(String.valueOf(delete)))
                input[i] = temp;
        }
        return input;
    }

    public String[] solution2(String[] input, int delete){
        HashSet<String> deleted = new HashSet<>();
        deleted.add(String.valueOf(delete));
        input[delete] = "*";
        for(int i = 0; i < input.length; i++){
            if(deleted.contains(input[i])){
                input[i] = "*";
                deleted.add(String.valueOf(i));
            }
        }

        return input;
    }

}
