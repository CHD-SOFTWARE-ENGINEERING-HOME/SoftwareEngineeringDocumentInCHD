package yanlai.chd;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

class Listener implements ActionListener
{
    private myApplet mya;  //myApplet对象引用
    //通过构造方法得到myApplet对象的引用
    public Listener(myApplet a){
        this.mya = a;
    }
    public void actionPerformed(ActionEvent e)
    {
        //改变myApplet对象的属性d的内容
        mya.d=Double.valueOf(mya.input.getText()).doubleValue();
        //调用myApplet对象refresh()进行刷新
        mya.refresh();
    }
};
public class myApplet extends Applet{
    public TextField input; //修改为public
    public double d=0.0;    //修改为public

    public void init()
    {
        input = new TextField(10);
        add(input);
        //input与监听者建立引用关系
        input.addActionListener(new Listener(this));
    }
    public void paint(Graphics g)
    {
        g.drawString("您输入了数据"+d,10,50);
    }
    //增加刷新方法进行刷新
    public void refresh(){
        repaint();
    }
}

