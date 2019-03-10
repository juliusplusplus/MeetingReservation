package com.example.meetingreservation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageView_set;
    private TextView toolbartitle;
    private LinearLayout buttom_1;
    private LinearLayout buttom_2;
    private LinearLayout buttom_3;
    private LinearLayout buttom_4;

    private ImageView iv_1;
    private ImageView iv_2;
    private ImageView iv_3;
    private ImageView iv_4;

    private TextView tv_1;
    private TextView tv_2;
    private TextView tv_3;
    private TextView tv_4;
    private Fragment CurrentFragment;
    private int currentid;

    private Fragment reminder;
    private Fragment order;
    private Fragment record;
    private Fragment my;
    private String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        restartBotton();
        iv_1.setImageResource(R.drawable.home);
        tv_1.setTextColor(0xff56abe4);
        setSelect(1);
    }
    @Override
    protected void onResume() {
        switch (currentid)
        {
            case 0:
                tv_1.setTextColor(0xff56abe4);
                iv_1.setImageResource(R.drawable.home);
                break;
            case 1:
                tv_2.setTextColor(0xff56abe4);
                iv_2.setImageResource(R.drawable.find);
                break;
            case 2:
                iv_3.setImageResource(R.drawable.statitas);
                tv_3.setTextColor(0xff56abe4);
                break;
            case 3:
                iv_4.setImageResource(R.drawable.my);
                tv_4.setTextColor(0xff56abe4);
                break;
        }
        super.onResume();
    }

    private void initEvent() {
        // 设置按钮监听
        buttom_1.setOnClickListener(this);
        buttom_2.setOnClickListener(this);
        buttom_3.setOnClickListener(this);
        buttom_4.setOnClickListener(this);
        imageView_set.setOnClickListener(this);
    }
    private void initView() {
        // initActionBar();
        // 底部菜单4个Linearlayout
        this.buttom_1 = findViewById(R.id.bottom_1);
        this.buttom_2 = findViewById(R.id.bottom_2);
        this.buttom_3 = findViewById(R.id.bottom_3);
        this.buttom_4 = findViewById(R.id.bottom_4);

        // 底部菜单4个ImageView
        this.iv_1 = findViewById(R.id.iv_buttom1);
        this.iv_2 = findViewById(R.id.iv_buttom2);
        this.iv_3 = findViewById(R.id.iv_buttom3);
        this.iv_4 = findViewById(R.id.iv_buttom4);

        // 底部菜单4个菜单标题
        this.tv_1 = findViewById(R.id.tv_buttom1);
        this.tv_2 = findViewById(R.id.tv_buttom2);
        this.tv_3 = findViewById(R.id.tv_buttom3);
        this.tv_4 = findViewById(R.id.tv_buttom4);
        toolbartitle=findViewById(R.id.toolbar_title);
        imageView_set=findViewById(R.id.set);
    }
    @Override
    public void onClick(View view) {
        restartBotton();
        // ImageView和TextView置为绿色，页面随之跳转
        switch (view.getId()) {
            case R.id.bottom_1:
                setSelect(0);
                iv_1.setImageResource(R.drawable.home);
                tv_1.setTextColor(0xff56abe4);
                break;
            case R.id.bottom_2:
                iv_2.setImageResource(R.drawable.find);
                tv_2.setTextColor(0xff56abe4);
                setSelect(1);
                break;
            case R.id.bottom_3:
                iv_3.setImageResource(R.drawable.statitas);
                tv_3.setTextColor(0xff56abe4);
                setSelect(2);
                break;
            case R.id.bottom_4:
                iv_4.setImageResource(R.drawable.my);
                tv_4.setTextColor(0xff56abe4);
                setSelect(3);
                break;
            default:
                break;
        }
    }

    private void restartBotton() {
        // ImageView置为灰色
        iv_1.setImageResource(R.drawable.home1 );
        iv_2.setImageResource(R.drawable.find1);
        iv_3.setImageResource(R.drawable.static1);
        iv_4.setImageResource(R.drawable.user1);

        tv_1.setTextColor(0xffa9b7b7);
        tv_2.setTextColor(0xffa9b7b7);
        tv_3.setTextColor(0xffa9b7b7);
        tv_4.setTextColor(0xffa9b7b7);
    }
    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();//创建一个事务
        hideFragment(transaction);//我们先把所有的Fragment隐藏了，然后下面再开始处理具体要显示的Fragment
        switch (i) {
            case 0:
                if (reminder == null) {
                    reminder = new Fragment_reminder();
                    transaction.add(R.id.id_content, reminder);//将微信聊天界面的Fragment添加到Activity中
                }else {
                    transaction.show(reminder);
                }
                CurrentFragment=reminder;
                currentid=0;
                toolbartitle.setText("我的预约");
                imageView_set.setVisibility(View.GONE);
                break;
            case 1:
                if (order == null) {
                    // order = new Fragment_order();
                    //transaction.add(R.id.id_content, order);
                }else {
                    transaction.show(order);
                }
                CurrentFragment=order;
                currentid=1;
                toolbartitle.setText("预约");
                imageView_set.setVisibility(View.GONE);
                break;
            case 2:
                if (record == null) {
                    //record = new Fragment_record();
                    // transaction.add(R.id.id_content, record);
                }else {
                    transaction.show(record);
                }
                CurrentFragment= record;
                currentid=2;
                toolbartitle.setText("历史纪录");
                imageView_set.setVisibility(View.GONE);
                break;
            case 3:
                if (my == null) {
                    my = new Fragment_my();
                     transaction.add(R.id.id_content, my);
                }else {
                    transaction.show(my);
                }
                CurrentFragment=my;
                currentid=3;
                toolbartitle.setText("我的");
                imageView_set.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        transaction.commit();//提交事务
    }

    private void hideFragment(FragmentTransaction transaction) {

        if (reminder != null) {
            transaction.hide(reminder);
        }
        if (order!= null) {
            transaction.hide(order);
        }
        if (my != null) {
            transaction.hide(my);
        }
        if (record != null) {
            transaction.hide(record);
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
            exitBy2Click();
        return false;
    }

    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
}
