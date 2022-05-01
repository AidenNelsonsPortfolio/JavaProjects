
package sortdemo;

import java.util.Random;

/**
 *
 * @author student
 */
public class SortDemo {

    private static final int SIZE = 1000;
    
    public static void main(String[] args) {
        long start =0, end = 0;
        Random rand = new Random();       

        
        //Sorting with bubble sort
        int[] wholeBunchOfRands = new int[SIZE];
        for(int i = 0; i < wholeBunchOfRands.length; i ++)
            wholeBunchOfRands[i] = rand.nextInt(1000);    
        start = System.nanoTime();
        Sorts.bubble(wholeBunchOfRands); 
        end = System.nanoTime();
        printArray(wholeBunchOfRands);
        if(inOrder(wholeBunchOfRands))
            System.out.println("Array is sorted now.");
        System.out.println("Bubble sorting took " + (end-start)/Math.pow(10,9) + " seconds.");
        System.out.println("");
        
        //Sorting with selection sort
        int[] num = new int[SIZE];
        for(int i = 0; i < num.length; i ++)
            num[i] = rand.nextInt(5000);
        System.out.println("");
        start = System.nanoTime();
        Sorts.selection(num);
        end = System.nanoTime();
        printArray(num);
        System.out.println("");
        System.out.println("Time of selectionSort == "+ (end-start)/Math.pow(10,9) + " seconds.");
        if(inOrder(num))
            System.out.println("Array is now Sorted");
        System.out.println("");
        
        
        num = new int[SIZE];
        for(int i = 0; i < num.length; i ++)
            num[i] = rand.nextInt(5000);
        System.out.println("");
        start = System.nanoTime();
        Sorts.insertion(num);
        end = System.nanoTime();
        printArray(num);
        System.out.println("");
        System.out.println("Time of insertionSort == "+ (end-start)/Math.pow(10,9) + " seconds.");
        if(inOrder(num))
            System.out.println("Array is now Sorted");
        
        num = new int[SIZE];
        for(int i = 0; i < num.length; i ++)
            num[i] = rand.nextInt(5000);
        System.out.println("");
        start = System.nanoTime();
        Sorts.quick(num);
        end = System.nanoTime();
        printArray(num);
        System.out.println("");
        System.out.println("Time of quickSort == "+ (end-start)/Math.pow(10,9) + " seconds.");
        if(inOrder(num))
            System.out.println("Array is now Sorted");
        
         num = new int[SIZE];
        for(int i = 0; i < num.length; i ++)
            num[i] = rand.nextInt(5000);
        System.out.println("");
        start = System.nanoTime();
        Sorts.merge(num);
        end = System.nanoTime();
        printArray(num);
        System.out.println(num[1] + "" + num[2] + num[3]);
        System.out.println("Time of mergeSort == "+ (end-start)/Math.pow(10,9) + " seconds.");
        if(inOrder(num))
            System.out.println("Array is now Sorted");
        
    }
    private static void printArray(int[] a){
    
        for(int i : a)
            System.out.print(i + ",");
        System.out.println("\b ");
        
    }
    public static boolean inOrder(int[] a){
        for(int i= 0; i < a.length-1; i ++)
            if(a[i]> a[i+1])
                return false;
        return true;
    }
}

