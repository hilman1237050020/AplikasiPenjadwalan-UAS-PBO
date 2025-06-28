
package com.raven.form;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.raven.ui.Event;
import com.raven.ui.EventDAO;

public class Form_1 extends javax.swing.JPanel {

    private JTextField tfId, tfName, tfDate, tfTime;
    private JTextArea taDescription;
    private JComboBox<String> cbPriority;
    private JTable tableEvent;
    private DefaultTableModel tableModel;
    
    public Form_1() {
        initComponents();
        loadEventData();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(242, 242, 242));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(70, 130, 180));
        JLabel title = new JLabel("Aplikasi Jadwal (CRUD)", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);

        // Main content panel with form and table
        JPanel contentPanel = new JPanel(new BorderLayout(15, 15));
        contentPanel.setBackground(new Color(242, 242, 242));

        // Form panel with card-like appearance
        JPanel formContainer = new JPanel(new BorderLayout());
        formContainer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            new EmptyBorder(15, 15, 15, 15)
        ));
        formContainer.setBackground(Color.WHITE);
        formContainer.setPreferredSize(new Dimension(350, getHeight()));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Initialize form components
        tfId = new JTextField(5);
        tfId.setEditable(false);
        tfName = new JTextField(15);
        tfDate = new JTextField(10);
        tfTime = new JTextField(5);
        taDescription = new JTextArea(3, 15);
        cbPriority = new JComboBox<>(new String[]{"Low", "Medium", "High"});

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tfDate.setText(sdf.format(new Date()));
        tfTime.setText("12:00");

        JScrollPane descriptionScroll = new JScrollPane(taDescription);
        descriptionScroll.setPreferredSize(new Dimension(200, 60));

        // Form labels and fields
        String[] labels = {"ID", "Nama Jadwal", "Tanggal", "Waktu", "Deskripsi", "Prioritas"};
        JComponent[] fields = {tfId, tfName, tfDate, tfTime, descriptionScroll, cbPriority};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.3;
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            formPanel.add(label, gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.7;
            ((JComponent) fields[i]).setFont(new Font("Segoe UI", Font.PLAIN, 12));
            formPanel.add(fields[i], gbc);
        }

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnAdd = createStyledButton("Add", new Color(76, 175, 80));
        JButton btnUpdate = createStyledButton("Update", new Color(33, 150, 243));
        JButton btnDelete = createStyledButton("Delete", new Color(244, 67, 54));

        btnAdd.addActionListener(e -> handleAdd());
        btnUpdate.addActionListener(e -> handleUpdate());
        btnDelete.addActionListener(e -> handleDelete());

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        // Add components to form container
        formContainer.add(formPanel, BorderLayout.CENTER);
        formContainer.add(buttonPanel, BorderLayout.SOUTH);

        // Table setup
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Date", "Time", "Description", "Priority"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { 
                return false; 
            }
        };
        
        tableEvent = new JTable(tableModel);
        tableEvent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableEvent.getSelectionModel().addListSelectionListener(e -> fillFormFromTable());
        
        // Style the table
        tableEvent.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableEvent.setRowHeight(25);
        tableEvent.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableEvent.setShowGrid(true);
        tableEvent.setGridColor(new Color(240, 240, 240));
        
        JScrollPane tableScroll = new JScrollPane(tableEvent);
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        
        // Add components to content panel
        contentPanel.add(formContainer, BorderLayout.WEST);
        contentPanel.add(tableScroll, BorderLayout.CENTER);
        
        add(contentPanel, BorderLayout.CENTER);
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    private void loadEventData() {
        tableModel.setRowCount(0); // Clear table
        List<Event> eventList = EventDAO.getAllEvents();
        for (Event e : eventList) {
            tableModel.addRow(new Object[]{
                e.getId(), e.getName(), e.getDate(), e.getTime(), e.getDescription(), e.getPriority()
            });
        }
    }

    private void clearForm() {
        tfId.setText("");
        tfName.setText("");
        tfDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        tfTime.setText("12:00");
        taDescription.setText("");
        cbPriority.setSelectedIndex(0);
    }

    private void fillFormFromTable() {
        int row = tableEvent.getSelectedRow();
        if (row != -1) {
            tfId.setText(tableModel.getValueAt(row, 0).toString());
            tfName.setText(tableModel.getValueAt(row, 1).toString());
            tfDate.setText(tableModel.getValueAt(row, 2).toString());
            tfTime.setText(tableModel.getValueAt(row, 3).toString());
            taDescription.setText(tableModel.getValueAt(row, 4).toString());
            cbPriority.setSelectedItem(tableModel.getValueAt(row, 5).toString());
        }
    }

    private void handleAdd() {
        String name = tfName.getText().trim();
        String date = tfDate.getText().trim();
        String time = tfTime.getText().trim();
        String description = taDescription.getText().trim();
        String priority = cbPriority.getSelectedItem().toString();

        if (name.isEmpty() || date.isEmpty() || time.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Nama, Tanggal, dan Waktu harus diisi!", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        Event event = new Event(0, name, date, time, description, priority);
        boolean success = EventDAO.insertEvent(event);

        if (success) {
            JOptionPane.showMessageDialog(this, 
                "Event berhasil ditambahkan!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            loadEventData();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Gagal menambahkan event.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleUpdate() {
        if (tfId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Pilih event yang akan diupdate dari tabel!", 
                "Warning", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String name = tfName.getText().trim();
        String date = tfDate.getText().trim();
        String time = tfTime.getText().trim();
        
        if (name.isEmpty() || date.isEmpty() || time.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Nama, Tanggal, dan Waktu harus diisi!", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        Event e = new Event(
            Integer.parseInt(tfId.getText()),
            name,
            date,
            time,
            taDescription.getText().trim(),
            cbPriority.getSelectedItem().toString()
        );
        
        boolean success = EventDAO.updateEvent(e);
        if (success) {
            JOptionPane.showMessageDialog(this, 
                "Event berhasil diupdate!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            loadEventData();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Gagal mengupdate event.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDelete() {
        if (tfId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Pilih event yang akan dihapus dari tabel!", 
                "Warning", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Apakah Anda yakin ingin menghapus event ini?", 
            "Konfirmasi Hapus", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(tfId.getText());
            boolean success = EventDAO.deleteEvent(id);
            
            if (success) {
                JOptionPane.showMessageDialog(this, 
                    "Event berhasil dihapus!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                loadEventData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Gagal menghapus event.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(106, 106, 106));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Form 1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(148, 148, 148))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
*/