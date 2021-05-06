package com.mananhirak.annadata.databases;

public class BDETAIL {

    private int id;
    private int seller_id;
    private int buyer_id;
    private int  buyer_order;
    private String buyer_address;
    private int delivery_boy;
    private String delivery_time;
    private int conform_order;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public int getBuyer_order() {
        return buyer_order;
    }

    public void setBuyer_order(int buyer_order) {
        this.buyer_order = buyer_order;
    }

    public String getBuyer_address() {
        return buyer_address;
    }

    public void setBuyer_address(String buyer_address) {
        this.buyer_address = buyer_address;
    }

    public int getDelivery_boy() {
        return delivery_boy;
    }

    public void setDelivery_boy(int delivery_boy) {
        this.delivery_boy = delivery_boy;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public int getConform_order() {
        return conform_order;
    }

    public void setConform_order(int conform_order) {
        this.conform_order = conform_order;
    }
}
