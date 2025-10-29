package com.example.tsrstore;

public class Homeproductmodel {
    String id,shopname,shopnumber,shopcategory,availableproducts,productname,price,location,description,image;

    public Homeproductmodel(String id, String shopname,String shopnumber, String shopcategory, String availableproducts, String productname, String price, String location, String description, String image) {
        this.id = id;
        this.shopname = shopname;
        this.shopnumber = shopnumber;
        this.shopcategory = shopcategory;
        this.availableproducts = availableproducts;
        this.productname = productname;
        this.price = price;
        this.location = location;
        this.description = description;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getShopname() {
        return shopname;
    }

    public String getShopnumber() {
        return shopnumber;
    }

    public String getShopcategory() {
        return shopcategory;
    }

    public String getAvailableproducts() {
        return availableproducts;
    }

    public String getProductname() {
        return productname;
    }

    public String getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    private boolean isFavorite;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    // Other properties like product name, price, etc.
}

