import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.*;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Ellipse2D;
public class RectangularHazard
{
   public int x;
   public int y;
   public int width;
   public int height;
   public Rectangle2D shape;
   
   public RectangularHazard(int x, int y, int width, int height)
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