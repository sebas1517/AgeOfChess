import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.ResourceBundle;


public class boardController {
    @FXML
    Button white_rook_1;
    @FXML
    Button white_pawn_1;
    @FXML
    Button white_knight_1;
    @FXML
    Button white_pawn_2;
    @FXML
    Button white_bishop_1;
    @FXML
    Button white_queen;
    @FXML
    Button white_pawn_3;
    @FXML
    Button white_pawn_4;
    @FXML
    Button white_pawn_5;
    @FXML
    Button white_monk;
    @FXML
    Button white_king;
    @FXML
    Button white_pawn_6;
    @FXML
    Button white_bishop_2;
    @FXML
    Button white_pawn_7;
    @FXML
    Button white_knight_2;
    @FXML
    Button white_pawn_8;
    @FXML
    Button white_rook_2;
    @FXML
    Button white_pawn_9;

    @FXML
    Button black_rook_1;
    @FXML
    Button black_pawn_1;
    @FXML
    Button black_knight_1;
    @FXML
    Button black_pawn_2;
    @FXML
    Button black_bishop_1;
    @FXML
    Button black_queen;
    @FXML
    Button black_pawn_3;
    @FXML
    Button black_pawn_4;
    @FXML
    Button black_pawn_5;
    @FXML
    Button black_monk;
    @FXML
    Button black_king;
    @FXML
    Button black_pawn_6;
    @FXML
    Button black_bishop_2;
    @FXML
    Button black_pawn_7;
    @FXML
    Button black_knight_2;
    @FXML
    Button black_pawn_8;
    @FXML
    Button black_rook_2;
    @FXML
    Button black_pawn_9;

    @FXML
    Button blue_rook_1;
    @FXML
    Button blue_pawn_1;
    @FXML
    Button blue_knight_1;
    @FXML
    Button blue_pawn_2;
    @FXML
    Button blue_bishop_1;
    @FXML
    Button blue_queen;
    @FXML
    Button blue_pawn_3;
    @FXML
    Button blue_pawn_4;
    @FXML
    Button blue_pawn_5;
    @FXML
    Button blue_monk;
    @FXML
    Button blue_king;
    @FXML
    Button blue_pawn_6;
    @FXML
    Button blue_bishop_2;
    @FXML
    Button blue_pawn_7;
    @FXML
    Button blue_knight_2;
    @FXML
    Button blue_pawn_8;
    @FXML
    Button blue_rook_2;
    @FXML
    Button blue_pawn_9;

    @FXML

    Button red_rook_1;
    @FXML
    Button red_pawn_1;
    @FXML
    Button red_knight_1;
    @FXML
    Button red_pawn_2;
    @FXML
    Button red_bishop_1;
    @FXML
    Button red_queen;
    @FXML
    Button red_pawn_3;
    @FXML
    Button red_pawn_4;
    @FXML
    Button red_pawn_5;
    @FXML
    Button red_monk;
    @FXML
    Button red_king;
    @FXML
    Button red_pawn_6;
    @FXML
    Button red_bishop_2;
    @FXML
    Button red_pawn_7;
    @FXML
    Button red_knight_2;
    @FXML
    Button red_pawn_8;
    @FXML
    Button red_rook_2;
    @FXML
    Button red_pawn_9;

    @FXML
    GridPane board_grid;
    ArrayList<String> turns = new ArrayList<String>();
    int currentTurn = 0;
    boolean whiteKing = true;
    boolean blueKing = true;
    boolean blackKing = true;
    boolean redKing = true;
    Button pieceBeingMoved;
    boolean movingPiece = false;

    Client clientConnection;

    boolean madeFirstMove = false;
    String myColor = "";

    @FXML
    public void initialize() {
        System.out.println("Yo does this shit work");
        turns.add("White");
        turns.add("Blue");
        turns.add("Black");
        turns.add("Red");
        clientConnection = new Client(data->{
                Platform.runLater(()->{System.out.println(data.toString());
                updateMoveOnClient(data.toString());
            });
        });
        clientConnection.start();
    }
    public void setDisableWhite(boolean b) {
        white_rook_1.setDisable(b);
        white_rook_2.setDisable(b);
        white_knight_1.setDisable(b);
        white_knight_2.setDisable(b);
        white_bishop_1.setDisable(b);
        white_bishop_2.setDisable(b);
        white_queen.setDisable(b);
        white_king.setDisable(b);
        white_monk.setDisable(b);
        white_pawn_1.setDisable(b);
        white_pawn_2.setDisable(b);
        white_pawn_3.setDisable(b);
        white_pawn_4.setDisable(b);
        white_pawn_5.setDisable(b);
        white_pawn_6.setDisable(b);
        white_pawn_7.setDisable(b);
        white_pawn_8.setDisable(b);
        white_pawn_9.setDisable(b);
    }

    public void setDisableBlack(boolean b) {
        black_rook_1.setDisable(b);
        black_rook_2.setDisable(b);
        black_knight_1.setDisable(b);
        black_knight_2.setDisable(b);
        black_bishop_1.setDisable(b);
        black_bishop_2.setDisable(b);
        black_queen.setDisable(b);
        black_king.setDisable(b);
        black_monk.setDisable(b);
        black_pawn_1.setDisable(b);
        black_pawn_2.setDisable(b);
        black_pawn_3.setDisable(b);
        black_pawn_4.setDisable(b);
        black_pawn_5.setDisable(b);
        black_pawn_6.setDisable(b);
        black_pawn_7.setDisable(b);
        black_pawn_8.setDisable(b);
        black_pawn_9.setDisable(b);
    }

    public void setDisableRed(boolean b) {
        red_rook_1.setDisable(b);
        red_rook_2.setDisable(b);
        red_knight_1.setDisable(b);
        red_knight_2.setDisable(b);
        red_bishop_1.setDisable(b);
        red_bishop_2.setDisable(b);
        red_queen.setDisable(b);
        red_king.setDisable(b);
        red_monk.setDisable(b);
        red_pawn_1.setDisable(b);
        red_pawn_2.setDisable(b);
        red_pawn_3.setDisable(b);
        red_pawn_4.setDisable(b);
        red_pawn_5.setDisable(b);
        red_pawn_6.setDisable(b);
        red_pawn_7.setDisable(b);
        red_pawn_8.setDisable(b);
        red_pawn_9.setDisable(b);
    }

    public void setDisableBlue(boolean b) {
        blue_rook_1.setDisable(b);
        blue_rook_2.setDisable(b);
        blue_knight_1.setDisable(b);
        blue_knight_2.setDisable(b);
        blue_bishop_1.setDisable(b);
        blue_bishop_2.setDisable(b);
        blue_queen.setDisable(b);
        blue_king.setDisable(b);
        blue_monk.setDisable(b);
        blue_pawn_1.setDisable(b);
        blue_pawn_2.setDisable(b);
        blue_pawn_3.setDisable(b);
        blue_pawn_4.setDisable(b);
        blue_pawn_5.setDisable(b);
        blue_pawn_6.setDisable(b);
        blue_pawn_7.setDisable(b);
        blue_pawn_8.setDisable(b);
        blue_pawn_9.setDisable(b);
    }

    // handler for when a blank piece is clicked
    public void blankPieceClicked(ActionEvent e) {
        System.out.println("This happens first");
        if (movingPiece) {
            Button b = (Button) e.getSource();
            int bRow = board_grid.getRowIndex(b);
            int bCol = board_grid.getColumnIndex(b);
            int movPieceRow = board_grid.getRowIndex(pieceBeingMoved);
            int movPieceCol = board_grid.getColumnIndex(pieceBeingMoved);

            String message = bRow + "/" + bCol + "/" + movPieceRow + "/" + movPieceCol + "/" + "no";
            MessageData m = new MessageData(message, "");

            // Moves the piece to the selected spot
            board_grid.setConstraints(pieceBeingMoved, bCol, bRow);
            board_grid.setConstraints(b, 0, 0);
            b.setVisible(false);

            b = null;
            if(!madeFirstMove){
//                myColor = turn;
                myColor = turns.get(currentTurn);
                madeFirstMove = true;
            }
//            turn = changeTurn(turn);
            /*
            if(currentTurn == turns.size() - 1){
                currentTurn = 0;
            }
            else{
                currentTurn += 1;
            }

             */
            currentTurn = (currentTurn + 1) % turns.size();
            changeTurn(turns.get(currentTurn));
            // new blank button to replace the piece that is moved
            Button defButton = new Button();
            defButton.setPrefHeight(51);
            defButton.setPrefWidth(61);
            defButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    blankPieceClicked(event);
                }
            });
            board_grid.add(defButton, movPieceCol, movPieceRow);
            pieceBeingMoved.toFront();
            movingPiece = false;
            clientConnection.send(m);
        }
    }

    public void changeTurn(String turn) {
        setDisableBlack(true);
        setDisableBlue(true);
        setDisableRed(true);
        setDisableWhite(true);
        if (turn.equals("White")) {
            if(!madeFirstMove){
                setDisableWhite(false);
            }
            if(myColor.equals(turn)){
                setDisableWhite(false);
            }
        } else if (turn.equals("Blue")) {
            if(!madeFirstMove){
                setDisableBlue(false);
            }
            if(myColor.equals(turn)){
                setDisableBlue(false);
            }
        } else if (turn.equals("Black")) {
            if(!madeFirstMove){
                setDisableBlack(false);
            }
            if(myColor.equals(turn)){
                setDisableBlack(false);
            }
        } else if(turn.equals("Red")){
            if(!madeFirstMove){
                setDisableRed(false);
            }
            if(myColor.equals(turn)){
                setDisableRed(false);
            }
        }
    }

    // handler for when a playable piece is clicked
    public void pieceClicked(ActionEvent e) throws SQLException {
        System.out.println("This is what happens actually");
        // If a piece is already being moved, then the piece being moved is replaced with a blank button
        System.out.println(movingPiece);
        Button b = (Button) e.getSource();
        if (movingPiece) {
            int bRow = board_grid.getRowIndex(b);
            int bCol = board_grid.getColumnIndex(b);
            int movPieceRow = board_grid.getRowIndex(pieceBeingMoved);
            int movPieceCol = board_grid.getColumnIndex(pieceBeingMoved);
            // If the piece being moved is clicked again, then the piece is not moved
            if (bRow == movPieceRow && bCol == movPieceCol) {
                return;
            }

            String message = bRow + "/" + bCol + "/" + movPieceRow + "/" + movPieceCol + "/" + "yes";
            MessageData m = new MessageData(message, "");
            //clientConnection.send(m);
            // New default blank button to replace moved pieces
            board_grid.setConstraints(pieceBeingMoved, bCol, bRow);
            board_grid.setConstraints(b, 0, 0);
            if (b.getId().contains("King")) {
                String pieceId = b.getId();
                if (pieceId.equals("blackKing")) {
                    blackKing = false;
                    turns.remove("Black");
                    turns.trimToSize();
                } else if (pieceId.equals("whiteKing")) {
                    whiteKing = false;
                    turns.remove("White");
                    turns.trimToSize();
                } else if (pieceId.equals("blueKing")) {
                    blueKing = false;
                    turns.remove("Blue");
                    turns.trimToSize();
                } else {
                    redKing = false;
                    turns.remove("Red");
                    turns.trimToSize();
                }
                if (turns.size() == 1) {
                    // This client is the winner
                    DatabaseOperations.updateGamesPlayed(ChessApplication.Username);
                    DatabaseOperations.updateWins(ChessApplication.Username);

                    ChessApplication.user_wins_text.setText("Games Won: " + DatabaseOperations.getWinsForUser(ChessApplication.Username));
                    ChessApplication.user_games_played_text.setText("Games Played: " + DatabaseOperations.getGamesForUser(ChessApplication.Username));


                    Popup popup = new Popup();
                    Text text = new Text(turns.get(0) + " won the game");
                    VBox content = new VBox(text);
                    //content.setAlignment(Pos.CENTER);
                    content.setStyle("-fx-background-color: white; -fx-padding: 10;");

                    popup.getContent().add(content);

                    Scene scene = board_grid.getScene();

                    double centerX = scene.getWidth() / 2;
                    double centerY = scene.getHeight() / 2;

                    Point2D centerPoint = new Point2D(centerX, centerY);
                    Point2D screenPoint = board_grid.localToScreen(centerPoint);

                    double screenCenterX = screenPoint.getX();
                    double screenCenterY = screenPoint.getY();

                    popup.show(board_grid.getScene().getWindow(), screenCenterX, screenCenterY);

                    System.out.println("I won the game");

                    PauseTransition pause = new PauseTransition(Duration.seconds(5));
                    pause.setOnFinished(event->{
                        Stage stage = (Stage) board_grid.getScene().getWindow();
                        stage.setScene(ChessApplication.getSceneMap().get("server/client"));
                        popup.hide();

                    });
                    pause.play();


                }
            }
            b.setVisible(false);
            b = null;

            currentTurn = (currentTurn + 1) % turns.size();
            changeTurn(turns.get(currentTurn));
            // New default blank button to replace moved pieces
            Button defButton = new Button();
            defButton.setPrefHeight(51);
            defButton.setPrefWidth(61);
            defButton.setOnAction(new EventHandler<ActionEvent>() {
                // This method is called when a piece is clicked
                @Override
                public void handle(ActionEvent event) {
                    // If a piece is already being moved, then the piece being moved is replaced with a blank button
                    blankPieceClicked(event);
                }
            });
            // end of the blank default button

            board_grid.add(defButton, movPieceCol, movPieceRow);
            //board_grid.getChildren().remove(b);
            pieceBeingMoved.toFront();
            movingPiece = false;
            clientConnection.send(m);
            return;
        } else {
            // White >> Blue >> Black >> Red
            String turn = turns.get(currentTurn);
            if (turn.equals("White")) {
                setDisableBlack(false);
                setDisableBlue(false);
                setDisableRed(false);
                setDisableWhite(true);
            } else if (turn.equals("Black")) {
                setDisableWhite(false);
                setDisableBlue(false);
                setDisableRed(false);
                setDisableBlack(true);
            } else if (turn.equals("Blue")) {
                setDisableWhite(false);
                setDisableBlack(false);
                setDisableRed(false);
                setDisableBlue(true);
            } else if (turn.equals("Red")){
                setDisableBlack(false);
                setDisableBlue(false);
                setDisableWhite(false);
                setDisableRed(true);
            }
            pieceBeingMoved = b;
            movingPiece = true;
            return;
        }
    }


    public void updateMoveOnClient(String data){
        //parse data from client
        String[] parts = data.split("/");

        for(int i = 0; i < parts.length;i++){
            System.out.println(parts[i]);
        }



        Integer eNodeRow = Integer.parseInt(parts[0]);
        Integer eNodeCol = Integer.parseInt(parts[1]);

        Integer sNodeRow = Integer.parseInt(parts[2]);
        Integer sNodeCol = Integer.parseInt(parts[3]);


        Button endButton = null;
        for (Node node : board_grid.getChildren()) {
            if (GridPane.getRowIndex(node) == eNodeRow && GridPane.getColumnIndex(node) == eNodeCol) {
                endButton = (Button) node;
                break;
            }
        }
        GridPane.setConstraints(endButton, 0, 0);
        try {
            if (endButton.getId().contains("King")) {
                String pieceId = endButton.getId();
                if (pieceId.equals("blackKing")) {
                    blackKing = false;
                    turns.remove("Black");
                    turns.trimToSize();
                } else if (pieceId.equals("whiteKing")) {
                    whiteKing = false;
                    turns.remove("White");
                    turns.trimToSize();
                } else if (pieceId.equals("blueKing")) {
                    blueKing = false;
                    turns.remove("Blue");
                    turns.trimToSize();
                } else {
                    redKing = false;
                    turns.remove("Red");
                    turns.trimToSize();
                }
                if (turns.size() == 1) {
                    // Another client won
                    DatabaseOperations.updateGamesPlayed(ChessApplication.Username);
                    ChessApplication.user_games_played_text.setText("Games Played: " + DatabaseOperations.getGamesForUser(ChessApplication.Username));

                    Popup popup = new Popup();
                    Text text = new Text(turns.get(0) + " won the game");
                    VBox content = new VBox(text);
                    //content.setAlignment(Pos.CENTER);
                    content.setStyle("-fx-background-color: white; -fx-padding: 10;");

                    popup.getContent().add(content);

                    Scene scene = board_grid.getScene();

                    double centerX = scene.getWidth() / 2;
                    double centerY = scene.getHeight() / 2;

                    Point2D centerPoint = new Point2D(centerX, centerY);
                    Point2D screenPoint = board_grid.localToScreen(centerPoint);

                    double screenCenterX = screenPoint.getX();
                    double screenCenterY = screenPoint.getY();

                    popup.show(board_grid.getScene().getWindow(), screenCenterX, screenCenterY);

                    PauseTransition pause = new PauseTransition(Duration.seconds(5));
                    pause.setOnFinished(event->{
                        Stage stage = (Stage) board_grid.getScene().getWindow();
                        stage.setScene(ChessApplication.getSceneMap().get("server/client"));
                        popup.hide();

                    });
                    pause.play();

                }
            }
        } catch (NullPointerException e) {
            //ignore
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        endButton.setVisible(false);
        endButton = null;



        System.out.println(eNodeRow+ " " + eNodeCol + " " + sNodeRow + " " + sNodeCol);


        Button startButton = null;

        for (Node node : board_grid.getChildren()) {
            if (GridPane.getRowIndex(node) == sNodeRow && GridPane.getColumnIndex(node) == sNodeCol) {
                startButton = (Button) node;
                break;
            }
        }

        //Node startNode = board_grid.getChildren().stream().filter(n-> GridPane.getRowIndex(n) == sNodeRow && GridPane.getColumnIndex(n) == sNodeCol).findFirst().orElse(null);
        //Button startButton = (Button) startNode;
        //if(startNode != null){
            //System.out.println("Wow this shit working fr");
        //}


        //Node endNode = board_grid.getChildren().stream().filter(n-> GridPane.getRowIndex(n) == eNodeRow && GridPane.getColumnIndex(n) == eNodeCol).findFirst().orElse(null);
        //Button endButton = (Button) endNode;

        //if(endNode != null){
            //System.out.println("Man this shit fr working");
        //}

        board_grid.setConstraints(startButton, eNodeCol, eNodeRow);
        currentTurn = (currentTurn + 1) % turns.size();
        changeTurn(turns.get(currentTurn));

        Button defButton = new Button();
        defButton.setPrefHeight(51);
        defButton.setPrefWidth(61);
        defButton.setOnAction(new EventHandler<ActionEvent>() {
            // This method is called when a piece is clicked
            @Override
            public void handle(ActionEvent event) {
                // If a piece is already being moved, then the piece being moved is replaced with a blank button
                blankPieceClicked(event);
            }
        });
        board_grid.add(defButton, sNodeCol, sNodeRow);
//        if(parts[4].equals("yes")){
//            System.out.println("does this even happen");
//            Button endButton = null;
//
//            for (Node node : board_grid.getChildren()) {
//                if (GridPane.getRowIndex(node) == eNodeRow && GridPane.getColumnIndex(node) == eNodeCol) {
//                    endButton = (Button) node;
//                    break;
//                }
//            }
//            endButton.setVisible(false);
//            //board_grid.getChildren().remove(endButton);
//        }
        //endButton.setDisable(true);
        //board_grid.getChildren().remove(endButton);
        startButton.toFront();
        //movingPiece = false;


    }



}

