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
public class AutoriEntity {
    private String name;
    
    public AutoriEntity(){
        
    }
    public AutoriEntity(String name){
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final AutoriEntity other = (AutoriEntity) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
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
