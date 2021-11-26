package te.project.aidd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.Nullable;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import te.project.aidd.MakeColor;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;



//import te.project.aidd.ui.exercises.ExercisesFragment;

public class ColourMatch extends AppCompatActivity{
    private TextView score,meaning,level,game_score;
    private ImageView image,image2;
    private boolean answer;
    private Button yes,no;
    public int points=0;
    private int question=0,decision,neww;
    private int no_of_q=0;
    public float analysis;
    private TextView timer ;
    private static final long COUNTDOWN_IN=30000;
    private CountDownTimer cd;
    public long timeleft;
    public static int make_level_1_points;
    public static int make_level_1_analysis;
    int flag=1, fla=1;
    DatabaseHelper db;
    Animation animation;
    CardView c1,c2;
    int amms=0;
    int sheet_list[]=new int[4];
    int item=0;
    int level_no=1;
    pop popup=new pop(ColourMatch.this);
    int new_array[]=new int[12];
    int analysis_sum=0;
    public static final String SHARED_PREFS = "shared_Prefs";












    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_match);
        db=new DatabaseHelper(this);
        score=(TextView) findViewById(R.id.score);
        image=(ImageView) findViewById((R.id.color));
        yes=(Button) findViewById(R.id.btn_yes);
        no=(Button) findViewById(R.id.btn_no);
        timer=(TextView) findViewById(R.id.time);
        meaning=findViewById(R.id.meaning);
        level=(TextView) findViewById(R.id.level);
        c1= findViewById(R.id.c1);
        c2= findViewById(R.id.c2);
        image2= (ImageView) findViewById(R.id.bb);
        //Random ran=new Random();
        //question=ran.nextInt(14);
        updateQuestion();
        timeleft=COUNTDOWN_IN;
        startCountDown();





        //logic
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer){
                    points++;

                    updateScore(points);
                    Toast.makeText(ColourMatch.this, "Correct", Toast.LENGTH_SHORT).show();
                    //perform this check before you update the question
                    if(question==Questions.answers.length){
                        question=0;
                        updateQuestion();
                    }
                    else{
                        updateQuestion();
                    }
                }
                else {
                    Toast.makeText(ColourMatch.this, "Wrong",Toast.LENGTH_SHORT).show();
                    points=points-1;
                    updateScore(points);
                    if(question==Questions.answers.length){
                        question=0;
                        updateQuestion();
                    }
                    else{
                        updateQuestion();
                    }

                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answer){
                    points++;
                    updateScore(points);
                    Toast.makeText(ColourMatch.this, "Correct", Toast.LENGTH_SHORT).show();
                    //perform this check before you update the question
                    if(question==Questions.answers.length){
                        question=0;
                        updateQuestion();
                    }
                    else{
                        updateQuestion();
                    }
                }
                else {
                    Toast.makeText(ColourMatch.this, "Wrong", Toast.LENGTH_SHORT).show();
                    points=points-1;
                    updateScore(points);
                    if(question==Questions.answers.length){
                        question=0;
                        updateQuestion();
                    }
                    else{
                        updateQuestion();
                    }

                }
            }
        });
    }


    private void updateQuestion(){
        Random ran=new Random();
        question=ran.nextInt(15);
        image.setImageResource(Questions.images[question]);
        answer=Questions.answers[question];
        animation= AnimationUtils.loadAnimation(ColourMatch.this,R.anim.textanim);
        image.startAnimation(animation);
        if(points>=10){
            level.setText("LEVEL 2");
            make_level_1_points=points;
            amms=1;
            level_no++;
            popup.startlevelpop();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    flag=0;
                    popup.dismisslevelpop();


                }
            },1250);
            Intent intent= new Intent(ColourMatch.this,MakeColor.class);
            long timetaken=30000-timeleft;
            timetaken=timetaken/1000;
            analysis= (float) ((0.6*points/no_of_q)+ (0.4*no_of_q/timetaken));
            int results =(int)(analysis*100);
            make_level_1_analysis=results;
            fla=0;
            Log.i("level_1","points:"+ points+"no of ques:"+no_of_q+"anal:"+results+"timetaken:"+timetaken);
            intent.putExtra("color_points",points);
            intent.putExtra("no_of_q",no_of_q);
            intent.putExtra("level_1_results",results);
            intent.putExtra("level",level_no);
            Intent instrut2=new Intent(ColourMatch.this,Color_Instruct2.class);
            startActivity(instrut2);

        }
//        else if(points>5 | flag==0){
//            if(flag==1){
//                popup.startlevelpop();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        flag=0;
//                        popup.dismisslevelpop();
//
//
//                    }
//                },1250);
//            }
//            level2();
//        }
//        else {}

        question++;
        no_of_q++;

    }


//    public void level2(){
//        LinearLayout.LayoutParams layoutParams=(LinearLayout.LayoutParams) c1.getLayoutParams();
//        layoutParams.height=420;
//        c1.setLayoutParams(layoutParams);
//        c2.setVisibility(View.VISIBLE);
//        level.setText("LEVEL 2");
//        meaning.setVisibility(View.VISIBLE);
//        image.setImageResource(Questions.meaning[question]);
//        image2.setImageResource(Questions.textcolor[question]);
//        image2.startAnimation(animation);
//        answer=Questions.answers[question];
//    }

    public void updateScore(int point){
        score.setText(""+points);
    }
    public void startCountDown(){
        cd=new CountDownTimer(timeleft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleft=millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                long timetaken=30000-timeleft;
                timetaken=timetaken/1000;
                neww= (int) timetaken;
                timeleft=0;
                // analysis of the game without model by 60% to 40% equation
                analysis= (float) ((0.6*points/no_of_q)+ (0.4*no_of_q/timetaken));
                int results =(int)(analysis*100);
                if (fla==1)
                {
                SessionManagement ses=new SessionManagement(ColourMatch.this);
                String email=db.getEmailForChild(ses.getTableID());
                Log.i("youuu",ses.getTableID()+" ");
                //if(level.getText().toString().equals("LEVEL 2") | level.getText().toString().equals("LEVEL 1") ){
                    db.addscore(points,email);
                    db.time_analysis(results,email);
                    System.out.println("AAAAAA" +analysis_sum/12);
                    db.color_match_30(email,ses.getnaaam(),points,results);
                    if(amms==0){
                        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                        String userrr=db.getEmailForChild(ses.getTableID())+"colorweek";
                        sendData(sharedPreferences.getInt(userrr,0));

                    }

                    Log.i("hello","points:"+ points+"no of ques:"+no_of_q+"anal:"+results);
                }

                if(decision!=1){

                popup.startpop();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        popup.dismisspop();
                        cd.cancel();
                        finish();
                    }
                },4000);}

            }
        }.start();

    }
    public void updateCountDownText(){
        int minutes=(int)(timeleft/1000)/60;
        int seconds=(int)(timeleft/1000)%60;
        String timeformat=String.format(Locale.getDefault(),"00:%02d",seconds);
        timer.setText(timeformat);
        if(timeleft<10000){
            timer.setTextColor(Color.RED);
        }else {
            timer.setTextColor(Color.BLACK);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            decision=1;
            finish();


        }
        return super.onKeyDown(keyCode, event);
    }
    private void addItemToSheet() {

        //final ProgressDialog loading = ProgressDialog.show(this, "Adding Item", "Please wait");
        //final String name = editTextItemName.getText().toString().trim();
        //final String brand = editTextBrand.getText().toString().trim();
        SessionManagement ses=new SessionManagement(ColourMatch.this);
         final String email=db.getEmailForChild(ses.getTableID());
         final String child_name=ses.getnaaam();
         sheet_list=db.colorweek_fetch(email);
         Log.d("yooo","yooo");
         final String game_1="Analysis:"+sheet_list[0];
         final String game_2="Analysis:"+sheet_list[1];
         final String game_3="Analysis:"+sheet_list[2];
         final String game_4="Analysis:"+sheet_list[3];


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbzfURLQdGxY6Bv6h20S9kCSgi_4WwEG4ZGTKjQSwPhg-R4D9i2dYbsVRvopoBqMzEOD/exec?",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //loading.dismiss();
                        //Toast.makeText(Additems.this, response, Toast.LENGTH_LONG).show();
                        //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        //startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("action", "addItem");
                parmas.put("child_name", child_name);
                parmas.put("email",email);
                parmas.put("week1",game_1);
                parmas.put("week2",game_2);
                parmas.put("week3",game_3);
                parmas.put("week4",game_4);

                return parmas;
            }
        };

        int socketTimeOut = 50000;

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);


    }

    public void sendData(int noOfgames){
        SessionManagement ss= new SessionManagement(this);

        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String userrr=db.getEmailForChild(ss.getTableID())+"colorweek";

        if(noOfgames==12){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(userrr,0);
            editor.apply();
            new_array=db.sheet_colormatch(db.getEmailForChild(ss.getTableID()));
            System.out.println(new_array);
            for(int i=0;i<12;i++){
                analysis_sum=analysis_sum+new_array[i];
            }
            db.addcolorweek(db.getEmailForChild(ss.getTableID()),analysis_sum/12);
            addItemToSheet();
        }
        else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(userrr,noOfgames+1);
            editor.apply();
            Log.i("didnt send dataaa",sharedPreferences.getInt(userrr,0)+"");
        }


    }



}

