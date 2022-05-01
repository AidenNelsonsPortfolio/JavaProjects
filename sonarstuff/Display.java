
package sonarstuff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Display extends JPanel{
    
    private Random rand = new Random();
    private SweeperArm sweep;
    private int level = 1, rectX, rectY, hearts = 3, missleSize;
    public boolean spawnEnemies = true;
    private ArrayList<Missle> missles;
    private ArrayList<Enemy> badGuys;
    private ImageIcon explosions;
    private Base base;
    private long rotation = 1;
    private Area oldSweep, rect;
    private Color backgroundColor = Color.GREEN.darker();
    private Font best;
    private ImageIcon heart, background;
   
    public Display(){
        super();
        setBackground(backgroundColor);
        sweep = new SweeperArm();
        badGuys = new ArrayList<>();
        missles = new ArrayList<>();
        base = new Base(50,50);
        rect = new Area(new Rectangle(GUI.getWidth(1)/2-(base.width/2+2), GUI.getHeight(1)/12, base.width-2, GUI.getHeight(1)/2-(GUI.getHeight(1)/12)));
        rectX = -100;
        rectY = -100;
        best = new Font("Times New Roman", 20, 20);
        heart = new ImageIcon("heart.png");
        background = new ImageIcon("sonar.png");
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
          
        g2.fillRect(10,10,110,30);
        g2.setColor(Color.WHITE);
        g2.setFont(best);
        g2.drawString("Score: "+ GUI.getGameScore(),30,30);
        for(int i = 0; i < hearts; i ++)
            g2.drawImage(heart.getImage(), 50*i, 100, 50, 50, this);
        g2.drawImage(background.getImage(), 100, -40, 800, 750, this);
        
        if(GUI.getGameScore()> 100 && !GUI.getInfinite()){
                GUI.stopEnemyTimer();
                GUI.stopMainTimer();
                System.out.println("You Beat Level #" + GUI.getLevel()+ "!");
                JOptionPane.showMessageDialog(null,"You Beat Level #" + GUI.getLevel());
                Scanner results = new Scanner("results.txt");
                int temp = GUI.getGameLevel();
                   
                int i = 0;
                ArrayList<String> contents = new ArrayList<>();
                while(results.hasNext()){
                    contents.add("" + results.next());
                    if(contents.get(i).equals("" + temp)){
                        results.next();
                        contents.add("true");
                        i++;
                    }
                    i++;
                }
                FileWriter file = null;

                try{ file = new FileWriter("results.txt");
                    while(!contents.isEmpty()&& i > 0){
                        file.append(contents.get(i-1));
                        file.append(contents.get(i));
                        contents.remove(i-1);
                        contents.remove(i);
                        i = i-2;
                }
                }
                catch(IOException e){
                    System.out.println("Don't mess with the results file!");
                    System.exit(0);
                }     
                
                
                
                System.exit(0);
        }
        
            
        
        if(GUI.enemySpawningTime && badGuys.size() < level + 5){
            
            badGuys.add(new Enemy(false, rand.nextInt(39)));
            GUI.enemySpawningTime = false;
            //System.out.println(badGuys.size());
            
        }
        for(int i = 0; i<badGuys.size(); i ++){
            
            if(GUI.movingTime){
                
                (badGuys.get(i)).translate((badGuys.get(i)).getDX(),(badGuys.get(i)).getDY());   
                
            }
            if(badGuys.get(i).isIntersected()){
                g2.setColor(Color.GREEN.darker());
                g2.draw(badGuys.get(i));
                g2.fill(badGuys.get(i));
            }
            
            else{
                
            //g2.setColor(Color.BLACK);
            //g2.draw(badGuys.get(i));
            //g2.fill(badGuys.get(i));
            
            }
            if(!badGuys.get(i).intersects(-200,-100,1500,1000))
                badGuys.remove(i);
        }
        GUI.movingTime = false;
        
        if(GUI.mouseMoved){
            g2.setColor(Color.GREEN);
            rectX = GUI.mouseX-7;
            rectY = GUI.mouseY-10;
            g2.drawRect(rectX-3, rectY-45, 6, 20);
            g2.fillRect(rectX-3, rectY-45, 6, 20);
            g2.drawRect(rectX-3, rectY-15, 6, 20);
            g2.fillRect(rectX-3, rectY-15, 6, 20);
            g2.drawRect(rectX-25, rectY-23, 20, 6);
            g2.fillRect(rectX-25, rectY-23, 20, 6);
            g2.drawRect(rectX+5, rectY-23, 20, 6);
            g2.fillRect(rectX+5, rectY-23, 20, 6);
            GUI.mouseMoved = false;
        }
        else{
            g2.setColor(Color.GREEN);            
            g2.drawRect(rectX-3, rectY-35, 6, 10);
            g2.fillRect(rectX-3, rectY-35, 6, 10);
            g2.drawRect(rectX-3, rectY-15, 6, 10);
            g2.fillRect(rectX-3, rectY-15, 6, 10);
            g2.drawRect(rectX-15, rectY-23, 10, 6);
            g2.fillRect(rectX-15, rectY-23, 10, 6);
            g2.drawRect(rectX+5, rectY-23, 10, 6);
            g2.fillRect(rectX+5, rectY-23, 10, 6);
        }
        if(missles.size()<4&&(GUI.mouseClicked||GUI.spaceBar)){
            missles.add(new Missle(GUI.mouseX, GUI.mouseY,false));
            GUI.mouseClicked = false;
            GUI.spaceBar = false;
        }
        
        for(int i = 0; i < missles.size(); i ++){
            g2.setColor(Color.BLUE);   
            missles.get(i).translate((missles.get(i).getDX())/20, (missles.get(i).getDY()/20));  
            g2.draw(missles.get(i));
            g2.fill(missles.get(i));
            if(!missles.get(i).intersects(0,0, GUI.getWidth(1), GUI.getHeight(1))){
                    missles.remove(i);
            }
            else{
            for(int j = 0; j < badGuys.size(); j++){
                try{
                if(badGuys.get(j)!= null && missles.get(i)!= null &&badGuys.size()>0&& missles.size()>0&&  badGuys.get(j).intersects(missles.get(i).getBounds2D())){
                    badGuys.remove(j);
                    missles.remove(i);
                    GUI.setGameScore(1);
                    }
                }catch(IndexOutOfBoundsException e){
                }
                }
            }
        }
        
            
        
    
        g2.setColor(Color.BLACK);
        g2.draw(base);
        g2.fill(base);
        g2.setColor(Color.BLUE);
        
        AffineTransform af = new AffineTransform();
        af.rotate(Math.toRadians(rotation), GUI.getWidth(1)/2, GUI.getHeight(1)/2);

        
        oldSweep = rect.createTransformedArea(af);
        
        g2.rotate(Math.toRadians(rotation), GUI.getWidth(1)/2, GUI.getHeight(1)/2-15);
        Color green = new Color(50,255,50,75);
        g2.setColor(green);
        g2.draw(sweep);
        g2.fill(sweep);
        
        g2.rotate(-Math.toRadians(2*rotation),GUI.getWidth(1)/2, GUI.getHeight(1)/2-15);
        
        for(int i = 0; i < badGuys.size(); i ++){
            
            if(badGuys.get(i).intersects(oldSweep.getBounds2D())){
                badGuys.get(i).setIntersected(true);
            }
            if(badGuys.get(i).intersects(base.getBounds2D())){
                badGuys.remove(i);
                hearts--;
            }
            
                
        }
        if(hearts < 1){
                   GUI.stopEnemyTimer();
                   GUI.stopMainTimer();
                   System.out.println("Game over, all your hearts were lost!");
                   JOptionPane.showMessageDialog(null,"Game Over, All of Your Hearts Were Lost!"); 
                   System.exit(0);
        }
        
        g2.setColor(Color.BLACK);
        rotation ++;
        
        
        
    }
    
    public void setEnemySpawn(boolean b){
        spawnEnemies = b;
    }

}
