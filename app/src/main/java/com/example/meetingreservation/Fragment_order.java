package com.example.meetingreservation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jay on 2019/3/12.
 */

public class Fragment_order extends Fragment implements View.OnClickListener {
    private View v;
    private Context context;
    private RelativeLayout introduction;
    private RelativeLayout booking;
    private RelativeLayout quick_booking;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        v = inflater.inflate(R.layout.page_2, container, false);
        initView(v);
        initEvent();
        //init();
        return v;
    }

    private void initView(View v) {
        introduction=(RelativeLayout)v.findViewById(R.id.introduction);
        booking=(RelativeLayout)v.findViewById(R.id.booking);
        quick_booking=(RelativeLayout)v.findViewById(R.id.quick_booking);
    }
    private void initEvent() {
        introduction.setOnClickListener(this);
        booking.setOnClickListener(this);
        quick_booking.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.introduction:
                Intent introduction_intent = new Intent();
                introduction_intent.setClass(getActivity(), introductionActivity.class);
                startActivity(introduction_intent);
                break;
            case R.id.booking:
                Intent booking_intent = new Intent();
                booking_intent.setClass(getActivity(), bookingActivity.class);
                startActivity(booking_intent);
                break;
            case R.id.quick_booking:
                Intent Qbooking_intent = new Intent();
                Qbooking_intent.setClass(getActivity(), QbookingActivity.class);
                startActivity(Qbooking_intent);
                break;
        }
    }
}
