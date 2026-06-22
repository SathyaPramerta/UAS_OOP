package uas_oop; // Sesuaikan dengan nama package Anda

public abstract class KomponenPC { // Abstraction
    private String namaProduk; // Encapsulation
    private double harga;
    private String tier;

    public KomponenPC(String namaProduk, double harga, String tier) {
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.tier = tier;
    }

    // Getter & Setter (Encapsulation)
    public String getNamaProduk() { return namaProduk; }
    public double getHarga() { return harga; }
    public String getTier() { return tier; }

    // Abstract method untuk Polymorphism
    public abstract String cetakSpesifikasiKasir();
}