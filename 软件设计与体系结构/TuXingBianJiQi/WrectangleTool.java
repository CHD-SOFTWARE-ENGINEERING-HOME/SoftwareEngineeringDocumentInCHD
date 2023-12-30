//ʵ�ֳ����ι��ߵĴ��� 

import java.awt.* ;

public class WrectangleTool extends CreationTool
{
  //���캯����ʼ��
  WrectangleTool(Diagram d)
  {
    super(d) ;
  }
  //����������
  Element newElement(Point start, Point stop)
  {
    return new Wrectangle(start, stop) ;
  }
  //�����εĻ���
  void drawElement(Graphics g)
  {
    g.drawRect( Math.min(start.x,current.x),
                Math.min(start.y,current.y),
                Math.abs(start.x-current.x),
                Math.abs(start.y-current.y)) ;
  }
}
