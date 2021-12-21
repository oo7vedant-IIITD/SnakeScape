package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class Controller
{
    static int roll;
    private Stage stage;
    private Scene scene;
    private Parent root;
    boolean isValid(String name){
        int n=name.length();
        if (n==0) return false;
        //checks if the name is alpha numeric
        for (int i=0;i<n;i++){
            if (!Character.isLetter(name.charAt(i)) && !Character.isDigit(name.charAt(i))) return false;
        }
        return true;
    }
    public void switchScene(Stage stage,String url) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        Parent root = fxmlLoader.load();
        stage = stage;
//        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    public void Quit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    private Button Back;

    @FXML
    private Button Dice;

    @FXML
    private ImageView Dice_arrow;

    @FXML
    private ImageView Piece_1;

    @FXML
    private ImageView Piece_2;

    @FXML
    private ImageView Player_1_image_view;

    @FXML
    private ImageView Player_2_image_view;

    @FXML
    private Label label_1;

    @FXML
    private Label label_2;

    @FXML
    private ImageView Dice_view_changer;

    @FXML
    void exit_pop_up(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("If you close it you will lose your progress.");
        alert.setHeaderText("Are you sure you want to go back to main menu?");
        alert.setTitle("BACK");

        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            try {
                switchScene(Main.Stage,"menu.fxml");
            }
            catch (Exception e) {

            }
        }

    }

    boolean player1_move=false;
    boolean player2_move=true;

    public int move_player(int x,Player p1,Objects[] board){
        // print the coordinates and tile number of p1

        System.out.println(p1.getName() + " rolled a " + x);

        if (p1.getStatus())
        {
            if (p1.getTile() + x > 100)
                return 0;
            //generic movement transition will occur right here
            for (int i = 1; i <= x; i++)
            {
                Pair p = Main.map.get(p1.getTile());
                System.out.println(p1.getName() + " is on tile " + p1.getTile());
                p1.setTile(p1.getTile() + 1);

                TranslateTransition t = new TranslateTransition(Duration.millis(1000));
                t.setAutoReverse(false);
                if (p.getX()%2 == 1)
                {
                    //move right only if p.getY()!=9
                    if (p1.getRole().equals("p1"))
                    {
                        t.setNode(Piece_1);
                        if (p.getY()!=9)
                        {
                            //transition right
                            t.setToX(Piece_1.getX() + 50);
                            t.play();
                        }
                        else
                        {
                            //transition above
                            t.setToY(Piece_1.getY() - 50);
                            t.play();
                        }
                    }
                    else
                    {
                        t.setNode(Piece_2);
                        if (p.getY()!=9)
                        {
                            //transition right
                            t.setToX(Piece_2.getX()+50);
                            t.play();
                        }
                        else {
                            //transition above
                            t.setToY(Piece_2.getY()-50);
                            t.play();
                        }
                    }

                }
                else
                {
                    //move left only if p.getY()!=0
                    //note the tile size of one pixel is around 50
                    if (p1.getRole().equals("p1"))
                    {
                        t.setNode(Piece_1);
                        if (p.getY()!=0)
                        {
                            //transition left
                            t.setToX(Piece_1.getX()-50);
                            t.play();
                        }
                        else
                        {
                            //transition below
                            t.setToY(Piece_1.getY()-50);
                            t.play();
                        }
                    }
                    else
                    {
                        t.setNode(Piece_2);
                        if (p.getY()!=0)
                        {
                            //transition left
                            t.setToX(Piece_2.getX()-50);
                            t.play();
                        }
                        else
                        {
                            //transition below
                            t.setToY(Piece_2.getY()-50);
                            t.play();
                        }
                    }
                }
            }

            if (p1.isWinner())
            {
                return 1;
            }
//            Object obj = board[p1.getTile()-1];
//            if (obj instanceof Ladders)
//            {
//                p1.setTile( ((Ladders) obj).getEnd() );
//                // insert the movement animation depending on the tile value and obj instance
//            }
//            else if (obj instanceof Snakes)
//            {
//                p1.setTile( ((Snakes) obj).getEnd() );
//            }
            // have assumed  that no ladder will take directly to 100 tile
        }
        else
        {
            if (x==1)
            {
                p1.setStatus(true);
                p1.setTile(1);
                TranslateTransition t;

                if (p1.getRole().equals("p1"))
                {
                    t = new TranslateTransition(Duration.ONE, Piece_1);
                    //move t on y axis by 50 points above
                    t.setToY(Piece_1.getY()-50);
                    t.setAutoReverse(false);
                }
                else
                {
                    //do the same but for piece 2
                    t = new TranslateTransition(Duration.ONE, Piece_2);
                    t.setToY(Piece_2.getY()-50);
                    t.setAutoReverse(false);
                }
                t.play();
            }
        }
        return 0;
    }

    @FXML
    private Text Status_Bar;

    @FXML
    void getCoordinates(MouseEvent event)
    {
        Status_Bar.setText("X : " + event.getX() + " Y : " + event.getY());
    }

    @FXML
    void Dice_roll(ActionEvent event)
    {
        Dice dice = new Dice();
        roll = dice.getVal();

        Image img = new Image("file:/D:/Coding/Java_Programs/Snake_and_Ladders/src/Dice_face_"+roll+".png");
        Dice_view_changer.setImage(img);
        //disable the image next to the dice for player
        new Thread(new Runnable() {
            @Override
            public void run() {
                int winner;
                try {

                }
                catch (Exception e){

                }
            }
        }).start();
        if (player1_move==false){
            winner=move_player(roll,Main.p1,Main.board);
            player1_move=true;
            player2_move=false;
            if (winner==1){
                // either design an alert pop up or just simply use another scene and switch according to user input
                // note that the same code would get copt pasted into the winner tile for player 2, consider making a function

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Winner : "+ Main.p1.getName()+" Loser : "+Main.p2.getName());
                alert.setTitle("Winner!");
                ButtonType answer = new ButtonType("REPLAY");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK){
                    try {
                        switchScene(Main.Stage,"menu.fxml");
                    }
                    catch (Exception e) {
                    }
                }
            }
            return;
        }
        //arrow animation here
        if (player2_move==false){
            winner=move_player(roll,Main.p2,Main.board);
            player1_move=false;
            player2_move=true;
            if (winner==1){
                // call some win function here for player 2
            }
        }


    }

    public void New_Game(ActionEvent actionEvent) throws Exception {
        // i assume you take the name input and verify it then use setName() function for the player to give them the appropriate name
        switchScene(Main.Stage,"board.fxml");
        new Thread(new Runnable () {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                }
                catch (Exception e){}
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            label_1.setText(Main.p1.getName());
                            label_2.setText(Main.p2.getName());
                        }
                        catch (Exception e){}
                    }
                });

            }
        }).start();


    }
}
