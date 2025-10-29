package com.example.tsrstore;

public class wishlistmodel {
    String id,userid,username,usernumber,shopname,shopid,productname,price,image,productid,productpack,description;

    public wishlistmodel(String id,String userid, String username, String usernumber, String shopname, String shopid, String productname, String price, String image,String productid,String productpack,String description) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.usernumber = usernumber;
        this.shopname = shopname;
        this.shopid = shopid;
        this.productname = productname;
        this.price = price;
        this.image = image;
        this.productid = productid;
        this.productpack = productpack;
        this.description = description;
    }
    public String getId() {
        return id;
    }
    public String getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getUsernumber() {
        return usernumber;
    }

    public String getShopname() {
        return shopname;
    }

    public String getShopid() {
        return shopid;
    }

    public String getProductname() {
        return productname;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getProductid() {
        return productid;
    }

    public String getProductpack() {return productpack;}

    public String getDescription() {
        return description;
    }


}
