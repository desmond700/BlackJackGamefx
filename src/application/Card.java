package application;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card extends Parent {
	private static final int SPADES = 0,
			                 HEARTS = 1,
			                 DIAMONDS = 2,
			                 CLUBS = 3;
	
	private static final int ACE = 1,
                             JACK = 11,
                             QUEEN = 12,
                             KING = 13;
	//private Deck deck = new Deck();
	private int suit = 0;
	private int value = 0;
	
	public Card(int suit, int value) {
		this.suit = suit;
		this.value = value;

		getChildren().add(new ImageView(new Image(this.getClass().getResourceAsStream("/resources/" + toString().concat(".png")))));
	}
	
	public int getSuit() {
		return suit;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getSuitAsString() {
		
		switch(suit) {
		case SPADES:   return "spades";
		case HEARTS:   return "hearts";
		case DIAMONDS: return "diamonds";
		case CLUBS:    return "clubs";
		default:       return "??";
			
		}
	}
	
	public String getValueAsString() {
		 
		switch(value) {
		case 1:  return "Ace";
		case 2:  return "2";
		case 3:  return "3";
		case 4:  return "4";
		case 5:  return "5";
		case 6:  return "6";
		case 7:  return "7";
		case 8:  return "8";
		case 9:  return "9";
		case 10: return "10";
		case 11: return "jack";
		case 12: return "queen";
		case 13: return "king";
		default: return "??";
		}
	}
	
	public String toString() {
		return getValueAsString() + "_of_" + getSuitAsString();
	}

}
