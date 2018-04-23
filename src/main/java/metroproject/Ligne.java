package metroproject;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class Ligne {
	private String numero;
	private LinkedHashSet<Station> stationsLigne;
	
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public LinkedHashSet<Station> getStationsLigne() {
		return stationsLigne;
	}
	public void setStationsLigne(LinkedHashSet<Station> stationsLigne) {
		this.stationsLigne = stationsLigne;
	}
	
	/*retourne la station de départ de la ligne*/
	public Station getDepart() {
		Iterator<Station> statIt = stationsLigne.iterator();
		return statIt.next();
	}
	
	/*retourne le terminus*/
	public Station getTerminus() {
		Iterator<Station> statIt = stationsLigne.iterator();
		Station terminus=statIt.next();
		while(statIt.hasNext()) {
			terminus=statIt.next();
        }
		return terminus;
	}
	

}
