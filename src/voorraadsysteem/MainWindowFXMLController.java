/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voorraadsysteem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;

/**
 * FXML Controller class
 *
 * @author abel
 */
public class MainWindowFXMLController implements Initializable {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
// Loading List Table
    @FXML
    private TableView<LoadTableModel> table_load;

    @FXML
    private TableColumn<LoadTableModel, String> col_ID1;

    @FXML
    private TableColumn<LoadTableModel, String> col_shipnr1;

    @FXML
    private TableColumn<LoadTableModel, String> col_shipdate1;

    @FXML
    private TableColumn<LoadTableModel, String> col_dim1;

    @FXML
    private TableColumn<LoadTableModel, String> col_sender1;

    @FXML
    private TableColumn<LoadTableModel, String> col_sendaddr1;

    @FXML
    private TableColumn<LoadTableModel, String> col_recipient1;

    @FXML
    private TableColumn<LoadTableModel, String> col_recaddr1;

    @FXML
    private TableColumn<LoadTableModel, String> col_location1;

    @FXML
    private TableColumn<LoadTableModel, String> col_handler1;

    @FXML
    private TableColumn<LoadTableModel, String> col_logged1;

    @FXML
    private TableColumn<LoadTableModel, String> col_load;

    //Loading List Fields
    @FXML
    private JFXTextField idTxt1;

    @FXML
    private JFXTextField shipNrTxt1;

    @FXML
    private JFXDatePicker shipDate1;

    @FXML
    private JFXTextArea dimTxt1;

    @FXML
    private JFXTextArea sendTxt1;

    @FXML
    private JFXTextArea recTxt1;

    @FXML
    private JFXTextField locTxt1;

    @FXML
    private JFXTextField handleTxt1;

    @FXML
    private JFXTextField logTxt1;

    @FXML
    private JFXTextArea sendAddrTxt1;

    @FXML
    private JFXTextArea recAddr1;

    @FXML
    private JFXTextField searchFilter1;

    @FXML
    private JFXTextField loadNumber1;

// Table
    @FXML
    private TableView<TableModel2> table_main;

    @FXML
    private TableColumn<TableModel2, String> col_ID;

    @FXML
    private TableColumn<TableModel2, String> col_shipnr;

    @FXML
    private TableColumn<TableModel2, String> col_shipdate;

    @FXML
    private TableColumn<TableModel2, String> col_dim;

    @FXML
    private TableColumn<TableModel2, String> col_sender;

    @FXML
    private TableColumn<TableModel2, String> col_sendaddr;

    @FXML
    private TableColumn<TableModel2, String> col_recipient;

    @FXML
    private TableColumn<TableModel2, String> col_recaddr;

    @FXML
    private TableColumn<TableModel2, String> col_location;

    @FXML
    private TableColumn<TableModel2, String> col_handler;

    @FXML
    private TableColumn<TableModel2, String> col_logged;

    //Textfields
    @FXML
    private JFXTextField searchFilter;

    @FXML
    private JFXTextField idTxt;

    @FXML
    private JFXTextField shipNrTxt;

    @FXML
    private JFXDatePicker shipDate;

    @FXML
    private JFXTextArea dimTxt;

    @FXML
    private JFXTextArea sendTxt;

    @FXML
    private JFXTextArea sendAddrTxt;

    @FXML
    private JFXTextArea recTxt;

    @FXML
    private JFXTextArea recAddr;

    @FXML
    private JFXTextField locTxt;

    @FXML
    private JFXTextField handleTxt;

    @FXML
    private JFXTextField logTxt;

    @FXML
    private JFXTextField loadNumber;

    //Buttons
    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton editBtn;

    @FXML
    private JFXButton relocateBtn;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton loadBtn;

    ObservableList<TableModel2> shipments = FXCollections.observableArrayList();
    FilteredList filtered = new FilteredList(shipments, e -> true);

    ObservableList<LoadTableModel> loadings = FXCollections.observableArrayList();
    FilteredList filteredloadings = new FilteredList(loadings, e -> true);

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Populate Loading Table
        String loadsql = "SELECT * FROM LOADINGLIST";

        try {

            Connection conn = ConnectDatabase.conDB();
            ResultSet rs = conn.createStatement().executeQuery(loadsql);

            while (rs.next()) {
                loadings.add(new LoadTableModel(
                        rs.getString("ID"),
                        rs.getString("SHIPMENTNUMBER"),
                        rs.getString("SHIPMENTDATE"),
                        rs.getString("DIMENSIONS"),
                        rs.getString("SENDER"),
                        rs.getString("SENDERADDRESS"),
                        rs.getString("RECIPIENT"),
                        rs.getString("RECIPIENTADDRESS"),
                        rs.getString("LOCATION"),
                        rs.getString("HANDLER"),
                        rs.getString("SHIPMENTLOGGED"),
                        rs.getString("LOADINGNUMBER")
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainWindowFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_ID1.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_shipnr1.setCellValueFactory(new PropertyValueFactory<>("Shipmentnumber"));
        col_shipdate1.setCellValueFactory(new PropertyValueFactory<>("Shipmentdate"));
        col_dim1.setCellValueFactory(new PropertyValueFactory<>("Dimensions"));
        col_sender1.setCellValueFactory(new PropertyValueFactory<>("Sender"));
        col_sendaddr1.setCellValueFactory(new PropertyValueFactory<>("Senderaddress"));
        col_recipient1.setCellValueFactory(new PropertyValueFactory<>("Recipient"));
        col_recaddr1.setCellValueFactory(new PropertyValueFactory<>("Recipientaddress"));
        col_location1.setCellValueFactory(new PropertyValueFactory<>("Location"));
        col_handler1.setCellValueFactory(new PropertyValueFactory<>("Handler"));
        col_logged1.setCellValueFactory(new PropertyValueFactory<>("Shipmentlogged"));
        col_load.setCellValueFactory(new PropertyValueFactory<>("Loadingnumber"));

        table_load.setItems(loadings);
        table_load.refresh();

        //Populate from Database Table
        String sql = "SELECT * FROM SHIPMENTS";

        try {

            Connection conn = ConnectDatabase.conDB();
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                shipments.add(new TableModel2(
                        rs.getString("ID"),
                        rs.getString("SHIPMENTNUMBER"),
                        rs.getString("SHIPMENTDATE"),
                        rs.getString("DIMENSIONS"),
                        rs.getString("SENDER"),
                        rs.getString("SENDERADDRESS"),
                        rs.getString("RECIPIENT"),
                        rs.getString("RECIPIENTADDRESS"),
                        rs.getString("LOCATION"),
                        rs.getString("HANDLER"),
                        rs.getString("SHIPMENTLOGGED")
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainWindowFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_shipnr.setCellValueFactory(new PropertyValueFactory<>("Shipmentnumber"));
        col_shipdate.setCellValueFactory(new PropertyValueFactory<>("Shipmentdate"));
        col_dim.setCellValueFactory(new PropertyValueFactory<>("Dimensions"));
        col_sender.setCellValueFactory(new PropertyValueFactory<>("Sender"));
        col_sendaddr.setCellValueFactory(new PropertyValueFactory<>("Senderaddress"));
        col_recipient.setCellValueFactory(new PropertyValueFactory<>("Recipient"));
        col_recaddr.setCellValueFactory(new PropertyValueFactory<>("Recipientaddress"));
        col_location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        col_handler.setCellValueFactory(new PropertyValueFactory<>("Handler"));
        col_logged.setCellValueFactory(new PropertyValueFactory<>("Shipmentlogged"));

        table_main.setItems(shipments);
        table_main.refresh();

    }

    //Populate Textfields from Table
    @FXML
    private void TableMainMouseClicked(MouseEvent event) {
        TableModel2 table = table_main.getItems().get(table_main.getSelectionModel().getSelectedIndex());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = table.getShipmentdate();

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);

        idTxt.setText(table.getID());
        shipNrTxt.setText(table.getShipmentnumber());
        shipDate.setValue(localDate);
        dimTxt.setText(table.getDimensions());
        sendTxt.setText(table.getSender());
        sendAddrTxt.setText(table.getSenderaddress());
        recTxt.setText(table.getRecipient());
        recAddr.setText(table.getRecipientaddress());
        locTxt.setText(table.getLocation());
        handleTxt.setText(table.getHandler());
        logTxt.setText(table.getShipmentlogged());
    }
    
    //For Loading List (might be unnecessary)
    @FXML
    private void TableLoadMouseClicked(MouseEvent event) {
        LoadTableModel loadTable = table_load.getItems().get(table_load.getSelectionModel().getSelectedIndex());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = loadTable.getShipmentdate();

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);

        idTxt.setText(loadTable.getID());
        shipNrTxt.setText(loadTable.getShipmentnumber());
        shipDate.setValue(localDate);
        dimTxt.setText(loadTable.getDimensions());
        sendTxt.setText(loadTable.getSender());
        sendAddrTxt.setText(loadTable.getSenderaddress());
        recTxt.setText(loadTable.getRecipient());
        recAddr.setText(loadTable.getRecipientaddress());
        locTxt.setText(loadTable.getLocation());
        handleTxt.setText(loadTable.getHandler());
        logTxt.setText(loadTable.getShipmentlogged());
    }

    //Filter
    @FXML
    private void FilterLoad(KeyEvent event) {
        searchFilter1.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            //  searchFilter.setText(newValue.toString());
            filteredloadings.setPredicate((Predicate<? super LoadTableModel>) new Predicate<LoadTableModel>() {
                @Override
                public boolean test(LoadTableModel tableModel) {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    } else if (tableModel.getShipmentnumber().toLowerCase().contains(newValue.toLowerCase())) {
                        return true;
                    } else if (tableModel.getSender().toLowerCase().contains(newValue.toLowerCase())) {
                        return true;
                    } else if (tableModel.getRecipient().toLowerCase().contains(newValue.toLowerCase())) {
                        return true;
                    } else if (tableModel.getLoadingnumber().toLowerCase().contains(newValue.toLowerCase())) {
                        return true;
                    }

                    return false;
                }
            });
            SortedList sort = new SortedList(filteredloadings);
            sort.comparatorProperty().bind(table_load.comparatorProperty());
            table_load.setItems(sort);
        });

    }

    @FXML
    private void Filter(KeyEvent event) {
        searchFilter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            //  searchFilter.setText(newValue.toString());
            filtered.setPredicate((Predicate<? super TableModel2>) new Predicate<TableModel2>() {
                @Override
                public boolean test(TableModel2 tableModel) {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    } else if (tableModel.getShipmentnumber().toLowerCase().contains(newValue.toLowerCase())) {
                        return true;
                    } else if (tableModel.getSender().toLowerCase().contains(newValue.toLowerCase())) {
                        return true;
                    } else if (tableModel.getRecipient().toLowerCase().contains(newValue.toLowerCase())) {
                        return true;
                    }
                    return false;
                }
            });
            SortedList sort = new SortedList(filtered);
            sort.comparatorProperty().bind(table_main.comparatorProperty());
            table_main.setItems(sort);
        });
    }

    //Send to Loading List: Assign loading number, send to Loading List and delete from Shipment list
    @FXML
    private void LoadingList(ActionEvent event) {
        LocalDate date = LocalDate.parse(shipDate.getValue().toString());
        String datevalue = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        try {
            String sql = "INSERT INTO LOADINGLIST"
                    + "(shipmentnumber, shipmentdate, dimensions, sender, senderaddress, recipient, recipientaddress, location, handler, shipmentlogged, loadingnumber)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            conn = ConnectDatabase.conDB();
            pst = conn.prepareStatement(sql);
            pst.setString(1, shipNrTxt.getText());
            pst.setString(2, datevalue.toString());
            pst.setString(3, dimTxt.getText());
            pst.setString(4, sendTxt.getText());
            pst.setString(5, sendAddrTxt.getText());
            pst.setString(6, recTxt.getText());
            pst.setString(7, recAddr.getText());
            pst.setString(8, locTxt.getText());
            pst.setString(9, handleTxt.getText());
            pst.setString(10, logTxt.getText());
            pst.setString(11, loadNumber.getText());

            pst.executeUpdate();
            Delete();
            ClearInputFields();
            ReloadLoadTable();
            
        } catch (SQLException ex) {
            Logger.getLogger(MainWindowFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Add new shipment into Database
    @FXML
    private void AddNewShipment(ActionEvent event) {

        LocalDate date = LocalDate.parse(shipDate.getValue().toString());
        String datevalue = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        //shipDate.setValue(date);

        try {
            String sql = "INSERT INTO SHIPMENTS"
                    + "(shipmentnumber, shipmentdate, dimensions, sender, senderaddress, recipient, recipientaddress, location, handler, shipmentlogged)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)";
            conn = ConnectDatabase.conDB();
            pst = conn.prepareStatement(sql);
            pst.setString(1, shipNrTxt.getText());
            pst.setString(2, datevalue.toString());
            pst.setString(3, dimTxt.getText());
            pst.setString(4, sendTxt.getText());
            pst.setString(5, sendAddrTxt.getText());
            pst.setString(6, recTxt.getText());
            pst.setString(7, recAddr.getText());
            pst.setString(8, locTxt.getText());
            pst.setString(9, handleTxt.getText());
            pst.setString(10, logTxt.getText());

            pst.executeUpdate();
            ClearInputFields();
            ReloadTable();
        } catch (SQLException ex) {
            Logger.getLogger(MainWindowFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //edit shipment details
    @FXML
    private void EditShipment(ActionEvent event) {

        LocalDate date = LocalDate.parse(shipDate.getValue().toString());
        String datevalue = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        try {
            String sql = "UPDATE  SHIPMENTS SET shipmentnumber=?, shipmentdate=?, dimensions=?, sender=?, senderaddress=?, recipient=?, recipientaddress=?, location=?, handler=?, shipmentlogged=? WHERE ID='" + idTxt.getText() + "'";
            conn = ConnectDatabase.conDB();
            pst = conn.prepareStatement(sql);

            pst.setString(1, shipNrTxt.getText());
            pst.setString(2, datevalue.toString());
            pst.setString(3, dimTxt.getText());
            pst.setString(4, sendTxt.getText());
            pst.setString(5, sendAddrTxt.getText());
            pst.setString(6, recTxt.getText());
            pst.setString(7, recAddr.getText());
            pst.setString(8, locTxt.getText());
            pst.setString(9, handleTxt.getText());
            pst.setString(10, logTxt.getText());
            pst.executeUpdate();

            ClearInputFields();
            ReloadTable();

        } catch (Exception e) {
            Logger.getLogger(MainWindowFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //delete shipment
    @FXML
    private void DeleteShipment() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm");
        confirm.setHeaderText("The selected shipment will be deleted");
        confirm.setContentText("Are you sure you want to delete the selected shipment");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.get() == ButtonType.OK) {

            try {
                TableModel2 model = (TableModel2) table_main.getSelectionModel().getSelectedItem();
                String pickid = idTxt.getText();
                String sql = "DELETE FROM SHIPMENTS WHERE ID=?";
                conn = ConnectDatabase.conDB();
                pst = conn.prepareStatement(sql);
                pst.setString(1, pickid);
                pst.executeUpdate();
//            pst.close();
//            rs.close();
                ReloadTable();
                ClearInputFields();

            } catch (Exception e) {
                Logger.getLogger(MainWindowFXMLController.class.getName()).log(Level.SEVERE, null, e);

            }
        } else {

        }
    }

    private void Delete() {
        try {
            TableModel2 model = (TableModel2) table_main.getSelectionModel().getSelectedItem();
            String pickid = idTxt.getText();
            String sql = "DELETE FROM SHIPMENTS WHERE ID=?";
            conn = ConnectDatabase.conDB();
            pst = conn.prepareStatement(sql);
            pst.setString(1, pickid);
            pst.executeUpdate();
//            pst.close();
//            rs.close();
            ReloadTable();
            ClearInputFields();

        } catch (Exception e) {
            Logger.getLogger(MainWindowFXMLController.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    private void ReloadLoadTable() {
        loadings.clear();
        LoadLoadingTable();

    }

    private void ReloadTable() {
        shipments.clear();
        LoadTableMethod();
    }

    private void ClearInputFields() {
        idTxt.setText("");
        shipNrTxt.setText("");
        shipDate.setValue(LocalDate.now());
        dimTxt.setText("");
        sendTxt.setText("");
        sendAddrTxt.setText("");
        recTxt.setText("");
        recAddr.setText("");
        locTxt.setText("");
        handleTxt.setText("");
        logTxt.setText("");
    }

    private void LoadTableMethod() {
        String sql = "SELECT * FROM SHIPMENTS";

        try {

            Connection conn = ConnectDatabase.conDB();
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                shipments.add(new TableModel2(
                        rs.getString("ID"),
                        rs.getString("SHIPMENTNUMBER"),
                        rs.getString("SHIPMENTDATE"),
                        rs.getString("DIMENSIONS"),
                        rs.getString("SENDER"),
                        rs.getString("SENDERADDRESS"),
                        rs.getString("RECIPIENT"),
                        rs.getString("RECIPIENTADDRESS"),
                        rs.getString("LOCATION"),
                        rs.getString("HANDLER"),
                        rs.getString("SHIPMENTLOGGED")
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainWindowFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_shipnr.setCellValueFactory(new PropertyValueFactory<>("Shipmentnumber"));
        col_shipdate.setCellValueFactory(new PropertyValueFactory<>("Shipmentdate"));
        col_dim.setCellValueFactory(new PropertyValueFactory<>("Dimensions"));
        col_sender.setCellValueFactory(new PropertyValueFactory<>("Sender"));
        col_sendaddr.setCellValueFactory(new PropertyValueFactory<>("Senderaddress"));
        col_recipient.setCellValueFactory(new PropertyValueFactory<>("Recipient"));
        col_recaddr.setCellValueFactory(new PropertyValueFactory<>("Recipientaddress"));
        col_location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        col_handler.setCellValueFactory(new PropertyValueFactory<>("Handler"));
        col_logged.setCellValueFactory(new PropertyValueFactory<>("Shipmentlogged"));

        table_main.setItems(shipments);
        table_main.refresh();

    }

    private void LoadLoadingTable() {
        String loadsql = "SELECT * FROM LOADINGLIST";

        try {

            Connection conn = ConnectDatabase.conDB();
            ResultSet rs = conn.createStatement().executeQuery(loadsql);

            while (rs.next()) {
                loadings.add(new LoadTableModel(
                        rs.getString("ID"),
                        rs.getString("SHIPMENTNUMBER"),
                        rs.getString("SHIPMENTDATE"),
                        rs.getString("DIMENSIONS"),
                        rs.getString("SENDER"),
                        rs.getString("SENDERADDRESS"),
                        rs.getString("RECIPIENT"),
                        rs.getString("RECIPIENTADDRESS"),
                        rs.getString("LOCATION"),
                        rs.getString("HANDLER"),
                        rs.getString("SHIPMENTLOGGED"),
                        rs.getString("LOADINGNUMBER")
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainWindowFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_ID1.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_shipnr1.setCellValueFactory(new PropertyValueFactory<>("Shipmentnumber"));
        col_shipdate1.setCellValueFactory(new PropertyValueFactory<>("Shipmentdate"));
        col_dim1.setCellValueFactory(new PropertyValueFactory<>("Dimensions"));
        col_sender1.setCellValueFactory(new PropertyValueFactory<>("Sender"));
        col_sendaddr1.setCellValueFactory(new PropertyValueFactory<>("Senderaddress"));
        col_recipient1.setCellValueFactory(new PropertyValueFactory<>("Recipient"));
        col_recaddr1.setCellValueFactory(new PropertyValueFactory<>("Recipientaddress"));
        col_location1.setCellValueFactory(new PropertyValueFactory<>("Location"));
        col_handler1.setCellValueFactory(new PropertyValueFactory<>("Handler"));
        col_logged1.setCellValueFactory(new PropertyValueFactory<>("Shipmentlogged"));
        col_load.setCellValueFactory(new PropertyValueFactory<>("Loadingnumber"));

        table_load.setItems(loadings);
        table_load.refresh();

    }

}
