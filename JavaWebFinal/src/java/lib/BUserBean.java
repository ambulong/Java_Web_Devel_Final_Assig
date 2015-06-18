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
public class BUserBean implements java.io.Serializable{
    private int id;
    private String username;
    private String password;
    private int age;
    private String regtime;
    private String head;
    private int gender;
    private String gendertoString;
    private int flag;
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setUsername(String str){
        this.username = str.trim();
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public void setPassword(String str){
        this.password = str;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setAge(int age){
        this.age = age;
    }
    
    public int getAge(){
        return this.age;
    }
    
    public void setRegtime(String str){
        this.regtime = str.trim();
    }
    
    public String getRegtime(){
        return this.regtime;
    }
    
    public void setHead(String str){
        this.head = str.trim();
    }
    
    public String getHead(){
        return this.head;
    }
    
    public void setGender(int gender){
        this.gender = gender;
        if(gender == 0)
            this.gendertoString = "男";
        else if(gender == 1)
            this.gendertoString = "女";
        else
            this.gendertoString = "其它";
    }
    
    public int getGender(){
        return this.gender;
    }
    
    public String getGendertoString(){
        return this.gendertoString;
    }
    
    public void setFlag(int flag){
        this.flag = flag;
    }
    
    public int getFlag(){
        return this.flag;
    }
    
    public boolean validate(){
        if(!validateStr(username))
            return false;
        if(!validateStr(password) || password.length() <= 6)
            return false;
        if(age < 0)
            return false;
        if(!validateStr(head))
            return false;
        if(gender < 0)
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
