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
public class BUserBean implements java.io.Serializable {

    private int id;
    private String username;
    private String password;
    private int age;
    private String regtime;
    private String head;
    private int gender;
    private String gendertoString;
    private int flag;
    
    public BUserBean(){
        this.age = -1;
        this.gender = -1;
        this.flag = 2; //normal user
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setUsername(String str) {
        this.username = str.trim();
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void setRegtime(String str) {
        this.regtime = str.trim();
    }

    public String getRegtime() {
        return this.regtime;
    }

    public void setHead(String str) {
        this.head = str.trim();
    }

    public String getHead() {
        return this.head;
    }

    public void setGender(int gender) {
        this.gender = gender;
        if (gender == 0) {
            this.gendertoString = "男";
        } else if (gender == 1) {
            this.gendertoString = "女";
        } else {
            this.gendertoString = "其它";
        }
    }

    public int getGender() {
        return this.gender;
    }

    public String getGendertoString() {
        return this.gendertoString;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return this.flag;
    }

    public boolean validate() {
        if (!validateStr(username) || !chkName(username)) {
            System.out.println("username: "+username);
            return false;
        }
        if (!validateStr(password) || password.length() <= 6) {
            System.out.println("password "+password);
            return false;
        }
        if (age < 0) {
            System.out.println("age: "+age);
            return false;
        }
        if (!validateStr(head)) {
            System.out.println("head: "+head);
            return false;
        }
        if (gender < 0) {
            System.out.println("gender: "+gender);
            return false;
        }
        return true;
    }
    
    public boolean validateProfile() {
        if (age < 0) {
            System.out.println("age: "+age);
            return false;
        }
        if (!validateStr(head)) {
            System.out.println("head: "+head);
            return false;
        }
        if (gender < 0) {
            System.out.println("gender: "+gender);
            return false;
        }
        return true;
    }

    private boolean validateStr(String str) {
        if (str == null || str.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    private boolean chkName(String str) {
        if (str.length() <= 6 || str.length() >= 12) {
            return false;
        }
        if (!str.matches("^[0-9a-zA-Z]*")) {
            return false;
        }
        return true;
    }
}
