/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import blackjack.model.*;

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
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.Mnemonic;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
@SuppressWarnings("all")
public class FXMLDocumentController implements Initializable {
    @FXML private ComboBox<String> comboSuit = new ComboBox<>(); 
    @FXML private ComboBox<String> comboNumber = new ComboBox<>();
    @FXML private TextArea textDealer = new TextArea();
    @FXML private TextArea textPlayer = new TextArea();
    @FXML private TextArea score = new TextArea();
    @FXML private Button hit = new Button();
    @FXML private Button stand = new Button();
    @FXML private Button newGame = new Button();
    @FXML private Label labelPlayer = new Label(); // the player's score
    @FXML private Label labelDealer = new Label(); // the dealer's score
    @FXML private TextArea scoreRound = new TextArea();
    @FXML private HBox playerHBox = new HBox();
    @FXML private HBox dealerHBox = new HBox();
    @FXML private Label playerTag = new Label();
    @FXML private Label dealerTag = new Label();
    @FXML private Label gameScoreLabel = new Label();
    @FXML private Button Meow = new Button();

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
        assert dealerHBox != null : "fx:id=\"dealerHBox\" was not injected";
        assert playerHBox != null : "fx:id=\"playerHBox\" was not injected";
        _blackjackModel = new BlackjackModel();
        score.setWrapText(true);

        // Set styles to items- labels, buttons and Hboxes
        hit.getStyleClass().add("hit_button");
        Image paw = new Image(getClass().getResourceAsStream("small_paw.gif"));
        hit.setGraphic(new ImageView(paw));
        stand.getStyleClass().add("stand_button");
        newGame.getStyleClass().add("newRound_button");
        playerTag.getStyleClass().add("playerTag");
        dealerTag.getStyleClass().add("playerTag");
        labelDealer.getStyleClass().add("scoreTag");
        labelPlayer.getStyleClass().add("scoreTag");
        score.getStyleClass().add("gameScoreDisplay");
        scoreRound.getStyleClass().add("roundScoreDisplay");
        gameScoreLabel.getStyleClass().add("gameScoreLabel");
        Meow.getStyleClass().add("meowButton");
        playerHBox.setSpacing(-50);
        dealerHBox.setSpacing(-50);

        // Starts the game
        newRound();
    }

    /**
     * Adds a new card to the pane
     * @param pane
     * @param suit
     * @param number
     */
    private void drawNewCard(HBox box, Suit suit, int number) {
        _blackjackModel.currentRound();
        String imageFile;
        Image newCard;

        imageFile = "Cards/";
        switch (suit) {
            case SPADES: imageFile += "Spades";
                break;
            case HEARTS: imageFile += "Hearts";
                break;
            case DIAMONDS: imageFile += "Diamonds";
                break;
            case CLUBS: imageFile += "Clubs";
                break;
        }
        imageFile += Integer.toString(number)+".png";
        /*if (number < 11) {
            imageFile += ".png";
        }
        else {
            imageFile += ".jpg";
        }*/
        try {
            newCard = new Image(getClass().getResourceAsStream(imageFile));
            box.getChildren().add(new ImageView(newCard));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    /**
     * Make a meow sound when pressing the "Meow" button.
     * Surprised?
     */
    private void meowButtonAction (ActionEvent event) {
        try {
            Media meowSound = new Media(getClass().getResource("Meow.mp3").toString());
            if (meowSound != null) {
                MediaPlayer mp = new MediaPlayer(meowSound);
                mp.play();
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    /**
     * The action performed when the "Hit" button is pressed
     * @param event
     */
    @FXML
    private void hitButtonAction(ActionEvent event) {
        BlackjackRound round = _blackjackModel.currentRound();
        Boolean b = round.hit();
        String imageFile;

        Image newCard;
        playerHBox.getChildren().clear();
        for (int i = 0; i < round.playerHand().visibleCards().size(); i++) {
            int number = round.playerHand().visibleCards().get(i).number();
            Suit suit = round.playerHand().visibleCards().get(i).suit();
            drawNewCard(playerHBox, suit, number);
        }


        textPlayer.setText(Card.cardsToString(round.playerHand().visibleCards())); //visibleCards: return ArrayList<Cars>
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
     * The action performed when the "Stand" button is pressed
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
     * UNUSED - IRRELEVANT
     * for the QA- draws a specific card from the deck
     * (make this actually work)
     */
    private void drawCardButtonAction(ActionEvent event) {
        //System.out.println("Insert draw card method here");
        _blackjackModel.newRound();

        // If a card is already used:
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Text text = new Text("Card already used: " + comboSuit.getValue() + " " + comboNumber.getValue());
        VBox vBox = new VBox();
        vBox.getChildren().add(text);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(5));
        dialogStage.setScene(new Scene(vBox));
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

    /**
     * Starts a new round by dealing new cards and updating the relevant fields
     */
    private void newRound() {
        BlackjackRound round = _blackjackModel.newRound();
        textDealer.clear();
        textPlayer.clear();
        playerHBox.getChildren().clear();
        dealerHBox.getChildren().clear();
        score.setText(Integer.toString(_blackjackModel.totalScore()));
        scoreRound.setText("0");
        hit.setDisable(false);
        stand.setDisable(false);
        newGame.setDisable(true);
        int dealerStart = round.dealerHand().visibleCards().get(0).number();
        labelDealer.setText(Integer.toString(dealerStart));
        labelPlayer.setText(Integer.toString(round.playerHand().value()));
        
        textPlayer.setText(Card.cardsToString(round.playerHand().visibleCards()));
        for (int i = 0; i < round.playerHand().visibleCards().size(); i++) {
            int number = round.playerHand().visibleCards().get(i).number();
            Suit suit = round.playerHand().visibleCards().get(i).suit();
            drawNewCard(playerHBox, suit, number);
        }
        textDealer.setText(Card.cardsToString(round.dealerHand().visibleCards()));
        dealerHBox.getChildren().add(new ImageView(new Image(getClass().getResourceAsStream("Cards/cover.png"))));
        for (int i = 0; i < round.dealerHand().visibleCards().size(); i++) {
            int number = round.dealerHand().visibleCards().get(i).number();
            Suit suit = round.dealerHand().visibleCards().get(i).suit();
            drawNewCard(dealerHBox, suit, number);
        }
        if (round.playerHand().value() == 21) {
           endRound();
        }
    }

    /**
     * Ends the round- displays "you win!"/"you lost" messege and updates the game score
     */
    private void endRound() {
        
        // Reveal the dealer's hidden card.
        Hand dealerHand = _blackjackModel.currentRound().dealerHand();
        textDealer.setText(Card.cardsToString(dealerHand.allCards()));
        scoreRound.setText("This Round: " + Integer.toString(_blackjackModel.currentRound().score()));
        score.setText(Integer.toString(_blackjackModel.endRound()));
        BlackjackRound round = _blackjackModel.currentRound();
        labelDealer.setText(Integer.toString(round.dealerHand().value()));
        labelDealer.setText(Integer.toString(round.dealerHand().value()));
        dealerHBox.getChildren().clear();
        for (int i = 0; i < round.dealerHand().allCards().size(); i++) {
            int number = round.dealerHand().allCards().get(i).number();
            Suit suit = round.dealerHand().allCards().get(i).suit();
            drawNewCard(dealerHBox, suit, number);
        }

        Boolean won = _blackjackModel.currentRound().isWon();

        String message;
        Image result;
        if (won == null) {
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
        Scene scene = new Scene(rootGroup, 300, 300, Color.TRANSPARENT);
        dialogStage.setScene(scene);
        dialogStage.centerOnScreen();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        Circle dragger = new Circle(150, 150, 150);

        dragger.setFill(new ImagePattern(result, 0, 0, 1, 1, true));

        Text text = new Text(message); //20, 110,
        text.setFill(Color.WHITE);
        //text.setEffect(new Lighting());
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 50));

        // USE A LAYOUT VBOX FOR EASIER POSITIONING OF THE VISUAL NODES ON SCENE
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(130, 60, 60, 44));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(text);
        rootGroup.getChildren().addAll(dragger, vBox);
        dialogStage.show();

        // Handle button Disable-ness-ity.
        hit.setDisable(true);
        stand.setDisable(true);
        newGame.setDisable(false);
        dialogStage.setAlwaysOnTop(true);


        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1500),
                ae -> dialogStage.close()));
        timeline.play();

    }
}
