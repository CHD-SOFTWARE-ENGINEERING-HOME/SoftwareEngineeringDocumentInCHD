
//�����ζ��� 
import java.awt.* ;

public class Wrectangle extends Element
{ //���캯����ʼ��
  public Wrectangle(Point p, Point q)
  {
    super(p, q) ;
  }
  //ˢ�¶�����
  void draw(Graphics g)
  {
    
	g.drawRect( bbox.x, bbox.y, bbox.width, bbox.height) ;
  }
  
}
