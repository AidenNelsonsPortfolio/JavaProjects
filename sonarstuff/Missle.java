
package sonarstuff;

import java.awt.Polygon;


public class Missle extends Polygon{
    public double angle = 0;
    public boolean rotated;
    private int deltaX, deltaY;
    public Missle(int targetX, int targetY, boolean rotated){
        this.rotated = rotated;
        
        npoints = 4;
        xpoints = new int[4];
        ypoints = new int[4]; 
        deltaX = targetX-GUI.getWidth(1)/2;
        deltaY = targetY-GUI.getHeight(1)/2;
        
        if(deltaY<0&&deltaX > 0)
            angle = -(Math.atan((double)deltaY/deltaX)+Math.PI/2)+(2*Math.PI);
        else if(deltaY > 0 && deltaX > 0)
            angle = -(Math.atan((double)deltaY/deltaX))- Math.PI/2+(2*Math.PI);
        else if(deltaY < 0 && deltaX< 0)
           angle = (-Math.atan((double)deltaY/deltaX)+Math.PI/2);
        else
           angle = (-Math.atan((double)deltaY/deltaX)+Math.PI/2);

        xpoints[0] = GUI.getWidth(1)/2;
        ypoints[0] = GUI.getHeight(1)/2-10;
        xpoints[1] = GUI.getWidth(1)/2-10;
        ypoints[1] = GUI.getHeight(1)/2+10;
        xpoints[2] = GUI.getWidth(1)/2+10;
        ypoints[2] = GUI.getHeight(1)/2+10;
        xpoints[3] = GUI.getWidth(1)/2;
        ypoints[3] = GUI.getHeight(1)/2-10;
    }
    public int getDX(){
        return this.deltaX;
    }
    
    public int getDY(){
        return this.deltaY;
    }
}
