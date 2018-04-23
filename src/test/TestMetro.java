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
	Station s1,s2,s3;
	
	@BeforeEach
	void beforeEach() {
		m.addStation("test");
		m.addStation("test2");
		m.addStation("test3");
		s1 = m.getStations().get("test");
		s2 = m.getStations().get("test2");
		s3 = m.getStations().get("test3");
	}
	

	@Test
	@DisplayName("Ajout d'une station")
	void addStationTest() {
		assertEquals("test", m.getStations().get("test").getNomStation());
	}
	
	@Test
	@DisplayName("Ajout d'un rail")
	void addRailTest() {
		m.addRail(s1,s2 , 5, false);
		assertEquals(s2, s1.getConnections().get(0));
	}
	
	@Test
	@DisplayName("Connexion directe entre deux stations")
	void areConnectedTest() {
		m.addRail(s1,s2 , 5, false);
		assertTrue(m.areConnected(s1, s2));
	}
	
	@Test
	@DisplayName("Pas de connexion directe entre deux stations")
	void areConnected2Test() {	
		m.addRail(s1,s3 , 5, false);
		assertFalse(m.areConnected(s1, s2));
	}
	
	
	@Test
	@DisplayName("areLinked avec lien direct")
	void areLinkedTest() {
		m.addRail(s1,s2 , 5, false);
		assertTrue(m.areLinked(s1, s2));
	}
	
	@Test
	@DisplayName("areLinked avec lien distancé")
	void areLinked2Test() {
		m.addRail(s1,s2 , 5, false);
		m.addRail(s2, s3, 1, false);
		assertTrue(m.areLinked(s1, s3));
	}
	
	@Test
	@DisplayName("Suppression rail")
	void deleteRailTest() {
		m.addRail(s1,s2 , 5, false);
		m.delRail(s1, s2);
		assertFalse(m.areConnected(s1, s2));
	}
	
	@Test
	@DisplayName("Suppression station")
	void deleteStationTest() {
		m.delStation(s1);
		assertEquals(2,m.getStations().size());
	}
	

	@Test
	@DisplayName("Suppression des rails après suppression station")
	void deleteStation2Test() {
		m.addRail(s1,s2 , 5, false);
		m.delStation(s1);
		assertEquals(0,m.getRails().size());
	}


}
