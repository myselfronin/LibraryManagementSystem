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
public class EditBookController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection Con;
    PreparedStatement ps;
    ResultSet rs;
        @FXML
    private TextField editBookCodeTextField;

    @FXML
    private TextField editISBNTextField;

    @FXML
    private TextField editNameTextField;

    @FXML
    private TextField editAuthorTextField;

    @FXML
    void editBtnPushed(ActionEvent event) throws SQLException {
       if(editBookCodeTextField.getText().trim().equals("")||
                editNameTextField.getText().trim().equals("")||
                editAuthorTextField.getText().trim().equals("")||
                editISBNTextField.getText().trim().equals(""))
       {
           
       }
       else{
           try{
            String sql= " update book_info set name =? ,author_name =? ,isbn =? where bookCode = ? ";
        ps = Con.prepareStatement(sql);
        ps.setString(1,editNameTextField.getText());
        ps.setString(2,editAuthorTextField.getText());
        ps.setString(3,editISBNTextField.getText());
        ps.setString(4,editBookCodeTextField.getText());
        ps.executeUpdate();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Updated");
        alert.setContentText("The book has successfully been Updated");
        alert.show();
        editBookCodeTextField.setText(null);
        editNameTextField.setText(null);
        editISBNTextField.setText(null);
        editAuthorTextField.setText(null);
        ((Node)(event.getSource())).getScene().getWindow().hide();
       }
       catch(NullPointerException ex)
       {
           
       }
       }
    }
    public void bookQuery() throws SQLException
    {
        String sql = "select * from  book_info where bookcode = ?";
        ps= Con.prepareStatement(sql);
        ps.setString(1,editBookCodeTextField.getText());
        rs = ps.executeQuery();
        if(rs.next())
        {
            editNameTextField.setText(rs.getString("name"));
            editAuthorTextField.setText(rs.getString("author_name"));
            editISBNTextField.setText(rs.getString("isbn"));
            
        }
        else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Book Code Error");
            alert.setContentText("Sorry the Book is not registered ");
            alert.show();
            editBookCodeTextField.setText(null);
        }
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DbConnection connect = new DbConnection();
        try {
            Con = connect.DbConnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditBookController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EditBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
