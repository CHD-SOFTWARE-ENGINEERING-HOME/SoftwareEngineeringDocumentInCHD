
import java.awt.* ;
import java.util.* ;
//ʵ��ѡ�񹤾�
public class SelectionTool extends Tool
{ //״̬��ʾ
  final static int Locating = 0 ;
  final static int Moving   = 1 ;
  final static int Resizing = 2 ;
  final static int Error    = 3 ;
  
  //�ϴ��ƶ���
  Point lastPoint ;
  //����ѡ��ͼ�ζ�������ݽṹ
  Vector selected = new Vector(16) ;
  //resizing������Ҫ�ı�ߴ��ѡ�����
  Element resizing ;
  //���캯����ʼ��
  SelectionTool(Diagram d) {
    super(d) ;
    state = Locating ;
  }
 //����ѡ���Ԫ��
  void draw( Graphics g )
  {
    Enumeration en = selected.elements() ;
    while (en.hasMoreElements()) {
      Element e = (Element) en.nextElement() ;
      e.highlight(g) ;
    }
  }
  //ɾ��ʱ����ѡ�е�Ԫ��ȫ��ȥ����
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
  �����ƶ���resizeʱ�Ĵ���
  current.x-lastPoint.x
  �������ƶ��ļ��������㷨
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
����ӵ��bug����Ϊ������Ƕ�׺��޷����
ԭ�����ڣ�
1ѡ��ı�־ΪElement el = diagram.find(current) 
���˾��ڲ鵽��һ��ʱ���Ͳ������²��Ƿ����
2���Ƶ���Ҳ�������ԭ��û��ѡ����ֻ�ǽ�����״̬��ת����û���κα�ʾ
3һ����ѡ�񣬲���ȥ��ѡ��
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
		else{//����Ƕ��ѡ��            
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
//�����Ԫ��û�б�ѡ�������ѡ��
  void select(Element e)
  {
    if (!selected.contains(e)) {
      selected.addElement(e) ;
    }
  }
//ȥ��ѡ��ɾ��ʱ����
  void unselect(Element e)
  {
    selected.removeElement(e) ;
  }
//״̬ת��
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
        //������ֱ���û�б��κ�ͼ�ΰ���ʱ����ѡ�����
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
