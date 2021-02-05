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
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DBAccess {

    private Connection conn;

    public DBAccess() throws ClassNotFoundException, SQLException {
         Class.forName("org.apache.derby.jdbc.ClientDriver");
         conn = DriverManager.getConnection("jdbc:derby://localhost:1527/books;create=false","app","app");
             }
    
    public void insertBook(BooksEntity b) throws SQLException{
      Statement s = conn.createStatement();
      PreparedStatement prep = conn.prepareStatement("INSERT INTO BOOKS(NAME,PRICE,AUTOR,UNITATI) VALUES (?,?,?,?)");
      prep.setString(1, b.getName().toLowerCase());
      prep.setDouble(2, b.getPrice());
      prep.setString(3, b.getAutor().toLowerCase());
      prep.setInt(4, b.getUnitati());
      prep.executeUpdate();
      s.close();
    }
    
    public void insertAuthor(String name) throws SQLException{
        Statement s = conn.createStatement();
        PreparedStatement prep = conn.prepareStatement("INSERT INTO AUTORI(NAME) VALUES (?)");
        prep.setString(1, name.toLowerCase());
        prep.executeUpdate();
        s.close();
    }
    
    public double insertVanzare(String numeCarte, int bucati, int discount, double pretVanzare, Date date_) throws SQLException{
        Statement s = conn.createStatement();
        double pretVanzare_;
        PreparedStatement prep = conn.prepareStatement("INSERT INTO VANZARI(VANZARI, DATA_VANZARE) VALUES (?,?)");
        if(discount == 0){
          pretVanzare_ = pretVanzare * bucati;
        } else {
            pretVanzare_ = ((pretVanzare*bucati) * (100-discount))/100;
        }
        
        prep.setDouble(1, pretVanzare_);
        Date data = new Date(date_.getTime());
        prep.setDate(2, data);
        prep.executeUpdate();
        updateUnitati(numeCarte, bucati);
        return pretVanzare_;
    }
    
    public AutoriEntity findAutorbyName(String name) throws SQLException{
        Statement s = conn.createStatement();
        PreparedStatement prep = conn.prepareStatement("SELECT * FROM AUTORI WHERE NAME=?");
//        ResultSet rs = s.executeQuery("SELECT * FROM AUTORI WHERE NAME='"+ name +"'");
        prep.setString(1, name);
        ResultSet rs = prep.executeQuery();
        if (rs.next()){
            return new AutoriEntity(rs.getString("name"));
        } else
            return null;
    }
    
    public BooksEntity findBookByName(String name) throws SQLException{
        Statement s = conn.createStatement();
        PreparedStatement prep = conn.prepareStatement("SELECT * FROM BOOKS WHERE NAME=?");
//        ResultSet rs = s.executeQuery("SELECT * FROM AUTORI WHERE NAME='"+ name +"'");
        prep.setString(1, name);
        ResultSet rs = prep.executeQuery();
        if(rs.next()){
            return new BooksEntity(rs.getString("name"),rs.getDouble("price"),rs.getString("autor"),rs.getInt("unitati"));
        } else {
            return null;
        }
    }
    
    public int cartiInInvetar()throws SQLException{
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM BOOKS");
        int totalNumber = 0;
        while(rs.next()){
            totalNumber++;
        } 
        
        return totalNumber;
    }
    
    public int autoriUnici()throws SQLException{
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM AUTORI");
        int totalNumber = 0;
        while(rs.next()){
            totalNumber++;
        } 
        return totalNumber;
    }
    
    public double totalSum() throws SQLException{
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("Select * from BOOKS");
        int totalSum = 0;
        while (rs.next()){
            double price = rs.getDouble("PRICE");
            int units = rs.getInt("UNITATI");
            totalSum += (price*units);
        }
        return totalSum;
    }
    
    public void updateUnitati(String numeCarte, int unitati) throws SQLException{
        Statement s = conn.createStatement();
        int unitatiAvailable = unitatiAvailable(numeCarte);
        int unitati_update = unitatiAvailable - unitati;
        PreparedStatement prep = conn.prepareStatement("UPDATE BOOKS SET UNITATI = ? WHERE NAME = ?");
        prep.setInt(1, unitati_update);
        prep.setString(2,numeCarte);
        prep.executeUpdate();
        
        s.close();
    }
    
    public int unitatiAvailable(String numeCarte) throws SQLException{
        int unitati = 0;
        Statement s = conn.createStatement();
        PreparedStatement prep = conn.prepareStatement("SELECT * FROM BOOKS WHERE NAME=?");
        prep.setString(1, numeCarte);
        ResultSet rs = prep.executeQuery();
        while(rs.next()){
            unitati = rs.getInt("UNITATI");
        }
        
        return unitati;
    }
    
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
    }
    
}
