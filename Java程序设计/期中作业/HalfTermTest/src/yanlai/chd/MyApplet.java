package yanlai.chd;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class MyApplet extends Applet implements KeyListener{
    private TextField input;
    private double d=0.0;
    /**
     进行初始化工作，产生对象，加入监听者
     */
    public void init()
    {
        input = new TextField(10);
        //myApplet是容器，input是组件，调用add使input嵌入容器
        add(input);
        input.addKeyListener(this);
    }
    public void paint(Graphics g)
    {
        g.drawString("您输入了数据"+d,10,50);
    }
    public void keyPressed(KeyEvent e)
    {}
    public void keyReleased(KeyEvent e)
    {
        //首先得到Double类的对象，之后调用对象方法doubleValue得到值
        d=Double.valueOf(input.getText()).doubleValue();
        //进行刷新，调用paint()方法
        repaint();
    }
    public void keyTyped(KeyEvent e)
    {}
}
