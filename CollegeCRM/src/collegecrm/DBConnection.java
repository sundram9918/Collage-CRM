/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package collegecrm;

/**
 *
 * @author manglesh tiwari
 */

import java.sql.*;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // yaha apna password daal de - mera "root" hai
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/college_crm", "root", "Sundram@9918");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}