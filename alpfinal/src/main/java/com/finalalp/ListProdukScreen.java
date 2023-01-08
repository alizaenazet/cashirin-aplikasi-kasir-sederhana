package com.finalalp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class ListProdukScreen implements Initializable {
    public Kategori daftarKategori = new Kategori(null);

    DateTimeFormatter formatWaktu = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    LocalDateTime waktuPengguna = LocalDateTime.now();
    String namaPesanan;
    String struk = "--------- Struk Pembelian --------- \n" + formatWaktu.format(waktuPengguna)
            + "\n --------------- \n \n";
    boolean StatusPesanan = false;

    @FXML
    void switchToHomeScreen(ActionEvent event) throws IOException {
        App.setRoot("homeScreen");
    }

    @FXML
    private TableView<Pembelian> daftarPesanan;

    @FXML
    private TableColumn<Pembelian, Integer> hargaProdukPesanan;

    @FXML
    private TableColumn<Pembelian, Integer> jumlahProdukPesanan;

    @FXML
    private TableColumn<Pembelian, String> namaProdukPesanan;

    @FXML
    private TextField jumlahProduk;

    @FXML
    private TextField produkPesananDisplay;

    @FXML
    private TextField hargaProdukPesananDisplay;

    @FXML
    private TextField nilaiPajak;

    @FXML
    private TextField jumlahUang;

    @FXML
    private Label jumlahPajak;

    @FXML
    private Label biayaPajak;

    @FXML
    private Label subtotalPesanan;

    @FXML
    private Label totalPesanan;

    @FXML
    private Label kembalian;

    // List produk

    @FXML
    private ChoiceBox<String> addListKategori;

    @FXML
    private ChoiceBox<String> listKategori;

    @FXML
    private ChoiceBox<String> listPilihKategori;

    @FXML
    private TableView<Produk> tableListProduk;

    @FXML
    private TableColumn<Produk, String> deskripsi;

    @FXML
    private TableColumn<Produk, Number> harga;

    @FXML
    private TableColumn<Produk, String> nama;

    @FXML
    private TableColumn<Produk, String> kategoriTable;

    @FXML
    private TextField namaKategoriBaru;

    @FXML
    private TextField namaPemesan;
    @FXML
    private TextField namaProduk;
    @FXML
    private TextField deskripsiProduk;
    @FXML
    private TextField hargaProduk;

    @FXML
    private TextField searchBarProduk;

    @FXML
    void printStruk(ActionEvent event) throws FileNotFoundException {

        struk += "\n\n\nSub total: \b \b" + subtotalPesanan.getText() + "\n Pajak: \b \b" + jumlahPajak.getText()
                + "\n Total: \b \b" + "Rp." + totalPesanan.getText() + "\n jumlah Uang: \b \b" + jumlahUang.getText()
                + "\n Kembalian: \b \b" + kembalian.getText();

        FileChooser fileChooser = new FileChooser();
        File sruk = fileChooser.showSaveDialog(new Stage());
        System.out.println("LETAK E NDEK SINI " + sruk);

        PrintStream ps = new PrintStream(sruk);
        System.setOut(ps);
        ps.println(struk);
    }

    @FXML
    void selesaiPesanan(ActionEvent event) {

        StatusPesanan = true;

        namaPemesan.clear();
        produkPesananDisplay.clear();
        hargaProdukPesananDisplay.clear();
        jumlahProduk.clear();
        nilaiPajak.clear();
        daftarPesanan.getItems().clear();

        jumlahUang.clear();
        subtotalPesanan.setText("0");
        biayaPajak.setText("0");
        totalPesanan.setText("0");
        jumlahPajak.setText("0");
        kembalian.setText("0");

    }

    @FXML
    void jumlahKembalian(KeyEvent event) {
        if (Integer.parseInt(totalPesanan.getText()) > Integer.parseInt(jumlahUang.getText())) {
            kembalian.setText("Mohon maaf uang tidak cukup");
        } else {
            kembalian.setText("Rp." + Integer
                    .toString(Integer.parseInt(jumlahUang.getText()) - Integer.parseInt(totalPesanan.getText())));

        }
    }

    @FXML
    void tambahkanPesanan(ActionEvent event) {

        StatusPesanan = false;
        Pembelian pembelianAdded = new Pembelian(produkPesananDisplay.getText(),
                Integer.parseInt(hargaProdukPesananDisplay.getText()), Integer.parseInt(jumlahProduk.getText()));

        int totalSementara = (Integer.parseInt(hargaProdukPesananDisplay.getText())
                * Integer.parseInt(jumlahProduk.getText())) + Integer.parseInt(subtotalPesanan.getText());

        subtotalPesanan.setText(Integer.toString(totalSementara));

        jumlahPajak.setText(nilaiPajak.getText() + "%");

        biayaPajak.setText(Integer.toString((totalSementara * Integer.parseInt(nilaiPajak.getText()) / 100)));

        totalPesanan.setText(Integer.toString(totalSementara + Integer.parseInt(biayaPajak.getText())));

        daftarPesanan.getItems().add(pembelianAdded);

        if (namaPesanan == null) {
            namaPesanan = namaPemesan.getText();
            struk += "atas nama pesanan: \b" + namaPesanan + "\n Daftar Pesanan \n \n";
        }

        struk += produkPesananDisplay.getText() + "\b " + hargaProdukPesananDisplay.getText() + "\b "
                + jumlahProduk.getText() + "\n";

    }

    @FXML
    void produkDipilih(MouseEvent event) {
        Produk produkDipilih = tableListProduk.getSelectionModel().getSelectedItem();
        produkPesananDisplay.setText(produkDipilih.getNama());
        hargaProdukPesananDisplay.setText(String.valueOf(produkDipilih.getHarga()));
    }

    @FXML
    void tambahkanProduk(ActionEvent event) {

        Produk produkAdded = new Produk(namaProduk.getText(), deskripsiProduk.getText(), listPilihKategori.getValue(),
                Integer.parseInt(hargaProduk.getText()));
        produkAdded.setHarga(Integer.parseInt(hargaProduk.getText()));
        produkAdded.setNama(namaProduk.getText());
        produkAdded.setKategori(listPilihKategori.getValue());
        produkAdded.setDeskripsi(deskripsiProduk.getText());
        datalist.addAll(produkAdded);
        tableListProduk.getItems().add(produkAdded);
        namaProduk.clear();
        deskripsiProduk.clear();
        hargaProduk.clear();
    }

    @FXML
    void pilihListKategori(MouseEvent event) {

        searchBarProduk.setText(listKategori.getValue());
    }

    ObservableList<Produk> datalist = FXCollections.observableArrayList();

    @FXML
    void filterPencarian(MouseEvent event) {

        // Searchbarnya cuuuy T_T
        FilteredList<Produk> filteredListdata = new FilteredList<>(datalist, b -> true);
        searchBarProduk.textProperty().addListener((Observable, oldValue, newValue) -> {
            filteredListdata.setPredicate(Produk -> {
                if (newValue.isBlank() || newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (Produk.getKategori().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Produk.getNama().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<Produk> sortedData = new SortedList<>(filteredListdata);

        sortedData.comparatorProperty().bind(tableListProduk.comparatorProperty());

        tableListProduk.setItems(sortedData);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        addListKategori.getItems().addAll(daftarKategori.makanan);

        nama.setCellValueFactory(new PropertyValueFactory<Produk, String>("nama"));
        harga.setCellValueFactory(new PropertyValueFactory<Produk, Number>("harga"));
        deskripsi.setCellValueFactory(new PropertyValueFactory<Produk, String>("deskripsi"));
        kategoriTable.setCellValueFactory(new PropertyValueFactory<Produk, String>("kategori"));

        hargaProdukPesanan.setCellValueFactory(new PropertyValueFactory<Pembelian, Integer>("hargaPesanan"));
        jumlahProdukPesanan.setCellValueFactory(new PropertyValueFactory<Pembelian, Integer>("jumlahPesanan"));
        namaProdukPesanan.setCellValueFactory(new PropertyValueFactory<Pembelian, String>("namaProdukPesanan"));

    }

    public void submitKategoriBaru(ActionEvent event) throws IOException {
        String namaKategoString = addListKategori.getValue();
        switch (namaKategoString) {
            case "1. Nama kategori":
                addListKategori.getItems().remove(namaKategoString);
                daftarKategori.makanan[0] = "1. " + namaKategoriBaru.getText();
                addListKategori.getItems().add(0, daftarKategori.makanan[0]);
                listKategori.getItems().add(0, daftarKategori.makanan[0]);
                listPilihKategori.getItems().add(0, daftarKategori.makanan[0]);

                // root = loader.load();
                break;

            case "2. Nama kategori":
                addListKategori.getItems().remove(namaKategoString);
                daftarKategori.makanan[1] = "2. " + namaKategoriBaru.getText();
                addListKategori.getItems().add(1, daftarKategori.makanan[1]);
                listKategori.getItems().add(1, daftarKategori.makanan[1]);
                listPilihKategori.getItems().add(1, daftarKategori.makanan[1]);

                break;

            case "3. Nama kategori":
                addListKategori.getItems().remove(namaKategoString);
                daftarKategori.makanan[2] = "3. " + namaKategoriBaru.getText();
                addListKategori.getItems().add(2, daftarKategori.makanan[2]);
                listKategori.getItems().add(2, daftarKategori.makanan[2]);
                listPilihKategori.getItems().add(2, daftarKategori.makanan[2]);

                break;

            case "4. Nama kategori":
                addListKategori.getItems().remove(namaKategoString);
                daftarKategori.makanan[3] = "4. " + namaKategoriBaru.getText();
                addListKategori.getItems().add(3, daftarKategori.makanan[3]);
                listKategori.getItems().add(3, daftarKategori.makanan[3]);
                listPilihKategori.getItems().add(3, daftarKategori.makanan[3]);

                break;

            case "5. Nama kategori":
                addListKategori.getItems().remove(namaKategoString);
                daftarKategori.makanan[4] = "5. " + namaKategoriBaru.getText();
                addListKategori.getItems().add(4, daftarKategori.makanan[4]);
                listKategori.getItems().add(4, daftarKategori.makanan[4]);
                listPilihKategori.getItems().add(4, daftarKategori.makanan[4]);

                break;

            default:
                break;
        }
        for (int i = 0; i < daftarKategori.makanan.length; i++) {
            System.out.println(daftarKategori.makanan[i]);
        }

    }

}
