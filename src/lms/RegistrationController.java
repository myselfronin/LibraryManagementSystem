/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Rabinson
 */
public class RegistrationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection Con;
    PreparedStatement ps;
    
    @FXML
    private TextField addRolll;

    @FXML
    private TextField addName;

    @FXML
    private TextField addAddress;

    @FXML
    private TextField addFaculty;

    @FXML
    private TextField addContact;

    @FXML
    void RegisterBtnPushed(ActionEvent event) throws SQLException {
     if(addRolll.getText().trim().equals("")||
             addName.getText().trim().equals("")||
             addAddress.getText().trim().equals("")||
             addFaculty.getText().trim().equals(""))
     {
         
     }
     else{
         try{
                String sql = "insert into student_info(roll_no,name,address,faculty,contact) values(?,?,?,?,?)";
        ps = Con.prepareStatement(sql);
        ps.setString(1,addRolll.getText());
       ps.setString(2,addName.getText());
       ps.setString(3,addAddress.getText());
       ps.setString(4,addFaculty.getText());
       ps.setString(5,addContact.getText());
       ps.executeUpdate();
       addRolll.setText(null);
       addName.setText(null);
       addAddress.setText(null);
       addFaculty.setText(null);
       addContact.setText(null);
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle("Successful Registration");
       alert.setContentText("New student has been registered!");
       alert.showAndWait();
       ((Node)(event.getSource())).getScene().getWindow().hide();

       
         }
         catch(SQLException | NullPointerException ex)
         {
             
         }
     }
       
        

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DbConnection connect = new DbConnection();
        try {
            Con=connect.DbConnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
