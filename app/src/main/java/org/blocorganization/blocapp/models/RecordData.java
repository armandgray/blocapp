package org.blocorganization.blocapp.models;

import org.blocorganization.blocapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecordData {

	private List<Record> records = new ArrayList<Record>();
	public List<Record> getRecords() {
		return records;
	}

	public RecordData() {
		records.add(new Record(RecordType.SYSTEM, "System", R.drawable.bloc_logo_original_positive, "Birthday", "10/15/16"));
		records.add(new Record(RecordType.CAMPAIGN, "Member", R.drawable.profile1_pic, "Bloc Party", "12/12/16"));
		records.add(new Record(RecordType.CONNECTION, "Daniela", R.drawable.profile2_pic, "Daniela is friends with Armand", "12/24/06"));
		records.add(new Record(RecordType.RESOURCE, "Bloc President", R.drawable.profile2_pic, "Bring your notebook to class everyday", "1/4/96"));
	}

}
