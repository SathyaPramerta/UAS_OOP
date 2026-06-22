package uas_oop;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.NumberFormat;
import java.util.Locale;

public class Form_Riwayat extends javax.swing.JFrame {

    // =========================================================
    // 1. CONSTRUCTOR
    // =========================================================
    public Form_Riwayat() {
        initComponents();
        this.setLocationRelativeTo(null); // Menengahkan posisi form
        tampilkanData(); // Otomatis memuat data saat form dibuka
    }

    // =========================================================
    // 2. METHOD TAMPIL DATA (Fitur READ CRUD)
    // =========================================================
   private void tampilkanData() {
       javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        model.addColumn("ID"); // Index 0 (Akan kita sembunyikan)
        model.addColumn("Nama Pemesan"); 
        model.addColumn("No. Telepon");
        model.addColumn("Tier");
        model.addColumn("Total Harga");
        model.addColumn("Catatan");

        tbl_riwayat.setModel(model);

        // --- TRIK AJAIB: SEMBUNYIKAN KOLOM ID DARI LAYAR ---
        tbl_riwayat.getColumnModel().getColumn(0).setMinWidth(0);
        tbl_riwayat.getColumnModel().getColumn(0).setMaxWidth(0);
        tbl_riwayat.getColumnModel().getColumn(0).setWidth(0);

        try {
            java.sql.Connection conn = koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery("SELECT * FROM transaksi_pesanan");
            
            java.text.NumberFormat formatRp = java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("in", "ID"));

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_pesanan"),      
                    rs.getString("nama_pemesan"),
                    rs.getString("no_telepon"),
                    rs.getString("tier_rakitan"),
                    formatRp.format(rs.getDouble("total_harga")),
                    rs.getString("catatan")
                });
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Gagal memuat riwayat: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_riwayat = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btn_catatan = new javax.swing.JButton();
        btn_edit_ulang = new javax.swing.JButton();
        btn_cetak = new javax.swing.JButton();

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_hapus.setText("Hapus Pesanan");
        btn_hapus.addActionListener(this::btn_hapusActionPerformed);

        tbl_riwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_riwayat);

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setText("Riwayat Pemesanan");

        btn_catatan.setText("Ubah Catatan");
        btn_catatan.addActionListener(this::btn_catatanActionPerformed);

        btn_edit_ulang.setText("Edit Ulang Komponen");
        btn_edit_ulang.addActionListener(this::btn_edit_ulangActionPerformed);

        btn_cetak.setText("Cetak Struk");
        btn_cetak.addActionListener(this::btn_cetakActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_cetak)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_edit_ulang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_catatan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_hapus))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_hapus)
                    .addComponent(btn_catatan)
                    .addComponent(btn_edit_ulang)
                    .addComponent(btn_cetak))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
    // =========================================================
    // 3. EVENT TOMBOL HAPUS (Fitur DELETE CRUD)
    // =========================================================                                        
        // Cek apakah ada baris yang dipilih di tabel
        int barisDipilih = tbl_riwayat.getSelectedRow();
        
        if (barisDipilih == -1) {
            JOptionPane.showMessageDialog(this, "Silakan klik salah satu data di tabel yang ingin dihapus!");
            return;
        }

        // Ambil ID dari kolom pertama (kolom index 0)
        String idPesanan = tbl_riwayat.getValueAt(barisDipilih, 0).toString();

        // Konfirmasi penghapusan
        int konfirmasi = JOptionPane.showConfirmDialog(this, 
                "Apakah Anda yakin ingin menghapus pesanan dengan ID " + idPesanan + "?", 
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                Connection conn = koneksi.getKoneksi();
                String sql = "DELETE FROM transaksi_pesanan WHERE id_pesanan = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, idPesanan);
                
                pst.executeUpdate(); // Mengeksekusi penghapusan
                
                JOptionPane.showMessageDialog(this, "Data pesanan berhasil dihapus!");
                tampilkanData(); // Segarkan tabel agar data yang dihapus hilang dari layar
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + e.getMessage());
            }
        }                                       
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_catatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_catatanActionPerformed
        int barisDipilih = tbl_riwayat.getSelectedRow();
        if (barisDipilih == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Pilih data pesanan di tabel dulu!");
            return;
        }

        String idPesanan = tbl_riwayat.getValueAt(barisDipilih, 0).toString();
        
        // Meminta input catatan dari kasir
        String catatanBaru = javax.swing.JOptionPane.showInputDialog(this, 
                "Masukkan catatan pesanan (misal: Lunas, Diambil Besok, dll):", 
                "Ubah Catatan Pelanggan", 
                javax.swing.JOptionPane.PLAIN_MESSAGE);
                
        // Cegah error jika kasir menekan tombol cancel
        if (catatanBaru == null) {
            return; 
        }

        try {
            java.sql.Connection conn = koneksi.getKoneksi();
            // Perintah UPDATE khusus untuk kolom catatan
            String sql = "UPDATE transaksi_pesanan SET catatan = ? WHERE id_pesanan = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, catatanBaru);
            pst.setString(2, idPesanan);
            
            pst.executeUpdate();
            javax.swing.JOptionPane.showMessageDialog(this, "Catatan berhasil diperbarui!");
            tampilkanData(); // Refresh tabel
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Gagal mengubah catatan: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_catatanActionPerformed

    private void btn_edit_ulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_ulangActionPerformed
       int barisDipilih = tbl_riwayat.getSelectedRow();
        if (barisDipilih == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Silakan klik data pesanan yang ingin diedit ulang!");
            return;
        }

        String idPesanan = tbl_riwayat.getValueAt(barisDipilih, 0).toString(); 

        try {
            // Kita tarik langsung dari database agar lebih akurat (karena kolom di tabel layar mungkin tidak lengkap)
            java.sql.Connection conn = koneksi.getKoneksi();
            java.sql.PreparedStatement pst = conn.prepareStatement("SELECT * FROM transaksi_pesanan WHERE id_pesanan = ?");
            pst.setString(1, idPesanan);
            java.sql.ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                pemesanan_pc formUtama = new pemesanan_pc();
                
                // Mengirim semua data dari database ke form pemesanan
                formUtama.muatDataUntukEdit(
                    idPesanan,
                    rs.getString("nama_pemesan"), // <--- TAMBAHAN
                    rs.getString("no_telepon"),   // <--- TAMBAHAN
                    rs.getString("tier_rakitan"),
                    rs.getString("detail_prosesor"),
                    rs.getString("detail_vga"),
                    rs.getString("detail_motherboard"),
                    rs.getString("detail_ram"),
                    rs.getString("detail_penyimpanan"),
                    rs.getString("detail_psu"),
                    rs.getString("detail_cooler"),
                    rs.getString("detail_casing"),
                    rs.getString("detail_fan")
                );
                
                formUtama.setVisible(true);
                this.dispose();
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Gagal memuat data komponen: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_edit_ulangActionPerformed

    private void btn_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetakActionPerformed
       int barisDipilih = tbl_riwayat.getSelectedRow();
        if (barisDipilih == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Silakan klik data pesanan yang ingin dicetak!");
            return;
        }

        // Menarik ID Pesanan dari tabel (Meskipun di layar tersembunyi, program tetap bisa membacanya di kolom 0)
        String idPesanan = tbl_riwayat.getModel().getValueAt(barisDipilih, 0).toString();

        try {
            java.sql.Connection conn = koneksi.getKoneksi();
            java.sql.PreparedStatement pst = conn.prepareStatement("SELECT * FROM transaksi_pesanan WHERE id_pesanan = ?");
            pst.setString(1, idPesanan);
            java.sql.ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                java.text.NumberFormat formatRp = java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("in", "ID"));
                
                // NAMA FILE OTOMATIS MENGIKUTI NAMA PEMESAN (spasi diganti garis bawah agar rapi)
                String namaPemesan = rs.getString("nama_pemesan");
                String namaFile = "Struk_" + namaPemesan.replaceAll("\\s+", "_") + ".txt"; 
                
                java.io.FileWriter fileWriter = new java.io.FileWriter(namaFile);
                java.io.PrintWriter printWriter = new java.io.PrintWriter(fileWriter);

                // --- DESAIN STRUK YANG BARU ---
                printWriter.println("=================================================");
                printWriter.println("               STRUK PEMESANAN PC                ");
                printWriter.println("=================================================");
                // Menampilkan Nama dan Telepon, menghilangkan ID
                printWriter.println("Nama Pemesan   : " + namaPemesan);
                printWriter.println("No. Telepon    : " + rs.getString("no_telepon"));
                printWriter.println("Tier Rakitan   : " + rs.getString("tier_rakitan"));
                
                String catatan = rs.getString("catatan");
                printWriter.println("Catatan        : " + (catatan != null ? catatan : "-"));
                
                printWriter.println("-------------------------------------------------");
                printWriter.println("KOMPONEN RAKITAN:");
                printWriter.println("- CPU          : " + rs.getString("detail_prosesor").replace("\n", " | "));
                printWriter.println("- VGA          : " + rs.getString("detail_vga").replace("\n", " | "));
                printWriter.println("- Motherboard  : " + rs.getString("detail_motherboard"));
                printWriter.println("- RAM          : " + rs.getString("detail_ram"));
                printWriter.println("- Penyimpanan  : " + rs.getString("detail_penyimpanan"));
                printWriter.println("- Power Supply : " + rs.getString("detail_psu"));
                printWriter.println("- Cooler       : " + rs.getString("detail_cooler"));
                printWriter.println("- Casing       : " + rs.getString("detail_casing"));
                printWriter.println("- Fan          : " + rs.getString("detail_fan"));
                printWriter.println("-------------------------------------------------");
                printWriter.println("Jumlah Unit    : " + rs.getInt("jumlah_pesanan") + " PC");
                printWriter.println("TOTAL BAYAR    : " + formatRp.format(rs.getDouble("total_harga")));
                printWriter.println("=================================================");
                printWriter.println("       Terima kasih atas pesanan Anda!           ");
                printWriter.println("=================================================");

                printWriter.close();
                
                javax.swing.JOptionPane.showMessageDialog(this, "Struk untuk " + namaPemesan + " berhasil dicetak!\nSilakan cek folder project NetBeans Anda.");
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Gagal mencetak struk: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_cetakActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.out.println(ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Form_Riwayat().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_catatan;
    private javax.swing.JButton btn_cetak;
    private javax.swing.JButton btn_edit_ulang;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_riwayat;
    // End of variables declaration//GEN-END:variables
}
