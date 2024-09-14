package model;
import java.util.UUID;
public class Superpower  {


        private String id;
        private String name;
        private String actionDescription;
        //weitere Attribute? Wie oft kann die Superkraft eingesetzt werden? Wie viel Schaden richtet sie an? Wie viel Leben kann sie wiederherstellen?

        /**
         * Konstruktor, um name und actionDescription zu setzen
         * 
         * @param id
         * @param name
         * @param actionDescription
         */
        public Superpower(String name, String actionDescription) {
            this.id = UUID.randomUUID().toString();
            this.name = name;
            this.actionDescription = actionDescription;
        }

        /**
         * Getter für id - Abruf der id der Superkraft
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
         * Setter für name - Setzen des Namens der Superkraft
         * 
         * @param name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Getter für actionDescription - Abruf der Beschreibung der Superkraft
         * 
         * @return
         */
        public String getActionDescription() {
            return actionDescription;
        }

        /**
         * Setter für actionDescription - Setzen der Beschreibung der Superkraft
         * 
         * @param actionDescription
         */
        public void setActionDescription(String actionDescription) {
            this.actionDescription = actionDescription;
        }

        /**
         * toString-Methode, um die Superkraft als String darzustellen
         * 
         * @return
         */
    public String toString() {
        return  name;
    }

        /**
         * equals-Methode, um zwei Superkräfte zu vergleichen
         * 
         * @param obj
         */
    private static final Superpower[] definedSuperpower={
            new Superpower("Laserstrahl","schießt tötliche Strahlen "),
            new Superpower("Höllenfeuer", "Blaue Flammen aus der unterwelt, die nicht gelöscht werden können"),
            new Superpower("Zeus-Blitz", "zerstörerischer Blizt vom Olympus Gott persönlich"),
            new Superpower("Kamehameha","Son-Gokus spezielle Energiewelle"),
            new Superpower("gomu gomu no pistol", "Pistolenschuss artige Attacke")
    };

            /**
             * Getter für definedSuperpower - Abruf der definierten Superkräfte
             * 
             * @return Superpower[]
             */
        public static Superpower[] getdefinedSuperpower() {
            return definedSuperpower;
    }

}