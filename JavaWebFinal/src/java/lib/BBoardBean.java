/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

/**
 *
 * @author Ambulong
 */
public class BBoardBean implements java.io.Serializable{
    private int id;
    private String name;
    private int pid;
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setName(String str){
        this.name = str.trim();
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setPid(int pid){
        this.pid = pid;
    }
    
    public int getPid(){
        return this.pid;
    }
}
