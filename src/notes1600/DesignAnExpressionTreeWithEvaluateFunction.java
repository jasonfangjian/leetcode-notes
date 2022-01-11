package notes1600;


import java.util.Stack;

/*
1628. Design an Expression Tree With Evaluate Function

Given the postfix tokens of an arithmetic expression, build and return the binary expression tree that represents this expression.

Postfix notation is a notation for writing arithmetic expressions in which the operands (numbers) appear before their operators. For example, the postfix tokens of the expression 4*(5-(7+2)) are represented in the array postfix = ["4","5","7","2","+","-","*"].

The class Node is an interface you should use to implement the binary expression tree. The returned tree will be tested using the evaluate function, which is supposed to evaluate the tree's value. You should not remove the Node class; however, you can modify it as you wish, and you can define other classes to implement it if needed.

A binary expression tree is a kind of binary tree used to represent arithmetic expressions. Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (numbers), and internal nodes (nodes with two children) correspond to the operators '+' (addition), '-' (subtraction), '*' (multiplication), and '/' (division).

It's guaranteed that no subtree will yield a value that exceeds 109 in absolute value, and all the operations are valid (i.e., no division by zero).

Follow up: Could you design the expression tree such that it is more modular? For example, is your design able to support additional operators without making changes to your existing evaluate implementation?
 */
public class DesignAnExpressionTreeWithEvaluateFunction {
    abstract class Node {
        public abstract int evaluate();
        // define your fields here
    };


    class TreeNode extends Node{

        public TreeNode left;

        public TreeNode right;

        public String val;

        @Override
        public int evaluate(){
            // System.out.println(this.val);
            if(this.val.equals("+")){
                return this.left.evaluate() + this.right.evaluate();
            } else if( this.val.equals("-")){
                return this.left.evaluate() - this.right.evaluate();
            } else if(this.val.equals("*")){
                return this.left.evaluate() * this.right.evaluate();
            } else if(this.val.equals("/")){
                return this.left.evaluate() / this.right.evaluate();
            } else {
                return Integer.parseInt(this.val);
            }
        }


    }


    /**
     * This is the TreeBuilder class.
     * You can treat it as the driver code that takes the postinfix input
     * and returns the expression tree represnting it as a Node.
     */

    class TreeBuilder {
        Stack<TreeNode> stack = new Stack<>();
        Node buildTree(String[] postfix) {
            for(int i =0; i < postfix.length; i++){
                // System.out.println(stack);
                if(postfix[i].equals("+")){
                    TreeNode cur = helper(stack,postfix[i]);
                    stack.push(cur);
                } else if(postfix[i].equals("-")){
                    TreeNode cur = helper(stack,postfix[i]);
                    stack.push(cur);
                } else if(postfix[i].equals("*")){
                    TreeNode cur = helper(stack,postfix[i]);
                    stack.push(cur);
                } else if(postfix[i].equals("/")){
                    TreeNode cur = helper(stack,postfix[i]);
                    stack.push(cur);
                } else {
                    TreeNode temp = new TreeNode();
                    temp.val = postfix[i];
                    stack.push(temp);
                }
            }
            // System.out.println(stack);
            return stack.pop();
        }

        public TreeNode helper(Stack<TreeNode> stack, String v){
            TreeNode right = stack.pop();
            TreeNode left = stack.pop();
            TreeNode root = new TreeNode();
            root.val = v;
            root.left = left;
            root.right = right;
            return root;
        }
    };

}
