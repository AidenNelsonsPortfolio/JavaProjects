
package sonarstuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class GUI {
    
    private JFrame window;
    private JLabel startText, levelText;
    private Display mainPanel;
    private JPanel buttonPanel, a, b, c, d, e;
    private static JSlider levelSlider;
    private static Timer mainTimer, enemyTimer;
    private static JButton resultsButton, infiniteButton, levelButton;
    private final static int SCREEN_WIDTH = 1000;
    private final static int SCREEN_HEIGHT = 700;
    private static int gameScore = 0, value = 1;
    public static int mouseX, mouseY, level, numTimes, timesThru;
    public static boolean mouseMoved = false, mouseClicked = false, spaceBar = false, enemySpawningTime = true, movingTime = true, timeToGo = true, infiniteLevel = false;
    public GUI(){
                
        infiniteButton = new JButton("Infinite Enemies Mode");
        
        levelButton = new JButton("Level Mode \n (select level below)");
       
        levelButton.setFont(new Font("Times New Roman", 40, 40));
        
        infiniteButton.setFont(new Font("Times New Roman", 40, 40));
        
        levelButton.addActionListener(new LevelListener());
        
        infiniteButton.addActionListener(new InfiniteListener());
        
        startText = new JLabel("Welcome to Sonar Game!");
        
        startText.setFont(new Font("Times New Roman", 80, 80));
                
        levelText = new JLabel("Level : " + value);
        
        levelText.setFont(new Font("Times New Roman", 40, 40));
        
        levelSlider= new JSlider(1,100,1);
        
        levelSlider.addChangeListener(new SliderListener());
        
        resultsButton = new JButton("Click to See Your Level Progress!");
        
        resultsButton.setFont(new Font("Times New Roman", 40, 40));
        
        resultsButton.addActionListener(new ResultsListener());
        
        buttonPanel = new JPanel();
        
        buttonPanel.setLayout(new GridLayout(4,1));
        
        
        
        
        a = new JPanel();
        b = new JPanel();
        c = new JPanel();
        d = new JPanel();
        e = new JPanel();
        
        a.add(startText);
        
        a.setBackground(Color.GREEN);
        
        b.setBackground(Color.GREEN);
       
        d.setBackground(Color.GREEN);
        
        e.setBackground(Color.GREEN);
                        
        c.setBackground(Color.GREEN);
        
        b.add(levelButton);
        
        b.add(infiniteButton);
        
        e.add(levelSlider);
        
        e.add(levelText);
        
        d.add(resultsButton);
        
        buttonPanel.add(a);
        buttonPanel.add(b);
        buttonPanel.add(e);
        buttonPanel.add(d);
        
        window = new JFrame("Radar Game");
        
        
        window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        window.setLocationRelativeTo(null);
        
        
        window.setResizable(false);
        
        
        
        
        window.addMouseMotionListener(new CrosshairListener());
        
        
        //Creating the mainPanel
        
        window.add(buttonPanel, BorderLayout.CENTER);
        
        
        window.setVisible(true);
        
    }
    
    public static int getGameLevel(){
        return level;
    }
    
    public static int getWidth(int a){
        return SCREEN_WIDTH;
    }
    
    public static boolean getInfinite(){
        return infiniteLevel;
    }
    
    public static int getLevel(){
        return levelSlider.getValue();
    }
    
    public static int getHeight(int a){
        return SCREEN_HEIGHT;
    }
    
    public static int getGameScore(){
        return gameScore;
    }
     public static void setGameScore(int a){
        gameScore += a;
    }
    
    public static void stopEnemyTimer(){
        enemyTimer.stop();
    }
     
    public static void stopMainTimer(){
        mainTimer.stop();
    }
    
    private class SliderListener implements ChangeListener{
        

        @Override
        public void stateChanged(ChangeEvent ce) {
            value = levelSlider.getValue();
            levelText.setText("Level: " + value);
            e.repaint();
        }
    
    }
    private class InfiniteListener implements ActionListener{
        @Override
        public void actionPerformed(java.awt.event.ActionEvent ae) {
            mainPanel = new Display();   
            window.dispose();
            window = new JFrame("Radar Game");
            window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setLocationRelativeTo(null);
            window.setResizable(false);
            window.add(mainPanel); 
            window.addMouseMotionListener(new CrosshairListener());
        
            window.addMouseListener(new ClickListener());
        
            window.addKeyListener(new SpaceListener());
        
            mainTimer = new Timer(10, new MovementListener());
        
            enemyTimer = new Timer((101-levelSlider.getValue())*10, new EnemyListener());
        
        
            window.setVisible(true);
            
            mainTimer.start();
            enemyTimer.start();
            mouseX = SCREEN_WIDTH/2;
            mouseY = SCREEN_HEIGHT/2;
            mouseMoved = false;
            
            infiniteLevel = true;
        }
        
    }
    private class LevelListener implements ActionListener{
        @Override
        public void actionPerformed(java.awt.event.ActionEvent ae) {
            mainPanel = new Display();   
            window.dispose();
            window = new JFrame("Radar Game");
            window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setLocationRelativeTo(null);
            window.setResizable(false);
            window.add(mainPanel); 
            window.addMouseMotionListener(new CrosshairListener());
        
            window.addMouseListener(new ClickListener());
        
            window.addKeyListener(new SpaceListener());
        
            mainTimer = new Timer(10, new MovementListener());
        
            enemyTimer = new Timer((101-levelSlider.getValue())*10, new EnemyListener());
        
        
            window.setVisible(true);
            
            mainTimer.start();
            enemyTimer.start();
            mouseX = SCREEN_WIDTH/2;
            mouseY = SCREEN_HEIGHT/2;
            mouseMoved = false;
            
            infiniteLevel = false;
        }
        
    }
    
    private class MovementListener implements ActionListener{
        @Override
        public void actionPerformed(java.awt.event.ActionEvent ae) {
            mainPanel.repaint();      
            numTimes ++;
            timesThru ++;
            if(numTimes %10 ==0){
            movingTime = true;
            numTimes = 0;
            }
            if(timesThru %12 == 0){
                timeToGo = true;
                timesThru = 0;
            }
            
        }
    }
    private class EnemyListener implements ActionListener{
        @Override
        public void actionPerformed(java.awt.event.ActionEvent ae) {
            enemySpawningTime = true;
            
        }
    }
    private class ResultsListener implements ActionListener{
        @Override
        public void actionPerformed(java.awt.event.ActionEvent ae) {
            
            window.dispose();
            window = new JFrame("Radar Game");
            window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setLocationRelativeTo(null);
            window.setResizable(false);
            window.setLayout(new GridLayout(10,10));
            ArrayList<JPanel> panels = new ArrayList<>();
            ArrayList<JLabel> labels = new ArrayList<>();
            for(int i = 0; i < 100; i ++){
                panels.add(new JPanel());
                labels.add(new JLabel("" + (i + 1)));
                labels.get(i).setFont(new Font("Times New Roman", 20, 20));
                panels.get(i).setBackground(Color.RED);
                panels.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK, 7));
                panels.get(i).add(labels.get(i));
                window.add(panels.get(i));
                
            }
        
            window.setVisible(true);
            
        }
    }

    private class CrosshairListener implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent me) {
        
        }

        @Override
        public void mouseMoved(MouseEvent me) {
            mouseMoved = true;
            mouseX = me.getX();
            mouseY = me.getY();
        }
        

}
    private class ClickListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            /*
            mouseX = me.getX();
            mouseY = me.getY();
            mouseClicked = true;
            */
        }

        @Override
        public void mousePressed(MouseEvent me) {
            mouseX = me.getX();
            mouseY = me.getY();
            mouseClicked = true;
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            
        }

        @Override
        public void mouseExited(MouseEvent me) {
            
        }
    
    }
    private class SpaceListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent ke) {
            if(ke.getKeyCode() == KeyEvent.VK_SPACE)
                spaceBar = true;
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            /*
            if(ke.getKeyCode() == KeyEvent.VK_SPACE)
                spaceBar = true; 
*/
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            if(ke.getKeyCode() == KeyEvent.VK_SPACE)
                spaceBar = true;        
        }
    
    }
    
}
