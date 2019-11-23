import java.applet.*;
import java.awt.*;
import java.util.*;
public class Sabrina extends Applet
{
   public void paint(Graphics g)
   {
      Scanner scan = new Scanner(System.in);
      scan.nextInt();
      g.setFont(new Font("Arial",Font.PLAIN,18));
      boolean L1 = true;
      int midx = 0;
      int midy = 180;
      for(double t = 600;t>0;t-=0.000005)
      {
         double x  = 128* Math.pow(Math.sin(t),3);
         double y = 104*Math.cos(t) - 40*Math.cos(2*t) - 16*Math.cos(3*t) - 8*Math.cos(4*t);
         
         int rx = 300+(int)Math.round(x*-1);
         int ry = 200+(int)Math.round(y*-1);
         
         if(L1)
            {midx = rx;L1 = false;}     
         g.setColor(new Color(255,0,0));
         g.drawRect(rx,ry,2,2); 
         g.drawLine(rx,ry,midx,midy);
         
         
         g.setColor(new Color(255,255,255));
         g.drawString("Sabrina", midx-34,210);
      }
   }
}
