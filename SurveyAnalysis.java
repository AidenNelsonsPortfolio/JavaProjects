import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;


public class SurveyAnalysis{
	public static void main(String[] args){
		int q1ACount = 0, q1BCount = 0, q1CCount = 0, q2ACount = 0, q2BCount = 0, q2CCount = 0, q3ACount = 0, q3BCount = 0, q3CCount = 0, q4ACount = 0, q4BCount = 0, q4CCount = 0, q5ACount = 0, q5BCount = 0, q5CCount = 0, q6ACount = 0, q6BCount = 0, q6CCount = 0;
		
		Scanner fromFile = null;
		
		try{
			fromFile = new Scanner(new File("results.sur"));
		}
		catch(FileNotFoundException e){
			
			System.out.println("File was not found, could not open 'results.sur'.");
			System.exit(0);
		}
		
		while(fromFile.hasNextInt()){
			
			if(fromFile.next() == 'A' || fromFile.next() == 'a'){
				q1ACount ++;
			}
			else if(fromFile.next()== 'B' || fromFile.next() == 'b'){
				q1BCount ++;
			}
			else{
				q1CCount ++;
			}
			
			if(){}
			else if(){}
			else{}
			
			if(){}
			else if(){}
			else{}
			
			if(){}
			else if(){}
			else{}
			
			if(){}
			else if(){}
			else{}
			
			if(){}
			else if(){}
			else{}
			
		
		}
	}
}