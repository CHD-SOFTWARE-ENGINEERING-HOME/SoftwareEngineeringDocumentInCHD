//ʵ����ʾ����ѡ�� 

import java.awt.* ;
import java.applet.* ;

class DiagramEditorControls extends Panel
{
    
  DiagramEditor diagramEditor ;//diagramEditor������
  //������ѡ��
  Button deleteButton      = new Button("ɾ��Ԫ��") ;
  Button nextDiagramButton = new Button("��һҳ") ;
  Button newDiagramButton  = new Button("��  ҳ") ;
  Button amplify  = new Button("��  ��") ;
  Button reduce   = new Button("��  С") ;
  Choice toolChoice        = new Choice() ;
  //���캯����ʼ��
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
    add(new Label("���ߣ�" )) ;
    add(toolChoice) ;    
    add(deleteButton) ;
  }
  //�Ը���ѡ���¼����з�Ӧ
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

