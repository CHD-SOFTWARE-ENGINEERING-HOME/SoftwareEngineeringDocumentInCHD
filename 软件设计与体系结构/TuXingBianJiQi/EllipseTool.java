

import java.awt.* ;

public class EllipseTool extends CreationTool
{
  EllipseTool(Diagram d)
  {
    super(d) ;
  }

  Element newElement(Point start, Point stop)
  {
    return new Ellipse(start, stop) ;
  }

  void drawElement(Graphics g)
  {
    g.drawOval( Math.min(start.x,current.x),
                Math.min(start.y,current.y),
                Math.abs(start.x-current.x),
                Math.abs(start.y-current.y)) ;
  }
}
