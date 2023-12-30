
//需要创建的图形的超类
import java.awt.* ;

public abstract class Element
{
  //选中的颜色表示
  static final Color highlightColor = Color.red ;
  //图形创建的矩形区域
  Rectangle bbox ;//椭圆和长方形都可用Rectangle来表达
  //进行尺寸改变时的类型
  int drag ;
  //构造函数初始化
  public Element(Point p, Point q)
  {
    resize(p, q) ;
  }
  //元素的移动，x、y的含义为移动的距离
  public void move(int x, int y)
  {
    bbox.x += x ;
    bbox.y += y ;
  }
  /*
  根据提供的两点产生一个矩形，因此两点不定，所以用此方法较好
  此矩形的作用是可直接用来设置控制点
  */
  public void resize(Point p, Point q)
  {
    bbox = new Rectangle(Math.min(p.x,q.x), Math.min(p.y,q.y),
                         Math.abs(p.x-q.x), Math.abs(p.y-q.y)) ;
  }
  //元素的resize的移动扩展，x、y的含义为移动的距离
  public void moveControl(int x, int y)
  {
    switch (drag) {
      case 0:
        bbox.x += x ;
        bbox.y += y ;
        bbox.width -= x ;
        bbox.height -= y ;
        break ;
      case 1:
        bbox.y += y ;
        bbox.width += x ;
        bbox.height -= y ;
        break ;
      case 2:
        bbox.width += x ;
        bbox.height += y ;
        break ;
      case 3:
        bbox.x += x ;
        bbox.width -= x ;
        bbox.height += y ;
        break ;
    }
    //移动的特殊情况的存在
    if (bbox.width < 0) {
      bbox.x += bbox.width ;
      bbox.width = -bbox.width ;
      drag += drag%2 == 0 ? 1 : -1 ;
    }
    if (bbox.height < 0) {
      bbox.y += bbox.height ;
      bbox.height = -bbox.height ;
      drag = 3-drag ;
    }
  }
  //检查控制点的位置，并根据位置的不同表现出不同的drag值
  public boolean findControl( Point p )
  {
    drag = -1 ;
    if (nearEnough(p, new Point(bbox.x, bbox.y))) {
      drag = 0 ;
    } else if (nearEnough(p, new Point(bbox.x+bbox.width, bbox.y))) {
      drag = 1 ;
    } else if (nearEnough(p, new Point(bbox.x+bbox.width, bbox.y+bbox.height))) {
      drag = 2 ;
    } else if (nearEnough(p, new Point(bbox.x, bbox.y+bbox.height))) {
      drag = 3 ;
    }
    return drag != -1 ;
  }
  //绘制控制点
  public void highlight(Graphics g)
  {
    drawHighlight(g, bbox.x, bbox.y, highlightColor) ;
    drawHighlight(g, bbox.x+bbox.width, bbox.y, highlightColor) ;
    drawHighlight(g, bbox.x, bbox.y+bbox.height, highlightColor) ;
    drawHighlight(g, bbox.x+bbox.width, bbox.y+bbox.height, highlightColor) ;
  }
  //返回控制矩形区域，单独罗列的原因是为重载作准备
  Rectangle bounds()
  {
    return bbox ;
  }
  //判断给定点是否包含在其内部
  public boolean contains( Point p )
  {
    return bounds().inside(p.x, p.y) ;
  }
  //绘制控制点小矩形
  void drawHighlight(Graphics g, int x, int y, Color c)
  {
    Color oldColor = g.getColor() ;
    g.setColor(c) ;
    g.drawRect(x-1, y-1, 3, 3) ;
    g.setColor(oldColor) ;
  }

  abstract void draw(Graphics g) ;
  
  //判断点击的点和控制点的位置是否足够近
  boolean nearEnough( Point p0, Point p1 )
  {
    return Math.abs(p0.x - p1.x) <= 3 && Math.abs(p0.y - p1.y) <= 3 ;
  }

  void zoom(float  co){
	  bbox.width  = (int)(bbox.width*co) ;
      bbox.height = (int)(bbox.height*co) ;
  }
}
