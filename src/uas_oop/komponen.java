/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package uas_oop;

/**
 *
 * @author BAGAS
 */
// Komponen.java (Parent Class)
public class komponen {
    // 4 Property sesuai syarat
    protected String nama;
    protected String merk;
    protected double harga;
    protected String kategori;

    // Constructor
    public komponen(String nama, String merk, double harga, String kategori) {
        this.nama = nama;
        this.merk = merk;
        this.harga = harga;
        this.kategori = kategori;
    }

    // 4 Method (Getter)
    public String getNama() { return nama; }
    public String getMerk() { return merk; }
    public double getHarga() { return harga; }
    public String getKategori() { return kategori; }

    // Method yang akan di-override (Polimorfisme)
    public String getSpekKhusus() {
        return "Standar";
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
