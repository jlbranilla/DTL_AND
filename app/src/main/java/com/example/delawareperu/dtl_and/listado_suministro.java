package com.example.delawareperu.dtl_and;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class listado_suministro extends AppCompatActivity {
    TextView txtNombre, tvUsuario, tvPassword;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_atenciones:
                    mTextMessage.setText(R.string.title_atenciones);
                    return true;
                case R.id.navigation_pago:
                    mTextMessage.setText(R.string.title_pago);
                    return true;
                case R.id.navigation_reporte:
                    mTextMessage.setText(R.string.title_reporte);
                    return true;
                case R.id.navigation_reclamos:
                    mTextMessage.setText(R.string.title_reclamos);
                    return true;
                case R.id.navigation_menu:
                    mTextMessage.setText(R.string.title_menu);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_suministro);

        txtNombre = (TextView) findViewById(R.id.txtdata);
        tvUsuario = (TextView) findViewById(R.id.txtusuario);
        tvPassword = (TextView) findViewById(R.id.txtpass);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        txtNombre.setText(name);
        tvUsuario.setText(username);
        tvPassword.setText(password);


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }




}
