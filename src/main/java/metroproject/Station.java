package metroproject;

import java.io.Serializable;
import java.util.ArrayList;

public class Station implements Serializable{
	
	String nomStation;
	ArrayList<Rail> rails;
	
	public Station(String nomStation) {
		this.nomStation = nomStation;
		this.rails = new ArrayList<Rail>();
	}

	public Station(ArrayList<Rail> rails) {
		this.rails = rails;
	}
	
	public ArrayList<Rail> getRails() {
		return rails;
	}

	public void setRails(ArrayList<Rail> rails) {
		this.rails = rails;
	}
	
	
	public String getNomStation() {
		return nomStation;
	}

	public void setNomStation(String nomStation) {
		this.nomStation = nomStation;
	}

	public void addRailtoStation(Rail rail) {
		this.rails.add(rail);
	}
	
	public void removeRailToStation(Rail rail) {
		this.rails.remove(rail);
	}
	
	//retournes les stations directements liées à la station
	public ArrayList<Station> getConnections(){
		ArrayList<Station> listResult = new ArrayList<Station>();
		for(Rail rail : rails) {
			if(rail.getDepart() == this) {
				listResult.add(rail.getArrivee());
			}
		}
		return listResult;
	}	

}
