package metroproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;


@SuppressWarnings("serial")
public class Ligne implements Serializable{
	private String numero;
	private LinkedHashSet<Station> stationsLigneAllez;
	private LinkedHashSet<Station> stationsLigneRetour;
	
	public Ligne(String numero) {
		this(numero, new LinkedHashSet<Station>());
	}
	
	public Ligne(String numero, LinkedHashSet<Station> ligneAllez) {
		this.numero = numero;
		this.stationsLigneAllez = ligneAllez;
		this.stationsLigneRetour = this.reverseListStation(ligneAllez);
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public LinkedHashSet<Station> getStationsLigne() {
		return stationsLigneAllez;
	}
	public void setStationsLigne(LinkedHashSet<Station> stationsLigne) {
		this.stationsLigneAllez = stationsLigne;
	}
	
	public LinkedHashSet<Station> getStationsLigneAllez() {
		return stationsLigneAllez;
	}

	public void setStationsLigneAllez(LinkedHashSet<Station> stationsLigneAllez) {
		this.stationsLigneAllez = stationsLigneAllez;
		this.setStationsLigneRetour(this.reverseListStation(stationsLigneAllez));
	}

	public LinkedHashSet<Station> getStationsLigneRetour() {
		return stationsLigneRetour;
	}

	public void setStationsLigneRetour(LinkedHashSet<Station> stationsLigneRetour) {
		this.stationsLigneRetour = stationsLigneRetour;
	}

	/*retourne la station de départ de la ligne*/
	public Station getDepartAllez() {
		Iterator<Station> statIt = stationsLigneAllez.iterator();
		return statIt.next();
	}
	
	public LinkedHashSet<Station> reverseListStation(LinkedHashSet<Station> listToReverse) {
		ArrayList<Station> listTemp = new ArrayList<Station>(listToReverse);
		Collections.reverse((listTemp));
		return new LinkedHashSet<Station>(listTemp);
	}
	
	/*retourne le terminus*/
	public Station getTerminusAllez() {
		Iterator<Station> statIt = stationsLigneAllez.iterator();
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
}
