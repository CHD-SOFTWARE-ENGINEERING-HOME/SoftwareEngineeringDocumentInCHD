package yanlai.chd;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static java.lang.System.*;

//Main类继承自Application
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btn = new Button("弹出窗口");

        btn.setOnMouseClicked(event -> out.println(alertWindow.display("新窗口", "是否关闭")));
        VBox vBox = new VBox();
        vBox.getChildren().add(btn);
        //向窗口添加元素btn
        vBox.setAlignment(Pos.CENTER);
        //将btn位置设置在中间
        Scene scene = new Scene(vBox, 400, 400);
        //设置窗口大小为400x400
        primaryStage.setTitle("弹出窗口示例");
        //初始窗口标题为“弹出窗口示例
        primaryStage.setScene(scene);
        //将场景至于窗口中
        primaryStage.show();
        //显示窗口
    }

    public static void main(String[] args) {
        launch(args);
    }
}