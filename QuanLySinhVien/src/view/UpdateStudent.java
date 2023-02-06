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
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Student;

/**
 *
 * @author quang
 */
public class UpdateStudent extends javax.swing.JFrame {

    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private final ConnectDatabase connectDB = new ConnectDatabase();

    private String sql = "SELECT * FROM SINHVIEN";

    /**
     * Creates new form UpdateStudent
     */
    public UpdateStudent() {
        initComponents();
        //loadData(sql);    
        show_student();
        btnAction();
    }

    public ArrayList<Student> studentList() {
        ArrayList<Student> studentList = new ArrayList<>();
        ResultSet rs = null;
        Statement stm = null;
        try {

            conn = connectDB.getDBConnect();
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            Student std;
            while (rs.next()) {
                std = new Student(
                        rs.getString("masv"),
                        rs.getString("hoten"),
                        rs.getString("ngaysinh"),
                        rs.getString("gioitinh"),
                        rs.getString("diachi"),
                        rs.getString("lopql"),
                        rs.getString("email"),
                        rs.getString("makhoa")
                );
                studentList.add(std);
            }   
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return studentList;
    }

    public void show_student() {
        ArrayList<Student> list = studentList();
        DefaultTableModel model = (DefaultTableModel) tableStudent.getModel();
        Object[] row = new Object[8];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getMasv();
            row[1] = list.get(i).getHoten();
            row[2] = list.get(i).getNgaysinh();
            row[3] = list.get(i).getGioitinh();
            row[4] = list.get(i).getDiachi();
            row[5] = list.get(i).getLopql();
            row[6] = list.get(i).getEmail();
            row[7] = list.get(i).getMakhoa();
            model.addRow(row);
        }
    }

    public void reset() {
        txt_masv.setText("");
        txt_tensv.setText("");
        txt_ngaysinh.setText("");
        txt_gioitinh.setText("");
        txt_diachi.setText("");
        txt_lopql.setText("");
        txt_email.setText("");
        txt_makhoa.setText("");
    }

    private void connection() {
        conn = connectDB.getDBConnect();
    }

    public int add(Student st) {
        try {
            String sSQL = "insert SINHVIEN(MaSV,HoTen,NgaySinh,GioiTinh,DiaChi,LopQL,Email,MaKhoa)";
        } catch (Exception e) {
        }
        return 1;
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
        btn_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sSQL = "select * from SINHVIEN where MaSV like N'" + txt_search.getText() + "' ;";
                try {
                    conn = connectDB.getDBConnect();
                    pst = conn.prepareStatement(sSQL);                          
                    pst.executeQuery();
                   reset();           
                    System.out.println("Thanh cong");
//                    UpdateStudent update = new UpdateStudent();
//                    update.setVisible(true);
                    studentList();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sSQL = "insert into SINHVIEN(MaSV,HoTen,NgaySinh,GioiTinh,DiaChi,LopQL,Email,MaKhoa)values(?,?,?,?,?,?,?,?)";
                try {
                    conn = connectDB.getDBConnect();
                    pst = conn.prepareStatement(sSQL);
                    pst.setString(1, txt_masv.getText());
                    pst.setString(2, txt_tensv.getText());
                    pst.setString(3, txt_ngaysinh.getText());
                    pst.setString(4, txt_gioitinh.getText());
                    pst.setString(5, txt_diachi.getText());
                    pst.setString(6, txt_lopql.getText());
                    pst.setString(7, txt_email.getText());
                    pst.setString(8, txt_makhoa.getText());
                    pst.executeUpdate();
                    reset();
                    dispose();
                    UpdateStudent update = new UpdateStudent();
                    update.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btn_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sSQL = "update SINHVIEN set HoTen = ?,NgaySinh = ?,GioiTinh = ?,DiaChi = ?,LopQL = ?,Email = ?,MaKhoa =? where MaSV = '" + txt_masv.getText() + "'";
                try {
                    conn = connectDB.getDBConnect();
                    pst = conn.prepareStatement(sSQL);
                    pst.setString(1, txt_tensv.getText());
                    pst.setString(2, txt_ngaysinh.getText());
                    pst.setString(3, txt_gioitinh.getText());
                    pst.setString(4, txt_diachi.getText());
                    pst.setString(5, txt_lopql.getText());
                    pst.setString(6, txt_email.getText());
                    pst.setString(7, txt_makhoa.getText());
                    pst.executeUpdate();
                    reset();
                    dispose();
                    UpdateStudent update = new UpdateStudent();
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
                int Click = tableStudent.getSelectedRow();
                TableModel model = tableStudent.getModel();
                int a = JOptionPane.showConfirmDialog(null, "Xác nhận xóa ?");
                if (a == JOptionPane.YES_OPTION) {
                    String sqlDelete = "delete SINHVIEN where MaSV = '" + txt_masv.getText() + "' ";

                    try {
                        // pst.setString(1, txt_masv.getText());
                        System.out.println("Delete success");
                        pst = conn.prepareStatement(sqlDelete);
                        pst.executeUpdate();
                        reset();
                        dispose();
                        UpdateStudent update = new UpdateStudent();
                        update.setVisible(true);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (a == JOptionPane.NO_OPTION) {
                    show_student();;
                }

            }
        });
        tableStudent.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int Click = tableStudent.getSelectedRow();
                TableModel model = tableStudent.getModel();

                // txt_stt.setText(model.getValueAt(Click, 0).toString());
                txt_masv.setText(model.getValueAt(Click, 0).toString());
                txt_tensv.setText(model.getValueAt(Click, 1).toString());
                txt_ngaysinh.setText(model.getValueAt(Click, 2).toString());
                txt_gioitinh.setText(model.getValueAt(Click, 3).toString());
                txt_diachi.setText(model.getValueAt(Click, 4).toString());
                txt_lopql.setText(model.getValueAt(Click, 5).toString());
                txt_email.setText(model.getValueAt(Click, 6).toString());
                txt_makhoa.setText(model.getValueAt(Click, 7).toString());

                btn_update.setEnabled(true);
                btn_delete.setEnabled(true);

            }
        });
    }

//    private void loadData(String sql) {
//        tableStudent.removeAll();
//        try {
//            String[] arr = {"Mã sinh viên", "Tên sinh viên", "Ngày sinh", "Giới tính", "Địa chỉ", "Lớp quản lý", "Email", "Mã khoa"};
//            DefaultTableModel modle = new DefaultTableModel(arr, 0);
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//            while (rs.next()) {
//                Vector vector = new Vector();
//                vector.add(rs.getString("MaSV").trim());
//                vector.add(rs.getString("HoTen").trim());
//                vector.add(rs.getString("NgaySinh").trim());
//                vector.add(rs.getString("GioiTinh").trim());
//                vector.add(rs.getString("DiaChi"));
//                vector.add(rs.getString("LopQL").trim());
//                vector.add(rs.getString("Email").trim());
//                vector.add(rs.getString("MaKhoa").trim());
//                modle.addRow(vector);
//            }
//            tableStudent.setModel(modle);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
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
        txt_masv = new java.awt.TextField();
        jLabel3 = new javax.swing.JLabel();
        txt_tensv = new java.awt.TextField();
        jLabel4 = new javax.swing.JLabel();
        txt_ngaysinh = new java.awt.TextField();
        jLabel5 = new javax.swing.JLabel();
        txt_gioitinh = new java.awt.TextField();
        jLabel6 = new javax.swing.JLabel();
        txt_lopql = new java.awt.TextField();
        jLabel7 = new javax.swing.JLabel();
        txt_email = new java.awt.TextField();
        jLabel8 = new javax.swing.JLabel();
        txt_makhoa = new java.awt.TextField();
        txt_diachi = new java.awt.TextField();
        jLabel9 = new javax.swing.JLabel();
        btn_update = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableStudent = new javax.swing.JTable();
        btn_back = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cập nhật dữ liệu sinh viên");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mã sinh viên");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tên sinh viên");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Ngày sinh");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Giới tính");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Lớp quản lý");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Email");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Mã khoa");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Địa chỉ");

        btn_update.setText("Sửa");

        btn_save.setText("Lưu");

        btn_add.setText("Thêm");

        btn_delete.setText("Xóa");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        tableStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sinh viên", "Tên sinh viên", "Ngày sinh", "Giới tính", "Địa chỉ", "Lớp quản lý", "Email", "Mã khoa"
            }
        ));
        jScrollPane2.setViewportView(tableStudent);

        btn_back.setText("Trở về");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        btn_search.setText("Tìm kiếm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_diachi, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_lopql, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_makhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(btn_search))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_masv, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(80, 80, 80)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_tensv, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(118, 118, 118)
                                        .addComponent(jLabel5)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_gioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_search))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_masv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_tensv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_ngaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_gioitinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_diachi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_lopql, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_makhoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_backActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateStudent().setVisible(true);
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableStudent;
    private java.awt.TextField txt_diachi;
    private java.awt.TextField txt_email;
    private java.awt.TextField txt_gioitinh;
    private java.awt.TextField txt_lopql;
    private java.awt.TextField txt_makhoa;
    private java.awt.TextField txt_masv;
    private java.awt.TextField txt_ngaysinh;
    private javax.swing.JTextField txt_search;
    private java.awt.TextField txt_tensv;
    // End of variables declaration//GEN-END:variables
}
