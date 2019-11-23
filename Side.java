import java.awt.geom.Line2D.Double;
import java.awt.geom.Line2D;

public class Side
{
   Line2D line;
   private Point p1,p2;
   private char face;
   
   public Side(int x1,int y1,int x2,int y2,char face)
   {
      p1 = new Point(x1,y1);
      p2 = new Point(x2,y2);
      this.face = face;
      line = new Line2D.Double(x1,y1,x2,y2);
   }
   
   public boolean isColliding(Line2D[] vectors)
   {
         for(int i = 0; i< vectors.length;i++)
         {
         if(line.intersectsLine(vectors[i]))
         return true;
         }
      
      return false;
   }
   
   public Point getP1()
   {
      return p1;
   }
   public Point getP2()
   {
      return p2;
   }
   public char getFace()
   {
      return face;
   }
   
   
   
   
   public String toString()
   {
      String output = "";
      output += "P1: " + p1.toString() + " P2: " + p2.toString();
      return output;
   }
   
   
}