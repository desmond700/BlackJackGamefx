package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class Hand {
	protected ObservableList<Node> cards;
	private SimpleIntegerProperty value = new SimpleIntegerProperty();
	
	public Hand() {
	}
	
	public void addCard(Card c) {
		cards.add(c);
		if(c.getValue() == 1) {
			if(value.get() == 10 || value.get() == 1) value.set(value.get() + 11);
			else if(value.get() == 11) value.set(value.get() + 1);
			else value.set(value.get() + c.getValue());
		}
		else if(c.getValue() >= 11) {
			if(c.getValue() == 1)
				value.set(value.get() + 20);
			else
				value.set(value.get() + 10);
		}
		else
			value.set(value.get() + c.getValue());
	}
	
	public SimpleIntegerProperty getCardValue() {
		return value;
	}
	
	public boolean isBlackJack(){
		if(value.get() == 21) return true;
		return false;
	}
	
	public boolean isBust() {
		if(value.get() > 21) return true;
		return false;
	}
	
	public void reset() {
		cards.clear();
		value.set(0);
	}

}
