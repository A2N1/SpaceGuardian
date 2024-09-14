package org.example.app;

//import model.EnemySpaceShip;
//import model.SpaceShip;
//import model.Superpower;	

import java.util.Scanner;

/**
 * @author Sahin_ Ali Haydar
 * @author Nick Luca Ninnemann - A2N1
 * @version 1.0
 * date: 21.01.2024 - time: 13:18
 */
public class SpacePatrolApp {

	private static Scanner scanner = new Scanner(System.in);
	private static SpaceShip[] spaceShipArray = new SpaceShip[5];

	/**
	 * Die main-Methode dient als Einstiegspunkt für das Programm.
	 * Sie führt eine Endlosschleife aus, zeigt ein Standardmenü an,
	 * liest die Benutzereingabe und behandelt die Auswahl des Benutzers.
	 * Tritt während dieses Prozesses eine Ausnahme auf, wird sie abgefangen
	 * und eine Fehlermeldung wird auf der Konsole ausgegeben.
	 *
	 * @param args Die Befehlszeilenargumente, die dem Programm übergeben werden.
	 */
	public static void main(String[] args) {
		while (true) {
			showStandardMenu();
			try {
				int choice = readUserInput();
				handle(choice);
			} catch (Exception e) {
				System.out.println("Exception gefangen " + e.getMessage());
			}
			System.out.println("====================");
		}
	}

	/**
	 * Die Methode liest die Benutzereingabe für das Menü und gibt die ausgewählte
	 * Menünummer zurück. Die Eingabe wird überprüft, um sicherzustellen, dass sie
	 * eine gültige Zahl zwischen 1 und 6 ist.
	 *
	 * @return Die ausgewählte Menünummer des Benutzers.
	 */
	private static int readUserInput() {
		int choiceInternal;
		while (true) {
			System.out.print("\nBitte, geben Sie die Nummer des gewählten Menüeintrags ein:\t");
			while (!scanner.hasNextInt()) {
				System.out.println("Ungültige Eingabe. Bitte geben Sie eine geforderte Zahl ein:");
				scanner.next();
			}
			choiceInternal = scanner.nextInt();
			scanner.nextLine();
			if (choiceInternal >= 1 && choiceInternal <= 6) {
				break;
			} else {
				System.out.println("Ungültige Eingabe. Bitte geben Sie eine Zahl zwischen 1 und 6 ein.");
			}
		}
		return choiceInternal;
	}

	/**
	 * Diese Methode behandelt die Benutzerauswahl basierend auf der übergebenen
	 * Menünummer. Je nach Auswahl wird eine entsprechende Aktion ausgeführt.
	 *
	 * @param choice Die vom Benutzer ausgewählte Menünummer.
	 */
	private static void handle(int choice) {
		switch (choice) {
			case 1:
				createSpaceShip();
				break;
			case 2:
				ShipData();
				break;
			case 3:
				shipDataAll();
				break;
			case 4:
				removeSpaceShip();
				break;
			case 5:
				showPatrolMenu();
				break;
			case 6:
				System.out.println("Vielen Dank fürs Spielen von Space Patrol 1.0!");
				System.out.println("Das Programm wird beendet.");
				System.exit(0);
				break;
			default: {
				System.out.println("Ungueltige Eingabe. Bitte ueberpruefen Sie die Eingabe");
				showStandardMenu();
			}
			break;
		}
	}

	/**
	 * Diese Methode zeigt das Standardmenü für das Space Patrol 1.0-Programm an.
	 * Das Menü enthält verschiedene Optionen für Aktionen im Zusammenhang mit Raumschiffen.
	 * Die Menüoptionen werden auf der Konsole ausgegeben.
	 */
	private static void showStandardMenu() {
		String menuItems[] = {"", "(1)\t Raumschiff anlegen", "(2)\t Daten eines Raumschiffs anzeigen",
				"(3)\t Daten aller Raumschiffe anzeigen", "(4)\t Raumschiff aus der Flotte nehmen", "(5)\t Patrouillen-Flug starten",
				"(6)\t Beenden"};
		System.out.println("\nSPACE PATROL 1.0\n");
		for (int i = 1; i < menuItems.length; i++) {
			System.out.println(menuItems[i]);
		}
	}

	/**
	 * Zeigt das Menü für Patrouillenaktionen an, einschließlich Optionen wie Patrouillieren, Regenerieren und Patrouille beenden. (5)
	 */
	private static void showPatrolMenu() {
		String menuItems[] = {"", "(1)\t Patrouillieren", "(2)\t Regenerieren",
				"(3)\t Patrouille beenden"};

		System.out.println("\n------ Patrouille Menu ------\n");
		for (int i = 1; i < menuItems.length; i++) {
			System.out.println(menuItems[i]);
		}
		int choice = readUserInput();
		switch (choice) {
			case 1:
				Patrol();
				break;
			case 2:
				shipDataLifePoints();
				break;
			case 3:
				System.out.println();
				System.out.println("Patrouille wurde beendet.");
				showStandardMenu();
				break;
			default:
				System.out.println("Ungültige Eingabe. Bitte geben Sie eine Zahl zwischen 1 und 3 ein.");
				showPatrolMenu();
				break;
		}
	}

	/**
	 * Erstellt ein neues Raumschiff und fügt es der Flotte hinzu. (1)
	 *
	 * @return Das neu erstellte Raumschiff.
	 */
	private static void createSpaceShip() {
		if (spaceShipArray[spaceShipArray.length - 1] != null) {
			System.out.println("Maximale Anzahl von Raumschiffen erreicht (5).");
			System.out.println("Möchten Sie ein Raumschiff entfernen oder zum Hauptmenü zurückkehren?");
			System.out.println("1. Ein Raumschiff Ihrer Wahl entfernen");
			System.out.println("2. Zum Hauptmenü zurückkehren");

			int choice = readUserInput();
			if (choice == 1) {
				removeSpaceShip();
			} else if (choice == 2) {
				showStandardMenu();
			} else {
				System.out.println("Ungültige Eingabe.");
				System.out.println("Bitte wählen Sie eine der beiden Optionen aus.");
				System.out.println("Sie werden zum Hauptmenü geleitet.");
				showStandardMenu();
			}
			return;
		}

		System.out.println("\nErstelle einen Raumschiff\n");
		System.out.println("Wie soll Ihr Raumschiff heißen?");
		String shipName = scanner.next();

		Superpower superpower = createSuperPower();

		SpaceShip raumSchiff = new SpaceShip(shipName, superpower);
		SpaceShip newSpaceShip = new SpaceShip(shipName, superpower);
		// Suche nach dem ersten leeren Platz im Array
		for (int i = 0; i < spaceShipArray.length; i++) {
			if (spaceShipArray[i] == null) {
				spaceShipArray[i] = newSpaceShip;
				break;
			}
		}
		System.out.println("Raumschiff " + shipName + " mit der Superkraft " + superpower + " erfolgreich angelegt!");

		waitForEnter();
	}


	private static Superpower createSuperPower() {
		System.out.println("\nWählen Sie eine Superkraft für Ihr Raumschiff aus:\n");

// Anzeigen der vordefinierten Superkräfte
		for (int i = 0; i < Superpower.getdefinedSuperpower().length; i++) {
			System.out.println((i + 1) + ". " + Superpower.getdefinedSuperpower()[i].getName());
		}

// Option für eigene Superkraft erstellen
		System.out.println((Superpower.getdefinedSuperpower().length + 1) + ". Eigene Superkraft erstellen");

// Benutzer wählt eine Superkraft aus
		int choice = readUserInput();
		if (choice >= 1 && choice <= Superpower.getdefinedSuperpower().length) {
			return Superpower.getdefinedSuperpower()[choice - 1];
		} else if (choice == Superpower.getdefinedSuperpower().length + 1) {
			return createCustomSuperpower();

		}
		return null;
	}

	/**
	 * Erstellt eine neue Superkraft und fügt sie der Liste der Superkräfte hinzu.
	 *
	 * @return Erstellt eine neue Superkraft und fügt sie der Liste der Superkräfte hinzu.
	 */
	private static Superpower createCustomSuperpower() {
		System.out.println("\nErstelle eine eigene Superkraft für das Raumschiff\n");
		System.out.println("Wie soll Ihre Superkraft heißen?");
		String name = scanner.next();
		System.out.println("Was soll die Superkraft " + name + " machen können?");
		String actionDescription = scanner.next();
		return new Superpower(name, actionDescription);
	}

	/**
	 * Zeigt die Daten eines Raumschiffs an, das der Benutzer auswählt. (2)
	 *
	 * @return Das ausgewählte Raumschiff.
	 */
	private static void ShipData() {
		if (spaceShipArray[0] == null) {
			System.out.println("Keine Raumschiffe vorhanden!");
			System.out.println("Möchten Sie ein Raumschiff anlegen?");
			System.out.println("1. Ja");
			System.out.println("2. Zurück zum Hauptmenü");

			int choice = readUserInput();
			if (choice == 1) {
				createSpaceShip();
			} else if (choice == 2) {
				showStandardMenu();
			} else {
				System.out.println("Ungültige Eingabe.");
				System.out.println("Bitte wählen Sie eine der beiden Optionen aus.");
				System.out.println("Sie werden zum Hauptmenü geleitet.");
				showStandardMenu();
			}
			return;
		}
		System.out.println("Wählen Sie eines Ihrer Raumschiffe aus:");
		for (int i = 0; i < spaceShipArray.length; i++) {
			if (spaceShipArray[i] != null) {
				System.out.println((i + 1) + ": " + spaceShipArray[i].getName());
			}
		}
		int choice = scanner.nextInt();
		if (choice > 0 && choice <= spaceShipArray.length && spaceShipArray[choice - 1] != null) {
			SpaceShip selectedSpaceShip = spaceShipArray[choice - 1];
			System.out.println("Daten für Raumschiff " + selectedSpaceShip.getName() + ":");
			System.out.println("Name: " + selectedSpaceShip.getName());
			System.out.println("Health Points Max: " + selectedSpaceShip.getHealthPointsMax());
			System.out.println("Experience Points: " + selectedSpaceShip.getExperiencePoints());
			System.out.println("Superpower: " + selectedSpaceShip.getSuperpower().getName());
			System.out.println("Ready To Fight: " + selectedSpaceShip.isReadyToFight());
			System.out.println("Alive: " + selectedSpaceShip.isAlive());
		} else {
			System.out.println("Ungültige Auswahl.");
			System.out.println("Bitte wählen Sie ein vorhandenes Raumschiff aus oder erstellen Sie ein neues.");
		}
		waitForEnter();
		scanner.nextLine();
	}

	/**
	 * Wartet auf die Eingabe des Benutzers, um fortzufahren.
	 */
	private static void waitForEnter() {
		System.out.println("Drücken Sie Enter, um fortzufahren...");
		scanner.nextLine();
	}

	/**
	 * Entfernt ein Raumschiff aus der Flotte. (4)
	 *
	 * @return Das entfernte Raumschiff.
	 */
	private static void removeSpaceShip() {
		if (spaceShipArray[0] == null) {
			System.out.println("Keine Raumschiffe zum entfernen vorhanden!");
			System.out.println();
			System.out.println("Möchten Sie ein Raumschiff anlegen?");
			System.out.println("1. Ja");
			System.out.println("2. Zurück zum Hauptmenü");

			int choice = readUserInput();
			if (choice == 1) {
				createSpaceShip();
			} else if (choice == 2) {
				showStandardMenu();
			} else {
				System.out.println("Ungültige Eingabe.");
				System.out.println("Bitte wählen Sie eine der beiden Optionen aus.");
				System.out.println("Sie werden zum Hauptmenü geleitet.");
				showStandardMenu();
			}
			return;
		}

		System.out.println("Wählen Sie ein Raumschiff zum Entfernen aus");
		for (int i = 0; i < spaceShipArray.length; i++) {
			if (spaceShipArray[i] != null) {
				System.out.println(i + 1 + ": " + spaceShipArray[i].getName());
			}
		}
		int choice = scanner.nextInt();
		if (choice > 0 && choice <= spaceShipArray.length && spaceShipArray[choice - 1] != null) {
			System.out.println("Ihr Raumschiff " + spaceShipArray[choice - 1].getName() + " wurde erfolgreich entfernt.");
			spaceShipArray[choice - 1] = null;
		} else {
			System.out.println("Ungültige Auswahl.");
			System.out.println("Bitte wählen Sie ein vorhandenes Raumschiff aus.");
		}
		waitForEnter();
		scanner.nextLine();
	}

	/**
	 * Zeigt die Daten aller Raumschiffe an. (3)
	 *
	 * @return Die Daten aller Raumschiffe.
	 */
	private static void shipDataAll() {
		if (spaceShipArray[0] == null) {
			System.out.println("Keine Raumschiffe vorhanden!");
			System.out.println("Möchten Sie ein Raumschiff anlegen?");
			System.out.println("1. Ja");
			System.out.println("2. Zurück zum Hauptmenü");

			int choice = readUserInput();
			if (choice == 1) {
				createSpaceShip();
			} else if (choice == 2) {
				showStandardMenu();
			} else {
				System.out.println("Ungültige Eingabe.");
				System.out.println("Bitte wählen Sie eine der beiden Optionen aus.");
				System.out.println("Sie werden zum Hauptmenü geleitet.");
				showStandardMenu();
			}
			return;
		}

		System.out.println("Hier sind die Daten aller angelegten Raumschiffe: ");
		for (int i = 0; i < spaceShipArray.length; i++) {
			if (spaceShipArray[i] != null) {
				SpaceShip currentSpaceShip = spaceShipArray[i];
				if (currentSpaceShip != null) { // Überprüfen Sie, ob das Objekt nicht null ist
					System.out.println("Raumschiff " + (i + 1) + ":");
					System.out.println("Name: " + currentSpaceShip.getName());
					System.out.println("Health Points Max: " + currentSpaceShip.getHealthPointsMax());
					System.out.println("Experience Points: " + currentSpaceShip.getExperiencePoints());
					System.out.println("Superpower: " + currentSpaceShip.getSuperpower().getName());
					System.out.println("Ready To Fight: " + currentSpaceShip.isReadyToFight());
					System.out.println("Alive: " + currentSpaceShip.isAlive());
					System.out.println("--------------------");
				}
			}
		}
		waitForEnter();
	}

	/**
	 * Implementiere Patrouillenflug (5)
	 * (1) Patrouillieren: Das Raumschiff fliegt 5 Sekunden lang und kann dabei auf ein anderes Raumschiff treffen.
	 * (2) Begegnung: Das Raumschiff trifft auf ein anderes Raumschiff. Mit einer Wahrscheinlichkeit von 60% handelt es sich um ein feindliches Raumschiff.
	 * (3) Kampf: Das Raumschiff kämpft mit seiner Superkraft. Wenn das feindliche Raumschiff besiegt wird, erhält das eigene Raumschiff einen Erfahrungspunkt.
	 * (4) Flucht: Das Raumschiff flieht mit einer Wahrscheinlichkeit von 80%. Wenn die Flucht gelingt, wird der Kampf beendet.
	 *
	 * @return Die Daten aller Raumschiffe.
	 */
	private static void Patrol() {
		if (countAliveSpaceships() == 0) {
			System.out.println("Sie müssen mindestens ein Raumschiff haben, um eine Patrouille starten zu können.");
			System.out.println();
			System.out.println("Möchten Sie ein Raumschiff anlegen?");
			System.out.println("1. Ja");
			System.out.println("2. Zurück zum Hauptmenü");

			int choice = readUserInput();
			if (choice == 1) {
				createSpaceShip();

			} else if (choice == 2) {
				showPatrolMenu();
			} else {
				System.out.println("Ungültige Eingabe.");
				showPatrolMenu();
			}
			return;
		}
		System.out.println("Patrouillen-Flug gestartet! ");
		for (int i = 0; i < 5; i++) {
			System.out.print(".");
			try {
				Thread.sleep(1000); // Warte 1 Sekunde
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		double encounterShip = Math.random();
		if (encounterShip <= 0.9) {
			System.out.println("Sie sind einem unbekannten Raumschiff begegnet! ");
			System.out.println();
			double encounterEnemy = Math.random();
			if (encounterEnemy <= 0.6) {
				System.out.println("Vorsicht! Es handelt sich um ein feindliches Schiff!");
				System.out.println();
				// Das gegnerische Raumschiff greift die Flotte an
				System.out.println("Das Feindliche Raumschiff greift Ihre Flotte an");

				// Überprüfen, ob das Spieler-Raumschiff besiegt wurde
				SpaceShip selectedSpaceship = chooseSpaceShip();
				if (selectedSpaceship != null && selectedSpaceship.isAlive()) {
					SpaceShip enemySpaceShip = new EnemySpaceShip(selectedSpaceship.getName());
					Superpower superpower = new Superpower("Feindliche Superkraft", "Unbekannte Attacke");
					enemySpaceShip.setSuperpower(superpower);

					if (selectedSpaceship.isReadyToFight()) {

						// Das Spieler-Raumschiff greift den Feind an
						battle(selectedSpaceship, enemySpaceShip);

						// Überprüfen, ob der Feind besiegt wurde
						if (!enemySpaceShip.isAlive()) {
							System.out.println("Sie haben das feindliche Raumschiff erfolgreich besiegt");
							selectedSpaceship.setExperiencePoints(selectedSpaceship.getExperiencePoints() + 1);
							System.out.println("Ihr Raumschiff hat einen Erfahrungspunkt erhalten");
						} else {
							System.out.println("Das feindliche Raumschiff hat überlebt und greift erneut an!");
						}
					} else {
						System.out.println("Das Raumschiff ist nicht bereit für den Kampf");
					}
				} else {
					System.out.println("Ihr Raumschiff wurde vom feindlichen Raumschiff zerstört!");
				}
			} else {
				System.out.println("Das Raumschiff ist freundlich gesinnt. Kein Angriff erfolgt.");
				System.out.println("Wie möchten Sie weiter vorgehen?");
				showPatrolMenu();
			}
		} else {
			System.out.println("Keine Begegnung mit anderen Raumschiffen während der Patrouille ");
			System.out.println();
			System.out.println("Möchten Sie noch eine Patrouille starten?");
			System.out.println("1. Ja");
			System.out.println("2. Zurück zum Patrouillen-menü");

			int choice = readUserInput();
			if (choice == 1) {
				Patrol();
			} else if (choice == 2) {
				showPatrolMenu();
			} else {
				System.out.println("Ungültige Eingabe.");
				System.out.println("Bitte wählen Sie eine der beiden Optionen aus.");
				showPatrolMenu();
			}
		}
	}

	/**
	 * Zählt die Anzahl der lebenden Raumschiffe in der Flotte.
	 *
	 * @return Die Anzahl der lebenden Raumschiffe in der Flotte.
	 */
	private static int countAliveSpaceships() {
		int count = 0;
		for (SpaceShip ship : spaceShipArray) {
			if (ship != null && ship.isAlive()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Lässt den Benutzer ein Raumschiff auswählen, das an der Patrouille teilnehmen soll.
	 *
	 * @return Das ausgewählte Raumschiff.
	 */


	/**
	 * Lässt den Benutzer ein Raumschiff auswählen, das an der Patrouille teilnehmen soll.
	 *
	 * @return Das ausgewählte Raumschiff.
	 */
	private static SpaceShip chooseSpaceShip() {
		System.out.println("Welches Raumschiff möchten Sie in den Kampf schicken?");
		for (int i = 0; i < spaceShipArray.length; i++) {
			if (spaceShipArray[i] != null) {
				System.out.println((i + 1) + ": " + spaceShipArray[i].getName());
			}
		}
		int choice = scanner.nextInt();
		scanner.nextLine(); // Verarbeite den Zeilenumbruch nach dem Einlesen der Zahl
		if (choice > 0 && choice <= spaceShipArray.length && spaceShipArray[choice - 1] != null) {
			return spaceShipArray[choice - 1];
		} else {
			System.out.println("Ungültige Auswahl. Kein Raumschiff ausgewählt");
			return null;
		}
	}

	/**
	 * Kampfdialog zwischen zwei Raumschiffen
	 *
	 * @param playerShip
	 * @param enemyShip
	 */
	private static void playerAttack(SpaceShip playerShip, SpaceShip enemyShip) {
		int playerDamage = playerShip.attack(enemyShip);
		System.out.println("Mit der Superkraft " + playerShip.getSuperpower().getName() +
				" greift Ihr Raumschiff " + playerShip.getName() + " das feindliche Raumschiff an.");
		System.out.println("Ihr Raumschiff hat " + playerDamage + " Schaden verursacht. Verbleibende Lebenspunkte des Gegners: "
				+ enemyShip.getHealthPointsCurrent());
	}

	// Methode für den Angriff des Gegners
	private static void enemyAttack(SpaceShip playerShip, SpaceShip enemyShip) {
		int enemyDamage = enemyShip.attack(playerShip);
		System.out.println("Der Gegner greift dein Raumschiff " + playerShip.getName() +
				" an und verursacht " + enemyDamage + " Schaden.");
		playerShip.takeDamage(enemyDamage);
	}

	// battle-Methode mit separaten Angriffen für Spieler und Gegner
	private static void battle(SpaceShip playerShip, SpaceShip enemyShip) {
		boolean inBattle = true;
		boolean enemyAttacked = false;

		// Rufe die enemyAttack-Methode außerhalb der Schleife auf, um den ersten Angriff des Gegners zu initiieren
		enemyAttack(playerShip, enemyShip);
		enemyAttacked = true;

		while (inBattle && enemyShip.isAlive()) {
			if (playerShip != null && playerShip.isAlive() && playerShip.isReadyToFight()) {
				System.out.println("Wählen Sie eine Aktion:");
				System.out.println("(1) Kämpfen");
				System.out.println("(2) Fliehen");

				int choice = readUserInput();

				if (choice == 1) {
					if (!enemyAttacked) {
						// enemyAttack(playerShip, enemyShip); // Entferne diesen Aufruf hier
						// enemyAttacked = true; // Entferne diesen Aufruf hier
					}

					playerAttack(playerShip, enemyShip);

					if (!enemyShip.isAlive()) {
						System.out.println("Sie haben das feindliche Raumschiff erfolgreich besiegt");
						playerShip.setExperiencePoints(playerShip.getExperiencePoints() + 1);
						System.out.println("Ihr Raumschiff hat einen Erfahrungspunkt erhalten");
					}
				} else if (choice == 2) {
					if (playerShip.flee()) {
						System.out.println("Ihr Raumschiff konnte erfolgreich fliehen!");
						System.out.println("Wie möchten Sie weiter vorgehen?");
						showPatrolMenu();
					} else {
						enemyAttacked = false;
						enemyAttack(playerShip, enemyShip);
						enemyAttacked = true;
					}
				} else {
					System.out.println("Ungültige Auswahl. Ihr Raumschiff greift automatisch an.");
					if (!enemyAttacked) {
						// enemyAttack(playerShip, enemyShip); // Entferne diesen Aufruf hier
						// enemyAttacked = true; // Entferne diesen Aufruf hier
					}
				}
			} else {
				System.out.println("Ihr Raumschiff wurde zerstört und muss regeneriert werden!");
				showPatrolMenu();
				inBattle = false;
			}

			enemyAttacked = false;
		}
	}

	/**
	 * Zeigt die Daten eines Raumschiffs an, das der Benutzer auswählt. (2)
	 * 
	 * @return Das ausgewählte Raumschiff.
	 */
	private static void regenerateShip(SpaceShip spaceShip) {
		if (!spaceShip.isAlive()) {
			spaceShip.regenerate();
			System.out.println("Ihr Raumschiff " + spaceShip.getName() + " wurde erfolgreich geheilt");
		}
		waitForEnter();
	}

	/**
	 * Zeigt die Daten aller Raumschiffe an. (3)
	 * 
	 * @return Die Daten aller Raumschiffe.
	 */
	private static void shipDataLifePoints() {
		if (isEmpty(spaceShipArray)) {
			System.out.println("Keine Raumschiffe vorhanden!");
			System.out.println("Möchten Sie ein Raumschiff anlegen?");
			System.out.println("1. Ja");
			System.out.println("2. Zurück zum Hauptmenü");

			int choice = readUserInput();
			if (choice == 1) {
				createSpaceShip();
			}
			return;
		}

		System.out.println("Hier sind die Daten aller Raumschiffe und Ihre aktuellen Lebenspunkte:");
		for (int i = 0; i < spaceShipArray.length; i++) {
			SpaceShip currentSpaceShip = spaceShipArray[i];
			if (currentSpaceShip != null) {
				System.out.println((i + 1) + ": " + currentSpaceShip.getName() +
						" - Lebenspunkte: " + currentSpaceShip.getHealthPointsCurrent());
		//	} else {
		//		System.out.println("Raumschiff an Position " + (i + 1) + " ist null.");
			}
		}

		System.out.println("Wählen Sie ein Raumschiff zum Regenerieren aus:");
		int choice = readUserInput();
		if (choice > 0 && choice <= spaceShipArray.length) {
			SpaceShip selectedSpaceShip = spaceShipArray[choice - 1];
			regenerateShip(selectedSpaceShip);
			System.out.println("erfolgreich geheilt");
			System.out.println(selectedSpaceShip.getName() + " - Lebenspunkte: " + selectedSpaceShip.getHealthPointsCurrent());
			System.out.println("Wählen Sie ein Raumschiff zum Regenerieren aus:");
			return;
		} else {
			System.out.println("Ungültige Auswahl.");
		}
	}

	/**
	 * Überprüft, ob das übergebene Array leer ist.
	 * 
	 * @param array
	 * @return
	 */
	private static boolean isEmpty(SpaceShip[] array) {
		for (SpaceShip spaceShip : array) {
			if (spaceShip != null) {
				return false;
			}
		}
		return true;
	}
}