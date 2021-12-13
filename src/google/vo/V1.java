package google.vo;

import java.util.*;
/*
毛子老哥，给一个incoming data stream, 给一个distance D，
要求如果data stream中出现连续三个within the distance D的elements，就remove the three elements from the memory (aka扔掉)。
e.g.: input_stream = [0.1, 0.1, 0.3, 0.3, 0.2, 0.5, 0.5, 0.5, 0.4], D = 0.1，让return最后剩在memory里的elements
 */
public class V1 {

    public static void main(String[] args){
        System.out.println(new V1().solution(new double[]{0.1, 0.1, 0.3, 0.3, 0.2, 0.5, 0.5, 0.5, 0.4},0.1));
    }

    public Stack<Double> solution(double[] input, double distance){
        Stack<Double> stack = new Stack<>();
        if(input.length == 0)
            return stack;
        stack.push(input[0]);
        int count = 1;


        for(int i = 1; i  < input.length; i++){
            if(Math.abs(input[i] - stack.peek()) <= distance){
                count++;
                stack.push(input[i]);
                if(count == 3){
                    while (count > 0){
                        stack.pop();
                        count--;
                    }
                }
            } else {
                stack.push(input[i]);
                count=1;
            }
        }

        return stack;
    }


}
