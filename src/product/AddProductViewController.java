package product;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AddProductViewController implements Initializable {

    private ObservableList<Product> products;

    private FileChooser fileChooser;
    private File img;

    @FXML private TextField vehicleNameTextField;
    @FXML private TextField vehicleGenreTextField;
    @FXML private TextField vehicleModelTextField;
    @FXML private TextField vehicleColorTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextField yearReleasedTextField;
    @FXML private DatePicker yearReleasedDatePicker;
    @FXML private TextField askPriceTextField;
    @FXML private Button addImageButton;
    @FXML private Label errorLabel;



    @FXML private ImageView productImage;


    /**
     * Method to return products from ObservableList
     * @param products
     */
    public void initialData(ObservableList<Product> products)
    {
        this.products = products;
    }

    /**
     * Method that listens for the ActionEvent of the FXML addButton
     * Instantiates a new object Product
     * Adds the newly created object to the ObservableList
     * Sets the FXML yearReleasedTextField to the value given by yearReleasedDatePicker.getValue()
     * @param event
     * @throws IOException
     */

    public void addProduct(ActionEvent event)
    {
        try{

            Product product = new Product(vehicleNameTextField.getText(),
                                          vehicleGenreTextField.getText(),
                                          vehicleModelTextField.getText(),
                                          vehicleColorTextField.getText(),
                                          descriptionTextField.getText(),
                                          yearReleasedDatePicker.getValue(),
                                          askPriceTextField.getText());

            products.add(product);

            changeSceneAPVC(event, "ProductView.fxml");


        }catch(IOException e){

        }

    }


    /**
     * Method to call for a scene change
     * @param event
     * @param fxmlFileName
     * @throws IOException
     */
    public void changeSceneAPVC(ActionEvent event, String fxmlFileName) throws IOException
    {
        //load a new scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFileName));
        Parent parent = loader.load();
        Scene newScene = new Scene(parent);

        ProductViewController controller = loader.getController();
        controller.loadProducts(products);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        //change the scene to the new scene
        stage.setTitle("Inventory");
        stage.setScene(newScene);
        stage.show();
    }

    public void setErrorLabel(){
        errorLabel.setText("");
    }

    /**
     * Method to set the yearReleasedTextField to the LocalDate value of DatePicker
     */
    public void setYearReleasedTextField(){
        yearReleasedTextField.setText("" + yearReleasedDatePicker.getValue());
    }

    /**
     * Method that listens for the ActionEvent of the FXML addImage
     * Opens a FileChooser object and prompts user to select image from device
     * Sets selected image to the vale of img
     * @param event
     */
    public void addImageButtonPressed(ActionEvent event)
    {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");

        FileChooser.ExtensionFilter jpgFilter
                = new FileChooser.ExtensionFilter("Image File (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter pngFilter
                = new FileChooser.ExtensionFilter("Image File (*.png)", "*.png");

        fileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter);


        String userDirectoryString = System.getProperty("user.home")+"\\Pictures";
        File userDirectory = new File(userDirectoryString);

        if (!userDirectory.canRead())
            userDirectory = new File(System.getProperty("user.home"));

        fileChooser.setInitialDirectory(userDirectory);

        img = fileChooser.showOpenDialog(stage);

        if (img.isFile())
        {
            try
            {
                BufferedImage bufferedImage = ImageIO.read(img);
                Image image = SwingFXUtils.toFXImage(bufferedImage,null);
                productImage.setImage(image);
            }
            catch (IOException e)
            {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Override initialize method for AddProductView
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try
        {
            BufferedImage bufferedImage = ImageIO.read(new File("./src/product/defaultProduct.jpg"));
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            productImage.setImage(image);
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
