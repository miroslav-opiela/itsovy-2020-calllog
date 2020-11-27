package sk.itsovy.android.calllog;

import android.graphics.Color;
import android.provider.CallLog;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// popisuje jednu polozku v recycler view, resp. popisuje view pre jednu polozku
public class CallsViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewNumber;
    private TextView textViewType;
    private View item;

    public CallsViewHolder(@NonNull View itemView) {
        super(itemView);
        item = itemView;
        textViewNumber = itemView.findViewById(R.id.singleCallTextView);
        textViewType = itemView.findViewById(R.id.callTypeTextView);
    }

    public void bind(Call call) {
        textViewNumber.setText(call.getNumber());
        textViewType.setText(call.getStringType());
        if (call.getType() == CallLog.Calls.REJECTED_TYPE) {
            item.setBackgroundColor(Color.RED);
        }
    }
}
