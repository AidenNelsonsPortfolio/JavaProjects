/**
	Name: Aiden Nelson
	Date Started: 4/15/2022
	Last Modified: 4/25/2022
	Program Details: This program allows you to move and manipulate blocks on stacks, via commands of either 'push', 'pile', 'move', or 'onto' via file input. 
*/

import java.util.Scanner;
import java.util.Stack;

class Main{
	
	
    public static void main(String[] args){
    
        Scanner keyboard = new Scanner(System.in);
        
        int size = keyboard.nextInt();
        
		@SuppressWarnings("unchecked")
        Stack<Integer>[] blockWorld = new Stack[size];
        
        int[] locations = new int[size];
        
        String[] command = new String[2];
        
        Stack<Integer> temp = new Stack<>();

        
        for(int i = 0; i < size; i ++){
        
            blockWorld[i] = new Stack<Integer>();
            
            blockWorld[i].push(i);
            
            locations[i] = i; 
        }
        
        int startingBlock, targetBlock;
        
        command[0] = (keyboard.next()).toLowerCase();
        
        while(!command[0].equals("exit") && !command[0].equals("quit")){
            
            startingBlock = keyboard.nextInt();
            
            command[1] = (keyboard.next()).toLowerCase();
            
            targetBlock = keyboard.nextInt();
			
            
            if((command[0].equals("move") || command[0].equals("pile")) && (startingBlock >= 0 && startingBlock < size) && (command[1].equals("onto") || command[1].equals("over")) && (targetBlock >= 0 && targetBlock < size && targetBlock != startingBlock) && (locations[startingBlock] != locations[targetBlock])){
                
                while(blockWorld[locations[startingBlock]].peek() != startingBlock){		
					
                    temp.push(blockWorld[locations[startingBlock]].pop());
				}
				
                temp.push(blockWorld[locations[startingBlock]].pop());
                
                //All blocks are on temp stack
                
                if(command[1].equals("onto")){
                             							  
                    while(blockWorld[locations[targetBlock]].peek() != targetBlock){
                    
                        locations[blockWorld[locations[targetBlock]].peek()] = blockWorld[locations[targetBlock]].peek();
                    											
                        blockWorld[blockWorld[locations[targetBlock]].peek()].push(blockWorld[locations[targetBlock]].pop());
                    }
                }
                //targetBlock is exposed if it was an "onto" command
                
                if(command[0].equals("pile")){
					                    
                    while(!temp.isEmpty()){
                      
                        locations[temp.peek()] = locations[targetBlock];
                      
                        blockWorld[locations[targetBlock]].push(temp.pop());
                    }
                }
                else{
                
                    //If the first command was "move" instead of "pile"...
                    
                    locations[temp.peek()] = locations[targetBlock];
					                    
                    blockWorld[locations[targetBlock]].push(temp.pop());
                    
                    //First one is now off, locate updated appropriately...
                    
                    while(!temp.isEmpty()){
                        
                       //move each block to their original location
					   
					   locations[temp.peek()] = temp.peek();
                       
                       blockWorld[locations[temp.peek()]].push(temp.pop());
                    }
                }
                
            } //end of changing blockWorld state

            command[0] = keyboard.next();
            
        } //end of while loop of commands
				
		
        
        for(int i = 0 ; i < size; i ++){
			//if(i > 0)
				//System.out.println();
			String stuff = "";
			//System.out.print(i + ":");
			//System.out.println(" " + blockWorld[i]);
			
			if(blockWorld[i].size() == 1){
				stuff = "" + blockWorld[i].pop();
				stuff = " " + stuff;
			}
			else if(blockWorld[i].size() > 1){
				while(!blockWorld[i].isEmpty())	
					stuff = " " + blockWorld[i].pop()+ stuff;
			}
			System.out.print(i + ":");
			System.out.println(stuff);
			

			
        }
        System.exit(0);
    }
}


