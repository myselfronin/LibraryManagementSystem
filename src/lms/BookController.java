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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Rabinson
 */
public class BookController implements Initializable {

    /**
     * Initializes the controller class.
     */
      Connection Con;
    PreparedStatement ps;
    ResultSet rs;
    @FXML
    private TextField bookCodeTextField;

    @FXML
    private Label isbnLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label authorLabel;

    @FXML
    void deleteBtnPushed(ActionEvent event) throws SQLException {
        String sql = "delete from book_info where bookcode = ?";
        ps= Con.prepareStatement(sql);
        ps.setString(1,bookCodeTextField.getText());
        ps.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Book Deleted");
        alert.setContentText("The book has successfully been deleted");
        alert.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
     public void bookQuery() throws SQLException
    {
        String sql = "select * from  book_info where bookcode = ?";
        ps= Con.prepareStatement(sql);
        ps.setString(1,bookCodeTextField.getText());
        rs = ps.executeQuery();
        if(rs.next())
        {
            nameLabel.setText(rs.getString("name"));
            authorLabel.setText(rs.getString("author_name"));
            isbnLabel.setText(rs.getString("isbn"));
            
        }
        else
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Book Code Error");
            alert.setContentText("Sorry the Book is not registered ");
            alert.show();
            bookCodeTextField.setText(null);
        }
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        DbConnection connect = new DbConnection();
          try {
              Con = connect.DbConnect();
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (SQLException ex) {
              Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }    
    
}
