package Main;

import java.util.InputMismatchException;
import java.util.Scanner;
import metroproject.Station;
import metroproject.Utilisateur;

public class Application {
	static IHM ihm;
	static String incorrectValue = "La valeur est incorrecte.";
	static String wrongLocationValue = "La valeur doit être comprise entre 0 et 100";
	static String errorStringValue = "Veuillez choisir un chiffre entre 0 et 4.";

	public static void main(String[] args) {
		/*
		 * String geoloc; String dest; Options options=new Options(); Option
		 * geolocalisation=new
		 * Option("g","geolocalisation",true,"Géolocalisation de l'utilisateur");
		 * geolocalisation.setRequired(true); options.addOption(geolocalisation); Option
		 * destination=new Option("d","destination",
		 * true,"Station destination souhaitée par l'utilisateur");
		 * destination.setRequired(true); options.addOption(destination); //si pas
		 * d'option de chemin précisée, on fait le plus rapide par défaut Option
		 * fastestPath=new Option("fp","Chemin le plus rapide"
		 * ,false,"Permet d'obtenir le chemin le plus rapide jusqu'à la station désirée"
		 * ); Option customPath=new Option("cp","Itinéraire personnalisé"
		 * ,true,"Permet d'obtenir un itinéraire passant par une station spécifique");
		 * Option leastTransferPath=new
		 * Option("lp","Itinéraire avec le moins de changements"
		 * ,true,"Permet d'obtenir un itinéraire passant par le moins de lignes possible"
		 * ); options.addOption(fastestPath); options.addOption(customPath);
		 * options.addOption(leastTransferPath); CommandLineParser parser = new
		 * DefaultParser();
		 * 
		 * //initialisations des valeurs String[] res=new String[2]; String depart;
		 * ArrayList<Station> path=new ArrayList<Station>(); IHM utilisation=new IHM();
		 * String outUser=""; try { CommandLine line = parser.parse(options, args);
		 * geoloc=line.getOptionValue("g"); dest=line.getOptionValue("d"); res =
		 * geoloc.split(","); if (res.length!=2) { throw new ParseException(geoloc); }
		 * Utilisateur u=new Utilisateur(Integer.parseInt(res[0]),
		 * Integer.parseInt(res[1]),"xxx"); depart=utilisation.findClosestStation(u); if
		 * (line.hasOption("lp")) { //itinéraire avec le moins de changements } else if
		 * (line.hasOption("cp")) { //itinéraire perso } else { //chemin le plus rapide
		 * (par défaut)
		 * outUser=utilisation.shortestPath(utilisation.getM().getStation(depart),
		 * utilisation.getM().getStation(depart)); } }catch(ParseException e){
		 * System.out.
		 * println("Problème de parsing. Les argument -g (géolocalisation) et -d (Station de destination) sont-ils bien renseignés?"
		 * ); } System.out.println(outUser);
		 */

		ihm = new IHM();
		int choix = 0;
		Scanner scanner = new Scanner(System.in);
		Utilisateur user = new Utilisateur(0, 0, "Bobby McUserinton");

		do {
			showMenu();
			while (!scanner.hasNextInt()) {
				System.out.println("Valeur incorrecte.");
				showMenu();
				scanner.nextLine();
			}

			choix = Integer.parseInt(scanner.nextLine());

			switch (choix) {
			case 0:
				break;
			case 1:
				findPath(user, scanner);
				break;
			case 2:
				majLoc(user, scanner);
				break;
			case 3:
				getInfoPerturbations(scanner);
				break;
			case 4:
				System.out.println("Station la plus proche: " + ihm.findClosestStation(user));
				break;
			default:
				showErrorMessage(incorrectValue);
				break;
			}
		} while (choix != 0);

	}

	private static void showMenu() {
		System.out.println("");
		System.out.println("Menu principal");
		System.out.println("0- Quitter");
		System.out.println("1- Trouver un chemin");
		System.out.println("2- Maj position");
		System.out.println("3- Connaitre les perturbations");
		System.out.println("4- Connaitre la station la plus proche");
	}

	private static void majLoc(Utilisateur u, Scanner scanner) {
		boolean updated = false;
		do {
			try {
				System.out.println("Indiquez la nouvelle coordonnée X (entre 0 et 100): ");
				int newX = affectValueLocation(scanner);
				System.out.println("Indiquez la nouvelle coordonnée Y (entre 0 et 100): ");
				int newY = affectValueLocation(scanner);
				u.setX(newX);
				u.setY(newY);
				updated = true;
			} catch (NumberFormatException e2) {
				showErrorMessage(incorrectValue);
			} catch (InputMismatchException e3) {
				showErrorMessage(incorrectValue);
			}
		} while (!updated);
	}

	private static void getInfoPerturbations(Scanner scanner) {
		boolean get = false;
		do {
			try {
				System.out.println("Souhaitez vous connaître les perturbations sur\n" + "1- Une ligne\n"
						+ "2- Une station\n" + "Defaut- Retour au menu");
				int choix = Integer.parseInt(scanner.nextLine());
				switch (choix) {
				case 1:
					boolean  find = false;
					do {
						System.out.println("Indiquez le numéro de la ligne (1 - 5, 0 pour annuler).");
						String ligne = scanner.nextLine();
						if(ihm.existLineOrStation(ligne, "LIGNE")) {
							find = true;
							System.out.println("");
							System.out.println(ihm.getLineIssues(ligne));
						}else {
							if(ligne.equals("0")) {
								find = true;
							}else {

								System.out.println("Ligne inconnue.");
							}
						}
					}while(!find);
					
					break;
				case 2:
					boolean  done = false;
					do {
						System.out.println("Indiquez le nom de la station. (0 pour annuler)");
						String station = scanner.nextLine();
						if(ihm.existLineOrStation(station, "STATION")) {
							done = true;

							System.out.println("");
							System.out.println(ihm.getStationIssues(station));
						}else {
							if(station.equals("0")) {
								done = true;
							}else {

								System.out.println("Station inconnue.");
							}
						}
					}while(!done);
					
					break;
				default:
					break;
				}
				get = true;
			} catch (NumberFormatException e2) {
				showErrorMessage(incorrectValue);
			} catch (InputMismatchException e3) {
				showErrorMessage(incorrectValue);
			}
		} while (!get);
	}

	private static void findPath(Utilisateur u, Scanner scanner) {
		String closestStation = ihm.findClosestStation(u);
		
		boolean find = false;
		do {
			System.out.println("Indiquez la station de destination (0 pour annuler): ");
			String destination = scanner.nextLine();
			if (ihm.existLineOrStation(destination, "STATION")) {
				askPreferredPath(ihm.getM().getStation(closestStation), ihm.getM().getStation(destination), scanner);
				find = true;
			} else {
				if(destination.equals("0")) {
					find = true;
				}else {
					System.out.println("Destination inconnue.");
				}
			}
		}while(!find);
		
	}

	private static void askPreferredPath(Station dep, Station dest, Scanner scanner) {
		boolean choose = false;
		do {
			String res ="";
			try {
				System.out.println("1- Je veux m'y rendre le plus rapidement possible");
				System.out.println("2- Le plus rapidement, à partir d'une station");
				System.out.println("3- Je veux minimiser les correspondances NON IMPLEMENTEE PR LE MOMENT");
				System.out.println("Defaut- Retour au menu");
				int choix = Integer.parseInt(scanner.nextLine());
		
				switch (choix) {
				case 1:
					res = ihm.shortestPath(dep, dest);
					System.out.println(res);
					break;
				case 2:
					boolean find = false;
					String depart;
					do {
						System.out.println("Station de départ (0 pour annuler):");
						depart = scanner.nextLine();
					
						if (!find && ihm.existLineOrStation(depart, "STATION")) {
							res = ihm.shortestPath(ihm.getM().getStation(depart), dest);
							find = true;
							System.out.println(res);
						}else {
							if(depart.equals("0")) {
								find = true;
							}else {
								System.out.println("Station inconnue");
							}
						}
					}while (!find);
					
					break;
				case 3:
					break;
				default:
					break;
				}
				choose = true;
			} catch (NumberFormatException e2) {
				showErrorMessage(incorrectValue);
			} catch (InputMismatchException e3) {
				showErrorMessage(incorrectValue);
			}
		}while(!choose);
	}

	public static void showErrorMessage(String message) {
		System.out.println(message);
	}

	private static int affectValueLocation(Scanner sc) {
		int res = 0;
		boolean estInteger = false;
		do {
			try {
				int value = Integer.parseInt(sc.nextLine());
				res = value;
				estInteger = true;
			} catch (InputMismatchException e2) {
				showErrorMessage(incorrectValue);
				sc.nextLine();
			}
		} while (!estInteger);

		while (res < 0 || res > 100) {
			showErrorMessage(wrongLocationValue);
			res = Integer.parseInt(sc.nextLine());
		}

		return res;
	}

}
