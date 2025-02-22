package com.mountreachsolution.rescuewings.SAVIOUR.POJO;

public class POJOOther {
    String id,name,mobile,email,gender,username,password,usertype,image;

    public POJOOther(String id, String name, String mobile, String email, String gender, String username, String password, String usertype, String image) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
