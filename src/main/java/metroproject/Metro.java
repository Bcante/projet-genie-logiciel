package metroproject;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Set;

public class Metro implements Serializable{

	private HashMap<String,Station> stations;
	private ArrayList<Rail> rails;
	private Geolocalisation geo;
	private HashMap<String, Ligne> lignes;
	
	public Metro() {
		this(new HashMap<String,Station>(),new ArrayList<Rail>(), new HashMap <String,Ligne>());
	}

	
	public Metro(HashMap<String,Station> stations, ArrayList<Rail> rails, HashMap <String,Ligne> lignes) {
		this.stations = stations;
		this.rails = rails;
		this.lignes=lignes;
		this.geo = new Geolocalisation(stations);
	}

	public HashMap<String,Station> getStations() {
		return stations;
	}
	
	public Station getStation(String nomStation) {
		return this.stations.get(nomStation);
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
	
	public HashMap<String, Ligne> getLignes() {
		return lignes;
	}


	public void setLignes(HashMap<String, Ligne> lignes) {
		this.lignes = lignes;
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
	
	private void addStation(Station s) {
		this.stations.put(s.getNomStation(), s);
	}
	
	/*ajoute une ligne à un station déjà existante et la met à jour dans les 
	 * éventuelles lignes existantes*/
	private Station addLigneToStation(Station s, String numero){
		//modifier la station dans le metro
		Station sExistante=stations.get(s.getNomStation());
		sExistante.addLigne(numero);
		Set<Entry<String, Ligne>> setLignes = lignes.entrySet();
		Iterator<Entry<String, Ligne>> lignesIt = setLignes.iterator();
		Entry<String, Ligne> ligne = lignesIt.next();
		/* vérification de toutes les lignes */
		while(lignesIt.hasNext()) {
		    Ligne ligneObj = ligne.getValue();
		    if (ligneObj.hasStation(ligne.getKey())){
		    	Station statFromLigne=ligneObj.getStation(s.getNomStation());
		    	statFromLigne.addLigne(numero);
		    }
		    ligne = lignesIt.next();
		}
		return sExistante; /* retour pour la fonction addLigne
		pour que la station soit directement ajoutée à la nouvelle ligne avec les autres lignes qu'elle dessert*/
	}
	
	public void addLigne(ArrayList<String> ligne, String numero) {
		LinkedHashSet<Station> stationsLigne=new LinkedHashSet<Station>();
		for (String l: ligne) {
			Station s=new Station(l);
			Station test=stations.get(l);
			//si la station existe déjà, on lui ajoute un numéro de station supplémentaire
			if (test != null) {
				s=addLigneToStation(s, numero);
			}
			else {
				addStation(s);
			}
			stationsLigne.add(s);
		}
		Ligne ligneF=new Ligne(numero, stationsLigne);
		this.lignes.put(numero,ligneF);
	}
	
	
	//Vérifie s'il existe un lien direct (un rail) entre les deux stations
	public boolean areConnected(Station s1, Station s2) {
		//Inspiré de l'algorithme de recherche A*
		ArrayList<Station> parcours = new ArrayList<Station>(); //les stations à parcourir
		ArrayList<Station> vu = new ArrayList<Station>(); //les stations déjà parcourues
		boolean existe=false;
		parcours.add(s1); //point de départ
		while ((parcours.size()!=0)&(existe==false)) {
		ArrayList<Station> sauvegarde=parcours.get(0).getConnections();
			while(sauvegarde.size()!=0) {
				if (vu.indexOf(sauvegarde.get(0))==-1) { //la station n'a pas été parcourue
					parcours.add(sauvegarde.get(0)); //la station est dans le parcours
					vu.add(sauvegarde.get(0)); //elle a été vue 
				}
				if(sauvegarde.get(0).equals(s2)) {
					existe=true;
				}
				sauvegarde.remove(0);
			}
			parcours.remove(0);
		}
		return existe;
	}
	
	//Vérifie s'il existe un chemin quelconque entre les deux stations
	public boolean areLinked(Station s1, Station s2) {
		boolean res = false;
		for(Rail rail : this.rails) {
			if (rail.isLinkedTo(s1) && rail.isLinkedTo(s2)) {
				res = true;
				break;
			}
		}
		return res;

	}
	
	public void delRail(Station s1, Station s2) {
		for(Rail rail : this.rails) {
			if(rail.isLinkedTo(s1) && rail.isLinkedTo(s2)) {
				s1.removeRailToStation(rail);
				s2.removeRailToStation(rail);
				this.rails.remove(rail);
				break;
			}
		}
	}
	
	public void delStation(Station station) {
		if(this.rails.size() > 0) {
			for(Rail rail : this.rails) {
				if(rail.isLinkedTo(station)) {
					rail.getArrivee().removeRailToStation(rail);
					rail.getDepart().removeRailToStation(rail);
					this.stations.remove(station.getNomStation());
					this.rails.remove(rail);
					break;
				}
			}
		}else {
			this.stations.remove(station.getNomStation());
		}
	}

	public Geolocalisation getGeo() {
		return geo;
	}

	public String trouveStationPlusProche(Utilisateur u) {

		Station res = geo.trouveStationProche(u);
		return (res == null ? null : res.getNomStation());
	}
	
	
}
