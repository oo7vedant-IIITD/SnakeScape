package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
class Pair{
    int x;
    int y;
    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
    //getters
    int getX(){
        return x;
    }
    int getY(){
        return y;
    }
}
public class Main extends Application
{
    static Map <Integer,Pair> map = new HashMap<>(); //holds the mapping of cords vs tile values
    static Player p1;
    static Player p2;
    static Objects board[];
    static Stage Stage;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        Controller c= fxmlLoader.getController();
        Stage= primaryStage;
//        primaryStage.resizableProperty();
        primaryStage.setTitle("Ludo Master");
        Image icon = new Image("Ludo_Master_Icon.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 600, 660));
        primaryStage.setResizable(false);
        primaryStage.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run(){
                            try {
                                c.switchScene(primaryStage,"menu.fxml");
                            }
                            catch (Exception e){}

                        }
                    });
                }
                catch (Exception e){

                }
            }
        }).start();

        // alert pop up maybe useful when we are trying to go back from the boardd
//        Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.setContentText("If you close it you will lose your progress.");
//        alert.setHeaderText("Are you sure you want to close the application?");
//        alert.setTitle("EXIT");

//        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
//        alert.getDialogPane().getButtonTypes().add(cancel);

        // I have assumed we are at the stage where we have taken the input from the user for the players
        String Player_name_1="Player 1";
        String Player_name_2="Player 2";
        p1 = new Player(Player_name_1,0,0,"p1");
        p2 = new Player(Player_name_2,0,0,"p2");
//        c.switchScene(primaryStage, "Board.fxml");

        // I have assumed we have switched to the game scene, now we have to begin the game
        board= new Objects[100];
        //board has 0 based indexing while the ladders and the snakes have 1 based indexing
        board[2] = new Ladders(3,24);
        board[6] = new Ladders(7,34);
        board[11] = new Ladders(12,31);
        board[19]  = new Ladders(20,41);
        board[35] = new Ladders(36,46);
        board[55] = new Ladders(56,63);
        board[59]  = new Ladders(60,81);
        board[68] = new Ladders(69,93);
        board[74] = new Ladders(75,95);
        board[77] = new Ladders(78,97);
        board[21] = new Snakes(22,2);
        board[14] = new Snakes(15,5);
        board[32] = new Snakes(33,8);
        board[43] = new Snakes(44,23);
        board[67] = new Snakes(68,50);
        board[78] = new Snakes(79,43);
        board[97] = new Snakes(98,82);
        board[84] = new Snakes(85,65);
        board[93] = new Snakes(94,47);
        board[91] = new Snakes(92,71);
    }



    public static void main(String[] args)
    {
        map_init();
        launch(args);

    }

    private static void map_init() {
        int lol=1;
        for (int i=9;i>=0;i--){
            if (i%2==1){
                for (int j=0;j<=9;j++){
                    map.put(lol,new Pair(i,j));
                    lol++;
                }
            }
            else {
                for (int j=9;j>=0;j--){
                    map.put(lol,new Pair(i,j));
                    lol++;
                }
            }
        }
    }
}
