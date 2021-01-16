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
public class BulkTableModel {
        private final int ID;
        private final String Artikelcode;
        private final String Locatie;
        private final String Aantal;
        private final String Omschrijving;
        private final String Eenheid;
        private final String Initiaal;
        private final String Datum;
        private final String Le;

    public BulkTableModel(int ID,String Artikelcode,  String Locatie, String Aantal, String Omschrijving, String Eenheid, String Initiaal,String Datum, String Le) {
        this.ID = ID;
        this.Aantal = Aantal;
        this.Locatie = Locatie;
        this.Artikelcode = Artikelcode;
        this.Omschrijving = Omschrijving;
        this.Eenheid = Eenheid;
        this.Initiaal = Initiaal;
        this.Datum = Datum;
        this.Le = Le;
    }


    public int getID() {
        return ID;
    }

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

    public String getDatum() {
        return Datum;
    }
    
    public String getLe() {
        return Le;
    }
    
    
    
    
}
