/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientPackage;

/**
 *
 * @author navid
 */
public class Product {
    
    protected String name;
    protected String category;
    protected String measurement;
    protected double price;
    protected double minimumQuantity;
    protected double quantity;

    public Product(String name, String category, String measurement, double price, double minimumQuantity, double quantity) {
        setName(name);
        setCategory(category);
        setMeasurement(measurement);
        setPrice(price);
        setMinimumQuantity(minimumQuantity);
        setQuantity(quantity);
    }

    public Product() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(double minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
   
    @Override
    public String toString(){
        return  name + ":" +
                category + ":" +
                measurement + ":" +
                String.format("%f", price) + ":" +
                String.format("%f", minimumQuantity) + ":" +
                String.format("%f", quantity);
    }

    
    
}
