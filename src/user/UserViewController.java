package user;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import user.*;

public class UserViewController implements Initializable{

    @FXML private TextField userIDTextField;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField userNameTextField;
    @FXML private TextField userPassTextField;
    @FXML private TextField sinTextField;
    @FXML private TextField phoneNumTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField dobTextField;
    @FXML private TextField adminTextField;
    @FXML private Label errorLabel;

    @FXML private Button backButton;

    private User user;

    public void backButtonPressed(ActionEvent event) throws IOException
    {
        SceneChanger.setLoggedInUser(user);
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "product/ProductView.fxml", "Inventory");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userIDTextField.setText(Integer.toString(user.getUserID()));
        firstNameTextField.setText(user.getFirstName());
        lastNameTextField.setText(user.getLastName());
        userNameTextField.setText(user.getUserName());
        userPassTextField.setText(user.getUserPass());
        sinTextField.setText(user.getSinNum());
        phoneNumTextField.setText(user.getPhoneNum());
        emailTextField.setText(user.getEmailAddress());
        dobTextField.setText(user.getDob().toString());
        adminTextField.setText(String.valueOf(user.isAdmin()));
        errorLabel.setText("");
    }
}
