package te.project.aidd.ui.videos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import te.project.aidd.FragmentSos;
import te.project.aidd.FragmentTutorials;
import te.project.aidd.R;
import te.project.aidd.ViewPagerAdapter;

public class VideoFragment extends Fragment {

    private TabLayout tablayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private Context _context;

    private VideoViewModel videoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        videoViewModel =
                ViewModelProviders.of(this).get(VideoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_video, container, false);
        tablayout=(TabLayout)root.findViewById(R.id.tablayout_id);
        viewPager=(ViewPager)root.findViewById(R.id.viewpager_id);
        adapter=new ViewPagerAdapter(getChildFragmentManager());

        adapter.AddFragment(new FragmentTutorials(),"Tutorials");
        adapter.AddFragment(new FragmentSos(),"Others");


        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);

       

//        final TextView textView = root.findViewById(R.id.text_gallery);
//        videoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        _context = context;
    }
}