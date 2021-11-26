package te.project.aidd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Handler;

import te.project.aidd.ui.logout.LogoutFragment;

public class Feedback extends AppCompatActivity {
    EditText uname;
    EditText ufeedback;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        uname = (EditText) findViewById(R.id.name);
        ufeedback = (EditText) findViewById(R.id.feedback);
        submit = (Button) findViewById(R.id.submitf);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = uname.getText().toString().trim();
                String feedback = ufeedback.getText().toString().trim();
                String mail="aidd.app.2020@gmail.com";
                String message="Name:"+name+"\nFeedback:"+feedback;
                String subject="Feedback";
                JavaMailAPI javaMailAPI=new JavaMailAPI(Feedback.this,mail,subject,message);
                javaMailAPI.execute();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SessionManagement sessionManagement=new SessionManagement(Feedback.this);
                        sessionManagement.removeSession();
                        Toast.makeText(Feedback.this,"Successfully logged out!",Toast.LENGTH_SHORT).show();
                        Intent yIntent = new Intent(Feedback.this, HomeActivity.class);
                        yIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(yIntent);
                    }
                }, 10000);
//                SessionManagement sessionManagement=new SessionManagement(Feedback.this);
//                sessionManagement.removeSession();
//                Toast.makeText(Feedback.this,"Successfully logged out!",Toast.LENGTH_SHORT).show();
//                Intent yIntent = new Intent(Feedback.this, HomeActivity.class);
//                yIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(yIntent);
            }
        });
    }


}