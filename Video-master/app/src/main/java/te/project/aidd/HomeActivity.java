package te.project.aidd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    public Button button2;
    public Button button;
    TextView parRegister;
    public void init2(){
        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent par=new Intent(HomeActivity.this,ParentLoginActivity.class);
                startActivity(par);
            }
        });
    }

    public void init(){
        button2=(Button)findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent par=new Intent(HomeActivity.this,ChildLoginActivity.class);
                startActivity(par);
            }
        });
        parRegister=(TextView) findViewById(R.id.textview_register);
        parRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent registerIntent=new Intent(HomeActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //check if user is logged in
        //if user is logged in -->move to their particular pageee
        SessionManagement sessionManagement=new SessionManagement(HomeActivity.this);
        int userID=sessionManagement.getSession();
        if (userID==1){
            Intent par=new Intent(HomeActivity.this,ChildNavActivity.class);
            par.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(par);
        }
        else if (userID==0){
            Intent par=new Intent(HomeActivity.this,ParentNavActivity.class);
            par.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(par);
        }
        else{
            //do nothing
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        init2();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to quit AiDD?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HomeActivity.super.onBackPressed();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
}