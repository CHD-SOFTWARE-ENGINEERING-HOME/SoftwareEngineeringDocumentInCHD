 

import java.awt.* ;
import java.applet.* ;
//����ͼ�α༭�����������л���
public class DiagramEditorApplet extends Applet
{
  //��ʼ��������DiagramEditorControls��DiagramEditor��Label(��ʾ��ǩ)
  public void init()
  {
    setLayout( new BorderLayout() ) ;
    Label lb = new Label("������ʾ");//��ʾ��ǩ�������
    DiagramEditor dc = new DiagramEditor(lb) ;    
    //dc.setLabel(lb);            //�����ǩ����
    add( "Center", dc ) ;
    add( "North", new DiagramEditorControls(dc) ) ;
    add("South",lb);           //��ʾ��ǩ�������
  }
}

