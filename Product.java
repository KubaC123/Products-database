package Products;

import java.sql.Date;

public class Product 
{
    private int id;
    private String name;
    private float price;
    private int amount;
    private String modifyDate;
    
    /**
     * Specifies the amount considered as small.
     */
    public static int smallAmount = 50;

    public Product(int id, String name, float price, int amount, String modifyDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.modifyDate = modifyDate;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
    
}
