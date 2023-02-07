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
public class ClassSubject {
    String maSV,hoten,lopQL;

    public ClassSubject(String maSV, String hoten, String lopQL) {
        this.maSV = maSV;
        this.hoten = hoten;
        this.lopQL = lopQL;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getLopQL() {
        return lopQL;
    }

    public void setLopQL(String lopQL) {
        this.lopQL = lopQL;
    }
    
    
}
