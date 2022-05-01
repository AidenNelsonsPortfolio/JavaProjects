/* 
 * Name: Aiden Nelson
 * Date Created: 11/13/2021
 * Date Modified: 11/17/2021
 * Description: Takes one or more than one file of chess moves and determines if they are a valid knight's tour sequence (checks to see if the file exists first, however).
*/

package knightstour;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class KnightsTour{
    private static final int BOARD_SIZE = 8;
    
    /**
     *
     * @param args Can pass file names to main method, keep .txt in the name of each, separate with a space only whenever passing.
     */
    public static void main(String[] args) {
        int linesScanned = 0, count = 0, filesScanned = 0, i = 0, numFiles = 0;
        String lastLine, currentFileName;
        Scanner userInput = new Scanner(System.in);
	if(args.length > 0){
            System.out.println("You passed command line arguments, reading in those now.");
            numFiles = args.length;
            System.out.println("Analyzing " + numFiles + " files in total.");		
	}
	else{
            System.out.println("How many files would you like to be analyzed for knight's tours?");
            numFiles = userInput.nextInt();
            System.out.println("What file(s) would you like me to analyze? Separate each file by a space only (include the .txt for each).");
	}
        while(filesScanned < numFiles && (args.length>0||userInput.hasNext())){
            System.out.println("");
            filesScanned ++;
            linesScanned = 0;
            if(args.length >0)
		currentFileName = args[i];
            else{
		currentFileName = userInput.next();
            }
            i ++;
            Scanner fromFile = null;
            try{
		fromFile = new Scanner(new File(currentFileName));
            }catch(FileNotFoundException e){
		System.out.println("The " + currentFileName + " file could not be found or reached.");
		System.exit(0);
            }
            while(fromFile.hasNextLine()){
                boolean[][] visited = new boolean[BOARD_SIZE][BOARD_SIZE];
		boolean isWithinRange = true, isValidMove = true, notADuplicate = true;
		count = 0; 
		lastLine = fromFile.nextLine();
                linesScanned ++;
		Scanner parseString = new Scanner(lastLine);
		String[] moves = new String[BOARD_SIZE*BOARD_SIZE+1];
		while(parseString.hasNext()&& count <= BOARD_SIZE*BOARD_SIZE){
                    moves[count] = parseString.next();
                    count ++;
		}
                count = 0;
		if(moves[BOARD_SIZE*BOARD_SIZE] != null || moves[BOARD_SIZE*BOARD_SIZE-1]== null)
                    System.out.println("There was either not enough or too many moves for line " + linesScanned + " in file " + currentFileName + " to be a valid knight's tour.");
		else{
                          while(isWithinRange && isValidMove && notADuplicate && count < BOARD_SIZE*BOARD_SIZE){						isWithinRange = withinRange(moves[count]);
						
				if(isWithinRange == true && count >= 1)
                                    isValidMove = validMove(moves[count], moves[count-1]);
						
				if(isWithinRange && isValidMove)
                                    notADuplicate = notDuplicate(moves[count], visited);
					   
				if(isWithinRange == false )
                                    System.out.println("Line number " + linesScanned + " in your file " + currentFileName + " contained moves that were outside of the chess board's bounds.");

				else if (isValidMove == false) 
                                    System.out.println("Line number " + linesScanned + " in your file " + currentFileName + " had invalid knight moves.");

				else if (notADuplicate == false)
                                    System.out.println("Line number " + linesScanned + " in your file " + currentFileName + " had duplicate moves, making it an invalid knight's tour.");
						
				else if(count == BOARD_SIZE*BOARD_SIZE-1&&notADuplicate&&isValidMove&&isWithinRange)
                                    System.out.println("You have completed a valid knight's tour on line " + linesScanned + " of your file " + currentFileName + "!");          
                                    count ++;
                            }
			}
            }
		System.out.println("");
		fromFile.close();
		}
		System.out.println("All lines of all files have been analyzed.");
    }
    
    /**
     *The below method checks if a move is within the range of a chessboard, defined by the BOARD_SIZE final variable.
     * @param move Is a String object that represents the move to be analyzed.
     * @return Returns true if the move was within the range of the BOARD_SIZE variable, false otherwise.
     * 
     */
    public static boolean withinRange(String move){
	if(move.length() > 2 || move.length() < 2)
            return false;
        if(move.charAt(0) > 'H' || move.charAt(0)< 'A')
            return false;
        else if(move.charAt(1) > '8' || move.charAt(1) < '1')
            return false;
        else
            return true;
    }

    /**
     *This method checks if the knight moved in a valid way, using an L shaped path, as in a chess game.
     * @param currentMove  Takes the current move in String form. Will check to see if the distance between this and the lastMove quantifies it as a valid knight's move.
     * @param lastMove  Takes the last move, also in String form.
     * @return Returns true if the move was a valid knight's move, false otherwise.
     */
    public static boolean validMove(String currentMove, String lastMove){
        int firstRow=0, firstCol=0, secRow=0, secCol=0;
        firstCol = currentMove.charAt(0)-'A';
        firstRow = currentMove.charAt(1)- '1';
        secCol = lastMove.charAt(0)- 'A';
        secRow = lastMove.charAt(1)-'1';
        if((firstCol - secCol == 2 ||firstCol-secCol == -2)&& (firstRow - secRow == 1||firstRow-secRow == -1))
            return true;
        else if ((firstCol-secCol == 1|| firstCol-secCol == -1)&& (firstRow - secRow == 2|| firstRow-secRow == -2))
            return true;
        else 
            return false;
    }

    /**
     *This method scans through an array represented by the chessboard's size, and determines if it has already been passed the same value as the 
	 current move, which would indicate a duplicate move, and, as such, an invalid knight's tour.
     * @param move  Takes the most current knight's move in question in String form.
     * @param visited  Takes the 2D array of the chessboard to check if the square the knight has moved to has already been visited.
     * @return Returns true if the square the knight moved to HAS NOT been moved to before, false otherwise.
     */
    public static boolean notDuplicate(String move, boolean[][] visited){
        if(visited[move.charAt(0)-'A'][move.charAt(1)-'1'] == false){
            visited[move.charAt(0)-'A'][move.charAt(1)-'1'] = true;
            return true;
        }
        else
            return false;
    }
}