
//??????????
import java.awt.* ;

public abstract class CreationTool extends Tool
{
  //?????
  final static int LocatingStart = 0;
  final static int LocatingStop = 1 ;
  
  Point start ;//?????

  CreationTool(Diagram d)
  {
    super(d) ;
    state = LocatingStart ;
  }
  //????????§Õ???????¦Ë???????¦Í??§Ý???
  void draw( Graphics g )
  {
    switch (state) {
      case LocatingStart:
        break ;
      case LocatingStop:
        g.setColor(Color.pink) ;
        drawElement(g) ;
        break ;
    }
  }
  //????????
  void move( Point p )
  {
    current = p ;
    switch (state) {
      case LocatingStart:
        break ;
      case LocatingStop:
        // Draw action implemented in draw() method.
        break ;
    }
  }
  //???????????????
  void press()
  { 
    switch (state) {
      case LocatingStart:
        start = current ;
        state = LocatingStop ;
        break ;
      case LocatingStop:
        break ;
    }
  }
  //????????¦Æ??????????
  void release()
  {
    switch (state) {
      case LocatingStart:
        break ;
      case LocatingStop:
        Element e = newElement(start, current) ;
        diagram.add(e) ;
        state = LocatingStart ;
        break ;
    }
  }
  //?????????????????
  abstract Element newElement(Point start, Point stop) ;
  //????????§Õ???????¦Ë???????¦Í??§Ý???
  abstract void    drawElement(Graphics g) ;
}
