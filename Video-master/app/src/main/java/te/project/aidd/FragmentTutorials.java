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
import java.util.List;

public class FragmentTutorials extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private ArrayList<Model> models;
    MyAdapter myAdapter;
    public FragmentTutorials() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_tutorials,container,false);
        recyclerView=(RecyclerView)v.findViewById(R.id.tutorials_recyclerview);
        myAdapter=new MyAdapter(getContext(),models);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myAdapter);
    return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        models=new ArrayList<>();
        models.add(new Model("Find The Match", R.drawable.findthematch));
        models.add(new Model("Colour Match", R.drawable.colour));
        models.add(new Model("Puzzle", R.drawable.jigsaw));
        models.add(new Model("Blink and click", R.drawable.simon_cover));
    }
}
