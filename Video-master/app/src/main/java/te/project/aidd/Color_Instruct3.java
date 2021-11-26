package te.project.aidd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Color_Instruct3 extends AppCompatActivity {
    Button cm,tutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color__instruct3);
        cm=(Button) findViewById(R.id.cm);
        tutorial=(Button) findViewById(R.id.tutorial_colour3);
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tut=new Intent(Color_Instruct3.this, YoutubeVideo1.class);
                startActivity(tut);
                finish();
            }
        });
        cm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cmin=new Intent(Color_Instruct3.this,ColorMatch_level3.class);
                startActivity(cmin);


                finish();
            }
        });
    }
}