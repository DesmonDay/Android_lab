package com.example.desmon.lab3_new;

/**
 * Created by Desmon on 2017/10/21.
 */

public class ShoppingItem {
    private String commodity; //商品名称
    private String price;
    private String Info;

    public ShoppingItem(String commodity, String price, String Info){
        this.commodity = commodity;
        this.price = price;
        this.Info = Info;
    }
    public String getCommodity(){
        return commodity;
    }
    public String getPrice(){ return price;}
    public String getInfo(){ return Info;}
}
