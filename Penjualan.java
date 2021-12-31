package com.example.contoh.model;

public class Penjualan {
    String id;
    String tanggal;
    String produk;
    String pembeli;
    String total;

    public Penjualan (String id, String tanggal, String produk, String pembeli, String total) {
        this.id = id;
        this.tanggal = tanggal;
        this.produk = produk;
        this.pembeli = pembeli;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getProduk() {
        return produk;
    }

    public String getPembeli() {
        return pembeli;
    }

    public String getTotal() {return total;}

    public void setId(String id) {
        this.id = id;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public void setPembeli(String pembeli) {
        this.pembeli = pembeli;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
