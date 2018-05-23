package Main;

import metroproject.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

/*
Classe contenant les méthodes directement appelables par un utilisateur
 */
public class IHM {

	private Plan p;
	private Metro m;
	private InfoTrafic info;
	private Geolocalisation geo;
	private PathFinder pf;

	public IHM() {
		p = new Plan();
		m = p.createParisMetro();
		info = new InfoTrafic(m.getStations());
		geo = new Geolocalisation(m.getStations());
		pf = new PathFinder();
	}

	public IHM(String code) {
		this();
		crashParty();
	}


	public void crashParty() {
		Rail r1 = m.getStation("Esplanade").
				getConnectionTo(m.getStation("FranklinDRoosvlet"));
		Rail r2 = m.getStation("ColonnelFabien").
				getConnectionTo(m.getStation("FranklinDRoosvlet"));
		r1.setIncident(true);
		r2.setIncident(true);
	}

	/* GeoLoc */
	public String findClosestStation(Utilisateur u) {
		Station res = geo.findCloseStation(u);
		return (res == null ? null : res.getNomStation());
	}

	public void updatePosition(Utilisateur u, int nvX, int nvY) {
		u.setX(nvX);
		u.setY(nvY);
	}

	/* Perturbations */
	public String getStationIssues(String nomStation) {
		if (!existLineOrStation(nomStation, "STATION")) {
			return "Station inconnue.";
		}
		LinkedHashSet<Rail> perturbations = info.getRailsIssueOfStation(m.getStation(nomStation));

		if (perturbations.isEmpty()) {
			return "Aucun problème pour la station " + nomStation;
		}
		String res;
		StringBuilder builder = new StringBuilder("Incidents sur les rails allant vers ");
		Iterator<Rail> it = perturbations.iterator();
		while (it.hasNext()) {
			Rail r = it.next();
			String dest;
			dest = r.getDepart().getNomStation().equals(nomStation) ? r.getArrivee().getNomStation()
					: r.getDepart().getNomStation();
			builder.append(dest + ", ");
		}
		res = builder.toString();
		return res.substring(0, res.length() - 2);
	}

	public String getLineIssues(String nomLigne) {
		if (!existLineOrStation(nomLigne, "LIGNE")) {
			return "Ligne inconnue.";
		}
		Ligne li = m.getLignes().get(nomLigne);
		LinkedHashSet<Rail> perturbations = info.getRailIssueLine(li);

		if (perturbations.isEmpty()) {
			return "Pas de problème sur la ligne " + nomLigne;
		}

		Iterator<Rail> it = perturbations.iterator();

		StringBuilder rep = new StringBuilder("Perturbations sur les rails suivants: ");

		while (it.hasNext()) {
			Rail r = it.next();
			rep.append(r.getDepart() + " " + r.getArrivee() + ", ");
		}

		return rep.toString();
	}

	public boolean existLineOrStation(String nom, String type) {
		if (type.equals("STATION")) {
			Station s = m.getStations().get(nom);
			return (s == null ? false : true);
		} else if (type.equals("LIGNE")) {
			Ligne l = m.getLignes().get(nom);
			return (l == null ? false : true);
		} else
			return false;
	}

	/* Avoir un chemin */
	public String shortestPath(Station debut, Station fin) {
		ArrayList<Station> res = pf.shortestPath(debut, fin);
		return this.getItineraire(res);
	}

	public String routeWithMultipleStation(Station debut, Station fin, Station intermediaire) {
		ArrayList<Station> res = pf.customPath(debut, fin, intermediaire);
		return this.getItineraire(res);
	}
	
	public String leastLineChange(Station debut, Station fin) {
		ArrayList<Station> res = pf.leastLineChange(debut, fin);
		return this.getItineraire(res);
	}

	public String getItineraire(ArrayList<Station> res) {
		Station debut = res.get(0);
		Station fin = res.get(res.size()-1);
		String defpath = "Voici votre itinéraire de " + debut.getNomStation() + " vers " + fin.getNomStation() + "\n";
		int i = 0;
		int j;
		Ligne temp = m.getLigneBetweenStation(res.get(0), res.get(1));
		defpath += " - Prendre la ligne " + temp.getNumero() + " en direction de " + res.get(1).getNomStation() +"\n";
		while (i < res.size()) {
			if (i != res.size() - 1) {
				Station station = res.get(i);
				String nomStation = station.getNomStation();
				j = i + 1;
				Station nextStation = res.get(j);
				String nomNextStation = nextStation.getNomStation();
				Ligne ligne = m.getLigneBetweenStation(station, nextStation);
				if (!temp.equals(ligne)) {
					defpath += " - Descendre à " + nomStation + " et prendre la ligne " + ligne.getNumero() + " à destination de " + nomNextStation + "\n";
					temp = ligne;
				}
				if(fin.equals(nextStation)) {
					defpath += " - Restez sur cette ligne jusqu'à " + nomNextStation + ".\n";
				}
			}

			i++;
		}
		return defpath;
	}
	
	public Plan getP() {
		return p;
	}

	public Metro getM() {
		return m;
	}

	public InfoTrafic getInfo() {
		return info;
	}

	public Geolocalisation getGeo() {
		return geo;
	}

}
