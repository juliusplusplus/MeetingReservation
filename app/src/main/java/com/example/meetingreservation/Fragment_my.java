package com.example.meetingreservation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wx.wheelview.widget.WheelView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jay on 2019/2/26.
 */
public class Fragment_my extends Fragment implements View.OnClickListener {
    TextView textView;
    private CircleImageView imageView;
    private Context context;
    private Bitmap head;// 头像Bitmap
    private static String path;// sd路径
    private RelativeLayout height;
    private RelativeLayout weight;
    private View v;
    private PopupWindow popupWindow;
    private WheelView wheelView;
    private RelativeLayout rl_usercredit;
    private RelativeLayout rl_nickname;
    private RelativeLayout rl_modifypassword;
    private RelativeLayout rl_userphone;
    private RelativeLayout rl_userlogout;
    private TextView tv_modifypassword;
    private TextView tv_id;
    private TextView tv_userphone;
    private TextView tv_name;
    private TextView tv_usercredit;
    private List<String> a = new ArrayList<String>();
    private List<String> b=new ArrayList<String>();
    private List<String> year = new ArrayList<String>();
    private List<String> month = new ArrayList<String>();
    private SharePreferenceUtil sp;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        //EventBus.getDefault().register(this);
        //a.clear();
        sp = new SharePreferenceUtil(getContext(), SharePreferenceConf.PERSONINFO);
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        path = context.getFilesDir().getPath();
        v = inflater.inflate(R.layout.page_4, container, false);
        initView(v);
        initEvent();
        init();



//        Bitmap bt = BitmapFactory.decodeFile(path + "/" + "head.jpg");// 从SD卡中找头像，转换成Bitmap
//
//        if (bt != null) {
//            @SuppressWarnings("deprecation")
//            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
//            imageView.setImageDrawable(drawable);
//        }
        return v;
    }

    private void initEvent() {
        imageView.setOnClickListener(this);
        rl_nickname.setOnClickListener(this);
        rl_modifypassword.setOnClickListener(this);
        rl_userphone.setOnClickListener(this);
        rl_userlogout.setOnClickListener(this);
    }

    private void init() {
        if (sp.getuserphone(sp.getUserid()) == 0)
            tv_userphone.setText("--");

        else
            tv_userphone.setText(sp.getuserphone(sp.getUserid())+"");

        if (sp.getusercredit(sp.getUserid()) == 0)
            tv_usercredit.setText("--");
        else
            tv_usercredit.setText(sp.getusercredit(sp.getUserid()) + "分");
        tv_id.setText("工号："+sp.getUserid());
        //tv_name.setText(sp.getname());
        //tv_sex.setText(sp.getsex());
    }

    private void initView(View v) {
        imageView = (CircleImageView) v.findViewById(R.id.profile_image);
        tv_id= (TextView) v.findViewById(R.id.userid);
        tv_userphone = (TextView) v.findViewById(R.id.tv_userphone);
        tv_usercredit = (TextView) v.findViewById(R.id.tv_usercredit);
        tv_name= (TextView) v.findViewById(R.id.username);
        rl_nickname=(RelativeLayout)v.findViewById(R.id.nickname);
        rl_modifypassword=(RelativeLayout)v.findViewById(R.id.Modify_password);
        rl_userphone=(RelativeLayout)v.findViewById(R.id.userphone);
        rl_usercredit= (RelativeLayout)v.findViewById(R.id.usercredit);
        rl_userlogout= (RelativeLayout)v.findViewById(R.id.userlogout);
    }

    @Override
    public void onStart() {
        super.onStart();
        tv_name.setText(sp.getname());
        tv_usercredit.setText(sp.getusercredit("userid")+"");
    }

    @Override
    public void onDestroy() {
        //EventBus.getDefault().unregister(this);//反注册EventBus
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_image:// 更换头像
                showTypeDialog();
                break;
            case R.id.userphone:
                Intent phone_intent=new Intent();
                phone_intent.setClass(getActivity(),userphoneActivity.class);
                startActivity(phone_intent);
                break;
            case R.id.Modify_password:
                Intent modify_intent =new Intent();
                modify_intent.setClass(getActivity(),Modify_passwordActivity.class);
                modify_intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(modify_intent);
                break;
            case R.id.userlogout:
                SharePreferenceUtil logout=new SharePreferenceUtil(getActivity().getApplicationContext(), SharePreferenceConf.PERSONINFO);
                logout.setUserid("");
                Intent logout_intent =new Intent();
                logout_intent.setClass(getActivity(),LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);  ;
                startActivity(logout_intent);
                break;
//            case R.id.rl_sex:
//                select_sex();
//                break;
//            case R.id.height:
//                slect_height(view);
//                break;
//            case R.id.rl_birthday:
//                //select_birth();
//                break;
//            case R.id.weight:
//                Intent It_weight = new Intent();
//                It_weight.setClass(getActivity(), WeightActivity.class);
//                startActivity(It_weight);
//                break;
        }
    }
//    private void select_sex(){
//        View contentView = LayoutInflater.from(context).inflate(
//                R.layout.wheelview_height, null);
//        TextView ok = (TextView) contentView.findViewById(R.id.ok);
//        TextView cancel = (TextView) contentView.findViewById(R.id.cancel);
//        popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        popupWindow.setContentView(contentView);
//        wheelView = (WheelView) contentView.findViewById(R.id.wheelview);
//        wheelView.setWheelAdapter(new ArrayWheelAdapter(context)); // 文本数据源
//        wheelView.setSkin(WheelView.Skin.Holo); // common皮肤
//        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
//        style.textColor = Color.GRAY;
//        style.selectedTextColor = Color.parseColor("#0288ce");
//        //  style.selectedTextColor=Color.BLUE;
//        // style.backgroundColor=Color.WHITE;
//        // style.holoBorderColor=Color.GRAY;
//        wheelView.setStyle(style);
//        wheelView.setWheelData(b);  // 数据集合
//        wheelView.setWheelSize(5);
//        if(sp.getsex()=="女") wheelView.setSelection(1);
//        popupWindow.setFocusable(true);
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        popupWindow.setBackgroundDrawable(dw);
//
//        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
//        //popupWindow.setOutsideTouchable(false);
//        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
//        lp.alpha = 0.7f;
//        getActivity().getWindow().setAttributes(lp);
//        popupWindow.showAtLocation(getActivity().findViewById(R.id.main), Gravity.BOTTOM | Gravity
//                .CENTER_HORIZONTAL, 0, 0);
//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                tv_sex.setText(b.get(wheelView.getCurrentPosition() ));
//                sp.setsex(b.get(wheelView.getCurrentPosition() ));
//                popupWindow.dismiss();
//            }
//        });
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                popupWindow.dismiss();
//            }
//        });
//
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//
//            @Override
//            public void onDismiss() {
//                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
//                lp.alpha = 1f;
//                getActivity().getWindow().setAttributes(lp);
//            }
//        });
//    }
//    private void slect_height(View view) {
//        View contentView = LayoutInflater.from(context).inflate(
//                R.layout.wheelview_height, null);
//        TextView ok = (TextView) contentView.findViewById(R.id.ok);
//        TextView cancel = (TextView) contentView.findViewById(R.id.cancel);
//        popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        popupWindow.setContentView(contentView);
//        wheelView = (WheelView) contentView.findViewById(R.id.wheelview);
//        wheelView.setWheelAdapter(new ArrayWheelAdapter(context)); // 文本数据源
//        wheelView.setSkin(WheelView.Skin.Holo); // common皮肤
//        wheelView.setExtraText("厘米", Color.parseColor("#0288ce"), 50, 100);
//        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
//        style.textColor = Color.GRAY;
//        style.selectedTextColor = Color.parseColor("#0288ce");
//        //  style.selectedTextColor=Color.BLUE;
//        // style.backgroundColor=Color.WHITE;
//        // style.holoBorderColor=Color.GRAY;
//        wheelView.setStyle(style);
//        wheelView.setWheelData(a);  // 数据集合
//        if(sp.getHeight(sp.getUsername())==0)
//            wheelView.setSelection(175 - 100);
//        else
//        wheelView.setSelection(sp.getHeight(sp.getUsername()) - 100);
//        wheelView.setWheelSize(5);
//        popupWindow.setFocusable(true);
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        popupWindow.setBackgroundDrawable(dw);
//
//        //popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
//        //popupWindow.setOutsideTouchable(false);
//        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
//        lp.alpha = 0.7f;
//        getActivity().getWindow().setAttributes(lp);
//        popupWindow.showAtLocation(getActivity().findViewById(R.id.main), Gravity.BOTTOM | Gravity
//                .CENTER_HORIZONTAL, 0, 0);
//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                tv_height.setText(wheelView.getCurrentPosition() + 100 + "厘米");
//                sp.setHeight(sp.getUsername(), wheelView.getCurrentPosition() + 100);
//                popupWindow.dismiss();
//            }
//        });
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                popupWindow.dismiss();
//            }
//        });
//
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//
//            @Override
//            public void onDismiss() {
//                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
//                lp.alpha = 1f;
//                getActivity().getWindow().setAttributes(lp);
//            }
//        });
//
//
//    }

    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(context, R.layout.dialog_select_photo, null);
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog不消失
        dialog.setView(view);
        dialog.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != Activity.RESULT_CANCELED) {
            switch (requestCode) {

                case 1:
                    if (resultCode == Activity.RESULT_OK) {
                        cropPhoto(data.getData());// 裁剪图片
                    }

                    break;
                case 2:
                    if (resultCode == Activity.RESULT_OK) {
                        File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                        cropPhoto(Uri.fromFile(temp));// 裁剪图片
                    }

                    break;
                case 3:
                    if (data != null) {
                        Bundle extras = data.getExtras();
                        head = extras.getParcelable("data");
                        if (head != null) {
                            /**
                             * 上传服务器代码
                             */
                            setPicToView(head);// 保存在SD卡中
                            imageView.setImageBitmap(head);// 用ImageView显示出来
                        }
                    }
                    break;
                default:
                    break;

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
//        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
//            return;
//        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "/" + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    private void select_birth() {
//        View contentView = LayoutInflater.from(context).inflate(R.layout.wheelview_birthday, null);
//        TextView ok = (TextView) contentView.findViewById(R.id.ok_birth);
//        TextView cancel = (TextView) contentView.findViewById(R.id.cancel_birth);
//        final PopupWindow popupWindow_birth = new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        popupWindow_birth.setContentView(contentView);
//        final WheelView wheelView_year = (WheelView) contentView.findViewById(R.id.wheelview1);
//        wheelView_year.setWheelAdapter(new ArrayWheelAdapter(context)); // 文本数据源
//        wheelView_year.setSkin(WheelView.Skin.Holo); // common皮肤
//        wheelView_year.setExtraText("年", Color.parseColor("#0288ce"), 50, 100);
//        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
//        style.textColor = Color.GRAY;
//        style.selectedTextColor = Color.parseColor("#0288ce");
//        wheelView_year.setStyle(style);
//        wheelView_year.setWheelData(year);  // 数据集合
//        if (sp.getYear(sp.getUsername()) == 0)
//            wheelView_year.setSelection(1995 - 1916);
//        else
//            wheelView_year.setSelection(sp.getYear(sp.getUsername()) - 1916);
//        wheelView_year.setWheelSize(5);
//        final WheelView wheelView_month = (WheelView) contentView.findViewById(R.id.wheelview2);
//        wheelView_month.setWheelAdapter(new ArrayWheelAdapter(context)); // 文本数据源
//        wheelView_month.setSkin(WheelView.Skin.Holo); // common皮肤
//        wheelView_month.setExtraText("月", Color.parseColor("#0288ce"), 50, 100);
//        wheelView_month.setStyle(style);
//        wheelView_month.setWheelData(month);  // 数据集合
//        wheelView_month.setSelection(sp.getMonth(sp.getUsername()) - 1);
//        wheelView_month.setWheelSize(5);
//        popupWindow_birth.setFocusable(true);
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        popupWindow_birth.setBackgroundDrawable(dw);
//        popupWindow_birth.setAnimationStyle(R.style.mypopwindow_anim_style);
//        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
//        lp.alpha = 0.7f;
//        getActivity().getWindow().setAttributes(lp);
//        popupWindow_birth.showAtLocation(getActivity().findViewById(R.id.main), Gravity.BOTTOM | Gravity
//                .CENTER_HORIZONTAL, 0, 0);
//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                tv_birthday.setText((wheelView_year.getCurrentPosition() + 1916 + "年") + (wheelView_month.getCurrentPosition() + 1 + "月"));
//                sp.setYear(sp.getUsername(), wheelView_year.getCurrentPosition() + 1916);
//                sp.setMonth(sp.getUsername(), wheelView_month.getCurrentPosition() + 1);
//                popupWindow_birth.dismiss();
//            }
//        });
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                popupWindow_birth.dismiss();
//            }
//        });
//
//        popupWindow_birth.setOnDismissListener(new PopupWindow.OnDismissListener() {
//
//            @Override
//            public void onDismiss() {
//                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
//                lp.alpha = 1f;
//                getActivity().getWindow().setAttributes(lp);
//            }
//        });
//
//
//    }

//    @Subscribe
//    public void onEventMainThread(WeightEvent event) {
//
//        tv_weight.setText(event.getWeight() + "公斤");
//    }
}
