package te.project.aidd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText UserName;
    EditText Password;
    Button register;
    EditText ChildName;
    EditText doctorMail;
    TextView login;
    int id=1;

    private static final Pattern PASSWORD_PATTERN=
            Pattern.compile("^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{8,}" +
            "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);
        ChildName = (EditText) findViewById(R.id.edittext_childname);
        doctorMail = (EditText) findViewById(R.id.edittext_mail);
        UserName = (EditText) findViewById(R.id.edittext_username);
        Password = (EditText) findViewById(R.id.edittext_password);
        register = (Button) findViewById(R.id.button_register);
        register.setEnabled(false);
        login = (TextView) findViewById(R.id.textview_login);
        register.setEnabled(isNetworkAvailable());
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, ParentLoginActivity.class);
                startActivity(loginIntent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ChildName.getText().toString().trim();
                String mail = doctorMail.getText().toString().trim();
                String user = UserName.getText().toString().trim();
                String password = Password.getText().toString().trim();
                if (mail.isEmpty()) {
                    if (!Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
                        Toast.makeText(RegisterActivity.this, "Please enter a valid Email address", Toast.LENGTH_SHORT).show();
                    }
//                    else if(!PASSWORD_PATTERN.matcher(password).matches()) {
//                        Toast.makeText(RegisterActivity.this, "Please enter a strong Password(atleast 8 characters long)", Toast.LENGTH_SHORT).show();
//                    }
                    else {
                        sendMail(name,mail,user,password);
                        long val = db.addUser(name, mail, user, password);
                        if (val > 0) {
                            Toast.makeText(RegisterActivity.this, "You have registered", Toast.LENGTH_SHORT).show();
                            Intent moveToLogin = new Intent(RegisterActivity.this, HomeActivity.class);
                            startActivity(moveToLogin);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    if (!Patterns.EMAIL_ADDRESS.matcher(user).matches() && !Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                        Toast.makeText(RegisterActivity.this, "Please enter a valid Email address", Toast.LENGTH_SHORT).show();
                    }
//                    else if(!PASSWORD_PATTERN.matcher(password).matches()) {
//                        Toast.makeText(RegisterActivity.this, "Please enter a strong Password(atleast 8 characters long)", Toast.LENGTH_SHORT).show();
//                    }
                    else {
                        long val = db.addUser(name, mail, user, password);
                        if (val > 0) {
                            sendMail(name,mail,user,password);
                            Toast.makeText(RegisterActivity.this, "You have registered", Toast.LENGTH_SHORT).show();
                            Intent moveToLogin = new Intent(RegisterActivity.this, HomeActivity.class);
                            startActivity(moveToLogin);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
        ChildName.addTextChangedListener(registerTextWatcher);
        UserName.addTextChangedListener(registerTextWatcher);
        Password.addTextChangedListener(registerTextWatcher);

    }

    private void sendMail(String name, String docMail, String userMail,String password){

        String mail=userMail;
        String message="You have successfully registered on AiDD:Assisting ADHD children and doctors app.Please note the Login credentials.\nParent/Doctor username:"+" "+userMail+"\nParent/Doctor Password:"+password+"\nChild name:"+name+"\nChild Password:"+name+String.valueOf(id);
        String subject="Registration successful";
        JavaMailAPI javaMailAPI=new JavaMailAPI(this,mail,subject,message);
        javaMailAPI.execute();
        id++;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onStart() {
        if (!isNetworkAvailable())
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("You are not connected to internet.To register, please check your internet connection!");
            builder.setCancelable(false);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent par=new Intent(RegisterActivity.this,HomeActivity.class);
                    par.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(par);
                    finish();
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
        super.onStart();
    }

    private TextWatcher registerTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = UserName.getText().toString().trim();
            String passwordInput = Password.getText().toString().trim();
            String nameInput = ChildName.getText().toString().trim();
            register.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty() && !nameInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}