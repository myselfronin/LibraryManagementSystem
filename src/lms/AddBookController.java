/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Rabinson
 */
public class AddBookController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection Con;
    PreparedStatement ps;
    ResultSet rs;
    @FXML
    private TextField addBookCodeTextField;

    @FXML
    private TextField addISBNTextField;

    @FXML
    private TextField addNameTextField;

    @FXML
    private TextField addAuthorTextField;

    @FXML
    void addBtnPushed(ActionEvent event) throws SQLException {
        if(addBookCodeTextField.getText().trim().equals("")||
                addNameTextField.getText().trim().equals("")||
                addAuthorTextField.getText().trim().equals("")||
                addISBNTextField.getText().trim().equals(""))
        {
            
        }
        else{
   try{
                String sql= " insert into  book_info values(?,?,?,?) ";
        ps = Con.prepareStatement(sql);
        ps.setString(1,addBookCodeTextField.getText());
        ps.setString(2,addNameTextField.getText());
        ps.setString(3,addAuthorTextField.getText());
        ps.setString(4,addISBNTextField.getText());
        ps.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucessfull Registration");
        alert.setContentText("The book has successfully been registered");
        alert.show();
        addBookCodeTextField.setText(null);
        addNameTextField.setText(null);
        addISBNTextField.setText(null);
        addAuthorTextField.setText(null);
        ((Node)(event.getSource())).getScene().getWindow().hide();
   }
   catch(SQLException | NullPointerException ex)
   {
       
   }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DbConnection connect = new DbConnection();
        try {
            Con = connect.DbConnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
