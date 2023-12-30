
import java.awt.* ;
import java.util.* ;
//实现选择工具
public class SelectionTool extends Tool
{ //状态表示
  final static int Locating = 0 ;
  final static int Moving   = 1 ;
  final static int Resizing = 2 ;
  final static int Error    = 3 ;
  
  //上次移动点
  Point lastPoint ;
  //引用选择图形对象的数据结构
  Vector selected = new Vector(16) ;
  //resizing引用需要改变尺寸的选择对象
  Element resizing ;
  //构造函数初始化
  SelectionTool(Diagram d) {
    super(d) ;
    state = Locating ;
  }
 //高亮选择此元素
  void draw( Graphics g )
  {
    Enumeration en = selected.elements() ;
    while (en.hasMoreElements()) {
      Element e = (Element) en.nextElement() ;
      e.highlight(g) ;
    }
  }
  //删除时，则将选中的元素全部去掉。
  void delete()
  {
    switch (state) {
      case Locating:
        Enumeration en = selected.elements() ;
        while (en.hasMoreElements()) {
          Element e = (Element) en.nextElement() ;	
		  unselect(e);
          diagram.remove(e) ;
        }       
        break ;
      case Moving:
        break ;
      case Resizing:
        break ;
      case Error:
        break ;
    }
  }
  /*
  对于移动和resize时的处理
  current.x-lastPoint.x
  构成了移动的简单算距离的算法
  */
  void move( Point p )
  {
    current = p ;
    switch (state) {
      case Locating:
        break ;
      case Moving:
        Enumeration en = selected.elements() ;        
        while (en.hasMoreElements()) {
           Element e = (Element) en.nextElement() ;
           e.move(current.x-lastPoint.x, current.y-lastPoint.y) ;
        }        
        break ;
      case Resizing:
        resizing.moveControl(current.x-lastPoint.x, current.y-lastPoint.y) ;
        break ;
      case Error:        
        break ;
    }
    lastPoint = current ;
  }
/*
本句拥有bug，因为正方形嵌套后无法察觉
原因在于：
1选择的标志为Element el = diagram.find(current) 
而此句在查到第一个时，就不在往下查是否包含
2控制点检查也存在如果原先没有选择，则只是进行了状态的转化，没有任何表示
3一旦被选择，不会去掉选择。
*/
  void press()
  {
    switch (state) {
      case Locating:
        Enumeration en = selected.elements() ;       
        while (en.hasMoreElements()) {
          Element el = (Element) en.nextElement() ;
          if (el.findControl(current)) {
            resizing = el ;
            break ;
          }
        }
        if (resizing != null) {
          state = Resizing ;
        } 
		else{//可以嵌套选择            
            en = diagram.getElements();            
            while (en.hasMoreElements()) {
                Element e = (Element) en.nextElement() ;
                if (e.contains(current)) {
					select(e) ;					
					state = Moving ;                                      
                    break;
                }               
                else {
                    state = Error ;
                }                 
            }           
        }
        break ;
      case Moving:
        break ;
      case Resizing:
        break ;
      case Error:
        break ;
    }
  }
//如果此元素没有被选择，则进行选择
  void select(Element e)
  {
    if (!selected.contains(e)) {
      selected.addElement(e) ;
    }
  }
//去掉选择，删除时调用
  void unselect(Element e)
  {
    selected.removeElement(e) ;
  }
//状态转化
  void release()
  {
    switch (state) {
      case Locating:
        break ;
      case Moving:  
		state = Locating ;
        break ;
      case Resizing:
        resizing = null ;
        state = Locating ;
        break ;
      case Error:
        state = Locating ;	     
        //如果发现本点没有被任何图形包含时，将选择清除
        int mark=0;
        Enumeration en = diagram.getElements() ;
        while (en.hasMoreElements()) {
           Element e = (Element) en.nextElement() ;
           if (e.contains(current)) {
               mark=1;
               break;
           }           
        }
        if (mark==0)
        {
            selected.removeAllElements();
        }
        break ;
    }
  }
}
