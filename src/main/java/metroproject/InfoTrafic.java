package metroproject;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

@SuppressWarnings("serial")
public class InfoTrafic implements Serializable{
    private Map<String,Station> stations;

    public InfoTrafic (Map<String,Station> stations) {
        this.stations = stations;
    }

    public boolean isIncidentBetweenStations(Station s1, Station s2){
        Rail r1 = s1.getConnectionTo(s2);
        return r1.isIncident();
    }

    /* Pour une ligne donnée, on renvoie tous les rails qui ont un accident */
	public LinkedHashSet<Rail> getRailIssueLine(Ligne ligne) {
        LinkedHashSet<Rail> problemes = new LinkedHashSet<Rail>();
        Iterator<Station> it = ligne.getStationsLigneAller().iterator();

        if (ligne.getStationsLigneAller().size() <= 1){ // il faut au moins 2 stations pour qu'il y a un problème de rail
            return problemes;
        }

        Station depart = it.next();
        Station dest = it.next();
        if (depart == null || dest == null) {
            return problemes; // Ligne avec 1 seule station = problème
        }

        while (it.hasNext()) {
            if (isIncidentBetweenStations(depart, dest)) {
                problemes.add(depart.getConnectionTo(dest));
            }
            /* Le départ devient la destination d'avant, et la nouvelle destination est la prochaine sur la ligne */
            depart=dest;
            dest=it.next();
        }
        return problemes;
    }

    public LinkedHashSet<Rail> getRailsIssueOfStation(Station s) {
        LinkedHashSet<Rail> problemes = new LinkedHashSet<Rail>();
        for (Rail rail : s.getRails()) {
            if (rail.isIncident()) {
                problemes.add(rail);
            }
        }
        return problemes;
    }

    public Map<String, Station> getStations() {
        return stations;
    }
}
