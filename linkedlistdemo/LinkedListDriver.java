/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlistdemo;


public class LinkedListDriver {
    
    private Node head;
    private int size;
    public LinkedListDriver(){
        size = 0;
        head = null;
    }
    
    //Adds an element to the linkedlist
    public void add(int value){
        if(size == 0){
            head = new Node(value);
            size = 1;
            return;
            
        }
        //If the value should be added to the front of the list
        Node nodeToAdd = new Node(value);
        if(head.data > value){
            nodeToAdd.next = head;
            head = nodeToAdd;
            size++;
        }
        else{
        // Traverse the list to find the spot to add "nodeToAdd"
        Node temp = head; 
        while(temp.next != null && temp.next.data < value)
            temp = temp.next;
        if(temp.next == null)
            temp.next = nodeToAdd;
        else {//Adding  in the middle of the list a number (still in sorted order, of course, true for all of these methods)
            nodeToAdd.next = temp.next;
            temp.next = nodeToAdd;
        }
        size++;
        }
    }
    
    //We have to decide for the following method if we are going to remove an index, all of a number, or just the first number specified.
    //Remove one element below
    public void remove(int value){
        if(contains(value)){

            if(size == 1)
                head = null;
            else if(head.data == value){
                Node nodeToRemove = head;
                head = head.next;
                nodeToRemove.next = null;
            }
            else {
                Node nodeToRemove = null, previous = head;
                while(previous.next != null && previous.next.data != value)
                    previous= previous.next;
                if(previous.next != null){
                    if(previous.next.next == null){
                        //Removing the last node
                        previous.next = null;
                    }
                    else{ 
                        //Removing anything other than the last node in a list (but still not the first node, 
                        //either, list has to be of size > 1
                        nodeToRemove = previous.next;
                        previous.next = nodeToRemove.next;
                        nodeToRemove.next = null;
                    }
                }
            
            }    
            size--;
        } //end of if statement (as long as something exists in the list
    } //end of method
    
    //Remove all of a specified data type
    public void removeAll(int value){
        while(contains(value)){
            remove(value);
        } //Not optimal for time complexity, sould have to write your own routine to find all elements, 
        //reducing time complexity from n to n^k
    }
    //Remove a certain number a specified number of times from the list.
    public void remove(int value, int quantity){
        int timesRemoved = 0;
        while(timesRemoved < quantity && contains(value)){
            remove(value);
            timesRemoved++; 
            //Or, could have quantity be decremented while it is greater than zero and timesRemoved < quantity
        }
            
    }
    public boolean contains(int n){
        if(size == 0)
            return false;
        else if(head.data == n)
            return true;
        else if(head.data > n)
            return false;
        else{
            Node temp = head;
            while(temp.next != null){
                temp = temp.next;
                if(temp.data == n)
                    return true;
                else if(temp.data > n)
                    return false;
            }
        }
        //If you exhaust the list fully, like if a number is greater than the largest number in the linkedList
        return false;
    }
    
    //Length / size of the list
    public int length(){
        return size;
    }
    
    @Override
    public String toString(){
        StringBuilder list = new StringBuilder();
        //Use a string builder if it is going to take too long to have to copy concantenate strings over and over (doesn't leave any 
        //floating chunks, keeps it all constantly together
        if(size > 0){
            Node temp = head;
            for(int i = 0; i < size; i ++){
                list = list.append(temp.data).append(",");
                temp = temp.next;
            }
            return list.substring(0,list.length()-1);
        }
        return "";
    }
    //Below is just a demo, will finish it up next time
    private class Node{
        //If the specifiers for data and next were to be private, we need getters and setters, as we would...
        //have to change next values for whenever we add/subtract stuff from list.
        //Not really, necessary, though, as the class is already private and just for the LinkedListDriver class
        public int data;
        public Node next;
        public Node(int value){
            data = value;
            next = null;
        }
    }
}
