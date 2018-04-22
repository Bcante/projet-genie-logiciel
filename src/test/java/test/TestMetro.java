package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import metroproject.Metro;
import metroproject.Station;

class TestMetro {
	

	Metro m = new Metro();
	
	@BeforeEach
	void beforeEach() {
		m.addStation("test");
		m.addStation("test2");
		m.addStation("test3");
	}
	

	@Test
	@DisplayName("Ajout d'une station")
	void addStationTest() {
		assertEquals("test", m.getStations().get(0).getNomStation());
	}
	
	@Test
	@DisplayName("Ajout d'un rail")
	void addRailTest() {
		Station s1 = m.getStations().get(0);
		Station s2 = m.getStations().get(1);
		m.addRail(s1,s2 , 5, false);
		assertEquals(s2, s1.getConnections().get(0));
	}
	
	@Test
	@DisplayName("Connexion directe entre deux stations")
	void areConnectedTest() {
		Station s1 = m.getStations().get(0);
		Station s2 = m.getStations().get(1);
		m.addRail(s1,s2 , 5, false);
		assertTrue(m.areConnected(s1, s2));
	}
	
	@Test
	@DisplayName("Pas de connexion directe entre deux stations")
	void areConnected2Test() {
		Station s1 = m.getStations().get(0);
		Station s2 = m.getStations().get(1);
		Station s3 = m.getStations().get(2);
		m.addRail(s1,s3 , 5, false);
		assertFalse(m.areConnected(s1, s2));
	}
	
	
	@Test
	@DisplayName("areLinked avec lien direct")
	void areLinkedTest() {
		Station s1 = m.getStations().get(0);
		Station s2 = m.getStations().get(1);
		m.addRail(s1,s2 , 5, false);
		assertTrue(m.areLinked(s1, s2));
	}
	
	@Test
	@DisplayName("areLinked avec lien distancé")
	void areLinked2Test() {
		Station s1 = m.getStations().get(0);
		Station s2 = m.getStations().get(1);
		Station s3 = m.getStations().get(2);
		m.addRail(s1,s2 , 5, false);
		m.addRail(s2, s3, 1, false);
		assertTrue(m.areLinked(s1, s3));
	}
	
	@Test
	@DisplayName("Suppression rail")
	void deleteRailTest() {
		Station s1 = m.getStations().get(0);
		Station s2 = m.getStations().get(1);
		m.addRail(s1,s2 , 5, false);
		m.delRail(s1, s2);
		assertFalse(m.areConnected(s1, s2));
	}
	
	@Test
	@DisplayName("Suppression station")
	void deleteStationTest() {
		Station s1 = m.getStations().get(0);
		m.delStation(s1);
		assertEquals(1,m.getStations().size());
	}
	

	@Test
	@DisplayName("Suppression des rails après suppression station")
	void deleteStation2Test() {
		Station s1 = m.getStations().get(0);
		m.delStation(s1);
		assertEquals(0,m.getRails().size());
	}


}
