//创建工具和选择工具的超类 
import java.awt.* ;
public abstract class Tool
{
  Point current ;//当前点
  int   state ;//状态表示

  Diagram diagram ;//Diagram的引用
  //构造函数初始化
  Tool(Diagram d) {
    diagram = d ;
  }
  //刷新函数
  void draw(Graphics g) {}
  //删除函数
  void delete() {}
  //鼠标移动
  abstract void move( Point p ) ;
  //鼠标按下
  abstract void press() ;
  //鼠标释放
  abstract void release() ;
}
