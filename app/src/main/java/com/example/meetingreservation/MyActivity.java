package com.example.meetingreservation;

/**
 * Created by Jay on 2019/2/26.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout userphone;
    private RelativeLayout usercredit;
    private RelativeLayout userlogout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_4);
        userphone= (RelativeLayout) findViewById(R.id.userphone);
        usercredit= (RelativeLayout) findViewById(R.id.usercredit);
        userlogout= (RelativeLayout) findViewById(R.id.userlogout);
        userphone.setOnClickListener(this);
        usercredit.setOnClickListener(this);
        userlogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.userphone:
                Intent intent =new Intent();
                intent.setClass(this,userphoneActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
//            case R.id.usercredit:
//                Intent intent1 =new Intent();
//                intent1.setClass(this,usercreditActivity.class);
//                intent1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                startActivity(intent1);
//                break;
            case R.id.userlogout:
                SharePreferenceUtil logout=new SharePreferenceUtil(getApplicationContext(), SharePreferenceConf.PERSONINFO);
                logout.setUserid("");
                Intent intent2 =new Intent();
                intent2.setClass(this,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);  ;
                startActivity(intent2);
                finish();
                break;
            case R.id.Modify_password:
                Intent intent3 =new Intent();
                intent3.setClass(this,Modify_passwordActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);;
            startActivity(intent3);
            break;
            case R.id.run:
                finish();
                break;
        }
    }
}

