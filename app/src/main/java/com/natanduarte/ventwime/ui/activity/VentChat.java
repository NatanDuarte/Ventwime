package com.natanduarte.ventwime.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.natanduarte.ventwime.R;
import com.natanduarte.ventwime.adapter.ChatBubbleAdapter;
import com.natanduarte.ventwime.model.VentMessage;

import java.util.ArrayList;
import java.util.List;

public class VentChat extends AppCompatActivity {

    private EditText textMessageField;
    private FloatingActionButton fabSendMessage;
    private ListView chatList;
    private ChatBubbleAdapter adapter;
    private List<VentMessage> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vent_chat);
        initializeComponents();
        initAdapter();
        fabSendMessage.setOnClickListener(view -> handleClick());
    }

    private void initializeComponents() {
        textMessageField = findViewById(R.id.chat_text_input);
        textMessageField.requestFocus();
        fabSendMessage = findViewById(R.id.fab_send_vent_message);
        chatList = findViewById(R.id.chat_list);
        messages = new ArrayList<>();
    }

    private void initAdapter() {
        adapter = new ChatBubbleAdapter(this, messages);
        chatList.setAdapter(adapter);
    }

    private void handleClick() {
        String message = textMessageField
                .getText().toString().trim();

        if (message.isEmpty())
            return;

        textMessageField.setText("");
        textMessageField.requestFocus();
        addNewVentMessage(message);
    }

    private void addNewVentMessage(String message) {
        VentMessage ventMessage = new VentMessage(message);
        adapter.add(ventMessage);
        adapter.notifyDataSetChanged();
    }
}