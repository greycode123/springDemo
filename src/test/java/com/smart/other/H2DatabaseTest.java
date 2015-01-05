package com.smart.other;

import java.sql.*;

public class H2DatabaseTest {

    private static final String jdbcURL = "jdbc:h2:file:C:\\Users\\wang\\Desktop\\sealIndex";

    public static void main(String[] args) throws SQLException {

        //createTable();

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = DriverManager.
                getConnection(jdbcURL, "sa", "");
        // add application code here
        Statement stmt = conn.createStatement();
        String insertSQl = "INSERT INTO TEST VALUES ('中国平安人寿保险股份有限公司保费业务专用章', 'C:/Users/wang/Desktop/seal/test0.pdf', 0, 0)";
        stmt.execute(insertSQl);

        ResultSet rs = stmt.executeQuery("SELECT * FROM TEST ");
        while (rs.next()) {
            System.out.println(rs.getString("sealName") + "," + rs.getString("classpath"));
        }
        conn.close();
    }

    public static void createTable() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = DriverManager.
                getConnection(jdbcURL, "sa", "");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE TEST(sealname VARCHAR(255), classpath VARCHAR(255), version INT(1), valid INT(1))");
        conn.close();
    }
}
