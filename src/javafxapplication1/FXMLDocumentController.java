/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import blackjack.model.BlackjackModel;
import blackjack.model.BlackjackRound;
import blackjack.model.Card;
import blackjack.model.Hand;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.Mnemonic;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Liron
 */
@SuppressWarnings("ALL")
public class FXMLDocumentController implements Initializable {
    @FXML private ComboBox<String> comboSuit = new ComboBox<>(); 
    @FXML private ComboBox<String> comboNumber = new ComboBox<>();
    @FXML private TextArea textDealer = new TextArea();
    @FXML private TextArea textPlayer = new TextArea();
    @FXML private TextArea score = new TextArea();
    @FXML private Button hit = new Button();
    @FXML private Button stand = new Button();
    @FXML private Button newGame = new Button();
    @FXML private Label labelPlayer = new Label();
    @FXML private Label labelDealer = new Label();
    @FXML private TextArea scoreRound = new TextArea();
    
    private BlackjackModel _blackjackModel;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize your logic here: all @FXML variables will have been injected
        assert comboSuit != null : "fx:id=\"comboSuit\" was not injected";
        comboSuit.getItems().clear();
        comboSuit.getItems().addAll("Heart","Diamond","Spade","Club");
        
        assert comboNumber != null : "fx:id=\"comboNumber\" was not injected";
        comboNumber.getItems().clear();
        comboNumber.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13");
        
        assert textDealer != null : "fx:id=\"textDealer\" was not injected";
        textDealer.clear();
        
        assert textPlayer != null : "fx:id=\"textPlayer\" was not injected";
        textPlayer.clear();
        
        assert score != null : "fx:id=\"score\" was not injected";
        score.clear();
    
        assert hit != null : "fx:id=\"hit\" was not injected";
        assert stand != null : "fx:id=\"stand\" was not injected";
        assert labelPlayer != null : "fx:id=\"labelPlayer\" was not injected";
        assert labelDealer != null : "fx:id=\"labelDealer\" was not injected";

        _blackjackModel = new BlackjackModel();
        score.setWrapText(true);
        /*I tried to install accelerators... and it failed miserably
        try {
        hit.getScene().
                getAccelerators().
                put(new KeyCodeCombination(KeyCode.H, KeyCombination.SHORTCUT_DOWN), (Runnable) () -> {
            hit.fire();
        });
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        hit.getStyleClass().add("hit_button");
        Image paw = new Image(getClass().getResourceAsStream("small_paw.gif"));
        hit.setGraphic(new ImageView(paw));
        stand.getStyleClass().add("stand_button");

        newRound();
    }

    /**
     * The action preformed when the "Hit" button is pressed
     * @param event
     */
    @FXML
    private void hitButtonAction(ActionEvent event) {
        BlackjackRound round = _blackjackModel.currentRound();
        Boolean b = round.hit();
        textPlayer.setText(Card.cardsToString(round.playerHand().visibleCards()));
        //textPlayer.appendText("\ntotal: " + round.playerHand().value() + "\n");
        // 666
        Hand playerHand = round.playerHand();
        labelPlayer.setText(Integer.toString(round.playerHand().value()));

        if (playerHand.value() >= 21) {
            System.out.println(Card.cardsToString(round.dealerHand().allCards()));
            //textDealer.setText(Card.cardsToString(round.dealerHand().allCards()));
            endRound();
        }
        
        System.out.println("end hit" + playerHand.value());
    }

    /**
     * The action preformed when the "Stand" button is pressed
     * @param event
     */
    @FXML
    private void standButtonAction(ActionEvent event) {
        BlackjackRound round = _blackjackModel.currentRound();
        round.stand();
        Hand dealerHand = round.dealerHand();
        textDealer.setText(Card.cardsToString(dealerHand.visibleCards()));
        labelDealer.setText(Integer.toString(round.playerHand().value()));

        endRound();
    }
    @FXML
    /**
     * for the QA- draws a specific card from the deck
     * TODO: the thing.
     * (make this actually work)
     */
    private void drawCardButtonAction(ActionEvent event) {
        //System.out.println("Insert draw card method here");
        BlackjackRound round = _blackjackModel.newRound();

        //if a card is already used:
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Text text = new Text("Card already used: "+ comboSuit.getValue() +" "+ comboNumber.getValue());
        dialogStage.setScene(
                new Scene(VBoxBuilder.create()
                        .children(text)
                        .alignment(Pos.CENTER)
                        .padding(new Insets(5))
                        .build()));
        dialogStage.show();
    }

    /**
     * The action preformed when the "New Game" button is pressed: starts a new game
     * @param event
     */
    @FXML
    private void newGameButtonAction(ActionEvent event) {
        newRound();
    }
    
    private void newRound() {
        
        BlackjackRound round = _blackjackModel.newRound();
        textDealer.clear();
        textPlayer.clear();
        score.setText(Integer.toString(_blackjackModel.totalScore()));
        scoreRound.setText("0");
        hit.setDisable(false);
        stand.setDisable(false);
        newGame.setDisable(true);
        int dealerStart = round.dealerHand().visibleCards().get(0).number();
        labelDealer.setText(Integer.toString(dealerStart));
        labelPlayer.setText(Integer.toString(round.playerHand().value()));
        
        textPlayer.setText(Card.cardsToString(round.playerHand().visibleCards()));
        textDealer.setText(Card.cardsToString(round.dealerHand().visibleCards()));
        if (round.playerHand().value() == 21) {
           endRound();
        }
    }
    
    // 
    private void endRound() {
        
        // Reveal the dealer's hidden card.
        Hand dealerHand = _blackjackModel.currentRound().dealerHand();
        textDealer.setText(Card.cardsToString(dealerHand.allCards()));
        scoreRound.setText("This Round: " + Integer.toString(_blackjackModel.currentRound().score()));
        score.setText(Integer.toString(_blackjackModel.endRound()));
        BlackjackRound round = _blackjackModel.currentRound();
        labelDealer.setText(Integer.toString(round.dealerHand().value()));
        labelDealer.setText(Integer.toString(round.dealerHand().value()));
        
        // Figure out some message for the user to explain what happend.
        Boolean won = _blackjackModel.currentRound().isWon();

        String message;
        Image result;
        if (won == null) {
            // TODO: raise an exception or something
            System.err.println("WHAT THE FUCK DID I DO WRONG");
            return;
        }
        else if (won) {
            result = new Image(getClass().getResourceAsStream("grumpy-cat-ariel.jpg"));
            message = "You Won!";
        }
        else {
            result = new Image(getClass().getResourceAsStream("catasstrophy.jpg"));
            message = "You lost";
        }
        
        // Tell the player whether it lost or not.
        Stage dialogStage = new Stage(StageStyle.TRANSPARENT);
        Group rootGroup = new Group();
        Scene scene = new Scene(rootGroup, 200, 200, Color.TRANSPARENT);
        dialogStage.setScene(scene);
        dialogStage.centerOnScreen();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        Circle dragger = new Circle(100, 100, 100);

        dragger.setFill(new ImagePattern(result, 0, 0, 1, 1, true));

        Text text = new Text(message); //20, 110,
        text.setFill(Color.WHITE);
        //text.setEffect(new Lighting());
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 30));

        // USE A LAYOUT VBOX FOR EASIER POSITIONING OF THE VISUAL NODES ON SCENE
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(90, 60, 60, 40));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(text);
        rootGroup.getChildren().addAll(dragger, vBox);
        dialogStage.show();

        // Handle button Disable-ness-ity.
        hit.setDisable(true);
        stand.setDisable(true);
        newGame.setDisable(false);
        dialogStage.setAlwaysOnTop(true);

        // Either a mouse click on the dialog or pressing any key closes it
        dialogStage.getScene().setOnMouseClicked(new EventHandler<MouseEvent> () {
            @Override
            public void handle(MouseEvent event) {
                dialogStage.close();
                //newGame.fire();
            }
        });
        
        dialogStage.getScene().setOnKeyPressed((KeyEvent ke) -> {
                dialogStage.close();
                //newGame.fire();
        });

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> dialogStage.close()));
        timeline.play();

    }
}
