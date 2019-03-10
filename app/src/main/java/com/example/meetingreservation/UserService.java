package com.example.meetingreservation;

import android.content.Context;

/**
 * Created by X45VD on 2016/7/26 0026.
 */
public interface UserService {
    public String changename(String newname);

    public int login(String userName, String password, Context context) ;

    public void logout();

    public int changepassword(String userName, String oldpassword, String newpassword);

    public int  feedback(String userName, String feedback, String contactway);

    public void uploadimg(String userName, String url);
}
