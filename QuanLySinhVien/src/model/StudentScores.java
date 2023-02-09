/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author quang
 */
public class StudentScores {
    String  tenMH, diem,diemhe4,diemChu;

    public StudentScores(String masv, String hoten, String tenMH, String diem, String diemhe4, String diemChu) {      
        this.tenMH = tenMH;
        this.diem = diem;
        this.diemhe4 = diemhe4;
        this.diemChu = diemChu;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public String getDiem() {
        return diem;
    }

    public void setDiem(String diem) {
        this.diem = diem;
    }

    public String getDiem4() {
        return diemhe4;
    }

    public void setDiem4(String diem4) {
        this.diemhe4 = diem4;
    }

    public String getDiemChu() {
        return diemChu;
    }

    public void setDiemChu(String diemChu) {
        this.diemChu = diemChu;
    }
    
    
}
