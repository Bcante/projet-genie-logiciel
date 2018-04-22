

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import metroproject.Metro;
import metroproject.Station;
import utilitaires.GestionnaireSauvegarde;

class testMetro {
	private static Metro grandMetro;
	
	@BeforeAll
	static void beforeAll() {
        grandMetro=new Metro();
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

}
