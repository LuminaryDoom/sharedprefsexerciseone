package nyc.c4q.inclasssharedprefsdos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.*;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_username_edittext)
    EditText userName;
    @BindView(R.id.register_password_edittext)
    EditText password;
    @BindView(R.id.confirm_password_edittext)
    EditText confirmPassword;
    @BindView(R.id.submit_button)
    Button submitButton;
    private SharedPreferences registerPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        registerPrefs = getApplicationContext().getSharedPreferences((intent.getStringExtra("testKey")), MODE_PRIVATE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = registerPrefs.edit();
                if (userName.getText() != null &&
                        password.getText() != null &&
                        confirmPassword.getText() != null &&
                        password.getText().toString().equals(
                                confirmPassword.getText().toString()
                        )) {
                    editor.putString("user" + userName.getText().toString(), userName.getText().toString());
                    editor.putString("password" + userName.getText().toString(), password.getText().toString());
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Authentication Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }
}
