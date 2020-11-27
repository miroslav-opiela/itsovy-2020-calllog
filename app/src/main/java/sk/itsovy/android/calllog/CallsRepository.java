package sk.itsovy.android.calllog;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.provider.CallLog;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class CallsRepository {

    private Context context;

    public CallsRepository(Context context) {
        this.context = context;
    }

    public LiveData<List<Call>> getLiveData() {
        return new LiveData<List<Call>>() {
            private ContentObserver observer;

            @Override
            protected void onActive() {
                observer = new ContentObserver(null) {

                    // metoda sa zavola ked sa zmenia udaje o telefonatoch
                    @Override
                    public void onChange(boolean selfChange) {
                        //1. nacitam si nove udaje o telefonatoch - loadCalls
                        //2. zmenim live data
                        postValue(loadCalls());
                    }
                };
                // prihlasenie posluchaca na zmeny v datach o telefonatoch
                context.getContentResolver().registerContentObserver(CallLog.Calls.CONTENT_URI,
                        true, observer);
                // nech sa nacitaju data aj pri zaregistrovani obervera - teda pri spusteni
                observer.onChange(true);
            }

            @Override
            protected void onInactive() {
                // odhlasenie posluchaca, ked sa odhlasi posluchac na live data
                context.getContentResolver().unregisterContentObserver(observer);
            }
        };
    }


    public List<Call> loadCalls() {
        ContentResolver contentResolver = context.getContentResolver();
        // ak chcem iba tel. cisla, tak v projection nazvy stlpcov
        Cursor cursor = contentResolver.query(CallLog.Calls.CONTENT_URI,
                null, null, null, null);
        List<Call> calls = new ArrayList<>();
        int count = 0;
        if (cursor != null) {
            count = cursor.getCount();
        }
        if (count > 0) {
            int idx = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int typeIdx = cursor.getColumnIndex(CallLog.Calls.TYPE);
            while (cursor.moveToNext()) {
                String number = cursor.getString(idx);
                int type = cursor.getInt(typeIdx);
                Call call = new Call(number, type);
                calls.add(call);

            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return calls;
    }

}
