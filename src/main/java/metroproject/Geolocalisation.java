package metroproject;

import java.awt.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class Geolocalisation {

    private Map<String,Station> stations;

    public Geolocalisation(Map stations) {
        this.stations = stations;
    }

    public Station trouveStationProche(Utilisateur u) {
        Point depart = new Point(u.getX(),u.getY());
        Point dest = null;
        Station laPlusProche = null;
        int distance = -1;

        for (Map.Entry<String, Station> entry : stations.entrySet())
        {
            Station stationTmp = entry.getValue();

            if (stationTmp.getX() != -1 && stationTmp.getY() != -1) {
                dest = new Point(stationTmp.getX(), stationTmp.getY());
                int distanceTmp = (int) depart.distanceSq(dest);

                if (laPlusProche == null || distance > distanceTmp) {
                    distance = distanceTmp;
                    laPlusProche = stationTmp;
                }
            }
        }

        return laPlusProche;
    }
}
