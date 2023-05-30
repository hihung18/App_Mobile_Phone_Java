package com.example.app_mobile_phone.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_mobile_phone.R;
import com.example.app_mobile_phone.Util.NetworkChangeReceiver;
import com.example.app_mobile_phone.Util.ShowDialog;

public class ForgotPassword_EnterCode extends AppCompatActivity {
    Button btnEnterCode;
    EditText txtEnterCode;
    ImageButton btnPrevious;
    private NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_enter_code);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnEnterCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = txtEnterCode.getText().toString().trim().toLowerCase();
                if (code.length() == 0) {
                    Dialog dialog = new Dialog(ForgotPassword_EnterCode.this);
                    ShowDialog showDialog = new ShowDialog(dialog, false, "Vui lòng nhập mã xác minh!", ForgotPassword_EnterCode.this, ForgotPassword_SetPass.class, true);
                    return;
                }
                if (!code.equals(String.valueOf(ForgotPassword_EnterEmail.otpCode)) || code.length() < 6) {
                    Dialog dialog = new Dialog(ForgotPassword_EnterCode.this);
                    ShowDialog showDialog = new ShowDialog(dialog, false, "Mã xác minh không chính xác, vui lòng kiểm tra lại hoặc lấy mã OTP mới!", ForgotPassword_EnterCode.this, ForgotPassword_SetPass.class, true);
                    return;
                }
                Intent intent = new Intent(ForgotPassword_EnterCode.this, ForgotPassword_SetPass.class);
                startActivity(intent);
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addControls() {
        btnEnterCode = findViewById(R.id.btnEnterCode);
        txtEnterCode = findViewById(R.id.txtEnterCode);
        btnPrevious = findViewById(R.id.btnPrevious);
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeReceiver, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        unregisterReceiver(networkChangeReceiver);
        super.onStop();
    }
}