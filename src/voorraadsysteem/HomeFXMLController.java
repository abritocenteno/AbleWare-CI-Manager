/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voorraadsysteem;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;
import com.sun.javafx.scene.control.skin.TextFieldSkin;
import static java.awt.SystemColor.info;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import static javafx.scene.paint.Color.color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import static javax.swing.UIManager.get;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author abel
 */
public class HomeFXMLController implements Initializable {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null;

    @FXML
    private TableView<RecentTableModel> recentTable;

    @FXML
    private TableColumn<RecentTableModel, String> col_idR;

    @FXML
    private TableColumn<RecentTableModel, String> col_artikelcodeR;

    @FXML
    private TableColumn<RecentTableModel, String> col_locatieR;

    @FXML
    private TableColumn<RecentTableModel, String> col_omschrijvingR;

    @FXML
    private TableColumn<RecentTableModel, String> col_aantalR;

    @FXML
    private TableColumn<RecentTableModel, String> col_eenheidR;

    @FXML
    private TableColumn<RecentTableModel, String> col_initiaalR;

    @FXML
    private JFXTextField zoekRecentsTxt;

    @FXML
    private JFXButton resetRecent;

    @FXML
    private JFXButton cleaRecentsBtn;

    @FXML
    private Tab homeTab;
    
    @FXML
    private Tab pickZtab;
    
    @FXML
    private Tab bulkZtab;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private Tab zoekTab;

    @FXML
    private Tab pickTab;
    
    @FXML
    private Tab boekTab;
    
    @FXML
    private Tab pickBtab;
    
    @FXML
    private Tab bulkBtab;
    
    @FXML
    private Tab bulkRtab;
    
    @FXML
    private Tab historyTab;
    
    @FXML
    private Tab pickHtab;
    
    @FXML
    private Tab bulkHtab;

    @FXML
    private JFXTextField searchfield;

    @FXML
    private JFXTextField pickAantalTxt;

    @FXML
    private JFXTextField pickInitiaalTxt;

    //Pick zoek tab
    @FXML
    private TableView<TableModelPickSearch> searchTable;

    @FXML
    private TableColumn<TableModelPickSearch, String> col_id;

    @FXML
    private TableColumn<TableModelPickSearch, String> col_locatie;

    @FXML
    private TableColumn<TableModelPickSearch, String> col_artikelcode;

    @FXML
    private TableColumn<TableModelPickSearch, String> col_omschrijving;

    @FXML
    private TableColumn<TableModelPickSearch, String> col_eenheid;

    @FXML
    private TableColumn<TableModelPickSearch, String> col_hal;

    @FXML
    private TableColumn<TableModelPickSearch, String> col_oescode1;

    @FXML
    private TableColumn<TableModelPickSearch, String> col_aantalP1;

    @FXML
    private TableColumn<TableModelPickSearch, String> col_initiaalP1;

    @FXML
    private TableColumn<TableModelPickSearch, String> col_max1;

    //Pick boek tab
    @FXML
    TableView<TableModel2> searchTable1;
    @FXML
    private TableColumn<TableModel2, String> col_pickId;

    @FXML
    private TableColumn<TableModel2, String> col_pickLoc;

    @FXML
    private TableColumn<TableModel2, String> col_pickArt;

    @FXML
    private TableColumn<TableModel2, String> col_pickDesc;

    @FXML
    private TableColumn<TableModel2, String> col_pickEenh;

    @FXML
    private TableColumn<TableModel2, String> col_pickCode;

    @FXML
    private TableColumn<TableModel2, String> col_pickHal;

    @FXML
    private TableColumn<TableModel2, String> col_pickAant;

    @FXML
    private TableColumn<TableModel2, String> col_pickInit;

    @FXML
    private TableColumn<TableModel2, String> col_pickMax;

    @FXML
    private JFXTextField pickEenheidTxt;

    @FXML
    private JFXComboBox<String> pickArticleCombo;

    @FXML
    private JFXTextArea pickDescTxt;

    @FXML
    private JFXTextField pickIdTxt;

    @FXML
    private JFXComboBox<String> pickLocationCombo;

    @FXML
    private JFXComboBox<String> pickHalCombo;

    @FXML
    private JFXTextField oesTxt;

    @FXML
    private JFXButton pickAddBtn;

    @FXML
    private JFXButton pickDelBtn;

    @FXML
    private JFXButton pickModifyBtn;

    @FXML
    private JFXButton pickResetBtn;

    @FXML
    private JFXTextField maxAantalTxt;

    //BULK zoek tab
    @FXML
    private JFXButton resetbutton;

    @FXML
    private JFXButton resetbutton1;

    @FXML
    private JFXTextField searchfield1;

    @FXML
    private JFXTextField bulkAantalTxt;

    @FXML
    private JFXTextField bulkInitiaalTxt;

    @FXML
    JFXTextField bulkIDtxt;
//
    @FXML
    private TableView<BulkTableModelSearch> bulkTable2;
//
    @FXML
    private TableColumn<BulkTableModelSearch, String> col_idB;

    @FXML
    private TableColumn<BulkTableModelSearch, String> col_locatie1;

    @FXML
    private TableColumn<BulkTableModelSearch, String> col_artikelcode1;

    @FXML
    private TableColumn<BulkTableModelSearch, String> col_omschrijving1;

    @FXML
    private TableColumn<BulkTableModelSearch, String> col_eenheid1;

    @FXML
    private TableColumn<BulkTableModelSearch, String> col_aantal1;

    @FXML
    private TableColumn<BulkTableModelSearch, String> col_initiaal1;

    @FXML
    private TableColumn<BulkTableModelSearch, String> col_datum1;
    
     @FXML
    private TableColumn<BulkTableModelSearch, String> col_lenummer;

    //Bulk boek tab
    @FXML
    private TableView<BulkTableModel> bulkTable21;
//
    @FXML
    private TableColumn<BulkTableModel, String> col_idB2;

    @FXML
    private TableColumn<BulkTableModel, String> col_locatie11;

    @FXML
    private TableColumn<BulkTableModel, String> col_artikelcode11;

    @FXML
    private TableColumn<BulkTableModel, String> col_omschrijving11;

    @FXML
    private TableColumn<BulkTableModel, String> col_eenheid11;

    @FXML
    private TableColumn<BulkTableModel, String> col_aantal11;

    @FXML
    private TableColumn<BulkTableModel, String> col_initiaal11;

    @FXML
    private TableColumn<BulkTableModel, String> col_datum11;
    
    @FXML
    private TableColumn<BulkTableModel, String> col_lebulk;
    
    @FXML
    private JFXTextField leTextField;

    @FXML
    private JFXTextField autocompleteTf;

    @FXML
    private JFXTextField autocompleteLoc;

    @FXML
    private JFXTextField autocompleteHal;

    @FXML
    private JFXTextField autocompleteBulkArt;

    @FXML
    private JFXTextField autocompleteBulkLoc;

    //Pick History
    @FXML
    private TableView<PickHistoryTableModel> pickHistoryTbl;

    @FXML
    private TableColumn<PickHistoryTableModel, String> col_pickIdH;

    @FXML
    private TableColumn<PickHistoryTableModel, String> col_pickLocH;

    @FXML
    private TableColumn<PickHistoryTableModel, String> col_pickArtH;

    @FXML
    private TableColumn<PickHistoryTableModel, String> col_pickDescH;

    @FXML
    private TableColumn<PickHistoryTableModel, String> col_pickEenhH;

    @FXML
    private TableColumn<PickHistoryTableModel, String> col_pickCodeH;

    @FXML
    private TableColumn<PickHistoryTableModel, String> col_pickHalH;

    @FXML
    private TableColumn<PickHistoryTableModel, String> col_pickAantH;

    @FXML
    private TableColumn<PickHistoryTableModel, String> col_pickInitH;

    @FXML
    private TableColumn<PickHistoryTableModel, String> col_pickMaxH;

    @FXML
    private TableColumn<PickHistoryTableModel, String> col_DatumH;

    @FXML
    private JFXTextField pickHistoryEenh;

    @FXML
    private JFXTextArea pickHistoryDesc;

    @FXML
    private JFXTextField pickHistoryOes;

    @FXML
    private JFXButton pickHistoryRestore;

    @FXML
    private JFXButton pickHistoryClear;

    @FXML
    private JFXButton pickHistoryReset;

    @FXML
    private JFXButton pickHistoryResetFilter;

    @FXML
    private JFXTextField pickHistoryFilter;

    @FXML
    private JFXTextField pickHistoryAant;

    @FXML
    private JFXTextField pickHistoryInit;

    @FXML
    private JFXTextField pickHistoryId;

    @FXML
    private JFXTextField pickHistoryArt;

    @FXML
    private JFXTextField pickHistoryLoc;

    @FXML
    private JFXTextField pickHistoryHal;

    @FXML
    private JFXTextField pickHistoryMax;

    //Bulk History
//    @FXML
//    private TableView<BulkHistoryTableModel> bulkHistoryTbl;
//
//    @FXML
//    private TableColumn<BulkHistoryTableModel, String> col_BulkIdH;
//
//    @FXML
//    private TableColumn<BulkHistoryTableModel, String> col_BulkArtH;
//
//    @FXML
//    private TableColumn<BulkHistoryTableModel, String> col_BulkLocH;
//
//    @FXML
//    private TableColumn<BulkHistoryTableModel, String> col_BulkDescH;
//
//    @FXML
//    private TableColumn<BulkHistoryTableModel, String> col_BulkAantH;
//
//    @FXML
//    private TableColumn<BulkHistoryTableModel, String> col_BulkEenhH;
//
//    @FXML
//    private TableColumn<BulkHistoryTableModel, String> col_BulkInitH;
//
//    @FXML
//    private TableColumn<BulkHistoryTableModel, String> col_BulkDatumH;

    @FXML
    private JFXTextField bulkEenheidH;

    @FXML
    private JFXTextArea bulkDescH;

    @FXML
    private JFXButton bulkRestoreH;

    @FXML
    private JFXButton bulkDelH;

    @FXML
    private JFXTextField bulkAantalH;

    @FXML
    private JFXTextField bulkInitiaalH;

    @FXML
    private JFXTextField bulkIDH;

    @FXML
    private JFXTextField bulkArtH;

    @FXML
    private JFXTextField bulkLocH;
    
    @FXML
    private JFXTextField pickFilterBdesc;
    
    @FXML
    private JFXTextField bulkFilterBdesc;
    
    @FXML 
    private JFXTextField pickZfilterDesc;
    
    @FXML
    private JFXTextField bulkZfilterDesc;
    
    
   @FXML
    private JFXTextField bulkSearchFilter1;

    ObservableList<TableModel2> picklocaties = FXCollections.observableArrayList();
    FilteredList filtered = new FilteredList(picklocaties, e -> true);

    ObservableList<TableModelPickSearch> pickles = FXCollections.observableArrayList();
    FilteredList filteredpick = new FilteredList(pickles, e -> true);

    ObservableList<BulkTableModel> bulklocaties = FXCollections.observableArrayList();
    FilteredList filteredB = new FilteredList(bulklocaties, e -> true);

    ObservableList<RecentTableModel> recentlocaties = FXCollections.observableArrayList();
    FilteredList filteredR = new FilteredList(recentlocaties, e -> true);

    ObservableList<BulkTableModelSearch> buckles = FXCollections.observableArrayList();
    FilteredList buckledList = new FilteredList(buckles, e -> true);

    ObservableList<PickHistoryTableModel> pickhistory = FXCollections.observableArrayList();
    FilteredList pickhistoryList = new FilteredList(pickhistory, e -> true);

//    ObservableList<BulkHistoryTableModel> bulkhistoryFilter = FXCollections.observableArrayList();
//    FilteredList bulkhistoryFilteredList = new FilteredList(bulkhistoryFilter, e -> true);

//    
//    ObservableList<TableModel> articlecodes = FXCollections.observableArrayList();
//    FilteredList artiekelnummer = new FilteredList(articlecodes, e -> true);
//    
//    
    @FXML
    private Tab bulkTab;

    @FXML
    private JFXButton resetbutton2;

    @FXML
    private JFXTextField searchfield2;

    //BUlk TAB Boek
    @FXML
    private JFXTextField bulkEenheidTxt;

    @FXML
    private JFXComboBox<String> bulkArticleCombo;

    @FXML
    private JFXTextArea bulkDescTxt;

    @FXML
    private JFXComboBox<String> bulkLocationCombo;

    @FXML
    private JFXButton bulkAddBtn;

    @FXML
    private JFXButton bulkModBtn;

    @FXML
    private JFXButton bulkFilterResetBtn;

    @FXML
    private JFXTextField bulkSearchFilter;

    @FXML
    private JFXButton bulkDelBtn;

    @FXML
    private JFXButton bulkResetBtn;

    @FXML
    private JFXButton bulkModEmpty;
    
    @FXML
    private JFXTextArea clickDescP;
    
    @FXML
    private JFXTextArea clickDescB;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    final ObservableList articlecodes = FXCollections.observableArrayList();
    final ObservableList picklocations = FXCollections.observableArrayList();
    final ObservableList hal = FXCollections.observableArrayList();
    final ObservableList bulklocations = FXCollections.observableArrayList();
    final ObservableList recentlocations = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        LoadPickTableMethod();
        LoadRecentsMethod();
        LoadPickTableSearch();
        LoadBulkTableMethod();
        LoadBulkSearchMethod();
        LoadPickHistory();
//        LoadBulkHistory();
        //Load Pick/bulk combo list     
        Connection conn = ConnectDatabase.conDB();
        try {
            String query = "SELECT * FROM STAMDATA";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                articlecodes.add(rs.getString("ARTIKELNUMMER"));

            }
            autocompleteTf.setTextFormatter(new TextFormatter<>((change) -> {
                change.setText(change.getText().toUpperCase());
                return change;
            }));
            TextFields.bindAutoCompletion(autocompleteTf, articlecodes);

            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Load Bulk Combo List
        Connection con = ConnectDatabase.conDB();
        try {
            String query = "SELECT * FROM STAMDATA";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                articlecodes.add(rs.getString("ARTIKELNUMMER"));
                //autocompleteBulkArt.setText(articlecodes.toString());
            }
            autocompleteBulkArt.setTextFormatter(new TextFormatter<>((change) -> {
                change.setText(change.getText().toUpperCase());
                return change;
            }));
            TextFields.bindAutoCompletion(autocompleteBulkArt, articlecodes);
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Load Bulk Location List 
        try {
            String queryLoc = "SELECT LOCATIE FROM VOORRAADSYSTEEM";
            pst = conn.prepareStatement(queryLoc);
            rs = pst.executeQuery();
            while (rs.next()) {
                bulklocations.add(rs.getString("LOCATIE"));
                //autocompleteBulkLoc.setText(bulklocations.toString());
            }

            autocompleteBulkLoc.setTextFormatter(new TextFormatter<>((change) -> {
                change.setText(change.getText().toUpperCase());
                return change;
            }));
            TextFields.bindAutoCompletion(autocompleteBulkLoc, bulklocations);
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Load Pick Locatie List
        try {
            String queryLoc = "SELECT * FROM PICKLOCATIES";
            pst = conn.prepareStatement(queryLoc);
            rs = pst.executeQuery();
            while (rs.next()) {
                picklocations.add(rs.getString("LOCATIE"));
                //autocompleteTf.setText(picklocations.toString());
            }

            autocompleteLoc.setTextFormatter(new TextFormatter<>((change) -> {
                change.setText(change.getText().toUpperCase());
                return change;
            }));
            TextFields.bindAutoCompletion(autocompleteLoc, picklocations);
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Load Hal list
        try {
            String queryHal = "SELECT * FROM OESCODE";
            pst = conn.prepareStatement(queryHal);
            rs = pst.executeQuery();
            while (rs.next()) {
                hal.add(rs.getString("AREA"));
                //autocompleteHal.setText(hal.toString());
            }

            autocompleteHal.setTextFormatter(new TextFormatter<>((change) -> {
                change.setText(change.getText().toUpperCase());
                return change;
            }));
            TextFields.bindAutoCompletion(autocompleteHal, hal);

            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void pickArticleComboContextMenu(ActionEvent event) {
        Connection conn = ConnectDatabase.conDB();
        String fill = (String) autocompleteTf.getText();
        String query = "SELECT * FROM STAMDATA WHERE ARTIKELNUMMER=?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, fill);
            rs = pst.executeQuery();
            if (rs.next()) {
                String desc = rs.getString("OMSCHRIJVING");
                String eenh = rs.getString("EENHEID");
                pickEenheidTxt.setText(eenh);
                pickDescTxt.setText(desc);
            }
        } catch (Exception e) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void bulkArticleComboContextMenu(ActionEvent event) {
        Connection conn = ConnectDatabase.conDB();
        String fill = (String) autocompleteBulkArt.getText();
        String query = "SELECT * FROM STAMDATA WHERE ARTIKELNUMMER=?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, fill);
            rs = pst.executeQuery();
            if (rs.next()) {
                String desc = rs.getString("OMSCHRIJVING");
                String eenh = rs.getString("EENHEID");
                bulkEenheidTxt.setText(eenh);
                bulkDescTxt.setText(desc);
            }
        } catch (Exception e) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void halContextMenu(ActionEvent event) {
        Connection conn = ConnectDatabase.conDB();
        String fill = (String) autocompleteHal.getText();
        String query = "SELECT * FROM OESCODE WHERE AREA=?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, fill);
            rs = pst.executeQuery();
            if (rs.next()) {
                String oescode = rs.getString("CODE");

                oesTxt.setText(oescode);

            }
        } catch (Exception e) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void PickLocationContextMenu(ActionEvent event) {
        Connection conn = ConnectDatabase.conDB();
        String fill = (String) autocompleteLoc.getText();
        String query = "SELECT * FROM PICKLOCATIES WHERE LOCATIE=?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, fill);
            rs = pst.executeQuery();
            if (rs.next()) {
                String hal = rs.getString("HAL");
                String code = rs.getString("CODE");

                autocompleteHal.setText(hal);
                oesTxt.setText(code);

            }
        } catch (Exception e) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void Filter(KeyEvent event) {
        searchfield.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            searchfield.setText(newValue.toUpperCase());
            filteredpick.setPredicate((Predicate<? super TableModelPickSearch>) new Predicate<TableModelPickSearch>() {
                @Override
                public boolean test(TableModelPickSearch tableModel) {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    } else if (tableModel.getArtikelcode().contains(newValue)) {
                        return true;
                    } else if (tableModel.getLocatie().contains(newValue)) {
                        return true;
                    } else if (tableModel.getOmschrijving().contains(newValue)) {
                        return true;
                    }
                    return false;
                }
            });
            SortedList sort = new SortedList(filteredpick);
            sort.comparatorProperty().bind(searchTable.comparatorProperty());
            searchTable.setItems(sort);
        });
    }
    
    @FXML
    private void FilterDesc(KeyEvent event) {
        pickZfilterDesc.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            
            filteredpick.setPredicate((Predicate<? super TableModelPickSearch>) new Predicate<TableModelPickSearch>() {
                @Override
                public boolean test(TableModelPickSearch tableModel) {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    } else if (tableModel.getOmschrijving().toLowerCase().contains(newValue.toLowerCase())) {
                        return true;
                    } 
                    return false;
                }
            });
            SortedList sort = new SortedList(filteredpick);
            sort.comparatorProperty().bind(searchTable.comparatorProperty());
            searchTable.setItems(sort);
        });
    }

    @FXML
    private void FilterB(KeyEvent event) {
        searchfield1.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            searchfield1.setText(newValue.toUpperCase());
            buckledList.setPredicate((Predicate<? super BulkTableModelSearch>) new Predicate<BulkTableModelSearch>() {
                @Override
                public boolean test(BulkTableModelSearch bulkTableModel) {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    } else if (bulkTableModel.getArtikelcode().contains(newValue)) {
                        return true;
                    } else if (bulkTableModel.getLocatie().contains(newValue)) {
                        return true;
                    } else if (bulkTableModel.getOmschrijving().contains(newValue)) {
                        return true;
                    } else if (bulkTableModel.getLe().contains(newValue)) {
                        return true;
                    }
                    return false;
                }
            });
        });
        SortedList sort = new SortedList(buckledList);
        sort.comparatorProperty().bind(bulkTable2.comparatorProperty());
        bulkTable2.setItems(sort);
    }
    
     @FXML
    private void FilterBDesc(KeyEvent event) {
        bulkZfilterDesc.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            
            buckledList.setPredicate((Predicate<? super BulkTableModelSearch>) new Predicate<BulkTableModelSearch>() {
                @Override
                public boolean test(BulkTableModelSearch bulkTableModel) {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    } else if (bulkTableModel.getOmschrijving().toLowerCase().contains(newValue.toLowerCase())) {
                        return true;
                    } 
                    return false;
                }
            });
        });
        SortedList sort = new SortedList(buckledList);
        sort.comparatorProperty().bind(bulkTable2.comparatorProperty());
        bulkTable2.setItems(sort);
    }

    @FXML
    private void FilterBulk(KeyEvent event) {
        bulkSearchFilter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            bulkSearchFilter.setText(newValue.toUpperCase());
            filteredB.setPredicate((Predicate<? super BulkTableModel>) new Predicate<BulkTableModel>() {
                @Override
                public boolean test(BulkTableModel bulkTableModel) {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    } else if (bulkTableModel.getArtikelcode().contains(newValue)) {
                        return true;
                    } else if (bulkTableModel.getLocatie().contains(newValue)) {
                        return true;
                    } else if (bulkTableModel.getOmschrijving().contains(newValue)) {
                        return true;
                    } else if (bulkTableModel.getLe().contains(newValue)) {
                        return true;
                    }
                    return false;
                }
            });
        });
        SortedList sort = new SortedList(filteredB);
        sort.comparatorProperty().bind(bulkTable21.comparatorProperty());
        bulkTable21.setItems(sort);
    }
    
    
     @FXML
    private void FilterBulkDesc(KeyEvent event) {
        bulkFilterBdesc.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            
            filteredB.setPredicate((Predicate<? super BulkTableModel>) new Predicate<BulkTableModel>() {
                @Override
                public boolean test(BulkTableModel bulkTableModel) {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    } else if (bulkTableModel.getOmschrijving().toLowerCase().contains(newValue.toLowerCase())) {
                        return true;
                    } 
                    return false;
                }
            });
        });
        SortedList sort = new SortedList(filteredB);
        sort.comparatorProperty().bind(bulkTable21.comparatorProperty());
        bulkTable21.setItems(sort);
    }

//    @FXML
//    private void FilterPick(KeyEvent event) {
//        searchfield2.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
//            searchfield2.setText(newValue.toUpperCase());
//            filtered.setPredicate((Predicate<? super TableModel2>) new Predicate<TableModel2>() {
//                @Override
//                public boolean test(TableModel2 tableModel) {
//                    if (newValue.isEmpty() || newValue == null) {
//                        return true;
//                    } else if (tableModel.getArtikelcode().contains(newValue)) {
//                        return true;
//                    } else if (tableModel.getLocatie().contains(newValue)) {
//                        return true;
//                    } else if (tableModel.getOmschrijving().contains(newValue)) {
//                        return true;
//                    }
//                    return false;
//                }
//            });
//        });
//        SortedList sort = new SortedList(filtered);
//        sort.comparatorProperty().bind(searchTable1.comparatorProperty());
//        searchTable1.setItems(sort);
//    }
    
    
//    @FXML
//    private void FilterPickDesc(KeyEvent event) {
//        pickFilterBdesc .textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
////            pickFilterBdesc.setText(newValue.toLowerCase());
//            filtered.setPredicate((Predicate<? super TableModel2>) new Predicate<TableModel2>() {
//                @Override
//                public boolean test(TableModel2 tableModel) {
//                    if (newValue.isEmpty() || newValue == null) {
//                        return true;
//                    } else if (tableModel.getOmschrijving().toLowerCase().contains(newValue.toLowerCase())) {
//                        return true;
//                    
//                    }
//                    return false;
//                }
//            });
//        });
//        SortedList sort = new SortedList(filtered);
//        sort.comparatorProperty().bind(searchTable1.comparatorProperty());
//        searchTable1.setItems(sort);
//    }

    @FXML
    private void FilterPickHistory(KeyEvent event) {
        pickHistoryFilter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            pickHistoryFilter.setText(newValue.toUpperCase());
            pickhistoryList.setPredicate((Predicate<? super PickHistoryTableModel>) new Predicate<PickHistoryTableModel>() {
                @Override
                public boolean test(PickHistoryTableModel historytableModel) {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    } else if (historytableModel.getArtikelcode().contains(newValue)) {
                        return true;
                    } else if (historytableModel.getLocatie().contains(newValue)) {
                        return true;
                    } else if (historytableModel.getOmschrijving().contains(newValue)) {
                        return true;
                    }
                    return false;
                }
            });
        });
        SortedList sortH = new SortedList(pickhistoryList);
        sortH.comparatorProperty().bind(pickHistoryTbl.comparatorProperty());
        pickHistoryTbl.setItems(sortH);
    }
    
    
//     @FXML
//    private void FilterBulkHistory(KeyEvent event) {
//         bulkSearchFilter1.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
//            bulkSearchFilter1.setText(newValue.toUpperCase());
//            bulkhistoryFilteredList.setPredicate((Predicate<? super BulkHistoryTableModel>) new Predicate<BulkHistoryTableModel>() {
//                @Override
//                public boolean test(BulkHistoryTableModel bhistorytableModel) {
//                    if (newValue.isEmpty() || newValue == null) {
//                        return true;
//                    } else if (bhistorytableModel.getArtikelcode().contains(newValue)) {
//                        return true;
//                    } else if (bhistorytableModel.getLocatie().contains(newValue)) {
//                        return true;
//                    } else if (bhistorytableModel.getOmschrijving().contains(newValue)) {
//                        return true;
//                    }
//                    return false;
//                }
//            });
//        });
//        SortedList sortHB = new SortedList(bulkhistoryFilteredList);
//        sortHB.comparatorProperty().bind(bulkHistoryTbl.comparatorProperty());
//        bulkHistoryTbl.setItems(sortHB);
//    }

    @FXML
    private void FilterRecent(KeyEvent event) {
        zoekRecentsTxt.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            zoekRecentsTxt.setText(newValue.toUpperCase());
            filteredR.setPredicate((Predicate<? super RecentTableModel>) new Predicate<RecentTableModel>() {
                @Override
                public boolean test(RecentTableModel recenttableModel) {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    } else if (recenttableModel.getArtikelcode().contains(newValue)) {
                        return true;
                    } else if (recenttableModel.getLocatie().contains(newValue)) {
                        return true;
                    } else if (recenttableModel.getOmschrijving().contains(newValue)) {
                        return true;
                    }
                    return false;
                }
            });
        });
        SortedList sort = new SortedList(filteredR);
        sort.comparatorProperty().bind(recentTable.comparatorProperty());
        recentTable.setItems(sort);
    }

    @FXML
    private void ResetField(ActionEvent event) {
        searchfield.setText("");
        pickZfilterDesc.setText("");
        clickDescP.setText("");
    }

    @FXML
    private void ResetFieldB(ActionEvent event) {
        searchfield1.setText("");
        
        bulkZfilterDesc.setText("");
        
        clickDescB.setText("");
    }

    @FXML
    private void ResetFieldPick(ActionEvent event) {
        searchfield2.setText("");
        pickFilterBdesc.setText("");
    }

    @FXML
    private void ResetFieldBulk(ActionEvent event) {
        bulkSearchFilter.setText("");
        bulkFilterBdesc.setText("");
    }

    @FXML
    private void ResetFieldRecents(ActionEvent event) {
        zoekRecentsTxt.setText("");
    }

    @FXML
    private void ResetHistoryFilter(ActionEvent event) {
        pickHistoryFilter.setText("");
    }
    
    @FXML
    private void ResetBulkHistoryFilter(ActionEvent event) {
        bulkSearchFilter1.setText("");
    }

    @FXML
    private void DeleteRecents(ActionEvent event) {
        recentTable.getItems().clear();
        try {
            // TableModel2 model = (TableModel2) searchTable1.getSelectionModel().getSelectedItem();
            String sql = "DELETE  FROM RECENTBULKADDED";
            conn = ConnectDatabase.conDB();
            pst = conn.prepareStatement(sql);
            
            pst.executeUpdate();
            pst.close();
            rs.close();
            LoadRecentsMethod();
//            

        } catch (Exception e) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void Logout(ActionEvent event) {
        logoutBtn.getScene().getWindow().hide();
        Stage login = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
            login.setTitle("Inloggen");
            login.initStyle(StageStyle.DECORATED);
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/voorraadsysteem/voorraadsysteem.css");
            login.setScene(scene);
            login.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void RestorePickHistory(ActionEvent event) {
        searchTable1.getItems().clear();

        try {

            String sql = "INSERT INTO PICKLOCATIES"
                    + "(artikelcode, locatie, omschrijving, eenheid,code,hal,aantal, initiaal, max)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            conn = ConnectDatabase.conDB();
            pst = conn.prepareStatement(sql);
            pst.setString(1, pickHistoryArt.getText());
            pst.setString(3, pickHistoryDesc.getText());
            pst.setString(2, pickHistoryLoc.getText());
            pst.setString(4, pickHistoryEenh.getText());
            pst.setString(5, pickHistoryOes.getText());
            pst.setString(6, pickHistoryHal.getText());
            pst.setString(7, pickHistoryAant.getText());
            pst.setString(8, pickHistoryInit.getText());
            pst.setString(9, pickHistoryMax.getText());
            pst.executeUpdate();
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setContentText("Artikel Hersteld");
            dialog.setHeaderText("Informatie");
            dialog.showAndWait();

            
            
            LoadPickHistory();
           // LoadPickTableMethod();
            LoadPickTableSearch();
            ClearPickHistoryFields();

        } catch (Exception e) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void AddPickHistory() {
       // pickHistoryTbl.getItems().clear();

        try {

            String date = new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).format(new Date());
            String sql = "INSERT INTO PICKHISTORY"
                    + "(artikelcode, locatie, omschrijving, eenheid,code,hal,aantal, initiaal, max, datum)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)";
            conn = ConnectDatabase.conDB();
            pst = conn.prepareStatement(sql);
            pst.setString(1, autocompleteTf.getText());
            pst.setString(3, pickDescTxt.getText());
            pst.setString(2, autocompleteLoc.getText());
            pst.setString(4, pickEenheidTxt.getText());
            pst.setString(5, oesTxt.getText());
            pst.setString(6, autocompleteHal.getText());
            pst.setString(7, pickAantalTxt.getText());
            pst.setString(8, pickInitiaalTxt.getText());
            pst.setString(9, maxAantalTxt.getText());
            pst.setString(10, date);
            pst.executeUpdate();
//            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
//            dialog.setContentText("Artikel Toegevoegd");
//            dialog.setHeaderText("Informatie");
//            dialog.showAndWait();

            //ClearAllFields();
            LoadPickHistory();

        } catch (Exception e) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    private void AddPick(ActionEvent event) {
//        searchTable1.getItems().clear();

        try {

            String sql = "INSERT INTO PICKLOCATIES"
                    + "(artikelcode, locatie, omschrijving, eenheid,code,hal,aantal, initiaal, max)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            conn = ConnectDatabase.conDB();
            pst = conn.prepareStatement(sql);
            pst.setString(1, autocompleteTf.getText());
            pst.setString(3, pickDescTxt.getText());
            pst.setString(2, autocompleteLoc.getText());
            pst.setString(4, pickEenheidTxt.getText());
            pst.setString(5, oesTxt.getText());
            pst.setString(6, autocompleteHal.getText());
            pst.setString(7, pickAantalTxt.getText());
            pst.setString(8, pickInitiaalTxt.getText());
            pst.setString(9, maxAantalTxt.getText());
            pst.executeUpdate();
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setContentText("Artikel Toegevoegd");
            dialog.setHeaderText("Informatie");
            dialog.showAndWait();

            ClearAllFields();

            ReloadPickTable();
          //  LoadPickTableSearch(); //replace with reloadsearchtable method
          ReloadSearchTable();
          
        } catch (Exception e) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    private void RestoreBulk() {
       
        bulkTable21.getItems().clear();
        
        try {
            String sql = "INSERT INTO VOORRAADSYSTEEM"
                    + "(artikelcode, locatie, omschrijving, aantal,eenheid,initiaal,datum)"
                    + "VALUES (?,?,?,?,?,?,?)";
            conn = ConnectDatabase.conDB();
            pst = conn.prepareStatement(sql);
            String date = new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).format(new Date());
            pst.setString(1, bulkArtH.getText());
            pst.setString(4, bulkAantalH.getText());
            pst.setString(2, bulkLocH.getText());
            pst.setString(3, bulkDescH.getText());
            pst.setString(5, bulkEenheidH.getText());
            pst.setString(6, bulkInitiaalH.getText());
            pst.setString(7, date);
            pst.executeUpdate();
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setContentText("Artikel Hersteld");
            dialog.setHeaderText("Informatie");
            dialog.showAndWait();
         
            
//            LoadBulkHistory();
            LoadBulkTableMethod();
            LoadBulkSearchMethod();
            ClearHistoryBulkFields();
        } catch (Exception e) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void AddBulkHistory() {
//        bulkHistoryTbl.getItems().clear();

        try {
            String sql = "INSERT INTO HISTORY"
                    + "(artikelcode, locatie, omschrijving, aantal,eenheid,initiaal,datum)"
                    + "VALUES (?,?,?,?,?,?,?)";
            conn = ConnectDatabase.conDB();
            pst = conn.prepareStatement(sql);
            String date = new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).format(new Date());
            pst.setString(1, autocompleteBulkArt.getText());
            pst.setString(4, bulkAantalTxt.getText());
            pst.setString(2, autocompleteBulkLoc.getText());
            pst.setString(3, bulkDescTxt.getText());
            pst.setString(5, bulkEenheidTxt.getText());
            pst.setString(6, bulkInitiaalTxt.getText());
            pst.setString(7, date);
            pst.executeUpdate();
//            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
//            dialog.setContentText("Artikel Toegevoegd");
//            dialog.setHeaderText("Informatie");
//            dialog.showAndWait();
//            ReloadBulkTable();

//            LoadBulkHistory();

        } catch (Exception e) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void AddBulk(ActionEvent event) {
//        bulkTable21.getItems().clear();

        try {
            String sql = "INSERT INTO VOORRAADSYSTEEM"
                    + "(artikelcode, locatie, omschrijving, aantal,eenheid,initiaal,datum,le)"
                    + "VALUES (?,?,?,?,?,?,?,?)";
            conn = ConnectDatabase.conDB();
            pst = conn.prepareStatement(sql);
            String date = new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).format(new Date());
            pst.setString(1, autocompleteBulkArt.getText());
            pst.setString(4, bulkAantalTxt.getText());
            pst.setString(2, autocompleteBulkLoc.getText());
            pst.setString(3, bulkDescTxt.getText());
            pst.setString(5, bulkEenheidTxt.getText());
            pst.setString(6, bulkInitiaalTxt.getText());
            pst.setString(7, date);
            pst.setString(8, leTextField.getText());
            pst.executeUpdate();
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setContentText("Artikel Toegevoegd");
            dialog.setHeaderText("Informatie");
            dialog.showAndWait();
            
            ReloadBulkTable();

            AddRecentsMethod();

            //LoadBulkSearchMethod(); //replace with reloadbulktable2 method
            ReloadBulkTable2();
            
        } catch (Exception e) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    private void ModifyPck(ActionEvent event) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Bevestiging");
        confirm.setHeaderText("Locatie/Artikel Wordt Aangepast");
        confirm.setContentText("Weet je zeker dat je dit artikel wil aanpassen?");
        Optional<ButtonType> result = confirm.showAndWait();
        if(result.get()==ButtonType.OK){

        try {

            String sql = "UPDATE PICKLOCATIES SET artikelcode=?,locatie=?,omschrijving=?, eenheid=?, hal=?, code=?, aantal=?, initiaal=?, max=? WHERE ID='" + pickIdTxt.getText() + "'";
            conn = ConnectDatabase.conDB();
            pst = conn.prepareStatement(sql);
            pst.setString(1, autocompleteTf.getText());
            pst.setString(3, pickDescTxt.getText());
            pst.setString(2, autocompleteLoc.getText());
            pst.setString(4, pickEenheidTxt.getText());
            pst.setString(5, autocompleteHal.getText());
            pst.setString(6, oesTxt.getText());
            pst.setString(7, pickAantalTxt.getText());
            pst.setString(8, pickInitiaalTxt.getText());
            pst.setString(9, maxAantalTxt.getText());
            pst.executeUpdate();
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setContentText("Artikel Aangepast");
            dialog.setHeaderText("Informatie");
            dialog.showAndWait();
            
            ClearAllFields();
//            
//            searchTable1.refresh();
            ReloadPickTable();
//            LoadPickTableMethod();
           // LoadPickTableSearch(); //replace with reloadsearchtable
           ReloadSearchTable();
        } catch (Exception e) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
        } else {
            
        }
    }

    @FXML
    private void ModifyBulk(ActionEvent event) {
//       
        try {
            String sql = "UPDATE  VOORRAADSYSTEEM SET artikelcode=?, locatie=?, omschrijving=?, aantal=?,eenheid=?,initiaal=?,datum=?, le=? WHERE ID='" + bulkIDtxt.getText() + "'";
            conn = ConnectDatabase.conDB();
            pst = conn.prepareStatement(sql);
            String date = new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).format(new Date());
            pst.setString(1, autocompleteBulkArt.getText());
            pst.setString(4, bulkAantalTxt.getText());
            pst.setString(2, autocompleteBulkLoc.getText());
            pst.setString(3, bulkDescTxt.getText());
            pst.setString(5, bulkEenheidTxt.getText());
            pst.setString(6, bulkInitiaalTxt.getText());
            pst.setString(7, date);
            pst.setString(8, leTextField.getText());
            pst.executeUpdate();
            
            ClearAllBulkFields();
            ReloadBulkTable();
            
            
            AddRecentsMethod();
            
            //LoadBulkSearchMethod(); //replace with reloadbulktable2 method
            ReloadBulkTable2();
            
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setContentText("Artikel Aangepast");
            dialog.setHeaderText("Informatie");
            dialog.showAndWait();
        } catch (Exception e) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
        
        
        
    }

    @FXML
    private void DeletePick(ActionEvent event) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Bevestiging");
        confirm.setHeaderText("Locatie/Artikel Wordt Verwijderd");
        confirm.setContentText("Weet je zeker dat je dit artikel wil Verwijderen?");
        Optional<ButtonType> result = confirm.showAndWait();
        if(result.get()==ButtonType.OK){
        AddPickHistory();
        try {
            TableModel2 model = (TableModel2) searchTable1.getSelectionModel().getSelectedItem();
            String pickid = pickIdTxt.getText();
            String sql = "DELETE FROM PICKLOCATIES WHERE ID=?";
            conn = ConnectDatabase.conDB();
            pst = conn.prepareStatement(sql);
            pst.setString(1,pickid);
            pst.executeUpdate();
            pst.close();
            rs.close();
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setContentText("Artikel Verwijderd");
            dialog.setHeaderText("Informatie");
            dialog.showAndWait();
            ClearAllFields();
            ReloadPickTable();

            //LoadPickTableSearch(); //replace with reloadsearchtable method
            
            ReloadSearchTable();
            
        } catch (Exception e) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
        } else {
            
        }
    }

    @FXML
    private void DeleteBulk(ActionEvent event) {
        
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Bevestiging");
        confirm.setHeaderText("Locatie/Artikel Wordt Verwijderd");
        confirm.setContentText("Weet je zeker dat je dit artikel wil Verwijderen?");
        Optional<ButtonType> result = confirm.showAndWait();
        if(result.get()==ButtonType.OK){
        AddBulkHistory();
        
        try {
            
            BulkTableModel bmodel = (BulkTableModel) bulkTable21.getSelectionModel().getSelectedItem();
            String bulkid =  bulkIDtxt.getText();
            String sql = "DELETE  FROM VOORRAADSYSTEEM WHERE ID=?";
            conn = ConnectDatabase.conDB();
            pst = conn.prepareStatement(sql);
            pst.setString(1, bulkid);
            pst.executeUpdate();
            pst.close();
            rs.close();
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setContentText("Artikel Verwijderd");
            dialog.setHeaderText("Informatie");
            dialog.showAndWait();
            ClearAllBulkFields();
            ReloadBulkTable();
            //LoadBulkSearchMethod(); //replace with reloadbulktable2 method
            ReloadBulkTable2();
        } catch (Exception e) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
        } else {
            
        }
    }

    @FXML
    private void EmptyBulk(ActionEvent event) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Bevestiging");
        confirm.setHeaderText("Locatie Wordt Leeggeboekt");
        confirm.setContentText("Weet je zeker dat je deze locatie wil leegboeken?");
        Optional<ButtonType> result = confirm.showAndWait();
        if(result.get()==ButtonType.OK){
        AddBulkHistory();
        try {
            BulkTableModel bmodel = (BulkTableModel) bulkTable21.getSelectionModel().getSelectedItem();
            String sql = "UPDATE  VOORRAADSYSTEEM SET artikelcode=?, locatie=?, omschrijving=?, aantal=?,eenheid=?,initiaal=?,datum=?,le=? WHERE ID='" + bulkIDtxt.getText() + "'";
            conn = ConnectDatabase.conDB();
            pst = conn.prepareStatement(sql);

            String date = new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).format(new Date());
            String leegdesc = "leeg";
            String leegquant = "0";
            String leegunit = " ";
            pst.setString(1, "LEEG");
            pst.setString(2, autocompleteBulkLoc.getText());
            pst.setString(3, "Lege Locatie");
            pst.setString(4, "0");
            pst.setString(5, "Leeg");
            pst.setString(6, bulkInitiaalTxt.getText());
            pst.setString(7, date);
            pst.setString(8, " ");
            pst.executeUpdate();
            pst.close();
            rs.close();
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setContentText(" Locatie Leeggeboekt");
            dialog.setHeaderText("Informatie");
            dialog.showAndWait();

            ReloadBulkTable();
            //LoadBulkSearchMethod(); //replace with reloadbulktablemethod
           ReloadBulkTable2();
            ClearAllBulkFields();
            ClearAllFields();

        } catch (Exception e) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
        } else {
            
        }
    }

    @FXML 
    private void SearchPTableMouseClicked(MouseEvent event) {
        TableModelPickSearch smodel = searchTable.getItems().get(searchTable.getSelectionModel().getSelectedIndex());
        clickDescP.setText(smodel.getOmschrijving());
    }
    
    
    @FXML
    private void SearchBTableMouseClicked(MouseEvent event) {
        BulkTableModelSearch bmodel = bulkTable2.getItems().get(bulkTable2.getSelectionModel().getSelectedIndex());
        clickDescB.setText(bmodel.getOmschrijving());
        
    }
    @FXML
    private void PickHistoryTableMouseClicked(MouseEvent event) {
        PickHistoryTableModel ptableH = pickHistoryTbl.getItems().get(pickHistoryTbl.getSelectionModel().getSelectedIndex());
        pickHistoryId.setText(ptableH.getID());
        pickHistoryArt.setText(ptableH.getArtikelcode());
        pickHistoryDesc.setText(ptableH.getOmschrijving());
        pickHistoryEenh.setText(ptableH.getEenheid());
        pickHistoryHal.setText(ptableH.getHal());
        pickHistoryLoc.setText(ptableH.getLocatie());
        pickHistoryOes.setText(ptableH.getOescode());
        pickHistoryAant.setText(ptableH.getAantal());
        pickHistoryInit.setText(ptableH.getInitiaal());
        pickHistoryMax.setText(ptableH.getMax());
    }

//    @FXML
//    private void PickTableMouseClicked(MouseEvent event) {
//        TableModel2 ptable = searchTable1.getItems().get(searchTable1.getSelectionModel().getSelectedIndex());
//        pickIdTxt.setText(ptable.getID());
//        autocompleteTf.setText(ptable.getArtikelcode());
//        pickDescTxt.setText(ptable.getOmschrijving());
//        pickEenheidTxt.setText(ptable.getEenheid());
//        autocompleteHal.setText(ptable.getHal());
//        autocompleteLoc.setText(ptable.getLocatie());
//        oesTxt.setText(ptable.getOescode());
//        pickAantalTxt.setText(ptable.getAantal());
//        pickInitiaalTxt.setText(ptable.getInitiaal());
//        maxAantalTxt.setText(ptable.getMax());
//    }

//    @FXML
//    private void BulkHistoryTableMouseClicked(MouseEvent event) {
//        BulkHistoryTableModel bhtable = bulkHistoryTbl.getItems().get(bulkHistoryTbl.getSelectionModel().getSelectedIndex());
//         bulkArtH.setText(bhtable.getArtikelcode());
//        bulkDescH.setText(bhtable.getOmschrijving());
//        bulkEenheidH.setText(bhtable.getEenheid());
//        bulkLocH.setText(bhtable.getLocatie());
//        bulkAantalH.setText(bhtable.getAantal());
//        bulkInitiaalH.setText(bhtable.getInitiaal());
//        bulkIDH.setText(bhtable.getID());
//    }
    @FXML
    private void BulkTableMouseClicked(MouseEvent event) {
        BulkTableModel btable = bulkTable21.getItems().get(bulkTable21.getSelectionModel().getSelectedIndex());
        
        autocompleteBulkArt.setText(btable.getArtikelcode());
        bulkDescTxt.setText(btable.getOmschrijving());
        bulkEenheidTxt.setText(btable.getEenheid());
        autocompleteBulkLoc.setText(btable.getLocatie());
        bulkAantalTxt.setText(btable.getAantal());
        bulkInitiaalTxt.setText(btable.getInitiaal());
        bulkIDtxt.setText(String.valueOf(btable.getID()));
        leTextField.setText((String.valueOf(btable.getLe())));
    }

    @FXML
    private void RecentTableMouseClicked(MouseEvent event) {
        RecentTableModel rtable = recentTable.getItems().get(recentTable.getSelectionModel().getSelectedIndex());
        autocompleteBulkArt.setText(rtable.getArtikelcode());
        bulkDescTxt.setText(rtable.getOmschrijving());
        bulkEenheidTxt.setText(rtable.getEenheid());
        autocompleteBulkLoc.setText(rtable.getLocatie());
        bulkAantalTxt.setText(rtable.getAantal());
        bulkInitiaalTxt.setText(rtable.getInitiaal());

    }
    
    private void ClearPickHistoryFields() {
        pickHistoryId.setText("");
        pickHistoryArt.setText("");
        pickHistoryDesc.setText("");
        pickHistoryEenh.setText("");
        pickHistoryHal.setText("");
        pickHistoryLoc.setText("");
        pickHistoryOes.setText("");
        pickHistoryAant.setText("");
        pickHistoryInit.setText("");
        pickHistoryMax.setText("");
    }

    @FXML
    private void ClearHistoryPickFields() {
         pickHistoryId.setText("");
        pickHistoryArt.setText("");
        pickHistoryDesc.setText("");
        pickHistoryEenh.setText("");
        pickHistoryHal.setText("");
        pickHistoryLoc.setText("");
        pickHistoryOes.setText("");
        pickHistoryAant.setText("");
        pickHistoryInit.setText("");
        pickHistoryMax.setText("");
        pickHistoryFilter.setText("");
        ReloadPickHistory();
    }
    
    @FXML
    private void ClickClearAllFields() {
        pickIdTxt.setText("");
        autocompleteTf.setText("");
        pickDescTxt.setText("");
        pickEenheidTxt.setText("");
        autocompleteHal.setText("");
        autocompleteLoc.setText("");
        oesTxt.setText("");
        pickAantalTxt.setText("");
        pickInitiaalTxt.setText("");
        pickIdTxt.setText("");
        maxAantalTxt.setText("");
        pickFilterBdesc.setText("");
        ReloadPickTable();

    }

    @FXML
    private void ClickClearAllBulkFields() {
        autocompleteBulkArt.setText("");
        autocompleteBulkLoc.setText("");
        bulkDescTxt.setText("");
        bulkEenheidTxt.setText("");
        bulkAantalTxt.setText("");
        bulkInitiaalTxt.setText("");
        bulkIDtxt.setText("");
        bulkFilterBdesc.setText("");
        leTextField.setText("");
        

    }

    private void ClearHistoryBulkFields() {
         bulkArtH.setText("");
        bulkDescH.setText("");
        bulkEenheidH.setText("");
        bulkLocH.setText("");
        bulkAantalH.setText("");
        bulkInitiaalH.setText("");
        bulkIDH.setText("");
    }
    
    @FXML
    private void ClearBulkHistoryFields() {
        bulkArtH.setText("");
        bulkDescH.setText("");
        bulkEenheidH.setText("");
        bulkLocH.setText("");
        bulkAantalH.setText("");
        bulkInitiaalH.setText("");
        bulkIDH.setText("");
        bulkSearchFilter1.setText("");
        ReloadBulkHistory();
        
    }
    
    
    private void LoadRecentsMethod() {
        String sqlrecent = "SELECT * FROM RECENTBULKADDED";
        try {
            Connection con = ConnectDatabase.conDB();
            ResultSet rs = con.createStatement().executeQuery(sqlrecent);
            while (rs.next()) {
                recentlocaties.add(new RecentTableModel(
                        rs.getString("ARTIKELCODE"),
                        rs.getString("LOCATIE"),
                        rs.getString("OMSCHRIJVING"),
                        rs.getString("AANTAL"),
                        rs.getString("EENHEID"),
                        rs.getString("INITIAAL")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //col_idR.setCellValueFactory(new PropertyValueFactory<>("Id"));
        col_locatieR.setCellValueFactory(new PropertyValueFactory<>("Locatie"));
        col_artikelcodeR.setCellValueFactory(new PropertyValueFactory<>("Artikelcode"));
        col_omschrijvingR.setCellValueFactory(new PropertyValueFactory<>("Omschrijving"));
        col_aantalR.setCellValueFactory(new PropertyValueFactory<>("Aantal"));
        col_eenheidR.setCellValueFactory(new PropertyValueFactory<>("Eenheid"));
        col_initiaalR.setCellValueFactory(new PropertyValueFactory<>("Initiaal"));
        recentTable.setItems(recentlocaties);
        recentTable.refresh();
    }

    
    private void ReloadSearchTable() {
        pickles.clear();
        LoadPickTableSearch();
    }
    
    private void ReloadBulkTable2() {
        buckles.clear();
        LoadBulkSearchMethod();
    }
    
    private void ReloadPickHistory() {
        pickhistory.clear();
        LoadPickHistory();
    }
    
    private void ReloadPickTable() {
        picklocaties.clear();
//        LoadPickTableMethod();

    }
    
    private void ReloadBulkHistory() {
//        bulkhistoryFilter.clear();
//        LoadBulkHistory();
    }

    private void ReloadBulkTable() {
        bulklocaties.clear();
        LoadBulkTableMethod();
    }

    private void ReloadRecentsTable() {
        recentlocaties.clear();
        LoadRecentsMethod();
    }

    private void AddRecentsMethod() {

        try {
            String sql = "INSERT INTO RECENTBULKADDED"
                    + "(artikelcode, locatie, omschrijving, aantal,eenheid,initiaal)"
                    + "VALUES (?,?,?,?,?,?)";
            conn = ConnectDatabase.conDB();
            pst = conn.prepareStatement(sql);
            pst.setString(1, autocompleteBulkArt.getText());
            pst.setString(4, bulkAantalTxt.getText());
            pst.setString(2, autocompleteBulkLoc.getText());
            pst.setString(3, bulkDescTxt.getText());
            pst.setString(5, bulkEenheidTxt.getText());
            pst.setString(6, bulkInitiaalTxt.getText());
            pst.executeUpdate();
//            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
//            dialog.setContentText("Artikel Toegevoegd Aan Recent");
//            dialog.setHeaderText("Informatie");
//            dialog.showAndWait();
            ClearAllBulkFields();
            ReloadRecentsTable();
        } catch (Exception e) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private ObservableList<ObservableList> data;
    String sql3 = "SELECT * FROM PICKLOCATIES";

    private void LoadPickHistory() {
        pickHistoryTbl.getItems().clear();
        String sqlpick = "SELECT * FROM PICKHISTORY";

        try {
            Connection con = ConnectDatabase.conDB();
            ResultSet rs = con.createStatement().executeQuery(sqlpick);
            while (rs.next()) {
                pickhistory.add(new PickHistoryTableModel(
                        rs.getString("ID"),
                        rs.getString("LOCATIE"),
                        rs.getString("ARTIKELCODE"),
                        rs.getString("OMSCHRIJVING"),
                        rs.getString("EENHEID"),
                        rs.getString("HAL"),
                        rs.getString("CODE"),
                        rs.getString("AANTAL"),
                        rs.getString("INITIAAL"),
                        rs.getString("MAX"),
                        rs.getString("DATUM")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //col_idR.setCellValueFactory(new PropertyValueFactory<>("Id"));
        col_pickIdH.setCellValueFactory(new PropertyValueFactory<>("Id"));
        col_pickLocH.setCellValueFactory(new PropertyValueFactory<>("Locatie"));
        col_pickArtH.setCellValueFactory(new PropertyValueFactory<>("Artikelcode"));
        col_pickDescH.setCellValueFactory(new PropertyValueFactory<>("Omschrijving"));
        col_pickEenhH.setCellValueFactory(new PropertyValueFactory<>("Eenheid"));
        col_pickHalH.setCellValueFactory(new PropertyValueFactory<>("Hal"));
        col_pickCodeH.setCellValueFactory(new PropertyValueFactory<>("Code"));
        col_pickAantH.setCellValueFactory(new PropertyValueFactory<>("Aantal"));
        col_pickInitH.setCellValueFactory(new PropertyValueFactory<>("Initiaal"));
        col_pickMaxH.setCellValueFactory(new PropertyValueFactory<>("Max"));
        col_DatumH.setCellValueFactory(new PropertyValueFactory<>("Datum"));

        pickHistoryTbl.setItems(pickhistory);
        pickHistoryTbl.refresh();

    }

//    private void LoadPickTableMethod() {
//
//        String sqlpick = "SELECT * FROM PICKLOCATIES";
//
//        try {
//            Connection con = ConnectDatabase.conDB();
//            ResultSet rs = con.createStatement().executeQuery(sqlpick);
//            while (rs.next()) {
//                picklocaties.add(new TableModel2(
//                        rs.getString("ID"),
//                        rs.getString("LOCATIE"),
//                        rs.getString("ARTIKELCODE"),
//                        rs.getString("OMSCHRIJVING"),
//                        rs.getString("EENHEID"),
//                        rs.getString("HAL"),
//                        rs.getString("CODE"),
//                        rs.getString("AANTAL"),
//                        rs.getString("INITIAAL"),
//                        rs.getString("MAX")));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        //col_idR.setCellValueFactory(new PropertyValueFactory<>("Id"));
//        col_pickId.setCellValueFactory(new PropertyValueFactory<>("Id"));
//        col_pickLoc.setCellValueFactory(new PropertyValueFactory<>("Locatie"));
//        col_pickArt.setCellValueFactory(new PropertyValueFactory<>("Artikelcode"));
//        col_pickDesc.setCellValueFactory(new PropertyValueFactory<>("Omschrijving"));
//        col_pickEenh.setCellValueFactory(new PropertyValueFactory<>("Eenheid"));
//        col_pickHal.setCellValueFactory(new PropertyValueFactory<>("Hal"));
//        col_pickCode.setCellValueFactory(new PropertyValueFactory<>("Code"));
//        col_pickAant.setCellValueFactory(new PropertyValueFactory<>("Aantal"));
//        col_pickInit.setCellValueFactory(new PropertyValueFactory<>("Initiaal"));
//        col_pickMax.setCellValueFactory(new PropertyValueFactory<>("Max"));
//
//        searchTable1.setItems(picklocaties);
//        searchTable1.refresh();
//
//    }

    private void LoadPickTableSearch() {
        String sqlpick = "SELECT * FROM PICKLOCATIES";
        try {
            Connection con = ConnectDatabase.conDB();
            ResultSet rs = con.createStatement().executeQuery(sqlpick);
            while (rs.next()) {
                pickles.add(new TableModelPickSearch(
                        rs.getString("ID"),
                        rs.getString("LOCATIE"),
                        rs.getString("ARTIKELCODE"),
                        rs.getString("OMSCHRIJVING"),
                        rs.getString("EENHEID"),
                        rs.getString("HAL")
                ));

            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //col_idR.setCellValueFactory(new PropertyValueFactory<>("Id"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        col_locatie.setCellValueFactory(new PropertyValueFactory<>("Locatie"));
        col_artikelcode.setCellValueFactory(new PropertyValueFactory<>("Artikelcode"));
        col_omschrijving.setCellValueFactory(new PropertyValueFactory<>("Omschrijving"));
        col_eenheid.setCellValueFactory(new PropertyValueFactory<>("Eenheid"));
        col_hal.setCellValueFactory(new PropertyValueFactory<>("Hal"));
//        col_pickCode.setCellValueFactory(new PropertyValueFactory<>("Code"));
//        col_pickAant.setCellValueFactory(new PropertyValueFactory<>("Aantal"));
//        col_pickInit.setCellValueFactory(new PropertyValueFactory<>("Initiaal"));
//        col_pickMax.setCellValueFactory(new PropertyValueFactory<>("Max"));
        searchTable.setItems(pickles);
    }

    private void LoadBulkSearchMethod() {
        String sql = "SELECT * FROM VOORRAADSYSTEEM";
        try {
            Connection con = ConnectDatabase.conDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                buckles.add(new BulkTableModelSearch(rs.getString("ID"),
                        rs.getString("ARTIKELCODE"),
                        rs.getString("LOCATIE"),
                        rs.getString("AANTAL"),
                        rs.getString("OMSCHRIJVING"),
                        rs.getString("Eenheid"),
                        rs.getString("Initiaal"),
                        rs.getString("Datum"),
                        rs.getString("Le")));
            }

        } catch (SQLException ex) {
//            Logger.getLogger(ZoekenBulkFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_artikelcode1.setCellValueFactory(new PropertyValueFactory<>("Artikelcode"));
        col_locatie1.setCellValueFactory(new PropertyValueFactory<>("Locatie"));
        col_omschrijving1.setCellValueFactory(new PropertyValueFactory<>("Omschrijving"));
        col_aantal1.setCellValueFactory(new PropertyValueFactory<>("Aantal"));
        col_eenheid1.setCellValueFactory(new PropertyValueFactory<>("Eenheid"));
        col_initiaal1.setCellValueFactory(new PropertyValueFactory<>("Initiaal"));
        col_datum1.setCellValueFactory(new PropertyValueFactory<>("Datum"));
        bulkTable2.setItems(buckles);
        col_lenummer.setCellValueFactory(new PropertyValueFactory<>("Le"));
    }

//    private void LoadBulkHistory() {
//        String sql = "SELECT * FROM HISTORY";
//        try {
//            Connection con = ConnectDatabase.conDB();
//            ResultSet rs = con.createStatement().executeQuery(sql);
//            while (rs.next()) {
//                bulkhistoryFilter.add(new BulkHistoryTableModel(rs.getString("ID"),
//                        rs.getString("ARTIKELCODE"),
//                        rs.getString("LOCATIE"),
//                        rs.getString("AANTAL"),
//                        rs.getString("OMSCHRIJVING"),
//                        rs.getString("Eenheid"),
//                        rs.getString("Initiaal"),
//                        rs.getString("Datum")));
//            }
//        } catch (SQLException ex) {
////            Logger.getLogger(ZoekenBulkFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        col_BulkArtH.setCellValueFactory(new PropertyValueFactory<>("Artikelcode"));
//        col_BulkLocH.setCellValueFactory(new PropertyValueFactory<>("Locatie"));
//        col_BulkDescH.setCellValueFactory(new PropertyValueFactory<>("Omschrijving"));
//        col_BulkAantH.setCellValueFactory(new PropertyValueFactory<>("Aantal"));
//        col_BulkEenhH.setCellValueFactory(new PropertyValueFactory<>("Eenheid"));
//        col_BulkInitH.setCellValueFactory(new PropertyValueFactory<>("Initiaal"));
//        col_BulkDatumH.setCellValueFactory(new PropertyValueFactory<>("Datum"));
//        bulkHistoryTbl.setItems(bulkhistoryFilter);
//        bulkHistoryTbl.refresh();
//    }

    private void LoadBulkTableMethod() {
        bulkTable21.getItems().clear();
        String sql = "SELECT * FROM VOORRAADSYSTEEM";
        try {
            Connection con = ConnectDatabase.conDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                bulklocaties.add(new BulkTableModel(rs.getInt("ID"),
                        rs.getString("ARTIKELCODE"),
                        rs.getString("LOCATIE"),
                        rs.getString("AANTAL"),
                        rs.getString("OMSCHRIJVING"),
                        rs.getString("Eenheid"),
                        rs.getString("Initiaal"),
                        rs.getString("Datum"),
                        rs.getString("Le")));
            }
        } catch (SQLException ex) {
//            Logger.getLogger(ZoekenBulkFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_artikelcode11.setCellValueFactory(new PropertyValueFactory<>("Artikelcode"));
        col_locatie11.setCellValueFactory(new PropertyValueFactory<>("Locatie"));
        col_omschrijving11.setCellValueFactory(new PropertyValueFactory<>("Omschrijving"));
        col_aantal11.setCellValueFactory(new PropertyValueFactory<>("Aantal"));
        col_eenheid11.setCellValueFactory(new PropertyValueFactory<>("Eenheid"));
        col_initiaal11.setCellValueFactory(new PropertyValueFactory<>("Initiaal"));
        col_datum11.setCellValueFactory(new PropertyValueFactory<>("Datum"));
        bulkTable21.setItems(bulklocaties);
        bulkTable21.refresh();
        col_lebulk.setCellValueFactory(new PropertyValueFactory<>("Le"));

    }

    private void ClearAllFields() {
        pickIdTxt.setText("");
        autocompleteTf.setText("");
        pickDescTxt.setText("");
        pickEenheidTxt.setText("");
        autocompleteHal.setText("");
        autocompleteLoc.setText("");
        oesTxt.setText("");
        pickAantalTxt.setText("");
        pickInitiaalTxt.setText("");
        maxAantalTxt.setText("");
        searchfield2.setText("");
    }

    private void ClearAllBulkFields() {
        autocompleteBulkArt.setText("");
        autocompleteBulkLoc.setText("");
        bulkDescTxt.setText("");
        bulkEenheidTxt.setText("");
        bulkAantalTxt.setText("");
        bulkInitiaalTxt.setText("");
        bulkIDtxt.setText("");
        searchfield2.setText("");
        bulkFilterBdesc.setText("");
        leTextField.setText("");
        //bulkSearchFilter.setText("");
    }
}
