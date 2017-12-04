package product;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;

/**
 *
 *
 * @author kenalexander
 */
public class ProductViewController implements Initializable {


    @FXML private TableView<Product> productTable;

    @FXML private TableColumn<Product, String> vehicleNameColumn;
    @FXML private TableColumn<Product, String> vehicleGenreColumn;
    @FXML private TableColumn<Product, String> vehicleModelColumn;
    @FXML private TableColumn<Product, String> vehicleColorColumn;
    @FXML private TableColumn<Product, String> descriptionColumn;
    @FXML private TableColumn<Product, LocalDate> yearReleasedColumn;
    @FXML private TableColumn<Product, String> askPriceColumn;
    @FXML private TableColumn<Product, Double> sellPriceColumn;

    @FXML private Button addButton;
    @FXML private Button exitButton;
    @FXML private Label totalValueLabel;
    @FXML private Label totalProductLabel;

    private double totalValue = 0;
    private int totalProduct = 0;

    /**
     * This method will populate the list of vehicles into the ObservableList
     * @return
     */
    public void loadProducts(ObservableList<Product> newList)
    {
        this.productTable.setItems(newList);
    }

    /**
     * This method will return the products populated in the ObservableList
     * @returns products
     */
    public ObservableList<Product> getProducts()
    {
        //define an observable list
        ObservableList<Product> products = FXCollections.observableArrayList();

        products.add(new Product("Ford", "Truck", "F250", "Black", "New", LocalDate.of(1992, Month.MARCH, 15), "25000"));
        products.add(new Product("Chevrolet", "Truck", "Silverado", "Grey", "New", LocalDate.of(1992, Month.APRIL, 22), "23000"));
        products.add(new Product("Toyota", "Truck", "Tundra", "Black", "New", LocalDate.of(1992, Month.JANUARY, 9), "25000"));

        products.add(new Product("Ford", "Truck", "F250", "Black", "New", LocalDate.of(1992, Month.MARCH, 15), "25000"));
        products.add(new Product("Chevrolet", "Truck", "Silverado", "Grey", "New", LocalDate.of(1992, Month.APRIL, 22), "23000"));
        products.add(new Product("Toyota", "Truck", "Tundra", "Black", "New", LocalDate.of(1992, Month.JANUARY, 9), "25000"));
        //return the list
        return products;
    }

    /**
     * Initializes the controller class. Instantiates the column values.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        vehicleNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("vehicleName"));
        vehicleGenreColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("vehicleGenre"));
        vehicleModelColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("vehicleModel"));
        vehicleColorColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("vehicleColor"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        yearReleasedColumn.setCellValueFactory(new PropertyValueFactory<Product, LocalDate>("yearReleased"));
        askPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("askPrice"));
        sellPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("sellPrice"));

        productTable.setItems(getProducts());
        calculateValue();
        calculateTotalProduct();

    }

    /**
     * Closes the application
     * @param event
     * @throws IOException
     */
    public void exitButtonPressed(ActionEvent event) throws IOException{
        System.exit(0);
    }

    /**
     * Method to calculate totalProduct and sets value to totalProductLabel
     */
    public void calculateTotalProduct(){

        totalProductLabel.setText("Total Product: " + productTable.getItems().size());
    }

    /**
     * Method to calculate totalValue and sets value to totalValueLabel
     * Runs through all rows to collect askPrice data and sum the total
     */
    public void calculateValue(){

        for (Product item : productTable.getItems()) {
            totalValue = totalValue + Double.parseDouble(item.getAskPrice());
        }
        DecimalFormat df = new DecimalFormat("#.00");

        totalValueLabel.setText("Total Value: $" + df.format(totalValue));
    }

    /**
     * Method for required action when addButton ActionListener is triggered
     * @param event
     * @throws IOException
     */
    public void addButtonPressed(ActionEvent event) throws IOException {

        changeScenePVC(event, "AddProductView.fxml");
    }

    /**
     * Method for changing scenes when called upon
     * @param event
     * @param fxmlFileName
     * @throws IOException
     */
    public void changeScenePVC(ActionEvent event, String fxmlFileName) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFileName));
        Parent root = loader.load();
        Scene newScene = new Scene(root);

        AddProductViewController controller = loader.getController();
        controller.initialData(getProducts());

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setTitle("Add Product");
        stage.setScene(newScene);
        stage.show();
    }
}