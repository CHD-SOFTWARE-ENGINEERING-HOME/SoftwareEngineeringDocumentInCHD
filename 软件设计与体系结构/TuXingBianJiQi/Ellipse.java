
import java.awt.* ;

public class Ellipse extends Element
{
  public Ellipse(Point p, Point q)
  {
    super(p, q) ;
  }
  //ªÊ÷∆Õ÷‘≤
  void draw(Graphics g)
  {
    g.drawOval( bbox.x, bbox.y, bbox.width, bbox.height) ;
	
  }
  
}
