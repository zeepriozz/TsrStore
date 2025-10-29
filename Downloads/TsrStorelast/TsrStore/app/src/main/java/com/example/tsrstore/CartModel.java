package com.example.tsrstore;

public class CartModel {
    String id,userid,username,usernumber,shopname,shopid,productcategory,productname,price,image,productid,size,totalquantity,totalprice,address;

    public CartModel(String id, String userid, String username, String usernumber, String shopname, String shopid,String productcategory, String productname, String price, String image,String productid,String size,String totalquantity,String totalprice,String address) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.usernumber = usernumber;
        this.shopname = shopname;
        this.shopid = shopid;
        this.productcategory =productcategory;
        this.productname = productname;
        this.price = price;
        this.image = image;
        this.productid=productid;
        this.size=size;
        this.totalquantity=totalquantity;
        this.totalprice=totalprice;
        this.address=address;
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
    public String getProductcategory(){ return productcategory; }

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

    public String getSize() {
        return size;
    }
    public String getTotalquantity() {
        return totalquantity;
    }

    public String getTotalprice() {
        return totalprice;
    }
    public String getAddress(){return address; }

}
