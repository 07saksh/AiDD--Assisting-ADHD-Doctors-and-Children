package te.project.aidd;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter1 extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Model> models;

    public MyAdapter1(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,null);
        final MyHolder myholder= new MyHolder(v);
        myholder.model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                c.startActivity(new Intent(c, YoutubeVideo.class));
                int a=myholder.getAdapterPosition();
//                Toast.makeText(c,"test click"+String.valueOf(myholder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
                if(a==0){
                    c.startActivity(new Intent(c, YoutubeVideo4.class));
                }
                else if(a==1){
                    c.startActivity(new Intent(c, YoutubeVideo5.class));
                }

            }
        });
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.mTitle.setText(models.get(position).getTitle());
        holder.mImg.setImageResource(models.get(position).getImg());

        Animation animation= AnimationUtils.loadAnimation(c,android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
