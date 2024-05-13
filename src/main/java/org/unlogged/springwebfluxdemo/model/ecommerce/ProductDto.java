package org.unlogged.springwebfluxdemo.model.ecommerce;

public class ProductDto {

    private String productId;
    private String productName;
    private String brand;
    private int countInStock;
    private double pricePerProduct;

    public ProductDto(String productId, String productName, String brand, int countInStock, double pricePerProduct) {
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.countInStock = countInStock;
        this.pricePerProduct = pricePerProduct;
    }

    public ProductDto() {

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCountInStock() {
        return countInStock;
    }

    public void setCountInStock(int countInStock) {
        this.countInStock = countInStock;
    }

    public double getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(double pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", brand='" + brand + '\'' +
                ", countInStock=" + countInStock +
                ", pricePerProduct=" + pricePerProduct +
                '}';
    }
}
