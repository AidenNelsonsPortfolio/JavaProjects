/* 
 * Name: Aiden Nelson
 * Date Created: 12/3/2021
 * Date Modified: 12/10/2021
 * Description: Displays on a GUI chessboard a valid knights tour sequence, with a variable speed slider.
*/

package ktdisplay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ChessVisualizer {
    private final int BOARD_SIZE = 8;
    
    private String fileChosen;
    private JPanel[][] board;
    private JLabel[][] boardLabel;
    private String[][] moves;
    private JFrame window;
    private JPanel mPanel, chessBoard, bPanel, tPanel;
    private JLabel sliderValue, whatSpeed;
    private JButton start, fileChooser;
    private JSlider speed;
    private JComboBox validKnightsTours;
    private int numLines = 0, moveCount = 0,timerCount = 0, lastStart = -1, lastEnd = -1;
    private boolean isStarted = false;
    private ImageIcon knight;
    private JMenuBar menuBar;
    private JMenu changeColor;
    private JMenuItem redAndBlack, brownAndGray, blackAndGray, brownAndBlack;
    private Color boardColorA, boardColorB;
    private boolean colorSet;
    private Timer timer;
    
    
    /**
     * Constructor for the chess board, displays in a GUI a chessboard with several functionalities, used for displaying chess knights tours. 
     */
    public ChessVisualizer(){
        window = new JFrame("Knight's Tour Visualizer");
        window.setSize(800,800);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        whatSpeed = new JLabel("         Speed: ");
        whatSpeed.setFont(new Font("Times New Roman", Font.BOLD, 24));
        moves = null;
        mPanel = new JPanel();
        bPanel = new JPanel();
        tPanel = new JPanel();
        chessBoard = new JPanel();
        sliderValue = new JLabel("5");
        start = new JButton("START");
        start.setEnabled(false);
        start.setFont(new Font("Times New Roman", Font.BOLD, 24));
        fileChooser = new JButton("Select File");
        fileChooser.setFont(new Font("Times New Roman", Font.BOLD, 24));
        fileChooser.addActionListener(new ButtonListener());
        validKnightsTours = new JComboBox();
        timer = new Timer(50, new TimerListener());
        menuBar = new JMenuBar();
        changeColor = new JMenu("Change Board Color Scheme... ");
        
        redAndBlack = new JMenuItem("Red and Black");
        redAndBlack.addActionListener(new MenuListener());
        changeColor.add(redAndBlack);
        redAndBlack.setEnabled(false);
        brownAndGray = new JMenuItem("Brown and Gray");
        brownAndGray.addActionListener(new MenuListener());
        changeColor.add(brownAndGray);
        blackAndGray = new JMenuItem("Black and Gray");
        blackAndGray.addActionListener(new MenuListener());
        changeColor.add(blackAndGray);
        brownAndBlack = new JMenuItem("Brown and Black");
        brownAndBlack.addActionListener(new MenuListener());
        changeColor.add(brownAndBlack);
        menuBar.add(changeColor);


        sliderValue.setFont(new Font("Times New Roman", Font.BOLD, 24));
        
        speed = new JSlider();
        speed.setMaximum(10);
        speed.setMinimum(1);
        speed.setValue(5);
        speed.addChangeListener(new ValueListener());
        speed.setEnabled(false);
        
        start.addActionListener(new StartListener());

        chessBoard.setLayout(new GridLayout(8,8,3,3));
        bPanel.setLayout(new GridLayout(1,6,3,3));
        mPanel.setLayout(new BorderLayout());
        
        
        chessBoard.setLayout(new GridLayout(BOARD_SIZE,
                                        BOARD_SIZE,2,2));  
        board = new JPanel[BOARD_SIZE][BOARD_SIZE];
        boardLabel = new JLabel[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<board.length; i++)
            for(int j=0; j<board[i].length; j++) {
                board[i][j] = new JPanel();
                if( (i+j)%2 == 0){
                    if(colorSet)
                        board[i][j].setBackground(boardColorA);
                    else
                        board[i][j].setBackground(Color.BLACK);
                }
                else{
                        if(colorSet)
                        board[i][j].setBackground(boardColorB);
                        else
                            board[i][j].setBackground(Color.RED);
                }
                boardLabel[i][j] = new JLabel("(" + (char)('A'+j) + "," + (char)('1'+i) + ")");
                boardLabel[i][j].setForeground(Color.WHITE);
                boardLabel[i][j].setFont(new Font("Algerian", Font.BOLD, 24));
                board[i][j].add(boardLabel[i][j]);
            }
        
        for(JPanel[] jpa : board)
            for(JPanel jp : jpa)
                chessBoard.add(jp);
        bPanel.add(fileChooser);
        bPanel.add(whatSpeed);
        bPanel.add(speed);
        bPanel.add(sliderValue);
        bPanel.add(start);
        
        mPanel.add(tPanel,BorderLayout.PAGE_START);
        mPanel.add(bPanel, BorderLayout.PAGE_END);
        mPanel.add(chessBoard,BorderLayout.CENTER);
        window.setJMenuBar(menuBar);
        window.add(mPanel);
        
        window.setVisible(true);
    
    }
    /**
     * Listens to the slider value and updates the label next to it appropriately. Determines the speed with which the knights tour progresses later on.
     */
    private class ValueListener implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent ce){
                sliderValue.setText("" + speed.getValue());
            }
            
    }
    /**
     * Listens to the fileChooser button, which, upon being pressed, opens a 
     * JOptionPane where the user can select the file that they want to have analyzed.
     */
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae){
            fileChosen = JOptionPane.showInputDialog(null,"What file do you want analyzed?", "File Name", JOptionPane.PLAIN_MESSAGE);
            Scanner fromFile = null;
            File tours = null;
            try{
            tours = new File(fileChosen);
            fromFile = new Scanner(tours);
            
            }catch(FileNotFoundException e){
                JOptionPane.showMessageDialog(null,"The file path was unable to be made, exiting the program now.","ERROR", JOptionPane.PLAIN_MESSAGE);
                System.exit(0);
            }
            fileChooser.setEnabled(false);
            whatSpeed.setText("\t Speed:");
            tPanel.add(new JLabel(fileChosen + " was connected to."));
            
            while(fromFile.hasNextLine()){
                numLines++;
                validKnightsTours.addItem("Knight's Tour: " + numLines);
                fromFile.nextLine();
            }
            moves = new String[numLines][BOARD_SIZE*BOARD_SIZE];
            numLines = 0;
            Scanner fromFile2 = null;
            try{
            fromFile2 = new Scanner(tours);
            }catch(FileNotFoundException e){
                System.exit(0);
            }
            while(fromFile2.hasNextLine()){
                moveCount = 0;
                Scanner parseString = new Scanner(fromFile2.nextLine());
                while(parseString.hasNext()){
                    moves[numLines][moveCount] = parseString.next();
                    moveCount++;
                }
                numLines ++;
               
            }
            bPanel.add(validKnightsTours);
            whatSpeed.setText("\t\tSpeed: ");
            speed.setEnabled(true);
            start.setEnabled(true);
            
        }
    }
    /**
     * Listens to the start button, which then disables all other functionality and 
     * sets isStarted to true, leading to the beginning of the visual knights tour.
     * 
    */
    private class StartListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            lastStart = -1;
            lastEnd = -1;
            timerCount = 0;
            start.setEnabled(false);
            speed.setEnabled(false);
            validKnightsTours.setEnabled(false);
            changeColor.setEnabled(false);
            isStarted = true;
            timer.setInitialDelay(1000);
            timer.setDelay((11-speed.getValue())*100);
            timer.start();
            
        }
    }
    /**
     * Listens to a timer object, as well as looks at if isStarted is true. 
     * Assuming isStarted is true, it begins the visualization of the selected knights tour (selected in the JComboBox).
     */
    private class TimerListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            
           
            if(isStarted == true&&timerCount<BOARD_SIZE*BOARD_SIZE){
                    String s = moves[validKnightsTours.getSelectedIndex()][timerCount];
                    ImageIcon knight = new ImageIcon("knight.png");
                    board[s.charAt(0)-'A'][s.charAt(1)-'1'].setBackground(Color.GREEN);
                    boardLabel[s.charAt(0)-'A'][s.charAt(1)-'1'].setText("");
                    boardLabel[s.charAt(0)-'A'][s.charAt(1)-'1'].setIcon(knight);

                    if(lastStart >= 0){
                        boardLabel[lastStart][lastEnd].setIcon(null);
                        boardLabel[lastStart][lastEnd].setText(""+(timerCount));
                        boardLabel[lastStart][lastEnd].setForeground(Color.BLACK);
                        boardLabel[lastStart][lastEnd].setFont(new Font("Algerian", Font.BOLD, 24));
                    }
                    timerCount++;
                    lastStart = s.charAt(0)-'A';
                    lastEnd = s.charAt(1)-'1';
                    

            }
            if(timerCount == BOARD_SIZE*BOARD_SIZE){
            boardLabel[lastStart][lastEnd].setIcon(null);
            boardLabel[lastStart][lastEnd].setText(""+(timerCount));
            boardLabel[lastStart][lastEnd].setForeground(Color.BLACK);
            boardLabel[lastStart][lastEnd].setFont(new Font("Algerian", Font.BOLD, 24));
            }
            
            if(timerCount >= BOARD_SIZE*BOARD_SIZE)  
                timerCount++;
           
            if(timerCount == BOARD_SIZE*BOARD_SIZE+BOARD_SIZE){
               lastStart = -1;
               lastEnd = -1;
               timerCount = 0;
               start.setEnabled(true);
               speed.setEnabled(true);
               validKnightsTours.setEnabled(true);
               isStarted = false;
               for(int i=0; i<board.length; i++){
                    for(int j=0; j<board[i].length; j++) {
                        if( (i+j)%2 == 0){
                            if(colorSet)
                            board[i][j].setBackground(boardColorA);
                            else
                                board[i][j].setBackground(Color.BLACK);
                        }
                        else{
                            if(colorSet)
                            board[i][j].setBackground(boardColorB);
                            else
                                board[i][j].setBackground(Color.RED);
                        }
                        boardLabel[i][j].setText("(" + (i+1) + "," + (j+1) + ")");
                        boardLabel[i][j].setForeground(Color.WHITE);
                    }
                }
               changeColor.setEnabled(true);
           }
           
           
        }
        
    }
    /**
     * Determines if any action has been done in the menu object. 
     * Depending upon the selection, the board colors are changed to the selected scheme.
     */
    private class MenuListener implements ActionListener{
      
          @Override
          public void actionPerformed(ActionEvent e){
            
            switch(((JMenuItem)(e.getSource())).getText()){
                case "Red and Black": 
                    boardColorA = Color.RED;
                    boardColorB = Color.BLACK;
                    blackAndGray.setEnabled(true);
                    brownAndBlack.setEnabled(true);
                    brownAndGray.setEnabled(true);
                    redAndBlack.setEnabled(false);
                    colorSet = true;
                    
                    break;
                        
                case "Brown and Gray":
                    boardColorA = new Color(150,75,0);
                    boardColorB = Color.LIGHT_GRAY;
                    blackAndGray.setEnabled(true);
                    brownAndBlack.setEnabled(true);
                    brownAndGray.setEnabled(false);
                    redAndBlack.setEnabled(true);
                    
                    colorSet = true;
                    break;
                case "Black and Gray":
                    boardColorA = Color.BLACK;
                    boardColorB = Color.LIGHT_GRAY;
                    blackAndGray.setEnabled(false);
                    brownAndBlack.setEnabled(true);
                    brownAndGray.setEnabled(true);
                    redAndBlack.setEnabled(true);
                    
                    colorSet = true;
                    break;
                case "Brown and Black":
                    boardColorA = new Color(150,75,0);
                    boardColorB = Color.BLACK;
                    blackAndGray.setEnabled(true);
                    brownAndBlack.setEnabled(false);
                    brownAndGray.setEnabled(true);
                    redAndBlack.setEnabled(true);
                    
                    colorSet = true;
                    break;

            }
            for(int i=0; i<board.length; i++)
                for(int j=0; j<board[i].length; j++) {
                    if( (i+j)%2 == 0)
                      board[i][j].setBackground(boardColorA);
                    else
                      board[i][j].setBackground(boardColorB);
                    
          }
      
      
      }
    
    
      }   
}
