package uas_oop;

public class Prosesor extends KomponenPC { // Syarat Inheritance
    
    // Tambahkan 'double harga' agar klop dengan KomponenPC
    public Prosesor(String namaProduk, double harga, String tier) {
        super(namaProduk, harga, tier); // Sekarang sudah mengirim 3 data!
    }

    @Override
    public String cetakSpesifikasiKasir() { // Syarat Polymorphism
        return "[CPU] " + getNamaProduk();
    }
}