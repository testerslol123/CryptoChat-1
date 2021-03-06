package frontend;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import backend.HomeScreenData;
import backend.Message;

import com.example.crypto_app.R;

public class ConversationAdapter extends ArrayAdapter<Message> {
	private Context context;
	private final int TYPE_SENDER = 0;
	private final int TYPE_ME = 1;
	private final ArrayList<Message> messages;
	
	public ConversationAdapter(Context context, int conversationPosition) {
		super(context, R.layout.conversation_sender, HomeScreenData.getInstance().conversations.get(conversationPosition).messages);
		messages = HomeScreenData.getInstance().conversations.get(conversationPosition).messages;
		this.context = context;
	}
	
	// Return the appropriate type to determine the layout of the view
	@Override
	public int getItemViewType(int position) {
		if (messages.get(position).sender.getName().equals("Home User")) {
			return TYPE_ME;
		}
		else {
			return TYPE_SENDER;
		}
	}
	
	// Two possible layouts for each view
	@Override
	public int getViewTypeCount() {
		return 2;
	}
	
	// Populate the ListView with messages
	@Override
	public View getView(int position, View convertView, ViewGroup parent) { 
		View myView = convertView;
		int type = getItemViewType(position);
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			switch (type) {
				case TYPE_SENDER:
					myView = (RelativeLayout) inflater.inflate(R.layout.conversation_sender, parent, false);
				case TYPE_ME:
					myView = (RelativeLayout) inflater.inflate(R.layout.conversation_me, parent, false);
			}
		}

		Message message = (Message) getItem(position);
		Log.w("myApp", "p"+position);
		if (type == TYPE_SENDER) { // Set text fields for sender's message
			Log.w("myApp", "4441");
			TextView name = (TextView) myView.findViewById(R.id.conversation_sender_name);
			Log.w("myApp", "4442");
			TextView strMessage = (TextView) myView.findViewById(R.id.conversation_sender_message);
			Log.w("myApp", "4443");
			TextView time = (TextView) myView.findViewById(R.id.conversation_sender_time);
			Log.w("myApp", "4444 " +message.getMessage());
			strMessage.setText(message.getMessage());
			Log.w("myApp", "4445");
			name.setText(message.sender.getName());
			Log.w("myApp", "4446");
			time.setText(message.getTime());
			Log.w("myApp", "4447");

		}
		else { // Or set text fields for home user sent message
			TextView strMessage = (TextView) myView.findViewById(R.id.conversation_me_message);
			TextView time = (TextView) myView.findViewById(R.id.conversation_me_time);
			Log.w("myApp", "p"+message.getMessage());
			strMessage.setText(message.getMessage());
			time.setText(message.getTime());
		}
		Log.w("myApp", "done?"+message.getMessage());
		return myView;
	}
}

