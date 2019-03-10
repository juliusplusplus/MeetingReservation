package com.example.meetingreservation;

import android.content.Context;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by X45VD on 2016/7/26 0026.
 */
public class UserServiceImpl implements UserService {
    private final OkHttpClient client = new OkHttpClient();
    private static final MediaType MEDIA_TYPE=MediaType.parse("image/png");
    @Override
    public int login(String userName, String password, Context context) {
        SharePreferenceUtil login=new SharePreferenceUtil(context, SharePreferenceConf.PERSONINFO);
        if(userName.equals("1"))
        {
            login.setUserid("1");
            login.setuserphone("159*****2950");
            login.setusercredit(login.getUserid(),100);
            login.setname("张三");
            return 1;
        }

        RequestBody formBody = new FormBody.Builder()
                .add("username", userName)
                .add("password", password)
                .build();
        System.out.println("-------->"+"1234");
        Request request = new Request.Builder()
                //.url(UrlConfig.url+UrlConfig.url_login)
                .post(formBody)
                .build();
        try {
            OkHttpClient.Builder builder=new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS);
            OkHttpClient okHttpClient=builder.build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                //System.out.println("-------->"+response.body().string());
                JSONObject jsonObject=new JSONObject(response.body().string());
                    if(jsonObject.getString("Code").equals("0")) {
                        //String s[] = jsonObject.getString("StudentBirthday").toString().split("-");
                        login.setUserid(jsonObject.getString("userId"));
                        login.setusercredit(login.getUserid(), jsonObject.getInt("usercredit"));
                        login.setuserphone(jsonObject.getString("userphone"));
                        login.setname(jsonObject.getString("username"));
                        login.settoken(jsonObject.getString("Token"));
                        //login.setUsername(userName);
                        //登入验证
                        //存入sp
                    return 1;
                }
                else
                    return 2;
            }
            else
            {
                System.out.println("-------->+失败");
                return  3;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("-------->+登录失败，请检查网络！");
            return  0;
        } catch (JSONException e) {
            e.printStackTrace();
            return 3;
        }
    }

    @Override
    public void logout() {

    }
    @Override
    public String changename(String newname){
        return newname;
    }
    @Override
    public int changepassword(String userName, String oldpassword, String newpassword) {
//        String MD5oldpassword=null;
//        String MD5newpassword=null;
//        try {
//           MD5oldpassword= MD5Util.md5Encode(oldpassword);
//           MD5newpassword= MD5Util.md5Encode(newpassword);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        RequestBody formBody = new FormBody.Builder()
                    .add("username", userName)
                     .add("oldPassword",oldpassword)
                     .add("newPassword",newpassword)
                .add("newPassword2",newpassword)
                     .build();
        Request request = new Request.Builder()
               //.url(UrlConfig.url+UrlConfig.url_modifypsw)
                .post(formBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                JSONObject jsonObject=new JSONObject(response.body().string());
                switch (jsonObject.getString("Code"))
                {
                    case "0":
                        return 1;
                    case "1":
                        return 2;
                    case "2":
                        return 2;
                    case "3":
                        return 2;
                    case "4":
                        return 3;
                    case "5":
                        return 4;
                    case "8":
                        return 5;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 2;
    }

    @Override
    public int feedback(String userName, String feedback, String contactway) {
        RequestBody formBody = new FormBody.Builder()
                .add("userName", userName)
                .add("feedback",feedback)
                .add("contactway",contactway)
                .build();
        Request request = new Request.Builder()
                //.url(UrlConfig.url)
                .post(formBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject jsonObject=new JSONObject(response.body().string());
            switch (jsonObject.getString("Code"))
            {
                case "1":
                    return 1;
                case "2":
                    return 2;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 3;
    }

    @Override
    public void uploadimg(String userName, String url) {
//        MultipartBody body = new MultipartBody.Builder("AaB03x")
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("files", null, new MultipartBody.Builder("BbC04y")
//                        .addPart(Headers.of("Content-Disposition", "form-data; filename=\"img.png\""),
//                                RequestBody.create(MediaType.parse("image/png"), new File(url)))
//                        .build())
//                .build();
//        Request request = new Request.Builder()
//                .url("https://en.wikipedia.org/w/index.php")
//                .post(body)
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//
//
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
