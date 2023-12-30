//实现图形编辑功能
import java.awt.* ;
import java.applet.* ;
import java.util.* ;

public class DiagramEditor extends Canvas
{
  //用于存放多个diagram  
  private Vector diagrams = new Vector(16) ;
  //当前的diagram
  Diagram currentDiagram ;
  //控制版：控制按钮和选择框，调用setControls来建立引用
  DiagramEditorControls controls ;
  //创建工具
  Tool tool ;
  //建立缓存
  Image offscreen ;
  //建立提示标签的引用
  Label lbb;
  //颜色选项
  Color col;

  //static float  co =1.0f;

  /*转化为int便于：
  应用Switch语句
  和list列表框中的索引对应
  用于在数组中得到相应的字符串
  */
  public final static int RECTANGLE = 0 ;
  public final static int LINE      = 1 ;
  public final static int ELLIPSE   = 2 ;
  public final static int SELECTION = 3 ;
  
  public String toolNames[]  = {"长方形", "直线", "椭圆", "选择"} ;
  
  //构造函数初始化
  public DiagramEditor(Label lb )
  {
    setBackground( Color.white ) ;
    lbb = lb ; //建立提示标签的引用
    col = Color.blue;
    newDiagram() ; 
    
  }
  //建立DiagramEditorControls的引用，以便于对list的控制
  public void setControls( DiagramEditorControls c )
  {
    controls = c ;
  }   
  /*当列表框变化时，设置对应的依赖关系，
  当工具变化或产成新的或进行选择Diagram时调用*/  
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
    //刷新界面
    repaint() ;
    //设置列表框的初始选项值，当产生新的Diagram时，将用来设置工具的默认值
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
  //删除选择元素
  public void deleteElements()
  {
    tool.delete() ;
    repaint() ;
  }
  //选择下一个Diagram
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
    //调用页码判断操作
    judgepage();
    
  }
  //判断当前页码值
  private void judgepage()
  {
    Integer serial = new Integer(diagrams.indexOf(currentDiagram)+1);
    lbb.setText("第"+serial.toString()+"页");
  }
  //生成新的Didgram
  public void newDiagram()
  {
    //判断图层的数目，不超过16个，产生新的图层，否则，给出提示
    if (diagrams.isEmpty())
    {
        currentDiagram = new Diagram() ;
        diagrams.addElement(currentDiagram) ;
        setTool(RECTANGLE) ;  
        //调用页码判断操作
        judgepage();
    }
    else if(diagrams.lastIndexOf(diagrams.lastElement())<15)
    {
        currentDiagram = new Diagram() ;
        diagrams.addElement(currentDiagram) ;
        setTool(RECTANGLE) ;  
        //调用页码判断操作
        judgepage();
    }
    else
    {
        StringBuffer sign = new StringBuffer(lbb.getText());
        if(sign.length() < 6)        
            lbb.setText(lbb.getText()+"   已达到最大图层数目—16个！");
        else
            lbb.setText(lbb.getText() );
    }
    
  }
  //处理鼠标在canvas上产生的操作
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
