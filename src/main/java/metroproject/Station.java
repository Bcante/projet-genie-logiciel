package metroproject;

import java.util.ArrayList;

public class Station {
	ArrayList<Rail> rails;
	
	public Station() {
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
	
	//retournes les stations directements liées à la station
	public ArrayList<Station> getConnections(){
		return new ArrayList<Station>();
	}	

}
