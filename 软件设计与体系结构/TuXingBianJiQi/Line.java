

import java.awt.* ;
import java.util.* ;

public class Line extends Element
{
  Point p0, p1 ;
  //调用组建的构造方法，为重载resize提供条件
  public Line(Point p, Point q)
  {
    super(p, q) ;
  }
  //重载上面的方法，改变本点的位置
  public void move(int x, int y)
  {
    p0.translate(x, y) ;
    p1.translate(x, y) ;
  }
  //重载resize方法
  public void resize(Point p, Point q)
  {
    p0 = new Point(p.x, p.y) ;
    p1 = new Point(q.x, q.y) ;
  }
  //重载moveControl方法
  public void moveControl(int x, int y)
  {
    switch (drag) {
      case 0:
        p0.translate(x, y) ;
        break ;
      case 1:
        p1.translate(x, y) ;
        break ;
    }
  }
  //重载findControl方法
  public boolean findControl( Point p )
  {
    drag = -1 ;
    if (nearEnough(p, p0)) {
      drag = 0 ;
    } else if (nearEnough(p, p1)) {
      drag = 1 ;
    }
    return drag != -1 ;
  }
  //绘制特殊的矩形区域
  Rectangle bounds()
  {
    return new Rectangle(Math.min(p0.x,p1.x), Math.min(p0.y,p1.y),
                         Math.abs(p0.x-p1.x), Math.abs(p0.y-p1.y)) ;
  }
  //重载highlight方法
  public void highlight(Graphics g)
  {
    drawHighlight(g, p0.x, p0.y, highlightColor) ;
    drawHighlight(g, p1.x, p1.y, highlightColor) ;
  }
  
  void draw(Graphics g)
  {
	g.drawLine( p0.x, p0.y, p1.x, p1.y ) ;
	
  }

  void zoom(float  co){
	   p1 = new Point((int)(p0.x+Math.abs(p0.x-p1.x)*co), (int)(p0.y+Math.abs(p0.y-p1.y)*co)) ;
  }
  
}
