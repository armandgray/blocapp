package org.blocorganization.blocapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {

    private TextView chatText;
    private List<ChatMessage> messageList = new ArrayList<>();
    private LinearLayout layout;

    public ChatArrayAdapter(Context context, int layout){
        super(context, layout);

    }

    public void add(ChatMessage chatMessage) {
        messageList.add(chatMessage);
        super.add(chatMessage);
    }

    public int getCount() {
        return this.messageList.size();
    }

    public ChatMessage getItem(int index) {
        return this.messageList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.messages_chat, parent, false);
        }

        layout = (LinearLayout) view.findViewById(R.id.message1);
        ChatMessage messageObj = getItem(position);
        chatText = (TextView) view.findViewById(R.id.singleMessage);
        chatText.setText(messageObj.message);
        chatText.setBackgroundResource(messageObj.left ? R.drawable.in_message_bg : R.drawable.out_message_bg);

        layout.setGravity(messageObj.left ? Gravity.LEFT : Gravity.RIGHT);

        return view;
    }

    public Bitmap decodeToBitmap(byte[] decodeByte) {
        return BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length);
    }
}
