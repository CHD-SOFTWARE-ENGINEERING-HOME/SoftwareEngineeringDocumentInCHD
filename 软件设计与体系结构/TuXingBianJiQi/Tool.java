//�������ߺ�ѡ�񹤾ߵĳ��� 
import java.awt.* ;
public abstract class Tool
{
  Point current ;//��ǰ��
  int   state ;//״̬��ʾ

  Diagram diagram ;//Diagram������
  //���캯����ʼ��
  Tool(Diagram d) {
    diagram = d ;
  }
  //ˢ�º���
  void draw(Graphics g) {}
  //ɾ������
  void delete() {}
  //����ƶ�
  abstract void move( Point p ) ;
  //��갴��
  abstract void press() ;
  //����ͷ�
  abstract void release() ;
}
