//ʵ��ͼ�α༭����
import java.awt.* ;
import java.applet.* ;
import java.util.* ;

public class DiagramEditor extends Canvas
{
  //���ڴ�Ŷ��diagram  
  private Vector diagrams = new Vector(16) ;
  //��ǰ��diagram
  Diagram currentDiagram ;
  //���ư棺���ư�ť��ѡ��򣬵���setControls����������
  DiagramEditorControls controls ;
  //��������
  Tool tool ;
  //��������
  Image offscreen ;
  //������ʾ��ǩ������
  Label lbb;
  //��ɫѡ��
  Color col;

  //static float  co =1.0f;

  /*ת��Ϊint���ڣ�
  Ӧ��Switch���
  ��list�б���е�������Ӧ
  �����������еõ���Ӧ���ַ���
  */
  public final static int RECTANGLE = 0 ;
  public final static int LINE      = 1 ;
  public final static int ELLIPSE   = 2 ;
  public final static int SELECTION = 3 ;
  
  public String toolNames[]  = {"������", "ֱ��", "��Բ", "ѡ��"} ;
  
  //���캯����ʼ��
  public DiagramEditor(Label lb )
  {
    setBackground( Color.white ) ;
    lbb = lb ; //������ʾ��ǩ������
    col = Color.blue;
    newDiagram() ; 
    
  }
  //����DiagramEditorControls�����ã��Ա��ڶ�list�Ŀ���
  public void setControls( DiagramEditorControls c )
  {
    controls = c ;
  }   
  /*���б��仯ʱ�����ö�Ӧ��������ϵ��
  �����߱仯������µĻ����ѡ��Diagramʱ����*/  
  public void setTool( int t )
  {
   switch (t) {
      case RECTANGLE:
        tool = new WrectangleTool(currentDiagram) ;
        break ;
      case LINE:
        tool = new LineTool(currentDiagram) ;
        break ;
      case ELLIPSE:
        tool = new EllipseTool(currentDiagram) ;
        break ;
      case SELECTION:
        tool = new SelectionTool(currentDiagram) ;
        break ;
    }
    //ˢ�½���
    repaint() ;
    //�����б��ĳ�ʼѡ��ֵ���������µ�Diagramʱ�����������ù��ߵ�Ĭ��ֵ
    if (controls != null) {
      controls.toolChoice.select(t) ;
    }
  }
  
  public void zoom(float co){
	  currentDiagram.zoom(co);
	  repaint();
  }

  public void  paint(Graphics g)
  {
    Dimension canvasSize = getSize() ;
    if (offscreen == null) {
      offscreen = this.createImage(canvasSize.width, canvasSize.height) ;
    }
    Graphics og = offscreen.getGraphics() ;
    og.setColor(getBackground()) ;
    og.fillRect(0, 0, canvasSize.width, canvasSize.height) ;
    og.setColor(Color.black) ;
    og.drawRect(0, 0, canvasSize.width-10, canvasSize.height-10) ;   
    og.setColor(col) ;
    currentDiagram.draw(og) ;
    tool.draw(og) ;
    g.drawImage(offscreen, 0, 0, this) ;
  }
  //ɾ��ѡ��Ԫ��
  public void deleteElements()
  {
    tool.delete() ;
    repaint() ;
  }
  //ѡ����һ��Diagram
  public void nextDiagram()
  {
    if ( currentDiagram == diagrams.lastElement() ) {
      currentDiagram = (Diagram) diagrams.firstElement() ;
    }
    else {
      int diagramIndex = diagrams.indexOf(currentDiagram) ;
      currentDiagram = (Diagram) diagrams.elementAt(diagramIndex + 1) ;
    }
    setTool(RECTANGLE) ;
    //����ҳ���жϲ���
    judgepage();
    
  }
  //�жϵ�ǰҳ��ֵ
  private void judgepage()
  {
    Integer serial = new Integer(diagrams.indexOf(currentDiagram)+1);
    lbb.setText("��"+serial.toString()+"ҳ");
  }
  //�����µ�Didgram
  public void newDiagram()
  {
    //�ж�ͼ�����Ŀ��������16���������µ�ͼ�㣬���򣬸�����ʾ
    if (diagrams.isEmpty())
    {
        currentDiagram = new Diagram() ;
        diagrams.addElement(currentDiagram) ;
        setTool(RECTANGLE) ;  
        //����ҳ���жϲ���
        judgepage();
    }
    else if(diagrams.lastIndexOf(diagrams.lastElement())<15)
    {
        currentDiagram = new Diagram() ;
        diagrams.addElement(currentDiagram) ;
        setTool(RECTANGLE) ;  
        //����ҳ���жϲ���
        judgepage();
    }
    else
    {
        StringBuffer sign = new StringBuffer(lbb.getText());
        if(sign.length() < 6)        
            lbb.setText(lbb.getText()+"   �Ѵﵽ���ͼ����Ŀ��16����");
        else
            lbb.setText(lbb.getText() );
    }
    
  }
  //���������canvas�ϲ����Ĳ���
  public boolean mouseDown( Event e, int x, int y )
  {
    tool.press() ;
    repaint() ;
    return true ;
  }  
 
  public boolean mouseMove( Event e, int x, int y )
  {
    tool.move( new Point(x,y) ) ;
    repaint() ;
    return true ;
  }

  public boolean mouseDrag( Event e, int x, int y )
  {
    tool.move( new Point(x,y) ) ;
    repaint() ;
    return true ;
  }


  public boolean mouseUp( Event e, int x, int y )
  {
    tool.release() ;
    repaint() ;
    return true ;
  }
}
