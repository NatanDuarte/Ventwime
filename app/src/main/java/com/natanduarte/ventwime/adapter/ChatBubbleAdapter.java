package com.natanduarte.ventwime.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.natanduarte.ventwime.R;
import com.natanduarte.ventwime.model.VentMessage;

import java.util.List;

public class ChatBubbleAdapter extends BaseAdapter {

    private final Context context;
    private final List<VentMessage> messages;

    public ChatBubbleAdapter(Context context, List<VentMessage> messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public VentMessage getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return messages.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder")
        View newView = LayoutInflater
                .from(context)
                .inflate(R.layout.item_chat_bubble, viewGroup, false);

        TextView chatBubbleContent = newView.findViewById(R.id.item_chat_bubble_content);
        chatBubbleContent.setText(this.messages.get(position).getContent());

        newView.postDelayed(() -> {
            newView.setVisibility(View.GONE);
            if (!messages.isEmpty()) {
                this.remove(this.getItem(position));
                this.notifyDataSetChanged();
            }
        }, 5000);

        return newView;
    }

    public void remove(VentMessage message) {
        this.messages.remove(message);
    }

    public void add(VentMessage message) {
        this.messages.add(message);
    }

    public void clear() {
        messages.clear();
    }
}
