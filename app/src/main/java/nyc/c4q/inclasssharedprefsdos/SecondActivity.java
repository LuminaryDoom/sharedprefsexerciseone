package nyc.c4q.inclasssharedprefsdos;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.*;
import android.widget.*;

import butterknife.*;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.session_message_textview)
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String user = intent.getStringExtra("currentUser");
        textView.setText("You are currently signed in as: " + user);

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.c4q.nyc/"));
        Toast.makeText(getApplicationContext(), "sending to website", Toast.LENGTH_SHORT).show();
        startActivity(browserIntent);
    }

}
