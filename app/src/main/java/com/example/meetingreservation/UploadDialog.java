package com.example.meetingreservation;


import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


/**
 * Created by X45VD on 2016/10/10 0010.
 */
public class UploadDialog {
    private volatile static Dialog UploadDialog;
    private static TextView textView;

    private static Dialog createDialog(Context context, String text) {
        View contentView = LayoutInflater.from(context).inflate(
                R.layout.dialog_upload, null);
        Dialog dlg = new Dialog(context, R.style.my_dialog_style);
        textView= (TextView) contentView.findViewById(R.id.text);
        textView.setText(text);
        dlg.setContentView(contentView);
        Window window = dlg.getWindow();
        WindowManager.LayoutParams param = window.getAttributes();
        Resources resource = context.getResources();
        DisplayMetrics dm = resource.getDisplayMetrics();
        param.width = dm.widthPixels;
        param.height = dm.heightPixels;
        param.gravity = Gravity.TOP;
        window.setAttributes(param);
        dlg.setCancelable(false);
        return dlg;
    }
    public static void show(Context context, String text) {
        if (UploadDialog == null) {
            synchronized (Dialog.class) {
                if (UploadDialog == null) {
                    UploadDialog = createDialog(context,text);
                }
            }
        }
        UploadDialog.show();
    }

    public static void dismiss() {
        if (UploadDialog != null) {
            synchronized (Dialog.class) {
                if (UploadDialog != null) {
                    UploadDialog.dismiss();
                    UploadDialog = null;
                }
            }
        }
    }
}
