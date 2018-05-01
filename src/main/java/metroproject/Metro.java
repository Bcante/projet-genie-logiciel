package metroproject;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.util.Pair;

@SuppressWarnings("serial")
public class Metro implements Serializable{

	private String nomVille;
	private HashMap<String,Station> stations;
	private HashMap<String,Ligne> lignes;
	private ArrayList<Rail> rails;
	private Geolocalisation geo;
	private InfoTrafic infoTrafic;
	
	public Metro(String nomVille) {
		this(nomVille,new HashMap<String,Station>(),new ArrayList<Rail>());
	}

	
	public Metro(String nomVille,HashMap<String,Station> stations, ArrayList<Rail> rails) {
		this.nomVille = nomVille;
		this.stations = stations;
		this.rails = rails;
		this.geo = new Geolocalisation(stations);
		this.infoTrafic = new InfoTrafic(stations);
	}

	public HashMap<String,Station> getStations() {
		return stations;
	}
	
	
	
	public String getNomVille() {
		return nomVille;
	}


	public HashMap<String, Ligne> getLignes() {
		return lignes;
	}


	public void setLignes(HashMap<String, Ligne> lignes) {
		this.lignes = lignes;
	}


	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}


	public void setGeo(Geolocalisation geo) {
		this.geo = geo;
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
	
	
	/*Vérifie s'il existe un chemin quelconque entre les deux stations*/
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
					System.out.println(sauvegarde.get(0).getNomStation());
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
	
	/* Recherche du chemin le plus court entre deux stations. Implémente l'algorithme A*.
	 * Suppose qu'un chemin existe entre s1 et s2.*/
	public ArrayList<Station> shortestPath(Station s1, Station s2){
		/*initialisations*/
		ArrayList<Station> vu = new ArrayList<Station>(); //les stations déjà développées
		ArrayList<ArrayList<Station>> queue=new ArrayList<ArrayList<Station>>(); //notre queue de chemins
		PathComparator comparator=new PathComparator(); 
		boolean done=false;
		ArrayList<Station> ini=new ArrayList<Station>();
		ini.add(s1);
		queue.add(ini);
		vu.add(s1);
		ArrayList<Station> chemin = new ArrayList<Station>();
		ArrayList<Station> sauvegarde=new ArrayList<Station>();
		while(!done && queue.size()!=0) {
			/*dérouler les fils*/
			chemin=queue.get(0); //on prend le premier chemin de la queue
			queue.remove(0); // et on le retire de la queue
			//former les sucesseurs à ce chemin
			sauvegarde=chemin.get(chemin.size()-1).getConnections(); //save des connections du dernier chemin
			while (sauvegarde.size()!=0) { //développer les successeurs
				if (vu.indexOf(sauvegarde.get(0))==-1) { //la station n'a pas été développée
					ArrayList<Station> newChemin=new ArrayList<>(chemin);
					newChemin.add(sauvegarde.get(0));
					queue.add(newChemin); //nouveau chemin ajouté
				}
				sauvegarde.remove(0);
			}
			vu.add(chemin.get(chemin.size()-1)); // on ajoute à vu le noeud développé
			//on classe la queue
			queue.sort(comparator);
			//supprimer les chemins nuls de la liste
			queue=delLongPaths(queue);
			//si le premier chemin atteint la destination, on s'arrête là
			if (queue.get(0).contains(s2)) {
				done=true;
			}
		}
		if (!done) {
			return null;
		}
		else {
			return queue.get(0);
		}
		
	}
	

	private ArrayList<ArrayList<Station>> delLongPaths(ArrayList<ArrayList<Station>> queue){
		Station finalS=null;
		Iterator<ArrayList<Station>> iteration=queue.iterator();
		ArrayList<Station> chemin;
		PathComparator comparator=new PathComparator();
		while(iteration.hasNext()) {
			chemin=iteration.next();
			finalS=chemin.get(chemin.size()-1); //station finale du chemin
			
			for(int i=0;i<queue.size();i++) {
				if ((queue.get(i).contains(finalS)) && (comparator.compare(chemin, queue.get(i))==-1)) {
					iteration.remove();
				}
			}
		}
		return queue;
	}
	
	/*Vérifie s'il existe un lien direct (un rail) entre les deux stations*/
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

	public InfoTrafic getInfoTrafic() {
		return infoTrafic;
	}

	public String trouveStationPlusProche(Utilisateur u) {

		Station res = geo.trouveStationProche(u);
		return (res == null ? null : res.getNomStation());
	}
	
	
}
