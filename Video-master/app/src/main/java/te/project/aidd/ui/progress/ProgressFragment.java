package te.project.aidd.ui.progress;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import te.project.aidd.Colormatchp;
import te.project.aidd.FindMatchProgress;
import te.project.aidd.Flipcardsp;
import te.project.aidd.HomeActivity;
import te.project.aidd.R;
import te.project.aidd.Simon_progress;
import te.project.aidd.ui.exercises.ExercisesFragment;
import te.project.aidd.ui.logout.LogoutViewModel;

public class ProgressFragment extends Fragment {
    private ProgressViewModel progressViewModel;
    CardView colormatch,findmatch,jigsaw,simon;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        progressViewModel =
                ViewModelProviders.of(this).get(ProgressViewModel.class);
        View root = inflater.inflate(R.layout.fragment_progress, container, false);
        colormatch=root.findViewById(R.id.colormatch);
        findmatch=root.findViewById(R.id.findmatch);
        jigsaw=root.findViewById(R.id.puzzle);
        simon=root.findViewById(R.id.simon);
        colormatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cmpin=new Intent(ProgressFragment.this.getActivity(), Colormatchp.class);
                startActivity(cmpin);
            }
        });
        findmatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fcpin=new Intent(ProgressFragment.this.getActivity(), FindMatchProgress.class);
                startActivity(fcpin);
            }
        });
        jigsaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jpin=new Intent(ProgressFragment.this.getActivity(), Flipcardsp.class);
                startActivity(jpin);

            }
        });
        simon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent spin=new Intent(ProgressFragment.this.getActivity(), Simon_progress.class);
                startActivity(spin);

            }
        });

        return root;
    }

}

