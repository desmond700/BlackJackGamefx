package application;

public class Deck {
	protected Card[] cards;
    protected int cardUsed;
    
    public Deck() {
    	cards = new Card[52];
    	int cardCt = 0;
    	for(int suit = 0; suit < 4; suit++) {
    		for(int value = 1; value <= 13; value++) {
    			cards[cardCt] = new Card(suit,value);
    			cardCt++;
    		}
    	}
    	cardUsed = 0;
    }
    
    public int cardsLeft() {
    	
    	return 52 - cardUsed;
    }
	

}
