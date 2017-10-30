package com.example.desmon.lab3_new;

import android.app.usage.UsageEvents;
import android.util.EventLog;

/**
 * Created by Desmon on 2017/10/29.
 */

public class MessageEvent {
    private ShoppingItem shoppingItem;
    public MessageEvent(ShoppingItem shoppingItem){
        this.shoppingItem = shoppingItem;
    }
    public ShoppingItem getShoppingItem(){
        return shoppingItem;
    }
}
