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
    
    public BBoardBean(){
        id = 0;
        pid = 0;
    }
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
    
    public boolean validate(){
        if(!validateStr(name))
            return false;
        if(pid < 0)
            return false;
        return true;
    }
    
    private boolean validateStr(String str){
        if(str == null || str.equals(""))
            return false;
        else
            return true;
    }
}
