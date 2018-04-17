package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import metroproject.Rail;
import metroproject.Station;

class TestStation {

	
	@Test
	void testGetTest() {
		Station s1 = new Station("test1");
		assertEquals("test1", s1.getNomStation());
	}
	
	@Test
	void testAddRailTest() {
		Station s1 = new Station("test1");
		Station s2 = new Station("test2");
		Rail r = new Rail(s1, s2, 5, false);
		s1.addRailtoStation(r);
		assertEquals(r, s1.getRails().get(0));
	}
	
	@Test
	void testGetConnectionsTest() {
		Station s1 = new Station("test1");
		Station s2 = new Station("test2");
		Rail r = new Rail(s1, s2, 5, false);
		s1.addRailtoStation(r);
		assertEquals(s2, s1.getConnections().get(0));
	}
	
	
	//Ici à décider si on prend en compte le sens ou non
	@Test
	@DisplayName("Une connection n'a pas de sens")
	void testGetConnectionsSensInverseTest() {
		Station s1 = new Station("test1");
		Station s2 = new Station("test2");
		Rail r = new Rail(s2, s1, 5, false);
		s1.addRailtoStation(r);
		assertEquals(s2, s1.getConnections().get(0));
	}
	
	
	
	
	

}
