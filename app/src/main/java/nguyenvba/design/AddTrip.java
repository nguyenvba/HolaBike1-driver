package nguyenvba.design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class AddTrip extends AppCompatActivity {
    @BindView(R.id.evStart) TextView evStart;
    @BindView(R.id.evEnd) TextView evEnd;
    @BindView(R.id.btnAdd) Button btnAdd;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        ButterKnife.bind(this);


    }

}
