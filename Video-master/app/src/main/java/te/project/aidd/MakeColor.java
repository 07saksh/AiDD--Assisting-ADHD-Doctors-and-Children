package te.project.aidd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
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

public class MakeColor extends AppCompatActivity {
    Button redb,yellowb,whiteb,blackb,blueb,greenb;
    ImageView yourcolor, givencolor;
    String colorvalue="";
    private int count=-1,decision;
    private static final long COUNTDOWN_IN=45000;
    private CountDownTimer cd;
    public long timeleft;
    String checktag="";
    pop popup=new pop(MakeColor.this);
    Random r=new Random();
    Button clear;
    int flag=1;
    DatabaseHelper db;
    int sheet_list[]=new int[4];
    TextView score,timer;
    Interpreter interpreter;
    long timetaken;
    int anal;
    int ams=0;
    float level_2_results;
    int points1 ,no_of_q1=0;
    int level_1_results, level_1_points;
    public static int make_level_2_analysis,make_level_2_points;
    ColourMatch cm=new ColourMatch();
    int new_array[]=new int[12];
    int analysis_sum=0;
    public static final String SHARED_PREFS = "shared_Prefs";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_color);
        try {
            interpreter=new Interpreter(loadModelfile(),null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        db=new DatabaseHelper(this);
        redb=(Button) findViewById(R.id.redb);
        yellowb=(Button) findViewById(R.id.yellowb);
        greenb=(Button) findViewById(R.id.greenb);
        blackb=(Button) findViewById(R.id.blackb);
        blueb=(Button) findViewById(R.id.blueb);
        whiteb=(Button) findViewById(R.id.whiteb);
        yourcolor=findViewById((R.id.yourcolor));
        givencolor=findViewById((R.id.givencolor));
        score=findViewById(R.id.score);
        clear=findViewById(R.id.clear);
        timer=findViewById(R.id.time);
        count=r.nextInt(Questions.colorss.length);
        newQuestion();
        timeleft=COUNTDOWN_IN;
        startCountDown();

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorvalue="";
                yourcolor.setImageResource(R.drawable.plain);
            }
        });

        redb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorvalue=colorvalue+"red";
                adding_color(colorvalue);

            }
        });
        yellowb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorvalue=colorvalue+"yellow";
                adding_color(colorvalue);
            }
        });

        blueb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorvalue=colorvalue+"blue";
                adding_color(colorvalue);
            }
        });

        greenb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorvalue=colorvalue+"green";
                adding_color(colorvalue);
            }
        });
        blackb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorvalue=colorvalue+"black";
                adding_color(colorvalue);
            }
        });
        whiteb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorvalue=colorvalue+"white";
                adding_color(colorvalue);
            }
        });

    }
    public void adding_color(String colorvalue){
        switch (colorvalue){
            case "red":
                yourcolor.setImageResource(R.drawable.red);
                checktag="red";
                checkCombination();
                break;
            case "redyellow":
            case "yellowred":
                yourcolor.setImageResource(R.drawable.orange);
                checktag="orange";
                checkCombination();
                break;
            case "yellow":
                yourcolor.setImageResource(R.drawable.yelloe);
                checktag="yellow";
                checkCombination();
                break;
            case "blue":
                yourcolor.setImageResource(R.drawable.blue);
                checktag="blue";
                checkCombination();
                break;
            case "redblue":
            case "bluered":
                yourcolor.setImageResource(R.drawable.purple);
                checktag="purple";
                checkCombination();
                break;
            case "blackwhite":
            case "whiteblack":
                yourcolor.setImageResource(R.drawable.grey);
                checktag="grey";
                checkCombination();
                break;
            case "black":
                yourcolor.setImageResource(R.drawable.black);
                checktag="black";
                checkCombination();
                break;
            case "white":
                yourcolor.setImageResource(R.drawable.white);
                checktag="white";
                checkCombination();
                break;
            case "redwhite":
            case "whitered":
                yourcolor.setImageResource(R.drawable.pink);
                checktag="pink";
                checkCombination();
                break;
            case "bluewhite":
            case "whiteblue":
                yourcolor.setImageResource(R.drawable.lightblue);
                checktag="lightblue";
                checkCombination();
                break;
            case "green":
                yourcolor.setImageResource(R.drawable.green);
                checktag="green";
                checkCombination();
                break;
            case "redbluewhite":
            case "whitebluered":
            case "redwhiteblue":
            case "blueredwhite":
                yourcolor.setImageResource(R.drawable.lightpurple);
                checktag="lightpurple";
                checkCombination();
                break;
            case "greenblue":
            case "bluegreen":
                yourcolor.setImageResource(R.drawable.greenblue);
                checktag="greenblue";
                checkCombination();
                break;
            case "greenblack":
            case "blackgreen":
                yourcolor.setImageResource(R.drawable.darkgreen);
                checktag="darkgreen";
                checkCombination();
                break;
            case "blueblack":
            case "blackblue":
                yourcolor.setImageResource(R.drawable.darkblue);
                checktag="darkblue";
                checkCombination();
                break;
            case "greenred":
            case "redgreen":
                yourcolor.setImageResource(R.drawable.brown);
                checktag="brown";
                checkCombination();
                break;

            default:
                popup.startincorrect();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        popup.dismissincorrect();
                        yourcolor.setImageResource(R.drawable.plain);
                        newQuestion();

                    }
                },1000);

                break;
        }
    }

    public void newQuestion(){
        if(points1>=15){
            ams=1;
            level_1_points=cm.make_level_1_points;
            level_1_results=cm.make_level_1_analysis;
            level_2_results=(float) points1*100/(2*no_of_q1);
            anal=(int) (level_1_results+level_2_results)/2;
            make_level_2_analysis=anal;
            make_level_2_points=points1;
            popup.startlevelpop();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    flag=0;
                    popup.dismisslevelpop();


                }
            },1250);
            Log.i("level 2__  ","analysissss "+anal+"levelll"+level_2_results);
         Intent in=new Intent(MakeColor.this,Color_Instruct3.class);
         startActivity(in);

        }
        else{

            no_of_q1++;
            int len=Questions.colorss.length;
            if(count==(len-1)){
                count=0;
            }
            else {
                count++;
            }
            givencolor.setImageResource(Questions.colorss[count]);
            givencolor.setTag(Questions.match[count]);
            colorvalue="";

        }

    }

    public void checkCombination(){
        String t= givencolor.getTag().toString();
        if(t.equals(checktag)){
            points1=points1+2;
            score.setText(points1+"");
            popup.startcorrect();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    popup.dismisscorrect();
                    yourcolor.setImageResource(R.drawable.plain);
                    newQuestion();

                }
            },1500);
        }
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
                timetaken=45000-timeleft;
                timetaken=timetaken/1000;
                timeleft=0;
                if(decision!=1){
                popup.startpop();
                    final long finalTimetaken = timetaken;
                    new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cd.cancel();
                        SessionManagement ses=new SessionManagement(MakeColor.this);
                        String email=db.getEmailForChild(ses.getTableID());
                       // int analysis=(int)doInference(level_1_results,no_of_q,points1,no_of_q1);
                        level_1_points=cm.make_level_1_points;
                        level_1_results=cm.make_level_1_analysis;
                        Log.i("level 1 POINTS",level_1_points+"");
                        level_2_results= (float) points1*100/(2*no_of_q1);
                        //level_2_results= level_2_results*100;
                        int analysis=(int) (level_1_results+level_2_results)/2;
                        Log.i("level 2: ","LEVEL 2 ANALYSIS"+analysis+"level"+level_2_results);
                        db.addscore((level_1_points+points1),email);
                        db.time_analysis(analysis,email);
                        db.color_match_30(email,ses.getnaaam(),(level_1_points+points1),analysis);
                        if(ams==0){
                            SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                            String userrr=db.getEmailForChild(ses.getTableID())+"colorweek";
                            sendData(sharedPreferences.getInt(userrr,0));
                        }

                        popup.dismisspop();
                        finish();
                    }
                },3000);}

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
    private MappedByteBuffer loadModelfile() throws IOException {
        AssetFileDescriptor assetFileDescriptor=this.getAssets().openFd("colormatch.tflite");
        FileInputStream fileInputStream=new FileInputStream(assetFileDescriptor.getFileDescriptor());
        FileChannel fileChannel=fileInputStream.getChannel();
        long startOffset=assetFileDescriptor.getStartOffset();
        long length=assetFileDescriptor.getLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY,startOffset,length);

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

        SessionManagement ses=new SessionManagement(MakeColor.this);
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


    public void sendData(int noOfgames) {
        SessionManagement ss = new SessionManagement(this);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String userrr = db.getEmailForChild(ss.getTableID()) + "colorweek";

        if (noOfgames == 12) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(userrr, 0);
            editor.apply();
            new_array = db.sheet_colormatch(db.getEmailForChild(ss.getTableID()));
            System.out.println(new_array);
            for (int i = 0; i < 12; i++) {
                analysis_sum = analysis_sum + new_array[i];
            }
            db.addcolorweek(db.getEmailForChild(ss.getTableID()), analysis_sum / 12);
            addItemToSheet();
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(userrr, noOfgames + 1);
            editor.apply();
            Log.i("didnt send dataaa", sharedPreferences.getInt(userrr, 0) + "");
        }


    }}