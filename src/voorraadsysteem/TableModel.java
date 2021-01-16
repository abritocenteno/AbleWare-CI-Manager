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
public class TableModel {
    
    
   public final String ID;
   public final String Locatie, Artikelcode, Omschrijving, Eenheid, Hal, Code, Aantal, Initiaal, Max;

    public TableModel(
            String ID, 
            String Locatie, 
            String Artikelcode, 
            String Omschrijving, 
            String Eenheid, 
            String Hal, 
            String Code, 
            String Aantal, 
            String Initiaal, 
            String Max) 
    {
        this.ID = ID;
        this.Locatie = Locatie;
        this.Artikelcode = Artikelcode;
        this.Omschrijving = Omschrijving;
        this.Eenheid = Eenheid;
        this.Hal = Hal;
        this.Code = Code;
        this.Aantal = Aantal;
        this.Initiaal = Initiaal;
        this.Max = Max;
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
    
    public String getOescode() {
        return Code;
    }
    
    public String getAantal() {
        return Aantal;
    }
    
    public String getInitiaal() {
        return Initiaal;
    }
    
    public String getMax(){
        return Max;
    }

    
    
    
    
}
