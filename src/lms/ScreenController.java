/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rabinson
 */
public class ScreenController implements Initializable {

    Connection Con;
    PreparedStatement ps;
    ResultSet rs;
    
    @FXML
    private JFXButton issueBookBtn;
    @FXML
    private TextField searchRollTextField;
    
    @FXML
    private TextField bookCodeTextField;

    @FXML
    private Label nameLabel;

    @FXML
    private Label faculyLabel;

    @FXML
    private Label contactLabel;
    
    @FXML
    private Label addressLabel;

    @FXML
    private ScrollPane scrollPane;

   @FXML
   private VBox vbox = new VBox() ;
   
   @FXML
   private VBox vbox1;
   
   @FXML
   private AnchorPane issueBook;
   
   @FXML
   private ScrollPane scrollPane1;
   
   @FXML
   private AnchorPane issuedBooks;
    
    @FXML
    void addBtnPushed(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/lms/registration.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Library Management System");
        stage.show();
    }
    @FXML
    void viewBtnPushed() throws SQLException
    {
        issuedBooks.toFront();
        studentCodeAction();
    }
    @FXML
    void viewBookBtnPushed() throws IOException
            
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/lms/book.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Library Management System");
        stage.show();
    }           
    @FXML
    void issueBookBtnPushed()
    {
        issueBook.toFront();
    }
    public void bookAddBtnPushed() throws IOException
    {
         Stage stage = new Stage();
              Parent root = FXMLLoader.load(getClass().getResource("/lms/addBook.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add New Book");
        stage.show();
    }
    public void bookEditBtnPushed() throws IOException
    {
         Stage stage = new Stage();
              Parent root = FXMLLoader.load(getClass().getResource("/lms/editBook.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add New Book");
        stage.show();
    }
    @FXML
    void studentCodeAction() throws SQLException
    {
        String sql = "select * from student_info where roll_no = ?";
        ps = Con.prepareStatement(sql);
        ps.setString(1,searchRollTextField.getText());
        rs = ps.executeQuery();
        if(rs.next())
        {
            nameLabel.setText(rs.getString("name"));
            faculyLabel.setText(rs.getString("faculty"));
            contactLabel.setText(rs.getString("contact"));
            addressLabel.setText(rs.getString("address"));
            issueBookBtn.setDisable(false);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Roll No Error");
            alert.setContentText("Sorry the Roll No is not registered ");
            alert.show();
            searchRollTextField.setText(null);
        }
        try{
             String sqll = "select * from issue_book join book_info on issue_book.book =book_info.bookcode";
        ps = Con.prepareStatement(sqll);
        rs= ps.executeQuery();
        vbox1 = new VBox();
        while(rs.next())
        {
            
             Pane pane1 = new Pane();
           pane1.setPrefSize(400, 50);
           pane1.setStyle("-fx-background-color: rgb(187,238,255);");
          
           Label bookCodeLabel = new Label();  
           bookCodeLabel.setText(rs.getString("bookcode"));
           bookCodeLabel.setTranslateX(10);
           bookCodeLabel.setTranslateY(10);
            Label name = new Label();
           name.setText(rs.getString("name"));
           name.setTranslateX(60);
           name.setTranslateY(10);
           Label authorLabel = new Label();
           authorLabel.setText(rs.getString("author_name"));
           authorLabel.setTranslateX(120);
           authorLabel.setTranslateY(10);
           pane1.getChildren().addAll(bookCodeLabel,name,authorLabel);
           
           issue(bookCodeLabel.getText());
           
           vbox1.getChildren().add(pane1);
 
        }
        scrollPane1.setContent(vbox1);
        }
        
        catch(SQLException | NullPointerException ex)
        {
            
        }
       
        
    }
  
    public void bookCodeTextFieldAction() throws SQLException
    {
       String sql = "select * from book_info where bookcode = ?";
       ps = Con.prepareStatement(sql);
       ps.setString(1,bookCodeTextField.getText());
       rs = ps.executeQuery();
       
       if(rs.next())
       {
          try{
               Pane pane = new Pane();
           pane.setPrefSize(400, 50);
           pane.setStyle("-fx-background-color: rgb(190,238,255);");
          
           Label bookCodeLabel = new Label();  
           bookCodeLabel.setText(rs.getString("bookcode"));
           bookCodeLabel.setTranslateX(10);
           bookCodeLabel.setTranslateY(10);
            Label name = new Label();
           name.setText(rs.getString("name"));
           name.setTranslateX(60);
           name.setTranslateY(10);
           Label authorLabel = new Label();
           authorLabel.setText(rs.getString("author_name"));
           authorLabel.setTranslateX(120);
           authorLabel.setTranslateY(10);
           pane.getChildren().addAll(name,bookCodeLabel,authorLabel);
           issue(bookCodeLabel.getText());
           
           vbox.getChildren().add(pane);
           scrollPane.setContent(vbox);
           
          }
          catch(NullPointerException | SQLException ex)
          {
              
          }
           
       }
       else
       {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Book Error");
           alert.setContentText("The book code is not registered");
           alert.show();
           bookCodeTextField.setText(null);
       }
    }
    void issue(String bookcode) throws SQLException
    {
        
        String sql = "insert into issue_book values(?,?)";
        ps = Con.prepareStatement(sql);
        ps.setString(1,searchRollTextField.getText());
        ps.setString(2,bookcode);
        ps.executeUpdate();
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        issueBookBtn.setDisable(true);
        DbConnection connect = new DbConnection();
        try {
            Con= connect.DbConnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
