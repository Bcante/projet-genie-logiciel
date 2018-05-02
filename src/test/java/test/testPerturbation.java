package test;

import Main.IHM;
import Main.Plan;
import metroproject.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

public class testPerturbation {
	static IHM i;
	static Metro m;
	static Plan p=new Plan();
	static Ligne l1;
	static InfoTrafic info;
	static Station s1;

	@BeforeAll
	static void init() {
		i = new IHM();
		info = i.getInfo();
		m = i.getM();
	}

	@BeforeEach
	void beforeEach() {

		m = i.getM();
		i = new IHM();
		l1 = m.getLignes().get("1");
		info = i.getInfo();
		s1 = m.getStation("Bourse");
	}

	@Test
	@DisplayName("Info entre 2 stations: Aucun problème")
	void stationsAucunPb() {
		Iterator<Station> it = l1.getStationsLigne().iterator();
		Station s2, s3;
		s2 = it.next();
		s3 = it.next();
		assertFalse(info.isIncidentBetweenStations(s2,s3));
	}

	@Test
	@DisplayName("Info entre 2 stations: Aucun problème")
	void stations1Pb() {
		Iterator<Station> it = l1.getStationsLigne().iterator();
		Station s2, s3;
		s2 = it.next();
		s3 = it.next();
		Rail rail1 = s2.getConnectionTo(s3);
		rail1.setIncident(true);
		assertTrue(info.isIncidentBetweenStations(s2,s3));
	}

	@Test
	@DisplayName("Info sur ligne: Aucun problème")
	void ligneAucunPb() {
		System.out.println();
		assertEquals(info.getRailIssueLine(l1).size(),0);
	}
	
	@Test
	@DisplayName("Info sur ligne: 1 rail accidenté")
	void lignePb1Rail() {
		Iterator<Station> it = l1.getStationsLigne().iterator();
		Station s2, s3;
		s2 = it.next();
		s3 = it.next();
		Rail rail1 = s2.getConnectionTo(s3);

		rail1.setIncident(true);
		assertEquals(info.getRailIssueLine(l1).size(),1);
	}

	@Test
	@DisplayName("Info sur ligne: Une station ou moins")
	void ligne1Station() {
		Ligne miniLigne = new Ligne("miniligne");
		LinkedHashSet<Station> listL1 = new LinkedHashSet<Station>();
		listL1.add(new Station("lele"));
		assertEquals(0,info.getRailIssueLine(miniLigne).size());
		miniLigne.setStationsLigneAller(listL1);
		assertEquals(0,info.getRailIssueLine(miniLigne).size());
	}

	@Test
	@DisplayName("Info sur station: aucun rail accidenté")
	void stationAucunPb() {
		assertEquals(info.getRailsIssueOfStation(s1).size(),0);

	}

	@Test
	@DisplayName("Info sur station: 1 rail accidenté")
	void station1Pb() {
		Rail r = s1.getConnectionTo(m.getStation("FranklinDRoosvlet"));
		r.setIncident(true);
		assertEquals(info.getRailsIssueOfStation(s1).size(),1);
	}



}
