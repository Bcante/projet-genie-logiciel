package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import metroproject.Rail;
import metroproject.Station;

class testRail {

	@Test
	@DisplayName("Simple test get")
	void testDepart() {
		Station s1 = new Station("test1");
		Station s2 = new Station("test2");
		Rail r = new Rail(s1, s2, 5, false);
		assertEquals(s1, r.getDepart());
	}
	
	@Test
	@DisplayName("Changement statut incident rail")
	void testIncident() {
		Station s1 = new Station("test1");
		Station s2 = new Station("test2");
		Rail r = new Rail(s1, s2, 5, false);
		r.setIncident(true);
		assertTrue(r.isIncident());
	}
	
	@Test
	@DisplayName("Liaison station")
	void testLinked() {
		Station s1 = new Station("test1");
		Station s2 = new Station("test2");
		Rail r = new Rail(s1, s2, 5, false);
		assertTrue(r.isLinkedTo(s1));
	}
	
	@Test
	@DisplayName("Liaison station inexistante")
	void testNotLinked() {
		Station s1 = new Station("test1");
		Station s2 = new Station("test2");
		Station s3 = new Station("test3");
		Rail r = new Rail(s1, s2, 5, false);
		assertFalse(r.isLinkedTo(s3));
	}
	
	
	
}
