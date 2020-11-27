package sk.itsovy.android.calllog;

import android.provider.CallLog;

public class Call {

    private String number;

    private int type;

    public Call(String number, int type) {
        this.number = number;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public int getType() {
        return type;
    }

    public String getStringType() {
        if (type == CallLog.Calls.INCOMING_TYPE) {
            return "Incoming";
        }
        if (type == CallLog.Calls.OUTGOING_TYPE) {
            return "Outgoing";
        }
        if (type == CallLog.Calls.MISSED_TYPE) {
            return "Missed";
        }
        if (type == CallLog.Calls.REJECTED_TYPE) {
            return "Rejected";
        }
        return "N/A";
    }

    @Override
    public String toString() {
        return number;
    }
}
