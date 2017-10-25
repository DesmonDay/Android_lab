package com.example.desmon.lab2_new;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    ImageView image = null;
    RadioGroup person = null;
    RadioButton student = null;
    RadioButton staff = null;
    Button login = null;
    Button register = null;
    TextInputLayout user_idText;
    TextInputLayout passwordText;
    AutoCompleteTextView mNumberEdit = null;
    EditText mPassEdit = null;
    String[] id_number = {"125235","162343","123456","126232","174521",
                            "162312","152424","154643","142452"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //点击图片
        image = (ImageView)findViewById(R.id.imageView); //中大图片
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("上传头像").setItems(new String[] {"拍摄","从相册选择"},
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        if(i == 0){
                            Toast.makeText(getApplicationContext(),"您选择了[拍摄]",Toast.LENGTH_SHORT).show();
                        }
                        if(i == 1){
                            Toast.makeText(getApplicationContext(),"您选择了[从相册选择]",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("取消",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        Toast.makeText(MainActivity.this,"你选择了取消",Toast.LENGTH_SHORT).show();
                    }
                }).create();
        if(image != null){
            image.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    alertDialog.show();
                }
            });
        }

        user_idText = (TextInputLayout)findViewById(R.id.textinputlayout1);
        passwordText = (TextInputLayout)findViewById(R.id.textinputlayout2);
        mNumberEdit = (AutoCompleteTextView)findViewById(R.id.editTextUser_id);
        mPassEdit = passwordText.getEditText();
        user_idText.setHint("请输入学号");
        passwordText.setHint("请输入密码");
        login = (Button)findViewById(R.id.button1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line,id_number);
        mNumberEdit.setAdapter(adapter);
        login.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                String user_id = mNumberEdit.getText().toString();
                String password = mPassEdit.getText().toString();
                if(TextUtils.isEmpty(user_id)){
                    user_idText.setErrorEnabled(true);
                    user_idText.setError("学号不能为空");
                    return;
                } else{
                    user_idText.setErrorEnabled(false);
                }
                if(TextUtils.isEmpty(password)){
                    passwordText.setErrorEnabled(true);
                    passwordText.setError("密码不能为空");
                } else{
                    passwordText.setErrorEnabled(false);
                }

                if(user_id.equals("123456") && password.equals("6666")){
                    Snackbar snackbar = Snackbar.make(login, "登录成功", Snackbar.LENGTH_LONG);
                    snackbar.setAction("确定",new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Toast.makeText(MainActivity.this, "Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                    snackbar.setActionTextColor(getResources().getColor(R.color.skyblue));
                }
                else if((!user_id.equals("123456") || !password.equals("6666")) &&
                        (!TextUtils.isEmpty(user_id)&&!TextUtils.isEmpty(password))){
                    Snackbar snackbar = Snackbar.make(login, "学号或密码错误", Snackbar.LENGTH_LONG);
                    snackbar.setAction("确定",new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Toast.makeText(MainActivity.this, "Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                    snackbar.setActionTextColor(getResources().getColor(R.color.skyblue));
                }
            }
        });

        //点击学生，教工，弹出Snackbar;
        person = (RadioGroup)findViewById(R.id.radioGroup);
        student = (RadioButton)findViewById(R.id.radioButton4);
        staff = (RadioButton)findViewById(R.id.radioButton5);
        person.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(checkedId == student.getId()){
                    Snackbar snackbar = Snackbar.make(group, "您选择了学生",Snackbar.LENGTH_LONG);
                    snackbar.setAction("确定", new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Toast.makeText(MainActivity.this,"Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                    snackbar.setActionTextColor(getResources().getColor(R.color.skyblue));
                }
                if(checkedId == staff.getId()){
                    Snackbar snackbar = Snackbar.make(group, "您选择了教职工",Snackbar.LENGTH_LONG);
                    snackbar.setAction("确定",new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Toast.makeText(MainActivity.this,"Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                    snackbar.setActionTextColor(getResources().getColor(R.color.skyblue));
                }
            }
        });

        register = (Button) findViewById(R.id.button2);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                int checkedId = radioGroup.getCheckedRadioButtonId();
                if(checkedId == R.id.radioButton4){
                    Snackbar snackbar = Snackbar.make(register, "学生注册功能尚未启用",Snackbar.LENGTH_LONG);
                    snackbar.setAction("确定", new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Toast.makeText(MainActivity.this,"Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                    snackbar.setActionTextColor(getResources().getColor(R.color.skyblue));
                }
                if(checkedId == R.id.radioButton5){
                    Toast.makeText(MainActivity.this,"教职工注册功能尚未启用",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
