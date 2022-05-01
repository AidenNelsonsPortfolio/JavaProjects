/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlistdemo;

/**
 *
 * @author student
 */
public class LinkedListDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long start, end;
        //To use Java's LinkedList: 
        // LinkedList<Integer> myList = new LinkedList<>
        
        LinkedListDriver myList = new LinkedListDriver();
                

        System.out.println(myList.length()+" : " + myList);
        myList.add(15);
        System.out.println(myList.length()+" : " + myList);
        myList.add(12);
        System.out.println(myList.length()+" : " + myList);
        myList.add(8);
        System.out.println(myList.length()+" : " + myList);
        myList.add(6);
        System.out.println(myList.length()+" : " + myList);
        myList.add(20);
        System.out.println(myList.length()+" : " + myList);
        myList.add(25);
        System.out.println(myList.length()+" : " + myList);
        myList.add(3);
        System.out.println(myList.length()+" : " + myList);
        myList.add(10);
        System.out.println(myList.length()+" : " + myList);
        myList.add(10);
        System.out.println(myList.length()+" : " + myList);
        System.out.println(myList.hashCode());
        LinkedListDriver what = new LinkedListDriver();
        what.add(10);
        System.out.println(what + "Hiiiii");
        what = myList;
        System.out.println(what+ "" + myList.hashCode());
        start = System.nanoTime();
        myList.add(12);
        end = System.nanoTime();
        
        System.out.println((end-start)+" time in nanoseconds to add one element.");
        
        System.out.println(myList.length()+" : " + myList);
        myList.add(-10);
        System.out.println(myList.length()+" : " + myList);
        myList.add(-50);
        System.out.println(myList.length()+" : " + myList);
        myList.add(-5);
        System.out.println(myList.length()+" : " + myList);
        
        //Check contains()
        
        System.out.println("11? "+ myList.contains(11));
        System.out.println("12? "+ myList.contains(12));
        System.out.println("50? "+ myList.contains(50));
        System.out.println("100? "+ myList.contains(100));
        System.out.println("-10? "+ myList.contains(-10));
        System.out.println("-100? "+ myList.contains(-100));
        
        //Check remove()
        
         System.out.println(myList.length()+" : " + myList + "  (before removal)");

        myList.remove(500);
        myList.remove(25);
        myList.remove(-50);
        myList.remove(12); 
        
        System.out.println(myList.length()+" : " + myList + "  (after removal)");
        
        for(int i = 0; i < 10; i ++){
            myList.add(16);
        }
        System.out.println(myList.length() + ": " + myList);
        myList.remove(15, -3);
        myList.remove(1000, 1);
        myList.remove(16, 3);
        myList.removeAll(10);
        myList.removeAll(25);
        System.out.println(myList.length() + ": " + myList);
        myList.removeAll(16); //Removes the rest of the 10 16's (7 were left from above for loop adding them)
        System.out.println(myList.length() + ": " + myList);



    }
    
    
}

    

