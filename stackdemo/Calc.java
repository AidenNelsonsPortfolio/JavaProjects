package stackdemo;

import java.util.Scanner;
import java.util.Stack;

public class Calc {
    
    public static double evalPostFix(String s) throws IllegalArgumentException {
        Stack<Double> value = new Stack<>();
        
        double leftOp = 0, rightOp = 0;
        Scanner text = new Scanner(s);
        String data = null;    
        while(text.hasNext()) {
            //value.push(Double.parseDouble(text.next()));
            data = text.next();
            try {
                value.push(Double.parseDouble(data));
            }
            catch(NumberFormatException e) {
                if(value.size() < 2)
                    throw new IllegalArgumentException("String is not in postfix notation.");
                rightOp = value.pop();
                leftOp  = value.pop();
                
                switch(data) {
                    case "+": value.push(leftOp + rightOp); break;
                    case "-": value.push(leftOp - rightOp); break;
                    case "*": value.push(leftOp * rightOp); break;
                    case "/": value.push(leftOp / rightOp); break;
                    //case "%": 
                    case "^": value.push(Math.pow(leftOp,rightOp)); break;
                    default: throw new IllegalArgumentException("String is not in postfix notation.");
                }
            } // end of catch
        } // end of while parsing through String
        
        if(value.size() != 1)
            throw new IllegalArgumentException("String is not in postfix notation.");
        return value.peek();
        
    } // end of evalPostFix method() ************************************
    
    public static String infixToPostix(String s)throws IllegalArgumentException{
        s = s.replaceAll("\\(", " ( ");
        s = s.replaceAll("\\)", " ) ");
        s = s.replaceAll("\\+", " + ");
        s = s.replaceAll("-", " - ");
        s = s.replaceAll("\\*", " * ");
        s = s.replaceAll("/", " / ");
        s = s.replaceAll("\\^", " ^ ");
        s = s.replaceAll("%", " % ");
        
        Stack<String> ops = new Stack<>();
        StringBuilder postFix = new StringBuilder();
        String data;
        Scanner input = new Scanner(s);
        
        
        while(input.hasNext()){
            if(input.hasNextDouble())
                postFix.append(input.next()).append(" ");
            else{
                data = input.next();
                if(ops.isEmpty()||data.equals("(")){
                    ops.push(data);
                }
                else if(data.equals(")")){
                    while(!ops.isEmpty() && !ops.peek().equals("(")){
                        postFix.append(ops.pop()).append(" ");
                    }
                    if(ops.isEmpty()){
                        throw new IllegalArgumentException("Invalid infix expression.");
                    }
                    else
                        ops.pop(); //popping off the matching "(" from the stack
                }
                else{
                    while(precLevel(ops.peek())>= precLevel(data)&& !ops.isEmpty())
                        postFix.append(ops.pop()).append(" ");
                    ops.push(data);
                }
                
            }
        }
        while(!ops.isEmpty())
                postFix.append(ops.pop()).append(" ");
        
        return postFix.toString();
    }
    private static int precLevel(String s){
        int pLevel = 0;
        switch(s){
            case "^": pLevel++;
            case "*":
            case "/":pLevel++;
            case "+":
            case "-": pLevel++;
            
        }
        return pLevel;
    }
}