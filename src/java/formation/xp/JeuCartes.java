package formation.xp;

import java.util.*;

public class JeuCartes {

	public static enum Cartes{
		PiqueAs("As de Pique",0),
		PiqueRoi("Roi de Pique",1),
		PiqueDame("Dame de Pique",2),
		PiqueValet("Valet de Pique",3),
		PiqueDix("Dix de Pique",4),
		PiqueNeuf("Neuf de Pique",5),
		PiqueHuit("Huit de Pique",6),
		PiqueSept("Sept de Pique",7),
		CoeurAs("As de Coeur",8),
		CoeurRoi("Roi de Coeur",9),
		CoeurDame("Dame de Coeur",10),
		CoeurValet("Valet de Coeur",11),
		CoeurDix("Dix de Coeur",12),
		CoeurNeuf("Neuf de Coeur",13),
		CoeurHuit("Huit de Coeur",14),
		CoeurSept("Sept de Coeur",15),
		CarreauAs("As de Carreau",16),
		CarreauRoi("Roi de Carreau",17),
		CarreauDame("Dame de Carreau",18),
		CarreauValet("Valet de Carreau",19),
		CarreauDix("Dix de Carreau",20),
		CarreauNeuf("Neuf de Carreau",21),
		CarreauHuit("Huit de Carreau",22),
		CarreauSept("Sept de Carreau",23),
		TrefleAs("As de Trèfle",24),
		TrefleRoi("Roi de Trèfle",25),
		TrefleDame("Dame de Trèfle",26),
		TrefleValet("Valet de Trèfle",27),
		TrefleDix("Dix de Trèfle",28),
		TrefleNeuf("Neuf de Trèfle",29),
		TrefleHuit("Huit de Trèfle",30),
		TrefleSept("Sept de Trèfle",31);
		
		private String name;
		private int key;
		
		public String toString() {
			return(name);
		}
		
		public static Cartes fromInt(int key) {
			for (Cartes carte : Cartes.values()) {
	            if (carte.key == key) {
	                return carte;
	            }
			}
			throw new NoSuchElementException("no enum for value " + key);
		}
		
		Cartes(String name, int key){
			this.name = name;
			this.key = key;			
		}
	}
	
	private List<Cartes> cartesTirees;
	
	public JeuCartes ()
	{
		cartesTirees = new ArrayList<Cartes>();
	}
	
	public Cartes tirerCartes() {
		Cartes carte;
		Random rand = new Random();
		int i;
		do {
			i = rand.nextInt(32);
			carte = Cartes.fromInt(i);
		}
		while(cartesTirees.contains(carte));
		cartesTirees.add(carte);
		return carte;
	}
	
}
