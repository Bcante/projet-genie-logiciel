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
        p=new Plan();
        m=p.createParisMetro();
        info = new InfoTrafic(m.getStations());
        geo = new Geolocalisation(m.getStations());
        pf = new PathFinder();
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
        if (!existLineOrStation(nomStation,"STATION")) {
            return "Station inconnue.";
        }
        LinkedHashSet<Rail> perturbations = info.getRailIssueLine(m.getLignes().get(nomStation));

        if (perturbations.isEmpty()) {
            return "Aucun problème pour la station "+nomStation;
        }
        String res;
        StringBuilder builder = new StringBuilder("Incidents sur les rails allant vers ");
        Iterator<Rail> it = perturbations.iterator();
        while (it.hasNext()) {
            Rail r = it.next();
            String dest;
            dest = r.getDepart().getNomStation().equals(nomStation)? r.getArrivee().getNomStation() : r.getDepart().getNomStation();
            builder.append(dest+", ");
        }
        res = builder.toString();
        return res.substring(0,res.length()-2);
    }

    public String getLineIssues(String nomLigne) {
        if (!existLineOrStation(nomLigne, "LIGNE")) {
            return "Ligne inconnue.";
        }
        Ligne li = m.getLignes().get(nomLigne);
        LinkedHashSet<Rail> perturbations = info.getRailIssueLine(li);

        if (perturbations.isEmpty()) {
            return "Pas de problème sur la ligne "+nomLigne;
        }

        Iterator<Rail> it = perturbations.iterator();

        StringBuilder rep = new StringBuilder("Perturbations sur les rails suivants: ");

        while (it.hasNext()) {
            Rail r = it.next();
            rep.append(r.getDepart()+" "+r.getArrivee()+", ");
        }

        return rep.toString();
    }

    private boolean existLineOrStation(String nom, String type) {
        if (type.equals("STATION")) {
            Station s = m.getStations().get(nom);
            return (s==null ? false : true);
        }
        else if (type.equals("LIGNE")) {
            Ligne l = m.getLignes().get(nom);
            return (l==null ? false : true);
        }
        else return false;
    }

    /* Avoir un chemin */
    public String shortestPath(Station debut, Station fin) {
    	ArrayList<Station>res=pf.shortestPath(debut, fin);
    	//Depart: Station ___
    	//Ligne 3 Direction blabla jusqu'à
    	String defpath="Départ: Station "+res.get(0).getNomStation();
    	int i=1;
    	while (i<res.size()) {
    		
    	}
    	return defpath;
    }

    public void routeWithMultipleStation(Station debut, Station fin, Station intermediaire) {
    	ArrayList<Station>res=pf.customPath(debut, fin, intermediaire);
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
