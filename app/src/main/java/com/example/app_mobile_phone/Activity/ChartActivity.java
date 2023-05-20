package com.example.app_mobile_phone.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_mobile_phone.Model.Feature;
import com.example.app_mobile_phone.Model.Order;
import com.example.app_mobile_phone.Model.Product;
import com.example.app_mobile_phone.Model.User;
import com.example.app_mobile_phone.R;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {
    User userInfoLogin;
    List<Order> orderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Intent intent = getIntent();
        userInfoLogin = (User) intent.getSerializableExtra("userInfoLogin");
        setControl();
        setEvent();
    }

    private void setControl() {
    }

    private void setEvent() {
        // nhận dữ liệu
        Bundle bundle = getIntent().getExtras();
        orderList = (List<Order>) bundle.getSerializable("orderList");
    }
}