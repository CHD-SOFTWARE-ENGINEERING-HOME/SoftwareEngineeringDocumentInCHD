//实现长方形工具的创建 

import java.awt.* ;

public class WrectangleTool extends CreationTool
{
  //构造函数初始化
  WrectangleTool(Diagram d)
  {
    super(d) ;
  }
  //创建长方形
  Element newElement(Point start, Point stop)
  {
    return new Wrectangle(start, stop) ;
  }
  //长方形的绘制
  void drawElement(Graphics g)
  {
    g.drawRect( Math.min(start.x,current.x),
                Math.min(start.y,current.y),
                Math.abs(start.x-current.x),
                Math.abs(start.y-current.y)) ;
  }
}
