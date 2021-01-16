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
public class ProductieTableModel {
        private final int ID;
        private final String Leverancier;
        private final String Materiaal;
        private final String Rolbreedte;
        private final String Locatie;
        

    public ProductieTableModel(int ID,String Leverancier,  String Materiaal, String Rolbreedte, String Locatie) {
        this.ID = ID;
        this.Leverancier = Leverancier;
        this.Materiaal = Materiaal;
        this.Rolbreedte = Rolbreedte;
        this.Locatie = Locatie;
       
    }


    public int getID() {
        return ID;
    }

    public String getLeverancier() {
        return Leverancier;
    }

    public String getMateriaal() {
        return Materiaal;
    }

    public String getRolbreedte() {
        return Rolbreedte;
    }

    public String getLocatie() {
        return Locatie;
    }
    
}
