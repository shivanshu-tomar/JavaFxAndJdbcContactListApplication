package sample;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.math.BigInteger;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
    Connection con;
    int sno ;



    private int getsno() throws SQLException {
        Statement stm = con.createStatement();
        ResultSet r = stm.executeQuery("select * from  list");
        String s = "";
        while (r.next()) {
             s = r.getString(1);
        }
        return Integer.parseInt(s);
    }



    public void getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/ContactList";
        String user = "root";
        String password ="";
        con = DriverManager.getConnection(url,user,password);

    }
    public void insert(int sno,String Name, String mob,int Bucks) throws SQLException {
        PreparedStatement stm = con.prepareStatement("INSERT INTO `list` (`Sno`, `Name`, `Mob no.`, `Bucks`) VALUES (?, ?, ?, ?)");
        stm.setInt(1,++sno);
        stm.setString(2,Name);
        stm.setString(3,mob);
        stm.setInt(4,Bucks);
        stm.execute();
        //sc.close();

    }
    public ObservableList<Contact> getContacts() throws SQLException {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        Statement stm = con.createStatement();
        ResultSet r =stm.executeQuery("select * from  list");
        while (r.next()){
            String name = r.getString(2);
            String sno = r.getString(1);
            String mob = r.getString(3);
            String buck = r.getString(4);
            contacts.add(new Contact(new SimpleStringProperty(name),new SimpleStringProperty(mob),new SimpleStringProperty(sno),new SimpleStringProperty(buck)));
        }
        return contacts;
    }
    public void setTable() throws SQLException {

        ObservableList<Contact> contacts = getContacts();
        table.setItems(contacts);
        nameTable.setCellValueFactory((a)->a.getValue().nameProperty());
        contactTable.setCellValueFactory((a)->a.getValue().mobProperty());
        SnoTable.setCellValueFactory((a)->a.getValue().snoProperty());
        bucksTable.setCellValueFactory((a)->a.getValue().bucksProperty());
        sno = Integer.parseInt(contacts.get(contacts.size()-1).snoProperty().getValue());
    }
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private TableView<Contact> table;

    @FXML
    private TableColumn<Contact,String> SnoTable;

    @FXML
    private TableColumn<Contact,String> nameTable;

    @FXML
    private TableColumn<Contact,String> contactTable;

    @FXML
    private TableColumn<Contact,String> bucksTable;

    @FXML
    private TextField nametxt;

    @FXML
    private TextField contacttxt;

    @FXML
    private TextField buckstxt;

    @FXML
    private Button addButton;

    @FXML
    void onClick(ActionEvent event) throws SQLException {
            String Name = nametxt.getText();
            String mob = contacttxt.getText();
            int bucks = Integer.parseInt(buckstxt.getText());
            insert(sno,Name,mob,bucks);
            nametxt.setText("");
            contacttxt.setText("");
            buckstxt.setText("");
            setTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getConnection();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            setTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
