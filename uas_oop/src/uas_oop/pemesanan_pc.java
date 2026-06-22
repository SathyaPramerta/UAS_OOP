package uas_oop;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.NumberFormat;
import java.util.Locale;

public class pemesanan_pc extends javax.swing.JFrame {

    // =========================================================
    // 1. VARIABEL GLOBAL
    // =========================================================
    public static String namaKasirAktif = "Guest";
    private boolean isSedangUpdate = false;
    private double totalHargaKasir = 0;
    private String idEditAktif = null; //

    // =========================================================
    // 2. CONSTRUCTOR (Dijalankan pertama kali)
    // =========================================================
    public pemesanan_pc() {
        initComponents();
        lbl_nama_kasir.setText("Kasir: " + namaKasirAktif);
        // Memastikan tabel kosong saat start
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) Form_Keranjang_Rakitan.getModel();
        model.setRowCount(0);
        this.setLocationRelativeTo(null); 

        cb_tier_rakitan.removeAllItems();
        cb_tier_rakitan.addItem("Pilih Tier...");
        cb_tier_rakitan.addItem("Low");
        cb_tier_rakitan.addItem("Mid");
        cb_tier_rakitan.addItem("End");

        cb_tier_rakitan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                    String tier = cb_tier_rakitan.getSelectedItem().toString();
                    if (!tier.equals("Pilih Tier...")) {
                        muatKomponenDariDatabase(tier);
                    }
                }
            }
        });

        // Event Listener agar tabel otomatis update saat komponen dipilih
        java.awt.event.ActionListener aksiPilih = new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perbaruiKeranjang();
            }
        };

        cb_prosessor.addActionListener(aksiPilih);
        cb_motherboard.addActionListener(aksiPilih);
        cb_ram.addActionListener(aksiPilih);
        cb_kartu_grafis.addActionListener(aksiPilih);
        cb_penyimpanan.addActionListener(aksiPilih);
        cb_power_supply.addActionListener(aksiPilih);
        cb_cpu_cooler.addActionListener(aksiPilih);
        cb_casing.addActionListener(aksiPilih);
        cb_fan_casing.addActionListener(aksiPilih);
    } 

    // =========================================================
    // 3. METHOD MENARIK DATA DARI DATABASE
    // =========================================================
    private void muatKomponenDariDatabase(String tier) {
        isSedangUpdate = true;
        
        cb_prosessor.removeAllItems(); cb_prosessor.addItem("Pilih CPU...");
        cb_motherboard.removeAllItems(); cb_motherboard.addItem("Pilih Motherboard...");
        cb_ram.removeAllItems(); cb_ram.addItem("Pilih RAM...");
        cb_kartu_grafis.removeAllItems(); cb_kartu_grafis.addItem("Pilih VGA...");
        cb_penyimpanan.removeAllItems(); cb_penyimpanan.addItem("Pilih Storage...");
        cb_power_supply.removeAllItems(); cb_power_supply.addItem("Pilih PSU...");
        cb_cpu_cooler.removeAllItems(); cb_cpu_cooler.addItem("Pilih Cooler...");
        cb_casing.removeAllItems(); cb_casing.addItem("Pilih Casing...");
        cb_fan_casing.removeAllItems(); cb_fan_casing.addItem("Pilih Fan...");

        try {
            Connection conn = koneksi.getKoneksi();
            Statement st = conn.createStatement();
            String sql = "SELECT kategori, nama_produk FROM komponen WHERE tier = '" + tier + "'";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String kategori = rs.getString("kategori");
                String nama = rs.getString("nama_produk");

                if (kategori.equalsIgnoreCase("CPU")) cb_prosessor.addItem(nama);
                else if (kategori.equalsIgnoreCase("Motherboard")) cb_motherboard.addItem(nama);
                else if (kategori.equalsIgnoreCase("RAM")) cb_ram.addItem(nama);
                else if (kategori.equalsIgnoreCase("VGA")) cb_kartu_grafis.addItem(nama);
                else if (kategori.equalsIgnoreCase("Storage")) cb_penyimpanan.addItem(nama);
                else if (kategori.equalsIgnoreCase("PSU")) cb_power_supply.addItem(nama);
                else if (kategori.equalsIgnoreCase("CPU Cooler")) cb_cpu_cooler.addItem(nama);
                else if (kategori.equalsIgnoreCase("Casing")) cb_casing.addItem(nama);
                else if (kategori.equalsIgnoreCase("Fan Casing")) cb_fan_casing.addItem(nama);
            }
            rs.close(); 
            st.close();
        } catch (Exception e) {
            System.out.println("Gagal memuat data: " + e.getMessage());
        }
        
        isSedangUpdate = false; 
        perbaruiKeranjang(); 
    }
    // =================================================================
    // METHOD UNTUK MENGUBAH ISI DROPDOWN BERDASARKAN TIER & PLATFORM
    // =================================================================
    private void sesuaikanKomponenPlatform() {
        // Ambil data yang sedang dipilih
        String tier = cb_tier_rakitan.getSelectedItem().toString();
        
        // Bersihkan isi dropdown CPU dan Motherboard saat ini
        cb_prosessor.removeAllItems();
        cb_prosessor.addItem("Pilih Prosesor...");
        
        cb_motherboard.removeAllItems();
        cb_motherboard.addItem("Pilih Motherboard...");
        
        // JIKA MEMILIH INTEL
        if (rb_intel.isSelected()) {
            if (tier.equals("Low")) {
                cb_prosessor.addItem("Core i3-12100F");
                cb_motherboard.addItem("H610M-HDV/M.2");
            } else if (tier.equals("Mid")) {
                cb_prosessor.addItem("Core i5-13400F");
                cb_motherboard.addItem("B760M Pro RS WIFI DDR5");
            } else if (tier.equals("End")) {
                cb_prosessor.addItem("Core i7-14700K");
                cb_prosessor.addItem("Core i9-14900K");
                cb_motherboard.addItem("Z790 Steel Legend WiFi");
            }
        } 
        // JIKA MEMILIH AMD
        else if (rb_amd.isSelected()) {
            if (tier.equals("Low")) {
                cb_prosessor.addItem("Ryzen 5 4600G");
                cb_motherboard.addItem("A520M-Pro");
            } else if (tier.equals("Mid")) {
                cb_prosessor.addItem("Ryzen 5 5600X");
                cb_motherboard.addItem("B550M AORUS ELITE");
            } else if (tier.equals("End")) {
                cb_prosessor.addItem("Ryzen 7 7800X3D");
                cb_prosessor.addItem("Ryzen 9 7950X");
                cb_motherboard.addItem("X670E ROG Crosshair");
            }
        }
        
        // Reset pilihan ke indeks 0 ("Pilih...")
        cb_prosessor.setSelectedIndex(0);
        cb_motherboard.setSelectedIndex(0);
    }
    
    // =========================================================
    // 4. METHOD KALKULASI TABEL KERANJANG
    // =========================================================
    private void perbaruiKeranjang() {
        if (isSedangUpdate) return; 

        // PASTIKAN NAMA TABEL ANDA ADALAH jTable1. Jika bukan, ganti jTable1 di bawah ini.
        DefaultTableModel model = (DefaultTableModel) Form_Keranjang_Rakitan.getModel();
        model.setRowCount(0); 
        totalHargaKasir = 0; 

        totalHargaKasir += cekDanMasukkan("CPU", cb_prosessor, model);
        totalHargaKasir += cekDanMasukkan("Motherboard", cb_motherboard, model);
        totalHargaKasir += cekDanMasukkan("RAM", cb_ram, model);
        totalHargaKasir += cekDanMasukkan("VGA", cb_kartu_grafis, model);
        totalHargaKasir += cekDanMasukkan("Storage", cb_penyimpanan, model);
        totalHargaKasir += cekDanMasukkan("PSU", cb_power_supply, model);
        totalHargaKasir += cekDanMasukkan("Cooler", cb_cpu_cooler, model);
        totalHargaKasir += cekDanMasukkan("Casing", cb_casing, model);
        totalHargaKasir += cekDanMasukkan("Fan", cb_fan_casing, model);
        // Menampilkan format Rupiah ke Label Total
        NumberFormat formatRp = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        lbl_total_harga.setText("Total: " + formatRp.format(totalHargaKasir));
    }
    private void resetKeranjang() {
        isSedangUpdate = true; // Kunci sementara agar tabel tidak error saat dropdown diubah

        cb_tier_rakitan.setSelectedIndex(0);
        cb_prosessor.setSelectedIndex(0);
        cb_motherboard.setSelectedIndex(0);
        cb_ram.setSelectedIndex(0);
        cb_kartu_grafis.setSelectedIndex(0);
        cb_penyimpanan.setSelectedIndex(0);
        cb_power_supply.setSelectedIndex(0);
        cb_cpu_cooler.setSelectedIndex(0);
        cb_casing.setSelectedIndex(0);
        cb_fan_casing.setSelectedIndex(0);

        isSedangUpdate = false; // Buka kunci
        perbaruiKeranjang(); // Hitung ulang (otomatis jadi Rp 0 dan tabel kosong)
    }
   // =========================================================
    // METHOD UNTUK MENERIMA DATA EDIT DARI FORM RIWAYAT
    // =========================================================
    // 1. METHOD BANTUAN PROSEDURAL (Tambahkan ini tepat di atas muatDataUntukEdit)
    // Fungsinya agar kita tidak perlu menulis loop "for" sampai 10 kali
    private void setDropdownProsedural(javax.swing.JComboBox combo, String dataLama) {
        if (dataLama == null || combo == null) return;
        for (int i = 0; i < combo.getItemCount(); i++) {
            String item = combo.getItemAt(i).toString();
            // Cek jika item bukan teks "Pilih..." dan string database mengandung nama item
            if (!item.contains("Pilih") && dataLama.contains(item)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    // 2. METHOD PENERIMA YANG BARU (Menggantikan method lama Anda)
    public void muatDataUntukEdit(String idPesananLama, String nama, String telp, String tier, String cpu, String vga, String mobo, String ram, String storage, String psu, String cooler, String casing, String fan) { {
      // 1. Simpan ID untuk mode Update (Edit)
        this.idEditAktif = idPesananLama;
        
        // 2. Memasukkan nama dan telepon ke text field UI
        txt_nama.setText(nama);
        txt_telepon.setText(telp);
        
        // 3. Deteksi platform prosesor (Intel/AMD) dan centang Radio Button-nya
        if (cpu != null) {
            if (cpu.contains("Core") || cpu.contains("Intel")) {
                rb_intel.setSelected(true);
            } else if (cpu.contains("Ryzen") || cpu.contains("AMD")) {
                rb_amd.setSelected(true);
            }
        }

        // 4. Pilih Tier 
        cb_tier_rakitan.setSelectedItem(tier);
        
        // 5. Gunakan method bantuan prosedural untuk mencari dan menetapkan nilai setiap dropdown
        setDropdownProsedural(cb_prosessor, cpu);
        setDropdownProsedural(cb_kartu_grafis, vga);
        setDropdownProsedural(cb_motherboard, mobo);
        setDropdownProsedural(cb_ram, ram);
        setDropdownProsedural(cb_penyimpanan, storage); 
        setDropdownProsedural(cb_power_supply, psu);
        setDropdownProsedural(cb_cpu_cooler, cooler);
        setDropdownProsedural(cb_casing, casing);
        setDropdownProsedural(cb_fan_casing, fan);
        
        // 6. Kalkulasi ulang harga
        perbaruiKeranjang();
        
        javax.swing.JOptionPane.showMessageDialog(this, "Seluruh data pesanan siap diedit ulang!");
     }
    }

       private double cekDanMasukkan(String kategori, javax.swing.JComboBox<String> cb, javax.swing.table.DefaultTableModel model) {
        if (cb.getSelectedItem() == null) return 0;
        String nama = cb.getSelectedItem().toString();
        if (nama.contains("Pilih") || nama.trim().isEmpty()) return 0;

        double harga = 0;
        try {
            // Menambahkan awalan java.sql. agar tidak perlu import di atas
            java.sql.Connection conn = koneksi.getKoneksi();
            
            // Menggunakan PreparedStatement agar lebih aman dari error karakter khusus
            java.sql.PreparedStatement pst = conn.prepareStatement("SELECT harga FROM komponen WHERE nama_produk = ? LIMIT 1");
            pst.setString(1, nama);
            java.sql.ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                harga = rs.getDouble("harga");
                
                // Menambahkan awalan java.text. dan java.util.
                java.text.NumberFormat formatRp = java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("in", "ID"));
                
                model.addRow(new Object[]{kategori, nama, formatRp.format(harga)});
            }
            rs.close(); 
            pst.close();
            
        } catch (Exception e) {
            System.out.println("Error ambil harga: " + e.getMessage());
        }
        return harga;
    }
       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cb_fan_casing = new javax.swing.JComboBox<>();
        cb_prosessor = new javax.swing.JComboBox<>();
        cb_motherboard = new javax.swing.JComboBox<>();
        cb_ram = new javax.swing.JComboBox<>();
        cb_kartu_grafis = new javax.swing.JComboBox<>();
        cb_penyimpanan = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cb_power_supply = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cb_cpu_cooler = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cb_casing = new javax.swing.JComboBox<>();
        rb_amd = new javax.swing.JRadioButton();
        rb_intel = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        cb_tier_rakitan = new javax.swing.JComboBox<>();
        txt_nama = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_telepon = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Form_Keranjang_Rakitan = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lbl_total_harga = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        btn_riwayat = new javax.swing.JButton();
        lbl_nama_kasir = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.gray);

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel10.setText("10. Fan Casing");

        cb_fan_casing.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cb_fan_casing.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_fan_casing.setPreferredSize(new java.awt.Dimension(200, 22));

        cb_prosessor.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cb_prosessor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_prosessor.setPreferredSize(new java.awt.Dimension(200, 22));

        cb_motherboard.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cb_motherboard.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_motherboard.setPreferredSize(new java.awt.Dimension(200, 22));

        cb_ram.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cb_ram.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_ram.setPreferredSize(new java.awt.Dimension(200, 22));

        cb_kartu_grafis.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cb_kartu_grafis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_kartu_grafis.setPreferredSize(new java.awt.Dimension(200, 22));

        cb_penyimpanan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cb_penyimpanan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_penyimpanan.setPreferredSize(new java.awt.Dimension(200, 22));

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel7.setText("7. Power Supply (PSU)");

        cb_power_supply.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cb_power_supply.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_power_supply.setPreferredSize(new java.awt.Dimension(200, 22));

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel8.setText("8. CPU Cooler");

        cb_cpu_cooler.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cb_cpu_cooler.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_cpu_cooler.setPreferredSize(new java.awt.Dimension(200, 22));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setText("1. Pilih Tier Rakitan");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setText("2. Prosessor");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setText("3. Motherboad");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setText("4. RAM");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel5.setText("5. Kartu Grafis");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel6.setText("6. Penyimpanan (SSD/HDD)");

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel9.setText("9. Casing");

        cb_casing.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cb_casing.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_casing.setPreferredSize(new java.awt.Dimension(200, 22));

        buttonGroup1.add(rb_amd);
        rb_amd.setText("AMD");
        rb_amd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_amdActionPerformed(evt);
            }
        });

        buttonGroup1.add(rb_intel);
        rb_intel.setText("Intel");
        rb_intel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_intelActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel12.setText("Nama Pemesan");

        cb_tier_rakitan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cb_tier_rakitan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_tier_rakitan.setPreferredSize(new java.awt.Dimension(200, 22));
        cb_tier_rakitan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tier_rakitanActionPerformed(evt);
            }
        });

        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel13.setText("Nomor Telepon");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel12)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rb_amd, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rb_intel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nama)
                            .addComponent(txt_telepon)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_fan_casing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(cb_casing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(cb_cpu_cooler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(cb_power_supply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(cb_penyimpanan, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(cb_kartu_grafis, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(cb_ram, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(cb_motherboard, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(cb_prosessor, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_tier_rakitan, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 84, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_telepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_tier_rakitan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_intel)
                    .addComponent(rb_amd))
                .addGap(10, 10, 10)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_prosessor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(cb_motherboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_ram, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_kartu_grafis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_penyimpanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_power_supply, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_cpu_cooler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_casing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_fan_casing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel11.setText("KERANJANG RAKITAN ANDA");

        Form_Keranjang_Rakitan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        Form_Keranjang_Rakitan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Kategori", "Nama Komponen", "Harga"
            }
        ));
        Form_Keranjang_Rakitan.setFillsViewportHeight(true);
        Form_Keranjang_Rakitan.setRowHeight(30);
        Form_Keranjang_Rakitan.setShowHorizontalLines(true);
        Form_Keranjang_Rakitan.setShowVerticalLines(true);
        jScrollPane1.setViewportView(Form_Keranjang_Rakitan);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(185, 185, 185))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        lbl_total_harga.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbl_total_harga.setText("Total: Rp 0");

        btn_simpan.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btn_simpan.setText("Simpan Pesanan");
        btn_simpan.setPreferredSize(new java.awt.Dimension(200, 22));
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_reset.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btn_reset.setText("Reset Keranjang");
        btn_reset.setPreferredSize(new java.awt.Dimension(200, 22));
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        btn_riwayat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btn_riwayat.setText("Lihat Riwayat");
        btn_riwayat.setPreferredSize(new java.awt.Dimension(200, 22));
        btn_riwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_riwayatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbl_total_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addComponent(btn_riwayat, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_total_harga)
                    .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_riwayat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        lbl_nama_kasir.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbl_nama_kasir.setText("Kasir: Guest");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbl_nama_kasir, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lbl_nama_kasir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 31, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
       // 1. PASTIKAN HARGA TERUPDATE SEBELUM DI-SIMPAN
        perbaruiKeranjang(); 

        try {
            // Ambil teks dari pilihan ComboBox Utama
            String tierDipilih = cb_tier_rakitan.getSelectedItem().toString();
            String cpuDipilih = cb_prosessor.getSelectedItem().toString();
            String vgaDipilih = cb_kartu_grafis.getSelectedItem().toString();
            
            // --- TAMBAHAN: Ambil teks dari 7 ComboBox komponen lainnya ---
            String moboDipilih = cb_motherboard.getSelectedItem().toString();
            String ramDipilih = cb_ram.getSelectedItem().toString();
            String storageDipilih = cb_penyimpanan.getSelectedItem().toString(); // Pastikan nama variabelnya sesuai (cb_penyimpanan)
            String psuDipilih = cb_power_supply.getSelectedItem().toString();
            String coolerDipilih = cb_cpu_cooler.getSelectedItem().toString();
            String casingDipilih = cb_casing.getSelectedItem().toString();
            String fanDipilih = cb_fan_casing.getSelectedItem().toString();
            
            // Validasi jika belum memilih komponen utama
            if(cpuDipilih.contains("Pilih") || vgaDipilih.contains("Pilih")) {
                 javax.swing.JOptionPane.showMessageDialog(this, "Silakan pilih komponen terlebih dahulu!");
                 return;
            }

            // 2. MENCIPTAKAN OBJECT OOP (Implementasi Polimorfisme)
            KomponenPC objCpu = new Prosesor(cpuDipilih, 0, tierDipilih);
            KomponenPC objVga = new VGA(vgaDipilih, 0, tierDipilih);
            
            // 3. KONFIRMASI SEBELUM SIMPAN
            java.text.NumberFormat formatRp = java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("in", "ID"));
            int konfirmasi = javax.swing.JOptionPane.showConfirmDialog(this, 
                "Simpan pesanan dengan total " + formatRp.format(totalHargaKasir) + "?", 
                "Konfirmasi Simpan", javax.swing.JOptionPane.YES_NO_OPTION);
                
            // Tarik input pelanggan
            String namaPelanggan = txt_nama.getText();
            String telpPelanggan = txt_telepon.getText();

            // Validasi jika nama kosong
            if (namaPelanggan.trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Nama pemesan wajib diisi!");
                return;
            }

            if (konfirmasi == javax.swing.JOptionPane.YES_OPTION) {
                java.sql.Connection conn = koneksi.getKoneksi();
                
                if (idEditAktif == null) {
                    // --- MODE INSERT ---
                    String sql = "INSERT INTO transaksi_pesanan (nama_pemesan, no_telepon, tier_rakitan, detail_prosesor, detail_vga, jumlah_pesanan, total_harga, detail_motherboard, detail_ram, detail_penyimpanan, detail_psu, detail_cooler, detail_casing, detail_fan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    
                    pst.setString(1, namaPelanggan);
                    pst.setString(2, telpPelanggan);
                    pst.setString(3, tierDipilih);
                    pst.setString(4, objCpu.cetakSpesifikasiKasir()); 
                    pst.setString(5, objVga.cetakSpesifikasiKasir()); 
                    pst.setInt(6, 1); 
                    pst.setDouble(7, totalHargaKasir); 
                    pst.setString(8, moboDipilih);
                    pst.setString(9, ramDipilih);
                    pst.setString(10, storageDipilih);
                    pst.setString(11, psuDipilih);
                    pst.setString(12, coolerDipilih);
                    pst.setString(13, casingDipilih);
                    pst.setString(14, fanDipilih);
                    
                    pst.executeUpdate(); 
                    javax.swing.JOptionPane.showMessageDialog(this, "Pesanan Baru Berhasil Disimpan!");
                } else {
                    // --- MODE UPDATE ---
                    String sql = "UPDATE transaksi_pesanan SET nama_pemesan=?, no_telepon=?, tier_rakitan=?, detail_prosesor=?, detail_vga=?, jumlah_pesanan=?, total_harga=?, detail_motherboard=?, detail_ram=?, detail_penyimpanan=?, detail_psu=?, detail_cooler=?, detail_casing=?, detail_fan=? WHERE id_pesanan=?";
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    
                    pst.setString(1, namaPelanggan);
                    pst.setString(2, telpPelanggan);
                    pst.setString(3, tierDipilih);
                    pst.setString(4, objCpu.cetakSpesifikasiKasir()); 
                    pst.setString(5, objVga.cetakSpesifikasiKasir()); 
                    pst.setInt(6, 1); 
                    pst.setDouble(7, totalHargaKasir); 
                    pst.setString(8, moboDipilih);
                    pst.setString(9, ramDipilih);
                    pst.setString(10, storageDipilih);
                    pst.setString(11, psuDipilih);
                    pst.setString(12, coolerDipilih);
                    pst.setString(13, casingDipilih);
                    pst.setString(14, fanDipilih);
                    pst.setString(15, idEditAktif);
                    
                    pst.executeUpdate(); 
                    javax.swing.JOptionPane.showMessageDialog(this, "Data Pesanan Berhasil Diperbarui!");
                }
                
                resetKeranjang(); 
                txt_nama.setText(""); // Kosongkan textfield nama
                txt_telepon.setText(""); // Kosongkan textfield telp
                idEditAktif = null; 
            }
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Gagal menyimpan: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        resetKeranjang();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_riwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_riwayatActionPerformed
        new Form_Riwayat().setVisible(true);
    }//GEN-LAST:event_btn_riwayatActionPerformed

    private void rb_amdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_amdActionPerformed
     sesuaikanKomponenPlatform();
    }//GEN-LAST:event_rb_amdActionPerformed

    private void rb_intelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_intelActionPerformed
      sesuaikanKomponenPlatform();
    }//GEN-LAST:event_rb_intelActionPerformed

    private void cb_tier_rakitanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tier_rakitanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_tier_rakitanActionPerformed

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(pemesanan_pc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Jalankan form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pemesanan_pc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Form_Keranjang_Rakitan;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_riwayat;
    private javax.swing.JButton btn_simpan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cb_casing;
    private javax.swing.JComboBox<String> cb_cpu_cooler;
    private javax.swing.JComboBox<String> cb_fan_casing;
    private javax.swing.JComboBox<String> cb_kartu_grafis;
    private javax.swing.JComboBox<String> cb_motherboard;
    private javax.swing.JComboBox<String> cb_penyimpanan;
    private javax.swing.JComboBox<String> cb_power_supply;
    private javax.swing.JComboBox<String> cb_prosessor;
    private javax.swing.JComboBox<String> cb_ram;
    private javax.swing.JComboBox<String> cb_tier_rakitan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_nama_kasir;
    private javax.swing.JLabel lbl_total_harga;
    private javax.swing.JRadioButton rb_amd;
    private javax.swing.JRadioButton rb_intel;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_telepon;
    // End of variables declaration//GEN-END:variables
}
