package com.example.meetingreservation;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * Created by Jay on 2019/2/26.
 */
public class SharePreferenceUtil {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public SharePreferenceUtil(Context context, String file) {
        sp = context.getSharedPreferences(file, context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void setusercredit(String userid, int credit) {
        editor.putInt(userid+SharePreferenceConf.usercredit, credit);
        editor.commit();
    }


    public int getusercredit(String userid) {
        return sp.getInt(userid+SharePreferenceConf.usercredit,0);
    }

    public void setPassword(String password)
    {
        editor.putString("password", password);
        editor.commit();
    }

    public String getPassword()
    {
        return sp.getString("height","");
    }
    public void setUserid(String userid)
    {
        editor.putString(SharePreferenceConf.USERID, userid);
        editor.commit();
    }

    public String getUserid()
    {
        return sp.getString(SharePreferenceConf.USERID,"");
    }

    public void setBnadrname(String userid, String bandname)
    {
        editor.putString(userid+SharePreferenceConf.BANDNAME, bandname);
        editor.commit();
    }
    public String getBnadrname(String userid)
    {
        return sp.getString(userid+SharePreferenceConf.BANDNAME,"");
    }


    public void setStepgoal(int Stepgoal)
    {
        editor.putInt(SharePreferenceConf.STEPGOAL, Stepgoal);
        editor.commit();
    }
    public int getStepgoal()
    {
        return sp.getInt(SharePreferenceConf.STEPGOAL,8000);
    }
    public void setStaticRate(int StaticRate)
    {
        editor.putInt(SharePreferenceConf.STATICHEARTRATE, StaticRate);
        editor.commit();
    }
    public int getStaticRate()
    {
        return sp.getInt(SharePreferenceConf.STATICHEARTRATE,60);
    }

    public  void setFirstrecord(String userid, String bandid, String record)
    {
        editor.putString(userid+bandid+SharePreferenceConf.FIRSTRECORDTIME, record);
        editor.commit();
    }
    public String getFirstrecord(String userid, String bandid)
    {
        return sp.getString(userid+bandid+SharePreferenceConf.FIRSTRECORDTIME,"");
    }
    public void setBestsrecord(int Bestsrecord)
    {
        editor.putInt("Bestsrecord", Bestsrecord);
        editor.commit();
    }
    public int getBestsrecord()
    {
        return sp.getInt("Bestsrecord",0);
    }

//    public void setMacaddress(String userid, String Macaddress)
//    {
//        editor.putString(userid+SharePreferenceConf.BANDMACADDRESS, Macaddress);
//        editor.commit();
//    }
//    public String getMacaddress(String userid)
//    {
//        return sp.getString(userid+SharePreferenceConf.BANDMACADDRESS,"");
//    }
    public int getuserphone(String userid)
    {
        return sp.getInt(SharePreferenceConf.userphone,0);
    }

    public void setuserphone(String userphone)
    {
        editor.putString(SharePreferenceConf.userphone, userphone);
        editor.commit();
    }
    public String getname()
    {
        return sp.getString(SharePreferenceConf.USERNAME,"");
    }

    public void setname(String name)
    {
        editor.putString(SharePreferenceConf.USERNAME, name);
        editor.commit();
    }
    public String gettoken()
    {
        return sp.getString(SharePreferenceConf.TOKEN,"");
    }

    public void settoken(String token)
    {
        editor.putString(SharePreferenceConf.TOKEN, token);
        editor.commit();
    }
    public String geturl()
    {
        return sp.getString(SharePreferenceConf.SYSTEMURL,"");
    }

    public void seturl(String url)
    {
        editor.putString(SharePreferenceConf.SYSTEMURL,url);
        editor.commit();
    }

}
