package te.project.aidd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ParentLoginActivity extends AppCompatActivity {
    EditText parUserName;
    EditText parPassword;
    Button parLogin;
    TextView parRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);

        db=new DatabaseHelper(this);
        parUserName=(EditText)findViewById(R.id.edittext_username);
        parPassword=(EditText)findViewById(R.id.edittext_password);
        parLogin=(Button)findViewById(R.id.button_login);
        parLogin.setEnabled(false);
        parRegister=(TextView) findViewById(R.id.textview_register);
        parRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent registerIntent=new Intent(ParentLoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        parLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=parUserName.getText().toString().trim();
                String password=parPassword.getText().toString().trim();
                    Boolean res = db.checkUser(user, password);
                    int id=0;
                    if (res == true) {
                        User new_user=new User(id,user);
                        SessionManagement sessionManagement=new SessionManagement(ParentLoginActivity.this);
                        sessionManagement.saveSession(new_user);
                        Toast.makeText(ParentLoginActivity.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                        Intent par=new Intent(ParentLoginActivity.this,ParentNavActivity.class);
                        par.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(par);
                    } else {
                        Toast.makeText(ParentLoginActivity.this, "Login error", Toast.LENGTH_SHORT).show();
                    }
                }


        });
        parUserName.addTextChangedListener(parentLoginTextWatcher);
        parPassword.addTextChangedListener(parentLoginTextWatcher);
    }


    private TextWatcher parentLoginTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput=parUserName.getText().toString().trim();
            String passwordInput=parPassword.getText().toString().trim();
            parLogin.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}