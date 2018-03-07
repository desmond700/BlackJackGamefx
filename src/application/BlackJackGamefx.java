package application;
	
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class BlackJackGamefx extends Application {
	private Dealer dealer = new Dealer();
	private Player player = new Player();
	private Text message = new Text();
	private SimpleBooleanProperty dealable = new SimpleBooleanProperty(false);
	private SimpleBooleanProperty hitOrStandable = new SimpleBooleanProperty(false);
	private SimpleBooleanProperty isNewGame = new SimpleBooleanProperty(false);
	HBox dealerCards = new HBox(20);
	HBox playerCards = new HBox(20);
	TextField money = new TextField("Money");
	Label dealerTotal = new Label("Total: ");
	final TextField bet = new TextField("Bet");
	Button Deal = new Button("Deal");
	float Money = 100f;
	String winner = "";
	URL intro = getClass().getResource("/resources/86876__milton__title-screen.wav");
	URL win = getClass().getResource("/resources/86880__milton__winnv.wav");
	URL lose = getClass().getResource("/resources/86866__milton__losev.wav");
	URL dealerBlkJck = getClass().getResource("/resources/86857__milton__dealerblackjack.wav");
	URL blackJack = getClass().getResource("/resources/86853__milton__blackjack.wav");
	URL exitGame = getClass().getResource("/resources/86860__milton__exit-game.wav");
	URL push = getClass().getResource("/resources/86871__milton__pushv.wav");
	URL buttonClick = getClass().getResource("/resources/button-click.wav");
	URL loseMus = getClass().getResource("/resources/horn-fail-wahwah.wav");
	AudioClip[] clips = new AudioClip[9];
	
	private Parent createContent() {
		dealer.hand.cards = dealerCards.getChildren();
		player.hand.cards = playerCards.getChildren();
		message.setFont(Font.font(java.awt.Font.SERIF, 17));
		message.setFill(Color.BLACK);
		message.setText("PLACE A BET");
		clips[0] = new AudioClip(intro.toString());
		clips[1] = new AudioClip(win.toString());
		clips[2] = new AudioClip(lose.toString());
		clips[3] = new AudioClip(dealerBlkJck.toString());
		clips[4] = new AudioClip(blackJack.toString());
		clips[5] = new AudioClip(exitGame.toString());
		clips[6] = new AudioClip(push.toString());
		clips[7] = new AudioClip(buttonClick.toString());
		clips[8] = new AudioClip(loseMus.toString());
		
		clips[0].setCycleCount(100);
		clips[0].play(1.0);
		
		Pane root = new Pane();
		root.setPrefSize(800, 600);
		
		Region background = new Region();
		background.setPrefSize(800, 600);
		
		// Creates the box to hold top and bottom rectangles
		VBox rootLayout = new VBox(5);
        rootLayout.setPadding(new Insets(5, 5, 5, 5));
        
        // Creates the top rectangle that displays the dealer and the player
		Rectangle topBG = new Rectangle(800, 500);
		topBG.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/resources/BG.jpg"))));
		
		// Creates the rectangle that displays the control buttons
		Rectangle bottomBG = new Rectangle(800, 100);
		bottomBG.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/resources/BG2.jpg"))));
		
		// Vertical box which holds dealer and player's cards
		VBox topBox = new VBox();
		topBox.setAlignment(Pos.CENTER);
		
		// Sets attributes of dealer's card container
		dealerCards.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: white;");
        dealerCards.setMaxWidth(600);
	    dealerCards.setLayoutX(400);
	    dealerCards.setTranslateY(40);
        dealerCards.setPrefHeight(155);
        dealerCards.setPadding(new Insets(1.5,0,1.5,2));
        
        // Sets attributes of player's card container
        playerCards.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: white;");
	    playerCards.setMaxWidth(600);
	    playerCards.setTranslateY(40);
	    playerCards.setPrefHeight(155);
	    playerCards.setPadding(new Insets(1.5,2,1.5,2));
	  
	    // Player's label attributes
		Label PlayerLb = new Label("Player");
		PlayerLb.setRotate(-90);
		PlayerLb.setTranslateX(-325);
		PlayerLb.setTranslateY(-60);
		PlayerLb.setFont(Font.font(40));
		PlayerLb.setTextFill(Color.BLACK);
		
		// Dealer's label attributes
		Label DealerLb = new Label("Dealer");
		DealerLb.setRotate(-90);
		DealerLb.setTranslateX(-325);
		DealerLb.setTranslateY(-60);
		DealerLb.setFont(Font.font(40));
		DealerLb.setTextFill(Color.BLACK);
		
		// Dealer's (total) label attributes
		dealerTotal.setTranslateX(340);
		dealerTotal.setTranslateY(-40);
        dealerTotal.setTextFill(Color.BLACK);
        dealerTotal.setFont(Font.font(20));
        
        // Player's (total) label attributes
		Label playerTotal = new Label("Total: ");
        playerTotal.setTranslateX(340);
        playerTotal.setTranslateY(-40);
		playerTotal.setFont(Font.font(20));
		playerTotal.setTextFill(Color.BLACK);
		
        topBox.getChildren().addAll(dealerCards,DealerLb,dealerTotal,message,playerCards,PlayerLb,playerTotal);
        
		// Bottom box which holds the control buttons
        HBox bottomBox = new HBox(70);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(15,5,15,5));
        
        // Textfield for money
        money.setMaxWidth(70);
        money.setEditable(false);
        
        // Sets bet textfield max-width
        bet.setMaxWidth(50);
        player.setMoney(100);
        
        // Control buttons
        Button newGame = new Button("New game");
        newGame.setMaxSize(90, 40);
        newGame.setPadding(new Insets(10,10,10,10));
        
        Button Deal = new Button("Deal");
        Deal.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        Deal.setPadding(new Insets(0,20,0,20));
        
        Button Hit = new Button("Hit");
        Hit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        Hit.setPadding(new Insets(5,10,5,10));
        
        Button Stand = new Button("Stand");
        Stand.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        Stand.setPadding(new Insets(5,10,5,10));
        
        VBox hitStandbtns = new VBox(15, Hit, Stand);

        bottomBox.getChildren().addAll(bet, money, Deal, hitStandbtns,newGame);
        
        // ADD BOTH STACKS TO ROOT LAYOUT
        rootLayout.getChildren().addAll(new StackPane(topBG,topBox), new StackPane(bottomBG,bottomBox));
        root.getChildren().addAll(background, rootLayout);
        
        // Binding properties for control buttons
        Deal.disableProperty().bind(dealable.not());
        Hit.disableProperty().bind(hitOrStandable.not());
        Stand.disableProperty().bind(hitOrStandable.not());
        newGame.disableProperty().bind(isNewGame.not());
       
        dealerTotal.textProperty().bind(new SimpleStringProperty("Total: ").concat("0"));
        playerTotal.textProperty().bind(new SimpleStringProperty("Total: ").concat(player.hand.getCardValue().asString()));
        
        // Check bet's textfield if valid bet is entered and take appropriate action
        bet.setOnKeyReleased(e -> {
        	if(isNotValidBet() == 1) {
        		message.setText("BET IS INVALID. PLACE ANOTHER BET");
        		dealable.set(false);
        	}
        	else if(isNotValidBet() == -1) {
        		message.setText("PLEASE ENTER A NUMBER.");
        		dealable.set(false);
        	}
        	else {
        		message.setText("BET IS VALID.");
        		dealable.set(true);
        	}
        });
        
        /*dealer.hand.getCardValue().addListener((obs, old, newValue) -> {
        	if(newValue.intValue() > 21)
        		endGame();
        });
        
        player.hand.getCardValue().addListener((obs, old, newValue) -> {
        	if(newValue.intValue() >= 21) {
        		endGame();
        	}
        });*/
        
		
        // Deal button even handling
        Deal.setOnAction(e -> {
        	clips[7].play(1.0);
        	startGame();
        	
        });
        
        // Hit button event handling
        Hit.setOnAction(e -> {
        	clips[7].play(1.0);
        	message.setText("PLAYER HITS");
        	player.Hit(dealer.dealCard());
        	if (player.hand.getCardValue().get() >= 21)
    			endGame();
        	
        });
        
        // Stand button event handling
        Stand.setOnAction(e -> {
        	clips[7].play(1.0);
        	dealer.reveal();
        	while(dealer.hand.getCardValue().get() < 17)
        		dealer.hand.addCard(dealer.dealCard());
        	
        	endGame();
        });
        
        newGame.setOnAction(e -> {
        	clips[7].play(1.0);
        	player.setMoney(Money);
        	bet.setDisable(false);
        	dealable.set(false);
        	bet.setText("Bet");
        	money.setText("Money");
        	message.setText("PLACE A BET");
        	
        	// Remove cards from player and dealer's hand
    		dealer.hand.reset();
    		player.hand.reset();
			isNewGame.set(false);
			clips[0].setCycleCount(100);
			clips[0].play(1.0);
        });	
        
		return root;
	}
	
	// Starts the game play
	private void startGame() {
		dealable.set(false);
		hitOrStandable.set(true);
		bet.setEditable(false);
		dealerTotal.textProperty().bind(new SimpleStringProperty("Total: ").concat("0"));
		clips[0].stop();
		clips[5].stop();
		clips[8].stop();
		// Remove cards from player and dealer's hand
		dealer.hand.reset();
		player.hand.reset();
		
		// Remove previous messages from screen
		message.setText("");
		
		// Prints money to textfield
		money.setText(String.format("$%.2f", player.getMoney()));
		
		// Shuffles deck and deal cards
		dealer.shuffle();
		player.hand.addCard(dealer.dealCard());
		player.hand.addCard(dealer.dealCard());
		dealer.hand.addCard(dealer.dealCard());
		dealer.hand.addCard(dealer.dealCard());
		dealer.hideCard();
		
		// Ends game if dealer's hand is greater than 21 or player's hand is greater than or equal to 21
		if(dealer.hand.getCardValue().get() > 21)
    		endGame();
		else if (player.hand.getCardValue().get() >= 21)
			endGame();
		
	}
	
	// Method that handles the end of game event
	private void endGame() {
		dealable.set(true);
		hitOrStandable.set(false);
		bet.setEditable(true);
		dealer.reveal();
		int dealerValue = dealer.hand.getCardValue().get();
		int playerValue = player.hand.getCardValue().get();		

		if(dealer.hand.isBlackJack() || player.hand.isBust() 
				|| (dealerValue > playerValue && !dealer.hand.isBust())) {
			winner = "DEALER";
			//Money -=  player.getBet();
			player.setMoney(player.getMoney() - player.getBet());
			money.setText(String.format("$%.2f", player.getMoney()));
			clips[2].play(1.0);
		}
		else if(dealerValue == playerValue) {
			winner = "DEALER";
			clips[6].play(1.0);
		}
		else if(dealer.hand.isBust() || player.hand.isBlackJack() || dealerValue > 21 || (playerValue > dealerValue)){
			winner = "PLAYER";
			//Money += player.getBet();
			player.setMoney(player.getMoney() + player.getBet());
			money.setText(String.format("$%.2f", player.getMoney()));
			if(player.hand.isBlackJack())
				clips[4].play(1.0);
			else
				clips[1].play(1.0);
		}
		
		dealerTotal.textProperty().bind(new SimpleStringProperty("Total: ").concat(dealer.hand.getCardValue().asString()));
		
		message.setText(winner + " WON.");
		
		if(player.getMoney() <= 0) {
			dealable.set(false);
			bet.setDisable(true);
			isNewGame.set(true);
			message.setText(winner + " WON. GAME OVER. YOUR OUT OF MONEY");
		}
		
		// Plays end audio depending on who wins
		if(winner.equals("PLAYER") || (dealerValue == playerValue)) {
			Timer timer = new java.util.Timer();
			timer.schedule(new TimerTask() {
			    public void run() {
			         Platform.runLater(new Runnable() {
			            public void run() {
			            	clips[5].play(1.0);
			            }
			        });
			    }
			}, 2000);
		}
		else {
			Timer timer = new java.util.Timer();
			timer.schedule(new TimerTask() {
			    public void run() {
			         Platform.runLater(new Runnable() {
			            public void run() {
			            	clips[8].play(1.0);
			            }
			        });
			    }
			}, 2000);
		}
		
	}
	
	// Method that checks if bet is not valid
	private int isNotValidBet() {
		try {
        	player.setBet(Float.parseFloat(bet.getText()));
        }
        catch(NumberFormatException ex) {
    		return -1;
        }
    	if(bet.getText() == null || player.getBet() <= 0 
    			|| player.getBet() > player.getMoney()) {
			return 1;
		}
    	
    	return 0;
	}

	
	@Override
	public void start(Stage primaryStage) {
		try {
			Scene scene = new Scene(createContent(),800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("BlackJackGame");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
