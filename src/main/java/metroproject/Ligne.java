package metroproject;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class Ligne {
	private String numero;
	private LinkedHashSet<Station> stationsLigne;
	
	public Ligne(String numero, LinkedHashSet<Station> stationsLigne) {
		super();
		this.numero = numero;
		this.stationsLigne = stationsLigne;
	}
	
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
	public boolean hasStation(String nomStation) {
		boolean stationFound=false;
		Iterator<Station> statIt = stationsLigne.iterator();
		Station stat=statIt.next();
		while(statIt.hasNext() && !stationFound) {
			if(stat.getNomStation().equals(nomStation)) {
				stationFound=true;
			}
			else {
				stat=statIt.next();
			}
		}
		return stationFound;
	}
	public Station getStation(String nomStation) {
		Iterator<Station> statIt = stationsLigne.iterator();
		Station stat=statIt.next();
		boolean stationFound=false;
		while(statIt.hasNext() && !stationFound) {
			if(stat.getNomStation().equals(nomStation)) {
				stationFound=true;
			}
			else {
				stat=statIt.next();
			}
		}
		return stat;
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
