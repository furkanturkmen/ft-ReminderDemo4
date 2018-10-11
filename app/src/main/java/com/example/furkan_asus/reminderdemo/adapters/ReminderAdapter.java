package com.example.furkan_asus.reminderdemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.furkan_asus.reminderdemo.models.Reminder;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {
    private List<Reminder> mReminders;
    final private ReminderClickListener mReminderClickListener;

    public interface ReminderClickListener{
        void reminderOnClick (int i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mReminderClickListener.reminderOnClick(clickedPosition);
        }
    }

    public ReminderAdapter(List<Reminder> reminders, ReminderClickListener mReminderClickListener) {
        this.mReminders = reminders;
        this.mReminderClickListener = mReminderClickListener;
    }

    @Override
    public ReminderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, null);

        // Return a new holder instance

        ReminderAdapter.ViewHolder viewHolder = new ReminderAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.ViewHolder holder, int position) {

        Reminder reminder = mReminders.get(position);

        holder.textView.setText(reminder.getReminderText());
    }

    @Override
    public int getItemCount() {
        return mReminders.size();
    }

    public void swapList (List<Reminder> newList) {
        mReminders = newList;
        if (newList != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }
}
