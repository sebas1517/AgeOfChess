import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class ChessApplication extends Application {

    private static final int BOARD_SIZE = 8;
    private static final int CELL_SIZE = 80;
    public static HashMap<String, Scene> sceneMap;
    ListView<String> listItems, listItems2;
    Button serverChoice, clientChoice;
    VBox buttonBox;
    VBox startPane;
    Scene startSceneChoice;
    Server serverConnection;
    static Client clientConnection;
    TextField usernameField;
    PasswordField passwordField;
    Button loginButton;
    static String Username;
    Text usernameText;
    int user_wins;
    int user_games_played;
    static Text user_wins_text;
    static Text user_games_played_text;








    public static Scene createChessboard2() throws IOException {
        Parent board = FXMLLoader.load(ChessApplication.class.getResource("chess/board.fxml"));
        return new Scene(board, 580, 580);
    }


    public Scene startScreen(){

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Add a big title label to the grid pane
        Label titleLabel = new Label("Age of Chess");
        titleLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(titleLabel, 0, 0, 2, 1);

        // Add a username label and text field to the grid pane
        Label usernameLabel = new Label("Username:");
        usernameField = new TextField();
        grid.add(usernameLabel, 0, 1);
        grid.add(usernameField, 1, 1);

        // Add a password label and password field to the grid pane
        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);

        // Add a login button to the grid pane
        loginButton = new Button("Log in");
        HBox hbLoginButton = new HBox(10);
        hbLoginButton.setAlignment(Pos.BOTTOM_RIGHT);
        hbLoginButton.getChildren().add(loginButton);
        grid.add(hbLoginButton, 1, 4);



        // Create a scene and add the grid pane to it
        Scene scene = new Scene(grid, 300, 275);

        return scene;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Main menu");

        this.serverChoice = new Button("Create Game");
        this.serverChoice.setStyle("-fx-font-family: \"sans-serif\"");
        this.serverChoice.setStyle("-fx-pref-width: 300px");
        this.serverChoice.setStyle("-fx-pref-height: 300px");


        this.serverChoice.setOnAction(e->{ primaryStage.setScene(sceneMap.get("server"));
            primaryStage.setTitle("This is the Server");
            serverConnection = new Server(data -> {
                Platform.runLater(()->{
                    listItems.getItems().add(data.toString());
                });

            });

        });



        this.clientChoice = new Button("Play Game");
        this.clientChoice.setStyle("-fx-pref-width: 300px");
        this.clientChoice.setStyle("-fx-pref-height: 300px");


        this.clientChoice.setOnAction(e-> {
            try {
                sceneMap.put("client",  createChessboard2());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            //clientConnection = new Client(data->{
                //Platform.runLater(()->{System.out.println(data.toString());
                    //boardController.updateMoveOnClient(data.toString());
                //});
            //});

            //creates the client connection / thread
            //clientConnection.start();
            primaryStage.setScene(sceneMap.get("client"));
            primaryStage.setTitle("This is a client");
        });


        //this.buttonBox = new VBox(serverChoice, clientChoice);
        //startPane = new BorderPane();

        usernameText = new Text("Username: " + Username);

        user_wins_text = new Text("Games Won: " + user_wins);
        user_games_played_text = new Text("Games Played: " + user_games_played);



        startPane = new VBox(usernameText, user_wins_text, user_games_played_text, serverChoice, clientChoice);
        //startPane.setPadding(new Insets(70));
        //startPane.setCenter(buttonBox);

        startSceneChoice = new Scene(startPane, 300,275);
        listItems = new ListView<String>();
        listItems2 = new ListView<String>();
        sceneMap = new HashMap<String, Scene>();
        sceneMap.put("server",  createServerGui());
        //sceneMap.put("client",  createChessboard2());
        sceneMap.put("server/client", startSceneChoice);
        sceneMap.put("start", startScreen());
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        loginButton.setOnAction(e->{
            Username = usernameField.getText();
            boolean usernameCheck = DatabaseOperations.usernameExists(usernameField.getText());
            if(usernameCheck){
                boolean checkPassword = DatabaseOperations.authenticate(usernameField.getText(), passwordField.getText());
                if(checkPassword){
                    primaryStage.setScene(sceneMap.get("server/client"));
                }
            }
            else{
                DatabaseOperations.addUser(usernameField.getText(), passwordField.getText());
                primaryStage.setScene(sceneMap.get("server/client"));
            }
            try {
                user_wins = DatabaseOperations.getWinsForUser(Username);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                user_games_played = DatabaseOperations.getGamesForUser(Username);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            usernameText.setText(Username);
            user_wins_text.setText("Games Won: " + user_wins);
            user_games_played_text.setText("Games Played: " + user_games_played);

        });




        primaryStage.setScene(sceneMap.get("start"));
        primaryStage.show();

    }

    public Scene createServerGui() {

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(70));
        pane.setStyle("-fx-background-color: coral");
        pane.setStyle("-fx-font-family: \"sans-serif\"");

        pane.setCenter(listItems);

        return new Scene(pane, 500, 400);


    }


    public static void main(String[] args) {
        launch();
    }

    public static Client getClient(){
        return clientConnection;
    }

    public static HashMap<String, Scene> getSceneMap(){
        return sceneMap;
    }

}
