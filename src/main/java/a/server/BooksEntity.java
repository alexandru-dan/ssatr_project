/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.server;

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
