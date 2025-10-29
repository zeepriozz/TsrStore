package com.example.tsrstore;

public class ProductModel {

    String id,shopid,shopname,shopnumber,shopcategory,availableproducts,productname,price,packagee,location,description,image;

    public ProductModel(String id,String shopid, String shopname,String shopnumber, String shopcategory ,String availableproducts, String productname, String price,String packagee, String location, String description, String image) {
        this.id = id;
        this.shopid= shopid;
        this.shopname = shopname;
        this.shopnumber = shopnumber;
        this.shopcategory = shopcategory;
        this.availableproducts = availableproducts;
        this.productname = productname;
        this.price = price;
        this.packagee = packagee;
        this.location = location;
        this.description = description;
        this.image = image;
    }

    public String getId() {
        return id;
    }
    public String getShopid() {
        return shopid;
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
    public String getPackagee() {
        return packagee;
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