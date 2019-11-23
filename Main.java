import java.applet.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.JOptionPane;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.applet.AudioClip;

public class Main extends Applet implements MouseListener, MouseMotionListener
{
   Graphics gBuffer;
   Image virtualMem;
   public int iniX;
   public int iniY;
   public int tempX;
   public int tempY;
   public int endX;
   public int endY;
   public double xV = 0;
   public double yV = 0;
   boolean finish;
   public Ball ball;
   Font scoreDisplay = new Font("Arial",Font.BOLD,25);
   AudioClip bounce;
   
   Level[] levels = new Level[3];
   public int currentLevel = 0;
   public Image ballpic;
   Image wrinkle;
      
      
   public void init()
   {
      try 
      {
         ballpic = ImageIO.read(new File("golfball.png"));
         wrinkle = ImageIO.read(new File("wrinkle.png"));
      } 
      catch (IOException e) {System.out.println("Error loading Pictures"); }
      int appletWidth = getWidth();                               
      int appletHeight = getHeight();                       
      virtualMem = createImage(appletWidth,appletHeight);  
      gBuffer = virtualMem.getGraphics();   
      addMouseMotionListener(this);
      addMouseListener(this);
       
      bounce = getAudioClip(getDocumentBase(),"bounce2.au");
      
      System.out.println("Setup Complete");
      
      
      levels[0] = new Level();
      levels[0].addObs(new Obstacle(650,0,70,550));
      levels[0].addObs(new Obstacle(520,490,130,60));   
      levels[0].addRectH(new RectangularHazard(600,0,50,490));   
      levels[0].addObs(new Obstacle(340,320,70,330));
      levels[0].addRectH(new RectangularHazard(10,0,130,110));
      levels[0].addCircH(new CircularHazard(30,400,80,170));
      levels[0].addRough(new Rough(340,170,70,150));
      levels[0].setBall(980,15,8);
      levels[0].setHole(110,610);
      ball = levels[0].ball;
      System.out.println("Level 1 Build");
      
      levels[1] = new Level();
      levels[1].addObs(new Obstacle(240,90,60,60));
      levels[1].addRough(new Rough(230,80,80,80));
      levels[1].addObs(new Obstacle(130,190,60,60));
      levels[1].addRough(new Rough(120,180,80,80));
      levels[1].addObs(new Obstacle(320,240,60,60));
      levels[1].addRough(new Rough(310,230,80,80));
      levels[1].addObs(new Obstacle(650,0,30,280));
      levels[1].addObs(new Obstacle(450,190,30,300));
      levels[1].addCircH(new CircularHazard(300,250,2100,910));
      levels[1].addCircH(new CircularHazard(0,-500,1000,550));
      levels[1].addRough(new Rough(0,0,1000,65));
      levels[1].addRough(new Rough(450,190,220,90));
      levels[1].setHole(920,80);
      levels[1].setBall(80,620,8);
      
      
      
      
      levels[2] = new Level();
      levels[2].addRectH(new RectangularHazard(760,250,250,20));
      levels[2].addRectH(new RectangularHazard(760,420,250,20));
      levels[2].addRectH(new RectangularHazard(960,250,40,170));
      levels[2].addRectH(new RectangularHazard(730,250,30,80));
      levels[2].addRectH(new RectangularHazard(730,360,30,80));
      
      levels[2].setHole(850,350);
      levels[2].setBall(0,400,8);
         
      
      
   }
   public void update(Graphics g)
   {
      paint(g);
   }
   
   
   
   public void paint(Graphics g)
   {
      if(!finish)
      {
         ball = levels[currentLevel].ball;
         ClearScreen();
         drawComponent();
         gBuffer.setColor(Color.red);
         gBuffer.drawLine(tempX,tempY,endX,endY);
         if(Math.abs(ball.getxV()) > 0.01 || Math.abs(ball.getyV()) > 0.01)
         {
            ball.recordVectors();
            if(checkCollision())
               System.out.println("collide");
            else
               updateBall();
            delay(15);
            repaint();
         }
         drawBall();
      
      
         g.drawImage(virtualMem,0,0,this);     
      }
      else{
         ClearScreen();
         g.setFont(scoreDisplay);
         g.drawString("Complete",450,300);
         g.drawString("Thanks for playing",400,340);}
   }
   
   
   
   
   
   
   public void mouseExited(MouseEvent e)  { }
   public void mouseMoved(MouseEvent e)   { }
   public void mouseEntered(MouseEvent e) { }
   public void mouseClicked(MouseEvent e) { }
   public void mousePressed(MouseEvent e)
   {
      if(!finish){
         iniX = e.getX();
         iniY = e.getY();
         System.out.println("Pressed  X:" + iniX + " Y:" + iniY);
      }}
   public void mouseDragged(MouseEvent e)
   {
      if(!finish){
         tempX = e.getX();
         tempY = e.getY();
         if(tempX < iniX)
         {
            int xDis = (iniX - tempX)*3;
            int yDis = (tempY - iniY)*3;
            endX = iniX + xDis;
            endY = iniY - yDis;
         }
         if(tempX > iniX)
         {
            int  xDis = (tempX - iniX)*3;
            int yDis = (tempY - iniY)*3;
            endX = iniX - xDis;
            endY = iniY - yDis;  
         }
         if(tempX == iniX)
         {
            int xDis = 0;
            int yDis = (tempY - iniY) * 3;
            endX = iniX - xDis;
            endY = iniY - yDis;
         }
         repaint();
      }}
   public void mouseReleased(MouseEvent e)
   {
      if(!finish){
         levels[currentLevel].stroke++;
         yV = (endY-iniY)/70.0;
         xV = (endX-iniX)/70.0;
         System.out.println("Mouse released, V:" + xV + "," + yV);
         ball.setV(xV,yV);
         repaint();}
   }
   
   private void updateBall()
   {
      ball.updateBall();
      int bx = (int)Math.round(ball.getX());
      int by = (int)Math.round(ball.getY());
      gBuffer.drawImage(ballpic,bx,by,this);
   }
   
   private void ClearScreen()
   {
      gBuffer.setColor(Color.white);
      gBuffer.fillRect(0,0,1000,650);
      gBuffer.setColor(new Color(30,200,30));
      gBuffer.fillRect(0,0,1000,650);
      
   }
   private void drawComponent()
   {
      
      
      
      gBuffer.drawRect(0,0,1000,650);
      for(int i = 0; i< levels[currentLevel].roughs.size();i++)
      {
         int x= levels[currentLevel].roughs.get(i).x; 
         int y= levels[currentLevel].roughs.get(i).y;
         int width = levels[currentLevel].roughs.get(i).width;
         int height = levels[currentLevel].roughs.get(i).height;
         gBuffer.setColor(new Color(0,155,0));
         gBuffer.fillRect(x,y,width,height);  
      }
      for(int i = 0; i < levels[currentLevel].obstacles.size();i++)
      {
         int x = levels[currentLevel].obstacles.get(i).left;
         int y = levels[currentLevel].obstacles.get(i).top;
         int width = levels[currentLevel].obstacles.get(i).width;
         int height = levels[currentLevel].obstacles.get(i).height;
         gBuffer.setColor(Color.black);
         gBuffer.fillRect(x,y,width,height);
      }
      
      for(int i = 0; i < levels[currentLevel].circHazard.size();i++)
      {
         int x = levels[currentLevel].circHazard.get(i).x;
         int y = levels[currentLevel].circHazard.get(i).y;
         int width = levels[currentLevel].circHazard.get(i).width;
         int height = levels[currentLevel].circHazard.get(i).height;
         gBuffer.setColor(new Color(0,128,255));
         gBuffer.fillOval(x,y,width,height);
      }
      
      for(int i = 0; i< levels[currentLevel].rectHazard.size();i++)
      {  
         int x= levels[currentLevel].rectHazard.get(i).x;
         int y= levels[currentLevel].rectHazard.get(i).y;
         int width = levels[currentLevel].rectHazard.get(i).width;
         int height = levels[currentLevel].rectHazard.get(i).height;
         gBuffer.setColor(new Color(0,128,255));
         gBuffer.fillRect(x,y,width,height);
      }
      
   
      
      gBuffer.setColor(Color.black);
      gBuffer.fillOval(levels[currentLevel].hole.x,levels[currentLevel].hole.y,levels[currentLevel].hole.r,levels[currentLevel].hole.r);
      gBuffer.setColor(Color.red);
      gBuffer.fillRect(levels[currentLevel].hole.x+levels[currentLevel].hole.r/2-2,levels[currentLevel].hole.y-65,4,70);
      gBuffer.fillRect(levels[currentLevel].hole.x+levels[currentLevel].hole.r/2,levels[currentLevel].hole.y-65,40,25);
      gBuffer.setFont(scoreDisplay);
      gBuffer.setColor(Color.black);
      gBuffer.drawString("Strokes: " + Integer.toString(levels[currentLevel].getStroke()),10,60);
      gBuffer.drawString("Current Level: " + Integer.toString(currentLevel+1),10,30);
   }
   
   
   private void drawBall()
   {
      int bx = (int)Math.round(ball.getX());
      int by = (int)Math.round(ball.getY());
      gBuffer.drawImage(ballpic,bx,by,this);
   
   }
   private boolean checkCollision()
   {
      if(ball.getX() < 0 || ball.getX() > 1000-ball.getR())
      {
         ball.setV(ball.getxV() * -1, ball.getyV());
         bounce.play();
      }
      if(ball.getY() < 0 || ball.getY() > 650-ball.getR())
         {
           ball.setV(ball.getxV(),ball.getyV() * -1);
            bounce.play();
         }

      for(Obstacle obs:levels[currentLevel].obstacles) 
      {
         switch(obs.checkCollision(ball.getVectors()))
         {
            case 'n': ball.setV(ball.getxV(),ball.getyV() * -1.0);bounce.play();
               return true;
            case 's': ball.setV(ball.getxV(),ball.getyV() * -1.0);bounce.play();
               return true;
            case 'w': ball.setV(ball.getxV() * -1.0, ball.getyV());bounce.play();
               return true;
            case 'e': ball.setV(ball.getxV() * -1.0, ball.getyV());bounce.play();
               return true;
         }
      }
      
      for(CircularHazard ch: levels[currentLevel].circHazard)
      {
         if(ch.checkCollide(ball))
         {infoBox("Water Hazard! \n stroke +1","Hazard!");
            levels[currentLevel].stroke++;
            ball.setPos((int)(ball.getX()-ball.getxV()*3),(int)(ball.getY()-ball.getyV()*3));
            ball.setV(0,0);
            return true;}
      }
      for(RectangularHazard ch: levels[currentLevel].rectHazard)
      {
         if(ch.checkCollide(ball))
         {infoBox("Water Hazard! \n stroke +1","Hazard!");
            levels[currentLevel].stroke++;
            ball.setPos((int)(ball.getX()-ball.getxV()*3),(int)(ball.getY()-ball.getyV()*3));
            ball.setV(0,0);
         
            return true;}
      }
      for(Rough r:levels[currentLevel].roughs)
      {
         if(r.checkCollide(ball))
         {
            ball.setV(ball.getxV()*0.967,ball.getyV() * 0.967);
            System.out.println("Ball v set to: " + ball.getxV()*0.967 + " " + ball.getyV() * 0.967);
         }  
      }
      
      if(levels[currentLevel].hole.checkCollide(ball))
      {
         if(ball.getVMag() > 3)
         {
                  ball.setV(ball.getxV() * 0.4,ball.getyV() * 0.4);
         System.out.println("jumped");
         }else{
         ball.setV(0,0);
         infoBox("Complete! Stroke: " + Integer.toString(levels[currentLevel].stroke),"Congratz");
         delay(1000);
         currentLevel++;
         if(currentLevel == levels.length)
         {
            ClearScreen();
            int totalScore = 0;
            for(int i = 0; i < levels.length;i++)
            {
               totalScore += levels[i].stroke;
            }
            infoBox("Game complete! \n Total Stroke: " + Integer.toString(totalScore), "Game Complete");
            finish = true;
            repaint();
         }
         else{
            repaint();
         }}
      }
      
      return false;
   }
   public static void infoBox(String infoMessage, String titleBar)
   {
      JOptionPane.showMessageDialog(null, infoMessage,titleBar, JOptionPane.INFORMATION_MESSAGE);
   }
   public void delay(int n)
   {
      long startDelay = System.currentTimeMillis();
      long endDelay = 0;
      while (endDelay - startDelay < n)
         endDelay = System.currentTimeMillis();	
   }
}



