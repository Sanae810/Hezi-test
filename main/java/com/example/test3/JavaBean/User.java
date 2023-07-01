package com.example.test3.JavaBean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    //username :学号 name ：姓名
    private String name;
    private String ID;
    private String address;
    private int Status;
    //Status : 0==Student 1==Teacher 2==Administrator

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

}

