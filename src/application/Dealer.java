package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Dealer {
	Hand hand;
	private String hiddenCard = "";
	private Deck deck;
	
	public Dealer() {
		hand = new Hand();
		deck = new Deck();
	}
	
	public void shuffle() {
		
		Card temp;
		int rand = 0;
		for(int i = 51; i > 0 ; i--) {
			rand = (int) (Math.random() * (i+1));
			temp = deck.cards[i];
			deck.cards[i] = deck.cards[rand];
			deck.cards[rand] = temp;
		}
		
		deck.cardUsed = 0;
	}
	
	public void hideCard() {
		hiddenCard = hand.cards.get(1).toString();
		hand.cards.remove(1);
		hand.cards.add(1,new ImageView(new Image(this.getClass().getResourceAsStream("/resources/BackBlue.jpg"))));
	}
	
	public void reveal() {
		hand.cards.remove(1);
		hand.cards.add(1,new ImageView(new Image(this.getClass().getResourceAsStream("/resources/"+hiddenCard+".png"))));
	}
	
	public Card dealCard() {
		if(deck.cardUsed == 52)
			shuffle();
		
		deck.cardUsed++;
		return deck.cards[deck.cardUsed - 1];
	}

}
