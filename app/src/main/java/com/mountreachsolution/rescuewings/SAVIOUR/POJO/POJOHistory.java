package com.mountreachsolution.rescuewings.SAVIOUR.POJO;

public class POJOHistory {
    String id,svusername,location,details,nvusername,name,mobile,image,status;

    public POJOHistory(String id, String svusername, String location, String details, String nvusername, String name, String mobile, String image, String status) {
        this.id = id;
        this.svusername = svusername;
        this.location = location;
        this.details = details;
        this.nvusername = nvusername;
        this.name = name;
        this.mobile = mobile;
        this.image = image;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSvusername() {
        return svusername;
    }

    public void setSvusername(String svusername) {
        this.svusername = svusername;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
