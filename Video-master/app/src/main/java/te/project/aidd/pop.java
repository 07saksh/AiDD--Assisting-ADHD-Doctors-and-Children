package te.project.aidd;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppComponentFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class pop{
    Activity activity;
    AlertDialog dialog;

    pop(Activity myac){
        activity=myac;
    }
    void startpop(){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.pop1,null));
        builder.setCancelable(true);
        dialog=builder.create();
        dialog.show();
    }

    void dismisspop(){
        dialog.dismiss();

    }
    void startlevelpop(){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.levelup,null));
        builder.setCancelable(true);
        dialog=builder.create();
        dialog.show();
    }

    void dismisslevelpop(){
        dialog.dismiss();

    }
    void startincorrect(){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.incorrect,null));
        builder.setCancelable(true);
        dialog=builder.create();
        dialog.show();
    }

    void dismissincorrect(){
        dialog.dismiss();

    }
    void startcorrect(){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.correct,null));
        builder.setCancelable(true);
        dialog=builder.create();
        dialog.show();
    }

    void dismisscorrect(){
        dialog.dismiss();

    }

}
