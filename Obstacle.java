import java.util.*;
import java.io.*;
import java.awt.*;
import java.applet.*;
import java.awt.geom.Line2D.Double;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.*;
public class Obstacle
{
   public int top;
   public int left;
   public int width;
   public int height;
   private ArrayList<Side> sides = new ArrayList<Side>();
   public Rectangle2D.Double rect;
   
   public Obstacle (int left, int top, int width, int height)
   {
      this.top = top;
      this.left = left;
      this.width = width;
      this.height = height;
      
      sides.add(new Side(left,top,left+width,top,'n'));
      sides.add(new Side(left+width,top,left+width,top+height,'e'));
      sides.add(new Side(left+width,top+height,left,top+height,'s'));
      sides.add(new Side(left,top+height,left,top,'w'));
      
      rect = new Rectangle2D.Double(left,top,width,height);
      
   }
   
   public char checkCollision(Line2D vectors[])
   {
      for(Side s:sides)
      {
         if(s.isColliding(vectors))
         return s.getFace();
      }
      return ' ';
   }
   
   
   public String toString()
   {
      String output = "";
      for(int i = 0; i < sides.size();i++)
      {
         output += sides.get(i).toString() + "\n";
      }
      return output;
   }
   
   }
   
