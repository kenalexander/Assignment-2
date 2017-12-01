
package product;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javafx.stage.FileChooser;
import java.time.Month;


/**
 * The Product class holds the methods that control the object when it is instantiated
 *
 * @author kenalexander
 * @version 1.0
 * @since 2017-10-14
 */
public class Product{

    private String vehicleName, vehicleGenre, vehicleModel, vehicleColor, description, askPrice;
    private LocalDate yearReleased;
    private double sellPrice;

    private boolean administrator;

    private static int productID = 10001;
    private Image img;

    /**
     * Default constructor
     */
    public Product(){

        setVehicleName(vehicleName);
        setVehicleGenre(vehicleGenre);
        setVehicleModel(vehicleModel);
        setVehicleColor(vehicleColor);
        setDescription(description);
        setYearReleased(yearReleased);
        setAskPrice(askPrice);
        setSellPrice(sellPrice);

        productID++;

        try
        {
            BufferedImage bufferedImage = ImageIO.read(new File("./src/product/defaultProduct.png"));
            img = SwingFXUtils.toFXImage(bufferedImage, null);
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Initial default constructor for the Product class
     *
     * @param vehicleName - controls the name of the product (type: String)
     * @param vehicleGenre - category of product (type: String)
     * @param vehicleModel - allows control of model of the product (type: String)
     * @param vehicleColor - color of the product (type: String)
     * @param description - misc. description to be determined by user input (type: String)
     * @param yearReleased - controls the year the product was originally released (type: int)
     * @param askPrice - controls initial asking price of the product (type: double)
     * @param sellPrice - controls the final selling price of the product (type: double)
     * @param img - controls the import of an image to represent the product (type: Image)
     */
    public Product(String vehicleName, String vehicleGenre, String vehicleModel, String vehicleColor, String description, LocalDate yearReleased, String askPrice, double sellPrice, Image img) {

        this.vehicleName = vehicleName;
        this.vehicleGenre = vehicleGenre;
        this.vehicleModel = vehicleModel;
        this.vehicleColor = vehicleColor;
        this.description = description;
        this.yearReleased = yearReleased;
        this.askPrice = askPrice;
        this.sellPrice = sellPrice;
        this.img = img;
    }

    /**
     * Contructor with instantiated set() methods
     * Increments the productID by 1 after object is called
     * Sets img to default image from package: images
     *
     * @param vehicleName
     * @param vehicleGenre
     * @param vehicleModel
     * @param vehicleColor
     * @param description
     */
    public Product(String vehicleName, String vehicleGenre, String vehicleModel, String vehicleColor, String description, LocalDate yearReleased, String askPrice) {

        setVehicleName(vehicleName);
        setVehicleGenre(vehicleGenre);
        setVehicleModel(vehicleModel);
        setVehicleColor(vehicleColor);
        setDescription(description);
        setYearReleased(yearReleased);
        setAskPrice(askPrice);


        productID++;

        try
        {
            BufferedImage bufferedImage = ImageIO.read(new File("./src/images/defaultProduct.png"));
            img = SwingFXUtils.toFXImage(bufferedImage, null);
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }

    }

    /**
     * Sets a user to administrator
     *
     * @param administrator
     */
    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    /**
     * toString method for Product class
     *
     * @return toString()
     */
    @Override
    public String toString()
    {
        return String.format("");
    }

    /**
     * get() method to return vehicleName
     * @return vehicleName
     */
    public String getVehicleName() {
        return vehicleName;
    }
    /**
     * get() method to return vehicleGenre
     * @return vehicleGenre
     */
    public String getVehicleGenre() {
        return vehicleGenre;
    }
    /**
     * get() method to return vehicleModel
     * @return vehicleModel
     */
    public String getVehicleModel() {
        return vehicleModel;
    }
    /**
     * get() method to return vehicleColor
     * @return vehicleColor
     */
    public String getVehicleColor() {
        return vehicleColor;
    }
    /**
     * get() method to return description
     * @return description
     */
    public String getDescription() {
        return description;
    }
    /**
     * get() method to return yearReleased
     * @return yearReleased
     */
    public LocalDate getYearReleased() {
        return yearReleased;
    }
    /**
     * get() method to return askPrice
     * @return askPrice
     */
    public String getAskPrice() {
        return askPrice;
    }
    /**
     * get() method to return sellPrice
     * @return sellPrice
     */
    public double getSellPrice() {
        return sellPrice;
    }
    /**
     * get() method to return productID
     * @return productID
     */
    public static int getProductID() {
        return productID;
    }
    /**
     * get() method to return img
     * @return img
     */
    public Image getImg() {
        return img;
    }
    /**
     * get() method to return administrator
     * @return administrator
     */
    public boolean isAdministrator() {
        return administrator;
    }

    /**
     * set() method for vehicleName
     * Checks that the string is no empty, and if so, throws an IllegalArgumentException
     * If the string is populated, it checks the value against the set RegEx standard
     * Sets the parameter to this.vehicleName
     *
     * @param vehicleName
     */
    public void setVehicleName(String vehicleName) {
        if (vehicleName.isEmpty()) {

            throw new IllegalArgumentException("Field cannot be left blank");
        }
        else if (vehicleName.matches("[a-zA-Z\\-\\s]*")) {

            this.vehicleName = vehicleName;
        }
    }

    /**
     * set() method for vehicleGenre
     * Checks that the string is no empty, and if so, throws an IllegalArgumentException
     * If the string is populated, it checks the value against the set RegEx standard
     * Sets the parameter to this.vehicleGenre
     *
     * @param vehicleGenre
     */
    public void setVehicleGenre(String vehicleGenre) {
        if (vehicleGenre.isEmpty()){

            throw new IllegalArgumentException("Field cannot be left blank");
        }
        else if (vehicleGenre.matches("[a-zA-Z\\-]*")) {

            this.vehicleGenre = vehicleGenre;
        }
    }

    /**
     * set() method for description
     * Checks that the string is no empty, and if so, throws an IllegalArgumentException
     * If the string is populated, it checks the value against the set RegEx standard
     * Sets the parameter to this.description
     *
     * @param description
     */
    public void setDescription(String description) {
        if (description.isEmpty()){

            throw new IllegalArgumentException("Field cannot be left blank");
        }
        else if (description.matches("[a-zA-Z0-9\\-]*")) {

            this.description = description;
        }
    }

    /**
     * set() method for yearReleased
     * Implements a try/catch block to check if values entered are acceptable and within boundaries by using a LocalDate
     * Creates local LocalDate variables to check ranges
     * Throws exception if date is not in range
     *
     * Sets the parameter to this.yearReleased
     *
     * @param yearReleased
     */
    public void setYearReleased(LocalDate yearReleased) {

        LocalDate currentYear = LocalDate.now().plusYears(1);
        LocalDate originYear = LocalDate.of(1990, Month.JANUARY, 1);

        try{
            if(yearReleased.getYear()>=originYear.getYear() && yearReleased.getYear()<=currentYear.getYear()){

                this.yearReleased=yearReleased;
            }
            else{

                throw new IllegalArgumentException("Year entered must be between 1990 - " + currentYear.getYear());
            }
        }
        catch (IllegalArgumentException yearIAE){

            System.err.println(yearIAE.getMessage());
        }
        catch (NullPointerException yearNE){

            System.err.println(yearNE.getMessage());
        }
        catch (Exception yearE){

            System.err.println(yearE.getMessage());
        }
    }

    /**
     * set() method for vehicleModel
     * Checks that the string is no empty, and if so, throws an IllegalArgumentException
     * If the string is populated, it checks the value against the set RegEx standard
     * Sets the parameter to this.vehicleModel
     *
     * @param vehicleModel
     */
    public void setVehicleModel(String vehicleModel) {

        if (vehicleModel.isEmpty()){

            throw new IllegalArgumentException("Field cannot be left blank");
        }
        else if (vehicleModel.matches("[a-zA-Z0-9\\-]*")) {

            this.vehicleModel = vehicleModel;
        }
    }

    /**
     * set() method for vehicleColor
     * Checks that the string is no empty, and if so, throws an IllegalArgumentException
     * If the string is populated, it checks the value against the set RegEx standard
     * Sets the parameter to this.vehicleColor
     *
     * @param vehicleColor
     */
    public void setVehicleColor(String vehicleColor) {

        if (vehicleColor.isEmpty()){

            throw new IllegalArgumentException("Field cannot be left blank");
        }
        else if (vehicleColor.matches("[a-zA-Z\\-]*")) {

            this.vehicleColor = vehicleColor;
        }
    }
    /**
     * set() method for askPrice
     * Implements a try/catch block to check if values entered are acceptable and within boundaries
     * Creates local if/else statement to check ranges
     * Throws exception if askPrice is not in range
     *
     * Sets the parameter to this.yearReleased
     *
     * @param askPrice
     */
    public void setAskPrice(String askPrice) {

        try{

            if (Double.parseDouble(askPrice)>0 && Double.parseDouble(askPrice)<Double.MAX_VALUE){

                this.askPrice = askPrice;
            }
            else{

                throw new IllegalArgumentException("Please enter a non-negative value that is no greater than "+ Double.MAX_VALUE);
            }

        }
        catch (IllegalArgumentException askPriceIAE){

            System.err.println(askPriceIAE.getMessage());
        }
        catch (NullPointerException askPriceNE){

            System.err.println(askPriceNE.getMessage());
        }
        catch (Exception askPriceE){

            System.err.println(askPriceE.getMessage());
        }
    }
    /**
     * set() method for sellPrice
     * Implements a try/catch block to check if values entered are acceptable and within boundaries
     * Creates local if/else statement to check ranges
     * Throws exception if sellPrice is not in range
     *
     * Sets the parameter to this.yearReleased
     *
     * @param sellPrice
     */
    public void setSellPrice(double sellPrice) {

        try{

            if (sellPrice>0 && sellPrice<Double.MAX_VALUE){

                this.sellPrice = sellPrice;
            }
            else{

                throw new IllegalArgumentException("Please enter a non-negative value that is no greater than "+ Double.MAX_VALUE);
            }

        }
        catch (IllegalArgumentException sellPriceIAE){

            System.err.println(sellPriceIAE.getMessage());
        }
        catch (NullPointerException sellPriceNE){

            System.err.println(sellPriceNE.getMessage());
        }
        catch (Exception sellPriceE){

            System.err.println(sellPriceE.getMessage());
        }
    }


    /**
     * set() method for productID
     * @param productID
     */
    public static void setProductID(int productID) {
        Product.productID = productID;
    }

    /**
     * set() method for img
     * Implements a FileChooser to allow user to upload an image for the product from their own device. This image is to be used to represent the product.
     * Sets an ExtensionFilter to ensure proper format of file is uploaded.
     * try/catch block used to check for IO error
     *
     */
    public void setImg() {

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        File file = fileChooser.showOpenDialog(null);

        try {

            BufferedImage bufferedImage = ImageIO.read(file);
            this.img = SwingFXUtils.toFXImage(bufferedImage, null);
        }
        catch (IOException imgIOE) {

            System.err.println(imgIOE.getMessage());
        }
    }
}
