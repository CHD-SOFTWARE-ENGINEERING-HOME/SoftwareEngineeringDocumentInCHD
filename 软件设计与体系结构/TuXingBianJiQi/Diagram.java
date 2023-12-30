//实现图层 

import java.awt.* ;
import java.util.* ;

public class Diagram
{
  //建立图形引用数组
  //private Vector elements = new Vector(16) ;
  private  Vector elements = new Vector(16) ;//为了能够嵌套选择而修改
  //添加图形引用
  public void add( Element e )
  {
    elements.addElement(e) ;
  }
  //删除图形引用
  public void remove( Element e )
  {
    elements.removeElement(e) ;
  }
  //确定某点是否在图形内部，如果为真，返回本图形的引用
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
  //对成为本图层的引用的对象进行刷新
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

