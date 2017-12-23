package user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import product.ProductViewController;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ResourceBundle;


public class LoginViewController implements Initializable{

    @FXML private TextField userNameTextField;
    @FXML private PasswordField userPassTextField;
    @FXML private Label errorLabel;


    public void loginButtonPushed(ActionEvent event) throws IOException, NoSuchAlgorithmException
    {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        String userName = userNameTextField.getText();

        try{

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userDB?useSSL=false", "root", "");

            String sql = "SELECT * FROM users WHERE userName = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, userName);

            resultSet = ps.executeQuery();

            String userPass = null;
            byte[] salt = null;
            boolean isAdmin = false;
            User user = null;

            while (resultSet.next())
            {
                userPass = resultSet.getString("userPass");

                Blob blob = resultSet.getBlob("salt");


                    int blobLength = (int) blob.length();
                    salt = blob.getBytes(1, blobLength);


                isAdmin = resultSet.getBoolean("isAdmin");

                user = new User(
                                resultSet.getString("firstName"),
                                resultSet.getString("lastName"),
                                resultSet.getString("userName"),
                                resultSet.getString("userPass"),
                                //resultSet.getByte("salt"),
                                resultSet.getString("sinNum"),
                                resultSet.getString("phoneNum"),
                                resultSet.getString("emailAddress"),
                                resultSet.getDate("dob").toLocalDate(),
                                resultSet.getBoolean("isAdmin"));
            }

            String password = PasswordGenerator.getSHA512Password(userPassTextField.getText(), salt);

            SceneChanger sc = new SceneChanger();

            if (password.equals(userPass))
                SceneChanger.setLoggedInUser(user);

            if (password.equals(userPass) && isAdmin)
                sc.changeScenes(event, "AdminView.fxml", "All Users");
            else if (password.equals(userPass))
            {
                UserViewController viewController = new UserViewController();

                sc.changeScenes(event, "UserView.fxml", "Employee", user, viewController);
            }
            else
                errorLabel.setText("The userName and password are incorrect");
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            errorLabel.setText("Connection not found");
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorLabel.setText("");
    }
}
