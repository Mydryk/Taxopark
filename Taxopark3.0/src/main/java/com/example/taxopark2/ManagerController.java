package com.example.taxopark2;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;


public class ManagerController implements Initializable {

    @FXML
    private TableView<cars> table_cars;

    @FXML
    private TableColumn<cars, Integer> col_id;

    @FXML
    private TableColumn<cars, String> col_name;

    @FXML
    private TableColumn<cars, String> col_model;

    @FXML
    private TableColumn<cars, String> col_hight_speed;

    @FXML
    private TableColumn<cars, Integer> col_fuel_consumption;

    @FXML
    private TableColumn<cars, String> col_price;

    @FXML
    private TextField txt_fuel_consumption;

    @FXML
    private TextField txt_hight_speed;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_model;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_price;

    @FXML
    private TextField filterField;


    ObservableList<cars> listM;
    ObservableList<cars> dataList;



    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    public void Add_users (){
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into cars (car_name,car_model,hight_speed,fuel_consumption,car_price)values(?,?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_name.getText());
            pst.setString(2, txt_model.getText());
            pst.setString(3, txt_hight_speed.getText());
            pst.setString(4, txt_fuel_consumption.getText());
            pst.setString(5, txt_price.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Cars Add succes");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


    //////// methode select users ///////
    @FXML
    void getSelected (MouseEvent event){
        index = table_cars.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_name.setText(col_name.getCellData(index).toString());
        txt_model.setText(col_model.getCellData(index).toString());
        txt_hight_speed.setText(col_hight_speed.getCellData(index).toString());
        txt_fuel_consumption.setText(col_fuel_consumption.getCellData(index).toString());
        txt_price.setText(col_price.getCellData(index).toString());

    }

    public void Update(){
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_name.getText();
            String value3 = txt_model.getText();
            String value4 = txt_hight_speed.getText();
            String value5 = txt_fuel_consumption.getText();
            String value6 = txt_price.getText();
            String sql = "update cars set idCars= '"+value1+"',car_name= '"+value2+"',car_model= '"+
                    value3+"',hight_speed= '"+value4+"',fuel_consumption= '"+value5+" ', car_price= ' "+value6+"' where idCars='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Delete(){
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from cars where idCars = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }


    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<cars,Integer>("idCars"));
        col_name.setCellValueFactory(new PropertyValueFactory<cars,String>("car_name"));
        col_model.setCellValueFactory(new PropertyValueFactory<cars,String>("car_model"));
        col_hight_speed.setCellValueFactory(new PropertyValueFactory<cars,String>("hight_speed"));
        col_fuel_consumption.setCellValueFactory(new PropertyValueFactory<cars,Integer>("fuel_consumption"));
        col_price.setCellValueFactory(new PropertyValueFactory<cars,String>("car_price"));

        listM = mysqlconnect.getDatacars();
        table_cars.setItems(listM);
    }




    @FXML
    void search_user() {
        col_id.setCellValueFactory(new PropertyValueFactory<cars,Integer>("idCars"));
        col_name.setCellValueFactory(new PropertyValueFactory<cars,String>("car_name"));
        col_model.setCellValueFactory(new PropertyValueFactory<cars,String>("car_model"));
        col_hight_speed.setCellValueFactory(new PropertyValueFactory<cars,String>("hight_speed"));
        col_fuel_consumption.setCellValueFactory(new PropertyValueFactory<cars,Integer>("fuel_consumption"));

        dataList = mysqlconnect.getDatacars();
        table_cars.setItems(dataList);
        FilteredList<cars> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(car -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (car.getCar_name().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches username
                } else if (car.getCar_model().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }else if (car.getHight_speed().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }

                else
                    return false; // Does not match.
            });
        });
        SortedList<cars> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_cars.comparatorProperty());
        table_cars.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
        search_user();
        // Code Source in description
    }
}