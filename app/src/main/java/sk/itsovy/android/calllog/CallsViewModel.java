package sk.itsovy.android.calllog;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CallsViewModel extends AndroidViewModel {

    private CallsRepository repository;
    private LiveData<List<Call>> calls;

    public CallsViewModel(@NonNull Application application) {
        super(application);
        repository = new CallsRepository(application);
        calls = repository.getLiveData();
    }

    public LiveData<List<Call>> getCalls() {
        return calls;
    }
}
