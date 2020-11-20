package sk.itsovy.android.calllog;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;

import java.util.ArrayList;
import java.util.List;

public class CallsRepository {

    private Context context;

    public CallsRepository(Context context) {
        this.context = context;
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
            while (cursor.moveToNext()) {
                String number = cursor.getString(idx);
                Call call = new Call(number);
                calls.add(call);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return calls;
    }

}
