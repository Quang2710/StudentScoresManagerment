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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author quang
 */
public class UpdateScores extends javax.swing.JFrame {

    private Connection conn = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;
    private final ConnectDatabase connectDB = new ConnectDatabase();

    private String sql = "SELECT * FROM SINHVIEN";

    public UpdateScores(String maLopMH, String ki) {
        initComponents();
        btn_action(maLopMH,ki);
        viewTable(maLopMH, ki);
        setDataLb(maLopMH);
    }

    public void viewTable(String maLopMH, String ki) {
        Vector cols = new Vector();
        cols.addElement("Mã sinh viên");
        cols.addElement("Họ Tên");
        cols.addElement("Lớp quản lý");
        cols.addElement("Điểm quá trình");
        cols.addElement("Điểm thi");
        //tao du lieu
        Vector data = new Vector();
        String SQL = "select SINHVIEN.MaSV,HoTen, SINHVIEN.LopQL, KETQUA.DiemQT, KETQUA.DiemThi from SINHVIEN join KETQUA on SINHVIEN.MaSV = KETQUA.MaSV join LOPMH on KETQUA.MaMH = LOPMH.MaMH  join KITHI on KETQUA.KiThiID = KITHI.KiThiID where MaLopMH = '"+maLopMH+"'and Ki ='"+ki+"'";
        ResultSet rs = null;
        Statement stm = null;
        try {
            conn = connectDB.getDBConnect();
            stm = conn.createStatement();
            rs = stm.executeQuery(SQL);

            while (rs.next()) {
                Vector user = new Vector();
                user.addElement(rs.getString("MaSV"));
                user.addElement(rs.getString("HoTen"));
                user.addElement(rs.getString("LopQL"));
                user.addElement(rs.getInt("DiemQT"));
                user.addElement(rs.getString("DiemThi"));
                data.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tablesScore.setModel(new DefaultTableModel(data, cols));
    }

    public void btn_action(String maLopMH, String ki) {
        btn_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sSQL = "update KETQUA set DiemQT = ?,DiemThi = ? where MaSV = '" + txt_maSV.getText() + "'";
                try {
                    conn = connectDB.getDBConnect();
                    stm = conn.prepareStatement(sSQL);
                    stm.setString(1, txt_diemQT.getText());
                    stm.setString(2, txt_diemThi.getText());
                    stm.executeUpdate();
                    viewTable(maLopMH,ki);
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
                int Click = tablesScore.getSelectedRow();
                TableModel model = tablesScore.getModel();
                int a = JOptionPane.showConfirmDialog(null, "Xác nhận xóa ?");
                if (a == JOptionPane.YES_OPTION) {
                    String sqlDelete = "update KETQUA set DiemQT = '',DiemThi = '' where MaSV = '" + txt_maSV.getText() + "'";

                    try {
                        // pst.setString(1, txt_masv.getText());
                        System.out.println("Delete success");
                        stm = conn.prepareStatement(sqlDelete);
                        stm.executeUpdate();
                        viewTable(maLopMH,ki);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (a == JOptionPane.NO_OPTION) {
                    //  show_student();;
                }

            }
        });

        tablesScore.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int Click = tablesScore.getSelectedRow();
                TableModel model = tablesScore.getModel();

                txt_maSV.setText((model.getValueAt(Click, 0).toString()));
                if (model.getValueAt(Click, 3) == null || model.getValueAt(Click, 4) == null) {
                    txt_diemQT.setText("0");
                    txt_diemThi.setText("0");
                } else {
                    txt_diemQT.setText(model.getValueAt(Click, 3).toString());
                    txt_diemThi.setText(model.getValueAt(Click, 4).toString());
                }

            }
        });
    }

    public void setDataLb(String maLopMH) {
        ResultSet rs = null;
        CallableStatement command = null;
        Statement stm = null;
        String Sql = "  select TenMH,MaLopMH from MONHOC join LOPMH on MONHOC.MaMH = LOPMH.MaMH where MaLopMH = '" + maLopMH + "' ";
        try {
            conn = connectDB.getDBConnect();
            stm = conn.createStatement();
            rs = stm.executeQuery(Sql);

            if (rs.next()) {
                String lopMH = rs.getString("TenMH");
                String maLopmh = rs.getString("MaLopMH");
                lb_lopMH.setText(lopMH);
                lb_maLopMH.setText(maLopmh);
                // txt_lopql.setText(lopQL);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_back = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablesScore = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_diemQT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_diemThi = new javax.swing.JTextField();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lb_lopMH = new javax.swing.JLabel();
        lb_maLopMH = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_maSV = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_back.setText("Trở về");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        tablesScore.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sinh viên", "Tên sinh viên", "Lớp quản lý", "Điểm quá trình", "Điểm thi"
            }
        ));
        jScrollPane2.setViewportView(tablesScore);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cập nhật điểm ");

        txt_diemQT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_diemQTActionPerformed(evt);
            }
        });

        jLabel2.setText("Điểm quá trình");

        jLabel3.setText("Điểm thi");

        btn_update.setText("Sửa");

        btn_delete.setText("Xóa");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Lớp môn học :");

        lb_lopMH.setText("lb_lopMh");

        lb_maLopMH.setText("lb_maLopMH");

        jLabel5.setText("Mã sinh viên");

        txt_maSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maSVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(420, 420, 420)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lb_lopMH, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_maLopMH, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_diemQT)
                                    .addComponent(txt_diemThi, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_maSV))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_lopMH)
                            .addComponent(lb_maLopMH))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_maSV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_diemQT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_diemThi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(137, 137, 137)
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        System.out.println("BACK");
        dispose();
        UpdateScore_Select_Class_Subject update = new UpdateScore_Select_Class_Subject();
        update.setVisible(true);
    }//GEN-LAST:event_btn_backActionPerformed

    private void txt_diemQTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_diemQTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_diemQTActionPerformed

    private void txt_maSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maSVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maSVActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_lopMH;
    private javax.swing.JLabel lb_maLopMH;
    private javax.swing.JTable tablesScore;
    private javax.swing.JTextField txt_diemQT;
    private javax.swing.JTextField txt_diemThi;
    private javax.swing.JTextField txt_maSV;
    // End of variables declaration//GEN-END:variables
}
