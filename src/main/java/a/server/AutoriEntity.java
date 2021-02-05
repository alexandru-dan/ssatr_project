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
public class AutoriEntity {
    private String name;
    
    public AutoriEntity(){
        
    }
    public AutoriEntity(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "AutoriEntity{" + ", name=" + name + '}';
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
    
}
