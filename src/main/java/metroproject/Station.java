package metroproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

@SuppressWarnings("serial")
public class Station implements Serializable{
	
	private String nomStation;
	private ArrayList<Rail> rails;
	private int x,y;
	private HashSet<Integer> lines;
	//private int[] lines;

    public Station(int x, int y, String nomStation, int[]lines) {
        this.x = x;
        this.y = y;
        this.nomStation = nomStation;
        this.rails = new ArrayList<Rail>();
        this.lines=new HashSet<Integer>();
        for (int i=0;i<lines.length;i++) {
        	this.lines.add(lines[i]);
        }
    }
    
    public HashSet<Integer> getLines() {
		return lines;
	}

	public Station(int x, int y, String nomStation) {
        this.x = x;
        this.y = y;
        this.nomStation = nomStation;
        this.rails = new ArrayList<Rail>();
        lines=new HashSet<Integer>();
    }

    public Station(String nomStation, int[]stations) {
		this(-1,-1,nomStation,stations);
	}
    
    public Station(String nomStation) {
		this(-1,-1,nomStation,new int[] {0});
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
	public ArrayList<Station> getConnections(boolean workingOnly){
		ArrayList<Station> listResult = new ArrayList<Station>();
		for(Rail rail : rails) {
			if (!rail.isIncident() || !workingOnly) {
				if (rail.getDepart() == this) {
					listResult.add(rail.getArrivee());
				} else {
					if (rail.getArrivee() == this) {
						listResult.add(rail.getDepart());
					}
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
  
}
