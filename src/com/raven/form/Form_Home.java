package com.raven.form;

import com.raven.ui.Model_Card;
import com.raven.ui.StatusType;
import com.raven.ui.Event;
import com.raven.ui.EventDAO;
import com.raven.swing.ScrollBar;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import com.raven.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


public class Form_Home extends javax.swing.JPanel {
    
public Form_Home() {
    initComponents();
    

try (Connection conn = DBConnection.getConnection()) {
    // Total jadwal (total event)
    Statement st1 = conn.createStatement();
    ResultSet rs1 = st1.executeQuery("SELECT COUNT(*) FROM event");
    rs1.next();
    int totalJadwal = rs1.getInt(1);

    // Jadwal hari ini
    PreparedStatement ps2 = conn.prepareStatement("SELECT COUNT(*) FROM event WHERE date = CURDATE()");
    ResultSet rs2 = ps2.executeQuery();
    rs2.next();
    int jadwalHariIni = rs2.getInt(1);

    // Karena tidak ada kolom 'dosen', kita pakai 'priority' sebagai contoh unik (bisa diubah sesuai kebutuhan)
    Statement st3 = conn.createStatement();
    ResultSet rs3 = st3.executeQuery("SELECT COUNT(DISTINCT priority) FROM event");
    rs3.next();
    int priorityUnik = rs3.getInt(1);

    // Set ke card
    card1.setData(new Model_Card(
        new ImageIcon(getClass().getResource("/com/raven/icon/stock.png")),
        "Total Jadwal",
        String.valueOf(totalJadwal),
        "Total semua jadwal terdaftar"
    ));

    card2.setData(new Model_Card(
        new ImageIcon(getClass().getResource("/com/raven/icon/profit.png")),
        "Jadwal Hari Ini",
        String.valueOf(jadwalHariIni),
        "Jumlah jadwal pada " + java.time.LocalDate.now()
    ));

    card3.setData(new Model_Card(
        new ImageIcon(getClass().getResource("/com/raven/icon/flag.png")),
        "Kategori Prioritas Unik",
        String.valueOf(priorityUnik),
        "Jumlah kategori prioritas dalam data"
    ));

} catch (SQLException e) {
    e.printStackTrace();
}

    
    // Scroll behavior
    spTable.setVerticalScrollBar(new ScrollBar());
    spTable.getVerticalScrollBar().setBackground(Color.WHITE);
    spTable.getViewport().setBackground(Color.WHITE);
    JPanel p = new JPanel();
    p.setBackground(Color.WHITE);
    spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
    
    // Load event dari database
    loadEventTable();
}

private void loadEventTable() {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0);  // kosongkan data sebelumnya

    List<Event> events = EventDAO.getAllEvents();
    for (Event e : events) {
        model.addRow(new Object[]{
            e.getId(),
            e.getName(),
            e.getDate(),
            e.getTime(),
            e.getDescription(),
            e.getPriority()
        });
    }
}

public void refreshData() {
    loadEventTable();
    // Refresh juga statistik kalau perlu
    try (Connection conn = DBConnection.getConnection()) {
        // total jadwal
        Statement st1 = conn.createStatement();
        ResultSet rs1 = st1.executeQuery("SELECT COUNT(*) FROM event");
        rs1.next();
        int totalJadwal = rs1.getInt(1);

        // jadwal hari ini
        PreparedStatement ps2 = conn.prepareStatement("SELECT COUNT(*) FROM event WHERE date = CURDATE()");
        ResultSet rs2 = ps2.executeQuery();
        rs2.next();
        int jadwalHariIni = rs2.getInt(1);

        Statement st3 = conn.createStatement();
        ResultSet rs3 = st3.executeQuery("SELECT COUNT(DISTINCT priority) FROM event");
        rs3.next();
        int priorityUnik = rs3.getInt(1);

        card1.setData(new Model_Card(
            new ImageIcon(getClass().getResource("/com/raven/icon/stock.png")),
            "Total Jadwal", String.valueOf(totalJadwal), "Total semua jadwal terdaftar"
        ));

        card2.setData(new Model_Card(
            new ImageIcon(getClass().getResource("/com/raven/icon/profit.png")),
            "Jadwal Hari Ini", String.valueOf(jadwalHariIni), "Jumlah jadwal pada " + java.time.LocalDate.now()
        ));

        card3.setData(new Model_Card(
            new ImageIcon(getClass().getResource("/com/raven/icon/flag.png")),
            "Kategori Prioritas Unik", String.valueOf(priorityUnik), "Jumlah kategori prioritas dalam data"
        ));
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new com.raven.component.Card();
        card2 = new com.raven.component.Card();
        card3 = new com.raven.component.Card();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.raven.swing.Table();

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        panel.add(card3);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Standard Table Design");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama", "Tanggal", "Waktu", "Deskripsi", "Prioritas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(spTable))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.component.Card card1;
    private com.raven.component.Card card2;
    private com.raven.component.Card card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
