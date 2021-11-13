/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tbl_Order;

/**
 *
 * @author Admin
 */
public class Tbl_OrderDTO {
    private String userId;
    private float total;

    public Tbl_OrderDTO() {
    }

    public Tbl_OrderDTO(String userId, float total) {
        this.userId = userId;
        this.total = total;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

   
}
