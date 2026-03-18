package com.example.contactadvancedemotry1;

import androidx.annotation.NonNull;

public class User {
    private int id;
    private String Name;
    private String PhoneNum;
    private String imgUri;
    private Boolean Status;

    public User() {
    }

    public User(int id, String imgUri, String name, String phoneNum, Boolean status) {
        this.id = id;
        this.imgUri = imgUri;
        Name = name;
        PhoneNum = phoneNum;
        Status = status;
    }

    public User(int id, String imgUri, String name, String phoneNum) {
        this.id = id;
        this.imgUri = imgUri;
        Name = name;
        PhoneNum = phoneNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    @NonNull
    @Override
    public String toString(){
        return getName() + " " + getPhoneNum() + " " + getStatus();
    }


}
