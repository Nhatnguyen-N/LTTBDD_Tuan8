package com.example.appdangnhap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String email;
    private String password;
    private int countHappy;
    private int countSimple;
    private int countSad;

    public User(){}


    public User(int id, String email, String password, int countHappy, int countSimple, int countSad) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.countHappy = countHappy;
        this.countSimple = countSimple;
        this.countSad = countSad;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCountHappy() {
        return countHappy;
    }

    public void setCountHappy(int countHappy) {
        this.countHappy = countHappy;
    }

    public int getCountSimple() {
        return countSimple;
    }

    public void setCountSimple(int countSimple) {
        this.countSimple = countSimple;
    }

    public int getCountSad() {
        return countSad;
    }

    public void setCountSad(int countSad) {
        this.countSad = countSad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
