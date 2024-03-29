package metroproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@SuppressWarnings("serial")
public class Metro implements Serializable {

	private String nomVille;
	private HashMap<String, Station> stations;
	private HashMap<String, Ligne> lignes;
	private ArrayList<Rail> rails;
	private Geolocalisation geo;
	private InfoTrafic infoTrafic;

	public Metro(String nomVille) {
		this(nomVille, new HashMap<String, Station>(), new ArrayList<Rail>());
	}

	public Metro(String nomVille, HashMap<String, Station> stations, ArrayList<Rail> rails) {
		this.nomVille = nomVille;
		this.stations = stations;
		this.rails = rails;
		this.geo = new Geolocalisation(stations);
		this.infoTrafic = new InfoTrafic(stations);
	}

	public InfoTrafic getInfoTrafic() {
		return infoTrafic;
	}

	public void setInfoTrafic(InfoTrafic infoTrafic) {
		this.infoTrafic = infoTrafic;
	}

	public Geolocalisation getGeo() {
		return geo;
	}

	public HashMap<String, Station> getStations() {
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

	public void setStations(HashMap<String, Station> stations) {
		this.stations = stations;
	}

	public ArrayList<Rail> getRails() {
		return rails;
	}

	public void setRails(ArrayList<Rail> rails) {
		this.rails = rails;
	}

	public void addRail(Station start, Station destination, int duree, boolean incident) {
		Rail railToAdd = new Rail(start, destination, duree, incident);
		this.rails.add(railToAdd);
	}

	public void addStation(String nom) {
		Station stationToAdd = new Station(nom);
		this.stations.put(nom, stationToAdd);
	}

	/* Vérifie s'il existe un chemin quelconque entre les deux stations */
	public boolean areConnected(Station s1, Station s2) {
		// Inspiré de l'algorithme de recherche A*
		ArrayList<Station> parcours = new ArrayList<Station>(); // les stations à parcourir
		ArrayList<Station> vu = new ArrayList<Station>(); // les stations déjà parcourues
		boolean existe = false;
		parcours.add(s1); // point de départ
		while ((parcours.size() != 0) & (existe == false)) {
			ArrayList<Station> sauvegarde = parcours.get(0).getConnections(true);
			while (sauvegarde.size() != 0) {
				if (vu.indexOf(sauvegarde.get(0)) == -1) { // la station n'a pas été parcourue
					parcours.add(sauvegarde.get(0)); // la station est dans le parcours
					vu.add(sauvegarde.get(0)); // elle a été vue
				}
				if (sauvegarde.get(0).equals(s2)) {
					existe = true;
				}
				sauvegarde.remove(0);
			}
			parcours.remove(0);
		}
		return existe;
	}

	/* Vérifie s'il existe un lien direct (un rail) entre les deux stations */
	public boolean areLinked(Station s1, Station s2) {
		boolean res = false;
		for (Rail rail : this.rails) {
			if (rail.isLinkedTo(s1) && rail.isLinkedTo(s2)) {
				res = true;
				break;
			}
		}
		return res;

	}

	public void delRail(Station s1, Station s2) {
		for (Rail rail : this.rails) {
			if (rail.isLinkedTo(s1) && rail.isLinkedTo(s2)) {
				s1.removeRailToStation(rail);
				s2.removeRailToStation(rail);
				this.rails.remove(rail);
				break;
			}
		}
	}

	public void delStation(Station station) {
		if (this.rails.size() > 0) {
			for (Rail rail : this.rails) {
				if (rail.isLinkedTo(station)) {
					rail.getArrivee().removeRailToStation(rail);
					rail.getDepart().removeRailToStation(rail);
					this.stations.remove(station.getNomStation());
					this.rails.remove(rail);
					break;
				}
			}
		} else {
			this.stations.remove(station.getNomStation());
		}
	}

	public Ligne getLigneBetweenStation(Station station1, Station station2) {
		Ligne result = null;
		for (String key : this.lignes.keySet()) {
			Ligne ligne = this.lignes.get(key);

			if (ligne.getStationsLigne().contains(station1)) {
				Iterator it = ligne.getStationsLigne().iterator();
				while (it.hasNext()) {
					Station firstStationInList = (Station) it.next();
					if (firstStationInList.equals(station1)) {
						Station nextstation = (Station) it.next();
						if (nextstation.equals(station2)) {
							result = ligne;
							break;
						}
					} else if (firstStationInList.equals(station2)) {
						Station nextstation = (Station) it.next();
						if (nextstation.equals(station1)) {
							result = ligne;
							break;
						}
					}

				}
			}
		}
		return result;
	}
}
