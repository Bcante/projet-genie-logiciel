package metroproject;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Station implements Serializable{
	
	private String nomStation;
	private ArrayList<Rail> rails;
	private int x,y;
	private int[] stations;

    public Station(int x, int y, String nomStation, int[]stations) {
        this.x = x;
        this.y = y;
        this.nomStation = nomStation;
        this.rails = new ArrayList<Rail>();
        this.stations=stations;
    }

    public Station(String nomStation, int[]stations) {
		this(-1,-1,nomStation,stations);
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
			else {
				if (rail.getArrivee() == this) {
					listResult.add(rail.getDepart());
				}
			}
		}
		return listResult;
	}
	
	/* suppose que la connection existe*/
	public Rail getConnectionTo(Station s) {
		Rail connectionTo=null;
		for (Rail r: rails) {
			if (r.getArrivee().equals(s) || r.getDepart().equals(s)) {
				connectionTo=r;
			}
		}
		return connectionTo;
	}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void getLignes() {
    	
    }
}
