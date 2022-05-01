package stackdemo;

public class StackDemo {

    public static void main(String[] args) {
        String test = "3 4 + 5 * 4 10 2 - * - 3 +",
               test2= "4 3 8 2 + 3 - * 1 2 + * -",
               test3= "10 8 - 4 5 2 1 3 7 + - * ^ / -";
        String infixA = "((3+4) * (5-2)^2+4 *( 6 / 3 ))*3";
        String infixB = "1 - (2*1) + 1 / (2 - 1)";
        
        // Since evalPostFix is a static method in class Calc
        System.out.println(Calc.evalPostFix(test));
        System.out.println(Calc.evalPostFix(test2));
        System.out.println(Calc.evalPostFix(test3));
        System.out.println(Calc.evalPostFix(Calc.infixToPostix(infixA)));
        System.out.println(Calc.evalPostFix(Calc.infixToPostix(infixB)));
        // If it was not:
        // Calc myCalculator = new Calc();
        // myCalculator.evalPostFix(test);
        
    }
    
}
