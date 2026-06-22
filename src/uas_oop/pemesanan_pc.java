/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uas_oop;

/**
 *
 * @author BAGAS
 */
public class pemesanan_pc extends javax.swing.JFrame {

    /**
     * Creates new form pemesanan_pc
     */
    // 1. Panggil fungsi inisialisasi di dalam Constructor Utama Anda
    public pemesanan_pc() {
        initComponents();
        loadComboData();          // Isi pilihan combo box dari db_rakitpc
        initComboListeners();     // Buat keranjang mendeteksi perubahan pilihan otomatis
    }

    // 2. Fungsi mengisi data dari Database ke JComboBox masing-masing
    private void loadComboData() {
        java.sql.Connection conn = Koneksi.getKoneksi();
        if (conn == null) return;

        try {
            // Mengatur pilihan Tier Rakitan
            cb_tier_rakitan.removeAllItems();
            cb_tier_rakitan.addItem("Semua Tier");
            cb_tier_rakitan.addItem("Low");
            cb_tier_rakitan.addItem("Mid");
            cb_tier_rakitan.addItem("End"); 

            // Ambil merk dan nama dari tabel komponen
            String sql = "SELECT kategori, merk, nama_produk FROM komponen";
            java.sql.PreparedStatement ps = conn.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();

            // Kosongkan item bawaan NetBeans ("Item 1", "Item 2", dll)
            cb_prosessor.removeAllItems();
            cb_motherboard.removeAllItems();
            cb_ram.removeAllItems();
            cb_kartu_grafis.removeAllItems();
            cb_penyimpanan.removeAllItems();
            cb_psu.removeAllItems();
            cb_cpu_cooler.removeAllItems();
            cb_casing.removeAllItems();
            cb_fan_casing.removeAllItems();

            // Beri petunjuk default di baris pertama
            cb_prosessor.addItem("- Pilih Processor -");
            cb_motherboard.addItem("- Pilih Motherboard -");
            cb_ram.addItem("- Pilih RAM -");
            cb_kartu_grafis.addItem("- Pilih Kartu Grafis -");
            cb_penyimpanan.addItem("- Pilih Penyimpanan -");
            cb_psu.addItem("- Pilih PSU -");
            cb_cpu_cooler.addItem("- Pilih CPU Cooler -");
            cb_casing.addItem("- Pilih Casing -");
            cb_fan_casing.addItem("- Pilih Fan Casing -");

            while (rs.next()) {
                String kategori = rs.getString("kategori");
                String itemLengkap = rs.getString("merk") + " " + rs.getString("nama_produk");

                // Memilah data masuk sesuai kategori database Anda
                switch (kategori) {
                    case "Processor": cb_prosessor.addItem(itemLengkap); break;
                    case "Motherboard": cb_motherboard.addItem(itemLengkap); break;
                    case "RAM": cb_ram.addItem(itemLengkap); break;
                    case "VGA": cb_kartu_grafis.addItem(itemLengkap); break; 
                    case "Storage": cb_penyimpanan.addItem(itemLengkap); break;
                    case "Power Supply": cb_psu.addItem(itemLengkap); break;
                    case "CPU Cooler": cb_cpu_cooler.addItem(itemLengkap); break;
                    case "Casing": cb_casing.addItem(itemLengkap); break;
                    case "Fan Casing": cb_fan_casing.addItem(itemLengkap); break;
                }
            }
            
            // Set text awal pembayaran menjadi 0 rupiah
            jumlah_pesanan.setText("Rp 0");
            
        } catch (Exception e) {
            System.out.println("Gagal memuat komponen ke form: " + e.getMessage());
        }
    }

    // 3. Fungsi Otomatis untuk Mengisi JTable Keranjang & Hitung Total Bayar
    private void updateKeranjangDanTotal() {
        java.sql.Connection conn = Koneksi.getKoneksi();
        if (conn == null) return;

        // Ambil model tabel dari JTable Form_Keranjang_Rakitan
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) Form_Keranjang_Rakitan.getModel();
        model.setRowCount(0); // Bersihkan isi keranjang sebelum dihitung ulang

        double totalHarga = 0;
        
        // Array komponen untuk mempermudah pengecekan looping
        String[] namaKomponen = {
            (String) cb_prosessor.getSelectedItem(),
            (String) cb_motherboard.getSelectedItem(),
            (String) cb_ram.getSelectedItem(),
            (String) cb_kartu_grafis.getSelectedItem(),
            (String) cb_penyimpanan.getSelectedItem(),
            (String) cb_psu.getSelectedItem(),
            (String) cb_cpu_cooler.getSelectedItem(),
            (String) cb_casing.getSelectedItem(),
            (String) cb_fan_casing.getSelectedItem()
        };

        try {
            // Query mencari harga berdasarkan kombinasi merk + nama_produk
            String sql = "SELECT kategori, harga FROM komponen WHERE CONCAT(merk, ' ', nama_produk) = ?";
            java.sql.PreparedStatement ps = conn.prepareStatement(sql);

            for (String item : namaKomponen) {
                if (item != null && !item.startsWith("- Pilih") && !item.isEmpty()) {
                    ps.setString(1, item);
                    java.sql.ResultSet rs = ps.executeQuery();
                    
                    if (rs.next()) {
                        String kategori = rs.getString("kategori");
                        double harga = rs.getDouble("harga");

                        // Tambahkan data ke baris JTable Keranjang kanan
                        model.addRow(new Object[]{kategori, item, "Rp " + String.format("%,.0f", harga)});
                        
                        // Akumulasikan ke total bayar
                        totalHarga += harga;
                    }
                }
            }

            // Tampilkan total akumulasi ke label jumlah_pesanan Anda
            jumlah_pesanan.setText("Rp " + String.format("%,.0f", totalHarga));

        } catch (Exception e) {
            System.out.println("Gagal memperbarui keranjang: " + e.getMessage());
        }
    }

    // Listener pemicu perubahan data combo box
    private void initComboListeners() {
        java.awt.event.ItemListener listener = new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                    updateKeranjangDanTotal();
                }
            }
        };
        cb_prosessor.addItemListener(listener);
        cb_motherboard.addItemListener(listener);
        cb_ram.addItemListener(listener);
        cb_kartu_grafis.addItemListener(listener);
        cb_penyimpanan.addItemListener(listener);
        cb_psu.addItemListener(listener);
        cb_cpu_cooler.addItemListener(listener);
        cb_casing.addItemListener(listener);
        cb_fan_casing.addItemListener(listener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cb_tier_rakitan = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Form_Keranjang_Rakitan = new javax.swing.JTable();
        cb_prosessor = new javax.swing.JComboBox<>();
        cb_motherboard = new javax.swing.JComboBox<>();
        cb_ram = new javax.swing.JComboBox<>();
        cb_kartu_grafis = new javax.swing.JComboBox<>();
        cb_penyimpanan = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cb_psu = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cb_cpu_cooler = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cb_casing = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cb_fan_casing = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jumlah_pesanan = new javax.swing.JLabel();
        btnbayar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("1. Pilih Tier Rakitan");

        jLabel2.setText("2. Prosessor");

        jLabel3.setText("3. Motherboad");

        cb_tier_rakitan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_tier_rakitan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tier_rakitanActionPerformed(evt);
            }
        });

        jLabel4.setText("4. RAM");

        jLabel5.setText("5. Kartu Grafis");

        jLabel6.setText("6. Penyimpanan (SSD/HDD)");

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
        jScrollPane1.setViewportView(Form_Keranjang_Rakitan);

        cb_prosessor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cb_motherboard.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cb_ram.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cb_kartu_grafis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cb_penyimpanan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("7. Power Supply (PSU)");

        cb_psu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("8. CPU Cooler");

        cb_cpu_cooler.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("9. Casing");

        cb_casing.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("10. Fan Casing");

        cb_fan_casing.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("KERANJANG RAKITAN ANDA");

        jumlah_pesanan.setText("jLabel12");

        btnbayar.setText("jButton1");
        btnbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbayarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cb_fan_casing, javax.swing.GroupLayout.Alignment.LEADING, 0, 208, Short.MAX_VALUE)
                                .addComponent(cb_casing, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cb_cpu_cooler, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cb_psu, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cb_tier_rakitan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cb_prosessor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cb_motherboard, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cb_ram, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cb_kartu_grafis, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cb_penyimpanan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(299, 299, 299)))
                            .addComponent(btnbayar, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jumlah_pesanan)
                        .addGap(440, 440, 440))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addGap(0, 757, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_tier_rakitan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_prosessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)
                        .addComponent(cb_motherboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_ram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(cb_kartu_grafis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(cb_penyimpanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(15, 15, 15)
                        .addComponent(cb_psu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jumlah_pesanan)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_cpu_cooler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(cb_casing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_fan_casing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbayar))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_tier_rakitanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tier_rakitanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_tier_rakitanActionPerformed

    private void btnbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbayarActionPerformed
            try {
            String totalBayar = jumlah_pesanan.getText();

            // Validasi jika belum ada barang dipilih
            if (totalBayar.equals("Rp 0") || totalBayar.equals("jLabel12")) {
                javax.swing.JOptionPane.showMessageDialog(this, "Keranjang rakitan Anda masih kosong!", "Peringatan", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Mengambil data terpilih
            String tier = cb_tier_rakitan.getSelectedItem().toString();
            String prosessor = cb_prosessor.getSelectedItem().toString();
            String mobo = cb_motherboard.getSelectedItem().toString();
            String ram = cb_ram.getSelectedItem().toString();
            String vga = cb_kartu_grafis.getSelectedItem().toString();
            String storage = cb_penyimpanan.getSelectedItem().toString();
            String psu = cb_psu.getSelectedItem().toString();
            String cooler = cb_cpu_cooler.getSelectedItem().toString();
            String casing = cb_casing.getSelectedItem().toString();
            String fan = cb_fan_casing.getSelectedItem().toString();

            // Hubungkan ke DB untuk simpan transaksi / cetak nota rakitan
            java.sql.Connection conn = Koneksi.getKoneksi();
            String sql = "INSERT INTO keranjang_rakitan (tier, prosessor, motherboard, ram, vga, penyimpanan, psu, cooler, casing, fan_casing, total_harga) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, tier);
            pst.setString(2, prosessor);
            pst.setString(3, mobo);
            pst.setString(4, ram);
            pst.setString(5, vga);
            pst.setString(6, storage);
            pst.setString(7, psu);
            pst.setString(8, cooler);
            pst.setString(9, casing);
            pst.setString(10, fan);
            pst.setString(11, totalBayar);

            pst.executeUpdate();
            
            // Memunculkan Struk Ringkasan Cetak Nota Pembayaran PC Anda
            javax.swing.JOptionPane.showMessageDialog(this, 
                "============= NOTA PEMBAYARAN RAKIT PC =============\n" +
                "Tier Spesifikasi: " + tier + "\n" +
                "Processor       : " + (prosessor.startsWith("-") ? "Tidak memilih" : prosessor) + "\n" +
                "Graphics Card   : " + (vga.startsWith("-") ? "Tidak memilih" : vga) + "\n" +
                "----------------------------------------------------\n" +
                "TOTAL AKHIR     : " + totalBayar + "\n" +
                "====================================================\n" +
                "Status: TRANSAKSI BERHASIL & SIMPAN DATABASE!", 
                "Cetak Nota Transaksi", 
                javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Terjadi kesalahan sistem: " + e.getMessage());
        }
    }//GEN-LAST:event_btnbayarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(pemesanan_pc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pemesanan_pc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pemesanan_pc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pemesanan_pc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pemesanan_pc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Form_Keranjang_Rakitan;
    private javax.swing.JButton btnbayar;
    private javax.swing.JComboBox<String> cb_casing;
    private javax.swing.JComboBox<String> cb_cpu_cooler;
    private javax.swing.JComboBox<String> cb_fan_casing;
    private javax.swing.JComboBox<String> cb_kartu_grafis;
    private javax.swing.JComboBox<String> cb_motherboard;
    private javax.swing.JComboBox<String> cb_penyimpanan;
    private javax.swing.JComboBox<String> cb_prosessor;
    private javax.swing.JComboBox<String> cb_psu;
    private javax.swing.JComboBox<String> cb_ram;
    private javax.swing.JComboBox<String> cb_tier_rakitan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jumlah_pesanan;
    // End of variables declaration//GEN-END:variables
}
