package nguyenvba.design;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.etFirstname) EditText etFirstname;
    @BindView(R.id.etLastname) EditText etLastname;
    @BindView(R.id.etEmail) EditText etEmail;
    @BindView(R.id.etPassword) EditText etPassword;
    @BindView(R.id.etPhone) EditText etPhone;
    @BindView(R.id.etRepassword) EditText etRepassword;
//    @BindView(R.id.License_plate) EditText License_plate;
//    @BindView(R.id.License_number) EditText License_number;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        etFirstname = (EditText)findViewById(R.id.etFirstname);
        etLastname =(EditText)findViewById(R.id.etLastname);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etRepassword = (EditText)findViewById(R.id.etRepassword);


//        etFirstname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(etFirstname.getText().toString().length()==0){
//                    etFirstname.setError("First name is required!");
//                }else{
//                    if(etFirstname.getText().toString().length()>10){
//                        etFirstname.setError("String can not more than 10 characters");
//                    }
//                }
//
//            }
//        });

    }

    @OnClick(R.id.btnRegister)
    public void submit(View view) {
        try{
            String vFirstname = etFirstname.getText().toString();
            String vLastname = etLastname.getText().toString();
            String vEmail = etEmail.getText().toString();
            String vPassword = etPassword.getText().toString();
            String vPhone = etPhone.getText().toString();
            String vRepassword = etRepassword.getText().toString();

            if("".equalsIgnoreCase(vFirstname)
               || "".equalsIgnoreCase(vLastname)
               || "".equalsIgnoreCase(vEmail)
               || "".equalsIgnoreCase(vPassword)
               || "".equalsIgnoreCase(vPhone)
               || "".equalsIgnoreCase(vRepassword))
            {
                Toast.makeText(RegisterActivity.this, "All Field Required !!!",Toast.LENGTH_SHORT).show();
            }else if(vFirstname.length()>7){
                Toast.makeText(RegisterActivity.this, "First name not more than 7 characters",Toast.LENGTH_SHORT).show();
            }else if(vLastname.length() > 15){
                Toast.makeText(RegisterActivity.this, "Last name not more than 15 characters", Toast.LENGTH_SHORT).show();
            }else if(vEmail.length()>30){
                Toast.makeText(RegisterActivity.this, "Email not more than 30 characters", Toast.LENGTH_SHORT).show();
            }
            else{
                Log.e("JSON code :","ftftyfytfty");
                //request chuoi json len server
                String json = "{\"firstname\":\""+etFirstname.getText()+"\","
                        +"\"lastname\":\""+etLastname.getText()+"\","
                        +"\"email\":\""+etEmail.getText()+"\","
                        +"\"password\":\""+etPassword.getText()+"\","
                        +"\"phone\":\""+etPhone.getText()+"\"}";
                Log.e("JSON code :",json.toString());
                RequestBody body = RequestBody.create(JSON, json);
                Request request = new Request.Builder()
                        .url("http://phudongtoiyeu.com/api/driver/register")
                        .post(body)
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {//truong hop neu failure or success
                    @Override
                    public void onFailure(Call call, IOException e) {//fail
//                Log.e("MEOMEO","error "+e.toString());
                        Toast.makeText(RegisterActivity.this,
                                "pls try again", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {//success

                        String result = response.body().string();
                        String a = "{\"notice\": {\"text\": \"Drivers Added\"}";
                        if(result.equalsIgnoreCase(a)){
                            Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);//nhay sang man hinh userspaceactivity
                            startActivity(registerIntent);
                        }
//
                    }
                });
            }

        }
        catch (Exception e){
            Log.e("ERROR NAY:",e.toString());
        }
    }
}
