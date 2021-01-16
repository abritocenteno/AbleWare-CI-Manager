/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voorraadsysteem;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author abel
 */
public class TableModel2 {
   public final String  SHIPMENTNUMBER;
   public final String ID,  DIMENSIONS, SENDER, SENDERADDRESS, RECIPIENT, RECIPIENTADDRESS, LOCATION, HANDLER, SHIPMENTLOGGED;
   public final String SHIPMENTDATE;

    public TableModel2(String ID, 
                        String Shipmentnumber, 
                        String Shipmentdate, 
                        String Dimensions, 
                        String Sender, 
                        String Senderaddress, 
                        String Recipient, 
                        String Recipientaddress, 
                        String Location, 
                        String Handler, 
                        String Shipmentlogged) {
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
   
   
}
