/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Alexandru
 */
public class NetConnector {
    
    Service service;
    public NetConnector() throws ClassNotFoundException, SQLException {
        service = new Service();
    }
    
    public void startServer(){
        try{
            ServerSocket ss = new ServerSocket(4050);
            
            while(true){
                System.out.println("Astept conexiune cu clientul!");
                Socket s = ss.accept();
                System.out.println("Conexiune stabilita");
                BufferedReader fluxIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter fluxOut = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
                
                String actiune = fluxIn.readLine();
                if(actiune.equals("Start")){
                fluxOut.println(service.getAllBooks());
                fluxOut.println(service.getAllAuthors());
                fluxOut.println(service.getTotalSum());
                }
                                              
                else if(actiune.equals("Adaugare")){
                
                String numeCarte = fluxIn.readLine();
                double pretCarte = Double.parseDouble(fluxIn.readLine());
                String numeAutor = fluxIn.readLine();
                int unitati = Integer.parseInt(fluxIn.readLine());
                String result = service.addBook(numeCarte,pretCarte, numeAutor, unitati);
                
                fluxOut.println(result);
                fluxOut.println(service.getAllBooks());
                fluxOut.println(service.getAllAuthors());
                fluxOut.println(service.getTotalSum());
                
                } else if(actiune.equals("Verificare")){
                  String numeCarte = fluxIn.readLine();
                  int result = service.findBookByName(numeCarte).getUnitati();
                  fluxOut.println(result);
                  
                } else if(actiune.equals("Vanzare")){
                  String numeCarte = fluxIn.readLine();
                  int unitatiDisponibile = Integer.parseInt(fluxIn.readLine());
                  double pretVanzare = Double.parseDouble(fluxIn.readLine());
                  int discount = Integer.parseInt(fluxIn.readLine());
                  Date in = new Date(System.currentTimeMillis());
                  
                  double result = service.insertVanzare(numeCarte,unitatiDisponibile, discount, pretVanzare, in);
                  fluxOut.println(result);
                  fluxOut.println(service.getAllBooks());
                  fluxOut.println(service.getAllAuthors());
                  fluxOut.println(service.getTotalSum());
                  
                }
                
                
                s.close();
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        NetConnector netCon = new NetConnector();
        netCon.startServer();
    }
    
}
