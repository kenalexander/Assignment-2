package user;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class User {

    private int userID;
    private String firstName, lastName, userName, userPass, sinNum, phoneNum, emailAddress;
    private LocalDate dob;
    private byte[] salt;
    private boolean isAdmin;

    public User(int userID, String firstName, String lastName, String userName, String userPass, String sinNum, String phoneNum, String emailAddress, LocalDate dob, boolean isAdmin) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userPass = userPass;
        this.sinNum = sinNum;
        this.phoneNum = phoneNum;
        this.emailAddress = emailAddress;
        this.dob = dob;
        this.isAdmin = isAdmin;
    }

    public User(String firstName, String lastName, String userName, String userPass, String sinNum, String phoneNum, String emailAddress, LocalDate dateOfBirth, boolean isAdmin) throws NoSuchAlgorithmException, SQLException {
        setFirstName(firstName);
        setLastName(lastName);
        setUserName(userName);
        salt = PasswordGenerator.getSalt();
        this.userPass = PasswordGenerator.getSHA512Password(userPass, salt);
        setSinNum(sinNum);
        setPhoneNum(phoneNum);
        setEmailAddress(emailAddress);
        setDob(dateOfBirth);
        this.setAdmin(isAdmin);

        insertIntoDB();
        updateUserDB();

    }

    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSinNum() {
        return sinNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setUserID(int userID) {
        if (userID >= 0)
            this.userID = userID;
        else
            throw new IllegalArgumentException("userID must be positive");
    }

    public void setFirstName(String firstName) {
        if (firstName.matches("[A-Z][a-zA-Z]*"))
            this.firstName = firstName;
        else
            throw new IllegalArgumentException("First name must adhere to proper naming conventions");
    }

    public void setLastName(String lastName) {
        if (lastName.matches("[A-Z][a-zA-Z\\-]*?"))
            this.lastName = lastName;
        else
            throw new IllegalArgumentException("Last name must adhere to proper naming conventions");
    }

    public void setUserName(String userName) {
        if (userName.matches("[a-zA-Z]*"))
            this.userName = userName;
        else
            throw new IllegalArgumentException("User name must be one word (lowercase only)");
    }

    public void setSinNum(String sinNum) {
        if (sinNum.matches("\\d{3}\\s\\d{3}\\s\\d{3}"))
            this.sinNum = sinNum;
        else
            throw new IllegalArgumentException("SIN must adhere to the format: XXX XXX XXX");
    }

    public void setPhoneNum(String phoneNum) {

        if (phoneNum.matches("[2-9]\\\\d{2}[-.]?\\\\d{3}[-.]\\\\d{4}"))
            this.phoneNum = phoneNum;
        else
            throw new IllegalArgumentException("Phone number must adhere to typical telephone number convention");
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress=emailAddress;
        if (emailAddress.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$"))
            this.emailAddress = emailAddress;
        else
            throw new IllegalArgumentException("Email address must be properly formatted and use a legitimate handle");
    }

    public void setDob(LocalDate dob) {
        int age = Period.between(dob, LocalDate.now()).getYears();

        if (age <= 100 && age >= 16)
            this.dob = dob;
        else
            throw new IllegalArgumentException("Employees can not be more than 100 years old or younger than 16 years old");
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String toString()
    {
        return String.format("%s %s is assigned the username %s and the userID of %d", firstName, lastName, userName, userID);
    }

    public void insertIntoDB() throws SQLException
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userDB?useSSL=false", "root", "");

            String sql = "INSERT INTO users (firstName, lastName, userName, userPass, salt, sinNum, phoneNum, emailAddress, dob, isAdmin) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            preparedStatement = conn.prepareStatement(sql);

            Date bd = Date.valueOf(dob);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, userPass);
            preparedStatement.setBlob(5, new javax.sql.rowset.serial.SerialBlob(salt));
            preparedStatement.setString(6, sinNum);
            preparedStatement.setString(7, phoneNum);
            preparedStatement.setString(8, emailAddress);
            preparedStatement.setDate(9, bd);
            preparedStatement.setBoolean(10, isAdmin);

            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            if (preparedStatement != null)
                preparedStatement.close();

            if (conn != null)
                conn.close();
        }
    }

    public void updateUserDB() throws SQLException
    {

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userDB?useSSL=false", "root", "");

            String sql = "UPDATE users SET firstName = ?, lastName = ?, userName = ?, userPass = ?, salt = ? , sinNum = ?, phoneNum = ?, emailAddress = ?, dob = ?, isAdmin = ?";

            preparedStatement = conn.prepareStatement(sql);

            Date bd = Date.valueOf(dob);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, userPass);
            preparedStatement.setBlob(5, new javax.sql.rowset.serial.SerialBlob(salt));
            preparedStatement.setString(6, sinNum);
            preparedStatement.setString(7, phoneNum);
            preparedStatement.setString(8, emailAddress);
            preparedStatement.setDate(9, bd);
            preparedStatement.setBoolean(10, isAdmin);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            if (conn != null)
                conn.close();

            if (preparedStatement != null)
                preparedStatement.close();
        }
    }
}
