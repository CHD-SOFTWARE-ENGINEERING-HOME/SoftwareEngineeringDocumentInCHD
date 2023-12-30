
//长方形对象 
import java.awt.* ;

public class Wrectangle extends Element
{ //构造函数初始化
  public Wrectangle(Point p, Point q)
  {
    super(p, q) ;
  }
  //刷新对象本身
  void draw(Graphics g)
  {
    
	g.drawRect( bbox.x, bbox.y, bbox.width, bbox.height) ;
  }
  
}
