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
public class LoadTableModel {
   public final String  SHIPMENTNUMBER, LOADINGNUMBER;
   public final String ID, SHIPMENTDATE, DIMENSIONS, SENDER, SENDERADDRESS, RECIPIENT, RECIPIENTADDRESS, LOCATION, HANDLER, SHIPMENTLOGGED;

    public LoadTableModel(String ID, 
            String Shipmentnumber, 
            String Shipmentdate, 
            String Dimensions, 
            String Sender, 
            String Senderaddress, 
            String Recipient, 
            String Recipientaddress, 
            String Location, 
            String Handler, 
            String Shipmentlogged,
            String Loadingnumber) {
        
        this.ID = ID;
        this.SHIPMENTNUMBER = Shipmentnumber;
        this.SHIPMENTDATE = Shipmentdate;
        this.DIMENSIONS = Dimensions;
        this.SENDER = Sender;
        this.SENDERADDRESS = Senderaddress;
        this.RECIPIENT = Recipient;
        this.RECIPIENTADDRESS = Recipientaddress;
        this.LOCATION = Location;
        this.HANDLER = Handler;
        this.SHIPMENTLOGGED = Shipmentlogged;
        this.LOADINGNUMBER = Loadingnumber;
        
    }

    public String getID() {
        return ID;
    }

    public String getShipmentnumber() {
        return SHIPMENTNUMBER;
    }

    public String getShipmentdate() {
        return SHIPMENTDATE;
    }

    public String getDimensions() {
        return DIMENSIONS;
    }

    public String getSender() {
        return SENDER;
    }

    public String getSenderaddress() {
        return SENDERADDRESS;
    }

    public String getRecipient() {
        return RECIPIENT;
    }

    public String getRecipientaddress() {
        return RECIPIENTADDRESS;
    }

    public String getLocation() {
        return LOCATION;
    }

    public String getHandler() {
        return HANDLER;
    }

    public String getShipmentlogged() {
        return SHIPMENTLOGGED;
    }
    
    public String getLoadingnumber() {
        return LOADINGNUMBER;
    }
   
   
}
