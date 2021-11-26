package te.project.aidd.ui.exercises;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import te.project.aidd.R;

public class ExercisesViewModel extends ViewModel {


    private MutableLiveData<String> mText;

    public ExercisesViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("EXERCISES");

    }

    public LiveData<String> getText() {
        return mText;
    }
}