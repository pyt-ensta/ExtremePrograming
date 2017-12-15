package formation.xp;

import java.util.*;

import org.junit.Test;
import formation.xp.*;
import formation.xp.JeuCartes.Cartes;

import junit.framework.TestCase;

public class GeneralTest extends TestCase {
	@Test
	public void test() {
		testInitGame();
		testPlayer();
		testTable();
		testJeuCartes();
		testTurn();
	}
	
	public void testPlayer() {
		Player player = new Player();
		int bank = player.getBank();
		assertEquals(1000, bank);
		player = new Player("toto");
		assertEquals(1000, player.getBank());
		assertEquals("toto", player.getName());
		player = new Player("tata",3000);
		assertEquals(3000, player.getBank());
		assertEquals("tata", player.getName());
		player.modifyBank(1500);
		assertEquals(4500, player.getBank());
	}
	
	public void testInitGame() {
		Player player = new Player();
		List<Player> players = new ArrayList<Player>();
		players.add(player);
		List<Cartes> cartes = new ArrayList<Cartes>();
		cartes.add(Cartes.PiqueValet);
		cartes.add(Cartes.CoeurAs);
		player.setHand(cartes);
		assertEquals(cartes, player.getHand());
		
		Game.init();
		assertEquals(2,Game.joueurs.size());
		Table plateau = new Table(players);
		plateau.setMise(player,100);
		assertEquals(100,plateau.getMise(player));
		plateau.setMise(player,50);
		assertEquals(150,plateau.getMise(player));
	}
	
	public void testTurn() {
		Game.init();
		Game.playTurn();
		Iterator<Player> it = Game.joueurs.listIterator();
		List<Cartes> playerCards;
		while(it.hasNext()) {
			playerCards = it.next().getHand();
			assertEquals(2,playerCards.size());
		}
	}
	
	public void testTable() {
		List<Cartes> cartes = new ArrayList<Cartes>();
		Player player = new Player();
		List<Player> players = new ArrayList<Player>();
		players.add(player);
		Table plateau = new Table(players);
		//Test de la méthode init() Vérification du nombre de cartes
		assertEquals(3,plateau.viewCards().size());
		plateau.setMise(player,100);
		assertEquals(100,plateau.getMiseActuelle());
		assertEquals(100,plateau.getMise(player));
		assertEquals(900,player.getBank());
		plateau.setMise(player,150);
		assertEquals(250,plateau.getMise(player));
		assertEquals(750,player.getBank());
		assertEquals(250,plateau.getTotalMise());
		plateau.suivre(player);
		assertEquals(400,plateau.getTotalMise());
		plateau.relancer(player,50);
		assertEquals(600,plateau.getTotalMise());
		plateau.faireTapis(player);
		assertEquals(1000,plateau.getTotalMise());
		assertEquals(0,player.getBank());
		assertEquals(true,player.hasLost());
		plateau.passer(player);
		assertEquals(0, plateau.getTurnPlayers().size());
		cartes.add(Cartes.CarreauAs);
		plateau.addCards(cartes);
		//Test de la méthode flipCard()
		/*assertEquals(Cartes.CarreauAs,plateau.flipCard());
		assertEquals(cartes,plateau.viewCards());*/	
		}
		
	public void testJeuCartes() {
		assertEquals(Cartes.CarreauAs,Cartes.fromInt(16));
	}
	
}
