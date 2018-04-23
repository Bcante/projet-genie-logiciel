package test;

import static org.junit.jupiter.api.Assertions.*;

import metroproject.Geolocalisation;
import metroproject.Metro;
import metroproject.Station;
import metroproject.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class testGeolocalisation {

    HashMap<String,Station> stations = new HashMap<>();
    Geolocalisation g = new Geolocalisation(stations);


    @BeforeEach
    public void constructeur(){
        Station s1 = new Station(5,5,"5 5");
        Station s2 = new Station(5,100,"5 100");
        Station s3 = new Station(-1,-1,"-1 -1");
        Station s4 = new Station(20,20,"20 20");
        Station s5 = new Station(10,10,"10 10");

        stations.put("5 5",s1);
        stations.put("5 100",s2);
        stations.put("-1 -1",s3);
        stations.put("20 20",s4);
        stations.put("10 10",s5);
    }

    @Test
    public void memeEndroit() {
        Utilisateur u = new Utilisateur(5,5,"Bob");
        String res = g.trouveStationProche(u);
        assertEquals(res,"5 5");
    }

    @Test
    public void casSimple() {
        Utilisateur u = new Utilisateur(21,17,"Bob");
        String res = g.trouveStationProche(u);
        assertEquals(res,"20 20");
    }

}
