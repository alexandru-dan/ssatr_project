/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.server;

import java.util.Objects;

/**
 *
 * @author Alexandru
 */
public class BooksEntity {
    private String name;
    private double price;
    private String autor;
    private int unitati;
    
    public BooksEntity(String name, double price, String autor, int unitati){
        this.name = name;
        this.price = price;
        this.autor = autor;
        this.unitati = unitati;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BooksEntity other = (BooksEntity) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    public void setUnitati(int unitati) {
        this.unitati = unitati;
    }

    public int getUnitati() {
        return unitati;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }
    
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public double getPrice(){
        return price;
    }
    
    public void setPrice(double price){
        this.price = price;
    }

    @Override
    public String toString() {
        return "BooksEntity{ " + "id = " + " , name = " + name + ", price =" + price + "}";
    }    
    
}
