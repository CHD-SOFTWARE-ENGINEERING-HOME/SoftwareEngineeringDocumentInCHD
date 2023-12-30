//ʵ��ͼ�� 

import java.awt.* ;
import java.util.* ;

public class Diagram
{
  //����ͼ����������
  //private Vector elements = new Vector(16) ;
  private  Vector elements = new Vector(16) ;//Ϊ���ܹ�Ƕ��ѡ����޸�
  //���ͼ������
  public void add( Element e )
  {
    elements.addElement(e) ;
  }
  //ɾ��ͼ������
  public void remove( Element e )
  {
    elements.removeElement(e) ;
  }
  //ȷ��ĳ���Ƿ���ͼ���ڲ������Ϊ�棬���ر�ͼ�ε�����
  public Element find( Point p )
  {
    Enumeration en = elements.elements() ;
    while (en.hasMoreElements()) {
      Element e = (Element) en.nextElement() ;
      if (e.contains(p)) {
        return e ;
      }
    }
    return null ;
  }
  //�Գ�Ϊ��ͼ������õĶ������ˢ��
  public void draw( Graphics g )
  {
    Enumeration en = elements.elements() ;
    while (en.hasMoreElements()) {
      Element e = (Element) en.nextElement() ;
      e.draw(g) ;
    }
  }
  public void zoom(float co){
	Enumeration en = elements.elements() ;
    while (en.hasMoreElements()) {
      Element e = (Element) en.nextElement() ;
      e.zoom(co) ;
    }
  }
  public  Enumeration getElements(){
	   return elements.elements() ;
  }
}

