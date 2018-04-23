package metroproject;

import java.io.Serializable;

public class Rail implements Serializable{
	Station depart, arrivee;
	int duree;
	boolean incident;
	
	public Rail(Station depart, Station arrivee, int duree, boolean incident) {
		this.depart = depart;
		this.arrivee = arrivee;
		this.duree = duree;
		this.incident = incident;
	}
	
	public Station getDepart() {
		return depart;
	}
	public void setDepart(Station depart) {
		this.depart = depart;
	}
	public Station getArrivee() {
		return arrivee;
	}
	public void setArrivee(Station arrivee) {
		this.arrivee = arrivee;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public boolean isIncident() {
		return incident;
	}
	public void setIncident(boolean incident) {
		this.incident = incident;
	}	
	
	public boolean isLinkedTo(Station station) {
		if(this.depart == station || this.arrivee == station) {
			return true;
		}else {
			return false;
		}
	}

}
