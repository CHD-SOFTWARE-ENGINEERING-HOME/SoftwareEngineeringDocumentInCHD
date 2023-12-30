 

import java.awt.* ;
import java.applet.* ;
//建立图形编辑器的整体运行环境
public class DiagramEditorApplet extends Applet
{
  //初始化，建立DiagramEditorControls、DiagramEditor、Label(提示标签)
  public void init()
  {
    setLayout( new BorderLayout() ) ;
    Label lb = new Label("窗口显示");//提示标签对象产生
    DiagramEditor dc = new DiagramEditor(lb) ;    
    //dc.setLabel(lb);            //传入标签引用
    add( "Center", dc ) ;
    add( "North", new DiagramEditorControls(dc) ) ;
    add("South",lb);           //提示标签加入面板
  }
}

