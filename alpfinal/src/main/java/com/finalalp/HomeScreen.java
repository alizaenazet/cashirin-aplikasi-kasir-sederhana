package com.finalalp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class HomeScreen implements Initializable {

    
    

    @FXML
    private TextField NamaPemesan;

    @FXML
    private Button addProduk;

    @FXML
    private TextField jumlahPajak;

    @FXML
    private TextField jumlahProduk;

    @FXML
    private ChoiceBox<String> listKategoriProduk;

    @FXML
    private ChoiceBox<?> listProdukAdded;

    @FXML
    private TextField searchbarProduk;

    @FXML
    private TableView<?> tablePesanan;

    @FXML
    private TableView<?> tableProduk;


    @FXML
    void switchToListProduk(ActionEvent event) throws IOException {
        App.setRoot("listProdukScreen");
       
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    
    }

    // String coba 
    public void addKategoriList(String namaKategoriList) {
        listKategoriProduk.getItems().add(namaKategoriList);
        System.out.println(listKategoriProduk.getValue());
    }
}
