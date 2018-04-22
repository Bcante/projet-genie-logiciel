package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import metroproject.Rail;
import metroproject.Station;

class TestRail {

	Station s1;
	Station s2;
	Station s3;
	Rail r;

	@BeforeEach
	void beforeEach() {
		s1 = new Station("test1");
		s2 = new Station("test2");
		s3 = new Station("test3");
		r = new Rail(s1, s2, 5, false);
	}
	
	@Test
	@DisplayName("Simple test get")
	void testDepartTest() {
		assertEquals(s1, r.getDepart());
	}
	
	@Test
	@DisplayName("Changement statut incident rail")
	void testIncidentTest() {
		r.setIncident(true);
		assertTrue(r.isIncident());
	}
	
	@Test
	@DisplayName("Liaison station")
	void testLinkedTest() {
		assertTrue(r.isLinkedTo(s1));
	}
	
	@Test
	@DisplayName("Liaison station inexistante")
	void testNotLinkedTest() {
		assertFalse(r.isLinkedTo(s3));
	}
	
	
	
}
