package formation.xp;

import java.util.*;
import formation.xp.JeuCartes.Cartes;

public class Player {

	private String name;
	private List<Cartes> hand;
	private int bank;
	
	public void setHand(List<Cartes> cartes) {
		hand = new ArrayList<Cartes>();
		Iterator<Cartes> it = cartes.listIterator();
		while(it.hasNext()) {
			hand.add(it.next());
		}
	}
	
	public List<Cartes> getHand() {
		return hand;
	}
	
	public String getName() {
		return name;
	}
	
	public int getBank() {
		return bank;
	}
	
	public void modifyBank(int value) {
		this.bank += value;
	}
	
	public boolean hasLost() {
		return (bank <= 0);
	}
	
	public Player() {
		this.bank = 1000;
	}
	
	public Player(String name) {
		this.name = name;
		this.bank = 1000;
	}
	
	public Player(String name, int bank) {
		this.name = name;
		this.bank = bank;
	}
}
