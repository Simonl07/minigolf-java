
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.*;

public class Rough
{
   public int x;
   public int y;
   public int width;
   public int height;
   public Rectangle2D shape;
   
   public Rough(int x,int y,int width,int height)
   {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      
      shape = new Rectangle2D.Double(x,y,width,height);
   }
   
   public boolean checkCollide(Ball ball)
   {
      if(shape.contains(ball.getX(),ball.getY()))
      return true;
      return false;
      
   }
   
}