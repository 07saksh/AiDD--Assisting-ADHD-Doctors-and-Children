package te.project.aidd.ui.progress;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProgressViewModel  extends ViewModel {
    private MutableLiveData<String> mText;

    public ProgressViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is logout fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
