package formation.xp;

import java.util.*;

import formation.xp.JeuCartes.Cartes;

public class Game {

	static List<Player> joueurs;
	static Table plateauJeu;
	// static JeuCartes cartes;

	static void init() {
		joueurs = new ArrayList<Player>();
		// plateauJeu = new Table();
		// cartes = new JeuCartes();
		for (int i = 0; i < 2; i++) {
			joueurs.add(new Player("Player " + i));
		}
	}

	private static void demanderMise(Player activePlayer) {
		Scanner sc = new Scanner(System.in);
		Integer miseJoueur = 0;
		String choix;
		boolean done = false;
		System.out.println("Joueur en cours : " + activePlayer.getName());
		System.out.println("Voici vos cartes : " + activePlayer.getHand());
		System.out.println("Cartes visibles : " + plateauJeu.viewCards());
		System.out.println("Somme en jeu : " + plateauJeu.getTotalMise());
		System.out.println("Votre cagnotte : " + activePlayer.getBank());
		if (plateauJeu.getMiseActuelle() == 0) {
			do {
				System.out.println("Faites votre mise initiale (0 pour passer)");
				choix = sc.nextLine();
				try {
					miseJoueur = Integer.parseInt(choix);
					if (miseJoueur <= activePlayer.getBank() && miseJoueur >= 0) {
						done = true;
					} else {
						throw new NumberFormatException();
					}
				} catch (NumberFormatException e) {
					System.out.println("Saisie invalide");
				}
			} while (!done);
			if (miseJoueur == 0) {
				plateauJeu.passer(activePlayer);
			} else {
				plateauJeu.setMise(activePlayer, miseJoueur);
			}
		} else {
			System.out.println("Mise en cours : " + plateauJeu.getMiseActuelle());
			System.out.println("Que voulez vous faire ?");
			System.out.println("-1 pour se coucher");
			System.out.println("0 pour suivre");
			System.out.println("Saisir une mise pour relancer");
			do {
				choix = sc.nextLine();
				try {
					miseJoueur = Integer.parseInt(choix);
					if (miseJoueur <= activePlayer.getBank() && miseJoueur >= -1) {
						done = true;
					} else {
						throw new NumberFormatException();
					}
				} catch (NumberFormatException e) {
					System.out.println("Saisie invalide");
				}
			} while (!done);
			if (miseJoueur == -1) {
				plateauJeu.passer(activePlayer);
			} else if (miseJoueur == 0) {
				plateauJeu.suivre(activePlayer);
			} else {
				plateauJeu.relancer(activePlayer, miseJoueur);
			}
		}
	}

	private static void demanderMiseInitiale(Player activePlayer) {
		Scanner sc = new Scanner(System.in);
		Integer miseJoueur = 0;
		String choix;
		boolean done = false;
		System.out.println("Joueur en cours : " + activePlayer.getName());
		System.out.println("Voici vos cartes : " + activePlayer.getHand());
		System.out.println("Cartes visibles : " + plateauJeu.viewCards());
		System.out.println("Votre cagnotte : " + activePlayer.getBank());
		if (plateauJeu.getMiseActuelle() == 0) {
			do {
				System.out.println("Faites votre mise initiale (0 pour passer)");
				choix = sc.nextLine();
				try {
					miseJoueur = Integer.parseInt(choix);
					if (miseJoueur <= activePlayer.getBank() && miseJoueur >= 0) {
						done = true;
					} else {
						throw new NumberFormatException();
					}
				} catch (NumberFormatException e) {
					System.out.println("Saisie invalide");
				}
			} while (!done);
			if (miseJoueur == 0) {
				plateauJeu.passer(activePlayer);
			} else {
				plateauJeu.setMise(activePlayer, miseJoueur);
			}
		} else {
			System.out.println("Mise en cours : " + plateauJeu.getMiseActuelle());
			System.out.println("Que voulez vous faire ?");
			System.out.println("0 pour passer");
			System.out.println("1 pour suivre");
			do {
				choix = sc.nextLine();
				try {
					miseJoueur = Integer.parseInt(choix);
					if (miseJoueur <= 1 && miseJoueur >= 0) {
						if (plateauJeu.getMiseActuelle() <= activePlayer.getBank()) {
							done = true;
						} else {
							throw new NumberFormatException();
						}

					} else {
						throw new NumberFormatException();
					}
				} catch (NumberFormatException e) {
					System.out.println("Saisie invalide");
				}
			} while (!done);
			if (miseJoueur == 0) {
				plateauJeu.passer(activePlayer);
			} else {
				plateauJeu.suivre(activePlayer);
			}

		}
	}
	
	private static void eliminerJoueur() {

		Iterator<Player> it = joueurs.listIterator();
		Player activePlayer;
		while (it.hasNext()) {
			activePlayer = it.next();
			if (activePlayer.hasLost()) {
				joueurs.remove(activePlayer);
				System.out.println("Le "+activePlayer.getName()+" est éliminé !!!");
			}
		}
	}

	static boolean playTurn() {
		// distribution cartes joueurs
		Iterator<Player> it;
		// Iterator<Cartes> itCards;
		// List<Cartes> playerCards;
		Player activePlayer;

		plateauJeu = new Table(joueurs);

		// mises initiales
		it = plateauJeu.getTurnPlayers().listIterator();
		while (it.hasNext()) {
			activePlayer = it.next();
			demanderMiseInitiale(activePlayer);
		}

		if (plateauJeu.getTurnPlayers().size() == 0) {
			return true;
		} else if (plateauJeu.getTurnPlayers().size() == 1) {
			plateauJeu.getTurnPlayers().remove(0).modifyBank(plateauJeu.getTotalMise());
			return true;
		}

		// retourner trois cartes
		for (int i = 0; i < 3; i++) {
			plateauJeu.flipCard();
		}

		System.out.println("Cartes retournées : " + plateauJeu.viewCards());

		it = plateauJeu.getTurnPlayers().listIterator();
		while (it.hasNext()) {
			activePlayer = it.next();
			demanderMise(activePlayer);
		}

		if (plateauJeu.getTurnPlayers().size() == 0) {
			eliminerJoueur();
			return true;
		} else if (plateauJeu.getTurnPlayers().size() == 1) {
			plateauJeu.getTurnPlayers().remove(0).modifyBank(plateauJeu.getTotalMise());
			eliminerJoueur();
			return true;
		}

		// Retourner la 4ème carte

		plateauJeu.flipCard();
		System.out.println("Cartes retournées : " + plateauJeu.viewCards());

		it = plateauJeu.getTurnPlayers().listIterator();
		while (it.hasNext()) {
			activePlayer = it.next();
			demanderMise(activePlayer);
		}

		if (plateauJeu.getTurnPlayers().size() == 0) {
			eliminerJoueur();
			return true;
		} else if (plateauJeu.getTurnPlayers().size() == 1) {
			plateauJeu.getTurnPlayers().remove(0).modifyBank(plateauJeu.getTotalMise());
			eliminerJoueur();
			return true;
		}

		// Retourner la 5ème carte
		plateauJeu.flipCard();
		System.out.println("Cartes retournées : " + plateauJeu.viewCards());

		it = plateauJeu.getTurnPlayers().listIterator();
		while (it.hasNext()) {
			activePlayer = it.next();
			demanderMise(activePlayer);
		}

		if (plateauJeu.getTurnPlayers().size() == 0) {
			eliminerJoueur();
			return true;
		} else if (plateauJeu.getTurnPlayers().size() == 1) {
			plateauJeu.getTurnPlayers().remove(0).modifyBank(plateauJeu.getTotalMise());
			eliminerJoueur();
			return true;
		} 
		return true;
	}
}
