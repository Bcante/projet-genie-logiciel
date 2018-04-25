package test;

import static org.junit.jupiter.api.Assertions.*;

import metroproject.Metro;
import metroproject.Station;
import metroproject.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class testGeolocalisation {


    HashMap<String,Station> simpleStations = new HashMap<>();
    Metro simpleM = new Metro("simpleM",simpleStations,null);

    /* Metro pour cas particulier*/
    HashMap<String,Station> penibleStation = new HashMap<>();
    Metro penibleM = new Metro("penibleM",penibleStation, null);

    @BeforeEach
    public void constructeur(){
        Station s1 = new Station(5,5,"5 5");
        Station s2 = new Station(5,100,"5 100");
        Station s3 = new Station(-1,-1,"-1 -1");
        Station s4 = new Station(20,20,"20 20");
        Station s5 = new Station(10,10,"10 10");

        simpleStations.put("5 5",s1);
        simpleStations.put("5 100",s2);
        simpleStations.put("-1 -1",s3);
        simpleStations.put("20 20",s4);
        simpleStations.put("10 10",s5);
    }

    @Test
    public void memeEndroit() {
        Utilisateur u = new Utilisateur(5,5,"Bob");
        String res = simpleM.trouveStationPlusProche(u);
        assertEquals(res,"5 5");
    }

    @Test
    public void casSimple() {
        Utilisateur u = new Utilisateur(21,17,"Bob");
        String res = simpleM.trouveStationPlusProche(u);
        assertEquals(res,"20 20");
    }

    @Test
    public void presDuMauvais() {
        Utilisateur u = new Utilisateur(0,0,"Bob");
        String res = simpleM.trouveStationPlusProche(u);
        assertEquals(res,"5 5");
    }

    @Test
    public void pasDeChoixPossible() {
        Utilisateur u = new Utilisateur(0,0,"Bob");
        String res = penibleM.trouveStationPlusProche(u);
        assertNull(res);
    }

}
