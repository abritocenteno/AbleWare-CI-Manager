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
public class TableModelPickSearch {
    
    
   public final String ID, Locatie, Artikelcode, Omschrijving, Eenheid, Hal;

    public TableModelPickSearch(
            String ID, 
            String Locatie, 
            String Artikelcode, 
            String Omschrijving, 
            String Eenheid, 
            String Hal)
            
    {
        this.ID = ID;
        this.Locatie = Locatie;
        this.Artikelcode = Artikelcode;
        this.Omschrijving = Omschrijving;
        this.Eenheid = Eenheid;
        this.Hal = Hal;
        
    }

    public String getID() {
        return ID;
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

    public String getHal() {
        return Hal;
    }
    
   

    
    
    
    
}
