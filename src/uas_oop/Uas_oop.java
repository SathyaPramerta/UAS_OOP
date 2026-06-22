/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package uas_oop;

// Pastikan import benar
import uas_oop.KomponenPC;
import uas_oop.Prosesor;
import uas_oop.VGA;
import javax.swing.JOptionPane;

public class Uas_oop extends javax.swing.JFrame {
    
    // ... (kode komponen frame Anda lainnya) ...

    private void btnbayarActionPerformed(java.awt.event.ActionEvent evt) {
        // Contoh Polimorfisme di dalam tombol
        try {
            // Mengambil data dari variabel global atau input user
            Prosesor objCpu = new Prosesor("Intel", "Core i7", 4500000, "Mid", "LGA1700");
            VGA objVga = new VGA("NVIDIA", "RTX 4060", 5200000, "End", "PCIe x16");

            // Menggabungkan info
            String info = "Detail Rakitan:\n" +
                          "- " + objCpu.getNamaProduk() + " (" + objCpu.getSpesifikasiUtama() + ")\n" +
                          "- " + objVga.getNamaProduk() + " (" + objVga.getSpesifikasiUtama() + ")";
            
            JOptionPane.showMessageDialog(this, info);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
