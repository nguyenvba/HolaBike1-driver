package nguyenvba.design;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.etEmail) EditText etEmail;
    @BindView(R.id.etPassword) EditText etPassword;
    @BindView(R.id.tvRegister) TextView registerLink;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initViews();
    }

    private void initViews(){
        ButterKnife.bind(this);// khai bao thu vien trong gradle app
        etEmail.setText("caothang@gmail.com");
        etPassword.setText("123456");
        client = new OkHttpClient();
    }

    @OnClick(R.id.tvRegister)
    public void register(View view) {
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }

    @OnClick(R.id.forgotPsw)
    public void forgotPsw(View view) {
        Intent forgotPswIntent = new Intent(this, ForgotPassword.class);
        startActivity(forgotPswIntent);
    }

    @OnClick(R.id.bLogin)
    public void submit(View view) {//request chuoi json len server

        String json = "{" + "\"" + "email" + "\"" + ":" + "\"" + etEmail.getText() + "\"" + "," + "\"" + "password" + "\"" + ":" + "\""
                + etPassword.getText() + "\"" + "}";
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://phudongtoiyeu.com/api/customer/login")
                .post(body)
                .build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() { //truong hop neu failure or success

            @Override
            public void onFailure(Call call, IOException e) {//fail
                Log.e("MEOMEO", "error " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {//success

                String result = response.body().string();
                String a = "{\"notice\": {\"text\": \"Login Successful\"}";
                if (result.equalsIgnoreCase(a)) {
                    Intent registerIntent = new Intent(LoginActivity.this, TripPost.class);//nhay sang man hinh userspaceactivity
                    startActivity(registerIntent);
                } else {
                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Wrong email or password, " +
                                    "pls try again", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}






