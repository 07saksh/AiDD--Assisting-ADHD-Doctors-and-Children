package te.project.aidd;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {
    ImageView mImg;
    TextView mTitle;
    CardView model;

    public MyHolder(@NonNull View itemView) {
        super(itemView);
        this.mImg=itemView.findViewById(R.id.img);
        this.mTitle=itemView.findViewById(R.id.title);
        this.model=itemView.findViewById(R.id.modelid);

    }
}
