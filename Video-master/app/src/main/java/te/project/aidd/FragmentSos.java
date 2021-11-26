package te.project.aidd;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentSos extends Fragment {
    View v;
    private RecyclerView recyclerView;
    private ArrayList<Model> models;
    MyAdapter1 myAdapter;
    public FragmentSos() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_sos,container,false);
        recyclerView=(RecyclerView)v.findViewById(R.id.tutorials_recyclerview);
        myAdapter=new MyAdapter1(getContext(),models);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myAdapter);
        return v;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        models=new ArrayList<>();
        models.add(new Model("Disaster", R.drawable.earthquake));
        models.add(new Model("Healthy Routine", R.drawable.dailyschedule));

    }
}
