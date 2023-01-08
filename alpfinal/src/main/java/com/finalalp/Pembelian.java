package com.finalalp;

public class Pembelian {
    String nama,produkPesanan,namaProdukPesanan;
    Number jumlah,pajak;
    int jumlahPesanan,hargaPesanan;
    public Pembelian(String namaProdukPesanan, Integer hargaPesanan, Integer jumlahPesanan) {
        this.namaProdukPesanan = namaProdukPesanan;
        this.hargaPesanan = hargaPesanan;
        this.jumlahPesanan = jumlahPesanan;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getProdukPesanan() {
        return produkPesanan;
    }
    public void setProdukPesanan(String produkPesanan) {
        this.produkPesanan = produkPesanan;
    }
    public String getNamaProdukPesanan() {
        return namaProdukPesanan;
    }
    public void setNamaProdukPesanan(String namaProdukPesanan) {
        this.namaProdukPesanan = namaProdukPesanan;
    }
    public Number getJumlah() {
        return jumlah;
    }
    public void setJumlah(Number jumlah) {
        this.jumlah = jumlah;
    }
    public Number getPajak() {
        return pajak;
    }
    public void setPajak(Number pajak) {
        this.pajak = pajak;
    }
    public Number getHargaPesanan() {
        return hargaPesanan;
    }
    public void setHargaPesanan(Integer hargaPesanan) {
        this.hargaPesanan = hargaPesanan;
    }
    public Number getJumlahPesanan() {
        return jumlahPesanan;
    }
    public void setJumlahPesanan(Integer jumlahPesanan) {
        this.jumlahPesanan = jumlahPesanan;
    }
    
}
