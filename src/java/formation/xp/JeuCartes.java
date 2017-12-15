package formation.xp;

import java.util.*;

public class JeuCartes {

	public static enum Cartes{
		PiqueAs("As de Pique",0,"Pique",14),
		PiqueRoi("Roi de Pique",1,"Pique",13),
		PiqueDame("Dame de Pique",2,"Pique",12),
		PiqueValet("Valet de Pique",3,"Pique",11),
		PiqueDix("Dix de Pique",4,"Pique",10),
		PiqueNeuf("Neuf de Pique",5,"Pique",9),
		PiqueHuit("Huit de Pique",6,"Pique",8),
		PiqueSept("Sept de Pique",7,"Pique",7),
		CoeurAs("As de Coeur",8,"Coeur",14),
		CoeurRoi("Roi de Coeur",9,"Coeur",13),
		CoeurDame("Dame de Coeur",10,"Coeur",12),
		CoeurValet("Valet de Coeur",11,"Coeur",11),
		CoeurDix("Dix de Coeur",12,"Coeur",10),
		CoeurNeuf("Neuf de Coeur",13,"Coeur",9),
		CoeurHuit("Huit de Coeur",14,"Coeur",8),
		CoeurSept("Sept de Coeur",15,"Coeur",7),
		CarreauAs("As de Carreau",16,"Carreau",14),
		CarreauRoi("Roi de Carreau",17,"Carreau",13),
		CarreauDame("Dame de Carreau",18,"Carreau",12),
		CarreauValet("Valet de Carreau",19,"Carreau",11),
		CarreauDix("Dix de Carreau",20,"Carreau",10),
		CarreauNeuf("Neuf de Carreau",21,"Carreau",9),
		CarreauHuit("Huit de Carreau",22,"Carreau",8),
		CarreauSept("Sept de Carreau",23,"Carreau",7),
		TrefleAs("As de Trèfle",24,"Trefle",14),
		TrefleRoi("Roi de Trèfle",25,"Trefle",13),
		TrefleDame("Dame de Trèfle",26,"Trefle",12),
		TrefleValet("Valet de Trèfle",27,"Trefle",11),
		TrefleDix("Dix de Trèfle",28,"Trefle",10),
		TrefleNeuf("Neuf de Trèfle",29,"Trefle",9),
		TrefleHuit("Huit de Trèfle",30,"Trefle",8),
		TrefleSept("Sept de Trèfle",31,"Trefle",7);
		
		private String name;
		private int key;
		private String couleur;
		private int valeur;
		
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
		
		Cartes(String name, int key, String couleur, int valeur){
			this.name = name;
			this.key = key;	
			this.couleur = couleur;
			this.valeur=valeur;
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
