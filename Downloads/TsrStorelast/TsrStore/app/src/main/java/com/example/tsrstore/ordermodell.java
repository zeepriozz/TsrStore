package com.example.tsrstore;

public class ordermodell {
    String  id,userid,username, usernumber,productid, productname, productprice, productimage, shopid, shopname,totalquantity,totalprice, payment ,date;

    public ordermodell(String id, String userid, String username, String usernumber, String productid, String productname, String productprice, String productimage, String shopid, String shopname, String totalquantity, String totalprice, String payment, String date) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.usernumber = usernumber;
        this.productid = productid;
        this.productname = productname;
        this.productprice = productprice;
        this.productimage = productimage;
        this.shopid = shopid;
        this.shopname = shopname;
        this.totalquantity = totalquantity;
        this.totalprice = totalprice;
        this.payment = payment;
        this.date = date;
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

    public String getProductid() {
        return productid;
    }

    public String getProductname() {
        return productname;
    }

    public String getProductprice() {
        return productprice;
    }

    public String getProductimage() {
        return productimage;
    }

    public String getShopid() {
        return shopid;
    }

    public String getShopname() {
        return shopname;
    }

    public String getTotalquantity() {
        return totalquantity;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public String getPayment() {
        return payment;
    }

    public String getDate() {
        return date;
    }
}
