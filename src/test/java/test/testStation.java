package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import metroproject.Rail;
import metroproject.Station;

class testStation {

	Station s1;
	Station s2;
	Rail r;
	
	@BeforeEach
	void beforeEach() {
		s1 = new Station("test1");
		s2 = new Station("test2");
		r = new Rail(s1, s2, 5, false);
	}
	
	@Test
	void testGetTest() {
		assertEquals("test1", s1.getNomStation());
	}
	
	@Test
	void testAddRailTest() {
		s1.addRailtoStation(r);
		assertEquals(r, s1.getRails().get(0));
	}
	
	@Test
	void testGetConnectionsTest() {
		s1.addRailtoStation(r);
		assertEquals(s2, s1.getConnections(true).get(0));
	}
	
	
	//Ici à décider si on prend en compte le sens ou non
	@Test
	@DisplayName("Une connection n'a pas de sens")
	void testGetConnectionsSensInverseTest() {
		s1.addRailtoStation(r);
		assertEquals(s2, s1.getConnections(true).get(0));
	}

}
