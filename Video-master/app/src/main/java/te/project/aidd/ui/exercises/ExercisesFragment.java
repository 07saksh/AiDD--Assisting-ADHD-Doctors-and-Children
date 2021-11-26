package te.project.aidd.ui.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import te.project.aidd.Color_instruct;
import te.project.aidd.ColourMatch;
import te.project.aidd.FindMatchInstruct;
import te.project.aidd.FindTheMatch;
import te.project.aidd.Jigsaw;
import te.project.aidd.SimonGame;
import te.project.aidd.SimonInstruct;
import te.project.aidd.JigsawInstructActivity;
import te.project.aidd.R;

public class ExercisesFragment extends Fragment {

    CardView card1;
    CardView card2;
    CardView card3;
    CardView card4;


    private ExercisesViewModel exercisesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        exercisesViewModel =
                ViewModelProviders.of(this).get(ExercisesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_exercises, container, false);
        card1=(CardView)root.findViewById(R.id.c1);
        card2=(CardView)root.findViewById(R.id.c2);
        card3=(CardView)root.findViewById(R.id.c3);
        card4=(CardView)root.findViewById(R.id.c4);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findMatchIntent=new Intent(ExercisesFragment.this.getActivity(), FindMatchInstruct.class);
                startActivity(findMatchIntent);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent flipcardsIntent=new Intent(ExercisesFragment.this.getActivity(), SimonInstruct.class);
                startActivity(flipcardsIntent);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jigsawIntent=new Intent(ExercisesFragment.this.getActivity(), JigsawInstructActivity.class);
                startActivity(jigsawIntent);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent colourmatchIntent=new Intent(ExercisesFragment.this.getActivity(), Color_instruct.class);
                startActivity(colourmatchIntent);
            }
        });
//        final TextView textView = root.findViewById(R.id.text_home);
//        exercisesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        return root;
    }
}