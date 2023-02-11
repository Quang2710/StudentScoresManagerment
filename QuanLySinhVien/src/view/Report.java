/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import database.ConnectDatabase;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import view.ViewReportClassSubject;

/**
 *
 * @author quang
 */
public class Report extends javax.swing.JFrame {

    CallableStatement command = null;
     private Connection conn = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;
    private final ConnectDatabase connectDB = new ConnectDatabase();
    private String sql = "SELECT MaLopMH FROM LOPMH";
    private ViewReportClassSubject ViewReportClassSubject;

    /**
     * Creates new form Report
     */
    public Report() {
        initComponents();
        innitComboBox();
        init();
    }

    private void innitComboBox() {       
        try {
            conn = connectDB.getDBConnect();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            cbx_maLopMh1.removeAllItems();
            while (rs.next()) {
                cbx_maLopMh1.addItem(rs.getString("MaLopMH"));
            }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    public void init() {
        ResultSet rs = null;
        Statement stm = null;    

        back_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("BACK");
                dispose();
                MainMenu main = new MainMenu();
                main.setVisible(true);
            }
        });
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        back_btn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbx_maLopMh1 = new javax.swing.JComboBox<>();
        btn_report_class_subject = new javax.swing.JButton();
        btn_report_scores_class_subject = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btn_report_student_score = new javax.swing.JButton();
        txt_masv = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REPORT");

        back_btn.setText("Back");

        jLabel2.setText("Mã lớp môn học");

        jLabel3.setText("Danh sách sinh viên của lớp môn học");

        jLabel4.setText("Bảng điểm lớp môn học");

        cbx_maLopMh1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbx_maLopMh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_maLopMh1ActionPerformed(evt);
            }
        });

        btn_report_class_subject.setText("Thống kê");
        btn_report_class_subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_report_class_subjectActionPerformed(evt);
            }
        });

        btn_report_scores_class_subject.setText("Thống kê");
        btn_report_scores_class_subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_report_scores_class_subjectActionPerformed(evt);
            }
        });

        jLabel5.setText("Bảng điểm của sinh viên");

        btn_report_student_score.setText("Thống kê");
        btn_report_student_score.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_report_student_scoreActionPerformed(evt);
            }
        });

        jLabel6.setText("Mã sinh viên");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back_btn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(332, 332, 332)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbx_maLopMh1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_masv, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_report_class_subject)
                    .addComponent(btn_report_scores_class_subject)
                    .addComponent(btn_report_student_score))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(174, 174, 174))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel2)
                        .addGap(5, 5, 5))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(back_btn)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_report_class_subject))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_report_scores_class_subject)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(cbx_maLopMh1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(85, 85, 85)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(btn_report_student_score)
                    .addComponent(txt_masv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbx_maLopMh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_maLopMh1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_maLopMh1ActionPerformed

    private void btn_report_class_subjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_report_class_subjectActionPerformed
        String maLopMH = cbx_maLopMh1.getSelectedItem().toString();        
        ViewReportClassSubject form = new ViewReportClassSubject(maLopMH);
        form.setVisible(true);
    }//GEN-LAST:event_btn_report_class_subjectActionPerformed

    private void btn_report_scores_class_subjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_report_scores_class_subjectActionPerformed
          String maLopMH = cbx_maLopMh1.getSelectedItem().toString();        
        ViewReportClassSubjectScores form = new ViewReportClassSubjectScores(maLopMH);
        form.setVisible(true);
    }//GEN-LAST:event_btn_report_scores_class_subjectActionPerformed

    private void btn_report_student_scoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_report_student_scoreActionPerformed
       String maSV = txt_masv.getText().toString();
       ViewReportStudentScores form = new ViewReportStudentScores(maSV);
       form.setVisible(true);
    }//GEN-LAST:event_btn_report_student_scoreActionPerformed

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
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Report().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back_btn;
    private javax.swing.JButton btn_report_class_subject;
    private javax.swing.JButton btn_report_scores_class_subject;
    private javax.swing.JButton btn_report_student_score;
    private javax.swing.JComboBox<String> cbx_maLopMh1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txt_masv;
    // End of variables declaration//GEN-END:variables
}
