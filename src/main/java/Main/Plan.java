package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import metroproject.Ligne;
import metroproject.Metro;
import metroproject.Rail;
import metroproject.Station;

public class Plan {

	public Metro createParisMetro() {
		Metro metroParis = new Metro("Paris");

		HashMap<String, Station> listeStation = new HashMap<String, Station>();
		HashMap<String, Ligne> listeLigne = new HashMap<String, Ligne>();
		
		ArrayList<Rail> listeRail = new ArrayList<Rail>();
		
		LinkedHashSet<Station> listL1 = new LinkedHashSet<Station>();
		LinkedHashSet<Station> listL2 = new LinkedHashSet<Station>();
		LinkedHashSet<Station> listL3 = new LinkedHashSet<Station>();
		LinkedHashSet<Station> listL4 = new LinkedHashSet<Station>();
		LinkedHashSet<Station> listL5 = new LinkedHashSet<Station>();

		// Ligne 1 Argentine -> ChampsElysee

		listeStation.put("Argentine", new Station("Argentine",new int[]{1}));
		listeStation.put("Bastille", new Station("Bastille",new int[]{1}));
		listeStation.put("Berault", new Station("Berault",new int[]{1}));
		listeStation.put("ChampsElysee", new Station("ChampsElysee",new int[]{1}));

		// Ligne 2 AlexandreDumas -> Pigalle
		listeStation.put("AlexandreDumas", new Station("AlexandreDumas",new int[]{2}));
		listeStation.put("Anvers", new Station("Anvers",new int[]{2}));
		listeStation.put("Avron", new Station("Avron",new int[]{2}));
		listeStation.put("Barbes", new Station("Barbes",new int[]{2}));
		listeStation.put("Pigalle", new Station("Pigalle",new int[]{2}));

		// Ligne 3
		listeStation.put("AnatoleFrance", new Station("AnatoleFrance",new int[]{3}));
		listeStation.put("ArtsEtMetiers", new Station("ArtsEtMetiers",new int[]{3}));
		listeStation.put("Bourse", new Station("Bourse",new int[]{3}));
		listeStation.put("Europe", new Station("Europe",new int[]{3}));

		// Ligne 4
		listeStation.put("ChateauDeVincennes", new Station("ChateauDeVincennes",new int[]{4}));
		listeStation.put("Chatelet", new Station("Chatelet",new int[]{4}));
		listeStation.put("Couronnes", new Station("Couronnes",new int[]{4}));

		// Ligne 5
		listeStation.put("Belleville", new Station("Belleville",new int[]{5}));
		listeStation.put("Blanche", new Station("Blanche",new int[]{5}));
		listeStation.put("Gallieni", new Station("Gallieni",new int[]{5}));
		listeStation.put("Gambetta", new Station("Gambetta",new int[]{5}));

		// LigneCommune
		listeStation.put("CharlesDeGaulle", new Station("CharlesDeGaulle",new int[]{1,2,4}));// 1 et 2 et 4
		listeStation.put("Concorde", new Station("Concorde",new int[]{1,3,5}));// 1,3,5
		listeStation.put("Esplanade", new Station("Esplanade",new int[]{5}));// 2,4
		listeStation.put("FranklinDRoosvlet", new Station("FranklinDRoosvlet",new int[]{2,3}));// 2,3
		listeStation.put("ColonnelFabien", new Station("ColonnelFabien",new int[]{2,3}));// 2,3
		listeStation.put("Courcelles", new Station("Courcelles",new int[]{4,5}));// 4,5
		listeStation.put("Jaures", new Station("Jaures",new int[]{2,5}));// 2,5
		
		metroParis.setStations(listeStation);
		
		// Rails L1

		
		listeRail.add(new Rail(listeStation.get("Argentine"), listeStation.get("Bastille"), 2, false));
		listeRail.add(new Rail(listeStation.get("Bastille"), listeStation.get("CharlesDeGaulle"), 4,false));
		listeRail.add(new Rail(listeStation.get("CharlesDeGaulle"), listeStation.get("Berault"), 4,false));
		listeRail.add(new Rail(listeStation.get("Berault"), listeStation.get("Concorde"), 2, false));
		listeRail.add(new Rail(listeStation.get("Concorde"), listeStation.get("ChampsElysee"), 1, false));

		listL1.add(listeStation.get("Argentine"));
		listL1.add(listeStation.get("Bastille"));
		listL1.add(listeStation.get("CharlesDeGaulle"));
		listL1.add(listeStation.get("Berault"));
		listL1.add(listeStation.get("Concorde"));
		listL1.add(listeStation.get("ChampsElysee"));
		
		// Rails L2
		listeRail.add(new Rail(listeStation.get("AlexandreDumas"), listeStation.get("Anvers"), 3,false));
		listeRail.add(new Rail(listeStation.get("Anvers"), listeStation.get("Esplanade"), 2,false));
		listeRail.add(new Rail(listeStation.get("Esplanade"), listeStation.get("FranklinDRoosvlet"), 2,false));
		listeRail.add(new Rail(listeStation.get("FranklinDRoosvlet"), listeStation.get("Avron"), 2,false));
		listeRail.add(new Rail(listeStation.get("Avron"), listeStation.get("CharlesDeGaulle"), 3,false));
		listeRail.add(new Rail(listeStation.get("CharlesDeGaulle"), listeStation.get("ColonnelFabien"), 2,false));
		listeRail.add(new Rail(listeStation.get("ColonnelFabien"), listeStation.get("Barbes"), 1,false));
		listeRail.add(new Rail(listeStation.get("Barbes"), listeStation.get("Pigalle"), 3,false));
		
		listL2.add(listeStation.get("ALexandreDumas"));
		listL2.add(listeStation.get("Anvers"));
		listL2.add(listeStation.get("Esplanade"));
		listL2.add(listeStation.get("FranklinDRoosvlet"));
		listL2.add(listeStation.get("Avron"));
		listL2.add(listeStation.get("CharlesDeGaulle"));
		listL2.add(listeStation.get("ColonnelFabien"));
		listL2.add(listeStation.get("Barbes"));
		listL2.add(listeStation.get("Pigalle"));
		
		// Rails L3
		listeRail.add(new Rail(listeStation.get("AnatoleFrance"), listeStation.get("ArtsEtMetiers"), 2,false));
		listeRail.add(new Rail(listeStation.get("ArtsEtMetiers"), listeStation.get("Concorde"), 3,false));
		listeRail.add(new Rail(listeStation.get("Concorde"), listeStation.get("Bourse"), 2,false));
		listeRail.add(new Rail(listeStation.get("Bourse"), listeStation.get("FranklinDRoosvlet"), 3,false));
		listeRail.add(new Rail(listeStation.get("FranklinDRoosvlet"), listeStation.get("ColonnelFabien"), 2,false));
		listeRail.add(new Rail(listeStation.get("ColonnelFabien"), listeStation.get("Barbes"), 1,false));
		
		listL3.add(listeStation.get("AnatoleFrance"));
		listL3.add(listeStation.get("ArtsEtMetiers"));
		listL3.add(listeStation.get("Concorde"));
		listL3.add(listeStation.get("Bourse"));
		listL3.add(listeStation.get("FranklinDRoosvlet"));
		listL3.add(listeStation.get("ColonnelFabien"));
		listL3.add(listeStation.get("Europe"));
		
		// Rails L4
		listeRail.add(new Rail(listeStation.get("ChateauDeVincennes"), listeStation.get("CharlesDeGaulle"), 2,false));
		listeRail.add(new Rail(listeStation.get("CharlesDeGaulle"), listeStation.get("Chatelet"), 3,false));
		listeRail.add(new Rail(listeStation.get("Chatelet"), listeStation.get("Esplanade"), 2,false));
		listeRail.add(new Rail(listeStation.get("Esplanade"), listeStation.get("Courcelles"), 3,false));
		listeRail.add(new Rail(listeStation.get("Courcelles"), listeStation.get("Couronnes"), 2,false));
		
		listL4.add(listeStation.get("ChateauDeVincennes"));
		listL4.add(listeStation.get("CharlesDeGaulle"));
		listL4.add(listeStation.get("Chatelet"));
		listL4.add(listeStation.get("Esplanade"));
		listL4.add(listeStation.get("Courcelles"));
		listL4.add(listeStation.get("Couronnes"));
		
		// Rails L5
		listeRail.add(new Rail(listeStation.get("Belleville"), listeStation.get("Blanche"), 2,false));
		listeRail.add(new Rail(listeStation.get("Blanche"), listeStation.get("Concorde"), 3,false));
		listeRail.add(new Rail(listeStation.get("Concorde"), listeStation.get("Courcelles"), 2,false));
		listeRail.add(new Rail(listeStation.get("Courcelles"), listeStation.get("Gallieni"), 3,false));
		listeRail.add(new Rail(listeStation.get("Gallieni"), listeStation.get("Jaures"), 1,false));
		listeRail.add(new Rail(listeStation.get("Jaures"), listeStation.get("Gambetta"), 1,false));
		
		listL5.add(listeStation.get("Belleville"));
		listL5.add(listeStation.get("Blanche"));
		listL5.add(listeStation.get("Concorde"));
		listL5.add(listeStation.get("Courcelles"));
		listL5.add(listeStation.get("Gallieni"));
		listL5.add(listeStation.get("Jaures"));
		listL5.add(listeStation.get("Gambetta"));

		listeLigne.put("1", new Ligne("1"));
		listeLigne.put("2", new Ligne("2"));
		listeLigne.put("3", new Ligne("3"));
		listeLigne.put("4", new Ligne("4"));
		listeLigne.put("5", new Ligne("5"));
		
		// Affectation des stations aux lignes
		listeLigne.get("1").setStationsLigneAller(listL1);
		listeLigne.get("2").setStationsLigneAller(listL2);
		listeLigne.get("3").setStationsLigneAller(listL3);
		listeLigne.get("4").setStationsLigneAller(listL4);
		listeLigne.get("5").setStationsLigneAller(listL5);
		
		//metroParis.setStations(listeStation);
		metroParis.setRails(listeRail);
		metroParis.setLignes(listeLigne);
		
		return metroParis;
	}
}
