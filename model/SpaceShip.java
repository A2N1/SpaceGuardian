package model;
import java.util.UUID;
import java.util.Random;

    /**
     * Konstruktor, um name und superpower zu setzen
     * 
     * @param name
     * @param superpower
     */
public class SpaceShip {
    private String id = UUID.randomUUID().toString();
    private String name;
    private int healthPointsCurrent = 50;
    private int healthPointsMax = 50;
    private int experiencePoints = 0;
    private Superpower superpower;
    private boolean readyToFight = true;
    private boolean inFight = false;
    private boolean alive = true;

        /**
         * Konstruktor, um name und superpower zu setzen
         *
         * @param name
         * @param superpower
         */
    public SpaceShip(String name, Superpower superpower) {

        this.name = name;
        this.superpower = superpower;
    }

        /**
         * Getter für id - Abruf der id des Raumschiffs
         *
         * @return
         */
    public String getId() {
        return id;
    }

        /**
         * Setter für id (Normalerweise sollte die id nicht verändert werden können) - raus?
         *
         * @param id
         */
    public String getName() {
        return name;
    }

        /**
         * Setter für name - Setzen des Namens des Raumschiffs
         *
         * @param name
         */
    public void setName(String name) {
        this.name = name;
    }

        /**
         * Getter für healthPointsCurrent - Abruf der aktuellen Lebenspunkte des Raumschiffs
         *
         * @return
         */
    public int getHealthPointsCurrent() {
        return healthPointsCurrent;
    }

        /**
         * Setter für healthPointsCurrent - Setzen der aktuellen Lebenspunkte des Raumschiffs
         *
         * @param healthPointsCurrent
         */
    public void setHealthPointsCurrent(int healthPointsCurrent) {
        this.healthPointsCurrent = healthPointsCurrent;
    }

        /**
         * Getter für healthPointsMax - Abruf der maximalen Lebenspunkte des Raumschiffs
         *
         * @return
         */
    public int getHealthPointsMax() {
        return healthPointsMax;
    }

        /**
         * Setter für healthPointsMax - Setzen der maximalen Lebenspunkte des Raumschiffs
         *
         * @param healthPointsMax
         */
    public void setHealthPointsMax(int healthPointsMax) {
        this.healthPointsMax = healthPointsMax;
    }

        /**
         * Getter für experiencePoints - Abruf der Erfahrungspunkte des Raumschiffs
         *
         * @return
         */
    public int getExperiencePoints() {
        return experiencePoints;
    }

        /**
         * Setter für experiencePoints - Setzen der Erfahrungspunkte des Raumschiffs
         *
         * @param experiencePoints
         */
    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
        // Berechne die neuen maximalen Lebenspunkte basierend auf Erfahrungspunkten
        this.healthPointsMax = 50 + (experiencePoints / 3);
    }

        /**
         * Getter für superpower - Abruf der Superkraft des Raumschiffs
         *
         * @return
         */
    public Superpower getSuperpower() {
        return superpower;
    }

        /**
         * Setter für superpower - Setzen der Superkraft des Raumschiffs
         *
         * @param superpower
         */
    public void setSuperpower(Superpower superpower) {
        this.superpower = superpower;
    }

        /**
         * toString-Methode, um das Raumschiff als String darzustellen
         *
         * @return
         */
    public boolean isReadyToFight() {
        return readyToFight;
    }

        /**
         * Setter für readyToFight - Setzen, ob das Raumschiff bereit für den Kampf ist
         *
         * @param readyToFight
         */
    public void setReadyToFight(boolean readyToFight) {
        this.readyToFight = readyToFight;
    }

        /**
         * Getter für inFight - Abruf, ob das Raumschiff sich im Kampf befindet
         *
         * @return
         */
    public boolean isInFight() {
        return inFight;
    }

        /**
         * Setter für inFight - Setzen, ob das Raumschiff sich im Kampf befindet
         *
         * @param inFight
         */
    public void setInFight(boolean inFight) {
        this.inFight = inFight;
    }

        /**
         * Getter für alive - Abruf, ob das Raumschiff noch lebt
         *
         * @return
         */
    public boolean isAlive() {
        return alive;
    }

        /**
         * Setter für alive - Setzen, ob das Raumschiff noch lebt
         *
         * @param alive
         */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

        /**
         * toString-Methode, um das Raumschiff als String darzustellen
         *
         * @return
         */
        public int attack(SpaceShip enemyShip) {
            // Erzeuge eine Zufallszahlengenerator-Instanz
            Random rand = new Random();

            // Generiere eine Zufallszahl zwischen 1 (inklusive) und 6 (inklusive)
            int damage = rand.nextInt(6) + 1; // Wir möchten Schaden von 1 bis 6

            // Überprüfe, ob ein kritischer Treffer erfolgt
            if (rand.nextInt(100) < 2 * experiencePoints) {
                damage *= 2; // Schaden verdoppelt
                System.out.println("Kritischer Treffer!");
            }

           //System.out.println("Der Gegner greift dein Raumschiff " + name + " an und verursacht " + damage + " Schaden.");
            enemyShip.takeDamage(damage);
            return damage;
        }

        /**
         * Methode, um Schaden am Raumschiff zu verursachen
         * 
         * @param damage
         */
    public void takeDamage(int damage) {
        // Ausgabe vor der Schadensanwendung
        //System.out.println(name + " vor Schaden: " + healthPointsCurrent);

        // Verringere die aktuellen Lebenspunkte um den erhaltenen Schaden
        healthPointsCurrent -= damage;

        // Ausgabe nach der Schadensanwendung
       //System.out.println(name + " nach Schaden: " + healthPointsCurrent);

        // Überprüfe, ob das Raumschiff zerstört wurde
        if (healthPointsCurrent <= 0) {
            healthPointsCurrent = 0; // Setze die Lebenspunkte auf 0, um negative Werte zu vermeiden
            alive = false; // Setze den Status "lebendig" auf false
            System.out.println(name + " wurde zerstört!");
        } else {
            // Gib eine Meldung über den erlittenen Schaden aus
            System.out.println(name + " erleidet " + damage + " Schaden. Verbleibende Lebenspunkte: " + healthPointsCurrent);
        }
    }

        /**
         * Raumschiff flieht mit 80% Wahrscheinlichkeit aus dem Kampf
         * 
         * @return
         */
    public boolean flee() {
        Random rand = new Random();
        return rand.nextInt(100) < 80;
    }

        /**
         * Methode, um das Raumschiff zu regenerieren
         */
    public void regenerate() {
    System.out.println(name + " regeneriert sich und ist für den nächsten Kampf nicht verfügbar.");
        readyToFight = false;
        inFight = false;
        healthPointsCurrent = healthPointsMax;
    }
}