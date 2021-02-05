/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.server;

import java.util.Date;

/**
 *
 * @author Alexandru
 */
public class VanzariEntity {
    private double vanzari;
    private Date data_vanzare;

    public VanzariEntity(double vanzari, Date data_vanzare) {
        this.vanzari = vanzari;
        this.data_vanzare = data_vanzare;
    }

    public void setData_vanzare(Date data_vanzare) {
        this.data_vanzare = data_vanzare;
    }

    public Date getData_vanzare() {
        return data_vanzare;
    }

    public double getVanzari() {
        return vanzari;
    }

    public void setVanzari(double vanzari) {
        this.vanzari = vanzari;
    }
    
    @Override
    public String toString() {
        return "VanzariEntity{" + "vanzari=" + vanzari + ", data_vanzare=" + data_vanzare + '}';
    }
}
