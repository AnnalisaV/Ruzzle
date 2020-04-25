package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {

	
	// algoritmo ricorsivo 
	
	
	
	
	/**
	 * Se al parola e' presente o no nella matrice Ruzzle
	 * @return
	 */
	public List<Pos> trovaProla(String parola, Board board) {
		
		// la ricorsione deve partire dalla posizione della prima lettera 
		for (Pos pos : board.getPositions()) {
			// la lettera in quella posizione deve essere uguale alla prima lettera della parola
			if (board.getCellValueProperty(pos).get().charAt(0)== parola.charAt(0)) {
				//inizio potenziale della parola 
				//ricorsione
				List<Pos> percorso= new ArrayList<Pos>(); 
				percorso.add(pos); // la soluzione ha gia' questa dentro
				if(ricorsione(parola, 1, percorso, board))
					return percorso; 
				
				
			}
		}
		return null; // non ho trovato niente sulla matrice per quella parola
	}

	private boolean ricorsione(String parola, int livello, List<Pos> percorso, Board board) {
		
		//caso terminale
		if (livello== parola.length())
		return true;
		
		
		// caso generale 
		Pos ultima= percorso.get(percorso.size()-1); 
		List<Pos> adiacenti= board.getAdjacencies(ultima); 
		
		for (Pos p : adiacenti) {
			// controllo non sia una cella utilizzata e che contenga la lettera che mi serve 
			if (!percorso.contains(p) && parola.charAt(livello)==board.getCellValueProperty(p).get().charAt(0)){
				// se ok ricorsione
				percorso.add(p); 
				if(ricorsione(parola, livello+1, percorso, board))
					return true; // se la trovo ciao esco subito (no problema di ottimizzazione, se trovo ok)
				percorso.remove(percorso.size()-1); 
			}
		}
		return false; 
	}
}
