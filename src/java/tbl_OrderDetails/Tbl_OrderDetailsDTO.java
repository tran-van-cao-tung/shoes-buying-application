/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbl_OrderDetails;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Tbl_OrderDetailsDTO implements Serializable{

    private String orderId;
    private String shoesId;
    private String shoesName;
    private int orderQuantity;
    private float price;
    private String images;   

    public Tbl_OrderDetailsDTO() {
    }

    public Tbl_OrderDetailsDTO(String orderId, String shoesId, String shoesName, int orderQuantity, float price, String images) {
        this.orderId = orderId;
        this.shoesId = shoesId;
        this.shoesName = shoesName;
        this.orderQuantity = orderQuantity;
        this.price = price;
        this.images = images;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShoesId() {
        return shoesId;
    }

    public void setShoesId(String shoesId) {
        this.shoesId = shoesId;
    }

    public String getShoesName() {
        return shoesName;
    }

    public void setShoesName(String shoesName) {
        this.shoesName = shoesName;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    
    

    
}
