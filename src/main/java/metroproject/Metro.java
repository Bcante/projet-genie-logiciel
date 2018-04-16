package metroproject;

import java.util.ArrayList;

public class Metro {

	private ArrayList<Station> stations;
	private ArrayList<Rail> rails;
	
	public Metro() {
		this.stations = new ArrayList<Station>();
		this.rails = new ArrayList<Rail>();
	}

	
	public Metro(ArrayList<Station> stations, ArrayList<Rail> rails) {
		this.stations = stations;
		this.rails = rails;
	}

	public ArrayList<Station> getStations() {
		return stations;
	}

	public void setStations(ArrayList<Station> stations) {
		this.stations = stations;
	}

	public ArrayList<Rail> getRails() {
		return rails;
	}

	public void setRails(ArrayList<Rail> rails) {
		this.rails = rails;
	}
	
	public void addRail(Station s1, Station s2, int duree) {
	}
	
	public void addStation(String nom) {
		
	}
	
	//Vérifie s'il existe un lien direct (un rail) entre les deux stations
	public boolean areConnected(Station s1, Station s2) {
		return false;
	}
	
	//Vérifie s'il existe un chemin quelconque entre les deux stations
	public boolean areLinked(Station s1, Station s2) {
		return false;
	}
	
	public void delRail(Station s1, Station s2) {
		
	}
	
	public void delStation(Station s) {
		
	}

	
	
}
