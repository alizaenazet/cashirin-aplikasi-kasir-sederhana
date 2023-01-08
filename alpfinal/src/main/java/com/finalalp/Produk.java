package com.finalalp;


public class Produk {
    String nama, deskripsi, kategori;
    Number harga;

    public Produk(String nama, String deskripsi,String kategori ,Number d) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.harga = d;
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public Number getHarga() {
        return harga;
    }

    public void setHarga(Number harga) {
        this.harga = harga;
    }

   

}
