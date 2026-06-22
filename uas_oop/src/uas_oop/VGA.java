package uas_oop;

public class VGA extends KomponenPC { // Syarat Inheritance
    
    public VGA(String namaProduk, double harga, String tier) {
        super(namaProduk, harga, tier);
    }

    @Override
    public String cetakSpesifikasiKasir() { // Syarat Polymorphism
        return "[VGA] " + getNamaProduk();
    }
}