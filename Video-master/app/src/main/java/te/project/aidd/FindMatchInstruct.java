package te.project.aidd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FindMatchInstruct extends AppCompatActivity {
    Button ftm,tutorial;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String OLD_DATE1 ="04/04/2020";
    public String userrr;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_match_instruct);
        ftm=(Button) findViewById(R.id.ftm);

        db=new DatabaseHelper(this);
        tutorial=(Button) findViewById(R.id.tutorial);
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        final String formattedDate = df.format(c);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String dd=sharedPreferences.getString(OLD_DATE1," ");
        SessionManagement sessionManagement=new SessionManagement(FindMatchInstruct.this);
        userrr=db.getEmailForChild(sessionManagement.getTableID())+"findmatch";

        final int diff=getDateDiffFromNow(sharedPreferences.getString(OLD_DATE1," "));
        System.out.println(diff);
        System.out.println("no of games played"+ sharedPreferences.getInt(userrr,-1));
        if(diff>0 && sharedPreferences.getInt(userrr,0)==2){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(userrr,0);
            editor.apply();

        }
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tut=new Intent(FindMatchInstruct.this, YoutubeVideo.class);
                startActivity(tut);
                finish();
            }
        });

        ftm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                if(diff>0 && sharedPreferences.getInt(userrr,0)==0){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(OLD_DATE1 ,formattedDate );
                    editor.putInt(userrr,1);
                    editor.apply();
                    //Toast.makeText(FindMatchInstruct.this, "Date saved", Toast.LENGTH_SHORT).show();
                    Intent cmin=new Intent(FindMatchInstruct.this,FindTheMatch.class);
                    cmin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(cmin);


                    finish();

                }
                else if(diff>0 && sharedPreferences.getInt(userrr,0)==1){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(OLD_DATE1 ,formattedDate );
                    editor.putInt(userrr,2);
                    editor.apply();
                    //Toast.makeText(FindMatchInstruct.this, "Date saved", Toast.LENGTH_SHORT).show();
                    Intent cmin=new Intent(FindMatchInstruct.this,FindTheMatch.class);
                    cmin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(cmin);


                    finish();

                }
                else if(diff==0 && sharedPreferences.getInt(userrr,0)==2){
                    System.out.println("nooo cant playyy");
                    System.out.println("cant ");

                    AlertDialog.Builder builder=new AlertDialog.Builder(FindMatchInstruct.this);
                    builder.setMessage("You have completed your exercises for the day");
                    builder.setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.setCancelable(false);
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }


                else  if(diff==0 && sharedPreferences.getInt(userrr,0)==0){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(OLD_DATE1 ,formattedDate );
                    editor.putInt(userrr,1);
                    editor.apply();
                    //Toast.makeText(FindMatchInstruct.this, "Date saved", Toast.LENGTH_SHORT).show();
                    Intent cmin=new Intent(FindMatchInstruct.this,FindTheMatch.class);
                    cmin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(cmin);


                    finish();

                }
                else  if(diff==0 && sharedPreferences.getInt(userrr,0)==1){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(OLD_DATE1 ,formattedDate );
                    editor.putInt(userrr,2);
                    editor.apply();
                    //Toast.makeText(FindMatchInstruct.this, "Date saved", Toast.LENGTH_SHORT).show();
                    Intent cmin=new Intent(FindMatchInstruct.this,FindTheMatch.class);
                    cmin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(cmin);


                    finish();

                }
                else{

                }
            }
        });
    }
    public int getDateDiffFromNow(String date){
        int days = 0;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            long diff = new Date().getTime() - sdf.parse(date).getTime();
            long seconds = diff / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            days = ((int) (long) hours / 24);
            Log.i("hello", "Date "+date+" Difference From Now :"+ days + " days");
        }catch (Exception e){
            e.printStackTrace();
        }
        return days;
    }
}