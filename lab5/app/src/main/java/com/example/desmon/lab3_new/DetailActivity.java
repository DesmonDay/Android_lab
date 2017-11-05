package com.example.desmon.lab3_new;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ListView mProInfo;
    private ImageButton star;
    private TextView goodname;
    private TextView goodprice;
    private TextView goodInfo;
    private ImageButton back;
    private ImageView pic;
    private List<String> more;
    private DetailAdapter detailAdapter;
    private ImageButton car;
    private DynamicReceiver dynamicReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mProInfo = (ListView) findViewById(R.id.mListView);
        String[] Information = new String[] {"一键下单","分享商品","不感兴趣","查看更多商品促销信息"};
        more = new ArrayList<>();
        for (int i=0; i<Information.length; i++){
            more.add(Information[i]);
        }
        mProInfo.setAdapter(detailAdapter = new DetailAdapter(DetailActivity.this, more));

        goodname = (TextView) findViewById(R.id.goodname);
        goodprice = (TextView) findViewById(R.id.goodprice);
        goodInfo = (TextView) findViewById(R.id.goodinfo);
        pic = (ImageView) findViewById(R.id.pic);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        //接受从MainActivity传来的信息
        String name = null;
        String price = null;
        String info = null;

        if(extras != null){
            name = extras.getString("name");
            price = extras.getString("price");
            info = extras.getString("Info");
        }
        goodname.setText(name);
        goodprice.setText(price);
        goodInfo.setText(info);

        final Intent intent1 = new Intent("dynamic");
        final Bundle mBundle = new Bundle();
        switch (name){
            case "Enchated Forest":
                pic.setImageResource(R.drawable.enchartedforest);
                mBundle.putInt("Image",R.drawable.enchartedforest);
                break;
            case "Arla Milk":
                pic.setImageResource(R.drawable.arla);
                mBundle.putInt("Image",R.drawable.arla);
                break;
            case "Devondale Milk":
                pic.setImageResource(R.drawable.devondale);
                mBundle.putInt("Image",R.drawable.devondale);
                break;
            case "Kindle Oasis":
                pic.setImageResource(R.drawable.kindle);
                mBundle.putInt("Image",R.drawable.kindle);
                break;
            case "waitrose 早餐麦片":
                pic.setImageResource(R.drawable.waitrose);
                mBundle.putInt("Image",R.drawable.waitrose);
                break;
            case "Mcvitie's 饼干":
                pic.setImageResource(R.drawable.mcvitie);
                mBundle.putInt("Image",R.drawable.mcvitie);
                break;
            case "Ferrero Rocher":
                pic.setImageResource(R.drawable.ferrero);
                mBundle.putInt("Image",R.drawable.ferrero);
                break;
            case "Maltesers":
                pic.setImageResource(R.drawable.maltesers);
                mBundle.putInt("Image",R.drawable.maltesers);
                break;
            case "Lindt":
                pic.setImageResource(R.drawable.lindt);
                mBundle.putInt("Image",R.drawable.lindt);
                break;
            case "Borggreve":
                pic.setImageResource(R.drawable.borggreve);
                mBundle.putInt("Image",R.drawable.borggreve);
                break;
        }
        mBundle.putString("name",name);
        mBundle.putString("price",price);
        mBundle.putString("Info",info);


        final ShoppingItem shoppingItem = new ShoppingItem(name, price, info);
        car = (ImageButton) findViewById(R.id.car);
        car.setTag("1");
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent(shoppingItem));
                //动态注册广播
                Object tag = car.getTag();
                if(tag == "1"){
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("dynamic");
                    dynamicReceiver = new DynamicReceiver();
                    registerReceiver(dynamicReceiver, intentFilter);
                    intent1.putExtras(mBundle);
                    sendBroadcast(intent1);
                    car.setTag("0");
                }
                if(tag == "0") {
                    unregisterReceiver(dynamicReceiver);
                    car.setTag("1");
                }

            }
        });

        back = (ImageButton)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        star = (ImageButton)findViewById(R.id.star);
        star.setTag("0");
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object tag = star.getTag();
                if(tag == "0"){
                    star.setImageResource(R.mipmap.full_star);
                    star.setTag("1");
                }else {
                    star.setImageResource(R.mipmap.empty_star);
                    star.setTag("0");
                }
            }
        });
    }

}
