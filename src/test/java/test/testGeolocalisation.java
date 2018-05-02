package test;

import static org.junit.jupiter.api.Assertions.*;

import metroproject.Geolocalisation;
import metroproject.Station;
import metroproject.Utilisateur;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class testGeolocalisation {


    static HashMap<String,Station> simpleStations = new HashMap<>();
    Geolocalisation g = new Geolocalisation(simpleStations);

    /* Metro pour cas particulier*/
    HashMap<String,Station> penibleStation = new HashMap<>();
    Geolocalisation g2 = new Geolocalisation(penibleStation);


    @BeforeAll
    public static void constructeur(){
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
        String res = g.findCloseStation(u).getNomStation();
        assertEquals(res,"5 5");
    }

    @Test
    public void casSimple() {
        Utilisateur u = new Utilisateur(21,17,"Bob");
        String res = g.findCloseStation(u).getNomStation();
        assertEquals(res,"20 20");
    }

    @Test
    public void presDuMauvais() {
        Utilisateur u = new Utilisateur(0,0,"Bob");
        String res = g.findCloseStation(u).getNomStation();
        assertEquals(res,"5 5");
    }

    @Test
    public void pasDeChoixPossible() {
        Utilisateur u = new Utilisateur(0,0,"Bob");
        assertNull(g2.findCloseStation(u));
    }

}
