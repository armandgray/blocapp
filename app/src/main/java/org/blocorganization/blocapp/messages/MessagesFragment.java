package org.blocorganization.blocapp.messages;


import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.ChatMessage;
import org.blocorganization.blocapp.utils.ChatArrayAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessagesFragment extends Fragment {

    private ChatArrayAdapter chatArrayAdapter;
    private ListView list;
    private EditText chatText;
    private Button sendBtn;
    private boolean leftSide = false;

    public MessagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_messages, container, false);

        sendBtn = (Button) rootView.findViewById(R.id.button);
        list = (ListView) rootView.findViewById(R.id.listView);
        chatText = (EditText) rootView.findViewById(R.id.chat);

        chatArrayAdapter = new ChatArrayAdapter(getContext(), R.id.chat);

        chatText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER) {
                    return sendChatMessage();
                }

                return false;
            }

        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendChatMessage();
            }
        });

        list.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        list.setAdapter(chatArrayAdapter);

        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                list.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });

        return rootView;
    }

    private boolean sendChatMessage() {
        chatArrayAdapter.add(new ChatMessage(leftSide, chatText.getText().toString()));
        chatText.setText("");

        leftSide = !leftSide;

        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("FRAG", "Messages onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("FRAG", "Messages onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("FRAG", "Messages onDestroy");
    }

}
