package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import metroproject.Metro;
import metroproject.Station;

class testMetro {
	private static Metro grandMetro;
	Metro m = new Metro("TestMetro");
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
	
	@BeforeAll
	static void beforeAll() {
        grandMetro=new Metro("France");
        grandMetro.addStation("Paris");
        grandMetro.addStation("Nancy");
        grandMetro.addStation("Metz");
        grandMetro.addStation("Strasbourg");
        grandMetro.addStation("Rennes");
        grandMetro.addRail(grandMetro.getStation("Rennes"), grandMetro.getStation("Paris"), 6, false);
        grandMetro.addRail(grandMetro.getStation("Paris"), grandMetro.getStation("Nancy"), 4, false);
		grandMetro.addRail(grandMetro.getStation("Nancy"), grandMetro.getStation("Metz"), 4, false);
	}

	@Test
	void test_connectedTrueSimple() {
		Station s1=grandMetro.getStation("Paris");
		Station s2=grandMetro.getStation("Nancy");
		assertEquals(true,grandMetro.areConnected(s1,s2));
	}
	
	@Test
	void test_connectedTrue() {
		Station s1=grandMetro.getStation("Paris");
		Station s3=grandMetro.getStation("Metz");
		assertEquals(true,grandMetro.areConnected(s1,s3));
	}
	
	@Test
	void test_connectedTrueLong() {
		Station s1=grandMetro.getStation("Rennes");
		Station s2=grandMetro.getStation("Metz");
		assertEquals(true,grandMetro.areConnected(s1,s2));
	}
	
	@Test
	void testconnectedFalse() {
		Station s1=grandMetro.getStation("Paris");
		Station s2=grandMetro.getStation("Strasbourg");
		assertEquals(false,grandMetro.areConnected(s1,s2));
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
		assertTrue(m.areLinked(s1, s2));
	}
	
	@Test
	@DisplayName("Pas de connexion directe entre deux stations")
	void areConnected2Test() {	
		m.addRail(s1,s3 , 5, false);
		assertFalse(m.areLinked(s1, s2));
	}
	
	
	@Test
	@DisplayName("areConnected avec lien direct")
	void areLinkedTest() {
		m.addRail(s1,s2 , 5, false);
		assertTrue(m.areConnected(s1, s2));
	}
	
	@Test
	@DisplayName("areConnected avec lien distancé")
	void areLinked2Test() {
		m.addRail(s1,s2 , 5, false);
		m.addRail(s2, s3, 1, false);
		assertTrue(m.areConnected(s1, s3));
	}
	
	@Test
	@DisplayName("Suppression rail")
	void deleteRailTest() {
		m.addRail(s1,s2 , 5, false);
		System.out.println(s1.getRails());

		m.delRail(s1, s2);
		System.out.println(s1.getRails());
		System.out.println(m.areConnected(s1, s2));
		
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
