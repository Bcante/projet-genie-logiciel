package metroproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Metro implements Serializable{

	private HashMap<String,Station> stations;
	private ArrayList<Rail> rails;
	
	public Metro() {
		this(new HashMap<String,Station>(),new ArrayList<Rail>());
	}

	
	public Metro(HashMap<String,Station> stations, ArrayList<Rail> rails) {
		this.stations = stations;
		this.rails = rails;
	}

	public HashMap<String,Station> getStations() {
		return stations;
	}

	public void setStations(HashMap<String,Station> stations) {
		this.stations = stations;
	}

	public ArrayList<Rail> getRails() {
		return rails;
	}

	public void setRails(ArrayList<Rail> rails) {
		this.rails = rails;
	}
	
	public void addRail(Station start, Station destination, int duree, boolean incident) {
		Rail railToAdd = new Rail(start,destination, duree,incident);
		this.rails.add(railToAdd);
		start.addRailtoStation(railToAdd);
		destination.addRailtoStation(railToAdd);
	}
	
	public void addStation(String nom) {
		Station stationToAdd = new Station(nom);
		this.stations.put(nom,stationToAdd);
	}
	
	//Vérifie s'il existe un lien direct (un rail) entre les deux stations
	public boolean areConnected(Station s1, Station s2) {
		boolean res = false;
		for(Rail rail : this.rails) {
			if (rail.isLinkedTo(s1) && rail.isLinkedTo(s2)) {
				res = true;
				break;
			}
		}
		return res;
	}
	
	//Vérifie s'il existe un chemin quelconque entre les deux stations
	public boolean areLinked(Station s1, Station s2) {
		return false;
	}
	
	public void delRail(Station s1, Station s2) {
		for(Rail rail : this.rails) {
			if(rail.isLinkedTo(s1) && rail.isLinkedTo(s2)) {
				s1.removeRailToStation(rail);
				s2.removeRailToStation(rail);
				this.rails.remove(rail);
			}
		}
	}
	
	public void delStation(Station station) {
		for(Rail rail : this.rails) {
			if(rail.isLinkedTo(station)) {
				rail.getArrivee().removeRailToStation(rail);
				rail.getDepart().removeRailToStation(rail);
				this.stations.remove(station);
				this.rails.remove(rail);
			}
		}
	}

	
	
}
