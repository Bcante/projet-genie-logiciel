package metroproject;

import java.io.Serializable;
import java.util.ArrayList;

public class Station implements Serializable{
	
	private String nomStation;
	private ArrayList<Rail> rails;
	private ArrayList<String> lignes;
	
	private int x,y;

    public Station(int x, int y, String nomStation) {
        this.x = x;
        this.y = y;
        this.nomStation = nomStation;
        this.rails = new ArrayList<Rail>();
    }
    
    public Station(String nomStation, ArrayList<String> lignes) {
    	this.nomStation = nomStation;
        this.rails = new ArrayList<Rail>();
        this.lignes=lignes;
    }
    
    public Station(String nomStation, ArrayList<String> lignes, ArrayList<Rail> rails) {
    	this.nomStation = nomStation;
        this.rails = rails;
        this.lignes = lignes;
    }

    public Station(String nomStation) {
		this(-1,-1,nomStation);
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
	
	public ArrayList<String> getLignes() {
		return lignes;
	}

	public void setLignes(ArrayList<String> lignes) {
		this.lignes = lignes;
	}
	
	public void addLigne(String ligne) {
		lignes.add(ligne);
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public boolean equals(Station s) {
    	
    	return false;
    }
}
