package org.blocorganization.blocapp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Record;

import java.util.List;

public class RecordArrayAdapter extends ArrayAdapter<Record> {

	private Context context;
	private List<? extends Record> objects;
	
	public RecordArrayAdapter(Context context, int resource, List<? extends Record> objects) {
		super(context, resource, (List<Record>) objects);
		this.context = context;
		this.objects = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Record record = objects.get(position);
		
		LayoutInflater inflater = 
				(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.record_listitem, null);
		
//		ImageView image = (ImageView) view.findViewById(R.id.ivRecordImage);
//		image.setImageResource(record.getImageResource());

		TextView tvDesc = (TextView) view.findViewById(R.id.tvRecordDescription);
		TextView tvDetails = (TextView) view.findViewById(R.id.tvDetails);
		switch (record.getRecordType()) {
			case "System":
				tvDesc.setText(record.getDescription());
				tvDetails.setText(record.getAdmin() + " - " + record.getTimestamp());
				break;
			case "Connection":
				tvDesc.setText(record.getDescription());
				tvDetails.setText(record.getAdmin() + " - " + record.getTimestamp());				break;
			case "Campaign":
				tvDesc.setText(record.getDescription());
				tvDetails.setText(record.getAdmin() + " - " + record.getTimestamp());				break;
			case "Resource":
				tvDesc.setText(record.getDescription());
				tvDetails.setText(record.getAdmin() + " - " + record.getTimestamp());				break;
			case "Default":
				tvDesc.setText(record.getDescription());
				tvDetails.setText(record.getAdmin() + " - " + record.getTimestamp());				break;
		}

		return view;
	}

}
