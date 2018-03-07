package application;


public class Player {
	private String name = "";
    private float bet = 0.0f;
	private float money = 0.0f;
	Hand hand;
	
	
	public Player() {
		hand = new Hand();
	}
	
	public String getName() {
		return name;
	}
	
	public float getBet() {
		return bet;
	}
	
	public float getMoney() {
		return money;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setBet(float bet) {
		this.bet = bet;
	}
	
	public void setMoney(float money) {
		this.money = money;
	}
	
	void Hit(Card c) {
		hand.addCard(c);
		
	}
	void stand() {
		
	}
	void split() {
		
	}
	

}
