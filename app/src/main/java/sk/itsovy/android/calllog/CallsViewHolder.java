package sk.itsovy.android.calllog;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// popisuje jednu polozku v recycler view, resp. popisuje view pre jednu polozku
public class CallsViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;

    public CallsViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.singleCallTextView);
    }

    public void bind(Call call) {
        textView.setText(call.getNumber());
    }
}
