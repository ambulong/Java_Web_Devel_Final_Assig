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
public class BReplyBean implements java.io.Serializable{
    private int id;
    private String title;
    private String content;
    private String pubtime;
    private int uid;
    private int bid;
    private String realfile;
    private String makefile;
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setTitle(String str){
        this.title = str.trim();
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public void setContent(String str){
        this.content = str;
    }
    
    public String getContent(){
        return this.content;
    }
    
    public void setPubtime(String str){
        this.pubtime = str.trim();
    }
    
    public String getPubtime(){
        return this.pubtime;
    }
    
    public void setUid(int uid){
        this.uid = uid;
    }
    
    public int getUid(){
        return this.uid;
    }
    
    public void setBid(int bid){
        this.bid = bid;
    }
    
    public int getBid(){
        return this.bid;
    }
    
    public void setRealfile(String str){
        this.realfile = str;
    }
    
    public String getRealfile(){
        return this.realfile;
    }
    
    public void setMakefile(String str){
        this.makefile = str;
    }
    
    public String getMakefile(){
        return this.makefile;
    }
    
    public boolean validate(){
        if(!validateStr(title))
            return false;
        if(!validateStr(content))
            return false;
        if(uid <= 0 || bid <= 0)
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
