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
public class Modify_passwordActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText oldpassword;
    private EditText newpassword;
    private EditText newpassword1;
    private Button bt_ok;
    private ImageView back;
    private SharePreferenceUtil sp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifypassword);
        initview();
    }

    private void initview() {
        sp=new SharePreferenceUtil(getApplicationContext(), SharePreferenceConf.PERSONINFO);
        back= (ImageView) findViewById(R.id.back);
        oldpassword= (EditText) findViewById(R.id.oldpassword);
        newpassword= (EditText) findViewById(R.id.newpassword);
        newpassword1= (EditText) findViewById(R.id.newpassword1);
        bt_ok= (Button) findViewById(R.id.Button_ok);
        back.setOnClickListener(this);
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(oldpassword.getText().toString().equals("")||oldpassword.getText().toString()==null)
                    Toast.makeText(getApplicationContext(),"请填写原密码", Toast.LENGTH_SHORT).show();
                else if(newpassword.getText().toString().equals("")||newpassword.getText().toString()==null)
                    Toast.makeText(getApplicationContext(),"请填写新密码", Toast.LENGTH_SHORT).show();
                else if(newpassword1.getText().toString().equals("")||newpassword1.getText().toString()==null)
                    Toast.makeText(getApplicationContext(),"请确认新密码", Toast.LENGTH_SHORT).show();
                else if(!newpassword.getText().toString().equals(newpassword1.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "新密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    newpassword.setText("");
                    newpassword1.setText("");
                }
                else if(newpassword.getText().toString().equals(oldpassword.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "旧密码和新密码相同，请重新输入", Toast.LENGTH_SHORT).show();
                    oldpassword.setText("");
                    newpassword.setText("");
                    newpassword1.setText("");
                }
                //else if(!NetWorkUtil.isNetworkConnected(getApplicationContext()))
                //    Toast.makeText(getApplicationContext(), "网络不可用", Toast.LENGTH_SHORT).show();
                else
                {
                    //UploadDialog.show(Modify_passwordActivity.this,"正在修改");
                    new ModifypassTask().execute(sp.getUserid(),oldpassword.getText().toString(),newpassword.getText().toString());
                }
            }
        });
    }
    private void aftermodifypassword(int category)
    {
        switch (category)
        {
            case 1:
                //UploadDialog.dismiss();
                Toast.makeText(getApplicationContext(), "密码修改成功,请重新登录！", Toast.LENGTH_SHORT).show();
                SharePreferenceUtil logout=new SharePreferenceUtil(getApplicationContext(), SharePreferenceConf.PERSONINFO);
                logout.setUserid("");
                Intent intent2 =new Intent();
                intent2.setClass(this,LoginActivity  .class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);  ;
                startActivity(intent2);
                finish();
                break;
            case 2:
                //UploadDialog.dismiss();
                Toast.makeText(getApplicationContext(), "密码修改失败，请稍后重试", Toast.LENGTH_SHORT).show();
                break;

            case 3:
                //UploadDialog.dismiss();
                Toast.makeText(getApplicationContext(), "原密码错误", Toast.LENGTH_SHORT).show();
                newpassword.setText("");
                newpassword1.setText("");
                oldpassword.setText("");
                break;
            case 4:
                //UploadDialog.dismiss();
                Toast.makeText(getApplicationContext(), "账户已停用", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                //UploadDialog.dismiss();
                Toast.makeText(getApplicationContext(), "用户不存在", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.back:
                finish();
                break;
        }
    }

    private class ModifypassTask extends AsyncTask<String,Void,Integer>
    {
        @Override
        protected Integer doInBackground(String... strings) {

            return ServiceFactory.getUserService(getApplicationContext()).changepassword(strings[0],strings[1],strings[2]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            aftermodifypassword(integer);
        }
    }
}


