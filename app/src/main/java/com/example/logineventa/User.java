package com.example.logineventa;

public class User {
    int RollNumber;
    String Password;

    public User() {
    }

    public User(int RollNumber, String Password) {
        this.RollNumber = RollNumber;
        this.Password = Password;
    }

    public int getRollNumber() {
        return RollNumber;
    }

    public void setRollNumber(int RollNumber) {
        this.RollNumber = RollNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}
