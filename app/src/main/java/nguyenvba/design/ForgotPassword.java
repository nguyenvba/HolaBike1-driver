package nguyenvba.design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class ForgotPassword extends AppCompatActivity {

    @BindView(R.id.rsemail)
    EditText rsemail;
    @BindView(R.id.rsCode) EditText rscode;
    @BindView(R.id.rsNewPassword) EditText rsNewPassword;
    @BindView(R.id.rsRepassword) EditText rsRepassword;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        ButterKnife.bind(this);// khai bao thu vien trong gradle app
    }
}
