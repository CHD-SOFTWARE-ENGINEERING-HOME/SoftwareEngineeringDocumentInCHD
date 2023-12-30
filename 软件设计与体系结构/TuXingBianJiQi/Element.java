
//��Ҫ������ͼ�εĳ���
import java.awt.* ;

public abstract class Element
{
  //ѡ�е���ɫ��ʾ
  static final Color highlightColor = Color.red ;
  //ͼ�δ����ľ�������
  Rectangle bbox ;//��Բ�ͳ����ζ�����Rectangle�����
  //���гߴ�ı�ʱ������
  int drag ;
  //���캯����ʼ��
  public Element(Point p, Point q)
  {
    resize(p, q) ;
  }
  //Ԫ�ص��ƶ���x��y�ĺ���Ϊ�ƶ��ľ���
  public void move(int x, int y)
  {
    bbox.x += x ;
    bbox.y += y ;
  }
  /*
  �����ṩ���������һ�����Σ�������㲻���������ô˷����Ϻ�
  �˾��ε������ǿ�ֱ���������ÿ��Ƶ�
  */
  public void resize(Point p, Point q)
  {
    bbox = new Rectangle(Math.min(p.x,q.x), Math.min(p.y,q.y),
                         Math.abs(p.x-q.x), Math.abs(p.y-q.y)) ;
  }
  //Ԫ�ص�resize���ƶ���չ��x��y�ĺ���Ϊ�ƶ��ľ���
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
    //�ƶ�����������Ĵ���
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
  //�����Ƶ��λ�ã�������λ�õĲ�ͬ���ֳ���ͬ��dragֵ
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
  //���ƿ��Ƶ�
  public void highlight(Graphics g)
  {
    drawHighlight(g, bbox.x, bbox.y, highlightColor) ;
    drawHighlight(g, bbox.x+bbox.width, bbox.y, highlightColor) ;
    drawHighlight(g, bbox.x, bbox.y+bbox.height, highlightColor) ;
    drawHighlight(g, bbox.x+bbox.width, bbox.y+bbox.height, highlightColor) ;
  }
  //���ؿ��ƾ������򣬵������е�ԭ����Ϊ������׼��
  Rectangle bounds()
  {
    return bbox ;
  }
  //�жϸ������Ƿ���������ڲ�
  public boolean contains( Point p )
  {
    return bounds().inside(p.x, p.y) ;
  }
  //���ƿ��Ƶ�С����
  void drawHighlight(Graphics g, int x, int y, Color c)
  {
    Color oldColor = g.getColor() ;
    g.setColor(c) ;
    g.drawRect(x-1, y-1, 3, 3) ;
    g.setColor(oldColor) ;
  }

  abstract void draw(Graphics g) ;
  
  //�жϵ���ĵ�Ϳ��Ƶ��λ���Ƿ��㹻��
  boolean nearEnough( Point p0, Point p1 )
  {
    return Math.abs(p0.x - p1.x) <= 3 && Math.abs(p0.y - p1.y) <= 3 ;
  }

  void zoom(float  co){
	  bbox.width  = (int)(bbox.width*co) ;
      bbox.height = (int)(bbox.height*co) ;
  }
}
