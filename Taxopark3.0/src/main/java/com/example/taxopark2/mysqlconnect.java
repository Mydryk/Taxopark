package com.example.taxopark2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;


public class mysqlconnect {

    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/taxopark","root","123456");
            //JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public static ObservableList<cars> getDatacars(){
        Connection conn = ConnectDb();
        ObservableList<cars> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from cars");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new cars(Integer.parseInt(rs.getString("idCars")), rs.getString("car_name"), rs.getString("car_model"),
                        rs.getString("hight_speed"), Integer.parseInt(rs.getString("fuel_consumption")), rs.getString("car_price")));
            }
        } catch (Exception e) {
        }
        return list;
    }

}
