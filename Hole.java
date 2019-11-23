import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Ellipse2D;
public class Hole
{
   public int x;
   public int y;
   public int r;
   private Ellipse2D shape;
   
   public Hole(int x, int y)
   {
      this.x = x;
      this.y = y;
      r = 14;
      shape = new Ellipse2D.Double(x,y,r,r);
   }
   public boolean checkCollide(Ball ball)
   {
      if(shape.contains(ball.getX()+ball.getR()/2,ball.getY()+ball.getR()/2))
      {System.out.println("inHole");return true;}
      return false;
   }
}