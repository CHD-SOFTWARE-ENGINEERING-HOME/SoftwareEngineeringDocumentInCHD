//实现提示功能选择 

import java.awt.* ;
import java.applet.* ;

class DiagramEditorControls extends Panel
{
    
  DiagramEditor diagramEditor ;//diagramEditor的引用
  //各功能选项
  Button deleteButton      = new Button("删除元素") ;
  Button nextDiagramButton = new Button("下一页") ;
  Button newDiagramButton  = new Button("新  页") ;
  Button amplify  = new Button("放  大") ;
  Button reduce   = new Button("缩  小") ;
  Choice toolChoice        = new Choice() ;
  //构造函数初始化
  public DiagramEditorControls( DiagramEditor e )
  {
    diagramEditor = e ;
    diagramEditor.setControls(this) ;
    
    for (int i = 0; i < diagramEditor.toolNames.length; i++) {
      toolChoice.addItem(diagramEditor.toolNames[i]) ;
    }   
    add(newDiagramButton) ;
    add(nextDiagramButton) ;
	add(amplify);
	add(reduce);
    add(new Label("工具：" )) ;
    add(toolChoice) ;    
    add(deleteButton) ;
  }
  //对各种选择事件进行反应
  public boolean action( Event e, Object arg )
  {
    if (e.target == deleteButton) {
      diagramEditor.deleteElements() ;
    } else if (e.target == nextDiagramButton) {
      diagramEditor.nextDiagram() ;
    } else if (e.target == newDiagramButton) {
      diagramEditor.newDiagram() ;
    } else if ( e.target == toolChoice ) {
      int toolIndex = ((Choice) e.target).getSelectedIndex() ;
      diagramEditor.setTool(toolIndex) ;
    }  
	else if(e.target == amplify){
	  diagramEditor.zoom(1.1f);
	}
	else if(e.target == reduce){
	  diagramEditor.zoom(0.9f);
	}
    return true ;
  }
}

