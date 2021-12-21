package sample;

import javafx.animation.TranslateTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.Optional;
import javafx.scene.Group;
public class Controller
{
    static double[] player1_cord;
    static double[] player2_cord;
    static int roll;
    private Stage stage;
    private Scene scene;
    private Parent root;

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
    private ImageView Choosing_menu;

    @FXML
    private Button Play2;

    @FXML
    private TextField Player_1_text_field;

    @FXML
    private TextField Player_2_text_field;

    boolean isValid(String name)
    {
        int n=name.length();
        if (n==0) return false;
        //checks if the name is alpha numeric
        for (int i=0;i<n;i++)
        {
            if (!Character.isLetter(name.charAt(i)) && !Character.isDigit(name.charAt(i))) return false;
        }
        return true;
    }

    public void switchScene(Stage stage,String url) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        Parent root = fxmlLoader.load();
//        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Quit(ActionEvent actionEvent)
    {
        System.exit(0);
    }

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
        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            try
            {
                switchScene(Main.Stage,"menu.fxml");
            }
            catch (Exception e) {}
        }
    }

    boolean player1_move=false;
    boolean player2_move=true;

    public int move_player(int x,Player p1,Objects[] board)
    {
        // print the coordinates and tile number of p1
        double offset_x=54.75;
        double offset_y=49.75;
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

//                TranslateTransition t = new TranslateTransition(Duration.millis(1000));
//                t.setAutoReverse(false);
                if (p.getX()%2 == 1)
                {
                    //move right only if p.getY()!=9
                    if (p1.getRole().equals("p1"))
                    {
//                        t.setNode(Piece_1);
                        if (p.getY()!=9)
                        {
                            //transition right
                            Piece_1.setX(Piece_1.getX() + offset_x);
                        }
                        else
                        {
                            //transition above
                            Piece_1.setY(Piece_1.getY() - offset_y);
                        }
                    }
                    else
                    {

                        if (p.getY()!=9)
                        {
                            //transition right
                            Piece_2.setX(Piece_2.getX()+offset_x);
                        }
                        else {
                            //transition above
                            Piece_2.setY(Piece_2.getY()-offset_y);
                        }
                    }
                }
                else
                {
                    //move left only if p.getY()!=0
                    //note the tile size of one pixel is around 50
                    if (p1.getRole().equals("p1"))
                    {

                        if (p.getY()!=0)
                        {
                            //transition left
                            Piece_1.setX(Piece_1.getX()-offset_x);
                        }
                        else
                        {
                            //transition above
                            Piece_1.setY(Piece_1.getY()-offset_y);
                        }
                    }
                    else
                    {

                        if (p.getY()!=0)
                        {
                            //transition left
                            Piece_2.setX(Piece_2.getX()-offset_x);
                        }
                        else
                        {
                            //transition above
                            Piece_2.setY(Piece_2.getY()-offset_y);
                        }
                    }
                }
            }

            if (p1.isWinner())
            {
                return 1;
            }
            Object obj = board[p1.getTile()-1];
            if (obj instanceof Ladders){

                p1.setTile( ((Ladders) obj).getEnd() );
                int start_tile=((Ladders) obj).getStart();
                Pair start=Main.map.get(start_tile);
                int end_tile=((Ladders) obj).getEnd();
                Pair end=Main.map.get(end_tile);
                // i have stored in i,j in the opposite order of the conventional x and y coordinates followed in maths so I need to take care of it

                if (p1.getRole().equals("p1")){
                    //move piece 1
                    int diff_x=end.getY()-start.getY();
                    int diff_y=end.getX()-start.getX();
                    Piece_1.setX(Piece_1.getX()+offset_x*diff_x);
                    Piece_1.setY(Piece_1.getY()+offset_y*diff_y);

                }
                else {
                    //do the same but for piece 2
                    int diff_x=end.getY()-start.getY();
                    int diff_y=end.getX()-start.getX();
                    Piece_2.setX(Piece_2.getX()+offset_x*diff_x);
                    Piece_2.setY(Piece_2.getY()+offset_y*diff_y);

                }

                // insert the movement animation depending on the tile value and obj instance
            }
            else if (obj instanceof Snakes)
            {
                p1.setTile( ((Snakes) obj).getEnd() );
                // do everything similar to ladders
                int start_tile=((Snakes) obj).getStart();
                Pair start=Main.map.get(start_tile);
                int end_tile=((Snakes) obj).getEnd();
                Pair end=Main.map.get(end_tile);
                if (p1.getRole().equals("p1")){
                    //move piece 1
                    int diff_x=end.getY()-start.getY();
                    int diff_y=end.getX()-start.getX();
                    Piece_1.setX(Piece_1.getX()+offset_x*diff_x);
                    Piece_1.setY(Piece_1.getY()+offset_y*diff_y);

                }
                else {
                    //do the same but for piece 2
                    int diff_x=end.getY()-start.getY();
                    int diff_y=end.getX()-start.getX();
                    Piece_2.setX(Piece_2.getX()+offset_x*diff_x);
                    Piece_2.setY(Piece_2.getY()+offset_y*diff_y);
                }
            }
             //have assumed  that no ladder will take directly to 100 tile
        }
        else
        {
            if (x==1)
            {
                p1.setStatus(true);
                p1.setTile(1);


                if (p1.getRole().equals("p1"))
                {

                    //move t on y axis by 50 points above
                    Piece_1.setY(Piece_1.getY()-offset_y);
                }
                else
                {
                    //do the same but for piece 2
                    Piece_2.setY(Piece_2.getY()-offset_y);

                }
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
        label_1.setText(Main.p1.getName());
        label_2.setText(Main.p2.getName());
    }

    @FXML
    void Dice_roll(ActionEvent event)
    {
        player1_cord=new double[2];
        player2_cord=new double[2];
        player1_cord[0]= Piece_1.getY();
        player1_cord[1]= Piece_1.getX();
        player2_cord[0]= Piece_2.getY();
        player2_cord[1]= Piece_2.getX();

        Dice dice = new Dice();
        roll = dice.getVal();

        Image img = new Image("file:/D:/Coding/Java_Programs/Snake_and_Ladders/src/Dice_face_"+roll+".png");
        Dice_view_changer.setImage(img);
        //disable the image next to the dice for player
        int winner;
        if (player1_move==false)
        {
            winner=move_player(roll,Main.p1,Main.board);
            player1_move=true;
            player2_move=false;
            if (winner==1)
            {
                // either design an alert pop up or just simply use another scene and switch according to user input
                // note that the same code would get copt pasted into the winner tile for player 2, consider making a function
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Winner : "+ Main.p1.getName()+" Loser : "+Main.p2.getName());
                alert.setTitle("Winner!");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK)
                {
                    try
                    {
                        switchScene(Main.Stage,"menu.fxml");
                    }
                    catch (Exception e) {}
                }
            }
            return;
        }
        //arrow animation here
        if (player2_move==false)
        {
            winner=move_player(roll,Main.p2,Main.board);
            player1_move=false;
            player2_move=true;
            if (winner==1)
            {
                // call some win function here for player 2
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Winner : "+ Main.p2.getName()+" Loser : "+Main.p1.getName());
                alert.setTitle("Winner!");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK)
                {
                    try
                    {
                        switchScene(Main.Stage,"menu.fxml");
                    }
                    catch (Exception e) {}
                }
            }
        }
    }
    @FXML
    public void showBoard(ActionEvent event) throws IOException {
        switchScene(stage,"board.fxml");
    }
    @FXML
    public void New_Game(ActionEvent actionEvent) throws Exception
    {
        // i assume you take the name input and verify it then use setName() function for the player to give them the appropriate name
        System.out.println("test");
        Group group = new Group();
        group.getChildren().add(Choosing_menu);
        group.getChildren().add(Player_1_text_field);
        group.getChildren().add(Player_2_text_field);
        group.getChildren().add(Play2);
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(1));
        transition.setNode(Choosing_menu);
        transition.setToY(Choosing_menu.getY()-500);
        transition.setAutoReverse(false);
        transition.play();
        stage=Main.Stage;
        switchScene(stage,"board.fxml");
    }
}
