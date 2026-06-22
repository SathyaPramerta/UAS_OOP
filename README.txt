# BuildWJax — Sistem Kasir & Rakitan PC (NetBeans Java Swing)

**BuildWJax** adalah aplikasi desktop yang dirancang khusus untuk manajemen pemesanan rakitan PC secara *custom*. Aplikasi ini memudahkan kasir dalam mengelola pemilihan komponen hardware berdasarkan *tier* dan *platform* (Intel/AMD), memproses transaksi, serta mengelola data pelanggan dengan sistem yang terintegrasi.

### Cakupan fitur (inti)
Login/Register → Pilih Tier & Platform → Konfigurasi Komponen → Simpan Pesanan → Riwayat Transaksi & Cetak Struk.

### Struktur Proyek

```text
BuildWJax/
├── build.xml               # Skrip Ant (NetBeans)
├── manifest.mf
├── nbproject/              # Metadata proyek NetBeans
├── database/
│   └── db_rakitan.sql      # Skrip pembuatan database + data contoh
└── src/
    └── uas_oop/
        ├── koneksi.java    # Konfigurasi koneksi ke database MySQL
        ├── Form_Login.java # Halaman otentikasi kasir
        ├── Form_Register.java # Registrasi akun kasir baru
        ├── Form_Riwayat.java # Manajemen histori & cetak struk (.txt)
        └── pemesanan_pc.java # Form utama: Konfigurasi & kalkulasi harga
