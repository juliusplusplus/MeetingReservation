package com.example.meetingreservation;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Jay on 2019/2/26.
 */

public class userphoneActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText phone;
    private EditText email;
    private Button bt_ok;
    //private ImageView back;
    private SharePreferenceUtil sp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        initview();
    }

    private void initview() {
        sp=new SharePreferenceUtil(getApplicationContext(), SharePreferenceConf.PERSONINFO);
        //back= (ImageView) findViewById(R.id.back);
        phone= (EditText) findViewById(R.id.phone);
        email= (EditText) findViewById(R.id.email);
        //newpassword1= (EditText) findViewById(R.id.newpassword1);
        bt_ok= (Button) findViewById(R.id.imageButton_save);
        //back.setOnClickListener(this);
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone.setText(sp.getuserphone("phone")+"");
                email.setText(sp.getemail("email")+"");
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
//            case R.id.back:
//                finish();
//                break;
        }
    }

//    private class ModifypassTask extends AsyncTask<String,Void,Integer>
//    {
//        @Override
//        protected Integer doInBackground(String... strings) {
//
//            return ServiceFactory.getUserService(getApplicationContext()).changepassword(strings[0],strings[1],strings[2]);
//        }
//
//    }
}



