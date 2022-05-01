
package sonarstuff;

import java.awt.Rectangle;


public class Base extends Rectangle {
    
    public Base(int width, int height){
    
       this.x = GUI.getWidth(1)/2-(int)(width*0.5);
       this.y = GUI.getHeight(1)/2-(int)(width*0.75);
       this.height = height;
       this.width = width;
       
       
    }
    
    
}
