/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbl_Users;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Tbl_UsersDTO implements Serializable{
    private String userId;
    private String password;
    private String fullName;
    private int phone;
    private String address;
    private int role;

    public Tbl_UsersDTO() {
    }

    public Tbl_UsersDTO(String userId, String password, String fullName, int phone, String address, int role) {
        this.userId = userId;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }  

    @Override
    public String toString() {
        return "Tbl_UsersDTO{" + "userId=" + userId + ", password=" + password + ", fullName=" + fullName + ", phone=" + phone + ", address=" + address + ", role=" + role + '}';
    }
}
