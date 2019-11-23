import java.util.*;
public class Level
{
   public ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
   public ArrayList<CircularHazard> circHazard = new ArrayList<CircularHazard>();
   public ArrayList<RectangularHazard> rectHazard = new ArrayList<RectangularHazard>();
   public ArrayList<Rough> roughs = new ArrayList<Rough>();
   public Hole hole;
   public Ball ball;
   public int stroke = 0;
   
   public void addObs(Obstacle obs)
   {
      obstacles.add(obs);
   }
   public void addCircH(CircularHazard ch)
   {
      circHazard.add(ch);
      System.out.println("CircH added");
   }
   public void addRectH(RectangularHazard rh)
   {
      rectHazard.add(rh);
   }
   public void addRough(Rough r)
   {
      roughs.add(r);
   }
   public void setHole(int x,int y)
   {
      hole = new Hole(x,y);
   }
   public void setBall(int x, int y, int r)
   {
      ball  = new Ball(x,y,r);
   }
   public int getStroke()
   {
      return stroke;
   }
   
}