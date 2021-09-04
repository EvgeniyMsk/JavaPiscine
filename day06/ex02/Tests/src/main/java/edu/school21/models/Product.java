package edu.school21.models;

import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private int price;

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product tmp = (Product) obj;
        return  Objects.equals(id, tmp.id) &&
                Objects.equals(name, tmp.name) &&
                Objects.equals(price, tmp.price);
    }

    @Override
    public String toString() {
        return "Product {" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price + "}";
    }
}
