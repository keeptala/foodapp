package Model;

import java.io.Serializable;

public class FoodDomain implements Serializable {
    private String title;
    private int pic;
    private String description;
    private float fee;
    private int numberIntCart;

    public FoodDomain(String title, int pic, String description, float fee) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
    }

    public FoodDomain(String title, int pic, String description, float fee, int numberIntCart) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
        this.numberIntCart = numberIntCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public int getNumberIntCart() {
        return numberIntCart;
    }

    public void setNumberIntCart(int numberIntCart) {
        this.numberIntCart = numberIntCart;
    }
}
