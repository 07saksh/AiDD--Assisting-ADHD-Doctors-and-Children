package te.project.aidd.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import te.project.aidd.Feedback;
import te.project.aidd.HomeActivity;
import te.project.aidd.R;
import te.project.aidd.SessionManagement;
import te.project.aidd.ui.exercises.ExercisesFragment;

public class LogoutFragment extends Fragment {
    private LogoutViewModel logoutViewModel;
    Button y,n;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logoutViewModel =
                ViewModelProviders.of(this).get(LogoutViewModel.class);
        View root = inflater.inflate(R.layout.fragment_logout, container, false);
        y=(Button)root.findViewById(R.id.button3);
        n=(Button)root.findViewById(R.id.button4);
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManagement sessionManagement=new SessionManagement(LogoutFragment.this.getActivity());
                sessionManagement.removeSession();
                Toast.makeText(getActivity(),"Successfully logged out!",Toast.LENGTH_SHORT).show();
//                Intent yIntent=new Intent(LogoutFragment.this.getActivity(), HomeActivity.class);
                Intent yIntent = new Intent(getActivity().getApplicationContext(), HomeActivity.class);
                yIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(yIntent);
            }
        });
        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nIntent=new Intent(LogoutFragment.this.getActivity(), Feedback.class);
                startActivity(nIntent);
            }
        });
        return root;
    }

}

