package com.example.meetingreservation;

import android.content.Context;
import android.util.Log;


/**
 * Created by X45VD on 2016/7/26 0026.
 */
public class ServiceFactory {
    public static UserService getUserService(Context context)
    {
        return new UserServiceImpl();
    }

}
