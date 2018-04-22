
import static org.junit.jupiter.api.Assertions.*;

import metroproject.Metro;
import metroproject.Rail;
import metroproject.Station;
import org.junit.jupiter.api.Test;
import utilitaires.GestionnaireSauvegarde;

class testSerial {

    @Test
    void serializationDeserialization() {
        Metro grandMetro = new Metro();
        grandMetro.addStation("Paris");
        grandMetro.addStation("Berlin");

        grandMetro.addRail(grandMetro.getStations().get(0),grandMetro.getStations().get(1),5,false);

        GestionnaireSauvegarde.sauvegarde(grandMetro);

        Metro clone = GestionnaireSauvegarde.chargement();
        assertNotNull(clone);
        assertEquals(2,clone.getStations().size());
        assertEquals(1,clone.getRails().size());
    }
}
