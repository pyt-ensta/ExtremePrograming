package formation.xp;

import java.util.*;
import formation.xp.JeuCartes.Cartes;

public enum Combinaisons {
	Paire("Paire",1),
	DoublePaire("Double paire",2),
	Brelan("Brelan",3),
	Quinte("Quinte",4),
	Couleur("Couleur",5),
	Full("Full",6),
	Carre("Carré",7),
	QuinteFlush("Quinte Flush",8),
	QuinteRoyal("Quinte royale",9);
	
	String name;
	int value;
	
	public Combinaisons resultat(List<Cartes> cartes) {
		
		return null;
	}
	
	private boolean hasPaire(List<Cartes> cartes) {
		return false;
	}
	
	public String toString() {
		return this.name;
	}
	
	Combinaisons(String name, int value){
		this.name = name;
		this.value = value;
	}
}
