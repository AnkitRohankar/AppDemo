package resoureces;

import java.sql.*;

import org.openqa.selenium.WebElement;

public class DBUtils {

    static String DB_URL =  "jdbc:mysql://3.14.103.172:3306/dummy_api";//"jdbc:mysql://localhost:3306/your_db_name";
    static String DB_USER = "readuser";// "db_username";
    static String DB_PASS = "Secret@123";//"db_password";
   

    public static boolean isMobilePresent(String mobileNumberTextbox) {
        boolean status = false;

        try {
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query =  " Select * from user_credentials where user_name =? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, mobileNumberTextbox);

            ResultSet rs = ps.executeQuery();
            status = rs.next(); // valid record

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
