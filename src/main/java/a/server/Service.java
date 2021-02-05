/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.server;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Alexandru
 */
public class Service {
    
    private DBAccess db;
    
    public Service() throws ClassNotFoundException, SQLException {
        db = new DBAccess();
    }
    
    
    public synchronized String addBook(String name, double pret, String autor, int unitati) throws SQLException{
        BooksEntity books = db.findBookByName(name);
        AutoriEntity autori = db.findAutorbyName(autor);
        if (books == null){
            BooksEntity b = new BooksEntity(name, pret, autor, unitati);
            if(autori == null){
                db.insertBook(b);
                db.insertAuthor(autor);
            } else {
                db.insertBook(b);
            }
            return "Added!";
        } else {
            return "Already exist!";
        }
                       
    }
    
    public synchronized String addUnits(String name, int units) throws SQLException{
        BooksEntity books = db.findBookByName(name);
        if(books == null){
            return "Doesn't exist!";
        } else {
            db.updateUnits(name, units);
           return "Added!"; 
           }
    }
    
    public synchronized int getAllBooks() throws SQLException{
        int all = db.cartiInInvetar();
        
        return all;
    }
    
    public synchronized int getAllAuthors() throws SQLException{
        int all = db.autoriUnici();
        return all;
    }
    
    public synchronized double getTotalSum() throws SQLException{
        double total = db.totalSum();
        return total;
    }

    public synchronized BooksEntity findBookByName(String name)throws SQLException{
        if (db.findBookByName(name) == null){
            return null;
        } else {
                return db.findBookByName(name);
        }
    }
    
    public synchronized double insertVanzare(String numeCarte, int bucati, int discount, double pretVanzare, Date date)throws SQLException{
       return db.insertVanzare(numeCarte, bucati, discount, pretVanzare, date);
    }
    
    public synchronized double getTotalVanzari()throws SQLException{
        double totalVanzari = db.totalVanzari();
        return totalVanzari;
    }
}
