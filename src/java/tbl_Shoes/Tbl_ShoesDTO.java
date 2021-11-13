/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbl_Shoes;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Tbl_ShoesDTO implements Serializable{

    private String shoesId;
    private String shoesName;
    private float price;
    private String description;
    private int quantity;
    private String image;
    private boolean notSale;

    public Tbl_ShoesDTO() {
    }

    public Tbl_ShoesDTO(String shoesId, String shoesName, float price, String description, int quantity, String image, boolean notSale) {
        this.shoesId = shoesId;
        this.shoesName = shoesName;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.image = image;
        this.notSale = notSale;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isNotSale() {
        return notSale;
    }

    public void setNotSale(boolean notSale) {
        this.notSale = notSale;
    }

    @Override
    public String toString() {
        return "Tbl_ShoesDTO{" + "shoesId=" + shoesId + ", shoesName=" + shoesName + ", price=" + price + ", description=" + description + ", quantity=" + quantity + ", image=" + image + ", notSale=" + notSale + '}';
    }

    
    
}
