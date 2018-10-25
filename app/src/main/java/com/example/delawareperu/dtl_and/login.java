package com.example.delawareperu.dtl_and;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class login extends AppCompatActivity {
    TextView tv_registrar;
    EditText et_usuario, et_password;
    Button btn_log;

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_usuario = (EditText) findViewById(R.id.txtusuario);
        et_password = (EditText) findViewById(R.id.txtpass);
        btn_log = (Button) findViewById(R.id.btningresar);

        /*boton registrar*/
        tv_registrar=(TextView) findViewById(R.id.tv_registrar);
        tv_registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intentRed = new Intent(login.this, registrar_usuario.class);
                login.this.startActivity(intentRed);
            }
        });


        /*Video de fondo*/
        mVideoView = (VideoView) findViewById(R.id.bgVideoView);

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.login);

        mVideoView.setVideoURI(uri);
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });


        /*boton ingresar*/
        btn_log.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                final String username= et_usuario.getText().toString();
                final String password= et_password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success) {
                                String name= jsonResponse.getString("name");

                                Intent intent = new Intent(login.this, listado_suministro.class);
                                intent.putExtra("name", name);
                                intent.putExtra("username", username);
                                intent.putExtra("password", password);

                                login.this.startActivity(intent);

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                                builder.setMessage("Error Logueo")
                                        .setNegativeButton("Retry", null)
                                        .create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest= new LoginRequest(username, password, responseListener);

                RequestQueue queue = Volley.newRequestQueue(login.this);
                queue.add(loginRequest);

            }
        });
    }
}
