package formation.xp;

import java.util.*;
import formation.xp.JeuCartes.Cartes;

public class Table {
	
	private List<Cartes> hiddenCards, visibleCards;
	private Map<Player,Integer> mises;
	int miseActuelle;
	List<Player> turnPlayer;
	private JeuCartes cartes;
	
	public int getMiseActuelle() {
		return miseActuelle;
	}
	
	public void setMise(Player joueur, int mise) {
		if (joueur.getBank()<mise) {
			throw new IllegalArgumentException("Mise supérieure à la cagnotte");
		}
		else {
			if (mises.containsKey(joueur)) {
				mises.replace(joueur, mises.get(joueur)+mise);
			}
			else {
				mises.put(joueur,mise);
			}
			joueur.modifyBank(0-mise);
			miseActuelle = mise;
		}
	}
	
	public void suivre(Player joueur) {
		setMise(joueur, miseActuelle);
	}
	
	public void relancer(Player joueur, int relance) {
		setMise(joueur,miseActuelle + relance);
	}
	
	public void faireTapis(Player joueur) {
		setMise(joueur,joueur.getBank());
	}
	
	public void passer(Player joueur) {
		turnPlayer.remove(joueur);
	}
	
	public int getMise(Player joueur) {
		return mises.get(joueur);
	}
	
	public void addCards(List<Cartes> cartes) {
		Iterator<Cartes> it = cartes.listIterator();
		while(it.hasNext()) {
			hiddenCards.add(it.next());
		}
	}
	
	public List<Cartes> viewCards(){
		List<Cartes> viewCard = new ArrayList<Cartes>(visibleCards);
		return viewCard;
	}
	
	public Cartes flipCard() {
		Cartes cards = hiddenCards.remove(0);
		visibleCards.add(cards);
		return cards;
	}
	
	public int getTotalMise() {
		Collection<Integer> values = mises.values();
		Iterator<Integer> it = values.iterator();
		int mise =0;
		while(it.hasNext()) {
			mise+=it.next();
		}
		return mise;
	}
	
	public List<Player> getTurnPlayers() {
		return new ArrayList(turnPlayer);
	}
	
	
	
	//Constructor
	public Table(List<Player> joueur) {
		hiddenCards = new ArrayList<Cartes>();
		visibleCards = new ArrayList<Cartes>();
		mises = new HashMap<Player,Integer>();
		miseActuelle = 0;
		turnPlayer = new ArrayList<Player>(joueur);
		cartes = new JeuCartes();
		
		Player activePlayer;
		Iterator<Player> it = turnPlayer.listIterator();
		List<Cartes> playerCards;
		Iterator<Cartes> itCards;
		
		while(it.hasNext()) {
			activePlayer = it.next();
			playerCards = new ArrayList<Cartes>();
			playerCards.add(cartes.tirerCartes());
			playerCards.add(cartes.tirerCartes());
			activePlayer.setHand(playerCards);
		}
		for(int i=0;i<5;i++) {
			hiddenCards.add(cartes.tirerCartes());
		}
	}
	
}
