package uas_oop;

public class Motherboard extends KomponenPC { // Syarat Inheritance
    
    // Pastikan parameter di sini 100% sama: String, double, String
    public Motherboard(String namaProduk, double harga, String tier) {
        super(namaProduk, harga, tier); // Mengirim 3 data ke KomponenPC
    }

    @Override
    public String cetakSpesifikasiKasir() { // Syarat Polymorphism
        return "[MOBO] " + getNamaProduk();
    }
}