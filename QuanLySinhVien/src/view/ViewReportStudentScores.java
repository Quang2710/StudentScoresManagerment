/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import database.ConnectDatabase;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.StudentScores;

/**
 *
 * @author quang
 */
public class ViewReportStudentScores extends javax.swing.JFrame {

    CallableStatement command = null;
    private Connection conn = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;
    private final ConnectDatabase connectDB = new ConnectDatabase();

    public ViewReportStudentScores(String maSV) {
        initComponents();
        show_StudentScore(maSV);
        setDataTxtField(maSV);
        setDataTxtField_DIEM_TB_TL(maSV);
    }

    public ArrayList<StudentScores> studentScoreList(String maSV) {
        ArrayList<StudentScores> studentScoreList = new ArrayList<>();
        ResultSet rs = null;
        CallableStatement command = null;
        try {
            conn = connectDB.getDBConnect();
            command = conn.prepareCall("{call BANG_DIEM_SV (?)}");
            command.setString(1, maSV);
            rs = command.executeQuery();
            StudentScores std;
            while (rs.next()) {
                std = new StudentScores(
                        rs.getString("maSV"),
                        rs.getString("hoten"),
                        rs.getString("tenMH"),
                        rs.getString("diem"),
                        rs.getString("diemhe4"),
                        rs.getString("diemChu")
                );
                studentScoreList.add(std);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                command.close();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return studentScoreList;
    }

    public void show_StudentScore(String maSV) {
        ArrayList<StudentScores> list = studentScoreList(maSV);
        DefaultTableModel model = (DefaultTableModel) tableStudentScore.getModel();
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getTenMH();
            row[1] = list.get(i).getDiem();
            row[2] = list.get(i).getDiem4();
            row[3] = list.get(i).getDiemChu();
            model.addRow(row);
        }
    }

    public void setDataTxtField(String maSV) {
        ResultSet rs = null;
        CallableStatement command = null;
        Statement stm = null;
        String sql = "SELECT * FROM SINHVIEN";
        try {
            conn = connectDB.getDBConnect();

            command = conn.prepareCall("{call BANG_DIEM_SV (?)}");
            command.setString(1, maSV);
            rs = command.executeQuery();
        //    stm = conn.createStatement();
        //    rs = stm.executeQuery(sql);
            if (rs.next()) {
                String masv = rs.getString("maSV");
                String hoten = rs.getString("hoten");
             //   String lopQL = rs.getString("lopQL");
                txt_masv.setText(masv);
                txt_hoten.setText(hoten);
               // txt_lopql.setText(lopQL);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void setDataTxtField_DIEM_TB_TL(String maSV) {
        ResultSet rs = null;
        CallableStatement command = null;
        Statement stm = null;
        try {
            conn = connectDB.getDBConnect();

            command = conn.prepareCall("{call DTB_TICHLUY (?)}");
            command.setString(1, maSV);
            rs = command.executeQuery();
      
            if (rs.next()) {
                String dtb = rs.getString("diemtb");         
                lb_dtb.setText(dtb);
               // txt_lopql.setText(lopQL);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tableStudentScore = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_masv = new javax.swing.JTextField();
        txt_hoten = new javax.swing.JTextField();
        lb_dtb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableStudentScore.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên môn học", "Điểm", "Điểm hệ 4", "Điểm chữ"
            }
        ));
        jScrollPane2.setViewportView(tableStudentScore);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bảng điểm của sinh viên");

        jLabel2.setText("Tên sinh viên");

        jLabel3.setText("Mã sinh viên");

        jLabel4.setText("Điểm trung bình tích lũy :");

        txt_hoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hotenActionPerformed(evt);
            }
        });

        lb_dtb.setText("jLabel6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(287, 287, 287)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_masv, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_hoten, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_dtb)
                                .addGap(241, 241, 241))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_hoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(lb_dtb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel3))
                    .addComponent(txt_masv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_hotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hotenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_dtb;
    private javax.swing.JTable tableStudentScore;
    private javax.swing.JTextField txt_hoten;
    private javax.swing.JTextField txt_masv;
    // End of variables declaration//GEN-END:variables
}
