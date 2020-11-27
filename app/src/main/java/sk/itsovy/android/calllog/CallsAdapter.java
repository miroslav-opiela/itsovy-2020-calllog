package sk.itsovy.android.calllog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// poskytuje data pre recycler view
public class CallsAdapter extends RecyclerView.Adapter<CallsViewHolder> {

    private List<Call> calls;

    public void setCalls(List<Call> calls) {
        this.calls = calls;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CallsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View layout = inflater.inflate(R.layout.call_item, parent, false);
        CallsViewHolder viewHolder = new CallsViewHolder(layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CallsViewHolder holder, int position) {
        holder.bind(calls.get(position));
    }

    @Override
    public int getItemCount() {
        return calls.size();
    }
}
