package com.mountreachsolution.rescuewings.SAVIOUR.POJO;

public class POJOSend {
    String id,username,loc,deta,nvusername,name,mobile,image,resend;

    public POJOSend(String id, String username, String loc, String deta, String nvusername, String name, String mobile, String image, String resend) {
        this.id = id;
        this.username = username;
        this.loc = loc;
        this.deta = deta;
        this.nvusername = nvusername;
        this.name = name;
        this.mobile = mobile;
        this.image = image;
        this.resend = resend;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getDeta() {
        return deta;
    }

    public void setDeta(String deta) {
        this.deta = deta;
    }

    public String getNvusername() {
        return nvusername;
    }

    public void setNvusername(String nvusername) {
        this.nvusername = nvusername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getResend() {
        return resend;
    }

    public void setResend(String resend) {
        this.resend = resend;
    }
}
