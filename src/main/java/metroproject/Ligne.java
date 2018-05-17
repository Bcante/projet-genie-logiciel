package metroproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;


@SuppressWarnings("serial")
public class Ligne implements Serializable{
	private String numero;
	private LinkedHashSet<Station> stationsLigneAller;
	private LinkedHashSet<Station> stationsLigneRetour;
	
	public Ligne(String numero) {
		this(numero, new LinkedHashSet<Station>());
	}
	
	public Ligne(String numero, LinkedHashSet<Station> ligneAller) {
		this.numero = numero;
		this.stationsLigneAller = ligneAller;
		this.stationsLigneRetour = this.reverseListStation(ligneAller);
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public LinkedHashSet<Station> getStationsLigne() {
		return stationsLigneAller;
	}
	public void setStationsLigne(LinkedHashSet<Station> stationsLigne) {
		this.stationsLigneAller = stationsLigne;
	}
	
	public LinkedHashSet<Station> getStationsLigneAller() {
		return stationsLigneAller;
	}

	public void setStationsLigneAller(LinkedHashSet<Station> stationsLigneAller) {
		this.stationsLigneAller = stationsLigneAller;
		this.setStationsLigneRetour(this.reverseListStation(stationsLigneAller));
	}

	public LinkedHashSet<Station> getStationsLigneRetour() {
		return stationsLigneRetour;
	}

	public void setStationsLigneRetour(LinkedHashSet<Station> stationsLigneRetour) {
		this.stationsLigneRetour = stationsLigneRetour;
	}

	/*retourne la station de départ de la ligne*/
	public Station getDepartAller() {
		Iterator<Station> statIt = stationsLigneAller.iterator();
		return statIt.next();
	}
	
	public LinkedHashSet<Station> reverseListStation(LinkedHashSet<Station> listToReverse) {
		ArrayList<Station> listTemp = new ArrayList<Station>(listToReverse);
		Collections.reverse((listTemp));
		return new LinkedHashSet<Station>(listTemp);
	}
	
	/*retourne le terminus*/
	public Station getTerminusAller() {
		Iterator<Station> statIt = stationsLigneAller.iterator();
		Station terminus=statIt.next();
		while(statIt.hasNext()) {
			terminus=statIt.next();
        }
		return terminus;
	}
	
	/*retourne la station de départ de la ligne*/
	public Station getDepartRetour() {
		Iterator<Station> statIt = stationsLigneRetour.iterator();
		return statIt.next();
	}
	
	/*retourne le terminus*/
	public Station getTerminusRetour() {
		Iterator<Station> statIt = stationsLigneRetour.iterator();
		Station terminus=statIt.next();
		while(statIt.hasNext()) {
			terminus=statIt.next();
        }
		return terminus;
	}
	
	public String toString() {
		String res = "";
		res += "Ligne "+this.numero +" aller : \n";
		for(Station s : this.stationsLigneAller) {
			res += s.getNomStation() + " ";
		}
		res += "\n";
		res += "Ligne "+this.numero +" retour : \n";
		for(Station s : this.stationsLigneRetour) {
			res += s.getNomStation() + " ";
		}
		res += "\n";
		return res;
	}
}
