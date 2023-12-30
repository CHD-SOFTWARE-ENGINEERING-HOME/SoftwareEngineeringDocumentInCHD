package yanlai.chd;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
class alertWindow {
    private static boolean res;
    public static boolean display (String title , String msg)
    //弹出标题为title,显示msg信息的窗口
    {
        Stage stage = new Stage ();
        //创建一个新场景
        stage.initModality(Modality.APPLICATION_MODAL);
        //阻止此JavaFX应用程序打开的所有其他窗口,视觉显示为其他窗口变灰，无法进行操作
        Label label = new Label();
        //创建一个新标签
        label.setText(msg);
        //设置label的文本为msg

        Button btn1= new Button("是");
        Button btn2= new Button("否");
        //创建两个文本分别为”是“和”否“的按钮btn1和btn2

        btn1.setOnMouseClicked(event ->{
            res=true;
            System.out.println("你点击了是");
            stage.close();//关闭窗口
        });

        btn2.setOnMouseClicked(event ->{
            res=false;
            System.out.println("U clicked the FALSE");
            stage.close();//关闭窗口
        });
        VBox vBox=new VBox();
        vBox.getChildren().addAll(label,btn1,btn2);
        //将label,btn1,btn2均堆叠在垂直列中

        vBox.setAlignment(Pos.CENTER);
        //将vBox位置设置为中心
        Scene scene = new Scene(vBox,200,200);
        //创建200x200的窗口
        stage.setScene(scene);
        //将场景置于窗口中

        stage.setTitle(title);
        //将窗口标题设置为title
        stage.showAndWait();
        //显示窗口，且不会立刻返回，如果改为show，则res的值会不经判断直接打印

        return res;
        //返回res，通过println打印
    }
}