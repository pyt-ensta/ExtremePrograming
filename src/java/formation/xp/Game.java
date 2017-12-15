package formation.xp;

import java.util.*;

import formation.xp.JeuCartes.Cartes;

public class Game {

	static List<Player> joueurs;
	static Table plateauJeu;
	static JeuCartes cartes;
	
	static void init(){
		joueurs = new ArrayList<Player>();
		plateauJeu = new Table();
		cartes = new JeuCartes();
		
		for (int i=0;i<2;i++) {
			joueurs.add(new Player("Player "+i));
		}
	}
	
	static void playTurn() {		
		// distribution cartes joueurs
		Iterator<Player> it = joueurs.listIterator();
		Iterator<Cartes> itCards;
		List<Cartes> playerCards;
		Player activePlayer;
		while(it.hasNext()) {
			activePlayer = it.next();
			playerCards = new ArrayList<Cartes>();
			playerCards.add(cartes.tirerCartes());
			playerCards.add(cartes.tirerCartes());
			activePlayer.setHand(playerCards);
			System.out.println(activePlayer.getName());
			itCards = activePlayer.getHand().listIterator();
			while(itCards.hasNext()) {
				System.out.println(itCards.next());
			}
		}
		
		// faire les mises
		it = joueurs.listIterator();
		while(it.hasNext()) {
			activePlayer = it.next();
			if (plateauJeu.getMiseActuelle() == 0) {
				plateauJeu.setMise(activePlayer, 10);
			}
			else {
				plateauJeu.relancer(activePlayer, 10);
			}
			
			System.out.println(activePlayer.getName()+" mise "+plateauJeu.getMise(activePlayer));
		}
	}
}
