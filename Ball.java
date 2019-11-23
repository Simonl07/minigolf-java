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

import java.awt.geom.Line2D.Double;
import java.awt.geom.Line2D;

public class Ball 
{
   private double x;
   private double y;
   private double xV;
   private double yV;
   private int r;
   private double friction;
   Line2D vector1;
   Line2D vector2;
   Line2D vector3;
   Line2D vector4;
   Line2D vector5;
   private double vMag = 0;
   
   
   public Ball(double x, double y, int r)
   {
      System.out.println("ball constructed");
      this.x = x;
      this.y = y;
      this.r = r;
      this.friction = 0.991;
      xV = 0;
      yV = 0;
      vector1 = new Line2D.Double(0,0,0,0);
      vector2 = new Line2D.Double(0,0,0,0);
      vector3 = new Line2D.Double(0,0,0,0);
      vector4 = new Line2D.Double(0,0,0,0);
      vector5 = new Line2D.Double(0,0,0,0);
           
   }
   
   public void setV(double xVel, double yVel)
   {
      System.out.println("V set to " + xVel + "," + yVel);
      xV = xVel;
      yV = yVel;
      vMag = Math.sqrt(Math.pow(xV,2) + Math.pow(yV,2));
      System.out.println("Vector Magnitude set to: " + vMag);
   }
   public void recordVectors()
   {
      vector1.setLine(x+r/2,y+r/2,x+r/2+xV,y+r/2+yV);
      vector2.setLine(x+r/2,y,x+r/2+xV,y+yV);
      vector3.setLine(x+r,y+r/2,x+r+xV,y+r/2+yV);
      vector4.setLine(x+r/2,y+r,x+r/2+xV,y+r+yV);
      vector5.setLine(x,y+r/2,x+xV,y+r/2+yV);
   }
   public void updateBall()
   {
      
      if(Math.abs(xV) > 0.08 || Math.abs(yV) > 0.08)
      {
         
         xV*=friction;
         yV*=friction;
         vMag = Math.sqrt(Math.pow(xV,2) + Math.pow(yV,2));
         System.out.println("Vector Magnitude set to: " + vMag);
         x+=xV;
         y+=yV;
      }
      
      
   }
   
   
   public double getVMag()
   {
      return vMag;
   }
   public Line2D[] getVectors()
   {
      Line2D[] vectors = {vector1,vector2,vector3,vector4,vector5};
      return vectors;
   }
   public double getX(){
      return x;}
   public double getY(){
      return y;}
   public double getxV(){
      return xV;}
   public double getyV(){
      return yV;}
   public int getR(){
      return r;}
   
   public void setPos(int x, int y)
   {
      this.x = x;
      this.y = y;
   }
  
   
}