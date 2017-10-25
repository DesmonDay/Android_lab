package com.example.desmon.lab3_new;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRcyclerView;
    private ListView shopListView;
    private HomeAdapter homeAdapter;
    private ShopAdapter shopAdapter;
    private List<ShoppingItem> mDatas;
    private List<ShoppingItem> shopDatas;
    private FloatingActionButton change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatas = new ArrayList<>();
        final String[] commodity_name = new String[] {"Enchated Forest","Arla Milk","Devondale Milk","Kindle Oasis",
                "waitrose 早餐麦片","Mcvitie's 饼干","Ferrero Rocher","Maltesers","Lindt","Borggreve"};
        final String[] commodity_price = new String[] {"￥ 5.00", "￥ 59.00", "￥ 79.00", "￥ 2399.0", "￥ 179.00", "￥ 14.9", "¥ 132.59", "¥ 141.43", "¥ 139.43", "¥ 28.90"};
        final String[] commodity_Info = new String[] {"作者 Johanna Basford", "产地 德国", "产地 澳大利亚", "版本 8GB", "重量 2Kg", "产地 英国", "重量 300g", "重量 118g", "重量 249g", "重量 640g"};
        for(int i=0; i<commodity_name.length; i++){
            ShoppingItem shop = new ShoppingItem(commodity_name[i], commodity_price[i], commodity_Info[i]);
            mDatas.add(shop);
        }
        mRcyclerView = (RecyclerView)findViewById(R.id.mRecycleView);
        mRcyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRcyclerView.setAdapter(homeAdapter = new HomeAdapter(mDatas, this));
        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener(){
            @Override
            public void onClick(int position){
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("name", mDatas.get(position).getCommodity());
                intent.putExtra("price",mDatas.get(position).getPrice());
                intent.putExtra("Info", mDatas.get(position).getInfo());
                startActivityForResult(intent, 0);
            }
            @Override
            public void onLongClick(int position){
                int i = position + 1;
                Toast.makeText(MainActivity.this, "移除第"+i+"个商品", Toast.LENGTH_SHORT).show();
                homeAdapter.removeData(position);
            }
        });

        shopDatas = new ArrayList<>();
        ShoppingItem temp = new ShoppingItem("购物车","价格","none");
        shopDatas.add(temp);
        /*for(int i=0; i<commodity_name.length; i++){
            ShoppingItem shop = new ShoppingItem(commodity_name[i], commodity_price[i], commodity_Info[i]);
            shopDatas.add(shop);
        }*/
        shopListView = (ListView)findViewById(R.id.mListView);
        shopListView.setAdapter(shopAdapter = new ShopAdapter(MainActivity.this, shopDatas));
        shopListView.setVisibility(View.INVISIBLE);
        shopListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id){
                if(position == 0);
                else{
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("name", shopDatas.get(position).getCommodity());
                    intent.putExtra("price",shopDatas.get(position).getPrice());
                    intent.putExtra("Info", shopDatas.get(position).getInfo());
                    startActivityForResult(intent, 0);
                }
            }
        });
        shopListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id){
                if(position==0) return true;
                ShoppingItem p = (ShoppingItem) parent.getItemAtPosition(position);
                String name = "从购物车移除" + p.getCommodity();
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("移除商品").setMessage(name+"?").setNegativeButton("取消",
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i){
                                try{
                                    Field field = dialogInterface.getClass().getSuperclass().getDeclaredField("mShowing");
                                    field.setAccessible(true);
                                    field.set(dialogInterface, true);
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }).setPositiveButton("确定",
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i){
                                shopAdapter.removeData(position);
                            }
                        }).show();
                //alertDialog.show();
                return true;
            }
        });

        change = (FloatingActionButton) findViewById(R.id.fab);
        change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(mRcyclerView.getVisibility() == View.VISIBLE &&
                        shopListView.getVisibility() == View.INVISIBLE){
                    mRcyclerView.setVisibility(View.INVISIBLE);
                    shopListView.setVisibility(View.VISIBLE);
                    change.setImageResource(R.mipmap.mainpage);
                }
                else if(mRcyclerView.getVisibility() == View.INVISIBLE &&
                        shopListView.getVisibility() == View.VISIBLE){
                    mRcyclerView.setVisibility(View.VISIBLE);
                    shopListView.setVisibility(View.INVISIBLE);
                    change.setImageResource(R.mipmap.shoplist);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(requestCode == 0 && resultCode == 1){
        //添加到购物车
            Bundle extras = intent.getExtras();
            String name = extras.getString("name");
            String price = extras.getString("price");
            String info = extras.getString("Info");
            ShoppingItem shop = new ShoppingItem(name, price, info);
            shopDatas.add(shop);
            shopAdapter.notifyDataSetChanged();
        }
    }
}
