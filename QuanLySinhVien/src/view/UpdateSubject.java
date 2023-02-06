/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import database.ConnectDatabase;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Subject;
import model.Teacher;

/**
 *
 * @author quang
 */
public class UpdateSubject extends javax.swing.JFrame {

    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private final ConnectDatabase connectDB = new ConnectDatabase();

    private String sql = "SELECT * FROM MONHOC";

    /**
     * Creates new form UpdateSubject
     */
    public UpdateSubject() {
        initComponents();
        show_subject();
        btnAction();
    }

    public void reset() {
        txt_mamh.setText("");
        txt_tenmh.setText("");
        txt_sotin.setText("");
    }

    private void connection() {
        conn = connectDB.getDBConnect();
    }

    public ArrayList<Subject> subjectList() {
        ArrayList<Subject> subjectList = new ArrayList<>();
        ResultSet rs = null;
        Statement stm = null;
        try {

            conn = connectDB.getDBConnect();
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            Subject subject;
            while (rs.next()) {
                subject = new Subject(
                        rs.getString("mamh"),
                        rs.getString("tenmh"),
                        rs.getString("sotin")
                );
                subjectList.add(subject);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return subjectList;
    }

    public void show_subject() {
        ArrayList<Subject> list = subjectList();
        DefaultTableModel model = (DefaultTableModel) tableSubject.getModel();
        Object[] row = new Object[3];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getMamh();
            row[1] = list.get(i).getTenmh();
            row[2] = list.get(i).getSotin();
            model.addRow(row);

        }
    }

    public void btnAction() {
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("BACK");
                dispose();
                UpdateDB update = new UpdateDB();
                update.setVisible(true);
            }
        });
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sSQL = "insert into MONHOC(MaMH,TenMH,SoTin)values(?,?,?)";
                try {
                    conn = connectDB.getDBConnect();
                    pst = conn.prepareStatement(sSQL);
                    pst.setString(1, txt_mamh.getText());
                    pst.setString(2, txt_tenmh.getText());
                    pst.setString(3, txt_sotin.getText());
                    pst.executeUpdate();
                    reset();
                    dispose();
                    UpdateSubject update = new UpdateSubject();
                    update.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btn_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sSQL = "update MONHOC set TenMH = ?,SoTin = ? where MaMH = '" + txt_mamh.getText() + "'";
                try {
                    conn = connectDB.getDBConnect();
                    pst = conn.prepareStatement(sSQL);
                    pst.setString(1, txt_tenmh.getText());
                    pst.setString(2, txt_sotin.getText());
                    pst.executeUpdate();
                    reset();
                    dispose();
                    UpdateSubject update = new UpdateSubject();
                    update.setVisible(true);
                    System.out.println("Update success");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn = connectDB.getDBConnect();
                int Click = tableSubject.getSelectedRow();
                TableModel model = tableSubject.getModel();
                int a = JOptionPane.showConfirmDialog(null, "Xác nhận xóa ?");
                if (a == JOptionPane.YES_OPTION) {
                    String sqlDelete = "delete MONHOC where MaMH = '" + txt_mamh.getText() + "' ";

                    try {
                        System.out.println("Delete success");
                        pst = conn.prepareStatement(sqlDelete);
                        pst.executeUpdate();
                        reset();
                        dispose();
                        UpdateSubject update = new UpdateSubject();
                        update.setVisible(true);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (a == JOptionPane.NO_OPTION) {
                    show_subject();
                }

            }
        });
             btn_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        tableSubject.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int Click = tableSubject.getSelectedRow();
                TableModel model = tableSubject.getModel();

                // txt_stt.setText(model.getValueAt(Click, 0).toString());
                txt_mamh.setText(model.getValueAt(Click, 0).toString());
                txt_tenmh.setText(model.getValueAt(Click, 1).toString());
                txt_sotin.setText(model.getValueAt(Click, 2).toString());
                btn_update.setEnabled(true);
                btn_delete.setEnabled(true);

            }
        });
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSubject = new javax.swing.JTable();
        txt_mamh = new javax.swing.JTextField();
        txt_tenmh = new javax.swing.JTextField();
        txt_sotin = new javax.swing.JTextField();
        btn_add = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cập nhật môn học");

        tableSubject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã môn học", "Tên môn học", "Số tín"
            }
        ));
        jScrollPane2.setViewportView(tableSubject);

        txt_mamh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_mamhActionPerformed(evt);
            }
        });

        txt_tenmh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenmhActionPerformed(evt);
            }
        });

        txt_sotin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sotinActionPerformed(evt);
            }
        });

        btn_add.setText("Thêm");

        btn_update.setText("Sửa");

        btn_delete.setText("Xóa");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_save.setText("Lưu");

        btn_back.setText("Trở về");

        jLabel2.setText("Mã môn học");

        jLabel3.setText("Tên môn học");

        jLabel4.setText("Số tín");

        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });

        btn_search.setText("Tìm kiếm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txt_mamh, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_tenmh, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_sotin, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(309, 309, 309)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_search)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_mamh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tenmh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sotin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_mamhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_mamhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mamhActionPerformed

    private void txt_tenmhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenmhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenmhActionPerformed

    private void txt_sotinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sotinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sotinActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateSubject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateSubject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateSubject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateSubject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateSubject().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableSubject;
    private javax.swing.JTextField txt_mamh;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_sotin;
    private javax.swing.JTextField txt_tenmh;
    // End of variables declaration//GEN-END:variables
}
