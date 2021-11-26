package te.project.aidd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
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
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class ColorMatch_level3 extends AppCompatActivity {
    private int question = 0,decision;
    Animation animation;
    private ImageView image;
    String colorvalue="";
    Button redb,yellowb,whiteb,blackb,blueb,greenb;
    private int score1=0;
    TextView points;
    private static final long COUNTDOWN_IN=45000;
    private CountDownTimer cd;
    public long timeleft;
    DatabaseHelper db;
    TextView timer;
    Interpreter interpreter;
    int quesss=0;
    int LEVEL_2_POINTS;
    pop popup=new pop(ColorMatch_level3.this);
    MakeColor mc=new MakeColor();
    int final_colormatch;
    ColourMatch cm=new ColourMatch();
    int sheet_list[]=new int[4];
    int new_array[]=new int[12];
    int analysis_sum=0;
    public static final String SHARED_PREFS = "shared_Prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_match_level3);
        try {
            interpreter=new Interpreter(loadModelfile(),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        image=findViewById(R.id.color);
        redb=(Button) findViewById(R.id.redb);
        yellowb=(Button) findViewById(R.id.yellowb);
        greenb=(Button) findViewById(R.id.greenb);
        blackb=(Button) findViewById(R.id.blackb);
        blueb=(Button) findViewById(R.id.blueb);
        whiteb=(Button) findViewById(R.id.whiteb);
        points=findViewById(R.id.marks);
        timer=findViewById(R.id.time);
        updateQuestion();
        timeleft=COUNTDOWN_IN;
        startCountDown();
        db=new DatabaseHelper(this);


        redb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorvalue="red";
                checkAnswer(colorvalue);


            }
        });
        yellowb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorvalue="yellow";
                checkAnswer(colorvalue);

            }
        });

        blueb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorvalue="blue";
                checkAnswer(colorvalue);
            }
        });

        greenb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorvalue="green";
                checkAnswer(colorvalue);
            }
        });
        blackb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorvalue="black";
                checkAnswer(colorvalue);
            }
        });
        whiteb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorvalue="white";
                checkAnswer(colorvalue);

            }
        });

    }


    private void checkAnswer(String colorvalue){
        String answers=Questions.solution[question];
        if(answers.equals(colorvalue)){
            score1=score1+1;
            points.setText(score1+"");
            Log.d("score",score1+"");
        }
        else{
            score1=score1-1;
            points.setText(score1+"");
            Log.d("score",score1+"");
        }
        colorvalue="";
        updateQuestion();
    }

    private void updateQuestion() {
        Random ran = new Random();
        question = ran.nextInt(14);
        image.setImageResource(Questions.images[question]);
        animation = AnimationUtils.loadAnimation(ColorMatch_level3.this, R.anim.textanim);
        image.startAnimation(animation);
        quesss++;
    }

    public void startCountDown() {
        cd = new CountDownTimer(timeleft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleft = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                long timetaken = 30000 - timeleft;
                timetaken = timetaken / 1000;
                timeleft = 0;
                if(decision!=1){

                    popup.startpop();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            popup.dismisspop();
                            LEVEL_2_POINTS=mc.make_level_2_analysis;
                            final_colormatch=(int)doInference(LEVEL_2_POINTS,score1,quesss);
                            if (final_colormatch>100){
                                final_colormatch=97;
                            }
                            int total=cm.make_level_1_points+ mc.make_level_2_points+score1;
                            SessionManagement ses=new SessionManagement(ColorMatch_level3.this);
                            String email=db.getEmailForChild(ses.getTableID());
                            Log.i("youuu",ses.getTableID()+" ");
                            db.addscore(total,email);
                            db.time_analysis(final_colormatch,email);
                            db.color_match_30(email,ses.getnaaam(),total,final_colormatch);
                            SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                            String userrr=db.getEmailForChild(ses.getTableID())+"colorweek";
                            sendData(sharedPreferences.getInt(userrr,0));
                            System.out.println(final_colormatch+"pointsss"+total);
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
    private MappedByteBuffer loadModelfile() throws IOException {
        AssetFileDescriptor assetFileDescriptor=this.getAssets().openFd("colorlevel3.tflite");
        FileInputStream fileInputStream=new FileInputStream(assetFileDescriptor.getFileDescriptor());
        FileChannel fileChannel=fileInputStream.getChannel();
        long startOffset=assetFileDescriptor.getStartOffset();
        long length=assetFileDescriptor.getLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY,startOffset,length);

    }
    public  float doInference(int level2, int score,int no_of_quess){
        float[][] input= new float[1][3];
        input[0][0]=(float)(level2);
        input[0][1]=(float)(score);
        input[0][2]=(float)(no_of_quess);
        float[][] output=new float[1][1];
        interpreter.run(input,output);
        return output[0][0];


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
    private void addItemToSheet() {

        //final ProgressDialog loading = ProgressDialog.show(this, "Adding Item", "Please wait");

        SessionManagement ses=new SessionManagement(ColorMatch_level3.this);
        final String email=db.getEmailForChild(ses.getTableID());
        final String child_name=ses.getnaaam();
        sheet_list=db.colorweek_fetch(email);
        final String game_1="Analysis:"+sheet_list[0];
        final String game_2="Analysis:"+sheet_list[1];
        final String game_3="Analysis:"+sheet_list[2];
        final String game_4="Analysis:"+sheet_list[3];


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbzfURLQdGxY6Bv6h20S9kCSgi_4WwEG4ZGTKjQSwPhg-R4D9i2dYbsVRvopoBqMzEOD/exec?",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //loading.dismiss();

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


}