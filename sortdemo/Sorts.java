
package sortdemo;

import java.util.Random;


public class Sorts {
    
    
    
    
    public static void bubble(int[] a){
    
        boolean swapped = false;
        int temp, times = 0;
        do{
           swapped = false;
           times++;
           for(int index = 0; index < a.length-times; index++)
             if(a[index]>a[index+1]){
                temp = a[index];
                a[index] = a[index+1];
                a[index+1] = temp;
                swapped = true;
            }
        }while(swapped&&times< a.length-1);
            
    }
    public static void selection(int[] a){
            
        int minIndex = 0, temp = 0;
        
        for(int i = 0; i < a.length-1; i ++){
            minIndex = i;
            for(int j = i+1; j < a.length; j ++){
                if(a[j]<a[minIndex])
                    minIndex = j;
            }
            if(minIndex != i){
                temp = a[i];
                
                a[i] = a[minIndex];
                a[minIndex] = temp;
            }
        }
    }
    public static void insertion(int[] a){
        
        int curValue = 0;
        for(int i = 1; i < a.length; i ++){
            curValue = a[i];
            int j = i-1;
            while(j>= 0 && a[j] > curValue){
                a[j+1] = a[j];
                j--;
            }
            
            a[j+1] = curValue;
                
        }
    }
    public static void quick(int[] a){
        quickR(a, 0, a.length-1);
    }
    private static void quickR(int[] a, int low, int high){
        
        int pIndex = findIndex(a,low,high);
        if(low < pIndex-1)
            quickR(a, low, pIndex-1);
        if(high > pIndex+1)
            quickR(a, pIndex+1, high);
    }
    private static int findIndex(int[] a, int low, int high){
        int pValue = a[low];
        while(low<high){
        //Highside
        
        while(a[high] >= pValue&& low<high)
            high--;
        
        if(low != high){
        a[low] = a[high];
        low++;
        }
        //Lowside
        while(a[low] <= pValue&& low < high)
            low++;
        if(low!= high){
        a[high] = a[low];
        high--;
        }
        }
        a[low] = pValue;
        return low;
    }
    public static void merge(int[] a){
       int[] aCopy = a.clone();
       order(aCopy, a, 0, a.length-1);
    
    }
    private static void order(int[] source, int[] dest, int start, int end){
        if(start != end){
            order(dest, source,start, (start+end)/2);
            order(dest, source, (start+end)/2+1, end);
            mergeWConditionalSort(source, dest, start, end); 
        }
        
        
    }
    private static void mergeA(int[] source, int[] dest, int start, int end){
        int leftIndex = start;
        int rightIndex = (start+end)/2+1;
        int destIndex = start;
        while(leftIndex<= (start+end)/2 && rightIndex<= end){
            if(source[leftIndex] <= source[rightIndex])
                dest[destIndex++] = source[leftIndex++]; 
            else
                dest[destIndex++] = source[rightIndex++]; 
            //USING POST INCREMENTS FOR THIS
            // WOULD NOT WORK, have to use post increments, and use destIndex increments after all if/else stuff is done:  dest[destIndex] = (source[leftIndex]<= source[rightIndex])? source[leftIndex]: source[rightIndex];         
        }
        if(rightIndex > end)
            while(leftIndex <= (start+end)/2)
                dest[destIndex++] = source[leftIndex++];
        else
            while(rightIndex <= end)
                dest[destIndex++] = source[rightIndex++];
    }
    private static void mergeWElseIf(int[] source, int[] dest, int start, int end){
        int leftIndex = start;
        int rightIndex = (start+end)/2+1;
        int destIndex = start;
        while(leftIndex<= (start+end)/2 || rightIndex<= end){
            if(rightIndex <= end && source[leftIndex] <= source[rightIndex])
                dest[destIndex++] = source[leftIndex++]; 
            else if(leftIndex <= (start+end)/2)
                dest[destIndex++] = source[rightIndex++];
            else if(rightIndex > end)
                dest[destIndex++] = source[leftIndex++];
            else
                dest[destIndex++] = source[rightIndex++];
            //USING POST INCREMENTS FOR THIS
            // WOULD NOT WORK:  dest[destIndex] = (source[leftIndex]<= source[rightIndex])? source[leftIndex]: source[rightIndex]; 
        }
    }
    private static void mergeWStoredValue(int[] source, int[] dest, int start, int end){
        int leftIndex = start;
        int midValue = (start+end)/2;
        int rightIndex = midValue+1;
        int destIndex = start;
        while(leftIndex<= midValue && rightIndex<= end){
            if(source[leftIndex] <= source[rightIndex])
                dest[destIndex++] = source[leftIndex++]; 
            else
                dest[destIndex++] = source[rightIndex++]; 
            //USING POST INCREMENTS FOR THIS
            // WOULD NOT WORK, have to use post increments, and use destIndex increments after all if/else stuff is done:  dest[destIndex] = (source[leftIndex]<= source[rightIndex])? source[leftIndex]: source[rightIndex];         
        }
        if(rightIndex > end)
            while(leftIndex <= midValue)
                dest[destIndex++] = source[leftIndex++];
        else
            while(rightIndex <= end)
                dest[destIndex++] = source[rightIndex++];
    }
    private static void mergeWConditionalSort(int[] source, int[] dest, int start, int end){
        int leftIndex = start;
        int midValue = (start+end)/2;
        int rightIndex = midValue+1;
                

        int destIndex = start;
        while(leftIndex<= midValue && rightIndex<= end){
            dest[destIndex++] = (source[leftIndex]<= source[rightIndex])? source[leftIndex++]: source[rightIndex++];
            /*
            if(source[leftIndex] <= source[rightIndex])
                dest[destIndex++] = source[leftIndex++]; 
            else
                dest[destIndex++] = source[rightIndex++]; 
        */
            //USING POST INCREMENTS FOR THIS
            // WOULD NOT WORK, have to use post increments, and use destIndex increments after all if/else stuff is done:  dest[destIndex] = (source[leftIndex]<= source[rightIndex])? source[leftIndex]: source[rightIndex];         
        }
        if(rightIndex > end)
            while(leftIndex <= midValue)
                dest[destIndex++] = source[leftIndex++];
        else
            while(rightIndex <= end)
                dest[destIndex++] = source[rightIndex++];
    }
    
    public static void bozo(int[] a){
        Random rand = new Random();
        int first = -1, second, storage;
        while(!SortDemo.inOrder(a)){
            first = rand.nextInt(a.length)-1;
            second = rand.nextInt(a.length)-1;
            while(first == second){
                second = rand.nextInt(a.length)-1;
            }
            storage = a[second];
            a[second] = a[first];
            a[first] = storage;
        }
    }

}
