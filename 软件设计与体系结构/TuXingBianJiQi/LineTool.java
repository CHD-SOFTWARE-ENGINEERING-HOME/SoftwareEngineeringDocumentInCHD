

import java.awt.* ;

public class LineTool extends CreationTool
{
  LineTool(Diagram d)
  {
    super(d) ;
  }

  Element newElement(Point start, Point stop)
  {
    return new Line(start, stop) ;
  }

  void drawElement(Graphics g)
  {
    g.drawLine( start.x, start.y, current.x, current.y ) ;
  }
}
