package egovframework.sample.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection connect() {
        Connection conn = null;

        try {
            String user = "lib02";
            String password = "lib01#";
            String url = "jdbc:oracle:thin:@localhost:1522:orcl";

            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e);
        }

        return conn;
    }
}
