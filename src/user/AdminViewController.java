package user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable{

    @FXML private TableView<User> userTable;
    @FXML private TableColumn<User, Integer> userIDColumn;
    @FXML private TableColumn<User, String> firstNameColumn;
    @FXML private TableColumn<User, String> lastNameColumn;
    @FXML private TableColumn<User, String> userNameColumn;
    @FXML private TableColumn<User, String> passwordColumn;
    //@FXML private TableColumn<User, Blob> saltColumn;
    @FXML private TableColumn<User, String> sinColumn;
    @FXML private TableColumn<User, String> phoneColumn;
    @FXML private TableColumn<User, String> emailColumn;
    @FXML private TableColumn<User, LocalDate> dobColumn;
    @FXML private TableColumn<User, String> isAdminColumn;

    @FXML private Button editUserButton;
    @FXML private Button addUserButton;
    @FXML private Button backButton;

    public void addButtonPressed(ActionEvent event) throws IOException
    {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "NewUserView.fxml", "Add User");
    }

    public void loadUsers() throws SQLException
    {
        ObservableList<User> users = FXCollections.observableArrayList();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userDB?useSSL=false", "root", "");

            statement = conn.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next())
            {
                User newUser = new User(
                                        resultSet.getString("firstName"),
                                        resultSet.getString("lastName"),
                                        resultSet.getString("userName"),
                                        resultSet.getString("userPass"),
                                        //resultSet.getBlob("salt"),
                                        resultSet.getString("sinNum"),
                                        resultSet.getString("phoneNum"),
                                        resultSet.getString("emailAddress"),
                                        resultSet.getDate("dob").toLocalDate(),
                                        resultSet.getBoolean("isAdmin"));

                users.add(newUser);
            }

            userTable.getItems().addAll(users);

        }catch (Exception e){
            System.err.println(e);
        }
        finally
        {
            if (conn != null)
                conn.close();
            if(statement != null)
                statement.close();
            if(resultSet != null)
                resultSet.close();
        }
    }

    public void backButtonPressed(ActionEvent event) throws IOException
    {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "product/ProductView.fxml", "Inventory");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userIDColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("userID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userPass"));
        //saltColumn.setCellValueFactory(new PropertyValueFactory<User, Blob>("salt"));
        sinColumn.setCellValueFactory(new PropertyValueFactory<User, String>("sinNum"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<User, String>("phoneNum"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("emailAddress"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<User, LocalDate>("dob"));
        isAdminColumn.setCellValueFactory(new PropertyValueFactory<User, String>("isAdmin"));

        try{
            loadUsers();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }

    }
}
