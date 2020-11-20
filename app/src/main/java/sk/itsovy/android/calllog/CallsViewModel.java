package sk.itsovy.android.calllog;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class CallsViewModel extends AndroidViewModel {

    private CallsRepository repository;

    public CallsViewModel(@NonNull Application application) {
        super(application);
        repository = new CallsRepository(application);
    }

    public List<Call> getCalls() {
        return repository.loadCalls();
    }
}
