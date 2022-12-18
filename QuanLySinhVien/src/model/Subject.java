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
public class Subject {
    private String mamh;
    private String tenmh;
    private String sotin;

    public Subject(String mamh, String tenmh, String sotin) {
        this.mamh = mamh;
        this.tenmh = tenmh;
        this.sotin = sotin;
    }

    public String getMamh() {
        return mamh;
    }

    public void setMamh(String mamh) {
        this.mamh = mamh;
    }

    public String getTenmh() {
        return tenmh;
    }

    public void setTenmh(String tenmh) {
        this.tenmh = tenmh;
    }

    public String getSotin() {
        return sotin;
    }

    public void setSotin(String sotin) {
        this.sotin = sotin;
    }
    
    
}
