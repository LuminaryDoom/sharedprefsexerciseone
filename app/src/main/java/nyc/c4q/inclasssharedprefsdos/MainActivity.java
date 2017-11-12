package nyc.c4q.inclasssharedprefsdos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.*;
import android.widget.*;
import android.view.View;

import butterknife.*;

public class MainActivity extends AppCompatActivity {

    private static final String SHARED_PREFS_KEY = "sharedPrefsTesting";
    @BindView(R.id.username_edittext)
    EditText username;
    @BindView(R.id.password_edittext)
    EditText password;
    @BindView(R.id.remember_me_checkbox)
    CheckBox checkBox;
    @BindView(R.id.submit_button)
    Button submitButton;
    @BindView(R.id.register_button)
    Button registerButton;
    private SharedPreferences login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        login = getApplicationContext().getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
        //if the checkbox is false then we set the previous the edittextfields to empty
        if (login.getBoolean("isChecked", false)) {
            username.setText(login.getString("username", null));
            password.setText(login.getString("password", null));
            checkBox.setChecked(login.getBoolean("isChecked", false));
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open up the object editor in editor mode
                SharedPreferences.Editor editor = login.edit();
                if (checkBox.isChecked()) { //if true
                    //modify the shared preference by adding a couple of key values to store the data
                    //string into shared prefrences with the key username
                    editor.putString("username", username.getText().toString());
                    //with key password
                    editor.putString("password", password.getText().toString());
                    //stores in key ischecked
                    editor.putBoolean("isChecked", checkBox.isChecked());
                    editor.commit();
                } else {
                    editor.putBoolean("isChecked", checkBox.isChecked());
                    editor.commit();
                }
                //save the is checked false statement
                String checkUser = "user" + username.getText().toString();
                String checkPassword = "password" + username.getText().toString();

                if (username.getText().toString().equalsIgnoreCase(login.getString(checkUser, null))
                        && password.getText().toString().equals(login.getString(checkPassword, null))) {
                    Toast.makeText(getApplicationContext(), "Authentication successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("currentUser", username.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //moving from login page to the signup page/register page
                //cotton eye joe came frrom main activity....where ya going? register activity class
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                //putting in key string testKey and your gonna be shared prefrecnes with key created in the main activiyty
                intent.putExtra("testKey", SHARED_PREFS_KEY);
                startActivity(intent);
            }
        });


    }

}
