package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        Controller c= fxmlLoader.getController();

        primaryStage.setTitle("Ludo Master");
        Image icon = new Image("Ludo_Master_Icon.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 660, 790));
        primaryStage.setResizable(false);
        primaryStage.show();
//        Thread thread = new Thread()
//        {
//            public void run()
//            {
//                try
//                {
//                    Thread.sleep(3000);
//                }
//                catch (InterruptedException | IOException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        };
//        thread.start();
        Thread.sleep(3000);
        c.switchScene(primaryStage);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
