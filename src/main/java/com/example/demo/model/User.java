package com.example.demo.model;

import com.aerospike.client.Record;

public class User {
    private String name;
    private String phone;

    public User(){
    }

    public User(Record record){
        this.name = record.getString("name");
        this.phone = record.getString("phone");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
