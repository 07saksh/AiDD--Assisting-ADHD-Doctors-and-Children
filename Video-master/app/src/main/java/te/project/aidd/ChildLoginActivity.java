package te.project.aidd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChildLoginActivity extends AppCompatActivity {
    EditText childUserName;
    EditText childPassword;
    Button childLogin;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_login);
        db=new DatabaseHelper(this);
        childUserName=(EditText)findViewById(R.id.edittext_cusername);
        childPassword=(EditText)findViewById(R.id.edittext_cpassword);
        childLogin=(Button)findViewById(R.id.button_clogin);
        childLogin.setEnabled(false);


        childLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=childUserName.getText().toString().trim();
                String password=childPassword.getText().toString().trim();
                int tableID=db.childCheckUser(user,password);
                int id=1;
                if (tableID>0)
                {
                    User new_user=new User(id,user,tableID);
                    SessionManagement sessionManagement=new SessionManagement(ChildLoginActivity.this);
                    sessionManagement.saveSession(new_user);
                    Toast.makeText(ChildLoginActivity.this,"Successfully Logged in",Toast.LENGTH_SHORT).show();
                    Intent par=new Intent(ChildLoginActivity.this,ChildNavActivity.class);
                    par.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(par);
                }
                else{
                    Toast.makeText( ChildLoginActivity.this,"Login error",Toast.LENGTH_SHORT).show();
                }
            }
        });

        childUserName.addTextChangedListener(childLoginTextWatcher);
        childPassword.addTextChangedListener(childLoginTextWatcher);
    }



    private TextWatcher childLoginTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput=childUserName.getText().toString().trim();
            String passwordInput=childPassword.getText().toString().trim();
            childLogin.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}