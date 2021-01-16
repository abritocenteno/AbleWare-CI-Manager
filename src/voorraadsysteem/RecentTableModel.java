/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voorraadsysteem;

/**
 *
 * @author abel
 */
public class RecentTableModel {
//        private final String ID;
        private final String Artikelcode;
        private final String Locatie;
        private final String Omschrijving;
        private final String Aantal;      
        private final String Eenheid;
        private final String Initiaal;


    public RecentTableModel(String Artikelcode,  String Locatie, String Omschrijving,String Aantal, String Eenheid, String Initiaal) {
      //  this.ID = ID;
        this.Aantal = Aantal;
        this.Locatie = Locatie;
        this.Omschrijving = Omschrijving;
        this.Artikelcode = Artikelcode; 
        this.Eenheid = Eenheid;
        this.Initiaal = Initiaal;
    }


//    public String getID() {
//        return ID;
//    }

    public String getAantal() {
        return Aantal;
    }

    public String getLocatie() {
        return Locatie;
    }

    public String getArtikelcode() {
        return Artikelcode;
    }

    public String getOmschrijving() {
        return Omschrijving;
    }

    public String getEenheid() {
        return Eenheid;
    }

    public String getInitiaal() {
        return Initiaal;
    }

    
}
